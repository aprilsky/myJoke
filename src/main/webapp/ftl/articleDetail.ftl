<#import "common_header.ftl" as p>
<@p.pageFrame "">
<script src="http://malsup.github.com/jquery.form.js"></script>


<script type="text/template" id="userList-template">
    <li class="comment">
    <article>
        <footer>
            <img src="${ctx!}/images/page-blog/thumb-1.jpg" alt=""/>
            <cite><a href="#">张三</a></cite>
            <div class="comment-meta">
                <time pubdate="" datetime="2012-08-06T00:18:45+00:00">2013-09-09</time>
            </div>
        </footer>
        <div class="comment-content">
            <%=commonContent%>:
        </div>
    </article>
    </li>
</script>
<script>
    $(function(){
        $('#submitCommentAction').click(function(){
            _this = $(this);
            _this.attr('disabled','disabled');
            $('#commentform').ajaxSubmit({
                dataType:'json',
                success:function(rep){
                    _this.removeAttr('disabled');
                    templateAppend(rep);
                    document.commentform.reset();
                },error:function(){
                    _this.removeAttr('disabled');
                }
            });
            return false;
        });
    })

    function templateAppend(rep){
        if(rep.success){
            var comments = {
                commonContent: $('#commentTextarea').val()
            };
            var html = _.template($('#userList-template').html(),comments);
            $('#comment_ol').prepend(html);
        }
    }
</script>

<div class="container">
<div class="row">

<!-- #content -->
<div id="content" class="span9">
    <!-- .post -->
    <article class="post">
        <!-- .entry-header -->
        <header class="entry-header" style="position:relative;">
            <div class="entry-meta-left">
                <div class="entry-date-wrap">
                    <a href="#" rel="bookmark">
                        <#assign submitStr = article.submitTime?string("yyyy年MM月dd日")>
                        <time class="entry-date" datetime="2008-05-03T06:19:03+00:00" pubdate="">${submitStr}</time>
                    </a>
                </div>
                <div class="entry-commnts">

                </div>
            </div>
            <hgroup>
                <h3 class="entry-title">
                    <a href="#" rel="bookmark">${article.articleTitle!}</a>
                </h3>
            </hgroup>
            <div class="entry-meta">
                                <span class="author">
                                    作者:
                                    <a href="#" title="View all posts by admin" rel="author">${article.userName!}</a>
                                </span>
            </div>
        </header>

        <!-- .entry-content -->
        <div class="entry-content">
        ${article.articleContent!}
        </div>


    </article>
    <!-- /.post -->


    <div class="comments">
        <h5 class="comments-title">一共有${replySize!0}个回应</h5>
        <div>
            <label>我来回复</label>
            <form name="commentform" id="commentform"  method="post" action="${ctx!}/comment/save.htm" data-initialized="true">
                <textarea class="textarea" name="commentContent" id="commentTextarea" style="width:490px;height:60px;"></textarea>
                <p><button id="submitCommentAction"><span>提交回复</span></button>
                    <input type="hidden" name="articleId" value="${article.articleId!}">
                    <i class="error"></i>
                    <i class="succed"></i>
                </p><br>
            </form>
        </div>
        <ol class="comment-list"  id="comment_ol">
            <#list commentsList as comment>
            <li class="comment">
                    <article>
                        <footer>
                            <img src="${ctx!}/images/page-blog/thumb-1.jpg" alt=""/>
                            <cite><a href="#">${comment.userName!}</a></cite>
                            <div class="comment-meta">
                                <time pubdate="" datetime="2012-08-06T00:18:45+00:00">${comment.commentDate!}</time>
                            </div>
                        </footer>
                        <div class="comment-content">
                            ${comment.commentContent!}
                        </div>
                    </article>
            </li>
            </#list>
        </ol>

    </div>
</div>
<!-- /#content -->


<!-- #sidebar -->
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

    <!-- .widget_text -->
    <aside class="widget widget widget_text">
        <h3 class="widget-title">Text widget</h3>

        <div>
            Digital Kitchen from Chicago uses Autodesk 3ds Max software for its 3D animation motion graphics and
            commercials work.
        </div>
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
<!-- /#sidebar -->


</div>
<!-- /row -->
</div>
</@p.pageFrame>