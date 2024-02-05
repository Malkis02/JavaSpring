package ru.geekbrain.example3sem3hometask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrain.example3sem3hometask.domain.User;
import ru.geekbrain.example3sem3hometask.services.DataProcessingService;
import ru.geekbrain.example3sem3hometask.services.NotificationService;
import ru.geekbrain.example3sem3hometask.services.RegistrationService;
import ru.geekbrain.example3sem3hometask.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private DataProcessingService dataProcessingService;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private RegistrationService service;

    @GetMapping
    public List<User> userList() { return service.getDataProcessingService().getRepository().getUsers(); }

    @PostMapping("/body")
    public String userAddFromBody(@RequestBody User user)
    {
        service.getDataProcessingService().getRepository().getUsers().add(user);
        return "User added from body!";
    }

    @PostMapping("/param")
    public String userAddFromParam(
            @RequestParam String name,
            @RequestParam int age,
            @RequestParam String email
    ) {
        User user = service.getUserService().createUser(name, age, email);
        dataProcessingService.addUserToList(user);
        return "User added from parameters!";
    }
}
