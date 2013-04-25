<#import "common.ftl" as p>
<@p.pageFrame "index">
<#assign pageNo =(RequestParameters.pageNo!'1')?number/>
<#assign articleList = articlePage.rows!''/>
<#assign count = (articlePage.total!'0')?number/>
<#assign totalPages = (articlePage.totalPages!'0')?number/>
<script type="text/javascript" src="${ctx!}/js/jquery.pager.js"></script>
<script type="text/javascript">
    $(function(){
        $('#pageDiv').pager({pageNo:${pageNo},totalPages:${totalPages}});
        $('.block-content').delegate('.comment_a','click',function(e){
            e.preventDefault();
            var id = $(this).attr('data-id');
            var _time = 1000;
            if($('#people_comment'+id).css('display')=='none'){
                $('#people_comment'+id).show(_time);
            }else{
                $('#people_comment'+id).hide(_time);
            }
            //加载所有的评论，及评论窗口
            var comment_url = '${ctx!}/comment/list.htm?articleId='+id;
            $('#people_comment'+id).load(comment_url);
        })
    })
    function more(t){
        //让这一个元素的 下一个class=content显示
        var parent_content = $(t).parent($(".content"));
        parent_content.css("display","none");
        parent_content.next(".content").css("display","block");
    }

</script>
<h1>热门</h1>
<div id="main">
    <#list articleList as article >
        <div class="block-content">

            <ul style="list-style: none">
                <li  style="font-size: 20px;">
                ${article.articleTitle!}
                </li>
                <#assign contentLength = (article.articleContent!)?length>
                <#if contentLength gt 500 >
                    <div class="content">${article.articleContent! ?substring(0,500)}...
                        <span style="color: gray;cursor:pointer" onclick="more(this)">更多</span>
                    </div>
                    <div class="content" style="display: none">${article.articleContent!}</div>
                <#else>
                    <div class="content">${article.articleContent!}</div>
                </#if>
                <div style="margin: 2px;font-size: 12px;color: gray"><a class="comment_a" data-id="${article.articleId!}" href="javascript:;"><span id="art_count_${article.articleId!}">${article.commentCount!}</span>人评论</a></div>
                <div id="people_comment${article.articleId!}" style="display: none;"></div>
            </ul>
        </div>
    </#list>
    <div id="pageDiv"></div>
</div>
<div id="sidebar">
    <h3>热门作者</h3>
    <ul class="posts">
        <li>
            <img src="images/thumb-smiling.png" alt="Img"> <span>Posted Feb. 21, 2023</span> <a href="blog.html">Choosing The Right Hair Color For You</a>
        </li>
        <li>
            <img src="images/thumb-beauty.png" alt="Img"> <span>Posted Feb. 20, 2023</span> <a href="blog.html">Color Trends For Spring 2023</a>
        </li>
        <li>
            <img src="images/thumb-shampoo.png" alt="Img"> <span>Posted Feb. 19, 2023</span> <a href="blog.html">Why You Should Wash Your Hair Less</a>
        </li>
        <li>
            <img src="images/thumb-sideview.png" alt="Img"> <span>Posted Feb. 18, 2023</span> <a href="blog.html">Top Shampoos for Colored Treated Hairs</a>
        </li>
    </ul>
</div>
</@p.pageFrame>