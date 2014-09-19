/**
 * 
 * @creatTime 下午5:16:05
 * @author Eddy
 */
package org.eddy.annotation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 算法枚举
 * 
 * @author Eddy
 * 
 */
public enum Algorithm {

	regx {
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eddy.annotation.Algorithm#match(java.lang.Object)
		 */
		@Override
		public boolean match(Object obj, Object expression) {
			super.checkParam(obj, expression);
			Pattern pattern = Pattern.compile(expression.toString());
			Matcher match = pattern.matcher(obj.toString());
			return match.matches();
		}
	},
	morethan {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eddy.annotation.Algorithm#match(java.lang.Object,
		 * java.lang.Object)
		 */
		@Override
		public boolean match(Object obj, Object expression) {
			super.checkParam(obj, expression);
			return Integer.parseInt(obj.toString()) > Integer.parseInt(expression.toString());
		}

	},
	lessthan {
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eddy.annotation.Algorithm#match(java.lang.Object,
		 * java.lang.Object)
		 */
		@Override
		public boolean match(Object obj, Object expression) {
			super.checkParam(obj, expression);
			return Integer.parseInt(obj.toString()) < Integer.parseInt(expression.toString());
		}
	},
	equal {
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eddy.annotation.Algorithm#match(java.lang.Object,
		 * java.lang.Object)
		 */
		@Override
		public boolean match(Object obj, Object expression) {
			super.checkParam(obj, expression);
			return obj.equals(expression);
		}
	};
	
	public static Algorithm getByName(String name) {
		switch (name) {
		case "lessthan":
			return lessthan;
		case "morethan":
			return morethan;
		case "equal":
			return equal;
		case "regx":
			return regx;
		default:
			return null;
		}
	}
	

	/**
	 * 算法具体的验证函数
	 * 
	 * @param obj
	 * @param expression
	 * @return
	 * @creatTime 下午5:38:27
	 * @author Eddy
	 */
	public boolean match(Object obj, Object expression) {
		throw new IllegalArgumentException("未支持的调用");
	}

	private void checkParam(Object obj, Object expression) {
		if (null == obj || null == expression) {
			throw new NullPointerException("参数非空");
		}
	}
}
