<div class="header_top">
	<div class="login_warp">
		<#if Session["User"]? exists>
			<a href="${base}/backend/home.html" style="color:#25a8f2;"><img src="${base}/media/image/user.png"> 我的空间</a> |
        	<a href="${base}/User/Logout.action" style="color:#25a8f2;">注销</a> |
        	<a href="#OpenWindow" rel="leanModal" style="color:#25a8f2;">修改密码</a> 
        <#else>
            <a href="${base}/login.html" style="color:#25a8f2;"><img src="${base}/media/image/user.png"> 登陆</a> | 
        	<a href="${base}/register.html" style="color:#25a8f2;">免费注册</a> |
        </#if>
    </div>            
</div>
<div class="header_main">
	<div class="container">
		<div class="header_wrap row">
	    	<div class="col-md-2 logo"><a href="#"><img src="${base}/media/image/kachezhiyou/logo.png" title="卡车之友" alt="卡车之友"/></a></div>
	        <div class="theme col-md-4">
	        	<p>革新卡车价值链 重塑多赢生态圈</p>
	        </div>
	        
	        <div class="col-md-6 menu">
		        <ul class="list-inline pull-right">
				  <li <#if Request.view == '/index.html'>class="active"</#if> ><a href="${base}/index.html">首页</a></li>
				  <li <#if Request.view == '/introduction.html'>class="active"</#if>><a href="${base}/introduction.html">联盟简介</a></li>
				  <li <#if Request.view == '/station/StationSearch.html'>class="active"</#if>><a href="${base}/station/StationSearch.html">服务站</a></li>
				  <li <#if Request.view == '/service/index.html'>class="active"</#if>><a href="${base}/service/index.html">维修培训</a></li>
		          <li><a href="#">专家答疑</a></li>	
				</ul>
			</div>
	    </div>
	 </div>
</div>   