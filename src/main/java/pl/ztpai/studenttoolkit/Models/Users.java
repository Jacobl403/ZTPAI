package pl.ztpai.studenttoolkit.Models;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@ToString
public class Users implements UserDetails  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "user_sequence")

    private Long userId;
    private String login;
    private String password;
    private String email;
    private Integer amountOfSubjects=0;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private Boolean locked=false;
    private Boolean enabled=false;

    @OneToMany(mappedBy="user",fetch=FetchType.LAZY,cascade = CascadeType.PERSIST)
    private List<Subject>subjects=new ArrayList<Subject>();

    @OneToMany(mappedBy="user",fetch=FetchType.LAZY,cascade = CascadeType.PERSIST)
    private List<Activity>activities=new ArrayList<Activity>();

    public Users(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority=new SimpleGrantedAuthority(UserRole.USER.name());
        return Collections.singletonList(authority);
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
