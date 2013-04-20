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

<#macro pageFrame highlight="hot">
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${ctx!}/css/main.css"/>
    <script type="text/javascript" src="${ctx!}/js/jquery.js"></script>
    <script type="text/javascript">
        $(function () {
            $('a').each(function (index) {
                $(this).removeClass("highlight");
            });
            $('#${highlight}').addClass("highlight");
        })
    </script>
    <title>${highlight}</title>
</head>
<body>
    <div class="header">
        <div style="float: right;padding: 10px 150px 0 0;border-top:none;font-size: 18px;width: 120px;">
                <#if (user.userName)?? && (user.userName)!=''>${user.userName}<a href="${ctx!}/userInfo/loginOut.htm">退出</a>
                <#else>
                    <a href="${ctx!}/userInfo/toLogin.htm" >登录</a>
                    <a href="${ctx!}/userInfo/toRegister.htm" >注册</a>
                </#if>
        </div>
    </div>
    <div class="menu-bar">
        <div class="menu">
            <ul>
                <li class="menuout"><a id="hot" class="highlight" href="${ctx!}/article/listHotArticle.htm">热门</a></li>
                <li class="menuout"><a id="approve" class="approve" href="${ctx!}/article/approveArticle.htm">审贴</a></li>
                <li class="menuout"><a id="contribute" href="${ctx!}/article/toAddArticle.htm">投稿</a></li>
            </ul>
        </div>
    </div>
    <#nested>
</body>
</html>

</#macro>
