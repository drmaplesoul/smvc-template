package com.zdr.annotion;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CatTranstion {
    public String controller() default "";
    public String method() default "";
    public String name() default "";
}
