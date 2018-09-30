package pl.sdacademy.autokomis.model;

import pl.sdacademy.autokomis.dto.OperationDto;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column (unique = true)
    private String vin;
    @Column
    private String brand;
    @Column
    private String model;
    @Column
    private Integer capacity;
    @Column
    private Integer horsePower;
    @Column
    private String fuelType;
    @Column
    private Integer mileage;
    @Column
    private String gearType;
    @Column
    private String description;
    @Column
    private Integer testDriveCounter = 0;
    @Column
    private Integer status = 0;
    @Column
    private Integer productionDate;
    @Column
    private BigDecimal value;

    public Vehicle() {
    }

    public Vehicle(OperationDto operationDto) {
        this.id = operationDto.getVehicleId();
        this.vin = operationDto.getVehicleVin();
        this.brand = operationDto.getVehicleBrand();
        this.model = operationDto.getVehicleModel();
        this.capacity = operationDto.getVehicleCapacity();
        this.horsePower = operationDto.getVehicleHorsePower();
        this.fuelType = operationDto.getVehicleFuelType();
        this.mileage = operationDto.getVehicleMileage();
        this.gearType = operationDto.getVehicleGearType();
        this.description = operationDto.getVehicleDescription();
        this.productionDate = operationDto.getVehicleProductionDate();
        this.value = operationDto.getVehicleValue();
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Integer getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Integer productionDate) {
        this.productionDate = productionDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(Integer horsePower) {
        this.horsePower = horsePower;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public String getGearType() {
        return gearType;
    }

    public void setGearType(String gearType) {
        this.gearType = gearType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTestDriveCounter() {
        return testDriveCounter;
    }

    public void setTestDriveCounter(Integer testDriveCounter) {
        this.testDriveCounter = testDriveCounter;
    }
}
