package pl.sdacademy.autokomis.dto;

import java.math.BigDecimal;

public class SaleSummaryDto {
    BigDecimal purchase;
    BigDecimal commission;
    BigDecimal sale;

    public BigDecimal getPurchase() {
        return purchase;
    }

    public void setPurchase(BigDecimal purchase) {
        this.purchase = purchase;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public BigDecimal getSale() {
        return sale;
    }

    public void setSale(BigDecimal sale) {
        this.sale = sale;
    }
}
