//为避免冲突，将我们的方法用一个匿名方法包裹起来
/**
 * 这个js用于 处理同步分页
 */
(function($) {
    //扩展这个方法到jquery
    $.fn.extend({
        //插件名字
        pager: function(options) {
            //设置默认值并用逗号隔开
            var defaults = {
                pageNo : 1,
                totalPages : 0
            };
            var options =  $.extend(defaults, options);
            //遍历匹配元素的集合
            return this.each(function() {
                var pageNO = options.pageNo;
                var totalPageNO = options.totalPages;
                if(pageNO<=0){
                    pageNO=1;
                }
                if(totalPageNO<0){
                    totalPageNO=0;
                }
                if(totalPageNO==0){
                    $(this).html('暂无数据');
                    return ;
                }

                //在这里编写相应代码进行处理 
                var startPage = 1;
                var endPage = totalPageNO;
                //上一页
                var pager= $('<ul>');
                if(pageNO ==1){
                    pager.append('<li class="disabled"><a href="#">←</a></li>');
                }else{
                    pager.append('<li> <a href="?pageNo=1" title="首页">←</a></li>');
                }
                if (pageNO > 9 && totalPageNO>9) {//如果当前页大于9页，开始位置是当前页－4，结束位置是当前页＋4
                    //比如：第5页，则从
                    startPage = pageNO - 4;
                    endPage = pageNO + 4;
                    if (endPage > totalPageNO) {//如果结束位置大于总页数，开始位置为总页数－8，结束位置为总页数
                        startPage = totalPageNO - 8;
                        endPage = totalPageNO;
                    }
                }

                for (var pageTmp = startPage; pageTmp <= endPage; pageTmp++) {
                    var currentButton = "";
                    if(pageTmp == pageNO){
                        currentButton = "<li class='active'><a href='?pageNo="+ pageTmp+ "'>"+ pageTmp + "</a></li>";
                    }else{
                        currentButton = "<li><a href='?pageNo="+ pageTmp+ "'>"+ pageTmp + "</a></li>";
                    }
                    pager.append(currentButton);
                }
                //下一页
                if(pageNO >= totalPageNO){
                    pager.append('<li class="disabled"><a href="#">→</a></li>');
                }else{
                    pager.append('<li><a title = "末页" href="?pageNo='+(totalPageNO) +'">→</a></li>');
                }
                $(this).attr("class","pagination pagination-centered");
                $(this).html(pager);
            });
        }
    });
    //传递jQuery到方法中，这样我们可以使用任何javascript中的变量来代替"$"
})(jQuery); 