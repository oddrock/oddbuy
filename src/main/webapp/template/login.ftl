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
            <input type="submit" id="submit" value='提交' />
        </div>
    </div>
	
	
<input type="hidden" name="type" value="0"/>
</form>

<#if errorTip?? >
	<div align="center"><p>${errorTip}</p></div>
</#if>
	
<#include "/include/footer.ftl">
<script>
function checkForm(){
	var pwd= document.getElementById('password');
	pwd.value=md5(pwd.value);
	document.getElementById('submit').disabled=true;
	return true;
}
</script>
<script type="text/javascript" src="js/md5.js"></script>
<script type="text/javascript" src="js/global.js"></script>
<script type="text/javascript" src="js/pageLogin.js"></script>
</body>
</html>