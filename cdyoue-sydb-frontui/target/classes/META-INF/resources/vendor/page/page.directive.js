(function () {
    'use strict';

    angular
        .module('sydbApp')
        .directive('youePage', youePage)
        .directive('shortPage', shortPage);

    youePage.$inject = ['$anchorScroll'];

    function youePage($anchorScroll) {
        var directive = {
            restrict: 'E',
            link: linkFunc,
            scope:{
                pageinfo:'=pageinfo',
                selectPage:'=selectPage',
                selectPageSize:'=selectPageSize',
            },
            templateUrl: 'vendor/page/pageinfo.html'
        };
        return directive;

        function linkFunc(scope, element, attrs) {
            scope.values = [{number:10},{number:30},{number:50},{number:100}];
            scope.myValue = 10;
            scope.$watch('pageinfo.nextPage',function (p1, p2, p3) {
                $anchorScroll();
            });
        }
    }

    function shortPage() {
        var directive = {
            restrict: 'E',
            link: linkFunc,
            scope:{
                pageinfo:'=pageinfo',
                selectPage:'=selectPage'
            },
            templateUrl: 'vendor/page/shortpage.html'
        };
        return directive;

        function linkFunc(scope, element, attrs) {

        }
    }

})();
