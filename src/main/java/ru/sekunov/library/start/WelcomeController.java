package ru.sekunov.library.start;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/library/welcome")
public class WelcomeController {

    @GetMapping
    public String enter() {
        return "welcomePage";
    }
}
