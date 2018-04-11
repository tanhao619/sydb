(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('NavbarController', NavbarController);

    NavbarController.$inject = ['$rootScope', 'name'];

    function NavbarController($rootScope, name) {
        var vm = this;
        var navItem = [];
        vm.name = name;
        vm.navItems = [];

        vm.Menus ={};
        if(!$rootScope.headerNavItems){
            if(!JSON.parse(sessionStorage.getItem("SY_MenuData"))){
                vm.Menus = JSON.parse(sessionStorage.getItem("oddBackUserTokenKey")).response.userMine.topMenus;
            }else {
                vm.Menus = angular.copy(JSON.parse(sessionStorage.getItem("SY_MenuData")));
            }
        }else {
            vm.Menus =$rootScope.headerNavItems;
        }
        console.log(vm.Menus);
        angular.forEach(vm.Menus,function (data, index, array) {
            switch (data.uiSref){
                case "personalPanel":
                    vm.personalPanel = data.leftMenus;
                    break ;
                case "contentManagement":
                    vm.contentManagement = data.leftMenus;
                    break ;
                case "backManagement":
                    vm.backManagement = data.leftMenus;
                    break ;
                default:
                    break ;
            }
        });

        switch (vm.name) {
            case 'personalPanel':
                vm.navItems = vm.personalPanel;
                break;
            case 'contentManagement':
                vm.navItems = vm.contentManagement;
                break;
            case 'backManagement':
                vm.navItems = vm.backManagement;
                break;
            default:
                break
        }
        for (var i = 0, len = vm.navItems.length; i < len; i++) {
            navItem.push(vm.navItems[i]);
        }
    }
})();
