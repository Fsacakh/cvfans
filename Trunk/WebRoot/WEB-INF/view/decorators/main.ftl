<!--
	页面模板
-->
<html lang="en">
  <head>
  	<title>卡车之友-革新卡车价值链，重建多赢生态圈</title>
	<meta name="keywords" content="">
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	
	<!-- BEGIN GLOBAL MANDATORY STYLES -->
	<link href="${base}/media/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
	<link href="${base}/media/css/bootstrap.css" rel="stylesheet" type="text/css">
	
	<link href="${base}/media/css/bootstrap-toggle-buttons.css" rel="stylesheet" type="text/css"/>
	<link href="${base}/media/css/bootstrap-tag.css" rel="stylesheet" type="text/css"/>
	<link href="${base}/media/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href="${base}/media/css/style-metro.css" rel="stylesheet" type="text/css">
	<link href="${base}/media/css/style.css" rel="stylesheet" type="text/css">
	<link href="${base}/media/css/default.css" rel="stylesheet" type="text/css" id="style_color">
	<link href="${base}/media/css/uniform.default.css" rel="stylesheet" type="text/css">
	<link href="${base}/media/css/jquery.wysiwyg.css" rel="stylesheet" type="text/css">

	<link href="${base}/media/css/main.css" rel="stylesheet" type="text/css">
	<!-- END GLOBAL MANDATORY STYLES -->

	<link href="${base}/media/image/favicon.ico" rel="shortcut icon">
    
    ${head}
    <script type="text/javascript" src="${base}/media/js/base/json2.js"></script>
    <script type="text/javascript" src="${base}/media/js/plugin/jquery-1.10.1.min.js"></script>
    <script type="text/javascript" src="${base}/media/js/plugin/bootstrap.js"></script>
    <script type="text/javascript" src="${base}/media/js/plugin/jquery.dataTables.min.js"></script>
    
    <script type="text/javascript">
		var base = '${base}';
	</script>
  </head>
  
  <body>
  	<div id="header" class="header  center-block"><#include "./header.ftl"></div>
  	
  	<div id="container" class="container" style="padding-left:1px;padding-right:1px">
  		<div class="col-md-12" style="padding-top:1px; padding-bottom:10px;">
  			${body}
  		</div>
  	</div>
  	
  	<!-- 页脚部分 -->
  	<div id="footer" data-role="footer">
  		<#include "./footer.ftl">
  	</div>
  	
  	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
 	<!--[if lt IE 9]>
	<script src="${base}/media/js/plugin/excanvas.min.js"></script>
	<script src="${base}/media/js/plugin/respond.min.js"></script>  
	<![endif]-->  
	
	<script type="text/javascript" src="${base}/media/js/plugin/jquery-ajaxfileupload.js"></script>
 	<script type="text/javascript" src="${base}/media/js/plugin/jquery.validate.min.js"></script>
 	<script type="text/javascript" src="${base}/media/js/plugin/bootstrap-rtl.min.js"></script>
 	<script type="text/javascript" src="${base}/media/js/plugin/bootstrap-tag.js"></script>
 	<script type="text/javascript" src="${base}/media/js/plugin/bootstrap-wysihtml5.js"></script>
 	<script type="text/javascript" src="${base}/media/js/plugin/bootstrap-bootbox.min.js"></script>
 	<script type="text/javascript" src="${base}/media/js/plugin/jquery.validate.min.js"></script>
 	<script type="text/javascript" src="${base}/media/js/plugin/jquery.uniform.min.js"></script>
 	<script type="text/javascript" src="${base}/media/js/plugin/jquery.toggle.buttons.js"></script>
 	<script type="text/javascript" src="${base}/media/js/plugin/jquery.cookie.min.js"></script>
 	<script type="text/javascript" src="${base}/media/js/plugin/jquery.raty.js"></script>
	<script type="text/javascript" src="${base}/media/WdatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${base}/media/js/plugin/jquery.wysiwyg.js"></script>
	
	<script type="text/javascript" src="${base}/media/js/plugin/wysiwyg.image.js"></script>
	<script type="text/javascript" src="${base}/media/js/plugin/wysiwyg.link.js"></script>
	<script type="text/javascript" src="${base}/media/js/plugin/wysiwyg.table.js"></script>
	<script type="text/javascript" src="${base}/media/js/plugin/wysiwyg.cssWrap.js"></script>
	<script type="text/javascript" src="${base}/media/js/plugin/wysiwyg.fileManager.js"></script>
	
	<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=7693a47bf0f82382d52372af5011da0b"></script>
	<script type="text/javascript" src="${base}/media/js/plugin/map.js"></script>
 	
 	<!-- END CORE PLUGINS -->
 	
 	<!-- BEGIN APP PLUGIN-->
 	<script type="text/javascript" src="${base}/media/js/plugin/jquery-app-form-1.0.0.js"></script>
 	<script type="text/javascript" src="${base}/media/js/plugin/jquery-app-utils-1.0.0.js"></script>
 	<script type="text/javascript" src="${base}/media/js/plugin/jquery-app-dataTables-1.0.0.js"></script>
 	<script type="text/javascript" src="${base}/media/js/plugin/jquery-app-validate-1.0.0.js"></script>
 	<script type="text/javascript" src="${base}/media/js/plugin/jquery-app-page-1.0.0.js"></script>
 	<script type="text/javascript" src="${base}/media/js/plugin/jquery-app-dropdown-1.0.0.js"></script>
 	<script type="text/javascript" src="${base}/media/js/plugin/jquery-app-cascade-1.0.0.js"></script>
 	<!-- END APP PLUGIN-->
 	
 	<!-- BEGIN UTILS SCRIPT -->
 	<script type="text/javascript" src="${base}/media/js/base/String.js"></script>
 	<script type="text/javascript" src="${base}/media/js/base/Number.js"></script>
 	<script type="text/javascript" src="${base}/media/js/base/Date.js"></script>
 	<script type="text/javascript" src="${base}/media/js/base/Default.js"></script>
 	<script type="text/javascript" src="${base}/media/js/base/app.js"></script>
 	<!-- END UTILS SCRIPT -->
 	
 	<!-- BEGIN MODULE SCRIPT -->
 	<script type="text/javascript" src="${base}/media/js/app/admin/Auto.js"></script>
 	<script type="text/javascript" src="${base}/media/js/app/admin/Driver.js"></script>
 	<script type="text/javascript" src="${base}/media/js/app/admin/Enterprise.js"></script>
 	<script type="text/javascript" src="${base}/media/js/app/admin/Station.js"></script>
 	<script type="text/javascript" src="${base}/media/js/app/admin/Register.js"></script>
 	<script type="text/javascript" src="${base}/media/js/app/admin/Others.js"></script>
 	<!-- END UTILS SCRIPT -->
 	
 	<script>
		jQuery(document).ready(function() {
		   	App.init();
		});
	</script>

  </body>
</html>	