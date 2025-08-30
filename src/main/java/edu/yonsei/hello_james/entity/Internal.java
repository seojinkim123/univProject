package edu.yonsei.hello_james.entity;


import edu.yonsei.hello_james.Dto.FacilityDto;
import edu.yonsei.hello_james.Dto.InternalDto;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Internal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String name;

    private String address;

    private String operation;

    private String explanation;

    @Column(name = "image_path")
    private String imagePath;

    @ManyToOne
    @JoinColumn(name="facilityId")
    private Facility facility;



    public void updateInternal(InternalDto internalDto) {

        this.name = internalDto.getName();
        this.address = internalDto.getAddress();
        this.explanation = internalDto.getExplanation();
        this.operation = internalDto.getOperation();



    }

}
