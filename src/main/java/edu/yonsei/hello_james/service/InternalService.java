package edu.yonsei.hello_james.service;

import edu.yonsei.hello_james.Dto.FacilityDto;
import edu.yonsei.hello_james.Dto.InternalDto;
import edu.yonsei.hello_james.entity.Facility;
import edu.yonsei.hello_james.entity.Internal;
import edu.yonsei.hello_james.repository.InternalRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class InternalService {

    private final InternalRepository internalRepository;
    private final FacilityService facilityService;
    private final ImageService imageService;

    public Internal saveInternal(String name, InternalDto internalDto) {
        Facility facility = facilityService.findFacilityByName(name).
                orElseThrow(()->new IllegalArgumentException("Facility not found"+name));

        Internal internal = internalDto.toInternal();


        if (internalDto.getImageFile() != null && !internalDto.getImageFile().isEmpty()) {
            try {
                String imagePath = imageService.saveImageFile(internalDto.getImageFile());
                internal.setImagePath(imagePath);
            } catch (IOException e) {
                throw new RuntimeException("이미지 파일 저장 중 오류가 발생했습니다.", e);
            }
        }

        internal.setFacility(facility);
        facility.getInternals().add(internal);


        return internalRepository.save(internal);




    }

    public Optional<Internal> findInternalById (Long Id) {
        return internalRepository.findById(Id);
    }

    public Optional<Internal> findInternalByName (String name) {
        return internalRepository.findByName(name);
    }

    public List<Internal> findInternalAll() {
        return internalRepository.findAll();
    }

    public void deleteInternalById(Long Id) {

        internalRepository.deleteById(Id);

    }

    public Internal updateInternal(InternalDto internalDto,Long Id) {
        Internal internal = internalRepository.findById(Id).
                orElseThrow(()->new IllegalArgumentException("Internal not found"+Id));
        internal.updateInternal(internalDto);

        return internal;
    }


}
