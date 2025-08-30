package edu.yonsei.hello_james.repository;

import edu.yonsei.hello_james.entity.Route;
import edu.yonsei.hello_james.entity.RouteStop;
import edu.yonsei.hello_james.entity.Stop;
import edu.yonsei.hello_james.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RouteStopRepository extends JpaRepository<RouteStop, Long> {

    @Query("SELECT DISTINCT rs1.route " +
            "FROM RouteStop rs1 " +
            "JOIN RouteStop rs2 ON rs1.route = rs2.route " +
            "WHERE rs1.stop = :startStop " +
            "AND rs2.stop = :endStop " +
            "AND rs1.stopOrder < rs2.stopOrder")
    List<Route> findRoutesByStartStopAndEndStop(@Param("startStop") Stop startStop, @Param("endStop") Stop endStop);



}
