package org.technical.test.api;

import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.ValidateWithMethod;
import org.technical.test.api.common.BaseObject;

import java.math.BigDecimal;
import java.util.Objects;

public class Account extends BaseObject {

    @NotNull
    private String name;

    @NotNull
    @ValidateWithMethod(methodName = "checkIsoCurrency", parameterType = String.class)
    private String currency;

    @NotNull
    @ValidateWithMethod(methodName = "checkBalance", parameterType = BigDecimal.class)
    private BigDecimal balance;

    @NotNull
    private Boolean treasury;

    public Account() {
    }

    public Account(@NotNull String name, @NotNull @ValidateWithMethod(methodName = "checkIsoCurrency", parameterType = String.class) String currency,
                   @NotNull @ValidateWithMethod(methodName = "checkBalance", parameterType = BigDecimal.class) BigDecimal balance, @NotNull Boolean treasury) {
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

    private static boolean checkIsoCurrency(String currency) {
        return currency.matches("[a-zA-Z]{3}");
    }

    private static boolean checkBalance(BigDecimal balance) {
        return balance.toString().matches("[0-9]*.[0-9]{2}");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return name.equals(account.name) && currency.equals(account.currency) && balance.equals(account.balance) && treasury.equals(account.treasury);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, currency, balance, treasury);
    }

}
