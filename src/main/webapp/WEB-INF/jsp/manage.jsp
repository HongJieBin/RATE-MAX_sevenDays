<%@ page import="com.memory.pojo.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.memory.pojo.Tag" %>
<%@ page import="com.memory.pojo.Chatroom" %><%--
  Created by IntelliJ IDEA.
  User: de'l'l
  Date: 2020/5/29
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>选项卡式导航</title>
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
    <style type="text/css">
        <!--
        * {
            margin: 0; padding:0
        }
        body {
            margin-top: 10px;
            margin-right: 10%;
            margin-bottom: 10px;
            margin-left: 10%;
            text-align: center;
            height: auto;
            width: auto;
            background-color: #666666;
            font-size: 12px;
            color: #000000;
        }
        #container {
            text-align: left;
            width: 1000px;
            height: 400px;
            padding: 20px;
        }
        #container #title {
            height: 28px;
        }
        #container #title li {
            float: left;
            list-style-type: none;
            height: 28px;
            line-height: 28px;
            text-align: center;
            margin-right: 1px;
        }
        #container #title ul {
            height: 28px;
        }
        #container #title a {
            text-decoration: none;
            color: #000000;
            display: block;
            width: auto;
        }
        #container #title a span{
            display: block;
            padding: 0 15px 0 15px;
        }
        #container #title #tag1 a:hover {
            text-decoration: none;
            display: block;
            width: auto;
        }
        #container #title #tag1 a:hover span{
            display: block;
            background-color: dodgerblue;
            padding: 0 15px 0 15px;
        }
        #container #title #tag2 a:hover {
            text-decoration: none;
            display: block;
            width: auto;
        }
        #container #title #tag2 a:hover span{
            background-color: dodgerblue;
            display: block;
            padding: 0 15px 0 15px;
        }
        #container #title #tag3 a:hover {
            text-decoration: none;
            display: block;
            width: auto;
        }
        #container #title #tag3 a:hover span{
            background-color: dodgerblue;
            display: block;
            padding: 0 15px 0 15px;
        }
        #container #title #tag4 a:hover {
            text-decoration: none;
            display: block;
            width: auto;
        }
        #container #title #tag4 a:hover span{
            background-color: dodgerblue;
            display: block;
            padding: 0 15px 0 15px;
        }
        #container #title #tag5 a:hover {
            text-decoration: none;
            display: block;
            width: auto;
        }
        #container #title #tag5 a:hover span{
            background-color: dodgerblue;
            display: block;
            padding: 0 15px 0 15px;
        }
        #container #title .selectli1 {
            text-decoration: none;
            color: #ffffff;
            display: block;
            width: auto;
        }
        #container #title a .selectspan1 {
            display: block;
            padding: 0 15px 0 15px;
        }
        #container #title .selectli2 {
            text-decoration: none;
            color: #ffffff;
            display: block;
            width: auto;
        }
        #container #title a .selectspan2 {
            display: block;
            padding: 0 15px 0 15px;
        }
        #container #title .selectli3 {
            text-decoration: none;
            color: #ffffff;
            display: block;
            width: auto;
        }
        #container #title a .selectspan3 {
            display: block;
            padding: 0 15px 0 15px;
        }
        #container #title .selectli4 {
            text-decoration: none;
            color: #ffffff;
            display: block;
            width: auto;
        }
        #container #title a .selectspan4 {
            display: block;
            padding: 0 15px 0 15px;
        }
        #container #title .selectli5 {
            text-decoration: none;
            color: #ffffff;
            display: block;
            width: auto;
        }
        #container #title a .selectspan5 {
            display: block;
            padding: 0 15px 0 15px;
        }
        #container #content ul {margin: 10px;}
        #container #content li {margin: 5px; }
        #container #content li img {margin: 5px;display:block;}
        #container #content {
            height: 480px;
            padding: 10px;
        }
        .content1 {
            border-top-width: 3px;
            border-right-width: 1px;
            border-bottom-width: 1px;
            border-left-width: 1px;
            border-top-style: solid;
            border-right-style: solid;
            border-bottom-style: solid;
            border-left-style: solid;
            border-top-color: #3A81C8;
            border-right-color: #3A81C8;
            border-bottom-color: #3A81C8;
            border-left-color: #3A81C8;
            background-color: #DFEBF7;
            overflow:scroll;
        }
        .content2 {
            border-top-width: 3px;
            border-right-width: 1px;
            border-bottom-width: 1px;
            border-left-width: 1px;
            border-top-style: solid;
            border-right-style: solid;
            border-bottom-style: solid;
            border-left-style: solid;
            border-top-color: #ff950b;
            border-right-color: #ff950b;
            border-bottom-color: #ff950b;
            border-left-color: #ff950b;
            background-color: #FFECD2;
            overflow:scroll;
        }
        .content3 {
            height: 300px;
            padding: 10px;
            border-top-width: 3px;
            border-right-width: 1px;
            border-bottom-width: 1px;

            border-left-width: 1px;
            border-top-style: solid;
            border-right-style: solid;
            border-bottom-style: solid;
            border-left-style: solid;
            border-top-color: #FE74B8;
            border-right-color: #FE74B8;
            border-bottom-color: #FE74B8;
            border-left-color: #FE74B8;
            background-color: #FFECF5;
            overflow:scroll;
        }
        .content4 {
            height: 300px;
            padding: 10px;
            border-top-width: 3px;
            border-right-width: 1px;
            border-bottom-width: 1px;
            border-left-width: 1px;
            border-top-style: solid;
            border-right-style: solid;
            border-bottom-style: solid;
            border-left-style: solid;
            border-top-color: #00988B;
            border-right-color: #00988B;
            border-bottom-color: #00988B;
            border-left-color: #00988B;
            background-color: #E8FFFD;
            overflow:scroll;
        }
        .hidecontent {display:none;}

        table {
            border-collapse: collapse;
            border-spacing: 0;
        }
        thead{
            text-align:center;
        }
        td,th {
            padding: 0;
            text-align:center;
        }

        .pure-table {
            border-collapse: collapse;
            border-spacing: 0;
            empty-cells: show;
            border: 1px solid #cbcbcb;
        }

        .pure-table caption {
            color: #000;
            font: italic 85%/1 arial,sans-serif;
            padding: 1em 0;
            text-align: center;
        }

        .pure-table td,.pure-table th {
            border-left: 1px solid #cbcbcb;
            border-width: 0 0 0 1px;
            font-size: inherit;
            margin: 0;
            overflow: visible;
            padding: .5em 1em;
        }

        .pure-table thead {
            background-color: #e0e0e0;
            color: #000;
            text-align: left;
            vertical-align: bottom;
        }

        .pure-table td {
            background-color: transparent;
        }

        .pure-table-bordered td {
            border-bottom: 1px solid #cbcbcb;
        }

        .pure-table-bordered tbody>tr:last-child>td {
            border-bottom-width: 0;
        }
        .button {
            background-color: #4CAF50; /* Green */
            border: none;
            color: white;
            padding: 3px 15px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            -webkit-transition-duration: 0.4s; /* Safari */
            transition-duration: 0.4s;
            cursor: pointer;
        }

        .button1 {
            background-color: white;
            color: black;
            border: 2px solid #4CAF50;
        }

        .button1:hover {
            background-color: #4CAF50;
            color: white;
        }

        .button3 {
            background-color: white;
            color: black;
            border: 2px solid #f44336;
        }

        .button3:hover {
            background-color: #f44336;
            color: white;
        }

        -->
    </style>
    <script language="javascript">
        server_url = "/seven_days";
        function switchTag(tag,content)
        {
//    alert(tag);
//    alert(content);
            for(i=1; i < 6; i++)
            {
                if ("tag"+i==tag)
                {
                    document.getElementById(tag).getElementsByTagName("a")[0].className="selectli"+i;
                    document.getElementById(tag).getElementsByTagName("a")[0].getElementsByTagName("span")
                        [0].className="selectspan"+i;
                }else{
                    document.getElementById("tag"+i).getElementsByTagName("a")[0].className="";
                    document.getElementById("tag"+i).getElementsByTagName("a")[0].getElementsByTagName("span")
                        [0].className="";
                }
                if ("content"+i==content)
                {
                    document.getElementById(content).className="";
                }else{
                    document.getElementById("content"+i).className="hidecontent";
                }
                document.getElementById("content").className=content;
            }
        }
    </script>
    <script>
        function ban(){
            var id = event.target.id;
            var json={
                userId:id
            }
            $.ajax({
                url: server_url+'/ban/banUser',//地址
                dataType:'json',
                data:JSON.stringify(json),
                contentType: "application/json;charset = UTF-8",
                type: 'POST',//类型
                timeout: 10000,//超时
                //请求成功
                success: function (data) {
                    if (data.status == 200) {
                        alert("封禁成功");
                    }
                    if (data.status == 500) {
                        alert("该用户已被封禁");
                    }
                },
                //失败/超时
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("请求错误"+textStatus);
                }
            });
        }

        function disBan(){
            var id = event.target.id;
            var json={
                userId:id
            }
            $.ajax({
                url: server_url+'/ban/disBan',//地址
                dataType: 'json',//数据类型
                data: JSON.stringify(json),
                contentType: "application/json; charset=utf-8",
                type: 'POST',//类型
                timeout: 10000,//超时
                //请求成功
                success: function (data) {
                    if (data.status == 200) {
                        alert("已将该用户解封");
                    }
                    if (data.status == 500) {
                        alert("该用户没有被封禁");
                    }
                },
                //失败/超时
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("请求错误");
                }
            });
        }
        function deleteTag(){
            var id = event.target.id;
            alert(id);
            var json={
                tagId: id
            }
            $.ajax({
                url: server_url+'/admin/deleteTag',//删除标签
                dataType: 'json',//数据类型
                data: JSON.stringify(json),
                contentType: "application/json; charset=utf-8",
                type: 'POST',//类型
                timeout: 10000,//超时
                //请求成功
                success: function (data) {
                    //alert(data);
                    //alert(status);
                    if(data.status==200) {
                        alert("已将该标签删除");
                        //getAllTags();
                    }
                },
                //失败/超时
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("请求错误");
                }
            });
        }
        function addTag(){
            var tagValue=document.getElementById("tags").value;
            //alert(id);
            var json={
                tags:tagValue
            }
            $.ajax({
                url: server_url+'/admin/addTags',//添加标签
                dataType: 'json',//数据类型
                data: JSON.stringify(json),
                contentType: "application/json; charset=utf-8",
                type: 'POST',//类型
                timeout: 10000,//超时
                //请求成功
                success: function (data) {
                    //alert(data);
                    //alert(status);
                    if (data.status == 200) {
                        alert("添加成功！");
                    }
                },
                //失败/超时
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("请求错误");
                }
            });
        }
        function getAllTags(){
            $.ajax({
                url: server_url+'/admin/getAllTags',//发送请求获取所有的标签，写到request里
                dataType: 'json',//数据类型
                data: {},
                contentType: "application/json; charset=utf-8",
                type: 'GET',//类型
                timeout: 10000,//超时
                //请求成功
                success: function (data) {
                    //alert(data);
                    //alert(status);
                    if (data.status == 200) {
                        var tags=data.data;
                        var tagListDom=document.getElementById("tagList");
                        var tagHtml="";
                        for (var i = 0; i <tags.length; i++) {
                            tagHtml += '<tr><td >'+tags[i].tagId+'</td>' +
                                '<td >'+tags[i].tagName+'</td>' +
                                '<td >'+'<input class="button button3" type="button" value="删除" id ="'+tags[i].tagId+'" onclick="deleteTag()"/></td></tr>';
                        }
                        tagListDom.innerHTML=tagHtml;
                    }
                },
                //失败/超时
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("请求错误");
                }
            });
        }
        function t(s){
            return s<10?"0"+s:s;
        }

        function getAllChatrooms(){
            var json={}
            $.ajax({
                url: server_url+'/admin/getAllChatRoom',//获取全部聊天室
                dataType: 'json',//数据类型
                data: {},
                contentType: "application/json; charset=utf-8",
                type: 'GET',//类型
                timeout: 10000,//超时
                //请求成功
                success: function (data) {
                    if (data.status == 200) {
                        var chatrooms=data.data;
                        var chatroomListDom=document.getElementById("chatroomList");
                        var chatroomHtml="";
                        for (var i = 0; i <chatrooms.length; i++) {
                            var startDate=new Date(chatrooms[i].chatroomStart);
                            var endDate=new Date(chatrooms[i].chatroomEnd);
                            chatroomHtml += '<tr><td >'+chatrooms[i].chatroomId+'</td>' +
                                '<td >'+chatrooms[i].chatroomName+'</td>'+
                                '<td >'+startDate.getFullYear()+'.'+t(startDate.getMonth()+1)+'.'+t(startDate.getDate())+' '+t(startDate.getHours())+':'+t(startDate.getMinutes())+':'+t(startDate.getSeconds())+'</td>'+
                                '<td >'+endDate.getFullYear()+'.'+t(endDate.getMonth()+1)+'.'+t(endDate.getDate())+' '+t(endDate.getHours())+':'+t(endDate.getMinutes())+':'+t(endDate.getSeconds())+'</td>'+
                                '<td >'+chatrooms[i].chatroomNumber+'</td>'+
                                '<td>'+chatrooms[i].chatroomHot+'</td>'+
                                '<td>'+chatrooms[i].userId+'</td>' +
                                '<td >'+'<input class="button button3" type="button" value="关闭该聊天室" id ="'+chatrooms[i].chatroomId+'" onclick="closeChatroom()"/></td>'+
                                '<td>'+'<input class="button button1" type="button" value="开启聊天室" id ="'+chatrooms[i].chatroomId+'" onclick="openChatroom()"/></td></tr>';
                        }
                        chatroomListDom.innerHTML=chatroomHtml;
                    }
                },
                //失败/超时
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("请求错误");
                }
            });
        }

        function closeChatroom(){
            var id = event.target.id;
            var json={
                chatRoomId:id
            }
            $.ajax({
                url: server_url+'/admin/closeChatRoom',
                dataType: 'json',//数据类型
                data: JSON.stringify(json),
                contentType: "application/json; charset=utf-8",
                type: 'POST',//类型
                timeout: 10000,//超时
                //请求成功
                success: function (data) {
                    if (data.status == 200) {
                        alert("成功关闭该聊天室！");
                    }
                    if (data.status == 555) {
                        alert("该聊天室已被关闭！");
                    }
                    //alert(data);
                    //alert(status);
                },
                //失败/超时
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("请求错误");
                }
            });
        }
        function openChatroom(){
            var id = event.target.id;
            var json={
                chatRoomId:id
            }
            $.ajax({
                url: server_url+'/admin/openChatRoom',
                dataType: 'json',//数据类型
                data: JSON.stringify(json),
                contentType: "application/json; charset=utf-8",
                type: 'POST',//类型
                timeout: 10000,//超时
                //请求成功
                success: function (data) {
                    if (data.status == 200) {
                        alert("成功开启聊天室！");
                    }
                    if (data.status == 555) {
                        alert("该聊天室没有被关闭！");
                    }
                    //alert(data);
                    //alert(status);
                },
                //失败/超时
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("请求错误");
                }
            });
        }
        function searchReport(){
            var reasonselect=document.getElementById("reasonSelect");
            var typeselect=document.getElementById("typeSelect");
            var reasonIndex=reasonselect.selectedIndex;
            var typeIndex=typeselect.selectedIndex;
            var reasonValue=reasonselect.options[reasonIndex].value;
            var typeValue=typeselect.options[typeIndex].value;
            var json={
                reportReasonId:reasonValue,
                reportTypeId:typeValue
            }
            $.ajax({
                url: server_url+'/admin/viewReport',
                dataType: 'json',//数据类型
                data: JSON.stringify(json),
                contentType: "application/json; charset=utf-8",
                type: 'POST',//类型
                timeout: 10000,//超时
                //请求成功
                success: function (data) {
                    if (data.status == 200) {
                        var reports=data.data;
                        var reportListDom=document.getElementById("reportList");
                        var reportHtml="";
                        for (var i = 0; i <reports.length; i++) {
                            var date=new Date(reports[i].reportDate);
                            reportHtml += '<tr><td >'+reports[i].reportReason+'</td>' +
                                '<td >'+reports[i].reportContent+'</td>'+
                                '<td >'+date.getFullYear()+'.'+t(date.getMonth()+1)+'.'+t(date.getDate())+' '+t(date.getHours())+':'+t(date.getMinutes())+':'+t(date.getSeconds())+'</td>'+
                                '<td >'+reports[i].sendId+'</td>'+
                                '<td >'+reports[i].reportTypeId+'</td>'+
                                '<td >'+'<input class="button button3" type="button" value="审核通过" id ="'+reports[i].reportId+'" onclick="handleReport('+reports[i].reportId+','+reports[i].reportTypeId+','+reports[i].reportedId+')"/></td></tr>';
                        }
                        reportListDom.innerHTML=reportHtml;
                    }
                    //alert(reportHtml);
                    //alert(status);
                },
                //失败/超时
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("请求错误");
                }
            });
        }

        function handleReport(reportId,reportTypeId,reportedId){
            var json={
                reportId:reportId,
                reportTypeId:reportTypeId,
                reportedId:reportedId
            }
            $.ajax({
                url: server_url+'/admin/handleReport',
                dataType: 'json',//数据类型
                data: JSON.stringify(json),
                contentType: "application/json; charset=utf-8",
                type: 'POST',//类型
                timeout: 10000,//超时
                //请求成功
                success: function (data) {
                    //alert(data);
                    //alert(status);
                    if (data.status == 200) {
                        alert("处理完毕！");
                    }
                    if(data.status==555){
                        alert("已将该用户/聊天室封禁！");
                    }
                },
                //失败/超时
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("请求错误"+textStatus);
                }
            });
        }
    </script>
</head>
<body>
<div id="container">
    <div id="title">
        <ul>
            <li id="tag1"><a href="#" onclick="switchTag('tag1','content1');this.blur();" class="selectli1"><span class="selectspan1" style="font-size: 20px">用户管理</span></a></li>
            <li id="tag2"><a href="#" onclick="getAllChatrooms();switchTag('tag2','content2');this.blur();"><span style="font-size: 20px">聊天室管理</span></a></li>
            <li id="tag3"><a href="#" onclick="getAllTags();switchTag('tag3','content3');this.blur();"><span style="font-size: 20px">标签管理</span></a></li>
            <li id="tag4"><a href="#" onclick="switchTag('tag4','content4');this.blur();"><span style="font-size: 20px">举报审核</span></a></li>
        </ul>
    </div>
    <div id="content" class="content1">
        <div id="content1" >
            <table id="userManage" border="1" width="100%" class="pure-table pure-table-bordered">
                <thead>
                <tr class="success">
                    <th >用户ID</th>
                    <th> 用户账号</th>
                    <th >用户昵称</th>
                    <th>性别</th>
                    <th> 简介</th>
                    <th colspan="2">操作</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<User> users = (List)request.getAttribute("users");
                    for (User user : users) {
                %>
                <tr>
                    <td ><%= user.getUserId()%></td>
                    <td ><%= user.getTelephone()%></td>
                    <td ><%= user.getNickname()%></td>
                    <td ><%= user.getGender()%></td>
                    <td ><%= user.getProfile()%></td>
                    <td ><input class="button button3" type="button" value="封禁" id = <%= user.getUserId()%> onclick="ban()"/></td>
                    <td><input class="button button1" type="button" value="解封" id = <%= user.getUserId()%> onclick="disBan()"/></td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
        <div id="content2" class="hidecontent">
            <table id="chatroomManage" border="1" width="100%" class="pure-table pure-table-bordered">
                <thead>
                <tr class="success">
                    <th >聊天室ID</th>
                    <th> 聊天室名称</th>
                    <th >开始时间</th>
                    <th>结束时间</th>
                    <th> 聊天室人数</th>
                    <th>聊天室热度</th>
                    <th>创建者ID</th>
                    <th colspan="2">操作</th>
                </tr>
                </thead>
                <tbody id="chatroomList"></tbody>
            </table>
        </div>

        <div id="content3" class="hidecontent">
            输入要添加的标签,标签之间请用英文逗号分隔，例如：孤独,浪漫
            <input type="text" id="tags" value=""/>
            <input class="button button1" type="button" value="添加" onclick="addTag()"/>
            <br>
            <table id="tagManage" border="1" width="100%" class="pure-table pure-table-bordered">
                <thead>
                <tr class="success">
                    <th >标签ID</th>
                    <th> 标签名称</th>
                    <th >操作</th>
                </tr>
                </thead>
                <tbody id="tagList">
                </tbody>
            </table>

    </div>

        <div id="content4" class="hidecontent">
            <p>
                <select id="reasonSelect">
                    <option value="1">淫秽色情传播</option>
                    <option value="2">欺诈骗钱</option>
                    <option value="3">骚扰</option>
                    <option value="4">考试舞弊</option>
                    <option value="5">政治敏感</option>
                    <option value="6">暴力血腥</option>
                    <option value="7">侵犯隐私</option>
                    <option value="8">其他违规</option>
                </select>
                <select id="typeSelect">
                    <option value="1">用户信息违规</option>
                    <option value="2">聊天内容违规</option>
                    <option value="3">动态相关违规</option>
                    <option value="4">聊天室违规</option>
                    <option value="5">漂流瓶内容违规</option>
                </select>
                <input class="button button1" type="button" value="查询" onclick="searchReport()" />
            </p>
            <table id="reportManage" border="1" width="100%" class="pure-table pure-table-bordered">
                <thead>
                <tr class="success">
                    <th >举报原因ID</th>
                    <th> 举报内容</th>
                    <th >举报时间</th>
                    <th>举报人ID</th>
                    <th> 举报类型</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="reportList"></tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>