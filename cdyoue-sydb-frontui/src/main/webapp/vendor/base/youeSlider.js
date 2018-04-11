//轮播
(function($){
    $.fn.sliderCarousel=function(options){
        var defaults={
            'sliderTime':500,                //过渡效果的时间
            'loadingTime':10000,              //等待时间
            'sliderWay':true,                //轮播方式，true是右，false是左
            'sliderAtuo':true                //是否自动轮播
        };
        var options= $.extend(defaults,options);
        return this.each(function(){
            var $this=$(this);
            var sliderContent=$this.find(".sliderContent");
            var inputLi=$this.find(".sliderInput").children("li");
            var content=$this.find(".sliderContent").children(".item");
            var inputLeft=$this.find(".sliderLeft");
            var inputRight=$this.find(".sliderRight");
            var value=0;                  //隐藏的input的值
            var inputValue;              //显示的input值
            var timing;                 //自动轮播定时器
            var guardTime=true;        //保护时间
            if(options.sliderAtuo){
                auto();
            }
            console.log()
            function sliderGo(obj,thisLeft){
                if(guardTime==false){
                    return;
                }
                guardTime=false;
                setTimeout(function () {
                    guardTime=true
                },options.sliderTime);
                if(obj!=''&&obj!=undefined){
                    var bb=$(obj);
                    inputValue=parseInt(bb.attr("data-imgValue"));
                }
                var showDiv=content.eq(inputValue);
                var hiddenDiv=content.eq(value);
                var divLeft=100;
                inputLi.eq(inputValue).addClass("active").siblings().removeClass("active");
                showDiv.addClass("active").siblings().removeClass("active");
                if(inputValue==value){
                    clearInterval(timing);
                    auto();
                    return;
                }
                  if(value<inputValue){
                     divLeft=-100;
                 }
                if(thisLeft!=''&&thisLeft!=undefined){
                    divLeft=thisLeft;
                }
                showDiv.css("left",-divLeft+"%");
                hiddenDiv.animate({
                    'left':divLeft+"%"
                },options.sliderTime);
                showDiv.animate({
                    'left':'0'
                },options.sliderTime);
                value=inputValue;
                clearInterval(timing);
                auto();
            }
            function sliderLeft(){
                var leftValue=value-1;

                if(leftValue==-1){
                    leftValue=content.length-1;
                }
                inputValue=leftValue;
                sliderGo('',100);
            }
            function silderRight(){
                var rightValue=value+1;
                if(rightValue==content.length){
                    rightValue=0;
                }
                inputValue=rightValue;
                sliderGo('',-100);
            }
            function auto(){
                timing=setInterval(function () {
                    if(options.sliderWay){
                        silderRight();
                    }
                    else{
                        sliderLeft();
                    }
                },options.loadingTime)
            }
            inputLeft.click(function () {
                sliderLeft();
            });
            inputRight.click(function(){
                silderRight();
            });
            inputLi.click(function () {

                sliderGo(this);
            });
        })
    }
})(jQuery);

$(function() {
    function slider() {
        $("#sliderImg").sliderCarousel()
    }
    setTimeout(slider, 3000);
});