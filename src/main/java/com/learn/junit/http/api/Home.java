package com.learn.junit.http.api;

import com.learn.junit.http.model.Body;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class Home {

    @GetMapping("/")
    public String home() {
        return "Welcome to Junit Testing";
    }


    @GetMapping("/about")
    public String about(@RequestBody Body body) {
        return """
                This is a simple API to demonstrate Junit Testing
                height: %s cm
                weight: %s kg
                age: %s years
                """.formatted(body.getHeight(), body.getWeight(), body.getAge());
    }

}
