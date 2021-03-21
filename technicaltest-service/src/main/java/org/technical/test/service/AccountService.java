package org.technical.test.service;

import org.technical.test.api.Account;
import org.technical.test.api.StateResult;
import org.technical.test.api.Transfer;

import java.util.List;

public interface AccountService {

    StateResult addAccount(Account account);

    Account searchAccount(String accountName);

    StateResult transferAmount(Transfer transfer);

    List<Account> getAccounts();

    StateResult addBalance(Account account);
}
