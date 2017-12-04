<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<title>AppleCare服务系统 :</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
    <title>后台管理系统</title>
    <link type="text/css" href="${ctx }/static/Stylesw/admin-all.css"  rel="stylesheet" />
    <link type="text/css" href="${ctx }/static/Stylesw/base.css"  rel="stylesheet" />
    <link  type="text/css" href="${ctx }/static/Stylesw/bootstrap.min.css"  rel="stylesheet" />
    <link type="text/css" href="${ctx }/static/Stylesw/ui-lightness/jquery-ui-1.8.22.custom.css"  rel="stylesheet" />
    <script type="text/javascript" src="${ctx }/static/Scripts/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctx }/static/Scripts/jquery-ui-1.8.22.custom.min.js"></script>
    <script type="text/javascript" src="${ctx }/static/Scripts/index.js"></script>
</head>
<body>
    <div class="warp">
        <!--头部开始-->
        <div class="top_c">
            <div class="top-menu">
                <ul class="top-menu-nav">
                    <li><a href="#">首页</a></li>
                    <shiro:hasRole name="admin">
                    <li><a href="#">查询界面<i class="tip-up"></i></a>
                        <ul class="kidc">
                            <li><a target="Conframe" href="${ctx}/admin/user">Admin Users</a></li>
                            <li><a target="Conframe" href="${ctx}/register">添加用户</a></li>
                            <li><a target="Conframe" href="${ctx}/zhekou">折扣管理</a></li>
                            <li><a target="Conframe" href="${ctx}/shangpin">商品管理</a></li>
                            <li><a target="Conframe" href="${ctx}/jinhuo">进货查询</a></li>
                            <li><a target="Conframe" href="${ctx}/zhongtongji">利润查询</a></li>
                            <li><a target="Conframe" href="${ctx}/chushou/list">销售查询</a></li>
                            <li><a target="Conframe" href="${ctx}/type">类型管理</a></li>
                            <li><a target="Conframe" href="${ctx}/fengcheng">分成设置</a></li>
                        </ul>
                    </li>
                    </shiro:hasRole>
                    <li><a href="#">服务商品<i class="tip-up"></i></a>
                        <ul class="kidc">
                        <li><b class="tip"></b><a target="Conframe" href="${ctx}/wx/list">售后服务</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="${ctx}/chushou">商品出售</a></li>
                        <li><b class="tip"></b><a target="Conframe" href="${ctx}/yeji">销售查询</a></li>                        
   						<li><b class="tip"></b><a target="Conframe" href="${ctx}/wx/sylist">查询服务</a></li>  
                    </ul>
                    </li>
                    
                </ul>
            </div>
            <div class="top-nav">欢迎您， <shiro:principal property="name"/>！&nbsp;&nbsp;<a href="">修改密码</a> | <a href="${ctx}/logout">安全退出</a></div>
        </div>
        <!--头部结束-->
        <!--左边菜单开始-->
        <div class="left_c left">
            <h1>系统操作菜单</h1>
            <div class="acc">
                <div>
                    <a class="one">新建服务</a>
                    <ul class="kid">
                        <li><b class="tip"></b><a target="Conframe" href="${ctx}/wx/save">新建服务</a></li>
                        
                    	
                    </ul>
                </div>
                <div>
                    <a class="one">查询服务</a>
                    <ul class="kid">
                        <li><b class="tip"></b><a target="Conframe" href="${ctx}/wx/sylist">查询服务</a></li>
                    
                    </ul>
                </div>
                <div>
                    <a class="one">所有服务</a>
                    <ul class="kid">
                        <li><b class="tip"></b><a target="Conframe" href="${ctx}/wx/list">所有服务</a></li>
                      
                    </ul>
                </div>
               
                
                <div id="datepicker"></div>
            </div>

        </div>
        <!--左边菜单结束-->
        <!--右边框架开始-->
        <div class="right_c">
            <div class="nav-tip" onclick="javascript:void(0)">&nbsp;</div>

        </div>
        <div class="Conframe">
            <iframe name="Conframe" id="Conframe"></iframe>
        </div>
        <!--右边框架结束-->

        <!--底部开始-->
        <div class="bottom_c">Copyright &copy;2005-2011</div>
        <!--底部结束-->
    </div>
</body>
</html>
