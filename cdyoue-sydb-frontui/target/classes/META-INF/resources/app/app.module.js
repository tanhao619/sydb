(function () {
    'use strict';
    angular
        .module('sydbApp',
            ['ui.router',
                'ngResource',
                'ui.bootstrap', 'angular-loading-bar',
                'ngFileUpload', 'angularFileUpload',
                'toaster', 'ngSanitize', 'ngScrollbar',
                'ui.select2', 'oc.lazyLoad',
                'ueditor.directive'])
        .config(['$httpProvider', function ($httpProvider) {
            $httpProvider.defaults.headers.patch = {
                'Content-Type': 'application/json;charset=utf-8'
            }
        }])
        .run(run);

    run.$inject = ['$rootScope','$state', 'cfpLoadingBar', 'CommonService'];
    function run($rootScope, $state,cfpLoadingBar, CommonService) {
        // 设置文件上传的基础路径（全局）
        if (!$rootScope.SY_UploadBasePath) {
            CommonService.getFileBasePath({}, function (obj) {
                $rootScope.SY_UploadBasePath = obj.basePath;
            });
        }
        $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {
            // if (toState.url.substring(1) == "personalCenter" || toState.parent == "personalCenter") {
            //     console.log("s");
            //     if(!sessionStorage.getItem("syFrontUserTokenKey")){
            //         console.log("no");
            //         $state.go("login");
            //     }
            // }
            cfpLoadingBar.start();
        });
        $rootScope.$on('$stateChangeSuccess', function (event, toState, toParams, fromState, fromParams) {
            cfpLoadingBar.complete();
        });
    }

})();