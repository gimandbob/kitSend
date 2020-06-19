package covidtest;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface SendRepository extends PagingAndSortingRepository<Send, Long>{
    Optional<Send> findByInspectionId(Long inspectionId);

}