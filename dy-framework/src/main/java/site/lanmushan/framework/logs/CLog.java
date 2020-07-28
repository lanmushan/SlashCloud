package site.lanmushan.framework.logs;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CLog {
    String name() default "";
    short optype() default 1;
    String paramType() default "json";
    String content() default "";
    String categoryName() default "";
}
