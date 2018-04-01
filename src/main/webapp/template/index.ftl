<!DOCTYPE html>
<html>
<#include "/include/head.ftl">
<body>
<#include "/include/support.ftl">
<#include "/include/header.ftl">
<#if RequestParameters['type']??>
<#assign listType = RequestParameters['type']>
<#else>
<#assign listType = '0'>
</#if>
<div class="g-doc">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <div class="tab">
            <ul>
                <li <#if !listType?? || listType != '1'>class="z-sel"</#if> ><a href="?type=0">所有内容</a></li>
                <#if user?? && user.userType == 0><li <#if listType == '1'>class="z-sel"</#if> ><a href="?type=1">未购买的内容</a></li></#if>
            </ul>
        </div>
    </div>
    <#if !productList?? || !productList?has_content>
    <div class="n-result">
        <h3>暂无内容！</h3>
    </div>
    <#else>
    <div class="n-plist">
        <ul class="f-cb" id="plist">
        <#if user?? && user.userType == 0 && listType == '1'>
            <#list productList as x>
                <#if !x.buy>
                <li id="p-${x.id}">
                    <a href="show?id=${x.id}" class="link">
                        <div class="img"><img src="${x.image}" alt="${x.title}"></div>
                        <h3>${x.title}</h3>
                        <div class="price"><span class="v-unit">¥</span><span class="v-value">${x.price}</span></div>
                    </a>
                </li>
                </#if>
            </#list>
        <#else>
            <#list productList as x>
                <li id="p-${x.id}">
                    <a href="show?id=${x.id}" class="link">
                        <div class="img"><img src="${x.image}" alt="${x.title}"></div>
                        <h3>${x.title}</h3>
                        <div class="price"><span class="v-unit">¥</span><span class="v-value">${x.price}</span></div>
						<#if user?? && user.userType==1>
						<div class="price">已出售的数量：<span class="v-value">${x.saleNum}</span></div>
						</#if>
                        <#if user?? && user.userType==0 && x.buy><span class="had"><b>已购买</b></span></#if>
                        <#if user?? && user.userType==1 && x.sell><span class="had"><b>已售出</b></span></#if>
						
                    </a>
                    <#if user?? && user.userType==1 && !x.sell> <a href="delete?id=${x.id}" class="link"><span class="u-btn u-btn-normal u-btn-xs del" data-del="${x.id}">删除</span></a></#if>
                </li>
            </#list>
        </#if>
        </ul>
    </div>
    </#if>
</div>
<#include "/include/footer.ftl">
<script type="text/javascript" src="/js/global.js"></script>
<script type="text/javascript" src="/js/pageIndex.js"></script>
</body>
</html>