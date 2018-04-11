(function() {

    'use strict';

    angular
        .module('oddApp')
        .factory('TimeSelect', TimeSelect);

    TimeSelect.$inject = ['$q'];

    function TimeSelect($q) {
        var service = {
            getTime:getTime
        };
        return service;

        function getTime(start,end){
            var date=new Date();
            // if(eDate===1){
            //     date=false
            // }else {
            //
            // }
            var defer=$q.defer();
            /*start*/
            // alert(start);
            $('#'+start).datepicker({
                clearBtn: true,
                language: "zh-CN",
                autoclose : true,
                todayHighlight : true,
                format:"yyyy-mm-dd",
                endDate : date
            }).on('changeDate',function(e){
                var startTime = e.date;
                $('#'+end).datepicker('setStartDate',startTime);
                defer.resolve(startTime)
            }).on('clearDate',function(e){
                console.log(e);
                $('#'+end).datepicker('setStartDate', '1970-01-01');
            });

            /*end*/
            $('#'+end).datepicker({
                clearBtn: true,
                language: "zh-CN",
                autoclose : true,
                todayHighlight : true,
                format:"yyyy-mm-dd",
                endDate : date
            }).on('changeDate',function(e){
                console.log(e);
                var endTime = e.date;
                $('#'+start).datepicker('setEndDate',endTime);
                defer.resolve(endTime)
            }).on('clearDate',function(e){
                $('#'+start).datepicker('setEndDate',date);
                console.log(e);
            });
            return defer.promise; //返回承诺，返回获取数据的API
        }

    }
})();