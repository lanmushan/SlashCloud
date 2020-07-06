package com.lanmushan.framework.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author cayden
 * @date 2020/7/4 14:46
 */


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OpenQueryList {
    public @interface Entity {     String name() default ""; }
}
