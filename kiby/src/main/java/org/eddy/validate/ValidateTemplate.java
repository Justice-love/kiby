/**
 * 
 * @creatTime 上午11:35:01
 * @author Eddy
 */
package org.eddy.validate;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

import org.eddy.annotation.ValidateRule;
import org.eddy.xml.Rule;
import org.eddy.xml.XmlParser;

/**
 * @author Eddy
 * 
 */
public class ValidateTemplate {

	private ConcurrentHashMap<String, Validate> cache = new ConcurrentHashMap<String, Validate>();

	public void validate(Throwable throwable) {
		Method method = getMethod(throwable);
		Class<?> parent = method.getDeclaringClass();
		if (!cache.containsKey(new StringBuilder(parent.getName()).append("_").append(method.getName()).toString())) {
			ValidateRule vrule = method.getAnnotation(ValidateRule.class);
			if (vrule != null) {
				Rule rule = XmlParser.tableRule(vrule.name());
			}
		}
	}

	/**
	 * 
	 * @param throwable
	 * @return
	 * @creatTime 上午11:50:37
	 * @author Eddy
	 */
	private Method getMethod(Throwable throwable) {
		StackTraceElement[] el = throwable.getStackTrace();
		String methodName = el[0].getMethodName();
		for (Method method : this.getClass().getDeclaredMethods()) {
			if (!method.isAccessible()) method.setAccessible(true);
			if (method.getName().equals(methodName)) return method;
		}
		return null;
	}
}
