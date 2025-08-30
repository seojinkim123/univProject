package edu.yonsei.hello_james.Dto;

import edu.yonsei.hello_james.entity.Internal;
import edu.yonsei.hello_james.entity.Stop;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter
@Setter
public class InternalDto {
    private String name;

    private String address;

    private String operation;

    private String explanation;



    private MultipartFile imageFile;





    private static ModelMapper modelMapper = new ModelMapper();

    public Internal toInternal() {
        return modelMapper.map(this, Internal.class);
    }

    public static InternalDto toInternalDto(Internal internal) {
        return modelMapper.map(internal, InternalDto.class);
    }



}
