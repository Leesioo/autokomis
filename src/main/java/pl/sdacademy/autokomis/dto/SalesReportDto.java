package pl.sdacademy.autokomis.dto;

import pl.sdacademy.autokomis.model.Customer;
import pl.sdacademy.autokomis.model.Operation;
import pl.sdacademy.autokomis.model.Vehicle;
import pl.sdacademy.autokomis.services.OperationService;

import java.math.BigDecimal;
import java.util.Optional;

public class SalesReportDto extends OperationDto{
    private BigDecimal cenaZakupu;
    private BigDecimal prowizja;

    public SalesReportDto(Vehicle vehicle, Customer customer, Operation operation, BigDecimal cenaZakupu, BigDecimal prowizja) {
        super(vehicle, customer, operation);
        this.cenaZakupu = cenaZakupu;
        this.prowizja = prowizja;
    }

    public SalesReportDto(Operation operation, OperationService operationService) {
        super(operation.getVehicle(), operation.getCustomer(), operation);
        Optional<Operation> zakup = operationService.findAll().stream()
                .filter(o -> o.getVehicle().getId() == operation.getVehicle().getId())
                .filter(o -> o.getType() != 2)
                .findFirst();
        if (zakup.isPresent()) {
            this.cenaZakupu = obliczCeneZakupu(operation, zakup.get());
            this.prowizja = obliczProwizje(operation, zakup.get());
        } else {
            this.cenaZakupu = new BigDecimal(0);
            this.prowizja = new BigDecimal(0);
        }
    }

    private BigDecimal obliczCeneZakupu(Operation operation, Operation zakup) {
        BigDecimal cenaZakupu = new BigDecimal(0);
        switch (zakup.getType()) {
            case 1: cenaZakupu = zakup.getValue(); break;
        }
        return cenaZakupu;
    }

    private BigDecimal obliczProwizje(Operation operation, Operation zakup) {
        BigDecimal cenaSprzedazy = operation.getValue();
        BigDecimal cenaZakupu = zakup.getValue();
        BigDecimal prowizja = new BigDecimal(0);
        switch (zakup.getType()) {
            case 1: prowizja = cenaSprzedazy.subtract(cenaZakupu); break;
            case 3: prowizja = cenaSprzedazy.multiply(BigDecimal.valueOf(0.2)); break;
        }
        return prowizja;
    }

    public BigDecimal getCenaZakupu() {
        return cenaZakupu;
    }

    public void setCenaZakupu(BigDecimal cenaZakupu) {
        this.cenaZakupu = cenaZakupu;
    }

    public BigDecimal getProwizja() {
        return prowizja;
    }

    public void setProwizja(BigDecimal prowizja) {
        this.prowizja = prowizja;
    }
}
