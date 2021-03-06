package pl.sdacademy.autokomis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdacademy.autokomis.model.Customer;
import pl.sdacademy.autokomis.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findById(Integer id) {
        return customerRepository.findById(id);
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteById(Integer id) {
        customerRepository.deleteById(id);
    }

    public Customer update(Customer customer) {
        if ((customer.getId() != null) && (customer.getId() > 0)) {
            return customerRepository.findById(customer.getId())
                    .orElse(customer);
        }
        if (!customer.getPesel().equals("")) {
            return customerRepository.findAll().stream()
                    .filter(c -> c.getPesel().equals(customer.getPesel()))
                    .findFirst()
                    .orElse(customer);
        } else if (!customer.getNip().equals("")) {
            return customerRepository.findAll().stream()
                    .filter(c -> c.getNip().equals(customer.getNip()))
                    .findFirst()
                    .orElse(customer);
        } else {
            return customerRepository.findAll().stream()
                    .filter(c -> c.getName().equals(customer.getName()))
                    .filter(c -> c.getFirstName().equals(customer.getFirstName()))
                    .findFirst()
                    .orElse(customer);
        }
    }
}
