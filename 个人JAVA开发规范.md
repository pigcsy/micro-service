# 规范
## 类
### 命名
1. 对于api和service接口,controller层请求参数超过2个,要求封装成对象,并以XXRequest为结尾,响应参数为自定义复杂对象(如只需要返回一个list集合,是否可以直接List<E>响应,或者Map中put进一个list集合响应???)以XXResponse结尾。
2. interface中的方法 要添加修饰(public),default除外。
3. 涉及到对外输出的类实现Serializable序列化接口。

## 包

1. 包名 写
2. domain包下放置服务提供方请求响应实体, request、response、enum等模块内共用的实体类或者枚举。
3. controller包放置在web包下(\*.\*.\*..web.controller)
4. Feign请求响应对象放置于provider.domain下(\*.\*.\*..provider.domain)
