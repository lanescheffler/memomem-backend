package net.yorksolutions.memomembackend;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MemosService {

    private UserListRepository repository;
    private ThreadListRepository threadListRepository;

    private HashMap<UUID, Long> tokenMap;

    @Autowired
    public MemosService(@NonNull UserListRepository repository, ThreadListRepository threadListRepository) {
        this.repository = repository;
        this.threadListRepository = threadListRepository;
        this.tokenMap = new HashMap<>();
    }

    public void registar(String username, String password) {
        if (repository.findByUsername(username).isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        } else {
            repository.save(new UserList(username, password));
        }
    }

    public UUID login(String username, String password) {
        Optional<UserList> result = repository.findByUsernameAndPassword(username, password);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        } else {
            final UUID token = UUID.randomUUID();
            tokenMap.put(token, result.get().id);
            return token;
        }
    }

    public void anotherFunction(UUID token) {
        // check to see if the user is logged in.

        // if they are not, return unauthorized.

        // else, do some functionality.
    }

    public void logout(UUID token) {
        // remove their token, id from the hashmap
    }

    public void CreateThread(@NotNull ThreadList thread) {
        Optional<ThreadList> createdThread = threadListRepository.findByTitleAndInvitedUser(thread.getTitle(), thread.getInvitedUser());
        if (createdThread.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        } else {
            ThreadList newThread = new ThreadList(thread.getUserCreated(), thread.getInvitedUser(), thread.getTitle(), thread.getDate());
            threadListRepository.save(newThread);
        }
    }

    public List<ThreadList> displayThreadList () {
        List<ThreadList> threadList = (List<ThreadList>) threadListRepository.findAll();
        return threadList;
    }

    public void editThread(ThreadList thread, Long id) {
        threadListRepository.findById(id).map(e -> {
            e.setInvitedUser(thread.getInvitedUser());
            e.setTitle(thread.getTitle());
            return threadListRepository.save(e);
        });
    }

    public void deleteThread (Long id) {
        Optional<ThreadList> result = threadListRepository.findById(id);
        if (result.isPresent()) {
            threadListRepository.deleteById(result.get().getId());
        }
        else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

//    public List<ThreadList> displayThreadList () {
//        List<ThreadList> threadList = (List<ThreadList>) ThreadListRepository.findAll();
//        return threadList;
//    }

//    public void updateThreadList(Thread thread) {
//        // exists or not
//        for(Thread currentThread: thread) {
//            if(currentThread.getId() == thread.getId()) {
//                currentThread.setId(thread.getId();
//            }
//        }
//    }
}
