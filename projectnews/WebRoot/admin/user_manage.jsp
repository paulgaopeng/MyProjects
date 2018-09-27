<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:if test="${not empty requestScope.msg}">
	<c:set var="flag" value="1" />
</c:if>
<c:if test="${empty requestScope.msg}">
	<c:set var="flag" value="0" />
</c:if>
<!DOCTYPE html>
<html>

	<head>
		<base href="<%=basePath%>">
		<title>新闻中国后台管理</title>
		<meta charset="utf-8">
		<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
		<!--让 Bootstrap 开发的网站对移动设备友好，确保适当的绘制和触屏缩放-->
		<meta name="viewport" content="width=device-width,initial-scale=1.0">
		<link href="lib/font-awesome/css/font-awesome.css" rel="stylesheet">
		<!--引入Bootstrap-->
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
		<!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
		<!--[if lt IE 9]>
	     <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	     <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
	  	<![endif]-->
		<!--jQuery (Bootstrap的Javascript插件需要引入 jQuery)-->
		<script src="js/jquery2.0.0.min.js"></script>
		<!--包含已编译的插件-->
		<script src="js/bootstrap.min.js"></script>
		<script src="js/myscript.js"></script>
		<link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
		<link rel="stylesheet" type="text/css" href="stylesheets/premium.css">
		<link href="css/myindex.css" rel="stylesheet">
		<script type="text/javascript">
		$(document).ready(function(){
			//设置显示消息	
			$(function(){
				//setTimeout(function(){$("#msg").fadeOut(2000);},3000);
				//是否有消息需要显示
				if("${pageScope.flag}"=='1'){
				    $("#msg").show();
					$("#msg").delay(3000).fadeOut(2000);  //延时淡出
				}
			});
			
			//"全选"点击事件 
			$("#chkAll").click(function(){
				if($(this).prop("checked")){
					$("[name='chk']").prop("checked",true);
				}else{
					$("[name='chk']").prop("checked",false);
				}
			});
			
			//每个子复选框点击事件
			$("[name='chk']").click(function(){
				if($(this).prop("checked")){
					//获取当前所有选中的子复选框的数量
					var selectCount = $("[name='chk']:checked").length;
					var sumLength = $("[name='chk']").length;
					if(selectCount<sumLength){
						$("#chkAll").prop("checked",false);
					}else{
						$("#chkAll").prop("checked",true);
					}
				}else{
					$("#chkAll").prop("checked",false);
				}
			});
			
			//点击删除链接时
			$("[name='del_link']").click(function(){
				//将要删除的userId传入模态框
				$("#deleteUserId").val($(this).attr("rel"));
				//显示模态框
				$("#deleteUser").modal();
			});
			
			//点击模态框中"确认删除"
			$("#deleteHaulBtn").click(function(){
				window.location.href="admin/adminUser?method=deleteUserById&userId="+$("#deleteUserId").val();
			});
			
			//批量删除
			$("#btnBatchDelete").click(function(){
				var selectCount = $("[name='chk']:checked").length;
				if(selectCount==0){
					alert("请先选中要删除的用户!");
					return;
				}
				
				//获取选中的 userId
				var chkArr = document.getElementsByName("chk");
				var ids="";
				for(var i=0;i<chkArr.length;i++){
					if(chkArr[i].checked){
						ids += chkArr[i].value+",";
					}
				}
				//去除尾部的逗号
				ids = ids.substring(0,ids.length-1);
				if(confirm("您确认批量删除选中的用户吗?")){
					window.location.href="admin/adminUser?method=deleteUserBatch&ids="+ids;
				}
			});
			
			//加载查询类别
			var opsArr = [{"value":"1","text":"按用户名查询"},{"value":"2","text":"按姓名查询"}];
			
			$.each(opsArr,function(index,content){
				if("${requestScope.queryTypeId}"==content.value){
					//$("#selectTypeId").append("<option value='"+content.value+"' selected>"+content.text+"</option>");
					$("#selectTypeId").append($("<option selected></option>").val(content.value).html(content.text));
				}else{
					//$("#selectTypeId").append("<option value='"+content.value+"'>"+content.text+"</option>");
					$("#selectTypeId").append($("<option></option>").val(content.value).html(content.text));
				}
			});
			
			//查询类别改变时
			$("#selectTypeId").change(function(){
				if($(this).val()==""){
					$("#queryCondition").val("");
				}
			});
			
			//表单提交时
			$("#searchForm").submit(function(){
				if($("#selectTypeId").val()!="" && $("#queryCondition").val()==""){
					alert("请输入查询条件!");
					$("#queryCondition").focus();
					return false;				
				}
				return true;
			});
		});
		
		function gotoPage(currPage){
			if(isNaN(currPage)){
				return;
			}
			window.location.href="admin/adminUser?method=userList&currPage="+currPage+
				"&queryTypeId="+$("#selectTypeId").val()+"&queryKeyWords="+$("#queryCondition").val();
		}
		</script>
	</head>

	<body onload="disptime()">
		<!--顶部导航栏-->
		<%@ include file="top.jsp" %>
		<div class="container-fluid">
			<div class="row clearfix">
				<!--左边栏 导航-->
				<%--@ include  file="left.jsp" --%>
				<jsp:include page="left.jsp">
					<jsp:param value="userManage" name="paramName"/>
				</jsp:include>
				<!--右边栏  主要显示区-->
				<div id="col2" class="col-sm-10 col-md-10 column" data-spy="scroll" data-target="#myNavBar" 
					data-offset="0" style="height:600px;overflow:auto;position:relative;">
					<div id="nav" style="border-bottom: 1px solid #EEEEEE;">
						<ul class="breadcrumb">
							<li>
								<span class="fa fa-tasks" style="vertical-align: middle;"></span>
								<a rel="nofollow" href="javascript:void(0);">系统管理</a>
							</li>
							<li class="active">
								用户管理
							</li>
						</ul>
					</div>
					<!--这里添加内容-->
					<div class="panel panel-default" style="margin-top:5px;">
						<div class="panel-heading">
							<h3 class="panel-title">
								用户列表
							</h3>
						</div>
						<div class="panel-body">
							<div class="col-md-12 column">
								<form class="form-horizontal" id="searchForm" action="admin/adminUser?method=userList" method="post">
									<div class="row clearfix">
										<div class="col-md-2 column">
											<select class="form-control" id="selectTypeId" name="queryTypeId">
												<option value="">-选择查询类别-</option>
											</select>
										</div>
										<div class="col-md-2 column">
											<input type="text" class="form-control" placeholder="输入查询条件" id="queryCondition"
												name="queryKeyWords" value="${ requestScope.queryKeyWords }">
										</div>
										<div class="col-md-2 column">
											<input type="submit" value="查询" class="btn btn-primary" id="btnSearch">
										</div>
										<div class="col-md-3 column">
										</div>
										<div class="col-md-3 column">
											<input type="button" value="新增用户" onclick="javascript:location.href='admin/adminUser?method=gotoAddUser'" 
												class="btn btn-default" style="border:1px solid #fff;color:#fff;color:#fff;background-color:#EC971F;">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="button" value="批量删除" class="btn btn-default" 
												style="border:1px solid #fff;color:#fff;color:#fff;background-color: #EC971F;"
												id="btnBatchDelete">
										</div>
									</div>
								</form>
							</div>
							<br><br>
							<div class="panel-body" >
								<table class="table table-hover" style="margin-top:2px auto;">
									<thead>
										<tr style="background-color: #129E5A;color:#fff;">
											<th><input type="checkbox" id="chkAll" value="option1"></th>
											<th>编号</th>
											<th>用户名</th>
											<th>姓名</th>
											<th>性别</th>
											<th>联系方式</th>
											<th>E-Mail</th>
											<th>是否管理员</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										 <c:forEach  var="user" items="${ requestScope.userList }" varStatus="vs">
										 	<tr>
												<td>
													<input type="checkbox" id="inlineCheckbox${ vs.count }"
														name="chk" value="${ user.userId }"
														style="display:${sessionScope.loginUser.username eq user.username ? "none" : "block"}">
												</td>
												<td scope="row">${ vs.count+pageData.pageSize*(pageData.currPage-1)}</td>
												<td><span style="color:${sessionScope.loginUser.username eq user.username ? "#ff0000" : "#000000"}">${ user.username }</span></td>
												<td>${ user.realname}</td>
												<td>${ user.gender ? "男" : "女" }</td>
												<td>${ user.telephone }</td>
												<td>${ user.email}</td>
												<td>${ user.isAdmin ? "是" : "否" }</td>
												<td>
													<a href="edit_user.html"><span class="fa fa-pencil-square"></span> 编辑</a> &nbsp;| &nbsp;
													<a href="javascript:void(0);" name="del_link" rel="${ user.userId }"
														style="display:${sessionScope.loginUser.username eq user.username ? "none" : ""}">
														<span class="fa fa-trash"></span> 删除</a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<!--分页组件-->
								<div style="margin:0px;text-align:center;margin-top:0px;border-top: 1px solid #CCCCCC;">
									<br>
									<ul class="pagination" style="height:50px;">
									   <c:if test="${ pageData.currPage eq  1}">
										   	<li class="disabled">
												<a href="javascript:void(0);">«</a>
											</li>
									   </c:if>
									   <c:if test="${ pageData.currPage ne  1}">
											<li>
												<a href="javascript:gotoPage(${ pageData.currPage-1 })">«</a>
											</li>
										</c:if>
										<c:forEach var="pageNum" begin="1" end="${ pageData.sumPages }">
										    <li class="${ pageNum eq pageData.currPage ? "active" : ""}">
												<a href="javascript:gotoPage(${ pageNum })">${ pageNum }</a>
											</li>
										</c:forEach>
										<c:if test="${ pageData.currPage eq  pageData.sumPages}">
										   	<li class="disabled">
												<a href="javascript:void(0);">»</a>
											</li>
									   </c:if>
									   <c:if test="${ pageData.currPage ne  pageData.sumPages}">
											<li>
												<a href="javascript:gotoPage(${ pageData.currPage+1 })">»</a>
											</li>
										</c:if>
										<li>
											总记录 ${ pageData.maxCount } 总页数 ${ pageData.sumPages } 当前第 ${ pageData.currPage } 页 转到
											<input type="text" style="width:30px;text-align:center;" class="input" value="1" id="skipPage"
											onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/> 页
											<button type="button" class="btn btn-primary active" onclick="gotoPage(skipPage.value)" >确定</button>
										</li>
									</ul>
								</div>
							</div>
							<div class="panel-footer" id="msg" 
								style="background-color:#47BB67;display:none;color:#fff;text-align:center;">${ msg }</div>
							</div>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 模态框   信息删除确认 -->
		<div class="modal fade" id="deleteUser">
			<div class="modal-dialog">
				<div class="modal-content message_align">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
						<h4 class="modal-title">提示</h4>
					</div>
					<div class="modal-body">
						<!-- 隐藏需要删除的id -->
						<input type="hidden" id="deleteUserId" />
						<p>您确认要删除吗？</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary" id="deleteHaulBtn">确认删除</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		
		<!--设置菜单折叠更改图标脚本 -->
		<script>
			$(function() {
				$(".nav-header").click(function(e) {
					/*切换折叠指示图标*/
					$(this).find("span").toggleClass("glyphicon-chevron-down");
					$(this).find("span").toggleClass("glyphicon-chevron-up");
				});
			});
		</script>
		<script>
			h = document.body.clientHeight;
			if(document.body.clientHeight < document.documentElement.clientHeight) {
				h = document.documentElement.clientHeight
			}
			colHeight = h - document.getElementById("col1").offsetTop;
			document.getElementById("col1").style.height = colHeight + "px";
			document.getElementById("col2").style.height = colHeight + "px";
		</script>
	</body>

</html>