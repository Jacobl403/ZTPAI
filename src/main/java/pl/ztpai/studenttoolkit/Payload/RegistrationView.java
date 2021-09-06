package pl.ztpai.studenttoolkit.Payload;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationView {
    private final String email;
    private final String password;
    private  String status;

    public void setStatus(String status) {
        this.status = status;
    }
}
