package pl.ztpai.studenttoolkit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ztpai.studenttoolkit.Models.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {
}
