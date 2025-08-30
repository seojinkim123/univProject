package edu.yonsei.hello_james.service;


import edu.yonsei.hello_james.Dto.RouteDto;
import edu.yonsei.hello_james.entity.Route;
import edu.yonsei.hello_james.repository.RouteRepository;
import edu.yonsei.hello_james.repository.RouteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository RouteRepository;

    public Route saveRoute(RouteDto RouteDto) {
        return RouteRepository.save(RouteDto.toRoute());
    }

    public Optional<Route> findRouteById (Long Id) {
        return RouteRepository.findById(Id);
    }

    public Optional<Route> findRouteByName (String name) {
        return RouteRepository.findByName(name);
    }

    public List<Route> findRouteAll() {
        return RouteRepository.findAll();
    }

    public void deleteRouteById(Long Id) {

        RouteRepository.deleteById(Id);

    }

    public Route updateRoute(RouteDto RouteDto,Long Id) {
        Route Route = RouteRepository.findById(Id).
                orElseThrow(()->new IllegalArgumentException("Route not found"+Id));
        Route.updateRoute(RouteDto);

        return Route;
    }






}
