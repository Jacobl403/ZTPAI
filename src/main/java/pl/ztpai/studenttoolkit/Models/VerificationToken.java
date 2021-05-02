package pl.ztpai.studenttoolkit.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class VerificationToken {

    @Id
    @SequenceGenerator(
            name = "token_sequence",
            sequenceName = "token_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "token_sequence")

    private Long id;

    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime created;
    @Column(nullable = false)
    private LocalDateTime expired;
    private LocalDateTime confirmed;
    @ManyToOne
    @JoinColumn(nullable = false, name ="users_id" )
    private Users appUsers;

    public VerificationToken(String token, LocalDateTime created, LocalDateTime expired, Users appUsers) {
        this.token = token;
        this.created = created;
        this.expired = expired;
        this.appUsers=appUsers;
    }
}
