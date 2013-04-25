<#setting number_format="############.###">
<#setting date_format="yyyy-MM-dd">
<#setting datetime_format="yyyy-MM-dd HH:mm:ss">


<#function getTime time="">
    <#if time?is_date>
        <#return time?datetime>
    <#else>
        <#return time>
    </#if>
</#function>

<#function getDate time="">
    <#if time?is_date>
        <#return time?date>
    <#else>
        <#return time>
    </#if>
</#function>

<#macro pageFrame highlight="index">
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${ctx!}/css/style.css"/>
    <script type="text/javascript" src="${ctx!}/js/jquery.js"></script>
    <script type="text/javascript">
        $(function () {
            $('#navigation li').each(function (index) {
                $(this).removeClass("selected");
            });
            $('#${highlight}').addClass("selected");
        })
    </script>
    <title>${highlight}</title>
</head>
<body>
    <div id="header">
        <div>
            <div id="logo">
                <a href="index.html"><img src="${ctx!}/images/logo.png" alt="LOGO"></a>
            </div>
            <ul id="navigation">
                <li id="index" class="selected">
                    <a href="${ctx!}/index.html">首页</a>
                </li>
                <li id="approve">
                    <a href="${ctx!}/article/approveArticle.htm">审核</a>
                </li>
                <li id="contribute">
                    <a href="${ctx!}/article/toAddArticle.htm">发表</a>
                </li>
            </ul>
            <div style="float: right;padding: 15px;">
                <#if (user.userName)?? && (user.userName)!=''>
                    <a href="#">${user.userName}</a>
                    <a style="margin-left: 10px;" href="${ctx!}/userInfo/loginOut.htm">退出</a>
                <#else>
                    <a href="${ctx!}/userInfo/toLogin.htm" >登录</a>
                    <a style="margin-left: 10px;" href="${ctx!}/userInfo/toRegister.htm" >注册</a>
                </#if>
            </div>
        </div>
    </div>
    <div id="contents">
        <div id="blog">
            <#nested>
        </div>
    </div>
    <div id="footer">
        <div class="footer">
            <p>
                &copy; Copyright &copy; 2013.Company name All rights reserved.
            </p>
        </div>
    </div>
</body>
</html>

</#macro>
