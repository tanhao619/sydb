(function() {

    'use strict';

    angular
        .module('oddApp')
        .factory('Test', Test);
    Test.$inject = ['$resource'];

    function Test($resource) {
        var resourceUrl = "/ycbdgems/api";
        return $resource(resourceUrl, {}, {
            'getList': {url:"/ycbdgems/api/findAll",method: 'GET',isArray:true} //获取所有列表
        });
    }
})();
