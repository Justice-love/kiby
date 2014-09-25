/**
 * 
 * @creatTime 上午11:35:01
 * @author Eddy
 */
package org.eddy.validate;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

import org.eddy.annotation.ValidateRule;
import org.eddy.param.ParamParser;
import org.eddy.xml.Rule;
import org.eddy.xml.Rule.Param;
import org.eddy.xml.XmlParser;
import org.springframework.stereotype.Component;

/**
 * @author Eddy
 * 
 */
@Component("validateTemplate")
public class ValidateTemplate {

	private ConcurrentHashMap<String, ValidateExcute> cache = new ConcurrentHashMap<String, ValidateExcute>();
	private ConcurrentHashMap<String, String[]> paramNameCache = new ConcurrentHashMap<String, String[]>();

	public void validate(String className, String methodName, Object[] values) {
		Method method = getMethod(className, methodName);
		Class<?> parent = method.getDeclaringClass();
		ValidateExcute excute = cache.get(new StringBuilder(parent.getName()).append("_").append(method.getName()).toString());
		if (null == excute) {
			ValidateRule vrule = method.getAnnotation(ValidateRule.class);
			if (vrule != null) {
				Rule rule = XmlParser.tableRule(vrule.name());
				if (!paramNameCache.containsKey(new StringBuilder().append(parent.getName()).append("_").append(method.getName()).toString())) {
					paramNameCache.putAll(ParamParser.param(parent));
				}
				String[] names = paramNameCache.get(new StringBuilder().append(parent.getName()).append("_").append(method.getName()).toString());
				Param[] param = rule.takeParamsAsArray(names, method.getParameterTypes());
				excute = new ValidateExcute(values, param);
				cache.put(new StringBuilder(parent.getName()).append("_").append(method.getName()).toString(), excute);
			}
		}
		excute.validate();
	}

	/**
	 * 
	 * @param throwable
	 * @return
	 * @creatTime 上午11:50:37
	 * @author Eddy
	 */
	private Method getMethod(String className, String methodName) {
		if (null == className || null == methodName) throw new IllegalArgumentException("className or methodName can not be null");
		try {
			for (Method method : Class.forName(className).getDeclaredMethods()) {
				if (!method.isAccessible()) method.setAccessible(true);
				if (method.getName().equals(methodName)) return method;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IllegalArgumentException("not found this class or class is private: " + className);
		}
		return null;
	}
}
