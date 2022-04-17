# graduation_design
## 店铺管家——小型店铺管理系统 功能说明

/xxx为后端接口名

### 一、首页

​		使用css动画，降低页面打开时长。

​		开始使用按钮判断Local Storage是否存在用户名，若存在则放行，不存在则跳转至登录界面。

### 二、登录、注册功能

##### 		1、登录  /userLogin

​		根据用户名（唯一）获取用户信息，返回用户信息对象交给前端判断，若密码和输入密码一致，则放行至系统页面，并把用户名存储至Local Storage中（或存至链接参数中），方便后续查询数据库。

##### 		2、注册  /userRegister

​		前端用正则表达式判断输入格式是否正确，后端先判断用户名和手机号是否唯一，若无重复则将用户信息插入user_info表中，并创建用户的仓库表（user_stock）和用户销售表（user_sales）。返回布尔型true，false用于前端判断是否成功。

### 三、后台系统

##### 		1、首页  

###### 		Ⅰ、销售信息 /infoDisplay

​		获取user_sale表中的销售信息，根据当日订单数显示顾客数；将当日销售额累加，传递至前端渲染。

###### 		Ⅱ、快捷功能

​		常用功能按钮群，和后面功能一致。

###### 		Ⅲ、备忘录

​		本质上是todolist的实现方法，动态添加dom元素，将用户备忘数据添加至Local Storage，重启浏览器依然存在。

##### 		2、商品列表

###### 		Ⅰ、基本的增删改查  /stockDelete /stockInsert /searchByName /updateStock

​		前端使用elementUI统一样式。表格会根据商品数量添加class：当库存数量大于0小于10为警告黄色，库存为零则为危险红色。

​		/stockDelete：成功返回200，失败返回HTTP Status: 500

​		/stockInsert：成功返回200，失败返回HTTP Status: 500，请求接口失败返回Request Time-out HTTP 408

​		/searchByName：成功返回根据名称查询的结果，失败返回HTTP Status: 500

​		/updateStock：成功返回200，失败返回HTTP Status: 500

###### 	    Ⅱ、加载页面前获取数据  /getStockList /getStockAll

​		 /getStockList：获取仓库列表，sql语句中用limit分页，20为一组，通过vue渲染至前端

​		/getStockAll：负责获取商品总数来负责分页
