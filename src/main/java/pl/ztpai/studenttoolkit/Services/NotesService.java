package pl.ztpai.studenttoolkit.Services;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.ztpai.studenttoolkit.Models.Subject;
import pl.ztpai.studenttoolkit.Models.Users;
import pl.ztpai.studenttoolkit.Repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class NotesService {
    private final UserRepository userDB;

    public ObjectNode getNotes(Authentication auth){
        ObjectMapper mapper=new ObjectMapper();
        ObjectNode JsonFormat=mapper.createObjectNode();

        Optional<Users> findedUser=userDB.findByEmail(auth.getPrincipal().toString());

        for (Subject name :findedUser.get().getSubjects()){
            JsonFormat.put(name.getSubjectName(),name.getUserNotes().toString());
        }
        //{Nokia:["blabla","blabla2","blasd3"]}
        return JsonFormat;
    }


//    public String getNotes(){
//        return "it also works";
//    }
}
