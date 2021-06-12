package pl.ztpai.studenttoolkit.Controllers;


import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.ztpai.studenttoolkit.Services.NotesService;

@RestController
@RequestMapping("/notatki")
@AllArgsConstructor
@CrossOrigin
public class NotesController {
    private final NotesService notesService;
    @GetMapping
    public ObjectNode getNotes(){
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("kuba_lag@o2.pl",
                "gumisie"));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return notesService.getNotes(auth);
    }
    @PostMapping
    public void saveNotes(){
    }
}
