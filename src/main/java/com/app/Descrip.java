package com.app;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/10/20.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Descrip {
    int id();
    String description() default "666";
}
