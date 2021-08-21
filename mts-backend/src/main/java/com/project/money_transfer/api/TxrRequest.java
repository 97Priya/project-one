package com.project.money_transfer.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="TxrRequest")
public class TxrRequest {
    @JsonProperty(value = "fromAccount")
    private int fromAccNum;
    @JsonProperty(value = "toAccount")
    private int toAccNum;
    private double amount;

    public TxrRequest() {
    }

    public TxrRequest(int fromAccNum, int toAccNum, double amount) {
        this.fromAccNum = fromAccNum;
        this.toAccNum = toAccNum;
        this.amount = amount;
    }

    public int getFromAccNum() {
        return fromAccNum;
    }

    public void setFromAccNum(int fromAccNum) {
        this.fromAccNum = fromAccNum;
    }

    public int getToAccNum() {
        return toAccNum;
    }

    public void setToAccNum(int toAccNum) {
        this.toAccNum = toAccNum;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
