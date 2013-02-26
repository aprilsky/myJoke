/**
 * 此js作为试验，最终作为jquery插件方式使用
 * @param pageNO
 * @param totalPages
 * @return {string}
 */
function appendPage(pageNO,totalPages){
    var startPoint = 1;
    var endPoint = 9;
    //上一页
    var pager= '<ul>';
    if(pageNO ==1){
        pager +='<li class="disabled"><a href="#">←</a></li>';
    }else{
        pager +='<li> <a href="?pageNo=${pageNo-1}">←</a></li>';
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
        pager += currentButton;
    }

    //下一页
    if(pageNO == totalPages){
        pager+='<li class="disabled"><a href="#">→</a></li>';
    }else{
        pager+='<li><a href="?pageNo='+(pageNO+1) +'">→</a></li>';
    }
    return pager;
}