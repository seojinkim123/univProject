package edu.yonsei.hello_james.Dto;

import edu.yonsei.hello_james.entity.Facility;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Data

public class FacilityDto {

    private String name;
    private String address;
    private String explanation;
    private String operation;
    private String category;

//  DTo에서 이미지 파일을 가져오고   엔티티에서는 imagePath를 가진다.
    private MultipartFile imageFile;



    private static ModelMapper modelMapper = new ModelMapper();

    public Facility toFacility() {
        return modelMapper.map(this, Facility.class);
    }

    public static FacilityDto toFacilityDto(Facility Facility) {
        return modelMapper.map(Facility, FacilityDto.class);
    }
    
}
