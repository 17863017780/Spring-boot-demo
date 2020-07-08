<%--
  Created by IntelliJ IDEA.
  User: chenjiahong11
  Date: 2019/10/21
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1" runat="server">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <script src="http://misc.360buyimg.com/??jdf/lib/jquery-1.6.4.js"type="text/javascript"></script>
    <title></title>
    <style type="text/css">
        html
        {
            height: 100%;
        }
        body
        {
            height: 100%;
        //background: #576a85;
        }
        ul{
        //float: left;
        }
        a{
            text-decoration:none;
        }
        li{
            list-style-type:none;
        }
        .if{
            frameborder:1;
            height:500px;
            width: 100%;;

        }


    </style>
</head>

<frameset cols="238,*" framespacing="0"  frameborder="no" border="0">
    <frame src="layui.html" name="leftFrame" scrolling="auto" id="leftFrame" title="leftFrame"/>
    <frame src="../../templates/interface.jsp" name="mainFrame" scrolling="auto" id="mainFrame" title="mainFrame" />
</frameset>

<body>
<div id="form1" runat="server">


    <div class="menu" id="menu" style="float:left;display:inline;width: 200px;margin-left: 30px">
        <h2>
            拍卖</h2>
        <ul>
            <li><a href="paimaiAccess.jsp" target="list">围观次数</a></li>
        </ul>
        <h2>
            频道</h2>
        <ul>
            <li><a href="QueryNotice.jsp" target="list">查询公告内容</a></li>

        </ul>
        <h2>
            中心</h2>
        <ul>
            <li><a href="getContentByAreaId.jsp" target="list">获取坑位内容</a></li>
        </ul>
    </div>
    <div style="float:left;display:inline;width: 1000px">
        <frame name="list" class="if">
        </frame>
    </div>
    <script type="text/javascript">
        $(".menu h2").click(function () {
            $(this).next("ul").slideToggle(300).siblings("ul").slideUp(400);
            //$(this).addClass("hover").siblings().removeClass("hover");
        })

        var oa = document.getElementById("menu").getElementsByTagName("a");
        for (i = 0; i < oa.length; i++) {
            oa[i].onclick = function () {
                var tit = self.parent.frames["title"].document.getElementsByTagName('div')[0].getElementsByTagName('p')[0];
                tit.innerHTML = this.innerHTML;
                for (i = 0; i < oa.length; i++) {
                    oa[i].className = '';
                }
                this.className = "cur";
            }
            oa[i].onfocus = function () {
                this.blur();
            }
        }
    </script>
</div>
</body>
</html>
