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
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<meta http-equiv="content-type" content="text/html;charset=UTF-8"/>
<head>
    <title>Cleanes</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <!--[if IE]>
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
    <link href="${ctx!}/external/bootstrap/css/bootstrap.css" rel="stylesheet"/>
    <!-- CSS: Bootstrap -->
    <link rel="stylesheet" type="text/css" href="${ctx!}/css/getCss.css"/>
    <!-- CSS: Our css :)  -->
    <link rel="stylesheet" type="text/css" href="${ctx!}/external/fancybox/jquery.fancybox-1.3.4.css"/>
    <!-- CSS: fancybox -->
    <!-- jQuery -->
    <script type="text/javascript" src="${ctx!}/js/jquery.js"></script>
    <script type="text/javascript" src="${ctx!}/js/underscore.js"></script>
    <!-- JS: effects core + fold and clip -->
    <!-- external stuff -->
    <script type="text/javascript" src="${ctx!}/js/custom.js"></script>
    <!-- JS: Our script :) -->
    <script type="text/javascript" src="${ctx!}/external/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
    <!-- JS: fancybox - modal image-->
    <script type="text/javascript" src="${ctx!}/external/fx.js"></script>
    <!-- JS: jquery fx used for the bar animation -->
    <script type="text/javascript" src="${ctx!}/external/jquery.nicescroll.min.js"></script>
    <!-- JS: nicescroll - the nice scrolling bar from index -->
    <script type="text/javascript" src="${ctx!}/external/jquery.masonry.min.js"></script>
    <!-- JS: masonry - portofolio new tile script -->
    <script type="text/javascript" src="${ctx!}/external/nivo-slider/jquery.nivo.slider.pack.js"></script>
    <!-- JS: nivo slider -->
    <link rel="stylesheet" type="text/css" href="${ctx!}/external/nivo-slider/nivo-slider.css"/>
    <!-- CSS: nivo slider -->
    <script type="text/javascript" src="${ctx!}/external/jquery.bxSlider/jquery.easing.1.3.js"></script>
    <!-- bx Slider -->
    <script type="text/javascript" src="${ctx!}/external/jquery.bxSlider/jquery.bxSlider.min.js"></script>
    <!-- bx Slider -->


</head>
<body>


<!-- black line from top -->
<div class="container" style="border-bottom:1px solid #ececec;">
    <div class="row header">
        <div class="span4">
            <!-- logo -->
            <a href="index.htm">
                <img class="logo" src="${ctx!}/images/header/logo.png" alt="" style="width:160px;"/>
            </a>
        </div>
        <div style="margin-left: 20px;float: left;">
            <!-- responsive dropdown menu -->
            <div class="js-jquery-dropdown-wrapper">
                <ul class="js-jquery-dropdown">
                    <li>
                        <a href="${ctx!}/index.htm">首页</a>
                    </li>


                    <li>
                        <a href="${ctx!}/article/listArticle.htm">文章</a>
                    </li>

                    <!-- submenu -->
                    <li class="sub_menu ">
                        <a href="#">每日一得
                            <div class="sub_menu_arrow"></div>
                        </a>
                        <ul>
                            <li>
                                <a href="${ctx!}/article/listArticle.htm?type=daily&articleType=tips">24小时<span
                                        class="label label-important">new</span></a>
                                <div class="dropdown-separator"></div>
                            </li>
                            <li>
                                <a href="${ctx!}/article/listArticle.htm?type=week&articleType=tips">一周前</a>

                                <div class="dropdown-separator"></div>
                            </li>
                            <li>
                                <a href="${ctx!}/article/listArticle.htm?type=month&articleType=tips">一个月前</a>
                            </li>
                        </ul>
                    </li>


                    <li style="margin-left: 190px;">
                        <a href="#">
                            <img src="http://qzonestyle.gtimg.cn/qzone/vas/opensns/res/img/Connect_logo_6.png">
                        </a>
                    </li>
                    <li>
                        <a href="${ctx!}/userInfo/toLogin.htm">登陆</a>
                    </li>
                    <li>
                        <a href="#">注册</a>
                    </li>
                </ul>

            </div>
            <!-- /menu -->
        </div>
    </div>
    <!-- /.row -->
</div>
    <#nested>
<!-- footer -->
<div class="footer">
    <div class="container">
        <div class="row">
            <div class="span6">
                <span class="footer-company">Collect from</div>
            <div class="span6">
                <div class="footer-right">
                    <span class="footer-company">office@tagdiv.com</span> +407.742.145.555
                    <div class="footer-social">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
<!-- Added by HTTrack -->
<meta http-equiv="content-type" content="text/html;charset=UTF-8"/>
<!-- /Added by HTTrack -->
</html>
</#macro>
