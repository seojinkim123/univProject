package edu.yonsei.hello_james.Dto;

import edu.yonsei.hello_james.entity.Facility;
import edu.yonsei.hello_james.entity.RouteStop;
import edu.yonsei.hello_james.entity.Stop;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.List;

@Data
public class StopDto {
    private String name;


    private String explanation;

    private String imagePath ;
    private String location;


    private static ModelMapper modelMapper = new ModelMapper();

    public Stop toStop() {
        return modelMapper.map(this, Stop.class);
    }

    public static StopDto toStopDto(Stop stop) {
        return modelMapper.map(stop, StopDto.class);
    }




}
