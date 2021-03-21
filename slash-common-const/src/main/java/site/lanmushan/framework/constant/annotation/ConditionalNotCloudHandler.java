package site.lanmushan.framework.constant.annotation;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import site.lanmushan.framework.constant.CurrentAppConstant;


/**
 * @author Administrator
 */
public class ConditionalNotCloudHandler implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return !CurrentAppConstant.cloudMode;
    }
}