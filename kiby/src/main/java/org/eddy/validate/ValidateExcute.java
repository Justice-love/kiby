/**
 * 
 * @creatTime 上午11:38:34
 * @author Eddy
 */
package org.eddy.validate;

import java.util.HashMap;
import java.util.Map;

import org.eddy.annotation.Algorithm;
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
//			if (!paramRule[i].getAlgorithm().match(values[i], paramRule[i].getExpect())) throw new IllegalArgumentException(paramRule[i].getException());
			paramRule[i].match(values[i]);
		}
		
	}
	
	public void clear(int index) {
		if (paramRule.length <= index) throw new IllegalArgumentException("index out of bound");
		Param p = paramRule[index];
		if (p != null) {
			p.clear();
		}
	}

	/**
	 * 
	 * @param i
	 * @param algorithm
	 * @param exception
	 * @param expect
	 * @creatTime 上午9:47:00
	 * @author Eddy
	 */
	public void enhanceValidate(int index, Algorithm algorithm, String exception, String expect) {
		if (paramRule.length <= index) throw new IllegalArgumentException("index out of bound");
		algorithm.setException(exception);
		Param p = paramRule[index];
		if (null == p) {
			Map<Algorithm, String> alg = new HashMap<Algorithm, String>();
			alg.put(algorithm, expect);
			paramRule[index] = new Param(alg);
		} else {
			p.put(algorithm, expect);
		}
	}

}
