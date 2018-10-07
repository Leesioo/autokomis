package pl.sdacademy.autokomis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.sdacademy.autokomis.dto.OperationsReportDto;
import pl.sdacademy.autokomis.model.Operation;
import pl.sdacademy.autokomis.repositories.MySQLProcedureExecutor;
import pl.sdacademy.autokomis.repositories.OperationRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OperationService {
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
    @Autowired
    private MySQLProcedureExecutor sqlProcedureExecutor;

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

    public Page<Operation> findAllByType(Integer type, Pageable pageable) {
        return operationRepository.findByType(type, pageable);
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

    public List<OperationsReportDto> getOperationsPerMonth() {
        List<OperationsReportDto> returnList = new ArrayList<>();
        String sqlStatement = "call getOperationsPerMonth('2018-09-01', -1);";
        List tempList = sqlProcedureExecutor.sqlProcedureSelect(sqlStatement);
        for (Object o : tempList) {
            Object[] t = (Object[]) o;
            String operationType = (String) t[0];
            String operationDate = (String) t[1];
            BigDecimal operationValue = (BigDecimal) t[2];
            String customerData = (String) t[3];
            String vehicleData = (String) t[4];
            Integer operationId = (Integer) t[5];
            Integer customerId = (Integer) t[6];
            Integer vehicleId = (Integer) t[7];
            returnList.add(new OperationsReportDto(operationType, operationDate, operationValue, customerData, vehicleData, operationId, customerId, vehicleId));
        }
        return returnList;
    }

//    @Procedure(procedureName = "getOperationsWithParams")
//    public void getOperationsProcedure(@Param("month"))
}
