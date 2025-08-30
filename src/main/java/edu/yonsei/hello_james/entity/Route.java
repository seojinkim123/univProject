package edu.yonsei.hello_james.entity;

import edu.yonsei.hello_james.Dto.FacilityDto;
import edu.yonsei.hello_james.Dto.RouteDto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalTime startTime;
    private String day;

    private String name;

    private String explanation;

    private Long  busNumber;


    @OneToMany(mappedBy = "route")
    private List<RouteStop> routeStops = new ArrayList<>();



    public void updateRoute(RouteDto RouteDto) {
        this.name = RouteDto.getName();
        this.explanation = RouteDto.getExplanation();
        this.busNumber = RouteDto.getBusNumber();
        this.day = RouteDto.getDay();

    }



}
