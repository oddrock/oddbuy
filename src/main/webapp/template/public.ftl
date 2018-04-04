<!DOCTYPE html>
<html>
<#include "/include/head.ftl">
<body>
<#include "/include/support.ftl">
<#include "/include/header.ftl">
<div class="g-doc">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>内容发布</h2>
    </div>
    <div class="n-public">
        <form class="m-form m-form-ht" id="form" method="post" action="${myBasePath}/publicSubmit" autocomplete="off"  enctype="multipart/form-data">
            <div class="fmitem">
                <label class="fmlab">标题：</label>
                <div class="fmipt">
					<#if product?? && product.title??>
						<input class="u-ipt ipt" name="title" autofocus placeholder="2-80字符" value="${product.title}"/>
					<#else>
						<input class="u-ipt ipt" name="title" autofocus placeholder="2-80字符"/>
					</#if>
					<#if errors?? && errors.getFieldError("title")??><br/><font color="red">${errors.getFieldError("title").defaultMessage}</font><br/></#if>
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">摘要：</label>
                <div class="fmipt">
					<#if product?? && product.summary??>
						<input class="u-ipt ipt" name="summary" placeholder="2-140字符" value="${product.summary}"/>
					<#else>
						<input class="u-ipt ipt" name="summary" placeholder="2-140字符"/>
					</#if>
					<#if errors?? && errors.getFieldError("summary")??><br/><font color="red">${errors.getFieldError("summary").defaultMessage }</font><br/></#if>
                </div>
            </div>
            <div class="fmitem">
            <label class="fmlab">图片：</label>
            	<div class="fmipt" id="uploadType">
	                <input name="pic" type="radio" value="url" checked /> 图片地址
					<input name="pic" type="radio" value="file" /> 本地上传
				</div>	
            </div>
            <div class="fmitem">
                <label class="fmlab"></label>
                <div class="fmipt" id="urlUpload">
					<#if product?? && product.image??>
						<input class="u-ipt ipt"  name="image" placeholder="图片地址" value="${product.image}"/>
					<#else>
						<input class="u-ipt ipt"  name="image" placeholder="图片地址"/>
					</#if>
					<#if errors?? && errors.getFieldError("image")??><br/><font color="red">${errors.getFieldError("image").defaultMessage }</font><br/></#if>
                </div>
                <div class="fmipt" id="fileUpload"  style="display:none">
                    <input class="u-ipt ipt" name="file" type="file" id="fileUp"/>
					<!--
                    <button class="u-btn u-btn-primary" type="button" id="upload">上传</button>
					-->
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">正文：</label>
                <div class="fmipt">
					<#if product?? && product.detail??>
						<textarea class="u-ipt" name="detail" rows="10" placeholder="2-1000个字符">${product.detail}</textarea>
					<#else>
						<textarea class="u-ipt" name="detail" rows="10" placeholder="2-1000个字符"></textarea>
					</#if> 
					<#if errors?? && errors.getFieldError("detail")??><br/><font color="red">${errors.getFieldError("detail").defaultMessage }</font><br/></#if>
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">价格：</label>
                <div class="fmipt">
					<#if product?? && product.price??>
						<input class="u-ipt price" name="price" value="${product.price}" />元
					<#else>
						 <input class="u-ipt price" name="price"/>元 
					</#if>
					<#if errors?? && errors.getFieldError("price")??><br/><font color="red">${errors.getFieldError("price").defaultMessage }</font><br/></#if>
                </div>
            </div>
            <div class="fmitem fmitem-nolab fmitem-btn">
                <div class="fmipt">
                    <button type="submit" class="u-btn u-btn-primary u-btn-lg">发 布</button>
                </div>
            </div>
        </form>
        <span class="imgpre"><img src="" alt="" id="imgpre"></span>
    </div>
</div>
<#include "/include/footer.ftl">
<script type="text/javascript" src="${myBasePath}/js/global.js"></script>
<script type="text/javascript" src="${myBasePath}/js/public.js"></script>
</body>
</html>