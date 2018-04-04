<div class="n-head">
    <div class="g-doc f-cb">
        <div class="user">
        <#if user??>
            <#if user.userType==1>卖家<#else>买家</#if>你好，<span class="name">${user.userName}</span>！<a href="${myBasePath}/logout">[退出]</a>	
        </#if>
        </div>
        <ul class="nav">
            <li><a href="${myBasePath}/">首页</a></li>
			<#if !user??>
			<li><a href="${myBasePath}/login">登录</a></li>
			</#if>
            <#if user?? && user.userType==0>
            <li><a href="${myBasePath}/account">账务</a></li>
            <li><a href="${myBasePath}/settleAccount">购物车</a></li>
            </#if>
            <#if user?? && user.userType==1>
            <li><a href="${myBasePath}/public">发布</a></li>
            </#if>
        </ul>
    </div>
</div>