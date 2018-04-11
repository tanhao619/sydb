(function () {
    'use strict';

    angular
        .module('sydbApp')
        .directive('buildClick', buildClick);

    //楼宇点击效果
    function buildClick() {
        return {
            restrict: 'AEC',
            scope: {
                data:'='
            },
            link : function(scope, element, attrs) {
                scope.$watch('data', function (newVal) {
                    if (element[0].id == newVal) {
                        var top;
                        var left;
                        var e = event || window.event;
                        var scrollX = document.documentElement.scrollLeft || document.body.scrollLeft;
                        var scrollY = document.documentElement.scrollTop || document.body.scrollTop;
                        var x = e.pageX || e.clientX + scrollX;
                        var y = e.pageY || e.clientY + scrollY;
                        top = element.offset().top;
                        left = element.offset().left;
                        $('#buildInfo').css({
                            'left': left + 'px',
                            'top': (top - 100) + 'px'
                        });
                        $('#location').css({
                            'left': (x - 10) + 'px',
                            'top': (y - 120) + 'px'
                        });
                    }
                }, true);
            }
        }
    }

})();
