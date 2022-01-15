<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021-11-28
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>图新案件信息查询系统</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>首页</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript"></script>

    <script>
        //鼠标悬停表格内容变色：
        function changeColor(id,flag) {
            if (flag == "over") {
                document.getElementById(id).style.backgroundColor = "greenyellow";
            } else if (flag == "out") {
                document.getElementById(id).style.backgroundColor = "white";
            }
        }

    </script>

    <style>

        .master{
            width: 90%;
            height: 95%;
            margin: 0 auto;
        }


        .huanying_msg{
            color: blue;
            font-size: 20px;
        }

        .out{
            background-color: white;
        }
        .over{
            background-color: pink;
        }

        .search_div{
            width: 500px;
            /*height: 50px;*/
            margin: auto;
        }

        .error_div{
            color: red;
            text-align: center;
            padding-bottom: 20px;
        }


        .search_form{
            width:600px;
            height:42px;
            margin: 30px;
        }


        /*左边输入框设置样式*/
        .input_text{
            width:400px;
            height: 40px;
            border:1px solid green;
            /*清除掉默认的padding*/
            padding:0px;

            /*提示字首行缩进*/
            text-indent: 10px;

            /*去掉蓝色高亮框*/
            outline: none;

            /*用浮动解决内联元素错位及小间距的问题*/
            float:left;
        }

        .input_sub {
            width: 100px;
            height: 42px;
            background: green;

            /*去掉submit按钮默认边框*/
            border: 0px;
            /*改成右浮动也是可以的*/
            float: left;
            color: white; /*搜索的字体颜色为白色*/
            cursor: pointer; /*鼠标变为小手*/
        }

        td.td1{
            text-align: center;
            color: #419641;
            font-weight:bold;
        }


        td.td2{
            text-align: center;
            color: #0f0f0f;
        }

        .breadcrumb{
            margin: 10px;
            font-size: 15px;
            color: #080808;
        }

        .div_fuxuankuang{
            width: 450px;
            margin: 30px;
            margin-right: 400px;
            /*float: right;*/

        }

        .msg{
            color:red;
        }

        .table_div{
            padding: 10px;
        }

        .msg_count{
            width: 300px;
            color: crimson;
        }

        .time_html{
            color: #2aabd2;
        }
    </style>
</head>
<body>

<%--搜索界面--%>

<%--如果user不为空，同时error为空,那么代表字段列选择了，并且查到了数据（即使是0），即可以进行检索--%>
<c:if test="${not empty user}">
<ol class="breadcrumb">
    <li><a href="#" class="huanying_msg">${user[0].username}</a><a>,您好</a></li>
</ol>

<ol class="breadcrumb2">
    <l1><a href="#">欢迎使用图新案件信息查询系统</a></l1>
</ol>


<ol class="breadcrumb2">
    <l2><a href="${pageContext.request.contextPath}AuToUpdateMouthXlsx.jsp">点击我更新底层数据</a></l2>
</ol>

 <ol class="breadcrumb2">
     <l2><a href="${pageContext.request.contextPath}LiuCheng.jsp"><font color="red" size="3.5px">>>>点我前往【流程人员操作界面】</font></a></l2>
 </ol>

<div class="master">
    <div class="search_div">

        <form class="search_form" action="${pageContext.request.contextPath}/Search" method="post">
            <c:if test="${not empty Case_name }">
                <input type="text" class="input_text" placeholder="请输入搜索内容" name="Case_name" value="${Case_name}">
            </c:if>
            <c:if test="${empty Case_name }">
                <input type="text" class="input_text" placeholder="请输入搜索内容" name="Case_name">
            </c:if>
            <input type="submit" value="搜索" class="input_sub">
             <%--如果error不为空，代表没选择列，不知道查什么字段--%>
             <c:if test="${not empty error}">
            <font style="margin-right: 2px" color="red">请选择列！！</font>
            </c:if>
            <div class="div_fuxuankuang">


                <label class="msg" >请选择列： </label>

                <c:if test="${not empty CaseName }">
                    <label><input name="Fruit" type="radio" value="CaseName" checked class="filed"/>案件名称 </label>
                </c:if>
                <c:if test="${empty CaseName }">
                    <label><input name="Fruit" type="radio" value="CaseName" class="filed"/>案件名称 </label>
                </c:if>

                <c:if test="${not empty Casetype }">
                    <label><input name="Fruit" type="radio" value="Casetype" checked class="filed" />案件类型 </label>
                </c:if>
                <c:if test="${empty Casetype }">
                    <label><input name="Fruit" type="radio" value="Casetype" class="filed" />案件类型 </label>
                </c:if>

                <c:if test="${not empty DuralTheConveyancer }">
                    <label><input name="Fruit" type="radio" value="DuralTheConveyancer" checked class="filed" />内部撰写人 </label>
                </c:if>

                <c:if test="${empty DuralTheConveyancer }">
                    <label><input name="Fruit" type="radio" value="DuralTheConveyancer" class="filed" />内部撰写人 </label>
                </c:if>

                <c:if test="${not empty Outsourcer }">
                    <label><input name="Fruit" type="radio" value="Outsourcer"  checked class="filed" />外部撰写人 </label>
                </c:if>

                <c:if test="${empty Outsourcer }">
                    <label><input name="Fruit" type="radio" value="Outsourcer" class="filed" />外部撰写人 </label>
                </c:if>
            </div>
        </form>
    </div>

    <c:if test="${not empty Case}">
        <div><label class="msg_count">共统计:${Case.size()}条,  <acronym class="time_html">耗时:${time}秒</acronym></label></div>
    </c:if>

    <c:if test="${empty Case and not empty time}">
        <div><label class="msg_count"> 共统计:0条,<acronym class="time_html">耗时:${time}秒</acronym></label></div>
    </c:if>


    <div class="table_div">
        <table class="table table-bordered">
            <div>
                <tr>
                    <td class="td1">客户</td>
                    <td class="td1">案件类型</td>
                    <td class="td1">案件名称</td>
                    <td class="td1">公司撰写人</td>
                    <td class="td1">外部撰写人</td>
                    <td class="td1">股东</td>
                    <td class="td1">接案时间</td>
                </tr>
            </div>

            <c:if test="${not empty Case}">
                <c:forEach  begin="0" end="${Case.size()-1}" var="i">
                    <div>
                        <tr onmouseover="changeColor(id,'over')" onmouseout="changeColor(id,'out')" id="tr+${Case[i]}">
                            <td class="td2" >${Case[i].customer}</td>
                            <td class="td2" >${Case[i].casetype}</td>
                            <td class="td2" >${Case[i].casename}</td>
                            <td class="td2" >${Case[i].duraltheconveyancer}</td>
                            <td class="td2" >${Case[i].outsourcer}</td>
                            <td class="td2" >${Case[i].nameofshareholder}</td>
                            <td class="td2" >${Case[i].caseReceivingperiod}</td>
                        </tr>
                    </div>
                </c:forEach>
            </c:if>
        </table>
    </div>

</div>
</c:if>

<%--如果user为空,代表压根不是从前端登录进来的,不给看--%>
<c:if test="${empty user}">
<font color="red">请登录！！</font>
</c:if>
</body>
</html>
