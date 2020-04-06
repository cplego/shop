package org.cplego.foodie.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceLogAspect {

    private Logger logger = LoggerFactory.getLogger(ServiceLogAspect.class);
    @Around("execution(* org.cplego.foodie.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {

        logger.info("======= 开始执行 {}.{} ======="
                ,joinPoint.getTarget().getClass()
                ,joinPoint.getSignature().getName());
        long beginTime = System.currentTimeMillis();
        //执行目标方法
        Object obj =  joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        long tollerTime = endTime - beginTime;

        if(tollerTime > 3000)
            logger.error("===== 执行结束，耗时:{} 毫秒 ======",tollerTime);
        else if(tollerTime > 2000)
            logger.warn("===== 执行结束，耗时:{} 毫秒 ======",tollerTime);
        else
            logger.info("===== 执行结束，耗时:{} 毫秒 ======",tollerTime);
        return obj;
    }

}
