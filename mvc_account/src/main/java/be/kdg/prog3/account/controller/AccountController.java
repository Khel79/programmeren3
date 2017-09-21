package be.kdg.prog3.account.controller;

import be.kdg.prog3.account.model.Account;
import be.kdg.prog3.account.model.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class AccountController {
    private final Accounts accounts;

    @Autowired
    public AccountController(Accounts accounts) {
        this.accounts = accounts;
    }

    @GetMapping("/accounts")
    public ModelAndView showAccountBalance() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("showAllAccounts");
        modelAndView.getModel().put("accounts", this.accounts);
        return modelAndView;
    }

    @GetMapping("/accounts/{accountOwner}")
    public ModelAndView showAccountBalance(@PathVariable String accountOwner) {
        final Account account = this.accounts.get(accountOwner);

        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("showAccountBalance");
        modelAndView.getModel().put("account", account);
        return modelAndView;
    }

    @GetMapping("/api/accounts")
    @ResponseBody
    public Collection<Account> getAccounts() {
        return this.accounts.getAll();
    }

    @GetMapping("/api/accounts/{accountOwner}")
    @ResponseBody
    public Account getAccount(@PathVariable String accountOwner) {
        return this.accounts.get(accountOwner);
    }
}
