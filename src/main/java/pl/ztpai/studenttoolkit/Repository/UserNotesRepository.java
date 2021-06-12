package pl.ztpai.studenttoolkit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ztpai.studenttoolkit.Models.UserNotes;

@Repository
public interface UserNotesRepository extends JpaRepository<UserNotes,Long> {
}
