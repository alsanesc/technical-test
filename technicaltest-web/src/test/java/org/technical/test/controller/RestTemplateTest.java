package org.technical.test.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.technical.test.api.Account;
import org.technical.test.api.Transfer;
import org.technical.test.api.common.Result;
import org.technical.test.api.exceptions.BadRequestException;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestTemplateTest {

    @Autowired
    private AccountController accountController;

    @Test
    public void createAccount_OK() throws BadRequestException {
        Account acc = new Account("account1", "EUR", new BigDecimal(12.95), true);
        assertEquals(accountController.createAccount(acc).getState(), Result.CA);

        acc = new Account("account2", "EUR", new BigDecimal(12.02), true);
        assertEquals(accountController.createAccount(acc).getState(), Result.CA);

        acc = new Account("account3", "EUR", new BigDecimal(2.95), false);
        assertEquals(accountController.createAccount(acc).getState(), Result.CA);
    }

    @Test
    public void createAccount_KO() throws BadRequestException {
        Account acc = new Account("account1", "EURO", new BigDecimal(12.95), true);
        assertEquals(accountController.createAccount(acc).getState(), Result.CA);
    }

    @Test
    public void getAccounts_OK() {
        assertNotNull(accountController.getAccounts());
    }

    @Test
    public void transferAmount_TransferDone() throws BadRequestException {
        Transfer transfer = new Transfer(new BigDecimal(2.65), "account1", "account3");
        assertEquals(accountController.transferAmount(transfer).getState(), Result.TD);
    }

    @Test
    public void transferAmount_NegativeBalance() throws BadRequestException {
        Transfer transfer = new Transfer(new BigDecimal(150.65), "account3", "account1");
        assertEquals(accountController.transferAmount(transfer).getState(), Result.NG);
    }

    @Test
    public void transferAmount_AccountDoesntExist() throws BadRequestException {
        Transfer transfer = new Transfer(new BigDecimal(150.65), "account3", "account15");
        assertEquals(accountController.transferAmount(transfer).getState(), Result.ADE);
    }

}
