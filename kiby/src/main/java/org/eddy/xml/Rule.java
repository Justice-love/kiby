/**
 * 
 * @creatTime 下午5:12:02
 * @author Eddy
 */
package org.eddy.xml;

import java.util.Map;

import org.eddy.annotation.Algorithm;

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
		/**
		 * 字段名
		 */
		private String name;
		/**
		 * 字段类型
		 */
		private String type;
		/**
		 * 算法
		 */
		private Algorithm algorithm;
		/**
		 * 抛出异常信息
		 */
		private String exception;

		private String expect;

		/**
		 * 构造函数
		 * 
		 * @creatTime 下午5:19:22
		 * @author Eddy
		 */
		public Param() {
			// TODO Auto-generated constructor stub
		}

		public Param(String name, String type, Algorithm algorithm, String exception, String expect) {
			super();
			this.name = name;
			this.type = type;
			this.algorithm = algorithm;
			this.exception = exception;
			this.expect = expect;
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

		public Algorithm getAlgorithm() {
			return algorithm;
		}

		public void setAlgorithm(Algorithm algorithm) {
			this.algorithm = algorithm;
		}

		public String getException() {
			return exception;
		}

		public void setException(String exception) {
			this.exception = exception;
		}

		public String getExpect() {
			return expect;
		}

		public void setExpect(String expect) {
			this.expect = expect;
		}

	}
}
