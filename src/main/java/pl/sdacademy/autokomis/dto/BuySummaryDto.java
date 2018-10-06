package pl.sdacademy.autokomis.dto;

import java.math.BigDecimal;

public class BuySummaryDto {
    BigDecimal purchase;

    public BigDecimal getPurchase() {
        return purchase;
    }

    public void setPurchase(BigDecimal purchase) {
        this.purchase = purchase;
    }
}
