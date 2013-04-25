<#import "common.ftl" as p>


<@p.pageFrame "">
<script type="text/javascript">

</script>
<form action="${ctx!}/userInfo/saveRegister.htm"  id="new_article" method="post">
    <div style="padding: 4px;">
        <div>邮箱:
            <input type="text" name="userEmail" >
        </div>
        <div>用户名:
            <input type="text" name="userName" >
        </div>
        <div>密码:
            <input type="password" name="passWord" >
        </div>
    </div>
    <input type="submit" style="letter-spacing: 20px;"  id="article_submit" name="commit"  value="注册">
</form>
</@p.pageFrame>