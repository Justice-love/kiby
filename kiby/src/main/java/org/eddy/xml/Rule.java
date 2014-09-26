/**
 * 
 * @creatTime 下午5:12:02
 * @author Eddy
 */
package org.eddy.xml;

import java.util.Map;
import java.util.Map.Entry;

import org.eddy.annotation.Algorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Eddy
 * 
 */
public class Rule {

	/**
	 * 规则名称
	 */
	private String name;

	/**
	 * 方法字段
	 */
	private Map<String, Param> params;

	/**
	 * 构造函数
	 * 
	 * @creatTime 下午5:22:39
	 * @author Eddy
	 */
	public Rule() {
		// TODO Auto-generated constructor stub
	}

	public Rule(String name, Map<String, Param> params) {
		super();
		this.name = name;
		this.params = params;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Param> getParams() {
		return params;
	}

	public void setParams(Map<String, Param> params) {
		this.params = params;
	}

	/**
	 * 获取Param数组
	 * 
	 * @param paramNames
	 * @param types
	 * @return
	 * @creatTime 下午1:47:49
	 * @author Eddy
	 */
	public Param[] takeParamsAsArray(String[] paramNames, Class<?>[] types) {
		if (null == paramNames || null == types || paramNames.length != types.length) {
			return null;
		}
		Param[] result = new Param[paramNames.length];
		for (int i = 0; i < paramNames.length; i++) {
			Param p = params.get(paramNames[i]);
			result[i] = p == null || !types[i].getName().equals(p.getType()) ? null : p;
		}
		return result;
	}

	/**
	 * 字段
	 * 
	 * @author Eddy
	 * 
	 */
	public static class Param {
		
		Logger logger = LoggerFactory.getLogger(Param.class);
		/**
		 * 字段名
		 */
		private String name;
		/**
		 * 字段类型
		 */
		private String type;

		/**
		 * 抛出异常信息
		 */
		private String exception;

		private Map<Algorithm, String> algorithms;

		/**
		 * 验证方法
		 * @param obj
		 * @creatTime 上午10:00:00
		 * @author Eddy
		 */
		public void match(Object obj) {
			for ( Entry<Algorithm, String> entry : algorithms.entrySet()) {
				if (!entry.getKey().match(obj, entry.getValue())) {
					logger.error("not match " + entry.getKey().name() + " expect: " + entry.getValue() + " value: " + obj);
					throw new IllegalArgumentException(entry.getKey().getException() == null ? exception : entry.getKey().getException());
				}
				logger.debug("value: " + obj + " match " + entry.getKey().name());
			}
		}

		/**
		 * 插入新验证表达式
		 * 
		 * @param key
		 * @param value
		 * @creatTime 上午9:53:42
		 * @author Eddy
		 */
		public void put(Algorithm key, String value) {
			algorithms.put(key, value);
		}

		/**
		 * 构造函数
		 * 
		 * @creatTime 下午5:19:22
		 * @author Eddy
		 */
		public Param() {
			// TODO Auto-generated constructor stub
		}

		public Param(String name, String type, String exception, Map<Algorithm, String> algorithms) {
			super();
			this.name = name;
			this.type = type;
			this.exception = exception;
			this.algorithms = algorithms;
		}

		public Param(Map<Algorithm, String> algorithms) {
			super();
			this.algorithms = algorithms;
		}

		public Map<Algorithm, String> getAlgorithms() {
			return algorithms;
		}

		public void setAlgorithms(Map<Algorithm, String> algorithms) {
			this.algorithms = algorithms;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getException() {
			return exception;
		}

		public void setException(String exception) {
			this.exception = exception;
		}

	}
}
