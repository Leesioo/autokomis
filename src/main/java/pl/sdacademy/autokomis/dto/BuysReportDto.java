package pl.sdacademy.autokomis.dto;

import pl.sdacademy.autokomis.model.Customer;
import pl.sdacademy.autokomis.model.Operation;
import pl.sdacademy.autokomis.model.Vehicle;
import pl.sdacademy.autokomis.services.OperationService;

import java.math.BigDecimal;
import java.util.Optional;

public class BuysReportDto extends OperationDto{
    private BigDecimal cenaZakupu;

    public BuysReportDto(Vehicle vehicle, Customer customer, Operation operation, BigDecimal cenaZakupu) {
        super(vehicle, customer, operation);
        this.cenaZakupu = cenaZakupu;
    }

    public BuysReportDto(Operation operation, OperationService operationService) {
        super(operation.getVehicle(), operation.getCustomer(), operation);
        Optional<Operation> zakup = operationService.findAll().stream()
                .filter(o -> o.getVehicle().getId() == operation.getVehicle().getId())
                .filter(o -> o.getType() != 1)
                .findFirst();
    }


    public BigDecimal getCenaZakupu() {
        return cenaZakupu;
    }

    public void setCenaZakupu(BigDecimal cenaZakupu) {
        this.cenaZakupu = cenaZakupu;
    }




}
