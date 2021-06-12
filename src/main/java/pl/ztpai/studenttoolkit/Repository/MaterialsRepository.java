package pl.ztpai.studenttoolkit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ztpai.studenttoolkit.Models.Materials;

@Repository
public interface MaterialsRepository extends JpaRepository<Materials,Long> {
}
