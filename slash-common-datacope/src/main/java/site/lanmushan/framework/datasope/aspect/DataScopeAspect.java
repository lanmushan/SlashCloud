package site.lanmushan.framework.datasope.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import site.lanmushan.framework.datasope.annotation.EnabledDataScope;
import site.lanmushan.framework.datasope.bus.DataScopeBus;

import java.util.Arrays;
import java.util.List;


/**
 * @author Administrator
 */
@Slf4j
@Component
@Aspect
public class DataScopeAspect {


    /**
     * Service层切点
     */
    @Pointcut(value = "@annotation(site.lanmushan.framework.datasope.annotation.EnabledDataScope)")
    public void dataScopeAspectPointcut() {
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @Before("dataScopeAspectPointcut()")
    public void doBefore(JoinPoint joinPoint) {
        //此处joinPoint的实现类是MethodInvocationProceedingJoinPoint
        Signature signature = joinPoint.getSignature();
        //获取参数名
        MethodSignature methodSignature = (MethodSignature) signature;
        EnabledDataScope enabledDataScope = methodSignature.getMethod().getAnnotation(EnabledDataScope.class);

        if (enabledDataScope != null) {
            DataScopeBus.enable();
        }
        List<String> enabledList = Arrays.asList(enabledDataScope.value());
        List<String> excludeList = Arrays.asList(enabledDataScope.exclude());
        DataScopeBus.setDataScopeContext(enabledList, excludeList);
    }

    @AfterThrowing(pointcut = "dataScopeAspectPointcut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        DataScopeBus.clean();
        ;

    }

    @After("dataScopeAspectPointcut()")
    public void doAfter(JoinPoint joinPoint) {
        DataScopeBus.clean();
    }


}
