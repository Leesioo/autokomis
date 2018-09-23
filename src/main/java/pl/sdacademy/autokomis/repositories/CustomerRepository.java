package pl.sdacademy.autokomis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.autokomis.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
