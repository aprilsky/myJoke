/**
 * @author yu
 */
(function($){
    function _$(t){
        return $(t);
    }
    function render(t,p){
        var data = $.data(t, "tpagination");
        var tpagination = data.tpagination;
        var dom=['<div class="tpagination-panel">',
            '<div class="pag-tool pag-info"></div>',
            '<div class="pag-tool"><button class="pag-prev-page">上一页</button></div>',
            //'<div class="pag-tool"><button class="pag-first-page">首页</button></div>',
            '<div class="pag-tool pagnum"></div>',
            //'<div class="pag-tool"><button class="pag-last-page">末页</button></div>',
            '<div class="pag-tool"><button class="pag-next-page">下一页</button></div>'
        ]
        dom.push('<p class="clear"></p>')
        dom.push('</div>')
        tpagination.append(dom.join(''));
        bindEvent(t);
        //TODO:NEED DELETE
        //updatePag(t);
        data.isLoaded=true;
        data.options.onRender.call(t)
        //data.options.onLoad.apply(t, arguments);
    }
    function renderPageNum(t){

        var data = $.data(t, "tpagination");
        if (data) {
            var tpagination = data.tpagination;
            var pagnum=tpagination.find('.pagnum');
            var page=data.options.page;
            var pageTotal=data.options.pageTotal;
            var pagnumdom=[];
            if(page<5){
                if(pageTotal<8){
                    for(var i=1;i<=pageTotal;i++){
                        if(i==page){
                            pagnumdom.push(getCur(i))
                        }else{
                            if(i==1){
                                pagnumdom.push(getLink(i,'first'))
                            }else{
                                if(i==pageTotal){
                                    pagnumdom.push(getLink(i,'last'))
                                }else{
                                    pagnumdom.push(getLink(i))
                                }
                            }
                        }
                    }
                }else{
                    for(var i=1;i<7;i++){
                        if(i==page){
                            pagnumdom.push(getCur(i))
                        }else{
                            if(i==1){
                                pagnumdom.push(getLink(i,'first'))
                            }else{
                                pagnumdom.push(getLink(i))
                            }
                        }
                    }
                    pagnumdom.push(getBreak())
                    pagnumdom.push(getLink(pageTotal,'last'))
                }
            }else{
                if(pageTotal<8){
                    for(var i=1;i<=pageTotal;i++){
                        if(i==page){
                            pagnumdom.push(getCur(i))
                        }else{
                            if(i==1){
                                pagnumdom.push(getLink(i,'first'))
                            }else{
                                if(i==pageTotal){
                                    pagnumdom.push(getLink(i,'last'))
                                }else{
                                    pagnumdom.push(getLink(i))
                                }
                            }
                        }
                    }
                }else{

                    if(pageTotal-page<5){
                        for(var i=pageTotal;i>=pageTotal-5;i--){
                            if(i==page){
                                pagnumdom.unshift(getCur(i))
                            }else{
                                if(i==pageTotal){
                                    pagnumdom.unshift(getLink(i,'last'))
                                }else{
                                    pagnumdom.unshift(getLink(i))
                                }
                            }
                        }
                        pagnumdom.unshift(getBreak())
                        pagnumdom.unshift(getLink(1,'first'))
                    }else{
                        pagnumdom.push(getLink(1,'first'))
                        pagnumdom.push(getBreak())
                        for(var i=page-2;i<=page+2;i++){
                            if(i==page){
                                pagnumdom.push(getCur(i))
                            }else{
                                pagnumdom.push(getLink(i))
                            }
                        }
                        pagnumdom.push(getBreak())
                        pagnumdom.push(getLink(pageTotal,'last'))
                    }



                }



            }





            pagnum.html('');
            pagnum.append(pagnumdom.join(''))
        }
    }
    function getLink(num,isWhat){
        var dom='<a href="javascript:void(0);"';
        if(isWhat){
            dom+=' class="pagnum-'+isWhat+'"';

        }
        dom+=' num="'+num+'">'+num+'</a>'
        return dom;

    }
    function getCur(num){
        return '<strong>'+num+'</strong>';
    }
    function getBreak(){
        return '<span class="break">...</span>';
    }
    function setBtn(t){
        var data = $.data(t, "tpagination");
        if (data) {
            var tpagination = data.tpagination;
            if(data.options.page==1){
                if(data.options.page==data.options.pageTotal){
                    tpagination.find('.pag-prev-page').prop('disabled',true).addClass('disabled');
                    tpagination.find('.pag-next-page').prop('disabled',true).addClass('disabled');
                }else{
                    tpagination.find('.pag-prev-page').prop('disabled',true).addClass('disabled');
                    tpagination.find('.pag-next-page').prop('disabled',false).removeClass('disabled');
                }
                if(data.options.total==0){
                    tpagination.find('.pag-prev-page').prop('disabled',true).addClass('disabled');
                    tpagination.find('.pag-next-page').prop('disabled',true).addClass('disabled');
                }

            }else if(data.options.page==data.options.pageTotal){
                tpagination.find('.pag-prev-page').prop('disabled',false).removeClass('disabled');
                tpagination.find('.pag-next-page').prop('disabled',true).addClass('disabled');
            }else{
                tpagination.find('.pag-prev-page').prop('disabled',false).removeClass('disabled');
                tpagination.find('.pag-next-page').prop('disabled',false).removeClass('disabled');
            }

        }
    }
    function setBtn2(t,p){
        var data = $.data(t, "tpagination");
        var tpagination = data.tpagination;
        tpagination.find('.pag-last-page').hide();
        //tpagination.find('.pag-go-panel').hide();
        if(data.options.page==1){
            tpagination.find('.pag-prev-page').prop('disabled',true);
        }else{
            tpagination.find('.pag-prev-page').prop('disabled',false);
        }
        if(p==true){
            tpagination.find('.pag-next-page').prop('disabled',false);
        }else{
            tpagination.find('.pag-next-page').prop('disabled',true);
        }
    }
    function bindEvent(t){
        var data = $.data(t, "tpagination");
        var tpagination = data.tpagination;
        tpagination.delegate( '.pag-tool.pagnum a', 'click', function(e){
            e.preventDefault();
            if(data.options.loading==true){return;}
            data.options.loading=true;
            data.options.page=parseInt($(this).attr('num'));
            data.options.onChange.apply(t);
            setBtn(t);
        })
        tpagination.delegate( '.pag-prev-page', 'click', function(){
            if(data.options.loading==true){return;}
            data.options.loading=true;
            data.options.page--;
            data.options.onChange.apply(t);
            setBtn(t);
            renderPageNum(t);
        })
        tpagination.delegate( '.pag-next-page', 'click', function(){
            if(data.options.loading==true){return;}
            data.options.loading=true;
            data.options.page++;
            data.options.onChange.apply(t);
            setBtn(t);
            renderPageNum(t);
        })



    };

    /*
     * 设置页码
     * @param	: t; 应用插件的HTML元素
     * @param	: pageNumber; 页码

     * @return	: undefined

     * @authod	: 罗志华
     * @date	: 2013/4/7
     */
    function setCurrentPage( t, pageNumber) {
        if (pageNumber instanceof Number) {
            var data = $.data(t, "spagination");
            data.options.page=pageNumber;
            data.options.onChange.apply(t);
            setBtn(t);
        }
    }

    function updatePag(t){
        var data = $.data(t, "tpagination");
        if (data) {
            var tpagination = data.tpagination;
            data.options.pageTotal=Math.ceil(data.options.total/data.options.rows);
            setBtn(t);
            updateInfo(t);
            renderPageNum(t);
            data.options.loading=false;
        }
    }
    function noLast(t,p){
        var data = $.data(t, "tpagination");
        var tpagination = data.tpagination;
        setBtn2(t,p);
        hideInfo(t);
        data.options.loading=false;
    }
    function hideInfo(t){
        var data = $.data(t, "tpagination");
        var tpagination = data.tpagination;
        tpagination.find('.pag-tool.pag-info').hide();
    }
    function updateInfo(t){
        var data = $.data(t, "tpagination");
        if (data) {
            var tpagination = data.tpagination;
            var op=data.options;
            var info=op.pageInfo;
            for(var k in op){
                var reg=new RegExp('{'+k+'}');
                if(op[k]){
                    info=info.replace(reg,op[k].toString());
                }

            }
            if(op.total>0){
                tpagination.find('.pag-info').html('<span>'+info+'</span>')
            }
        }
    }
    function empty(t){
        var data = $.data(t, "tpagination");
        var tpagination = data.tpagination;
        tpagination.find('.pag-prev-page').prop('disabled',true).addClass('disabled');
        tpagination.find('.pag-next-page').prop('disabled',true).addClass('disabled');
    }
    function setTotal(t,total){
        var data = $.data(t, "tpagination");
        data.options.total=total
    }
    $.fn.tpagination=function(o, p) {
        if( typeof o == "string") {
            return $.fn.tpagination.methods[o](this, p);
        }
        o = o || {};
        return this.each(function() {
            var data = $.data(this, "tpagination");
            var option;
            if(data) {
                option = $.extend(data.options, o);
            } else {
                option = $.extend({}, $.fn.tpagination.defaults, $.fn.tpagination.parseOptions(this),o);
                $(this).attr("title", "");
                data = $.data(this, "tpagination", {
                    options : option,
                    tpagination : _$(this),
                    isLoaded : false
                });
            }
            render(this);
        })}
    $.fn.tpagination.methods={
        render:function(t,p){
            return t.each(function() {
                render(this, p);
            });
        },
        setCurrentPage : function(t, pageNumber ){
            return t.each(function() {
                setCurrentPage(this, pageNumber);
            });
        },
        updateInfo:function(t){
            return t.each(function() {
                updateInfo(this);
            });
        },
        updatePag:function(t){
            return t.each(function() {
                updatePag(this);
            });
        },
        noLast:function(t,p){
            return t.each(function() {
                noLast(this,p);
            });
        },
        setTotal:function(t,total){
            return t.each(function() {
                setTotal(this,total);
            });
        },
        empty:function(t){
            return t.each(function() {
                empty(this);
            });
        }
    }
    $.fn.tpagination.parseOptions = function(t) {
        var t = $(t);
        return {}
    }

    $.fn.tpagination.defaults = {
        page:1,
        pageName:'pageNumber',
        rows:10,
        rowsName:'pageSize',
        total:0,
        pageTotal:0,
        loading:null,
        pageInfo:'第{page}/{pageTotal}页 共{total}条记录',
        onChange:function(){},
        onRender:function(){}
    }
})(jQuery)
