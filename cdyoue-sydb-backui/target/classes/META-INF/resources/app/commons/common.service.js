(function () {

    'use strict';

    angular
        .module('oddApp')
        .factory('CommonService', CommonService);

    CommonService.$inject = ['$resource','SYDB'];

    function CommonService($resource,SYDB) {
        var resourceUrl = "";
        return $resource(resourceUrl, {}, {
            'getFileBasePath': {url:SYDB+"/common/customerService/fileBasePath",method: 'GET'} // 获取文件上传的基础路径
        });
    }
})();
