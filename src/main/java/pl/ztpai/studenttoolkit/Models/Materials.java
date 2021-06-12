package pl.ztpai.studenttoolkit.Models;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Materials {
    @Id
    @GeneratedValue
    private Long materialsId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Subject.class)
    @JoinColumn(name="subjectmaterials", referencedColumnName = "subjectID", nullable = false)
    private Subject subject;
    @Lob
    private byte[] file;
}
