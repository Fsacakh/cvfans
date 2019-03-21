<!--
	页面模板
-->
<html lang="en">
  <head>
  	<title>首页-卡车之友</title>
	<meta name="keywords" content="">
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	
	<!-- BEGIN GLOBAL MANDATORY STYLES -->
	<link href="${base}/media/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
	<link href="${base}/media/css/bootstrap.css" rel="stylesheet" type="text/css">
	
	<link href="${base}/media/css/bootstrap-toggle-buttons.css" rel="stylesheet" type="text/css"/>
	<link href="${base}/media/css/bootstrap-tag.css" rel="stylesheet" type="text/css"/>
	<link href="${base}/media/css/bootstrap-fileupload.css" rel="stylesheet" type="text/css"/>
	<link href="${base}/media/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href="${base}/media/css/style-metro.css" rel="stylesheet" type="text/css">
	<link href="${base}/media/css/style.css" rel="stylesheet" type="text/css">
	<link href="${base}/media/css/style-responsive.css" rel="stylesheet" type="text/css">
	<link href="${base}/media/css/default.css" rel="stylesheet" type="text/css" id="style_color">
	<link href="${base}/media/css/uniform.default.css" rel="stylesheet" type="text/css">
	<link href="${base}/media/css/datepicker.css" rel="stylesheet" type="text/css"  />


	<link href="${base}/media/css/common.css" rel="stylesheet" type="text/css">
	<link href="${base}/media/css/main.css" rel="stylesheet" type="text/css">
	<!-- END GLOBAL MANDATORY STYLES -->

	<link href="${base}/media/image/favicon.ico" rel="shortcut icon">
    
    ${head}
    
    <script type="text/javascript" src="${base}/media/js/plugin/jquery-1.10.1.min.js"></script>
    <script type="text/javascript" src="${base}/media/js/plugin/bootstrap.js"></script>
    <script type="text/javascript" src="${base}/media/js/plugin/jquery.dataTables.min.js"></script>
  </head>
  
  <body>
  	<div id="container" class="container" style="padding-top:20px; padding-bottom:20px">
		${body}
  	</div>
  	
  	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
 	<!--[if lt IE 9]>
	<script type="text/javascript" src="${base}/media/js/plugin/excanvas.min.js"></script>
	<script type="text/javascript" src="${base}/media/js/plugin/respond.min.js"></script>  
	<![endif]-->  
	<!--[if lte IE 9]>
	<script type="text/javascript" src="${base}/media/js/base/json2.js"></script>
	<![endif]--> 
	
 	<script type="text/javascript" src="${base}/media/js/plugin/jquery.validate.min.js"></script>
 	
 	<script type="text/javascript" src="${base}/media/js/plugin/bootstrap-datepicker.js"></script>
 	<script type="text/javascript" src="${base}/media/js/plugin/bootstrap-rtl.min.js"></script>
 	<script type="text/javascript" src="${base}/media/js/plugin/bootstrap-tag.js"></script>
 	<script type="text/javascript" src="${base}/media/js/plugin/bootstrap-wysihtml5.js"></script>
 	<script type="text/javascript" src="${base}/media/js/plugin/bootstrap-bootbox.min.js"></script>
 	<script type="text/javascript" src="${base}/media/js/plugin/jquery.validate.min.js"></script>
 	<script type="text/javascript" src="${base}/media/js/plugin/jquery.uniform.min.js"></script>
 	<script type="text/javascript" src="${base}/media/js/plugin/jquery.toggle.buttons.js"></script>
 	
 	<!-- END CORE PLUGINS -->
 	
 	<!-- BEGIN UTILS SCRIPT -->
 	<script type="text/javascript" src="${base}/media/js/base/String.js"></script>
 	<script type="text/javascript" src="${base}/media/js/base/Number.js"></script>
 	<script type="text/javascript" src="${base}/media/js/base/Date.js"></script>
	<script type="text/javascript" src="${base}/media/js/base/app.js"></script>
 	<!-- END UTILS SCRIPT -->
 	
 	<!-- BEGIN APP PLUGIN-->
 	<script type="text/javascript" src="${base}/media/js/plugin/jquery-app-form-1.0.0.js"></script>
 	<script type="text/javascript" src="${base}/media/js/plugin/jquery-app-utils-1.0.0.js"></script>
 	<script type="text/javascript" src="${base}/media/js/plugin/jquery-app-dataTables-1.0.0.js"></script>
 	<script type="text/javascript" src="${base}/media/js/plugin/jquery-app-page-1.0.0.js"></script>
 	<script type="text/javascript" src="${base}/media/js/plugin/jquery-app-validate-1.0.0.js"></script>
 	<!-- END APP PLUGIN-->
 	
 	<script>
		jQuery(document).ready(function() {    
		   App.init();
		});
	</script>

  </body>
</html>	