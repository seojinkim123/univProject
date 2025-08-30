package edu.yonsei.hello_james.controller;


import edu.yonsei.hello_james.Dto.*;
import edu.yonsei.hello_james.entity.*;
import edu.yonsei.hello_james.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


// 사랑합니다.
@Controller
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {
    private final UserService userService;
    private final FacilityService facilityService;
    private final RouteService routeService;
    private final StopService stopService;
    private final RouteStopService routeStopService;
    private final InternalService internalService;
    private final ImageService imageService;

//    post 등록

    @PostMapping("/users")
    @ResponseBody
    public User UserApi(@RequestBody UserDto userDto) {
        User user =userService.saveUser(userDto);
        return user;
    }

    @PostMapping("/users/batch")
    @ResponseBody
    public List<User> UserApi(@RequestBody List<UserDto> userDtoList) {
        List<User> users = new ArrayList<>();
        for (UserDto userDto : userDtoList) {
            User user = userService.saveUser(userDto);
            users.add(user);
        }
        return users;
    }


    @PostMapping("/facilities")
    @ResponseBody
    public Facility FacilityApi(@RequestBody FacilityDto facilityDto) {
        Facility facility =facilityService.saveFacility(facilityDto);
        return facility;
    }

    @PostMapping("/facilities/batch")
    @ResponseBody
    public List<Facility> FacilityApi(@RequestBody List<FacilityDto> facilityDtoList) {
        List<Facility> facilities = new ArrayList<>();
        for (FacilityDto facilityDto : facilityDtoList) {
            Facility facility = facilityService.saveFacility(facilityDto);
            facilities.add(facility);
        }
        return facilities;
    }

    @PostMapping("/facilities/{name}/internals")
    @ResponseBody
    public  Internal InternalApi(@PathVariable String name, @RequestBody InternalDto internalDto) {

        return internalService.saveInternal(name, internalDto);

    }





    @PostMapping("/routes")
    @ResponseBody
    public Route RouteApi(@RequestBody RouteDto RouteDto) {
        Route route = routeService.saveRoute(RouteDto);
        return route;
    }

    @PostMapping("/stops")
    @ResponseBody
    public Stop StopApi(@RequestBody StopDto stopDto) {
        Stop stop = stopService.saveStop(stopDto);
        return stop;
    }

    @PostMapping("/routeStops")
    @ResponseBody
    public String  RouteStopApi(@RequestBody RouteStopDto routeStopDto) {
        //routeStopDto 는  StartTime 과  StopDto List
        // route를 신규생성 &  stop 은 기존에 있으면 가져오고 없으면 객체 새로 생성한다.

       routeStopService.saveRouteStop(routeStopDto);

       return "ok";
    }


    @PostMapping("/routeStops/batch")
    @ResponseBody
    public String  RouteStopApi(@RequestBody List<RouteStopDto> routeStopDtoList) {
        //routeStopDto 는  StartTime 과  StopDto List
        // route를 신규생성 &  stop 은 기존에 있으면 가져오고 없으면 객체 새로 생성한다.


        for ( RouteStopDto routeStopDto : routeStopDtoList) {
            routeStopService.saveRouteStop(routeStopDto);

        }


        return "ok";
    }






}
