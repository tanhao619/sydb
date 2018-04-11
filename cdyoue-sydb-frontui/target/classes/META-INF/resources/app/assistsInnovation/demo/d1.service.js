(function() {

    'use strict';

    angular
        .module('sydbApp')
        .factory('Demo', Demo);
    Demo.$inject = ['$resource'];

    function Demo($resource) {
        var resourceUrl = "/ycgems/api";
        return $resource(resourceUrl, {}, {
            'getDetail': {url:"/ycgems/api/enterprise/demands/:id",method: 'GET'}, //获取企业需求详情
            'getList': {url:"/ycgems/api/enterprise/demands",method: 'GET'} //获取企业需求列表
        });
    }
})();
