package se.iths.cecilia.chatapp.config;


import org.springframework.stereotype.Component;
import se.iths.cecilia.chatapp.service.UserService;
import se.iths.cecilia.chatapp.service.MessageService;

@Component
public class ServiceRegistry {

    public static UserService userService;
    public static MessageService messageService;

    public ServiceRegistry(UserService userService, MessageService messageService) {
        ServiceRegistry.userService = userService;
        ServiceRegistry.messageService = messageService;
    }
}

