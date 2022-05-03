package ru.learnup.db.aop.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @After("execution(public * buyTicket(ru.learnup.db.entity.Ticket))")
    public void afterBuyTicket(){
        System.err.println("Информацию о покупке мы отправили на почту.");
    }

}
