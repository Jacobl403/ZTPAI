package pl.ztpai.studenttoolkit.Controllers;


import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.ztpai.studenttoolkit.Payload.NotesView;
import pl.ztpai.studenttoolkit.Services.NotesService;

@RestController
@RequestMapping("/notatki")
@AllArgsConstructor
@CrossOrigin
public class NotesController {
    private final NotesService notesService;
    @GetMapping
    public @ResponseBody NotesView getNotes(){
//        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("kuba_lag@o2.pl",
//                "gumisie"));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return notesService.getNotes(auth);
    }
    @PostMapping("/dodajnotatke")
    public NotesView saveNotes(@RequestBody NotesView request){
//        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("kuba_lag@o2.pl",
//                "gumisie"));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return notesService.saveNotes(auth,request);
    }
    @PostMapping("/usunnotatke")
    public NotesView deleteNote(@RequestBody NotesView request){
//        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("kuba_lag@o2.pl",
//                "gumisie"));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return notesService.deleteNote(auth,request);
    }
}
