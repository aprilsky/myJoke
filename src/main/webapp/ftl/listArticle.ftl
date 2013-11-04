<#import "common_header.ftl" as p>
<@p.pageFrame "">
<#assign pageNo =(RequestParameters.pageNo!'1')?number/>
<#assign articleList = articlePage.rows!''/>
<#assign totalPages = (articlePage.totalPages!'0')?number/>
<script type="text/javascript" src="${ctx!}/js/jquery.pager.js"></script>

<script type="text/javascript">
    $(function(){
        $('#pageDiv').pager({pageNo:${pageNo}, totalPages:${totalPages}});
    })
</script>
<style>

</style>
<div class="container">
<div class="row">
<div id="content" class="span9">
    <#assign dataStr = "">
    <#list articleList as article>
        <article class="post">
        <!-- .entry-header -->
        <header class="entry-header" style="position:relative;">
            <#assign submitStr = article.submitTime?string("yyyy年MM月dd日")>
            <#if dataStr?index_of(submitStr) lt 0 >
                <div class="entry-meta-left">
                    <div class="entry-date-wrap">
                        <a href="#current${article_index}" rel="bookmark">
                        ${submitStr}
                        </a>
                    </div>
                </div>
                <#assign dataStr= submitStr + dataStr>
            </#if>

        <hgroup>
            <h3 class="entry-title">
                <a href="${ctx!}/article/articleDetail.htm?articleId=${article.articleId!}" rel="bookmark">${article.articleTitle!}
                </a>
            </h3>
        </hgroup>
    </header>

    <!-- .entry-content -->
        <div class="entry-content">
            ${article.articleContent!}
            <p>
                <a href="${ctx!}/article/articleDetail.htm?articleId=${article.articleId!}" class="more-link">回应</a>
            </p>
        </div>
    </article>
    </#list>
    <div id="pageDiv"></div>
</div>
<div id="sidebar" class="span3 widget-area">

    <!-- .widget_categories -->
    <aside class="widget widget_categories">
        <h3 class="widget-title">Categories</h3>
        <ul>
            <li class="cat-item current-cat"><a href="#">All categories</a></li>
            <li class="cat-item"><a href="#">Tipology and rotoscopic</a></li>
            <li class="cat-item"><a href="#">Planning</a></li>
            <li class="cat-item"><a href="#">Market places</a></li>
            <li class="cat-item"><a href="#">Zbrush deformation</a></li>
        </ul>
    </aside>

    <!-- .widget_recent_entries -->
    <aside class="widget widget_recent_entries">
        <h3 class="widget-title">Recent posts</h3>
        <ul>
            <li>
                <a href="#">Autodesk 3ds Max Products</a>

                <div>28 july 2012</div>
            </li>

        </ul>
        <ul>
            <li>
                <a href="#">3ds Max for Television</a>

                <div>28 july 2012</div>
            </li>

        </ul>
        <ul>
            <li>
                <a href="#">Featured customer stories</a>

                <div>28 july 2012</div>
            </li>

        </ul>
        <ul>
            <li>
                <a href="#">Market places</a>

                <div>28 july 2012</div>
            </li>

        </ul>
        <ul>
            <li>
                <a href="#">Tutorials for Photoshop CS5</a>

                <div>28 july 2012</div>
            </li>

        </ul>
    </aside>


    <!-- .widget_tag_cloud -->
    <aside class="widget widget widget_tag_cloud">
        <h3 class="widget-title">Tags</h3>

        <div>
            <a href="#">cienaga</a>
            <a style="font-size: 1.1em" href="#">claycold</a>
            <a style="font-size: 1.3em" href="#">crushing</a>
            <a href="#">dinarchy</a>
            <a style="font-size: 0.7em" href="#">doolie</a>
            <a href="#">energumen</a>
        </div>
    </aside>

</div>
</div>

</div>
</@p.pageFrame>