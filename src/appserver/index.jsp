<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 15-5-8
  Time: 下午3:41
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/taglib.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title><h1>快递实名业务处理中心</h1></title>
    <style>
        body {
            text-align: center
        }

        .div {
            margin: 0 auto;
            width: 400px;
            height: 100px;
        }

        /* css注释：为了观察效果设置宽度 边框 高度等样式 */
    </style>
    <script language="javascript">
        function down_android() {
            document.getElementById('downAndroid').href = "<c:url value="/DownLoadClient"/>";
        }
    </script>
</head>

<body>
<div style="text-align:center;">
    <h1>欢迎进入快递实名业务处理中心</h1>
    <a href="javascript:void(0);" id="downAndroid" onclick="down_android();"><font color="#00008b">安卓客户端</font></a>&nbsp;
    <p/>

    <div class="div"><img width="150" height="150" src="ClientVersionAction_qrcode.action"/></div>
</div>
</body>
</html>
