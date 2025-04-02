# 1.**管理员接口（仅管理员权限）**



### **1.1 添加新书**

​                ● **请求类型**：POST

​                ● **端点**：

​                ● **描述**：管理员添加新电子书到目录。

​                ● **认证**：需要 JWT 认证。

​                ● **请求参数**

​                ○ **{**

​                ○  **"****name****": "****书名****",**

​                ○  **"author": "作者"**

​                ○  **"****publishing_house****": "****出版社****",**

​                ○  **"category": "小说",**

​                ○  **"description": "描述",**

​                ○  **"****book_cover****": "https://yourdomain.com/covers/new.jpg",**

**}**

​                ● **成功响应**：返回添加的新书信息及 HTTP 201 Created 状态。

 

### **1.2 更新书籍信息**

​                ● **请求类型**：PUT

​                ● **端点**：

​                ● **描述**：更新指定书籍的信息。

​                ● **认证**：需要 JWT 认证。

​                ● **请求参数**

​                ● **成功响应**：返回更新后的书籍信息。

 

### **1.3 删除书籍**

**【建议把删除功能作隐藏处理】**

​                ● **请求类型**：DELETE

​                ● **端点**：

​                ● **描述**：删除指定书籍。

​                ● **认证**：需要 JWT 认证。

​                ● **请求参数****(暂定)****：book_id	admin_id**

​                ● **成功响应**：返回删除成功的状态消息。

------





# 2. **支付机制（Payment System）**

### **2.1支付电子书借阅费用**

方法：POST

端点：

描述：使用 HorsePay 模拟系统支付指定电子书的借阅费用。

认证：需要 JWT 认证。请求头需包含 Authorization: Bearer <token>

 

**请求参数（JSON 格式）：**

{

​    	  "book_id": 123,

​     	  "card_number": "1234567890123456",

  "expiry_date": "12/25",

  "cvv": "123"

}	

**成功响应（HTTP 200 OK）：**

{

  "transaction_id": "abc123",

  "status": "success",

  "message": "Payment successful for eBook hire"

}

**错误响应示例：**

HTTP 400 Bad Request（无效的卡信息）：

{

 		 "error": "Bad Request",

 		 "message": "Invalid card details"

}

HTTP 401 Unauthorized（缺少认证）

HTTP 500 Internal Server Error（支付网关异常）

使用 HorsePay 模拟支付系统（仅限测试开发用途）：

http://homepages.cs.ncl.ac.uk/daniel.nesbitt/CSC8019/HorsePay



### **2.2 获取支付历史记录**

方法：GET

端点：

描述：获取当前登录用户的支付历史。

认证：需要 JWT 认证。

 

**查询参数：**

page（可选，默认 1）

per_page（可选，默认 10）

 

**成功响应：**

{

  "total_payments": 25,

  "page": 1,

  "per_page": 10,

  "payments": [

​    {

​      "transaction_id": "abc123",

​      "book_id": 123,

​      "amount": 5.99,

​      "date": "2025-03-23T13:45:00Z"

​    }

​    // ...更多记录

  ]

}
