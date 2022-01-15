<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/11/29
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>数据更新</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript"></script>
    <style>
        .row{
            width: 50%;
            margin: 0 auto;
        }
        .col-xs-3{
            margin: 40px;
        }

        .div_submit{
            width: 50px;
            margin: auto;
        }

        .div_submit2{
            padding: 20px;
            padding-left: 150px;
        }

    </style>
</head>
<body>
         <form class="" method="post" action="${pageContext.request.contextPath}/AuToUpdateMouthXlsx">
             <div class="row">
                 <div class="col-xs-3">
                     <input type="text" class="form-control" placeholder="xlsx文件地址，如:/root/pwd/" name="execl_dir">
                     /root/1.xls、/root/2.xls
                 </div>
                 <div class="col-xs-3">
                     <input type="text" class="form-control" placeholder="sheet月份，如:2021-4" name="sheet_name">
                     2021-4、2021-5、2021-6
                 </div>
             </div>
                <div class="div_submit">
                    <div class="div_submit2">
                        <input type="submit" value="开始更新" >
                    </div>
                </div>
         </form>
</body>
</html>
