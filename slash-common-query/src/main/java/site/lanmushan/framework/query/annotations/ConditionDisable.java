package site.lanmushan.framework.query.annotations;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @author Administrator
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(ConditionDisableHandler.class)
public @interface ConditionDisable{
}
