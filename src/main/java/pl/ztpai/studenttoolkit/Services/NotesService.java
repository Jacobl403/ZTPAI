package pl.ztpai.studenttoolkit.Services;


import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.ztpai.studenttoolkit.Models.Subject;
import pl.ztpai.studenttoolkit.Models.UserNotes;
import pl.ztpai.studenttoolkit.Models.Users;
import pl.ztpai.studenttoolkit.Payload.NotesView;
import pl.ztpai.studenttoolkit.Repository.SubjectRepository;
import pl.ztpai.studenttoolkit.Repository.UserNotesRepository;
import pl.ztpai.studenttoolkit.Repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class NotesService {
    private final UserRepository userDB;
    private final SubjectRepository subjectRepository;
    private final UserNotesRepository userNotesRepository;
    public NotesView getNotes(Authentication auth){
        Optional<Users> foundUser=userDB.findByEmail(auth.getPrincipal().toString());
        return new NotesView(foundUser.get().getSubjects());
    }

    public NotesView saveNotes(Authentication auth, NotesView request){
        Optional<Users> foundUser=userDB.findByEmail(auth.getPrincipal().toString());
        Optional<Subject>foundSubject=subjectRepository.findSubjectByUserAndSubjectName(foundUser.get(), request.getSubject());
        UserNotes userNotes=new UserNotes(request.getText(),foundSubject.get());
        userNotesRepository.save(userNotes);
        return request;
    }
    public NotesView deleteNote(Authentication auth, NotesView request){
        Optional<Users> foundUser=userDB.findByEmail(auth.getPrincipal().toString());
        Optional<Subject>foundSubject=subjectRepository.findSubjectByUserAndSubjectName(foundUser.get(), request.getSubject());
        userNotesRepository.deleteByNoteTextAndAndSubject(request.getText(),foundSubject.get());
        return request;
    }
}
