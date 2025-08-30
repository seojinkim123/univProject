package edu.yonsei.hello_james.service;

import edu.yonsei.hello_james.Dto.FacilityDto;
import edu.yonsei.hello_james.entity.Facility;
import edu.yonsei.hello_james.repository.FacilityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FacilityService {


    private final FacilityRepository FacilityRepository;
    private final ImageService ImageService;
    private final ImageService imageService;
    private final FacilityRepository facilityRepository;

    public Facility saveFacility(FacilityDto facilityDto) {
        Facility facility = facilityDto.toFacility();

        // 이미지 파일이 있는 경우 저장하고 경로 설정
        if (facilityDto.getImageFile() != null && !facilityDto.getImageFile().isEmpty()) {
            try {
                String imagePath = imageService.saveImageFile(facilityDto.getImageFile());
                facility.setImagePath(imagePath);
            } catch (IOException e) {
                throw new RuntimeException("이미지 파일 저장 중 오류가 발생했습니다.", e);
            }
        }

        return FacilityRepository.save(facility);
    }

    public Optional<Facility> findFacilityById (Long Id) {
        return FacilityRepository.findById(Id);
    }

    public Optional<Facility> findFacilityByName (String name) {
        return FacilityRepository.findByName(name);
    }

    public List<Facility> findFacilityAll() {
        return FacilityRepository.findAll();
    }

    public void deleteFacilityById(Long Id) {

        FacilityRepository.deleteById(Id);

    }

    public Facility updateFacility(FacilityDto FacilityDto,Long Id) {
        Facility Facility = FacilityRepository.findById(Id).
                orElseThrow(()->new IllegalArgumentException("Facility not found"+Id));
        Facility.updateFacility(FacilityDto);

        return Facility;
    }



}
