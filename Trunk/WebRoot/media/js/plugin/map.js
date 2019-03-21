var mapObj, toolBar, scale, locationInfo, data,from;
	function intMap(flag,data,about) {
		
		from=flag;
		mapObj = new AMap.Map("gdmap", {
			rotateEnable : true,
			dragEnable : true,
			zoomEnable : true,
			view : new AMap.View2D( {
				// center : postion,// 地图中心点
					zoom : 12
				})
		});
		// 地图中添加地图操作ToolBar插件
		mapObj.plugin( [ "AMap.ToolBar" ], function() {
			toolBar = new AMap.ToolBar();
			mapObj.addControl(toolBar);

		});
		if(data==null||data.posLong==""||data.posLat==""||about=="toIP")
		{
			// 加载IP定位插件
			mapObj.plugin( [ "AMap.CitySearch" ], function() {
				// 实例化城市查询类
				var citysearch = new AMap.CitySearch();
				// 自动获取用户IP，返回当前城市
				citysearch.getLocalCity();
				// citysearch.getCityByIp("192.168.168.*");
				AMap.event.addListener(citysearch, "complete",
						function(result) {
					if (result && result.city && result.bounds) {
						// var cityinfo = result.city;
						var citybounds = result.bounds;
						// 地图显示当前城市
						mapObj.setBounds(citybounds);
					} else {
						alert("您当前所在城市：" + result.info + "");
					}
				});
				AMap.event.addListener(citysearch, "error", function(result) {
					alert(result.info);
				});
			});
		}
		else
		{
			mapObj.view=new AMap.LngLat(data.posLong,data.posLat);//地图中心点
		}

		// 加载比例尺插件
		mapObj.plugin( [ "AMap.Scale" ], function() {
			scale = new AMap.Scale();
			mapObj.addControl(scale);
		});
	}
	
	function rangeStation(data) {
		var rangeDate = {
			statementId : "cvfans.queryStationListByRange",
			parameters : {
				posLong : data.posLong,
				posLat : data.posLat,
				limit:10
			}
		};
		
		var $fjStation=$('#fjStation');
		$.Service.invoke( {
			url : "/Query/Station/List.action",
			data : rangeDate,
			success : function(data) {
				$.each(data, function(index, data) {
					var img = '';
					if(data.LOGO_FILE == ''){
						img="images/station/default_medium.png";
					}else{
						img = $.ImageUtil.get(data.LOGO_FILE,'middle');
					}
					
					if(from=="frontend" && index < 4)
					{
						$fjStation.append("<li style='width:25%; border:none; text-align:center' data-id="+data.STATION_ID+"><a href='" + $.URL.get('/station/StationDetail.html?stationId=' + data.STATION_ID ) +"'><img src='"+ $.URL.get(img) + "'/><br/>" +data.NAME+"</a></li>");

					}
					else if(from=="admin")
					{
						$fjStation.append("<li style='whidth:25%'  data-id="+data.STATION_ID+"><a href='/auto/admin/station/details.html?stationId="+data.STATION_ID+"'>"+data.NAME+"</a></li>");
					}
					
					addMarker(data, 1);
				});
			}
		});
		
		if(data!=null
				&&data!="null"
				&&data!=""
				&&data!=undefined
				&&data.posLat!=null
				&&data.posLat!=undefined
				&&data.posLat!="null"
				&&data.posLat!=''
				&&data.posLong!=null
				&&data.posLong!=undefined
				&&data.posLong!="null"
				&&data.posLong!='')
		{
			addMarker(data, 0);
		}
	}
	// 添加marker标记
	function addMarker(data, flag) {
		var marker, content, postion, infoWindow,content;
		var img = '';
		
		
		if (flag == 0) {
			if(data.logoFile == '')
			{
				img="images/station/default_small.png";
			}else{
				img = $.ImageUtil.get(data.logoFile,'small');
			}
			
			postion = new AMap.LngLat(data.posLong, data.posLat);
			if(from=="frontend")
			{
				content="<img src='"+ $.URL.get(img) + "' style='position:relative;float:left;margin:0 5px 5px 0;'>地址："+data.address+"<br/>电话："+data.contactTel+"<br/><div style='text-align: center;'><a href='/auto/station/StationDetail.html?stationId="+data.stationId+"'>详细信息</a></div>";
			}
			else if(from=="admin")
			{
				content="<img src='"+ $.URL.get(img) + "' style='position:relative;float:left;margin:0 5px 5px 0;'>地址："+data.address+"<br/>电话："+data.contactTel+"<br/><div style='text-align: center;'></div>";
			}
			var infoWindow = new AMap.InfoWindow({
				isCustom:true,  // 使用自定义窗体
				showShadow : true,
				autoMove : true,
				offset : new AMap.Pixel(15, -40),
				content:createInfoWindow('<span><strong>&nbsp'+data.name+'</span></strong>',content)
			});
		} else {
			postion = new AMap.LngLat(data.POS_LONG, data.POS_LAT);
			if(data.LOGO_FILE == '')
			{
				img="images/station/default_small.png";
			}else{
				img = $.ImageUtil.get(data.LOGO_FILE,'small');
			}
			if(from=="frontend")
			{
				content="<img src='"+ $.URL.get(img) + "' style='position:relative;float:left;margin:0 5px 5px 0;'>地址："+data.ADDRESS+"<br/>电话："+data.CONTACT_TEL+"<br/><div style='text-align: center;'><a href='/auto/station/StationDetail.html?stationId="+data.STATION_ID+"'>详细信息</a></div>";
			}
			else if(from=="admin")
			{
				content="<img src='"+ $.URL.get(img) + "' style='position:relative;float:left;margin:0 5px 5px 0;'>地址："+data.ADDRESS+"<br/>电话："+data.CONTACT_TEL+"<br/><div style='text-align: center;'></div>";
			}
//			else
//			{
//				content="<img src='"+ $.URL.get(img) + "' style='position:relative;float:left;margin:0 5px 5px 0;'>地址："+data.ADDRESS+"<br/>电话："+data.CONTACT_TEL+"<br/><div style='text-align: center;'><a href='/auto/station/details.html?stationId="+data.STATION_ID+"'>详细信息</a></div>";
//			}
			var infoWindow = new AMap.InfoWindow({
				isCustom:true,  // 使用自定义窗体
				showShadow : true,
				autoMove : true,
				offset : new AMap.Pixel(15, -40),
				content:createInfoWindow('<span><strong>&nbsp'+data.NAME+'</span></strong>',content)
			});
		}
		marker = new AMap.Marker( {
			map : mapObj,
			position : postion, // 位置
			icon : "http://webapi.amap.com/images/0.png" // 复杂图标
		});
		if(flag==0)
		{
			marker.setTitle(data.name);
		}
		else
		{
			marker.setTitle(data.NAME);
		}
		AMap.event.addListener(marker, 'click', function() { // 鼠标点击marker弹出自定义的信息窗体
			infoWindow.open(mapObj,marker.getPosition());
	    });
		if (flag == 0) {
			infoWindow.open(mapObj,marker.getPosition());
		}
	}
	// 构建自定义信息窗体
	function createInfoWindow(title,content){var info = document.createElement("div");
		info.className = "info";
	
		// 可以通过下面的方式修改自定义窗体的宽高
		// info.style.width = "400px";
	
		// 定义顶部标题
		var top = document.createElement("div");
		top.className = "info-top";
		var titleD = document.createElement("div");
		titleD.innerHTML = title;
		var img=document.createElement("span");
		img.style.float="right";
		img.style.cursor="default";
		img.style.paddingTop="5px";
		img.style.paddingRight="2px";
		var closeX = document.createElement("img");
		closeX.src = "http://webapi.amap.com/images/close2.gif";
		closeX.onclick = closeInfoWindow;
		  
		top.appendChild(titleD);
		titleD.appendChild(img);
		img.appendChild(closeX);
		info.appendChild(top);
		
	    
		// 定义中部内容
		var middle = document.createElement("div");
		middle.className = "info-middle";
		middle.style.backgroundColor='white';
		middle.innerHTML = content;
		info.appendChild(middle);
		
//		// 定义底部内容
		var bottom = document.createElement("div");
		bottom.className = "info-bottom";
		bottom.style.position = 'relative';
		bottom.style.top = '0px';
		bottom.style.margin = '0 auto';
		var sharp = document.createElement("img");
		sharp.src = "http://webapi.amap.com/images/sharp.png";
		bottom.appendChild(sharp);	
		info.appendChild(bottom);
	    return info;
	}
	// 关闭信息窗体
	function closeInfoWindow(){
		mapObj.clearInfoWindow();
	}