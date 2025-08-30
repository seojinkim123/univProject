package edu.yonsei.hello_james.service;

import edu.yonsei.hello_james.Dto.UserDto;
import edu.yonsei.hello_james.Dto.UserDto;
import edu.yonsei.hello_james.entity.User;
import edu.yonsei.hello_james.entity.User;
import edu.yonsei.hello_james.repository.UserRepository;
import edu.yonsei.hello_james.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Transactional
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public User registerNewUser(String username, String rawPassword) {
        if (userRepository.findByName(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        User newUser = new User();
        newUser.setName(username);
        newUser.setPassword(passwordEncoder.encode(rawPassword)); // 비밀번호 암호화
        newUser.addRole("ROLE_USER"); // 기본 역할 부여
        return userRepository.save(newUser);
    }


    public User saveUser(UserDto UserDto) {
        return userRepository.save(UserDto.toUser());
    }

    public Optional<User> findUserById (Long Id) {
        return userRepository.findById(Id);
    }

    public Optional<User> findUserByName (String name) {
        return userRepository.findByName(name);
    }

    public List<User> findUserAll() {
        return userRepository.findAll();
    }

    public void deleteUserById(Long Id) {

        userRepository.deleteById(Id);

    }

    public User updateUser(UserDto UserDto,Long Id) {
        User User = userRepository.findById(Id).
                orElseThrow(()->new IllegalArgumentException("User not found"+Id));
        User.updateUser(UserDto);

        return User;
    }





}
