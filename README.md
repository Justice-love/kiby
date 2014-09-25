kiby
====

基于spring IOC， AOP实现的方法参数验证框架， 支持自定义匹配逻辑与匹配失败错误消息

可通过xml和参数注解两种形式定义验证逻辑， xml为默认模版配置， 通过方法参数名和参数类型匹配验证表达式
参数注解为私有配置方式，为单个方法参数自定义验证逻辑
参数注解方式 优先级大于 xml模版配置方式
目前支持的验证逻辑算法有：
REGX  正则验证
MORETHAN 大于
LESSTHAN 小于
EQUAL 相等
NOTNULL  不为空

目前不支持为同一个方法参数添加多个验证逻辑
