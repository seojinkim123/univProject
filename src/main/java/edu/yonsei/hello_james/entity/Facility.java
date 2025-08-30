package edu.yonsei.hello_james.entity;



import edu.yonsei.hello_james.Dto.FacilityDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String name;

    private String address;

    private String operation;

    private String explanation;


    private String category;

    @Column(name = "image_path")
    private String imagePath;


    @OneToMany(mappedBy = "facility")
    private List<Internal> internals = new ArrayList<>();

    public void updateFacility(FacilityDto FacilityDto) {
        this.name = FacilityDto.getName();
        this.address = FacilityDto.getAddress();

        this.explanation = FacilityDto.getExplanation();
        this.operation = FacilityDto.getOperation();
        this.category = FacilityDto.getCategory();

    }

}
