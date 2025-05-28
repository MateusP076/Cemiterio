package com.cemiterio.Cemiterio.ControlerHome;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home"; // index.html
    }

    @GetMapping("/sector")
    public String sectorHome() {
        return "sector/Telainiciosector";
    }

    @GetMapping("/user")
    public String userHome() {
        return "Users/index";
    }
    @GetMapping("/deaduser")
    public String deadHome(){
        return "Deaduser/homedeaduser";
    }
}
