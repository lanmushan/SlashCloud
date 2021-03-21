package site.lanmushan.framework.cloud;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * FeignClient 指定的服务是自己，就不启用
 * @author Administrator
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(ConditionalFeignSelfDisableHandler.class)
public @interface ConditionalFeignSelfDisable {
}
