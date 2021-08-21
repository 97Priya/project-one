package com.project.money_transfer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table(name = "accounts",schema = "moneytransfer")
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int account_number;

    @JsonProperty(value = "account_holder_name")
    @Column(name="account_holder_name")
    private String accountHolderName;

    private double balance;

    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private List<Transaction> txns;

    public Account() {
    }

    public Account(String accountHolderName, double balance) {
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public int getAccount_number() {
        return account_number;
    }

    public void setAccount_number(int account_number) {
        this.account_number = account_number;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTxns() {
        return txns;
    }

    public void setTxns(List<Transaction> txns) {
        this.txns = txns;
    }
}
