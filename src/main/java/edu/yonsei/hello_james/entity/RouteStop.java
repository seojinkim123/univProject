package edu.yonsei.hello_james.entity;

import edu.yonsei.hello_james.Dto.FacilityDto;
import edu.yonsei.hello_james.Dto.RouteStopDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Data
@Getter
@Setter
public class RouteStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // 정류장 순서
    private int stopOrder;

    // 예정 도착 시간
    private LocalTime arrivalTime;


    @ManyToOne
    @JoinColumn(name="routeId")
    private Route route;

    @ManyToOne
    @JoinColumn(name="stopId")
    private Stop stop;



}
