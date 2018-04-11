(function () {
    'use strict';

    angular
        .module('sydbApp')
        .controller('knowledgeBuyController', knowledgeBuyController);

    knowledgeBuyController.$inject = ['$scope', '$state'];

    function knowledgeBuyController($scope, $state) {
        var vm = this;
       console.log("成功了");
    }
})();
