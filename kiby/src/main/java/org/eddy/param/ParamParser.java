/**
 * 
 * @creatTime 上午10:44:08
 * @author Eddy
 */
package org.eddy.param;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

/**
 * @author Eddy
 *
 */
public class ParamParser {
	static ClassPool pool = ClassPool.getDefault();
	
	/**
	 * 解析参数
	 * @param klass
	 * @return
	 * @creatTime 上午10:45:36
	 * @author Eddy
	 * @throws NotFoundException 
	 */
	public static Map<String, String[]> param(Class<?> klass){
		try {
			CtClass cl = pool.get(klass.getName());
			if (null == cl) {
				pool.insertClassPath(klass.getName());
				cl = pool.get(klass.getName());
			}
			Map<String, String[]> result = new HashMap<String, String[]>();
			Method[] methods = klass.getDeclaredMethods();
			for (Method method : methods) {
				if (!method.isAccessible()) method.setAccessible(true);
				CtMethod cm = cl.getDeclaredMethod(method.getName(), paramType(method));
				MethodInfo methodInfo = cm.getMethodInfo();
				CodeAttribute ca = methodInfo.getCodeAttribute();
				LocalVariableAttribute lva = (LocalVariableAttribute) ca.getAttribute(LocalVariableAttribute.tag);
				String[] methodParam = new String[method.getParameterTypes().length];
				for (int i = 1; i <= methodParam.length; i++) {
//					if ("this".equals(lva.descriptorIndex(i))) continue;
					methodParam[i - 1] = lva.variableName(i);
				}
				result.put(new StringBuilder().append(klass.getName()).append("_").append(method.getName()).toString(), methodParam);
			}
			return result;
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}
	}
	
	static CtClass[] paramType(Method method) throws NotFoundException {
		Class<?>[] types = method.getParameterTypes();
		CtClass[] paramType = new CtClass[types.length];
		for (int i = 0; i < paramType.length; i++) {
			paramType[i] = pool.get(types[i].getName());
			if (null == paramType[i]) {
				pool.insertClassPath(types[i].getName());
				paramType[i] = pool.get(types[i].getName());
			}
		}
		return paramType;
	}
}
