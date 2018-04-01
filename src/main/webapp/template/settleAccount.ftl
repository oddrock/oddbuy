<!DOCTYPE html>
<html>
<#include "/include/head.ftl">
<body>
<#include "/include/support.ftl">
<#include "/include/header.ftl">
<div class="g-doc" id="settleAccount">
    <div class="m-tab m-tab-fw m-tab-simple f-cb" >
        <h2>已添加到购物车的内容</h2>
    </div>
 	<table id="newTable" class="m-table m-table-row n-table g-b3">
	<tr>
		<td>标题</td><td>价格</td><td>数量</td>
	</tr>
	<#if cartProductList??>
		<#list cartProductList as product>
		<tr><td>${product.title}</td><td>${product.price}</td><td>${product.buyNum}</td></tr>
		</#list>
	</#if>
 	</table>
 	<div id="act-btn"><button class="u-btn u-btn-primary" id="back">退出</button>
 	<button class="u-btn u-btn-primary" id="Account">购买</button></div>
</div>
<#include "/include/footer.ftl">
<script type="text/javascript" src="/js/global.js"></script>
<script type="text/javascript" src="/js/settleAccount.js"></script>
</body>
</html>