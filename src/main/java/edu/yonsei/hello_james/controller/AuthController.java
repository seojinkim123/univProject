package edu.yonsei.hello_james.controller;

import edu.yonsei.hello_james.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

//@Controller
//public class AuthController {
//
//    private final UserService userService;
//
//    public AuthController(UserService userService) {
//        this.userService = userService;
//    }
//
//
//    @GetMapping("/login")
//    public String login() {
//        return "login"; // login.html 템플릿 반환
//    }
//
//    @GetMapping("/join")
//    public String showRegistrationForm() {
//        return "join"; // join.html 템플릿 반환
//    }
//
//    @PostMapping("/join")
//    public String registerUser(@RequestParam String username, @RequestParam String password) {
//        userService.registerNewUser(username, password);
//        return "redirect:/login?registered=true"; // 회원가입 성공 후 로그인 페이지로 리다이렉트
//    }
//
//
//}

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String login() {
        return "login"; // login.html 템플릿 반환
    }

    @GetMapping("/join")
    public String showRegistrationForm() {
        return "join"; // join.html 템플릿 반환
    }

    @PostMapping("/join")
    public String registerUser(@RequestParam String username, @RequestParam String password) {
        userService.registerNewUser(username, password);
        return "redirect:/login?registered=true"; // 회원가입 성공 후 로그인 페이지로 리다이렉트
    }

    // Add endpoint to return authentication status
    @GetMapping("/api/auth/status")
    @ResponseBody
    public Map<String, Object> authStatus(Principal principal) {
        Map<String, Object> result = new HashMap<>();
        result.put("authenticated", principal != null);
        if (principal != null) {
            result.put("username", principal.getName());
        }
        return result;
    }

}