package org.technical.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.technical.test.api.Account;
import org.technical.test.api.StateResult;
import org.technical.test.api.Transfer;
import org.technical.test.api.common.Result;
import org.technical.test.api.exceptions.BadRequestException;
import org.technical.test.service.AccountService;
import org.technical.test.utility.Utility;

import java.util.List;

@RestController("accountController")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/account/create", method = RequestMethod.POST)
    public @ResponseBody StateResult createAccount(@RequestBody Account account) {
        StateResult state = null;

        if (account != null) {
            try {
                Utility.validarJSON(account);
                state = accountService.addAccount(account);
            } catch (BadRequestException e) {
                if (e.getMessage().contains("currency")) {
                    state = new StateResult(Result.IC.getValue(), "The currency is invalid; it must be only three characters");
                }
            }
        }

        return state;
    }

    @RequestMapping(value = "/account/search/{accountName}", method = RequestMethod.GET)
    public @ResponseBody Account searchAccount(@PathVariable("accountName") String accountName) throws Exception {
        Account account = null;

        try {
            account = accountService.searchAccount(accountName);
        } catch (Exception e) {
            throw new Exception(e);
        }

        return account;
    }

    @RequestMapping(value = "/account/transfer", method = RequestMethod.POST)
    public @ResponseBody StateResult transferAmount(@RequestBody Transfer transfer) throws BadRequestException {
        StateResult state = null;

        if (transfer != null) {
            try {
                Utility.validarJSON(transfer);
                state = accountService.transferAmount(transfer);
            } catch (BadRequestException e) {
                throw new BadRequestException(e.getMessage());
            }
        }

        return state;
    }

    @RequestMapping(value = "/account/get", method = RequestMethod.GET)
    public @ResponseBody List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @RequestMapping(value = "/account/add", method = RequestMethod.POST)
    public @ResponseBody StateResult addBalance(@RequestBody Account account) throws BadRequestException {
        StateResult state = null;

        if (account != null) {
            try {
                Utility.validarJSON(account);
                state = accountService.addBalance(account);
            } catch (BadRequestException e) {
                throw new BadRequestException(e.getMessage());
            }
        }

        return state;
    }

}
