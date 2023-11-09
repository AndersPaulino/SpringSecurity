package br.com.uniamerica.aulasecurity.app.repository;

import br.com.uniamerica.aulasecurity.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT e FROM User where e.login = ?1")
    User findByLogin(@Param("login") String login);
}
