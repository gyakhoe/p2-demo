package com.revature.aspect;

import jakarta.servlet.http.HttpSession;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/*
 * this class is an ASPECT - class that trigger at any point  functionality in our application
 * When a certain method is called, this class can listen for that , and trigger some method invocation
 */
@Aspect //This Class is an ASPECT - a class that can trigger functionality at any point in our application
//When a certain method is called, this class can listen for that, and trigger some method invocation
@Component
public class AuthAspect {

    //2 use cases we'll see:
    //1) When any method in the controller is called, we'll check if the user is logged in first
    //We can bypass this check for login/register though
    //2) When a method with our custom @AdminOnly annotation is called, check that the user is a manager

    //TERM: An advice is the functionality that we want to trigger
    //every method you see below is an advice


    //This advice will check if the requester is logged in after any controller method is called
    //EXCEPT for login or register
    //@Before lets this advice run BEFORE any of the specified methods are called
    @Before("within(com.revature.controller.*) " +
            "&& !execution(* com.revature.controller.AuthController.login(..))" +
            "&& !execution(* com.revature.controller.UserController.insertUser(..))")
    @Order(1)
    public void checkLoggedIn(){

        //get access to the session (or lack thereof)
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(false);

//        System.out.println("============== " +session.getAttribute("userId"));
        //we check if the session exists, and if the userId has been set in the session
        if (session == null || session.getAttribute("userId") == null) {
            throw new IllegalArgumentException("User is not logged in");
        }

    }

    /*If your login and register are in AuthController, you can skip a line and do this:

    @Before("within(com.revature.controllers.*) " +
            "&& !execution(* com.revature.controllers.AuthController.*(..))")

    * */


    //This advice will check if the requester is a manager before any method with the @AdminOnly annotation
    @Before("@annotation(AdminOnly)")
    @Order(2)
    public void checkAdmin(){
        //If the logged in User's role != "manager" throw an exception
        if(!"manager".equals(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getSession().getAttribute("role"))){

            throw new IllegalArgumentException("User is not a manager!");

        }
        //This is doing what I did in checkLogin, but as a one liner
    }


}
