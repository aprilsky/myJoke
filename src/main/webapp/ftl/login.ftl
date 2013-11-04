<#import "common_header.ftl" as p>
<@p.pageFrame "">
<script type="text/javascript">

</script>
<div class="container">
    <div class="row">
    <div class="span6">
        <h1>Custom development</h1>

        <p>We are tagDiv – an online agency focusing on <strong>website design</strong> internet marketing and
            increasing your bottom line. We combine focus and strategy in <strong>web technology</strong> to provide
            with the tools to grow and make the new digital landscape.</p>
        <a class="btn btn-large" href="index0a3c.html?page=page_typology">Read more</a>
    </div>
    <div class="span6">
        <form class="form-horizontal" style="margin-top: 40px;" action="${ctx!}/userInfo/login.htm" method="post">
            <div class="control-group">
                <label class="control-label" for="inputEmail">Email</label>

                <div class="controls">
                    <input type="text" id="inputEmail" name="userEmail" placeholder="Email">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="inputPassword">Password</label>

                <div class="controls">
                    <input type="password" id="inputPassword" name="passWord" placeholder="Password">
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <label class="checkbox">
                        <input type="checkbox"> Remember me
                    </label>
                    <button type="submit" class="btn btn-large">Sign in</button>
                    <button type="button" id="register_btn_id" style="margin-left: 80px;"
                            class="btn btn-large btn-black">注册
                    </button>
                </div>
            </div>
        </form>
    </div>
    </div>
</div>
</@p.pageFrame>