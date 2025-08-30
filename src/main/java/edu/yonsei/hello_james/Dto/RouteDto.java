package edu.yonsei.hello_james.Dto;

import edu.yonsei.hello_james.entity.Facility;
import edu.yonsei.hello_james.entity.Route;
import edu.yonsei.hello_james.entity.RouteStop;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalTime;
import java.util.List;
@Data
public class RouteDto {

    private LocalTime startTime;
    private String day ;

    private String name;

    private String explanation;

    private Long  busNumber;


    private static ModelMapper modelMapper = new ModelMapper();

    public Route toRoute() {
        return modelMapper.map(this, Route.class);
    }

    public static RouteDto toRouteDto(Route route) {
        return modelMapper.map(route, RouteDto.class);
    }


}
