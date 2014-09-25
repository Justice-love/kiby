/**
 * 
 * @creatTime 下午3:23:52
 * @author Eddy
 */
package org.eddy.test;

import org.eddy.annotation.ValidateRule;
import org.springframework.stereotype.Component;

/**
 * @author Eddy
 *
 */
@Component("test")
public class Test {

	@ValidateRule(name="defaultRule2")
	public void test123(String str1, String str2) {
		System.out.println("参数验证框架测试" + str1 + "   " + str2);
	}
}
