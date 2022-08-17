package net.yorksolutions.memomembackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/")
@CrossOrigin
public class MemosController {

    private MemosService service;

    @Autowired
    public MemosController(@NonNull MemosService service) { this.service = service; }

    @GetMapping("/registar")
    public void registar(@RequestParam String username, String password) { service.registar(username, password); }

    @GetMapping("login")
    public UUID login(@RequestParam String username, String password) {return service.login(username, password); }

    @PostMapping(
            value = "/createThread",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )

    public void createThread(@RequestBody ThreadList thread) {
        service.CreateThread(thread);
    }

    @GetMapping("/getThreadList")
    public List<ThreadList> displayThreadList () {

        return service.displayThreadList();
    }

    @PutMapping(
            value="/editThread/{id}"
    )
    public void editThread(@RequestBody ThreadList threadObj, @PathVariable Long id) {
        service.editThread(threadObj, id);
    }

    @DeleteMapping(
            value="/deleteThread/{id}"
    )
    public void deleteThread(@PathVariable Long id) {
        service.deleteThread(id);
    }

//    @PutMapping(
//            value = "/updateThreadList",
//            consumes = {MediaType.APPLICATION_JSON_VALUE},
//            produces = {MediaType.APPLICATION_JSON_VALUE}
//    )
//
//    public void updateThreadList(RequestBody ThreadList thread) {
//        service.updateThreadList(thread);
//    }



}
