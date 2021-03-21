package site.lanmushan.framework.constant.annotation;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 *
 * @author Administrator
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(ConditionalNotCloudHandler.class)
public @interface ConditionalNotCloud {

}
