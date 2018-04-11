(function () {
    'use strict';

    angular
        .module('sydbApp')
        .config(httpConfig)
        .run(run);
    httpConfig.$inject = ['$urlRouterProvider', '$httpProvider', '$urlMatcherFactoryProvider'];
    run.$inject = ['$location', '$rootScope', '$stateParams', '$anchorScroll'];
    function httpConfig($urlRouterProvider, $httpProvider, $urlMatcherFactoryProvider) {

        $urlRouterProvider.otherwise('/assistsInnovation');

        $urlMatcherFactoryProvider.type('boolean', {
            name: 'boolean',
            decode: function (val) {
                return val === true || val === 'true';
            },
            encode: function (val) {
                return val ? 1 : 0;
            },
            equals: function (a, b) {
                return this.is(a) && a === b;
            },
            is: function (val) {
                return [true, false, 0, 1].indexOf(val) >= 0;
            },
            pattern: /bool|true|0|1/
        });

        if (!$httpProvider.defaults.headers.get) {
            $httpProvider.defaults.headers.get = {};
        }
        $httpProvider.interceptors.push('myInterceptor');
        // Enables Request.IsAjaxRequest() in ASP.NET MVC
        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
        //禁用IE对ajax的缓存
        $httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
        $httpProvider.defaults.headers.get['Pragma'] = 'no-cache';
        var browser = navigator.appName;
        var b_version = navigator.appVersion;
        var version =b_version.split(";");
        var trim_Version = version[1] && version[1].replace(/[ ]/g, "");
        if (browser == "Microsoft Internet Explorer" && trim_Version == "MSIE9.0") {
            $httpProvider.defaults.headers.get['If-Modified-Since'] = '0';
        }

        $httpProvider.defaults.headers.patch = {
            'Content-Type': 'application/json;charset=utf-8'
        }
    }

    function run($location, $rootScope, $stateParams, $anchorScroll) {
        $rootScope.$on('$stateChangeSuccess', function (event, toState, toParams, fromState, fromParams) {
            $anchorScroll();
        });
    }
})();
