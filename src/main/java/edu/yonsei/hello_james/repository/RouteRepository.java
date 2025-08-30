package edu.yonsei.hello_james.repository;

import edu.yonsei.hello_james.entity.Route;
import edu.yonsei.hello_james.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long> {
    Optional<Route> findByName(String name);
}
