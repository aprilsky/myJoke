<#import "common.ftl" as p>
<@p.pageFrame "contribute">
<script type="text/javascript">
    var maxLength = 500;
    $(function () {
        $('#textarea').bind('click',function (e) {
            if ($(this).val() == '分享我的真实糗事') {
                $(this).val('');
            }
        }).bind('blur', function (e) {
                    if ($(this).val() == '') {
                        $(this).val('分享我的真实糗事');
                    }
                })
    })
</script>
        <h1>发表</h1>
        <form action="${ctx!}/article/saveArticle.htm" id="new_article" method="post" >
            <textarea id="textarea" name="articleContent" style="height:176px;width:655px;">分享我的真实糗事</textarea>

            <div id="Num"></div>

            <div style="padding: 2px;">
                <label>标题：</label>
                <input type="text" size="30" name="articleTitle" maxlength="30" id="articleTitle">
            </div>
            <div style="padding: 2px;">
                <label>选项:</label>
                <input type="checkbox" checked="checked" id="accept">
                <label for="accept">我已阅读并同意</label> <a href="#cp1">投稿须知</a>
            </div>
            <input type="submit" style="letter-spacing: 20px;"
                   id="article_submit" name="commit" title="Ctrl + Enter可以快捷投递" value="投递">
            <#if !(user.userId)??>
                <a href="${ctx!}/userInfo/toLogin.htm" style="color: red">请先登录</a>
            </#if>
        </form>
        <div style="font-size: 14px;">
            <a name="cp1"></a>
            <h1>投稿须知</h1>
            <ol>
                <li>自己的或听说过的糗事，真实有笑点，不含政治、色情、广告、个人隐私、歧视等内容。</li>
                <li>糗事经过审核后发表。</li>
                <li>转载请注明出处。</li>
                <li>内容版权归糗事百科网站所有。</li>
            </ol>
        </div>
</@p.pageFrame>