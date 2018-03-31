<!DOCTYPE html>
<html>
<#include "/include/head.ftl">
<body>
<#include "/include/support.ftl">
<#include "/include/header.ftl">
<form class="m-form m-form-ht n-login" id="loginForm" method="post" action="login" onsubmit="return checkForm()" autocomplete="off">
    <div class="fmitem">
        <label class="fmlab">用户名：</label>
        <div class="fmipt">
            <input class="u-ipt" name="userName" autofocus/>
        </div>
    </div>
    <div class="fmitem">
        <label class="fmlab">密码：</label>
        <div class="fmipt">
            <input class="u-ipt" type="password" id="password" name="password"/>
        </div>
    </div>
    <div class="fmitem fmitem-nolab fmitem-btn">
        <div class="fmipt">
            <input type="submit" value='提交' />
        </div>
    </div>
	<div>买家用户：buyer，密码：reyub，卖家用户：seller，密码：relles</div>
<input type="hidden" name="type" value="0"/>
</form>
<#include "/include/footer.ftl">
function checkForm(){
	var pwd= document.getElementById('password');
	alert(pwd.value);
	alert(md5(pwd.value));
	alert(pwd.value);
	return false;
}
</script>
<script type="text/javascript" src="/js/md5.js"></script>
<script type="text/javascript" src="/js/global.js"></script>
<script type="text/javascript" src="/js/pageLogin.js"></script>
</body>
</html>