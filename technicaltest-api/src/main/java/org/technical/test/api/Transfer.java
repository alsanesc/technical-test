package org.technical.test.api;

import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.ValidateWithMethod;
import org.technical.test.api.common.BaseObject;

import java.math.BigDecimal;
import java.util.Objects;

public class Transfer extends BaseObject {

    @NotNull
    @ValidateWithMethod(methodName = "checkBalance", parameterType = BigDecimal.class)
    private BigDecimal amount;

    @NotNull
    private String sourceAccount;

    @NotNull
    private String destinyAccount;

    public Transfer() {
    }

    public Transfer(@NotNull @ValidateWithMethod(methodName = "checkBalance", parameterType = BigDecimal.class) BigDecimal amount,
                    @NotNull String sourceAccount, @NotNull String destinyAccount) {
        this.amount = amount;
        this.sourceAccount = sourceAccount;
        this.destinyAccount = destinyAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public String getDestinyAccount() {
        return destinyAccount;
    }

    public void setDestinyAccount(String destinyAccount) {
        this.destinyAccount = destinyAccount;
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
        Transfer transfer = (Transfer) o;
        return amount.equals(transfer.amount) && sourceAccount.equals(transfer.sourceAccount) && destinyAccount.equals(transfer.destinyAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, sourceAccount, destinyAccount);
    }

}
