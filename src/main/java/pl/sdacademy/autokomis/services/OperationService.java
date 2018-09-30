package pl.sdacademy.autokomis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.sdacademy.autokomis.model.Operation;
import pl.sdacademy.autokomis.repositories.OperationRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OperationService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final OperationRepository operationRepository;

    @Autowired
    public OperationService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    public Page<Operation> findAll(Pageable pageable) {
        Page<Operation> page = operationRepository.findAll(pageable);
        return page;
    }

    public List<Operation> findAll() {
        return operationRepository.findAll();
    }

    public List<Operation> findAllByType(Integer type) {
        return operationRepository.findAll().stream()
                .filter(o -> o.getType() == type)
                .collect(Collectors.toList());
    }

    public Optional<Operation> findById(Integer id) {
        return operationRepository.findById(id);
    }

    public void save(Operation customer) {
        operationRepository.save(customer);
    }

    public void deleteById(Integer id) {
        operationRepository.deleteById(id);
    }

    public Operation update(Operation operation) {
        return operationRepository.findAll().stream()
                .filter(c -> c.getId().equals(operation.getId()))
                .findFirst()
                .orElse(operation);
    }
}
