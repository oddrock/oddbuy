<!DOCTYPE html>
<html>
<#include "/include/head.ftl">
<body>
<#include "/include/support.ftl">
<#include "/include/header.ftl">
<div class="g-doc">
    <#if !product??>
    <div class="n-result">
        <h3>内容不存在！</h3>
    </div>
    <#else>
    <div class="n-show f-cb" id="showContent">
        <div class="img"><img src="${product.image}" alt="" ></div>
        <div class="cnt">
            <h2>${product.title}</h2>
            <p class="summary">${product.summary}</p>
            <div class="price">
                <span class="v-unit">¥</span><span class="v-value">${product.price}</span>
            </div>
            <div class="num">购买数量：<span id="plusNum" class="lessNum"><a>-</a></span><span class="totalNum" id="allNum">${product.buyNum}</span><span id="addNum" class="moreNum"><a>+</a></span></div>
            <div class="oprt f-cb">
                <#if user?? && user.userType==0>
                    <#if product.buy>
                    <span class="u-btn u-btn-primary z-dis">已购买</span>
                    <span class="buyprice">当时购买价格：¥${product.buyPrice}</span>
                    <#else>
					 <button class="u-btn u-btn-primary" id="add" data-id="${product.id}" data-title="${product.title}" data-price="${product.price}">加入购物车</button>
                    </#if>
                </#if>
                <#if user?? && user.userType==1>
                <a href="${myBasePath}/edit?id=${product.id}" class="u-btn u-btn-primary">编 辑</a>
                </#if>
            </div>
        </div>
    </div>
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>详细信息</h2>
    </div>
    <div class="n-detail">
        ${product.detail}
    </div>
    </#if>
</div>
<input type="hidden" id="productId" name="productId" value="${product.id}"/>
<input type="hidden" id="productPrice" name="productPrice" value="${product.price}"/>
<input type="hidden" id="productTitle" name="productTitle" value="${product.title}"/>

<#include "/include/footer.ftl">
<script>
	function addCartNum(){
		/*var elem = document.getElementById("cartNum");
		elem.value=parseInt(elem.value)+1;*/
	}
	function subCartNum(){
		/*var elem = document.getElementById("cartNum");
		var num = parseInt(elem.value);
		if(num>0){
			elem.value=num-1;
		}*/
	}
	function addCart(){
		//var id=button.getAttribute('data-id');
		//var price=button.getAttribute('data-price');
		var msg = "您确定要加入购物车吗？\n\n请确认！"; 
		if (confirm(msg)==true){ 
			document.getElementById("showForm").submit();
		}	
			
	}
</script>
<script type="text/javascript" src="${myBasePath}/js/global.js"></script>
<script type="text/javascript" src="${myBasePath}/js/pageShow.js"></script>
</body>
</html>