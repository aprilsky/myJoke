<#import "common.ftl" as p>


<@p.pageFrame "">
<script type="text/javascript">

</script>
<div class="block-content">
    <form action="${ctx!}/userInfo/login.htm" method="post">
        <div style="padding: 4px;">
            <div>账号:
                <input type="text" name="userEmail" style="border: 1px solid ;background: #FFB9B9;">输入用户名或者邮箱
            </div>
            <div>密码:
                <input type="password" name="passWord" style="border: 1px solid ;background: #FFB9B9;">
            </div>
            <#if msg??>
                <span style="color: red;">登录失败</span>
            </#if>
        </div>
        <input type="submit" style="letter-spacing: 20px;" id="article_submit" name="commit" value="登录">
    </form>
</div>
</div>
</@p.pageFrame>