<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-sm-2 col-md-2 column">
	<ul id="main-nav" class="nav nav-tabs nav-stacked" style="">
		<li><a href="manager_index.html"> <i
				class="glyphicon glyphicon-home"></i> 首页 </a>
		</li>
		
		<c:choose>
			<c:when test="${ param.paramName eq 'topicManage' or param.paramName eq 'newsManage' or
			                param.paramName eq 'noticeManage' or param.paramName eq 'commentManage'}">
				<li><a href="#functionManage" id="collapseListGroupHeading1"
					class="nav-header collapsed" data-toggle="collapse"> <i
						class="glyphicon glyphicon-cog"></i> 功能管理 <span
						class="pull-right glyphicon glyphicon-chevron-up"></span> </a>
					<div id="functionManage" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="collapseListGroupHeading1">
						<ul class="nav nav-pills nav-stacked secondmenu">
							<li><a href="type_manage.html"><i class="fa fa-th"></i>
									主题管理</a></li>
							
							<c:choose>
								<c:when test="${param.paramName eq 'newsManage' }">
									<li><a href="admin/adminNews?method=newsList" style="color:#D58512"><i class="fa fa-newspaper-o"></i> 新闻管理</a></li>		
								</c:when>
								<c:otherwise>
									<li><a href="admin/adminNews?method=newsList"><i class="fa fa-newspaper-o"></i> 新闻管理</a></li>
								</c:otherwise>
							</c:choose>
							
							
							<li><a href="#"><i class="fa fa-bullhorn"></i> 公告管理</a></li>
							<li><a href="#"><i class="fa fa-comment"></i> 评论管理</a></li>
						</ul>
					</div>
				</li>
			</c:when>
			<c:otherwise>
				<li><a href="#functionManage" id="collapseListGroupHeading1"
					class="nav-header collapsed" data-toggle="collapse"> <i
						class="glyphicon glyphicon-cog"></i> 功能管理 <span
						class="pull-right glyphicon glyphicon-chevron-down"></span> </a>
					<div id="functionManage" class="panel-collapse collapse"
						role="tabpanel" aria-labelledby="collapseListGroupHeading1">
						<ul class="nav nav-pills nav-stacked secondmenu">
							<li><a href="type_manage.html"><i class="fa fa-th"></i>
									主题管理</a></li>
							<li><a href="admin/adminNews?method=newsList"><i class="fa fa-newspaper-o"></i> 新闻管理</a></li>
							<li><a href="#"><i class="fa fa-bullhorn"></i> 公告管理</a></li>
							<li><a href="#"><i class="fa fa-comment"></i> 评论管理</a></li>
						</ul>
					</div>
				</li>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${ param.paramName eq 'userManage' or param.paramName eq 'sysLog'}">
				<li><a href="#systemSetting" id="collapseListGroupHeading2"
					class="nav-header collapsed" data-toggle="collapse"> <i
						class="glyphicon glyphicon-list"></i> 系统管理 <span
						class="pull-right glyphicon glyphicon-chevron-up"></span> </a>
					<div id="systemSetting" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="collapseListGroupHeading2">
						<ul class="nav nav-pills nav-stacked secondmenu">
						    <c:choose>
							    <c:when test="${ param.paramName eq 'userManage' }">
									<li>
										<a href="admin/adminUser?method=userList" style="color:#D58512"><i class="fa fa-user"></i>
											用户管理</a>
									<li>
								</c:when>
								<c:otherwise>
									<li>
										<a href="admin/adminUser?method=userList"><i class="fa fa-user"></i>
											用户管理</a>
									<li>
								</c:otherwise>
							</c:choose>
							<c:choose>
							    <c:when test="${ param.paramName eq 'sysLog' }">
									<li>
										<a href="#" style="color:#D58512"><i class="fa fa-edit"></i> 系统日志</a>
									</li>
								</c:when>
								<c:otherwise>
									<li>
										<a href="#"><i class="fa fa-edit"></i> 系统日志</a>
									</li>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
		        </li>
	        </c:when>
	        <c:otherwise>
	        	<li><a href="#systemSetting" id="collapseListGroupHeading2"
					class="nav-header collapsed" data-toggle="collapse"> <i
						class="glyphicon glyphicon-list"></i> 系统管理 <span
						class="pull-right glyphicon glyphicon-chevron-down"></span> </a>
					<div id="systemSetting" class="panel-collapse collapse"
						role="tabpanel" aria-labelledby="collapseListGroupHeading2">
						<ul class="nav nav-pills nav-stacked secondmenu">
							<li><a href="admin/adminUser?method=userList"><i class="fa fa-user"></i>
									用户管理</a></li>
							<li><a href="#"><i class="fa fa-edit"></i> 系统日志</a></li>
						</ul>
					</div>
		        </li>
	        </c:otherwise>
        </c:choose>
	</ul>
</div>