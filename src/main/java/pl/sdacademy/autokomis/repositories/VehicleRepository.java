package pl.sdacademy.autokomis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.autokomis.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

}
