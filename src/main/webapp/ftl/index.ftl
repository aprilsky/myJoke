<#import "common_header.ftl" as p>
<@p.pageFrame "index">
    <#assign pageNo =(RequestParameters.pageNo!'1')?number/>
    <#assign articleList = articlePage.rows!''/>
    <#assign count = (articlePage.total!'0')?number/>
    <#assign totalPages = (articlePage.totalPages!'0')?number/>
<script type="text/javascript" src="${ctx!}/js/jquery.pager.js"></script>
<script type="text/javascript">
    $(function () {

        $('#pageDiv').pager({pageNo:${pageNo}, totalPages:${totalPages}});
        $('.block-content').delegate('.comment_a', 'click', function (e) {
            e.preventDefault();
            var id = $(this).attr('data-id');
            var _time = 1000;
            if ($('#people_comment' + id).css('display') == 'none') {
                $('#people_comment' + id).show(_time);
            } else {
                $('#people_comment' + id).hide(_time);
            }
            //加载所有的评论，及评论窗口
            var comment_url = '${ctx!}/comment/list.htm?articleId=' + id;
            $('#people_comment' + id).load(comment_url);
        })
    })
    function more(t) {
        //让这一个元素的 下一个class=content显示
        var parent_content = $(t).parent($(".content"));
        parent_content.css("display", "none");
        parent_content.next(".content").css("display", "block");
    }

</script>
    <div class="container">
        <div class="row">
            <div class="span6">
                <h1>Custom development</h1>
                <!--为什么要戒烟，为什么要有该网站-->
                <p>We are tagDiv – an online agency focusing on <strong>website design</strong> internet marketing and
                    increasing your bottom line. We combine focus and strategy in <strong>web technology</strong> to
                    provide with the tools to grow and make the new digital landscape.</p>

                <div id="button_div" style="margin-top: 80px">
                    <a class="btn btn-large" href="#">Read more</a>
                    <a class="btn btn-large" style="margin-left:180px" href="userInfo/toLogin.htm">Join US</a>
                </div>
            </div>
            <div class="span6">
                <!--吸烟导致的死亡人数宣传-->
                <img src="images/page-index/page-index-featured-img-1.png" alt=""/>
            </div>
        </div>
        <div class="row">
            <div class="span12">
                <ul id="tab-panel-1" class="content-tab">
                    <li id="tab-1" class="tab-selected"><a href="#">Hot Experience</a></li>
                    <li id="tab-2"><a href="#">Hot Blog</a></li>
                    <li id="tab-3"><a href="#">成功案例</a></li>
                </ul>
            </div>
        </div>

        <!-- tab panels -->
        <div class="tab-panel-wrap tab-panel-1">
            <!-- Tab 1 -->
            <div class="tab-panel tab-1">
                <div class="row">
                    <div class="span6 responsive-center">
                        <img src="images/page-index/content-img-1.jpg" alt=""/>
                    </div>

                    <div class="span6">
                        <h3>Get the latest info</h3>

                        <p>Our team of experienced IT professionals will work with you to advise on the most suitable ways
                            to maximise the technological infrastructure of your business or home system.</p>

                        <p>Working on an hourly or contract basis (to suit you), we’ll advise, facilitate and offer support
                            from concept to post-installation.</p>
                    </div>
                </div>
            </div>

            <!-- Tab 2 -->
            <div class="tab-panel tab-2">

                <div class="row">
                    <div class="span8">
                        <iframe class="video-content"
                                src="http://player.vimeo.com/video/44446467?title=0&amp;byline=0&amp;portrait=0&amp;color=ff0179"
                                width="100%" height="360" frameborder="0" webkitAllowFullScreen mozallowfullscreen
                                allowFullScreen></iframe>


                    </div>
                    <div class="span4">
                        <div class="content-scroll" style="height: 360px;  overflow:auto">
                            <div class="content-scroll-item">
                                <img src="images/page-index/thumb1.jpg" alt=""/>

                                <div class="title"><a class="black-link-normal" href="#">Websites</a></div>
                                <div class="description">Combining visuals with intuitive seo.</div>
                            </div>

                            <div class="content-scroll-item">
                                <img src="images/page-index/thumb2.jpg" alt=""/>

                                <div class="title"><a class="black-link-normal" href="#">Software</a></div>
                                <div class="description">A website should be an investment.</div>
                            </div>
                            <div class="content-scroll-item">
                                <img src="images/page-index/thumb1.jpg" alt=""/>

                                <div class="title"><a class="black-link-normal" href="#">Search</a></div>
                                <div class="description">We are always extending our it support offering.</div>
                            </div>
                            <div class="content-scroll-item">
                                <img src="images/page-index/thumb2.jpg" alt=""/>

                                <div class="title"><a class="black-link-normal" href="#">Photo</a></div>
                                <div class="description">Have a member of our friendly sales.</div>
                            </div>

                            <h3>Customize it</h3>

                            <p>Online marketing is growing ever faster, and getting noticed is becoming tougher. Having a
                                website is now only the first stage.</p>
                            <a href="index0a3c.html?page=page_typology" class="black-link">More info</a>

                            <h3>The best theme</h3>

                            <p>A website that has been optimised to perform well on Google can make the difference between
                                having a trickle of enquiries and having a flood.</p>
                            <a href="index0a3c.html?page=page_typology" class="black-link">More info</a>

                            <h3>Customize it</h3>

                            <p>We have many years' experience in SEO and offer an honest realistic service. If you want a
                                supplier who is going to guarantee you mind blowing results.</p>
                            <a href="index0a3c.html?page=page_typology" class="black-link">More info</a>

                        </div>
                    </div>
                </div>
            </div>


            <!-- Tab 3 -->
            <div class="tab-panel tab-3">
                <div class="row">
                    <div class="span6">
                        <img src="images/page-index/content-img-3.jpg" alt="">
                    </div>

                    <div class="span6">
                        <div class="content-scroll" style="height: 200px;  overflow:auto; margin-top: 30px;">


                            <h3>Customize it</h3>

                            <p>Have a member of our friendly sales staff contact you. They will answer any questions you may
                                have and educate you about our company.</p>


                            <h3>The best theme</h3>

                            <p>We have developed remote interview systems for immigration companies, allowing their agents
                                to carry out visa check.</p>
                            <a href="index0a3c.html?page=page_typology" class="black-link">More info</a>

                            <h3>Customize it</h3>

                            <p>This is just a sample of the many applications we have developed over the years to the
                                highest standards of reliability and quality, with extremely competitive pricing.</p>
                            <a href="index0a3c.html?page=page_typology" class="black-link">More info</a>

                        </div>
                    </div>
                </div>


            </div>


            <div class="row row-spacer">
                <div class="span4">
                    <h3 style="color: #777777">发表多少心情</h3>

                    <p>Whether you need data protection, recovery or back-up, web design or hosting, network design or
                        support, software installation, upgrades.</p>
                    <a href="#" class="black-link">More info</a>
                </div>
                <div class="span4">
                    <h3 style="color: #777777">发表多少日志</h3>

                    <p>We’ve worked with a range of SMEs across different sectors including media companies, defence,
                        pharmaceutical and finance.</p>
                    <a href="#" class="black-link">Visit the portofolio</a>
                </div>
                <div class="span4">
                    <h3 style="color: #777777">目前有多少人</h3>

                    <p>If the service you require is not on the list, please get in touch, as we are always extending
                        our it support offering.</p>
                    <a href="#" class="black-link">Buy our theme</a>
                </div>
            </div>
        </div>
        <!-- /tab - panel -->
    </div>
</@p.pageFrame>