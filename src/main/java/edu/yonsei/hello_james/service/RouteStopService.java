package edu.yonsei.hello_james.service;


import edu.yonsei.hello_james.Dto.RouteDto;
import edu.yonsei.hello_james.Dto.RouteStopDto;
import edu.yonsei.hello_james.Dto.StopDto;
import edu.yonsei.hello_james.entity.Route;
import edu.yonsei.hello_james.entity.RouteStop;
import edu.yonsei.hello_james.entity.Stop;
import edu.yonsei.hello_james.repository.RouteRepository;
import edu.yonsei.hello_james.repository.RouteStopRepository;
import edu.yonsei.hello_james.repository.StopRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class RouteStopService {

    private final RouteStopRepository routeStopRepository;
    private final StopService stopService;
    private final RouteService routeService;

    public void saveRouteStop(RouteStopDto routeStopDto) {

        RouteDto routeDto = new RouteDto();

        routeDto.setStartTime(routeStopDto.getStartTime());
        routeDto.setDay(routeStopDto.getDay());

        Route route = routeService.saveRoute(routeDto);

        int order = 0 ;


        for (StopDto stopDto : routeStopDto.getStops()) {
            Stop stop =stopService.findStopByName(stopDto.getName())
                    .orElseGet(() -> stopService.saveStop(stopDto));

            RouteStop routeStop = new RouteStop();
            routeStop.setRoute(route);
            routeStop.setStop(stop);
            routeStop.setStopOrder(order++);


            routeStopRepository.save(routeStop);

            route.getRouteStops().add(routeStop);
            stop.getRouteStops().add(routeStop);

        }


    }

    public Optional<RouteStop> findRouteStopById (Long Id) {
        return routeStopRepository.findById(Id);
    }



    public List<RouteStop> findRouteStopAll() {
        return routeStopRepository.findAll();
    }

    public void deleteRouteStopById(Long Id) {

        routeStopRepository.deleteById(Id);

    }


    public List<Route> findRoutesByStartAndEndStops(String startStopName, String endStopName) {
        Stop startStop = stopService.findStopByName(startStopName)
                .orElseThrow(() -> new IllegalArgumentException("Start Stop not found with name: " + startStopName));
        Stop endStop = stopService.findStopByName(endStopName)
                .orElseThrow(() -> new IllegalArgumentException("End Stop not found with name: " + endStopName));

        log.info("Start Stop: " + startStopName);
        log.info("End Stop: " + endStopName);
        // RouteStopRepository에 정의된 JPQL 쿼리를 사용하여 검색
        List<Route> routes = routeStopRepository.findRoutesByStartStopAndEndStop(startStop, endStop);

        return routes;

    }



}
