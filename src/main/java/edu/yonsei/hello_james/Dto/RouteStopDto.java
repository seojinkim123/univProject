package edu.yonsei.hello_james.Dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
public class RouteStopDto {

    LocalTime startTime;
    String day;
    List<StopDto> stops;


}
