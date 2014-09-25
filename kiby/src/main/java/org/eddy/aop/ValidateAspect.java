/**
 * 
 * @creatTime 下午4:42:56
 * @author Eddy
 */
package org.eddy.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.eddy.validate.ValidateTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author Eddy
 * 
 */
public class ValidateAspect {

	@Autowired
	@Qualifier("validateTemplate")
	private ValidateTemplate validateTemplate;

	public void validateAround(ProceedingJoinPoint pjp) throws Throwable {
		Object[] values = pjp.getArgs();
		validateTemplate.validate(pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName(), values);
		pjp.proceed();
	}

	public void setValidateTemplate(ValidateTemplate validateTemplate) {
		this.validateTemplate = validateTemplate;
	}

}
