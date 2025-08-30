package edu.yonsei.hello_james.service;

import edu.yonsei.hello_james.Dto.StopDto;
import edu.yonsei.hello_james.entity.Stop;
import edu.yonsei.hello_james.repository.StopRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StopService {

    private final StopRepository stopRepository;
    private final ImageService imageService;

    public Stop saveStop(StopDto stopDto) {

        Stop stop = stopDto.toStop();


        return stopRepository.save(stop);

    }

    public Optional<Stop> findStopById (Long Id) {
        return stopRepository.findById(Id);
    }

    public Optional<Stop> findStopByName (String name) {
        return stopRepository.findByName(name);
    }

    public List<Stop> findStopAll() {
        return stopRepository.findAll();
    }

    public void deleteStopById(Long Id) {

        stopRepository.deleteById(Id);

    }

    public Stop updateStop(StopDto StopDto,Long Id) {
        Stop stop = stopRepository.findById(Id).
                orElseThrow(()->new IllegalArgumentException("Stop not found"+Id));
        stop.updateStop(StopDto);

        return stop;
    }


}
