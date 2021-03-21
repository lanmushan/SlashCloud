package site.lanmushan.framework.cloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import site.lanmushan.framework.constant.CurrentAppConstant;

import java.util.Map;


/**
 * @author Administrator
 */
public class ConditionalFeignSelfDisableHandler implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        if(!CurrentAppConstant.cloudMode)
        {
            return false;
        }
        Map<String,Object>  attr= metadata.getAnnotationAttributes(FeignClient.class.getName());
        String name=attr.get("name").toString();
        if(CurrentAppConstant.appName.equals(name))
        {
            return false;
        }
        return true;
    }
}