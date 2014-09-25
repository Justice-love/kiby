/**
 * 
 * @creatTime 下午4:51:17
 * @author Eddy
 */
package org.eddy.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Eddy
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ValidateRule {

	/**
	 * 模板名称
	 * @return
	 * @creatTime 下午12:03:25
	 * @author Eddy
	 */
	String name();
}
