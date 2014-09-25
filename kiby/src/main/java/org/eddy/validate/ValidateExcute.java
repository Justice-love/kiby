/**
 * 
 * @creatTime 上午11:38:34
 * @author Eddy
 */
package org.eddy.validate;

import org.eddy.xml.Rule.Param;

/**
 * @author Eddy
 * 
 */
public class ValidateExcute {

	private Object[] values;
	private Param[] paramRule;

	public ValidateExcute(Object[] values, Param[] paramRule) {
		super();
		if (null == values || null == paramRule || values.length != paramRule.length) {
			throw new IllegalArgumentException("param is not match!");
		}
		this.values = values;
		this.paramRule = paramRule;
	}

	/**
	 * 
	 * @creatTime 下午2:38:31
	 * @author Eddy
	 */
	public void validate() {
		for (int i = 0; i < paramRule.length; i++) {
			if (null == paramRule[i]) continue;
			if (!paramRule[i].getAlgorithm().match(values[i], paramRule[i].getExpect())) throw new IllegalArgumentException(paramRule[i].getException());
		}
		
	}

}
