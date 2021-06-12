package pl.ztpai.studenttoolkit.Services;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MaterialsService {

    public ObjectNode getMaterials(Authentication auth){
        ObjectMapper mapper=new ObjectMapper();
        ObjectNode JsonFormat=mapper.createObjectNode();


        return JsonFormat;
    }
}
