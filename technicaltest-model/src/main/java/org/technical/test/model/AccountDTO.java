package org.technical.test.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class AccountDTO {

    @Id
    private String name;
    private String currency;
    private BigDecimal balance;
    private Boolean treasury;

    public AccountDTO() {
    }

    public AccountDTO(String name, String currency, BigDecimal balance, Boolean treasury) {
        this.name = name;
        this.currency = currency;
        this.balance = balance;
        this.treasury = treasury;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Boolean isTreasury() {
        return treasury;
    }

    public void setTreasury(Boolean treasury) {
        this.treasury = treasury;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDTO that = (AccountDTO) o;
        return name.equals(that.name) && currency.equals(that.currency) && balance.equals(that.balance) && treasury.equals(that.treasury);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, currency, balance, treasury);
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "name='" + name + '\'' +
                ", currency='" + currency + '\'' +
                ", balance=" + balance +
                ", treasury=" + treasury +
                '}';
    }

}
