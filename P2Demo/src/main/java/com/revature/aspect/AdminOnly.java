package com.revature.aspect;

//This is a custom annotation! We can use it annotate any method that can only be accessed by managers

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME) // this annotation will be available at runtime.
public @interface AdminOnly {

    // No needs for fields
    // TODO : some annotations
}
