/**
 * 
 * @creatTime 下午3:23:52
 * @author Eddy
 */
package org.eddy.test;

import org.eddy.annotation.Algorithm;
import org.eddy.annotation.Validate;
import org.eddy.annotation.ValidateRule;
import org.springframework.stereotype.Component;

/**
 * @author Eddy
 *
 */
@Component("test")
public class Test {

	@ValidateRule(name="defaultRule2")
	public void test123(@Validate(algorithm = Algorithm.NOTNULL, expect = "") String str1, @Validate(algorithm = Algorithm.REGX, expect = "\\w*")String str2) {
		System.out.println("参数验证框架测试" + str1 + "   " + str2);
	}
	
	public void test(String str1, String str2) {
		System.out.println("参数验证框架测试" + str1 + "   " + str2);
	}
}
