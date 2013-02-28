<#assign pageNo =(RequestParameters.pageNo!'1')?number/>
<#assign articleList = commentPage.rows!''/>
<#assign count = (commentPage.total!'0')?number/>
<#assign totalPages = (commentPage.totalPages!'0')?number/>
<#assign articleId = (RequestParameters.articleId)/>
<script type="text/javascript" src="${ctx!}/js/jquery.pager.js"></script>
<script type="text/javascript">
    $(function(){
        $('#commentPageDiv').pager({pageNo:${pageNo},totalPages:${totalPages}});
    })

</script>
<style type="text/css">

</style>
<div id="commentPageDiv">
</div>
<h2>你的回应 &nbsp;·&nbsp;·&nbsp;·&nbsp;·&nbsp;·&nbsp;·</h2>
<div class="txd">
    <form action="${ctx!}/comment/save.htm" method="POST" id="commentform" name="commentform">
        <input type="hidden" name="articleId" value="${articleId}">
        <textarea name="commentContent" id="rv_comment" style="height:50px;width:400px;" class="textarea radius text"></textarea>
        <br>
        <input type="submit" value="加上去" id="submitFileForm">
        <i class="error" style="display: none;">你还没有登录..</i>
    </form>
</div>

