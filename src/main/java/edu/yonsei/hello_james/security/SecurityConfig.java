package edu.yonsei.hello_james.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // REST API의 경우 보통 비활성화
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login", "/join", "/css/**", "/js/**").permitAll() // 로그인, 회원가입 페이지 등은 모든 사용자 접근 허용
                        .requestMatchers("/admin/**").hasRole("ADMIN") // /admin 경로에는 ADMIN 역할만 접근 허용
                        .requestMatchers("/", "/transportation/**", "/facility/**").permitAll()
//                        .anyRequest().authenticated() // 그 외 모든 요청은 인증된 사용자만 접근 허용
                        .anyRequest().permitAll() // 그 외 모든 요청은    모두 허용
                )
                .formLogin(form -> form
                        .loginPage("/login") // 커스텀 로그인 페이지 경로
                        .loginProcessingUrl("/perform_login") // 로그인 폼 제출 시 처리할 URL (스프링 시큐리티가 처리)
                        .defaultSuccessUrl("/", true) // 로그인 성공 시 이동할 URL
                        .failureUrl("/login?error=true") // 로그인 실패 시 이동할 URL
                        .usernameParameter("username") // 로그인 폼에서 사용자 ID 필드의 이름
                        .passwordParameter("password") // 로그인 폼에서 비밀번호 필드의 이름
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // 로그아웃 처리 URL
                        .logoutSuccessUrl("/login?logout=true") // 로그아웃 성공 시 이동할 URL
                        .invalidateHttpSession(true) // HTTP 세션 무효화
                        .deleteCookies("JSESSIONID") // 쿠키 삭제
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 비밀번호 암호화를 위한 BCryptPasswordEncoder 사용
    }
}