(function(){
    'use strict';
    angular.module('sydbApp')
        .controller('ActivitiesController',ActivitiesController);

    ActivitiesController.$inject=['$http','ActivityService','$state'];

    function ActivitiesController($http,ActivityService,$state) {
        var vm=this;
        //document.title = "场地服务";
        vm.query = {
            pageSize:10,
            pageNumber:0,
            q:'',
            sort:"-publishTime",
            areaIdLvPre:'',
            areaIdLvNext:''
        };
        vm.contentShow = true;
        vm.search = search;//查询
        vm.places=[];

        getCitys();//获取二级城市
        getPlaces();//获取以及城市
        vm.getNextCitys = getNextCitys;//获取一级城市下的二级城市
        vm.getNextData  =getNextData;//获取二级城市下的场地
        vm.selectPage = selectPage;
        vm.selectPageSize = selectPageSize;
        // vm.goDetail = goDetail;

        function search() {
            vm.query.pageNumber = 0;
            getPlaces();
        }
        function getPlaces() {
            ActivityService.getActivities(vm.query,function (rel) {
                if(rel.status == 'SUCCESS'){
                    vm.places = rel.response.list;
                }else {
                    vm.places=[];
                    vm.contentShow = false;
                    vm.contentMsg ="当前数据为空"
                }
                vm.pages = rel.response;
            },function (err) {
                vm.places=[];
                vm.contentShow = false;
                vm.contentMsg ="当前数据为空"
            })
        }
        function getNextData(val) {
            vm.activeCode1 = val;
            // console.log(val);
            if(val == 0){
                vm.query.areaIdLvNext = '';
            }else {
                vm.query.areaIdLvNext = val;
            }
            getPlaces();
        }
        function getNextCitys(code) {
            vm.citys=[];
            vm.activeCode = code;
            //二级城市默认选中全部
            vm.activeCode1 = '';
            vm.query.areaIdLvNext ='';
            //默认选中全部 end
            var cs = getCityByPro(vm.initData, code);//获取二级城市--ByCode
            vm.citys = angular.copy(cs);
            if(code == 0){
                vm.query.areaIdLvPre = '';
                vm.query.areaIdLvNext ='';
            }else {
                vm.query.areaIdLvPre = code;
            }
            if (code != vm.query.areaIdLvPre){
                vm.query.areaIdLvNext ='';
            }
            getPlaces()
        };
        function goDetail(id) {
            $state.go("ActivityDetail",{id:id});
        }
        function selectPage(select, current, max) {
            if (select != current) {
                if (select > max) {
                    return;
                }
                vm.query.pageNumber = select;
                ActivityService.getActivities(vm.query, function (data) {
                    if(data.status == 'SUCCESS'){
                        vm.places = data.response.list;
                    }else {
                        vm.places=[];
                        vm.contentShow = false;
                        vm.contentMsg ="当前数据为空"
                    }
                    vm.pages = data.response;
                    vm.pages.pageNum = select;
                })
            }
        }

        function selectPageSize(pageSize) {
            if (pageSize == null || pageSize == "") {
                return;
            }
            vm.query.pageSize = pageSize;
            vm.query.pageNumber = 0;
            ActivityService.getActivities(vm.query, function (data) {
                if(data.status == 'SUCCESS'){
                    vm.places = data.response.list;
                }else {
                    vm.places=[];
                    vm.contentShow = false;
                    vm.contentMsg ="当前数据为空"
                }
                vm.pages = data.response;
                vm.pages.pageNum = 0;
            });
        }



        //===========================//
        //======获取地理数据Begin=======//
        //============================//
        var defaults = {
            code: 0,                   //地区编码
            province: 0,               //省份,可以为地区编码或者名称
            city: 0,                   //城市,可以为地区编码或者名称
            area: 0                    //地区,可以为地区编码或者名称
        };

        //--------------------------//
        //获取地理城市 code And Name  //
        //--------------------------//
        var options = $.extend({}, defaults, {});
        var province, city, area, hasCity;
        if (options.code) {   //如果设置地区编码，则忽略单独设置的信息
            var c = options.code - options.code % 1e4;
            if (data[c]) {
                options.province = c;
            }
            c = options.code - (options.code % 1e4 ? options.code % 1e2 : options.code);
            if (data[c]) {
                options.city = c;
            }
            c = options.code % 1e2 ? options.code : 0;
            if (data[c]) {
                options.area = c;
            }
        }



        function getCitys() {
            $http.get('').then(function (rel) {
                // var a = updateData(rel.data);
                vm.province = getProvince(rel.data);
                vm.activeCode = '0';
                vm.activeCode1 = '0';
                vm.initData = rel.data;
                var  a = getCityByPro(rel.data, '0');
                vm.citys = angular.copy(a);
            }).catch(function (err) {
                console.log(err);

            });
        }


        function getProvince(data, val) {
            options.province = val;
            province = [], area = [];
            province.push({
                code: 0,
                name: '全部'
            });
            hasCity = false;       //判断是非有地级城市
            for (var code in data) {
                if (!(code % 1e4)) {     //获取所有的省级行政单位
                    // province[code] = data[code];
                    province.push({
                        code: code,
                        name: data[code]
                    });
                    if (options.required && !options.province) {
                        if (options.city && !(options.city % 1e4)) {  //省未填，并判断为直辖市
                            options.province = options.city;
                        } else {
                            options.province = code;
                        }
                    } else if (data[code].indexOf(options.province) > -1) {
                        options.province = isNaN(options.province) ? code : options.province;
                    }
                }
            }
            return province;

        }

        function getCityByPro(data, val) {
            var city = city = [];
            hasCity = false;
            city.push({
                code: 0,
                name: '全部'
            });
            options.province = val;
            for (var code in data) {
                if ((code % 1e4)) {
                    var p = code - options.province;
                    if (options.province && p > 0 && p < 1e4) {    //同省的城市或地区
                        if (!(code % 100)) {
                            hasCity = true;
                            // city[code] = data[code];
                            city.push({
                                code: code,
                                name: data[code]
                            });
                            if (options.required && !options.city) {
                                options.city = code;
                            } else if (data[code].indexOf(options.city) > -1) {
                                options.city = isNaN(options.city) ? code : options.city;
                            }
                        } else if (p > 9000) {                   //省直辖县级行政单位
                            // city[code] = data[code];
                            city.push({
                                code: code,
                                name: data[code]
                            });
                            if (options.required && !options.city) {
                                options.city = code;
                            } else if (data[code].indexOf(options.city) > -1) {
                                options.city = isNaN(options.city) ? code : options.city;
                            }
                        } else if (hasCity) {                  //非直辖市
                            var c = code - options.city;
                            if (options.city && c > 0 && c < 100) {     //同个城市的地区
                                area[code] = data[code];
                                if (options.required && !options.area) {
                                    options.area = code;
                                } else if (data[code].indexOf(options.area) > -1) {
                                    options.area = isNaN(options.area) ? code : options.area;
                                }
                            }
                        } else {
                            // city[code] = data[code];
                            city.push({
                                code: code,
                                name: data[code]
                            }); //直辖市
                            if (options.area) {
                                options.city = options.area;
                                options.area = '';
                            }
                            if (options.required && !options.city) {
                                options.city = code;
                            } else if (data[code].indexOf(options.city) > -1) {
                                options.city = isNaN(options.city) ? code : options.city;
                            }
                        }
                    }
                }
            }
            return city;

        }
    }
})();