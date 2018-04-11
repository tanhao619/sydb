(function() {

    'use strict';

    angular
        .module('sydbApp')
        .factory('professionalInterpretationService', professionalInterpretationService);
    professionalInterpretationService.$inject = ['$resource', 'SYDB'];

    function professionalInterpretationService($resource, SYDB) {
        var resourceUrl = "/sy/api";
        return $resource(resourceUrl, {}, {
            'getProfessionalInterpretations': {url:SYDB + "/professionalInterpretations",method: 'GET'}, //获取专业解读列表
            'getProfessionalInterpretation': {url:SYDB + "/professionalInterpretation/:id",method: 'GET'}, //获取专业解读详细
            'colProfessionalInterpretation': {url:SYDB + "/professionalInterpretation/:id/collect",params: {id:'@id'},method: 'POST'}, //收藏专业解读详细
        });
    }
})();
