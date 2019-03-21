 <#-- 系统管理员功能菜单 -->
  <input type="hidden" id="userId" value="${Session.User.userId}"/>
  <input type="hidden" id="userName" value="${Session.User.realName}"/>
  <input type="hidden" id="ownerId" value="${Session.User.ownerId}"/>
  <input type="hidden" id="ownerRole" value="${Session.User.ownerRole}"/>
  
 <#if Session.User.ownerRole = 4>   
	<ul class="page-sidebar-menu">
		<li style="height:45px">
		</li>
		
		<li <#if Request.view?ends_with('/sys/StationManagement.html')>class="active"</#if>>
			<a href="${base}/backend/sys/StationManagement.html">
			<span><img src="${requestContext.contextPath}/media/image/kachezhiyou/common/fwznav01.png"></img></span>
			<span class="title">服务站管理</span>
			<span class="selected"></span>
			</a>
		</li>
		
		<li <#if Request.view?ends_with('/sys/EnterpriseManagement.html')>class="active"</#if>>
			<a href="${base}/backend/sys/EnterpriseManagement.html">
			<span><img src="${requestContext.contextPath}/media/image/kachezhiyou/common/fwznav01.png"></img></span>
			<span class="title">车管管理</span>
			<span class="selected"></span>
			</a>
		</li>
		
		<li <#if Request.view?ends_with('/sys/DriverManagement.html')>class="active"</#if>>
			<a href="${base}/backend/sys/DriverManagement.html">
			<span><img src="${requestContext.contextPath}/media/image/kachezhiyou/common/fwznav01.png"></img></span>
			<span class="title">司机管理</span>
			<span class="selected"></span>
			</a>
		</li>
		
		<li <#if Request.view?ends_with('/sys/AutoManagement.html')>class="active"</#if>>
			<a href="${base}/backend/sys/AutoManagement.html">
			<span><img src="${requestContext.contextPath}/media/image/kachezhiyou/common/fwznav01.png"></img></span>
			<span class="title">车辆管理</span>
			<span class="selected"></span>
			</a>
		</li>
		
		<li <#if Request.view?ends_with('/sys/ComplainManagement.html')>class="active"</#if>>
			<a href="${base}/backend/sys/ComplainManagement.html">
			<span><img src="${requestContext.contextPath}/media/image/kachezhiyou/common/fwznav01.png"></img></span>
			<span class="title">投诉处理</span>
			<span class="selected"></span>
			</a>
		</li>
	</ul>
</#if>

<#if Session.User.ownerRole = 2>  
	<ul class="page-sidebar-menu">
		<li style="height:45px">
		</li>
		
		<li <#if Request.view?ends_with('/station/StationManagement.html')>class="active"</#if>>
			<a href="${base}/backend/station/StationManagement.html">
			<span><img src="${requestContext.contextPath}/media/image/kachezhiyou/common/fwznav01.png"></img></span>
			<span class="title">服务站管理</span>
			<span class="selected"></span>
			</a>
		</li>
		
		<#if Session.User.owner.parentId?default('0') = '0'>
			<li <#if Request.view?ends_with('/station/SubStationManagement.html')>class="active"</#if>>
				<a href="${base}/backend/station/SubStationManagement.html">
				<span><img src="${requestContext.contextPath}/media/image/kachezhiyou/common/fwznav03.png"></img></span>
				<span class="title">二级服务站管理</span>
				<span class="selected"></span>
				</a>
			</li>
		</#if>
		
		<#if Session.User.owner.parentId?default('0') = '0'>
			<li <#if Request.view?ends_with('/station/PushMessageManagement.html')>class="active"</#if>>
				<a href="${base}/backend/station/PushMessageManagement.html">
				<span><img src="${requestContext.contextPath}/media/image/kachezhiyou/common/fwznav03.png"></img></span>
			    <span class="title">消息推送</span>
				<span class="selected"></span>
				</a>
		</#if>
		
		<#if Session.User.owner.parentId?default('0') = '0'>
			<li <#if Request.view?ends_with('/station/CustomerManagement.html')>class="active"</#if>>
				<a href="${base}/backend/station/CustomerManagement.html">
				<span><img src="${requestContext.contextPath}/media/image/kachezhiyou/common/fwznav03.png"></img></span>
				<span class="title">客户管理</span>
				<span class="selected"></span>
				</a>
			</li>
		</#if>
		
		<li <#if Request.view?ends_with('/station/AutoGuaranteeManagement.html')>class="active"</#if>>
			<a href="${base}/backend/station/AutoGuaranteeManagement.html">
			<span><img src="${requestContext.contextPath}/media/image/kachezhiyou/common/fwznav05.png"></img></span>
			<span class="title">车辆担保管理</span>
			<span class="selected"></span>
			</a>
		</li>
		
		<li <#if Request.view?ends_with('/station/StationServiceManagement.html')>class="active"</#if>>
			<a href="${base}/backend/station/StationServiceManagement.html">
			<span><img src="${requestContext.contextPath}/media/image/kachezhiyou/common/fwznav04.png"></img></span>
			<span class="title">车辆维修管理</span>
			<span class="selected"></span>
			</a>
		</li>
		
		<li <#if Request.view?ends_with('/station/PaymentGuaranteeManagement.html')>class="active"</#if>>
			<a href="${base}/backend/station/PaymentGuaranteeManagement.html">
			<span><img src="${requestContext.contextPath}/media/image/kachezhiyou/common/fwznav05.png"></img></span>
			<span class="title">支付担保管理</span>
			<span class="selected"></span>
			</a>
		</li>
		
		<li <#if Request.view?ends_with('/station/PromotionManagement.html')>class="active"</#if>>
			<a href="${base}/backend/station/PromotionManagement.html">
			<span><img src="${requestContext.contextPath}/media/image/kachezhiyou/common/fwznav06.png"></img></span>
			<span class="title">促销信息管理</span>
			<span class="selected"></span>
			</a>
		</li>
		<li <#if Request.view?ends_with('/station/DiscountManagement.html')>class="active"</#if>>
			<a href="${base}/backend/station/DiscountManagement.html">
			<span><img src="${requestContext.contextPath}/media/image/kachezhiyou/common/fwznav06.png"></img></span>
			<span class="title">折扣信息管理</span>
			<span class="selected"></span>
			</a>
		</li>
		<li <#if Request.view?ends_with('/station/StationCommentManagement.html')>class="active"</#if>>
			<a href="${base}/backend/station/StationCommentManagement.html">
			<span><img src="${requestContext.contextPath}/media/image/kachezhiyou/common/fwznav07.png"></img></span>
			<span class="title">用户评价管理</span>
			<span class="selected"></span>
			</a>
		</li>
		<li <#if Request.view?ends_with('/station/SearchStation.html')>class="active"</#if>>
			<a href="${base}/backend/station/SearchStation.html">
			<span><img src="${requestContext.contextPath}/media/image/kachezhiyou/common/fwznav08.png"></img></span>
			<span class="title">服务站搜索</span>
			<span class="selected"></span>
			</a>
		</li>
	</ul>
</#if>


<#if Session.User.ownerRole = 3>   
	<ul class="page-sidebar-menu">
		<li style="height:45px">
		</li>
		
		<li <#if Request.view?ends_with('/enterprise/EnterpriseManagement.html')>class="active"</#if>>
			<a href="${base}/backend/enterprise/EnterpriseManagement.html">
			<span><img src="${requestContext.contextPath}/media/image/kachezhiyou/common/fwznav01.png"></img></span>
			<span class="title">车管信息管理</span>
			<span class="selected"></span>
			</a>
		</li>
		<li  <#if Request.view?ends_with('/enterprise/AutoManagement.html')>class="active"</#if>>
			<a href="${base}/backend/enterprise/AutoManagement.html">
			<span><img src="${requestContext.contextPath}/media/image/kachezhiyou/common/fwznav01.png"></img></span>
			<span class="title">车队车辆管理</span>
			<span class="selected"></span>
			</a>
		</li>
		
		<li <#if Request.view?ends_with('/enterprise/EnterpriseServiceManagement.html')>class="active"</#if>>
			<a href="${base}/backend/enterprise/EnterpriseServiceManagement.html">
			<span><img src="${requestContext.contextPath}/media/image/kachezhiyou/common/fwznav01.png"></img></span>
			<span class="title">车辆维修管理</span>
			<span class="selected"></span>
			</a>
		</li>
		
		<li <#if Request.view?ends_with('/enterprise/AutoGuaranteeManagement.html')>class="active"</#if>>
			<a href="${base}/backend/enterprise/AutoGuaranteeManagement.html">
			<span><img src="${requestContext.contextPath}/media/image/kachezhiyou/common/fwznav01.png"></img></span>
			<span class="title">车辆担保管理</span>
			<span class="selected"></span>
			</a>
		</li>
		
		<li <#if Request.view?ends_with('/station/SearchStation.html')>class="active"</#if>>
			<a href="${base}/backend/station/SearchStation.html">
			<span><img src="${requestContext.contextPath}/media/image/kachezhiyou/common/fwznav01.png"></img></span>
			<span class="title">服务站搜索</span>
			<span class="selected"></span>
			</a>
		</li>
	</ul>
</#if>

<#if Session.User.ownerRole = 1>   
	<ul class="page-sidebar-menu">
		<li style="height:45px">
		</li>
		
		<li <#if Request.view?ends_with('/driver/updateLogo.html')>class="active"</#if>>
			<a href="#">
			<i class="icon-home"></i> 
			<span class="title">修改头像</span>
			<span class="selected"></span>
			</a>
		</li>
			
		<li <#if Request.view?ends_with('/driver/details.html')>class="active"</#if>>
			<a href="#">
			<i class="icon-home"></i> 
			<span class="title">个人资料</span>
			<span class="selected"></span>
			</a>
		</li>

		<li <#if Request.view?ends_with('/driver/service.html')>class="active"</#if>>
			<a href="#">
			<i class="icon-home"></i> 
			<span class="title">维修记录</span>
			<span class="selected"></span>
			</a>
		</li>

		
		<li <#if Request.view?ends_with('/driver/service.html')>class="active"</#if>>
			<a href="#">
			<i class="icon-home"></i> 
			<span class="title">积分</span>
			<span class="selected"></span>
			</a>
		</li>

		<li <#if Request.view?ends_with('/updatePassword.html')>class="active"</#if>>
			<a href="#">
			<i class="icon-home"></i> 
			<span class="title">密码安全</span>
			<span class="selected"></span>
			</a>
		</li>
	</ul>
</#if>


