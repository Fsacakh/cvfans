	function initPageData_Home()
	{
		var datas={
			statementId:"cvfans.queryStationPromotionInfos_f"
		};
		
		var stations=
		{
			statementId:"cvfans.queryStationInfos_10"
		};
		var p=""; 
		var $station=  $('#station');
		$.Service.invoke(
		{
			url:"/Query/Station/List.action",
			data:stations,
			success: function (data){
			$.each(data,function(index,data){
				 p="";
				 if(data.PROVINCE_NAME!=null&&data.PROVINCE_NAME!=""&&data.PROVINCE_NAME!=undefined&&data.PROVINCE_NAME!="null")
				 {
					 if(data.PROVINCE_NAME=="上海"||data.PROVINCE_NAME=="重庆"||data.PROVINCE_NAME=="北京"||data.PROVINCE_NAME=="天津")
					 {
						 p=data.PROVINCE_NAME;
					 }
					 else
					 {
						 p=date.PROVINCE_NAME+"省";
						 if(data.CITY_NAME!=null&&data.CITY_NAME!=""&&data.CITY_NAME!=undefined&&data.CITY_NAME!="null")
						 {
							 p= p+data.CITY_NAME;
						 }
					 }
				 }
				 if(data.AREA_NAME!=null&&data.AREA_NAME!=""&&data.AREA_NAME!=undefined&&data.AREA_NAME!="null")
				 {
					 p= p+data.AREA_NAME;
				 }
				 if(data.ADDRESS!=""&&data.ADDRESS!="null"&&data.ADDRESS!=undefined)
				 {
					 
						 p=p+data.ADDRESS;
				 }
				 $station.append("<div class='panel' style='padding-bottom: 20px; padding-top: 10px'>" +
	            		"<a href='station/details.html?stationId="+data.STATION_ID+"'><div class='panel-header'>" +
	            		"<h3>" +data.NAME+"</h3>"+
	            		"</div><div class='row panel-body'><div class='col-lg-8'>" +
	            		"<p><img src='"+$.URL.get('media/image/kachezhiyou/common/add.png')+"' />" +
	            		"<span>"+p+"</span>" +
	            		"</p>" +
	            		"<p>" +
	            		"<img src='"+$.URL.get('media/image/kachezhiyou/common/phone02.png')+"' />" +
	            		"<span>"+data.CONTACT_TEL+"</span>" +
	            		"</p>" +
	            		"<p >" +
	            		"<span id='score"+index+"'></span>" +
	            		"</p>" +
	            		"</div>" +
	            		"<div class='col-lg-4'>" +
	            		"<img  src='/auto/media/image/kachezhiyou/ad/displaypic.jpg' style='width: 100%'/>" +
	            		"</div>" +
	            		"</div>" +
	            		"</div></a>"
	            );
	            var id="#score"+index;
	            $(id).raty({score:getScore(data.GENERAL_SCORE), readOnly:  true});
			});
			var $panel=$('.panel:odd');
			$panel.css("background-color","rgb(239, 238, 238)");
  			}
		});
		var $promotionList=$('#promotionList');
		$.Service.invoke(
				{
					url:"/Query/Promotion/List.action",
					data:datas,
					success : function(data) {
						$.each(data, function(index, data) {
							$promotionList.append(
									"<li><a href='#' data-id="+data.PROMOTION_ID + ">"+ data.TITLE + "</a></li>");
						});
					}
		});
		
		var area={entity : "DataInfo",parameters : {
			parentId : "1"
		}};	
		var $area=$('#area');
		$.Service.invoke(
				{
					url:"/Query/DataInfo/List.action",
					data:area,
					success : function(data) {
					$.each(data, function(index, data) {
						$area.append(
								"<option value='"+data.label+"'>"+data.label+"</option>");
					});
				}
		});
		var brand={statementId : "cvfans.queryAutoBrandInfoes"};
		var $brand=$('#brand');
		$.Service.invoke(
				{
					url:"/Query/DataInfo/List.action",
					data:brand,
					success : function(data) {
					$.each(data, function(index, data) {
						$brand.append(
								"<option value='"+data.label+"'>"+data.label+"</option>");
					});
				}
		});
	}
	
	$('#promotionList li a').on('click',function() {
		alert($(this).data('id'));
	});
	
	$('#brand').on('click',function() 
	{
		if($('#brand option:first').val()=="请选择品牌")
		{
			$("#brand option:first").detach();
		}
	});
	
	$('#area').on('click',function() 
	{
		if($('#area option:first').val()=="请选择区域")
		{
			$("#area option:first").detach();
		}
	});
