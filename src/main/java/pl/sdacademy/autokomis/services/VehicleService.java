package pl.sdacademy.autokomis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdacademy.autokomis.model.Vehicle;
import pl.sdacademy.autokomis.repositories.VehicleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> findAllByStatus(Integer status) {
        return vehicleRepository.findAll()
                .stream()
                .filter(v -> v.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> findById(Integer id) {
        return vehicleRepository.findById(id);
    }

    public void save(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    public void deleteById(Integer id) {
        vehicleRepository.deleteById(id);
    }

    public Vehicle update(Vehicle vehicle) {
        return vehicleRepository.findAll().stream()
                .filter(v -> v.getVin().equals(vehicle.getVin()))
                .findFirst()
                .orElse(vehicle);
    }
}
