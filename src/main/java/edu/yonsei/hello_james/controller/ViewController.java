package edu.yonsei.hello_james.controller;

import edu.yonsei.hello_james.Dto.*;
import edu.yonsei.hello_james.entity.*;
import edu.yonsei.hello_james.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ViewController {

    private final FacilityService facilityService;
    private final UserService userService;
    private final StopService stopService;
    private final RouteService routeService;
    private final RouteStopService routeStopService;
    private final InternalService internalService;
    private final ImageService imageService;


    @GetMapping
    public String MainPage(Model model) {

        return "mainPage";
    }


    @GetMapping("/restaurant")
    public String restaurant(Model model) {

        return "restaurant";
    }


    @GetMapping("/facility")
    public String FactilityPage(Model model) {

        List<Facility> facilities = facilityService.findFacilityAll().stream()
                .toList();

        model.addAttribute("facilities", facilities);

        return "facility";
    }






    @GetMapping("/facilities/{name}")
    public String FacilitiesPage(@PathVariable String name, Model model) {
        Facility facility = facilityService.findFacilityByName(name)
                .orElse(new Facility());
        List<Internal> internals = facility.getInternals();

        model.addAttribute("facility", facility);
        model.addAttribute("internals", internals);

        return "facilityInternal";
    }


    @GetMapping("/transportation")
    public String TransportationPage(Model model) {
        List<Stop> stops = stopService.findStopAll();
        model.addAttribute("stops", stops);
        return "transportation";
    }




    @GetMapping("/transportation/search")
    public String TransportationSearchPage(@RequestParam String departure, @RequestParam String arrival, Model model) {


        List<Route> matchingRoutes = routeStopService.findRoutesByStartAndEndStops(departure, arrival);

//        log.info("Routes found: hhh" + matchingRoutes);
        model.addAttribute("routes", matchingRoutes);


        return "transportSearchPage";
    }


    @GetMapping("/images")
    public String imageUpload() {
        return "upload";
    }

    @GetMapping("/ad")
    public String AdminPage(Model model) {

        model.addAttribute("facilities", facilityService.findFacilityAll());
        model.addAttribute("stops", stopService.findStopAll());
        model.addAttribute("routes", routeService.findRouteAll());
        model.addAttribute("routeStops", routeStopService.findRouteStopAll());
        model.addAttribute("internals", internalService.findInternalAll());
        model.addAttribute("users", userService.findUserAll());


        return "admin";
    }



    @GetMapping("/ad/{entity}")
    public String AdminPage(Model model, @PathVariable String entity) {

        model.addAttribute("facilities", facilityService.findFacilityAll());
        model.addAttribute("stops", stopService.findStopAll());
        model.addAttribute("routes", routeService.findRouteAll());
        model.addAttribute("routeStops", routeStopService.findRouteStopAll());
        model.addAttribute("internals", internalService.findInternalAll());
        model.addAttribute("users", userService.findUserAll());


        return "admin"+entity;
    }




    @GetMapping("ad/facilities/new")
    public String FactilityNewPage(Model model) {
        return "facilityForm";
    }

    @GetMapping("ad/facilities/{name}")
    public String facilitiesSpecificApi(@PathVariable String name,Model model) {
        Facility facility = facilityService.findFacilityByName(name)
                .orElse(new Facility());

        List<Internal> internals = facility.getInternals();

        model.addAttribute("interanals", internals);
        model.addAttribute("facilitiyName", name);

        return "admininternals";
    }


    @PostMapping("ad/facilities")
    public String facilitiesApi(@ModelAttribute FacilityDto facilityDto) {
        // 파일 검증
        if (facilityDto.getImageFile().isEmpty()) {
            throw new IllegalArgumentException("이미지 파일이 필요합니다.");
        }

        // 파일 타입 검증
        String contentType = facilityDto.getImageFile().getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("이미지 파일만 업로드 가능합니다.");
        }

        try {
            // 파일 저장
            String imagePath = imageService.saveImageFile(facilityDto.getImageFile());


            System.out.println("시설 등록 완료: " + facilityDto.getName());


            facilityService.saveFacility(facilityDto);
            return "redirect:/ad/facilities";



        } catch (IOException e) {
            throw new RuntimeException("파일 저장 중 오류가 발생했습니다.", e);
        }
    }










    @GetMapping("ad/routes/new")
    public String RoutesNewPage(Model model) {
        return "routeForm";
    }

    @PostMapping("ad/routes")
    public String routesApi(@RequestBody RouteDto routeDto) {
        routeService.saveRoute(routeDto);
        return "redirect:/ad/routes";
    }




    @GetMapping("ad/stops/new")
    public String StopNewPage(Model model) {
        return "stopForm";

    }
    @PostMapping("ad/stops")
    public String stopsApi(@RequestBody StopDto stopDto) {
        stopService.saveStop(stopDto);
        return "redirect:/ad/stops";
    }




    @GetMapping("ad/facilities/{name}/internals/new")
    public String InternalsNewPage(Model model, @PathVariable String name) {

        return "internalForm";
    }

    @PostMapping("ad/facilities/{name}/internals")
    public String internalsApi(@ModelAttribute InternalDto internalDto, @PathVariable String name) {
        // 파일 검증
        if (internalDto.getImageFile().isEmpty()) {
            throw new IllegalArgumentException("이미지 파일이 필요합니다.");
        }

        // 파일 타입 검증
        String contentType = internalDto.getImageFile().getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("이미지 파일만 업로드 가능합니다.");
        }

        try {

            String imagePath = imageService.saveImageFile(internalDto.getImageFile());

            System.out.println("시설 등록 완료: " + internalDto.getName());



            internalService.saveInternal(name, internalDto);
            return "redirect:/ad/facilities";



        } catch (IOException e) {
            throw new RuntimeException("파일 저장 중 오류가 발생했습니다.", e);
        }
    }


























    @GetMapping("ad/users/new")
    public String usersNewPage(Model model) {
        return "userForm";
    }

    @PostMapping("ad/users")
    public String UserApi(@RequestBody UserDto userDto) {
        userService.saveUser(userDto);
        return "redirect:/ad/facilities";
    }


    @GetMapping("ad/routeStops/new")
    public String routeStopsNewPage(Model model) {
        return "routeStopForm";
    }
    @PostMapping("ad/routeStops")
    public String routeStopsNewPage(@RequestBody RouteStopDto routeStopDto) {
        routeStopService.saveRouteStop(routeStopDto);
        return "redirect:/ad/routeStops";
    }





    //update
    @GetMapping("ad/facilities/{id}/update")
    public String FactilityupdatePage(Model model, @PathVariable Long id) {

        Facility facility = facilityService.findFacilityById(id)
                        .orElse(new Facility());

        model.addAttribute("facility",facility);

        return "facilityForm";
    }

    @GetMapping("ad/routes/{id}/update")
    public String RoutesupdatePage(Model model, @PathVariable Long id) {

        Route route = routeService.findRouteById(id)
                .orElse(new Route());

        model.addAttribute("route",route);

        return "routeForm";
    }

    @GetMapping("ad/stops/{id}/update")
    public String StopupdatePage(Model model, @PathVariable Long id) {

        Stop stop = stopService.findStopById(id)
                .orElse(new Stop());

        model.addAttribute("Stop",stop);

        return "stopForm";
    }


    @GetMapping("ad/internals/{id}/update")
    public String InternalsupdatePage(Model model, @PathVariable Long id) {

        Internal internal = internalService.findInternalById(id)
                .orElse(new Internal());

        model.addAttribute("internal",internal);

        return "internalForm";
    }

//    @GetMapping("/images/{id}/update")
//    public String ImagesupdatePage(Model model, @PathVariable Long id) {
//
//        Image image = imageService.findImageById(id)
//                .orElse(new Image());
//
//        model.addAttribute("image",image);
//        return "imageForm";
//    }


    @GetMapping("ad/users/{id}/update")
    public String usersupdatePage(Model model, @PathVariable Long id) {
        User user = userService.findUserById(id)
                .orElse(new User());

        model.addAttribute("user",user);
        return "userForm";
    }

//    @GetMapping("/routeStops/{id}/update")
//    public String routeStopsupdatePage(Model model, @PathVariable Long id) {
//
//        RouteStop routeStop = RouteStopService.findRouteStopById(id)
//                .orElse(new RouteStop());
//
//        model.addAttribute("routeStop",routeStop);
//        return "routeStopForm";
//    }




}
