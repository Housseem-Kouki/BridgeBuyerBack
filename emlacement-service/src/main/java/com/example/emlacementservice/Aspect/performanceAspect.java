package com.example.emlacementservice.Aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class
performanceAspect {
	@Around("execution(* com.example.emlacementservice.service.*.add(..))")
	public void performaneCal(ProceedingJoinPoint pjp) throws Throwable{
		Long startDate=System.currentTimeMillis();
		pjp.proceed();
		Long differenceTime=System.currentTimeMillis() - startDate;
		log.info("la methode a éxécuté " +differenceTime);
	}

	@AfterReturning("execution(* com.example.emlacementservice.service.*.add*(..))")
	public void Aspect(JoinPoint jp) {
		String nom=jp.getSignature().getName();
		log.info("la methode est bien executé " +nom);
	}
	

}
