/**
 * 
 * @creatTime 下午5:12:02
 * @author Eddy
 */
package org.eddy.xml;

import java.util.List;

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
	private List<Param> params;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Param> getParams() {
		return params;
	}

	public void setParams(List<Param> params) {
		this.params = params;
	}

	/**
	 * 字段
	 * @author Eddy
	 *
	 */
	public class Param {
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
		
	}
}
