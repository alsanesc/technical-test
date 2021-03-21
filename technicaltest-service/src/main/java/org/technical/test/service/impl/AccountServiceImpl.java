package org.technical.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.technical.test.api.Account;
import org.technical.test.api.StateResult;
import org.technical.test.api.Transfer;
import org.technical.test.api.common.Result;
import org.technical.test.model.AccountDTO;
import org.technical.test.repository.AccountRepository;
import org.technical.test.service.AccountService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public StateResult addAccount(Account account) {
        AccountDTO accountDTO = new AccountDTO();

        accountDTO.setName(account.getName());
        accountDTO.setCurrency(account.getCurrency());
        accountDTO.setBalance(account.getBalance());
        accountDTO.setTreasury(account.isTreasury());

        accountRepository.save(accountDTO);

        return new StateResult(Result.CA.getValue(), "The account was created");
    }

    @Override
    public Account searchAccount(String accountName) {
        Account account = null;

        if (accountName != null) {
            List<AccountDTO> accountList = accountRepository.findAll();

            for (AccountDTO acc : accountList) {
                if (acc.getName().equals(accountName)) {
                    account = new Account();
                    account.setName(acc.getName());
                    account.setBalance(acc.getBalance());
                    account.setCurrency(acc.getCurrency());
                    account.setTreasury(acc.isTreasury());

                    break;
                }
            }
        }

        return account;
    }

    @Override
    public StateResult transferAmount(Transfer transfer) {
        StateResult state = new StateResult();
        List<AccountDTO> accountList = accountRepository.findAll();

        BigDecimal amount = transfer.getAmount();
        String sourceAccount = transfer.getSourceAccount();
        String destinyAccount = transfer.getDestinyAccount();

        AccountDTO sourceAccountDTO = null;
        AccountDTO destinyAccountDTO = null;

        for (AccountDTO acc : accountList) {
            if (acc.getName().equals(sourceAccount) && sourceAccountDTO == null) {
                sourceAccountDTO = acc;
            } else if (acc.getName().equals(destinyAccount) && destinyAccountDTO == null) {
                destinyAccountDTO = acc;
            }
        }

        if (sourceAccountDTO != null && destinyAccountDTO != null) {
            if (sourceAccountDTO.isTreasury()) {
                sourceAccountDTO.setBalance(sourceAccountDTO.getBalance().subtract(amount));
                destinyAccountDTO.setBalance(destinyAccountDTO.getBalance().add(amount));

                accountRepository.save(sourceAccountDTO);
                accountRepository.save(destinyAccountDTO);

                state.setState(Result.TD.getValue());
                state.setDescription("Transference done correctly");
            } else {
                if (sourceAccountDTO.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) == -1) {
                    state.setState(Result.NG.getValue());
                    state.setDescription("The balance in the source account can't be negative");
                } else {
                    sourceAccountDTO.setBalance(sourceAccountDTO.getBalance().subtract(amount));
                    destinyAccountDTO.setBalance(destinyAccountDTO.getBalance().add(amount));

                    accountRepository.save(sourceAccountDTO);
                    accountRepository.save(destinyAccountDTO);

                    state.setState(Result.TD.getValue());
                    state.setDescription("Transference done correctly");
                }
            }
        } else {
            state.setState(Result.ADE.getValue());
            state.setDescription("The source/destiny account doesn't exist");
        }

        return state;
    }

    @Override
    public List<Account> getAccounts() {
        List<Account> accountList = null;
        List<AccountDTO> accList = accountRepository.findAll();

        if (accList != null && accList.size() > 0) {
            accountList = new ArrayList<Account>();
            for (AccountDTO acc : accList) {
                Account account = new Account();

                account.setName(acc.getName());
                account.setBalance(acc.getBalance());
                account.setCurrency(acc.getCurrency());
                account.setTreasury(acc.isTreasury());

                accountList.add(account);
            }
        }

        return accountList;
    }

    @Override
    public StateResult addBalance(Account ac) {
        AccountDTO accountDTO = null;
        StateResult state = new StateResult();

        List<AccountDTO> accountList = accountRepository.findAll();

        for (AccountDTO acc : accountList) {
            if (acc.getName().equals(ac.getName())) {
                accountDTO = new AccountDTO();

                accountDTO.setName(acc.getName());
                accountDTO.setBalance(acc.getBalance());
                accountDTO.setCurrency(acc.getCurrency());
                accountDTO.setTreasury(acc.isTreasury());

                break;
            }
        }

        if (accountDTO != null) {
            accountDTO.setBalance(accountDTO.getBalance().add(ac.getBalance()));
            accountRepository.save(accountDTO);

            state.setState(Result.BA.getValue());
            state.setDescription("The balance has been added");
        } else {
            state.setState(Result.ADE.getValue());
            state.setDescription("The account doesn't exist");
        }

        return state;
    }

}
