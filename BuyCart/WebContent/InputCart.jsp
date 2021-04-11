<%@ page import="java.io.IOException" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="Lin.NoteBook.Cart" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta "viewport"
     content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0,
      minimum-scale=1.0">
<meta http-equiv="X-UA-compatible" content="ie=edge">
<title>Document</title>
</head>
<body>
<table>
   <form action="Cart" method="post">
   <tr>
      <td>編號</td>
      <td><input type="text" name="booknum"></td>
   </tr>
   
      <td><button type=sumit>提交</button></td>
   </tr>   
   </form>
</table>



   <%
     SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日hh時mm分ss秒");
     List list = (List)session.getAttribute("messageList");
     if(list != null) {
       for(int i=0; i<list.size(); i++) {
    	   
    	Cart message = (Cart) list.get(i);
    	   
    	   
   %>
       
        編號:<%=message.getBooknum() %> <br>
        書名:<%=message.getBookname() %> <br>
        價格:<%=message.getPrice() %> <br>
        時間:<%=format.format(message.getCreateTime()) %> <br><br><br>                
   <%
       }
     }
   %>
   
</body>
</html>