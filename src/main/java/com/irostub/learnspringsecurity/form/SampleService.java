package com.irostub.learnspringsecurity.form;

import com.irostub.learnspringsecurity.account.Account;
import com.irostub.learnspringsecurity.account.AccountContext;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

    public void dashboard() {
        Account account = AccountContext.getAccount();
        System.out.println("-------------------------");
        System.out.println("account.getUsername() = " + account.getUsername());
    }
}
