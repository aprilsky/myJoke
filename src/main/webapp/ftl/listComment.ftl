<#--列出所有评论，暂时不分页-->
<#assign commentList = commentPage.rows!''/>
<#assign count = (commentPage.total!'0')?number/>
<#assign articleId = (RequestParameters.articleId)/>
<script type="text/javascript">
    $(function(){
        $('#comment_list_Div'+${articleId!}).delegate('#submitFileForm'+${articleId!},'click',function(e){
            e.preventDefault();
            var _url = "${ctx!}/comment/save.htm";
            $.ajax({url:_url,data:$('#commentform'+${articleId!}).serialize(),success:function(e){
                if(e.success){
                    alert('评论成功');
                    $('#art_count_'+${articleId!}).html(parseInt($('#art_count_'+${articleId!}).html())+1);
                    var _str = $('#rv_comment'+${articleId!}).val()
                    $('#comment_li'+${articleId!}).append('<li>'+_str+'</li>')
                    $('#rv_comment'+${articleId!}).val('');
                }
            }});
        })
    })

</script>
<style type="text/css">

</style>
<div id="commentPageDiv${articleId!}">
    <ul id="comment_li${articleId!}">
        <#list commentList as entity>
            <li>${entity.commentContent!}</li>
        </#list>
    </ul>
</div>
<h2>你的回应 &nbsp;·&nbsp;·&nbsp;·&nbsp;·&nbsp;·&nbsp;·</h2>
<div class="txd" id="comment_list_Div${articleId!}">
    <form action="" onsubmit="return false;" method="POST" id="commentform${articleId!}" name="commentform">
        <input type="hidden" name="articleId" value="${articleId!}">
        <textarea name="commentContent" id="rv_comment${articleId!}" style="height:50px;width:400px;" class="textarea radius text"></textarea>
        <br>
        <input type="submit" value="加上去" id="submitFileForm${articleId!}">
    </form>
</div>