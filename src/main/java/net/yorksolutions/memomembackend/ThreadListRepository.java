package net.yorksolutions.memomembackend;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThreadListRepository extends CrudRepository<ThreadList, Long> {

    Optional<ThreadList> findByTitleAndInvitedUser(String title, String invitedUser);

    Optional<ThreadList> findById(Long id);
//    Optional<ThreadList> findByCurrentUser(String currentUser);
//    Optional<ThreadList> findByTitle(String title);

}
