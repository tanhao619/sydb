(function() {
    'use strict';

    angular
        .module('oddApp')
        .directive('autoHeight', autoHeight)
        .directive('sidebarHeight', sidebarHeight)
        .directive('buildClick', buildClick);

    //动态设置container最小高度
    function autoHeight($window) {
        return {
            restrict: 'AC',
            link : function($scope, element, attrs) {
                var winowHeight = $window.innerHeight; //获取窗口高度
                element.css('min-height', winowHeight + 'px');
            }
        }
    }

    //动态设置sidebar最大高度
    function sidebarHeight($window) {
        return {
            restrict: 'AC',
            link : function($scope, element, attrs) {
                $scope.getHeightAgain = function () {
                    // setTimeout(getHeight(),800);
                    getHeight();
                };
                function getHeight() {
                    var winowHeight = $window.innerHeight; //获取窗口高度
                    var height = $('.main-content').outerHeight(true);//获取窗口高度
                    if(height <= winowHeight){
                        element.css('max-height', (height - 150) + 'px');
                        $scope.$broadcast('rebuild:me');
                    }
                    else {
                        element.css('max-height', height + 'px');
                        $('.ngsb-wrap').css({
                            'position': 'relative',
                            'overflow': 'visible',
                            'max-width': '100%',
                            'height': '100%'
                        });
                        $scope.$broadcast('rebuild:me');
                    }
                }
                setTimeout(getHeight,800);
            }
        }
    }

    //楼宇点击效果
    function buildClick() {
        return {
            restrict: 'AEC',
            link : function($scope, element, attrs) {
                element.bind('click',function(event){
                    var e = event || window.event;
                    var scrollX = document.documentElement.scrollLeft || document.body.scrollLeft;
                    var scrollY = document.documentElement.scrollTop || document.body.scrollTop;
                    var x = e.pageX || e.clientX + scrollX;
                    var y = e.pageY || e.clientY + scrollY;
                    $('#buildInfo').css({
                        'left': (x - 545) + 'px',
                        'top': (y - 395) + 'px'
                    });
                    $('#location').css({
                        'left': (x - 278) + 'px',
                        'top': (y - 33) + 'px'
                    });
                });
            }
        }
    }
})();