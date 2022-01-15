<%@ page import="java.util.List"%>
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
            width: 610px;
            height: 60px;
            margin: auto;
            /*border: 1px solid red;*/
        }


        .show_msg{
            width: 95%;
            height: 30px;
            margin: auto;
            /*border: 1px solid red;*/
        }


        .search_form1{
            /*width:780px;*/
            /*height:42px;*/
            float: left;
        }

        .search_form2{
            /*width:180px;*/
            /*height:42px;*/
            float: right;
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
            width: 200px;
            height: 42px;
            background: green;

            /*去掉submit按钮默认边框*/
            border: 0px;
            /*改成右浮动也是可以的*/
            /*float: left;*/
            color: white; /*搜索的字体颜色为白色*/
            cursor: pointer; /*鼠标变为小手*/
        }


        .input_sub2 {
            width: 200px;
            height: 42px;
            background: blueviolet;

            /*去掉submit按钮默认边框*/
            border: 0px;
            /*改成右浮动也是可以的*/
            /*float: left;*/
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
            /*color: crimson;*/
            float: left;
        }

        .down_xls{
            float: right;
        }

        .time_html{
            /*color: #2aabd2;*/
        }
    </style>
</head>
<body>

<ol class="breadcrumb">
    <li><a href="#" class="huanying_msg">图新</a><a>,您好</a></li>
</ol>

<ol class="breadcrumb2">
    <l1><a href="#">欢迎使用图新案件信息查询系统</a></l1>
</ol>

<ol class="breadcrumb2">
    <l2><a href="#">>>>点击我更新<font color="green" size="3.5px"><u>申报案件表</u></font>流程数据(接口暂时关闭)</a></l2>
</ol>

<ol class="breadcrumb2">
    <l2><a href="#">>>>>点击我更新<font color="#8a2be2" size="4.5px"><u>未完成案件表</u></font>流程数据(接口暂时关闭)</a></l2>
</ol>

<div class="master">

    <div class="search_div">

        <form class="search_form1" action="${pageContext.request.contextPath}/JiaoFei" method="post">
            <input type="submit" value="临近缴费过期案件查询" class="input_sub" >
        </form>

        <form class="search_form2" action="${pageContext.request.contextPath}/Undelivered_manuscript" method="post">
            <input type="submit" value="未交稿搜索" class="input_sub2">
        </form>

    </div>


    <%--临近缴费过期案件查询的结果展示，在第一次打开本页面的时候，这里不会显示，只有点击【临近缴费过期案件】才会显示--%>
    <c:if test="${not empty liuchengsearchdb}">
        <div><label class="msg_count">共统计:${liuchengsearchdb.size()}条,<acronym class="time_html">耗时:${time}秒</acronym></label></div>
        <div class="table_div">
            <table class="table table-bordered">
                <div>
                    <tr>
                        <td class="td1">申请人</td>
                        <td class="td1">专利名称</td>
                        <td class="td1">专利类型</td>
                        <td class="td1">专利（申请）号</td>
                        <td class="td1">申请日期</td>
                        <td class="td1">申请费截止日</td>
                    </tr>
                </div>


                <c:forEach  begin="0" end="${liuchengsearchdb.size()-1}" var="i">
                    <div>
                        <tr onmouseover="changeColor(id,'over')" onmouseout="changeColor(id,'out')" id="tr+${liuchengsearchdb[i]}">
                            <td class="td2" >${liuchengsearchdb[i].filed1}</td>
                            <td class="td2" >${liuchengsearchdb[i].filed2}</td>
                            <td class="td2" >${liuchengsearchdb[i].filed3}</td>
                            <td class="td2" >${liuchengsearchdb[i].filed4}</td>
                            <td class="td2" >${liuchengsearchdb[i].filed5}</td>
                            <td class="td2" ><font color="red" size="5px">${liuchengsearchdb[i].filed6}</font></td>
                        </tr>
                    </div>
                </c:forEach>
            </table>
        </div>
    </c:if>

    <%--未交稿的[已经有派写对象]案件查询的结果展示，在第一次打开本页面的时候，这里不会显示，只有点击【未交稿检索】才会显示--%>
    <c:if test="${not empty undelivered_manuscript}">
        <div class="show_msg">
        <div><label class="msg_count">共统计:${undelivered_manuscript.size()}条,<acronym class="time_html">耗时:${time}秒</acronym></label></div>

         <c:if test="${not empty server_file_path}">
            <div class="down_xls">
                <form action="${pageContext.request.contextPath}/download">
                       <input type="submit" value="下载文件" >
                       <input type="hidden" name="file_name" value="${server_file_path}">
                </form>
            </div>
       </c:if>
        </div>
        <div class="table_div">
            <table class="table table-bordered">
                <div>
                    <tr>
                        <td class="td1">类型</td>
                        <td class="td1">交稿时间</td>
                        <td class="td1">名称</td>
                        <td class="td1">公司撰写人</td>
                        <td class="td1">外部撰写人</td>
                        <td class="td1">状态</td>
                        <td class="td1">交接对象</td>
                    </tr>
                </div>

                    <c:forEach  begin="0" end="${undelivered_manuscript.size()-1}" var="i">
                        <div>
                            <tr onmouseover="changeColor(id,'over')" onmouseout="changeColor(id,'out')" id="tr+${undelivered_manuscript[i]}">
                                <td class="td2">${undelivered_manuscript[i].casetype}</td>
                                <td class="td2">${undelivered_manuscript[i].comittime}</td>
                                <td class="td2">${undelivered_manuscript[i].casename}</td>
                                <td class="td2">${undelivered_manuscript[i].neibu}</td>
                                <td class="td2">${undelivered_manuscript[i].waibu}</td>
                                <td class="td2">${undelivered_manuscript[i].status}</td>
                                <td class="td2">${undelivered_manuscript[i].gudong}</td>
                            </tr>
                        </div>
                    </c:forEach>

            </table>
        </div>
    </c:if>

</div>

</body>
</html>
