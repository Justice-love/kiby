/**
 * 
 * @creatTime 下午3:11:56
 * @author Eddy
 */
package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.eddy.validate.ValidateTemplate;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Eddy
 * 
 */
@Aspect
@Order(1)
@Component
public class TestAop {
	
	@Around(value = "execution(* org.eddy.test.Test.test123(..))")
	public void around(ProceedingJoinPoint pjp) throws Throwable {
		Object[] values = pjp.getArgs();
		new ValidateTemplate().validate(pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName(), values);
		pjp.proceed();
	}
	
}
