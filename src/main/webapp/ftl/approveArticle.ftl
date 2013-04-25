<#import "common.ftl" as p>
<@p.pageFrame "approve">
<script type="text/javascript">
    $(function () {
        var articleId = $("#articleId").val();
        var url = "${ctx!}/article/approve.htm?articleId=" + articleId;
        $(".status").click(function(){
            var status = $(this).attr("data-id");
            window.location.href=url+'&status='+status;
        })
    })
</script>
    <input type="hidden" id="articleId" value="${article.articleId!}">
    <div title="${article.articleTitle!}">${article.articleTitle!}</br>${article.articleContent!}</div>
    <div style="float: right;margin: 3px;">
        <a href="javascript:void(0)" class="status passed" data-id="passed" id="passed" style="color: blue">通过</a>
        <a href="javascript:void(0)" class="status filed" data-id="filed" id="filed" style="color: red;">拒绝</a>
    </div>
</@p.pageFrame>