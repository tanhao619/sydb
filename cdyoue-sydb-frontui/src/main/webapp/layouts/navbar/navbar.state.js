 (function () {
    'use strict';

    angular
        .module('sydbApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];


    function stateConfig($stateProvider) {
        $stateProvider.state('assistsInnovation', { //助力创新
            parent: "app",
            url: "/assistsInnovation",
            active: "assistsInnovation",
            views:{
                'content@':{
                    templateUrl:"app/assistsInnovation/index/index.html",
                    controller:"AssistsInnovationController",
                    controllerAs:"vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['assistsInnovation']);
                }]
            }
        }).state('login', { //登录
            parent: "app",
            url: "/login",
            active: "login",
            views:{
                'content@':{
                    templateUrl:"app/login/login.html",
                    controller:"loginController",
                    controllerAs:"vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['login']);
                }]
            }
        }).state('register', { //注册
            parent: "app",
            url: "/register",
            active: "register",
            views:{
                'content@':{
                    templateUrl:"app/register/register.html",
                    controller:"registerController",
                    controllerAs:"vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['register']);
                }]
            }
        }).state('forgetPassword', { //忘记密码
            parent: "app",
            url: "/forgetPassword",
            active: "forgetPassword",
            views:{
                'content@':{
                    templateUrl:"app/forgetPassword/forgetPassword.html",
                    controller:"forgetPasswordController",
                    controllerAs:"vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['forgetPassword']);
                }]
            }
        }).state('securityValidate', { //安全验证
            parent: "app",
            url: "/securityValidate/:tel",
            active: "securityValidate",
            views:{
                'content@':{
                    templateUrl:"app/forgetPassword/securityValidate.html",
                    controller:"securityValidateController",
                    controllerAs:"vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['securityValidate']);
                }]
            }
        })
            .state('repeatPassword', { //重置密码
            parent: "app",
            url: "/repeatPassword/:tel",
            active: "repeatPassword",
            views:{
                'content@':{
                    templateUrl:"app/forgetPassword/repeatPassword.html",
                    controller:"repeatPasswordController",
                    controllerAs:"vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['repeatPassword']);
                }]
            }
        }) .state('findPasswordSuccess', { //找回密码成功
            parent: "app",
            url: "/findPasswordSuccess",
            active: "findPasswordSuccess",
            views:{
                'content@':{
                    templateUrl:"app/forgetPassword/findPasswordSuccess.html",
                    controller:"findPasswordSuccessController",
                    controllerAs:"vm"
                }
            },
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', 'LOAD_DEMAND', function ($ocLazyLoad, LOAD_DEMAND) {
                    return $ocLazyLoad.load(LOAD_DEMAND['findPasswordSuccess']);
                }]
            }
        })

    }
})();
