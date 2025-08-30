package edu.yonsei.hello_james.entity;

import edu.yonsei.hello_james.Dto.FacilityDto;
import edu.yonsei.hello_james.Dto.StopDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Stop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "stop")
    List<RouteStop> routeStops = new ArrayList<>();

    private String explanation;

    private String imagePath ;

    private String location;


    public void updateStop(StopDto StopDto) {
        this.name = StopDto.getName();
        this.imagePath = StopDto.getImagePath();
        this.explanation = StopDto.getExplanation();
        this.location = StopDto.getLocation();

    }




}
