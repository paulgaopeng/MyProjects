<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

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
			//发送异步请求,加载新闻主题 列表
			$.post("admin/adminNewsType",{"method":"getNewsTypeList"},function(result){
				//将json字符串转为JSON数组对象
				var arr = eval("("+result+")");
				$.each(arr,function(index,content){
					//$("#selectTypeId").append("<option value='"+content.typeId+"'>"+(index+1)+"-"+content.typeName+"</option>");
					$("#selectTypeId").append($("<option></option>").val(content.typeId).html((index+1)+"-"+content.typeName));
				});	
			});
		});
		</script>
	</head>

	<body onload="disptime()">
		<!--顶部导航栏-->
		<%@include file="top.jsp" %>
		<div class="container-fluid">
			<div class="row clearfix">
				<!--左边栏 导航-->
				<jsp:include page="left.jsp">
					<jsp:param value="newsManage" name="paramName"/>
				</jsp:include>
				<!--右边栏  主要显示区-->
				<div id="col2" class="col-sm-10 col-md-10 column" data-spy="scroll" data-target="#myNavBar" 
					data-offset="0" style="height:600px;overflow:auto;position:relative;">
					<div id="nav" style="border-bottom: 1px solid #EEEEEE;">
						<ul class="breadcrumb">
							<li>
								<span class="fa fa-tasks" style="vertical-align: middle;"></span>
								<a rel="nofollow" href="javascript:void(0);">功能管理</a>
							</li>
							<li class="active">
								新闻管理
							</li>
						</ul>
					</div>
					<!--这里添加内容-->
					<div class="panel panel-default" style="margin-top:5px;">
						<div class="panel-heading">
							<h3 class="panel-title">
								查询条件
							</h3>
						</div>
						<div class="panel-body">
							<div class="col-md-12 column">
								<form class="form-horizontal">
									<div class="row clearfix">
										<div class="col-md-5 column">
											<div class="form-group">
												<label for="selectTypeId" class="col-sm-4 control-label">主题类别</label>
												<div class="col-sm-7">
													<select class="form-control" id="selectTypeId">
														<option value="0"></option>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label for="inputNewsAuthor" class="col-sm-4 control-label">作者</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="inputNewsAuthor" />
												</div>
											</div>
										</div>
										<div class="col-md-5 column">
											<div class="form-group">
												<label for="inputNewsTitle" class="col-sm-4 control-label">新闻标题</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="inputNewsTitle" />
												</div>
											</div>
											<div class="form-group">
												<label for="inputNewsPubDate" class="col-sm-4 control-label">发布日期</label>
												<div class="col-sm-7">
													<input type="date" class="form-control" id="inputNewsPubDate">
												</div>
											</div>
										</div>
										<div class="col-md-2 column" style="padding-top:50px;">
											<button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp;
											<button type="reset" class="btn btn-default">重置</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>	
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								查询列表
							</h3>
						</div>
						<div class="col-md-12 column" style="line-height: 40px;">
							<div class="row clearfix">
								<div class="col-md-9 column"></div>
								<div class="col-md-3 column" style="text-align: right;">
									<input type="button" value="添加新闻" class="btn btn-default" 
										onclick="javascript:location.href='add_news.html'"
										style="border:1px solid #fff;color:#fff;background-color:#EC971F;">
								<input type="button" value="批量删除" class="btn btn-default" 
										style="border:1px solid #fff;color:#fff;color:#fff;background-color: #EC971F;"
										id="btnBatchDelete">
								</div>
								
							</div>
						</div>
						<div class="panel-body">
							<table class="table table-hover" align="center" style="margin-top:2px auto;width:90%;">
								<thead>
									<tr>
										<th><input type="checkbox" id="inlineCheckboxAll" value="option1"></th>
										<th>序号</th>
										<th>新闻标题</th>
										<th>新闻作者</th>
										<th>发布日期</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><input type="checkbox" id="inlineCheckbox1" value="option1"></td>
										<td scope="row">1</td>
										<td>深足教练组：说我们买球是侮辱 朱广沪常暗中支招 </td>
										<td>sport</td>
										<td>2016-10-28 01:03:51</td>
										<td>
											<a href="edit_news.html"><span class="fa fa-edit"></span>编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
											<a href="#" data-toggle="modal" data-target="#deleteNews"><span class="fa fa-trash-o"></span>删除</a>
										</td>
									</tr>
									<tr>
										<td><input type="checkbox" id="inlineCheckbox1" value="option1"></td>
										<td scope="row">2</td>
										<td>气候变化导致海平面上升 </td>
										<td>肖锋</td>
										<td>2016-10-27 01:03:51</td>
										<td>
											<a href="edit_news.html"><span class="fa fa-edit"></span>编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
											<a href="#" data-toggle="modal" data-target="#deleteNews"><span class="fa fa-trash-o"></span>删除</a>
										</td>
									</tr>
									<tr>
										<td><input type="checkbox" id="inlineCheckbox1" value="option1"></td>
										<td scope="row">3</td>
										<td>洪元硕：北京人的脸就看你们了 最后一哆嗦看着办 </td>
										<td>小强</td>
										<td>2016-10-26 01:03:51</td>
										<td>
											<a href="edit_news.html"><span class="fa fa-edit"></span>编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
											<a href="#" data-toggle="modal" data-target="#deleteNews"><span class="fa fa-trash-o"></span>删除</a>
										</td>
									</tr>
									<tr>
										<td><input type="checkbox" id="inlineCheckbox1" value="option1"></td>
										<td scope="row">4</td>
										<td>国内成品油价格上调几成定局  </td>
										<td>付磊</td>
										<td>2016-10-24 21:23:30</td>
										<td>
											<a href="edit_news.html"><span class="fa fa-edit"></span>编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
											<a href="#" data-toggle="modal" data-target="#deleteNews"><span class="fa fa-trash-o"></span>删除</a>
										</td>
									</tr>
									<tr>
										<td><input type="checkbox" id="inlineCheckbox1" value="option1"></td>
										<td scope="row">5</td>
										<td>国台办介绍大陆高校加大对台招生力度情况   </td>
										<td>付磊</td>
										<td>2016-10-22 19:20:31</td>
										<td>
											<a href="edit_news.html"><span class="fa fa-edit"></span>编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
											<a href="#" data-toggle="modal" data-target="#deleteNews"><span class="fa fa-trash-o"></span>删除</a>
										</td>
									</tr>
								</tbody>
							</table>
							<!--分页组件-->
								<div style="margin:0px;text-align:center;margin-top:0px;border-top: 1px solid #CCCCCC;">
									<ul class="pagination">
										<li>
											<a href="#">«</a>
										</li>
										<li class="active">
											<a href="#">1</a>
										</li>
										<li class="disabled">
											<a href="#">2</a>
										</li>
										<li>
											<a href="#">3</a>
										</li>
										<li>
											<a href="#">4</a>
										</li>
										<li>
											<a href="#">5</a>
										</li>
										<li>
											<a href="#">»</a>
										</li>
										<li>
											总记录 1 总页数 1 当前第 1 页 转到
											<input type="text" style="width:30px;text-align: center;" class="input" value="1" /> 页
											<button type="button" class="btn btn-primary active">确定</button>
										</li>
									</ul>
								</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 模态框   信息删除确认 -->
		<div class="modal fade" id="deleteNews">
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
						<input type="hidden" id="deletePersonId" />
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