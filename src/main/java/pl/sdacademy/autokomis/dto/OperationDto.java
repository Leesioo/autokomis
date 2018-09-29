package pl.sdacademy.autokomis.dto;

import pl.sdacademy.autokomis.model.Customer;
import pl.sdacademy.autokomis.model.Operation;
import pl.sdacademy.autokomis.model.Vehicle;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OperationDto {
    private Integer vehicleId;
    private String vehicleVin;
    private String vehicleBrand;
    private String vehicleModel;
    private Integer vehicleCapacity;
    private Integer vehicleHorsePower;
    private String vehicleFuelType;
    private Integer vehicleMileage;
    private String vehicleGearType;
    private String vehicleDescription;
    private Integer vehicleProductionDate;
    private Integer customerId;
    private String customerName;
    private String customerFirstName;
    private String customerAddress;
    private String customerNip;
    private String customerPesel;
    private Integer operationId;
    private String operationData;
    private BigDecimal operationValue;
    private Integer operationType;

    public OperationDto(Vehicle vehicle, Customer customer, Operation operation) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String data = "";
        try {
            data = sdf.format(operation.getData());
        } catch (Exception e) {
            data = sdf.format(new Date());
        }

        this.vehicleId = vehicle.getId();
        this.vehicleVin = vehicle.getVin();
        this.vehicleBrand = vehicle.getBrand();
        this.vehicleModel = vehicle.getModel();
        this.vehicleCapacity = vehicle.getCapacity();
        this.vehicleHorsePower = vehicle.getHorsePower();
        this.vehicleFuelType = vehicle.getFuelType();
        this.vehicleMileage = vehicle.getMileage();
        this.vehicleGearType = vehicle.getGearType();
        this.vehicleDescription = vehicle.getDescription();
        this.vehicleProductionDate = vehicle.getProductionDate();
        this.customerId = customer.getId();
        this.customerName = customer.getName();
        this.customerFirstName = customer.getFirstName();
        this.customerAddress = customer.getAddress();
        this.customerNip = customer.getNip();
        this.customerPesel = customer.getPesel();
        this.operationId = operation.getId();
        this.operationData = data;
        this.operationValue = operation.getValue();
        this.operationType = operation.getType();
    }

    public OperationDto() {
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleVin() {
        return vehicleVin;
    }

    public void setVehicleVin(String vehicleVin) {
        this.vehicleVin = vehicleVin;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public Integer getVehicleCapacity() {
        return vehicleCapacity;
    }

    public void setVehicleCapacity(Integer vehicleCapacity) {
        this.vehicleCapacity = vehicleCapacity;
    }

    public Integer getVehicleHorsePower() {
        return vehicleHorsePower;
    }

    public void setVehicleHorsePower(Integer vehicleHorsePower) {
        this.vehicleHorsePower = vehicleHorsePower;
    }

    public String getVehicleFuelType() {
        return vehicleFuelType;
    }

    public void setVehicleFuelType(String vehicleFuelType) {
        this.vehicleFuelType = vehicleFuelType;
    }

    public Integer getVehicleMileage() {
        return vehicleMileage;
    }

    public void setVehicleMileage(Integer vehicleMileage) {
        this.vehicleMileage = vehicleMileage;
    }

    public String getVehicleGearType() {
        return vehicleGearType;
    }

    public void setVehicleGearType(String vehicleGearType) {
        this.vehicleGearType = vehicleGearType;
    }

    public String getVehicleDescription() {
        return vehicleDescription;
    }

    public void setVehicleDescription(String vehicleDescription) {
        this.vehicleDescription = vehicleDescription;
    }

    public Integer getVehicleProductionDate() {
        return vehicleProductionDate;
    }

    public void setVehicleProductionDate(Integer vehicleProductionDate) {
        this.vehicleProductionDate = vehicleProductionDate;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerNip() {
        return customerNip;
    }

    public void setCustomerNip(String customerNip) {
        this.customerNip = customerNip;
    }

    public String getCustomerPesel() {
        return customerPesel;
    }

    public void setCustomerPesel(String customerPesel) {
        this.customerPesel = customerPesel;
    }

    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    public String getOperationData() {
        return operationData;
    }

    public void setOperationData(String operationData) {
        this.operationData = operationData;
    }

    public BigDecimal getOperationValue() {
        return operationValue;
    }

    public void setOperationValue(BigDecimal operationValue) {
        this.operationValue = operationValue;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }
}
