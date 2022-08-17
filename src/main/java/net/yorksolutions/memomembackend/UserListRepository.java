package net.yorksolutions.memomembackend;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserListRepository extends CrudRepository<UserList, Long> {

    Optional<UserList> findByUsername(String username);

    Optional<UserList> findByUsernameAndPassword(String username, String password);
}
