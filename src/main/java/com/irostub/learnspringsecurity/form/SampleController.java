package com.irostub.learnspringsecurity.form;

import com.irostub.learnspringsecurity.account.AccountContext;
import com.irostub.learnspringsecurity.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class SampleController {

    private final SampleService sampleService;

    private final AccountRepository accountRepository;

    @Autowired
    public SampleController(SampleService sampleService, AccountRepository accountRepository) {
        this.sampleService = sampleService;
        this.accountRepository = accountRepository;
    }

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        if(principal == null){
            model.addAttribute("message", "Hello String Security");
        }else{
            model.addAttribute("message", "Hello " + principal.getName());
        }
        return "index";
    }

    @GetMapping("/info")
    public String info(Model model) {
        model.addAttribute("message", "Info");
        return "info";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        model.addAttribute("message", "Hello " + principal.getName());
        sampleService.dashboard();
        AccountContext.setAccount(accountRepository.findByUsername(principal.getName()));
        return "dashboard";
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("message", "Hello Admin " + principal.getName());
        return "admin";
    }

    @GetMapping("/user")
    public String user(Model model, Principal principal) {
        model.addAttribute("message", "Hello User " + principal.getName());
        return "user";
    }
}
