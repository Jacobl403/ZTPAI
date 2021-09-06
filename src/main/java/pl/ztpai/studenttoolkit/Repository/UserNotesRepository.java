package pl.ztpai.studenttoolkit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.ztpai.studenttoolkit.Models.Subject;
import pl.ztpai.studenttoolkit.Models.UserNotes;

import java.util.Optional;

@Repository
public interface UserNotesRepository extends JpaRepository<UserNotes,Long> {
    @Transactional
    Optional<Integer> deleteByNoteTextAndAndSubject(String noteText , Subject subject);
    //zmiana
}
