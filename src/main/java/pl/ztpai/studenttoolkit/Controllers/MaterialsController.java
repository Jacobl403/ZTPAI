package pl.ztpai.studenttoolkit.Controllers;


import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.ztpai.studenttoolkit.Services.MaterialsService;

@RestController
@RequestMapping("/materialy")
@CrossOrigin
public class MaterialsController {
    @Autowired
    private MaterialsService materialsService;
    //tutaj musze sie upewnic jak przesyłać pliki
    @GetMapping
    public ObjectNode getMaterials(){
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("kuba_lag@o2.pl",
                "gumisie"));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return materialsService.getMaterials(auth);
    }
    @PostMapping
    public void saveNotes(){
    }
}
