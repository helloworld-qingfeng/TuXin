<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width,initial-scale=1.0" />
  <title>用户登录</title>
  <link rel="stylesheet"  href="index_log.css" />

  <style>
    body {
      background: silver;
    }

    .tpt-login {
      width: 360px;
      height: 360px;
      padding: 50px 30px;
      background: #FFF;
      border-radius: 8px;
      margin: auto;
      position: absolute;
      top: 0;
      left: 0;
      bottom: 0;
      right: 0;
    }


    .tpt-login h2 {
      font-size: 28px;
      font-weight: 500;
      padding-bottom: 50px;
      text-align: center;
      color: #333;
    }


    .tpt-login input {
      width: 340px;
      padding: 0 10px;
      margin-bottom: 20px;
      height: 55px;
      line-height: 55px;
      border: 0;
      background: #f5f5f5;
      font-size: 16px;
      color: #666;
    }

    .tpt-login button {
      display: inline-block;
      height: 50px;
      line-height: 50px;
      width: 360px;
      background: #1e9fff;
      color: #fff;
      font-size: 16px;
      margin-top: 20px;
      border: none;
      border-radius: 2px;
      cursor: pointer;
    }
    .tpt-login p {
      font-size: 14px;
      color: #777;
    }
    .tpt-login a {
      font-size: 14px;
      color: #3581b9;
    }
    .tpt-login span {
      position: relative;
      display: inline-block;
      width: 7px;
      height: 7px;
      border-radius: 100%;
      border: 1px solid #ff5722;
      padding: 4px;
      top: 4px;
      margin-right: 6px;
    }
    .tpt-login i {
      position: absolute;
      display: inline-block;
      width: 7px;
      height: 7px;
      border-radius: 100%;
      background: #ff5722;
    }

  </style>

</head>

<body>

<%--正常的登录页面--%>
<div class="tpt-login">
  <h2>图新案件管理系统</h2>
  <form action="${pageContext.request.contextPath}/CheckUserServelt" method="post">
    <input type="text" name="username" placeholder="请输入账号">
    <input type="password" name="password" placeholder="请输入密码">
    <p>
      <%--<a>密码错误</a>--%>
    </p>
    <button>立即登录</button>
  </form>
</div>


</body>
</html>