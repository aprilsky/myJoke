//为避免冲突，将我们的方法用一个匿名方法包裹起来
(function($) {
    //扩展这个方法到jquery
    $.fn.extend({
        //插件名字
        pager: function(options) {
            //设置默认值并用逗号隔开
            var defaults = {
                pageNo : 1,
                totalPages : 10
            };
            var options =  $.extend(defaults, options);
            //遍历匹配元素的集合
            return this.each(function() {
                var pageNO = options.pageNo;
                var totalPages = options.totalPages;
                //在这里编写相应代码进行处理 
                var startPoint = 1;
                var endPoint = 9;
                //上一页
                var pager= $('<ul>');
                if(pageNO ==1){
                    pager.append('<li class="disabled"><a href="#">←</a></li>');
                }else{
                    pager.append('<li> <a href="?pageNo='+(pageNO-1)+'">←</a></li>');
                }
                if (pageNO > 4) {//如果当前页大于4页，开始位置是当前页－4，结束位置是当前页＋4
                    startPoint = pageNO - 4;
                    endPoint = pageNO + 4;
                }
                if (endPoint > totalPages) {//如果结束位置大于总页数，开始位置为总页数－8，结束位置为总页数
                    startPoint = totalPages - 8;
                    endPoint = totalPages;
                }
                for (var pageTmp = startPoint; pageTmp <= endPoint; pageTmp++) {
                    var currentButton = "";
                    if(pageTmp == pageNO){
                        currentButton = "<li class='active'><a href='?pageNo="+ pageTmp+ "'>"+ pageTmp + "</a></li>";
                    }else{
                        currentButton = "<li><a href='?pageNo="+ pageTmp+ "'>"+ pageTmp + "</a></li>";
                    }
                    pager.append(currentButton);
                }
                //下一页
                if(pageNO == totalPages){
                    pager.append('<li class="disabled"><a href="#">→</a></li>');
                }else{
                    pager.append('<li><a href="?pageNo='+(pageNO+1) +'">→</a></li>');
                }
                $(this).attr("class","pagination pagination-centered");
                $(this).html(pager);
            });
        }
    });
    //传递jQuery到方法中，这样我们可以使用任何javascript中的变量来代替"$"
})(jQuery); 