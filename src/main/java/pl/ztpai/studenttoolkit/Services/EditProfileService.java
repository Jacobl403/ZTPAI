package pl.ztpai.studenttoolkit.Services;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.ztpai.studenttoolkit.Models.Subject;
import pl.ztpai.studenttoolkit.Models.Users;
import pl.ztpai.studenttoolkit.Repository.UserRepository;
import pl.ztpai.studenttoolkit.RequestClass.editProfileRequest;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EditProfileService {
    private final UserRepository userDB;

    public ObjectNode loadData(Authentication auth){
        ObjectMapper mapper=new ObjectMapper();
        ObjectNode JsonFormat=mapper.createObjectNode();
        Optional<Users> findedUser=userDB.findByEmail(auth.getPrincipal().toString());

        String email=findedUser.get().getEmail();
        String password=findedUser.get().getPassword();
        Integer amountOfSubjects=findedUser.get().getAmountOfSubjects();
        List<Subject> subjectsList = findedUser.get().getSubjects();

        JsonFormat.put("email",email);
        JsonFormat.put("password",password);
        JsonFormat.put("amountOfSubjects",amountOfSubjects.toString());
        JsonFormat.put("Subjects",findedUser.get().getSubjects().toString());

        return JsonFormat;
    }
    public void saveChanges(Authentication auth, editProfileRequest request){
        //dowiedziec sie czy mam coś z powrotem wysyłać
        Optional<Users> findedUser=userDB.findByEmail(auth.getPrincipal().toString());
        findedUser.get().setEmail(request.getEmail());
        findedUser.get().setPassword(request.getPassword());
        findedUser.get().setAmountOfSubjects(request.getAmountOfSubjects());
        findedUser.get().setSubjects(request.getSubjects());
        userDB.save(findedUser.get());
    }
}
