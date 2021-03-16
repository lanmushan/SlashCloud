package site.lanmushan.framework.datasope.annotation;

import java.lang.annotation.*;

/**
 * 如果没指定的话，那么就是所有的sql都会进行权限处理
 * @author Administrator
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnabledDataScope {
    String[] value() default {};
    String[] exclude() default {};
}
