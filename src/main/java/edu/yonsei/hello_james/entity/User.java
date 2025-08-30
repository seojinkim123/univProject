package edu.yonsei.hello_james.entity;

import edu.yonsei.hello_james.Dto.UserDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.HashSet;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;



    @ElementCollection(fetch = FetchType.EAGER) // 또는 @ManyToMany로 Role 엔티티와 매핑
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role_name")
    private Set<String> roles = new HashSet<>(); // 예: "ROLE_USER", "ROLE_ADMIN"

    // 편의 메서드
    public void addRole(String role) {
        this.roles.add(role);
    }

    public void updateUser(UserDto userDto) {
        this.email=userDto.getEmail();
        this.name = userDto.getName();
    }

}
