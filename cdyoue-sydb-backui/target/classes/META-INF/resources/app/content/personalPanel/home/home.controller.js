(function() {
    'use strict';

    angular
        .module('oddApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['ModifyPersonalInfoService'];

    function HomeController (ModifyPersonalInfoService) {
        var vm = this;
        ModifyPersonalInfoService.getMyPerson({},function (obj) {
            if (obj.status != "SUCCESS") {
                return;
            }
            vm.myInfo = obj.response;
        });
    }
})();
