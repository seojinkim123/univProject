package edu.yonsei.hello_james.Dto;


import edu.yonsei.hello_james.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@Data
public class UserDto {
    private String name;
    private String email;

    private static ModelMapper modelMapper = new ModelMapper();

    public User toUser() {
        return modelMapper.map(this, User.class);
    }

    public static UserDto toUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}
