package ru.learnup.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.learnup.db.entity.Premiere;
import ru.learnup.db.services.EventService;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class,args);
        EventService eventService = context.getBean(EventService.class);


        eventService.demoTransaction();

//       eventService.removeTicket("Три медведя", "Элина");






    }

}
