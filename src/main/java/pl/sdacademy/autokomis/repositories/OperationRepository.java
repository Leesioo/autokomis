package pl.sdacademy.autokomis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.autokomis.model.Operation;

public interface OperationRepository extends JpaRepository<Operation, Integer> {

}
