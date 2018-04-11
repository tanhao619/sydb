(function() {
    'use strict';

    angular
        .module('oddApp')
        .config('cfpLoadingBarProvider',cfpLoadingConfig);


    function cfpLoadingConfig(cfpLoadingBarProvider) {
        cfpLoadingBarProvider.latencyThreshold = 500;
    }
})();
