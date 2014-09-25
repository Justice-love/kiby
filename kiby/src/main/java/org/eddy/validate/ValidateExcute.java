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

	private Param[] paramRule;

	public ValidateExcute(Param[] paramRule) {
		super();
		if (null == paramRule) {
			throw new IllegalArgumentException("param is not match!");
		}
		this.paramRule = paramRule;
	}
	
	public void changeValidate(int index, Param param) {
		if (paramRule.length <= index) throw new IllegalArgumentException("index out of bound");
		paramRule[index] = param;
	}

	/**
	 * 参数校验
	 * @creatTime 下午2:38:31
	 * @author Eddy
	 */
	public void validate(Object[] values) {
		if (null == values || values.length != paramRule.length) {
			throw new IllegalArgumentException("param is not match!");
		}
		for (int i = 0; i < paramRule.length; i++) {
			if (null == paramRule[i]) continue;
			if (!paramRule[i].getAlgorithm().match(values[i], paramRule[i].getExpect())) throw new IllegalArgumentException(paramRule[i].getException());
		}
		
	}

}
