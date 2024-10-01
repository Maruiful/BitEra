package com.github.paicoding.forum.core.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * 异步执行
 * */
@Slf4j
@Aspect
@Component
public class AsyncExecuteAspect implements ApplicationContextAware {

    /**
     * 超时执行的切面
     *
     * @param joinPoint
     * @param asyncExecute
     * @return
     * @throws Throwable
     */
    @Around("@annotation(asyncExecute)")
    public Object handle(ProceedingJoinPoint joinPoint, AsyncExecute asyncExecute) throws Throwable {
        if (!asyncExecute.value()) {
            // 不支持异步执行时，直接返回
            return joinPoint.proceed();
        }

        try {
            // 携带超时时间的执行调用
            return AsyncUtil.callWithTimeLimit(asyncExecute.timeOut(), asyncExecute.unit(), () -> {
                try {
                    return joinPoint.proceed();
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            if (StringUtils.isNotBlank(asyncExecute.timeOutRsp())) {
                return defaultRespWhenTimeOut(joinPoint, asyncExecute);
            } else {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private Object defaultRespWhenTimeOut(ProceedingJoinPoint joinPoint, AsyncExecute asyncExecute)  { return null; }


    private ExpressionParser parser;
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException  {}
}
