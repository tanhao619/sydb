(function() {

    'use strict';

    angular
        .module('sydbApp')
        .factory('TimeSelect', TimeSelect);

    TimeSelect.$inject = ['$q'];

    function TimeSelect($q) {
        var service = {
            getTime:getTime
        };
        return service;

        function getTime(start,end,eDate){
            var date=new Date();
            if(eDate===1){
                date=false
            }else {

            }
            var defer=$q.defer();
            /*start*/
            $('#'+start).datepicker({
                clearBtn: true,
                language: "zh-CN",
                autoclose : true,
                todayHighlight : false,
                format:"yyyy-mm-dd",
                endDate : date,
                orientation: 'bottom'
            }).on('changeDate',function(e){
                var startTime = e.date;
                $('#'+end).datepicker('setStartDate',startTime);
                defer.resolve(startTime)
            });

            /*end*/
            $('#'+end).datepicker({
                clearBtn: true,
                language: "zh-CN",
                autoclose : true,
                todayHighlight : false,
                format:"yyyy-mm-dd",
                endDate : date,
                orientation: 'bottom'
            }).on('changeDate',function(e){
                var endTime = e.date;
                $('#'+start).datepicker('setEndDate',endTime);
                defer.resolve(endTime)
            });
            return defer.promise; //返回承诺，返回获取数据的API
        }

    }
})();