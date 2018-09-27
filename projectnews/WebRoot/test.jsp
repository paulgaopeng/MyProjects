<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
  	<script type="text/javascript">
  	function refreshImage(obj){
  		obj.src="image?randNum="+Math.random();
  	}
  	</script>

  </head>
  
  <body>
    <img src="image" title="看不清,点击图片更换" onclick="refreshImage(this)" style="cursor:hand;"/>	
   </body>
</html>
