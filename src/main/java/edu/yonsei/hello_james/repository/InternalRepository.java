package edu.yonsei.hello_james.repository;

import edu.yonsei.hello_james.entity.Internal;
import edu.yonsei.hello_james.entity.Stop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InternalRepository extends JpaRepository<Internal, Long> {
    Optional<Internal> findByName(String name);
}
