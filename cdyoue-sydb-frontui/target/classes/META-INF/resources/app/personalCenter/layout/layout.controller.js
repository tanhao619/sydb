(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('layoutsController', layoutsController);

    layoutsController.$inject = ['$rootScope','$scope', '$state', '$location', '$timeout',  'toaster'];

    function layoutsController($rootScope,$scope, $state, $location, $timeout, toaster) {
        var vm = this;
        var navItem = [];
        var navMenu = [];
        vm.activetUrl = $state.current.active;
        vm.show = {value: null};
        vm.navItems = [
            {
                name: "基本资料",
                targetBlank: false,
                icon: "fa-building",
                href:'personalCenter'
            },
            {
                name: "我的收藏",
                targetBlank: false,
                icon: "fa-building",
                href:'collections'

            },
            {
                name: "我的知产",
                targetBlank: false,
                icon: "fa-building",
                href:'knowledge'
            },
            {
                name: "我的申报",
                targetBlank: false,
                icon: "fa-building",
                href:'declare'
            },
            {
                name: "我的融资",
                targetBlank: false,
                icon: "fa-building",
                href:'financing'
            },
            {
                name: "修改密码",
                targetBlank: false,
                icon: "fa-building",
                href:'modifyPassword'
            }
            ,{
                name: "交易中心",
                targetBlank: false,
                icon: "fa-building",
                url:'http://syda.youedata.com/ds/sy-platform/views/member/personalCenter.html'
            }


        ];

        vm.selectedNavItem = "";
        vm.selectedSubmenu = "";
        vm.activeShow = "";
        vm.navClick = navClick;//激活导航


        for (var i = 0, len = vm.navItems.length; i < len; i++) {
            navItem.push(vm.navItems[i]);
            defaultSelected();
        }


        function defaultSelected() {
            for (var j = 0; j < navItem.length; j++) {
                if (vm.activetUrl == navItem[j].href) {
                    vm.selectedNavItem = navItem[j].name;
                    break;
                }
                else if (navItem[j].hasOwnProperty("submenu")) {
                    angular.forEach(navItem[j], function (data, index, array) {
                        for (var l = 0; l < array.submenu.length; l++) {
                            if (vm.activetUrl == array.submenu[l].href) {
                                vm.show.value = j;
                                vm.selectedSubmenu = array.submenu[l].name;
                                vm.selectedNavItem = array.name;
                                break;
                            }
                        }
                    });
                }
            }
        }


        function navClick(navTitie, index) {
            var myIndex = index;
            var selectedSubmenu = vm.selectedSubmenu;
            vm.currentUrl = navTitie;
            vm.show.value = null;
            if (vm.selectedNavItem == navTitie) {
                vm.selectedNavItem = "";
            }
            else {
                vm.selectedNavItem = navTitie;
                vm.show.value = myIndex;
            }
            // vm.show.value = index;
            // vm.selectedNavItem = navTitie;
            for (var k = 0; k < navItem.length; k++) {
                if (navItem[k].name == navTitie && navItem[k].hasOwnProperty("submenu")) {
                    // vm.show.value = myIndex;
                    vm.selectedSubmenu = selectedSubmenu;
                    break;
                }
                else {
                    vm.selectedSubmenu = null;
                }
            }
        }


    }
})();
