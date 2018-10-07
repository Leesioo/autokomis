package pl.sdacademy.autokomis.dto;

import java.math.BigDecimal;

public class OperationsReportDto {
    private String operationType;
    private String operationDate;
    private BigDecimal operationValue;
    private String customerData;
    private String vehicleData;
    private int operationId;
    private int customerId;
    private int vehicleId;

    public OperationsReportDto() {
    }

    public OperationsReportDto(String operationType, String operationDate, BigDecimal operationValue, String customerData, String vehicleData, Integer operationId, Integer customerId, Integer vehicleId) {
        this.operationType = operationType;
        this.operationDate = operationDate;
        this.operationValue = operationValue;
        this.customerData = customerData;
        this.vehicleData = vehicleData;
        this.operationId = operationId;
        this.customerId = customerId;
        this.vehicleId = vehicleId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(String operationDate) {
        this.operationDate = operationDate;
    }

    public BigDecimal getOperationValue() {
        return operationValue;
    }

    public void setOperationValue(BigDecimal operationValue) {
        this.operationValue = operationValue;
    }

    public String getCustomerData() {
        return customerData;
    }

    public void setCustomerData(String customerData) {
        this.customerData = customerData;
    }

    public String getVehicleData() {
        return vehicleData;
    }

    public void setVehicleData(String vehicleData) {
        this.vehicleData = vehicleData;
    }

    public int getOperationId() {
        return operationId;
    }

    public void setOperationId(int operationId) {
        this.operationId = operationId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }
}
