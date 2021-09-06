package pl.ztpai.studenttoolkit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ztpai.studenttoolkit.Models.Activity;


@Repository
public interface ActivityRepository extends JpaRepository<Activity,Long> {
}
