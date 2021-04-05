package hiebrnate.envers.enhancement.infrastructure.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/greetings")
public class GreetingController {
    @GetMapping("/hello")
    public String getGreeting() {
        return "Hello world.";
    }

}
