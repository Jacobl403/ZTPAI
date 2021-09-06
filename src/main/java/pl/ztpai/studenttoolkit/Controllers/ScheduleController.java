package pl.ztpai.studenttoolkit.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.ztpai.studenttoolkit.Payload.ScheduleView;
import pl.ztpai.studenttoolkit.Services.ScheduleService;

@RestController
@RequestMapping("/planzajec")
@CrossOrigin
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @GetMapping
    public ScheduleView getSchedule(){
//        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("kuba_lag@o2.pl", "gumisie"));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       return scheduleService.getSchedule(auth);
    }
    @PostMapping("/zapiszstan")
    public ScheduleView saveSchedule (@RequestBody ScheduleView request){
//        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("kuba_lag@o2.pl", "gumisie"));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      return   scheduleService.saveSchedule(auth,request);
    }

}
