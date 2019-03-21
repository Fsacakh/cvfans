$(document).ready(
		function() {
			initPageData();
			function initPageData() {
				var datas = {
					entity : "Station",
					parameters : {
						stationId : $('#stationId').val()
					}
				};
				
				var discount = {
					statementId:"cvfans.queryStationDiscountInfos_f",
					parameters:{
						stationId : $('#stationId').val()
					}
				};
				var promotion = {
					statementId:"cvfans.queryStationPromotionInfos_f",
					parameters : {
						stationId : $('#stationId').val()
					}
				};
				$.Service.invoke( {
					url : "/Query/Station/Load.action",
					data : datas,
					success : function(data) {
						$('#name').append(data.name);
						$('#contactTel').append(data.contactTel);
						$('#address').append(data.address);
						$('#generalScore').raty({score:getScore(data.generalScore), readOnly:  true});
						$('#qualityScore').raty({score:getScore(data.qualityScore), readOnly:  true});
						$('#timeScore').raty({score:getScore(data.timeScore), readOnly:  true});
						$('#priceScore').raty({score:getScore(data.priceScore), readOnly:  true});
					    intMap();
					    rangeStation(data);
					}
				});

				$.Service.invoke( {
					url : "/Query/Discount/List.action",
					data : discount,
					success : function(data) {
						$.each(data, function(index, data) {
							$('#discount').append(
									"<li><a href='#'>" + data.BRAND
											+ "</a></li>");
						});
					}
				});

				$.Service.invoke( {
					url : "/Query/Promotion/List.action",
					data : promotion,
					success : function(data) {
						$.each(data, function(index, data) {
							$('#promotion').append(
									"<li><a href='#'>" + data.TITLE
											+ "</a></li>");
						});
					}
				});
			}
			
			var mapObj, toolBar, scale, locationInfo, data;
			function intMap() {
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
						posLong : mapObj.getCenter().lng,
						posLat : mapObj.getCenter().lat,
					}
				};

				$.Service.invoke( {
					url : "/Query/Station/List.action",
					data : rangeDate,
					success : function(data) {
						$.each(data, function(index, data) {
							$('#fjStation').append("<li data-id="+data.STATION_ID+"><a href='/auto/station/details.html?stationId="+data.STATION_ID+"'>"+data.NAME+"</a></li>");
							addMarker(data, 1)
						});
					}
				});
				if(data!=null)
				{
					addMarker(data, 0);
				}
			}
			// 添加marker标记
			function addMarker(data, flag) {
				var marker, content, postion, infoWindow;
				if (flag == 0) {
					postion = new AMap.LngLat(data.posLong, data.posLat);

					var infoWindow = new AMap.InfoWindow({
						isCustom:true,  // 使用自定义窗体
						showShadow : true,
						autoMove : true,
						offset : new AMap.Pixel(15, -40),
						content:createInfoWindow('<span><strong>'+data.name+'</span></strong>',
						"<img src='http://tpc.googlesyndication.com/simgad/5843493769827749134' style='position:relative;float:left;margin:0 5px 5px 0;'>地址："+data.address+"<br/>电话："+data.contactTel+"<br/><div style='text-align: center;'><a href='/auto/station/details.html?stationId="+data.stationId+"'>详细信息</a></div>"),
					});
				} else {
					postion = new AMap.LngLat(data.POS_LONG, data.POS_LAT);
					var infoWindow = new AMap.InfoWindow({
						isCustom:true,  // 使用自定义窗体
						showShadow : true,
						autoMove : true,
						offset : new AMap.Pixel(15, -40),
						content:createInfoWindow('<span><strong>'+data.NAME+'</span></strong>',
						"<img src='http://tpc.googlesyndication.com/simgad/5843493769827749134' style='position:relative;float:left;margin:0 5px 5px 0;'>地址："+data.ADDRESS+"<br/>电话："+data.CONTAC_TEL+"<br/><div style='text-align: center;'><a href='/auto/station/details.html?stationId="+data.STATION_ID+"'>详细信息</a></div>"),
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

			$('#Page_SearchStaion #search').on('click',function() {
						var datas = {
							statementId : "cvfans.queryStationInfos",
							parameters : {}
						};
						var name=$("#name").val();
						if ($.trim(name)!= ""
								&& $.trim(name)!= "null"
								&& $.trim(name) != null
								&& $.trim(name) != undefined) {
							datas.parameters.name = $.trim(name);
						}
						if ($("#isMember").is(":checked")) {
							datas.parameters.isMember ="1";
						}

						if (JSON.stringify(datas.parameters) == "{}") {
							initPageData();
						} else {
							mapObj.clearMap();
							$.Service.invoke( {
								url : "/Query/Station/List.action",
								data : datas,
								success : function(data) {
									$.each(data, function(index, data) {
										addMarker(data, 2)
									});
								}
							});
						}
					});
			
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
				
				// 定义底部内容
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
		});