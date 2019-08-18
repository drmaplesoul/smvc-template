package com.zdr.component;

import com.zdr.annotion.CatTranstion;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.UUID;

@Component
@Aspect
@Slf4j
public class CatTransactionAdvice {
    ThreadLocal<Long> time = new ThreadLocal<Long>();
    ThreadLocal<String> tag = new ThreadLocal<String>();

    public CatTransactionAdvice() {
        log.info("init CatTransactionAdvice");
    }

    @Pointcut("@annotation(com.zdr.annotion.CatTranstion)")
    public void catTranstion() {

    }

    // 目标方法执行前执行
    @Before("catTranstion()")
    public void beforeExec(JoinPoint joinPoint) {
        log.info("catTranstion begin");
        time.set(System.currentTimeMillis());
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        String actionName = method.getAnnotation(CatTranstion.class).name();
        tag.set(UUID.randomUUID().toString());
        log.info("Tranastion name:{},Joinpont is: {},tag is :{}",actionName,joinPoint,tag);
    }

    // 目标方法执行后执行
    @After("catTranstion()")
    public void afterExec(JoinPoint joinPoint) {
        log.info("catTranstion finished");
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        log.info("标记为{}的方法{}运行消耗{}ms",tag.get(),method.getName(),(System.currentTimeMillis() - time.get()));
    }

    // 目标方法返回后执行，如果发生异常不执行
    @AfterReturning("catTranstion()")
    public void AfterReturningExec(JoinPoint joinPoint) {
        log.info("catTranstion returned");
    }

    // 异常时执行
    @AfterThrowing("catTranstion()")
    public void afterThrowingExec(JoinPoint joinPoint) {
        log.info("catTranstion exception occur");
    }

    // 环境通知，可以在执行前后进行处理，也可以干预目标方法执行
    @Around("catTranstion()")
    public Object aroundExec(ProceedingJoinPoint pjp) throws Throwable {
        log.info("aroundExec before exec...");
        // 必须加上这句，否则导致目标方法没有执行
        Object ret = pjp.proceed();
        log.info("aroundExec after exec...");
        // 必须有返回值，否则会使目标方法返回null
        return ret;
    }
}
