package edu.yonsei.hello_james.repository;

import edu.yonsei.hello_james.entity.Stop;
import edu.yonsei.hello_james.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StopRepository extends JpaRepository<Stop, Long> {
    Optional<Stop> findByName(String name);

}
