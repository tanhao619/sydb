(function () {
    'use strict';

    angular
        .module('oddApp')
        .controller('UserInfoUpdateController', UserInfoUpdateController);
    UserInfoUpdateController.$inject = ['$scope', '$stateParams', '$timeout', '$state', 'PortalManageService', 'Upload', 'SYDB','toaster','$uibModal'];
    function UserInfoUpdateController($scope, $stateParams, $timeout, $state, PortalManageService, Upload, SYDB,toaster,$uibModal) {
        var vm = this;
        vm.id = $stateParams.id;
        vm.role = $stateParams.role;
        vm.userSexRadio = "0"; // 性别
        vm.selectAddress = {
            //province:'北京市',
            //city:'东城区'
        };

        vm.homeTownAddress = {
            //province:'北京市',
            //city:'东城区'
        };

        getCategorie();
        init();

        // 时间插件
        $timeout(function () {
            if (vm.role == 2) {
                $('#personCity').citys({
                    required: false,
                    //nodata:'disabled',
                    onChange: function (data) {
                        vm.selectAddress.province = data.province;
                        vm.selectAddress.city = data.city;
                    }
                });
                $('#homeTownCity').citys({
                    required: false,
                    //nodata:'disabled',
                    onChange: function (data) {
                        vm.homeTownAddress.province = data.province;
                        vm.homeTownAddress.city = data.city;
                    }
                });
            } else if (vm.role == 1) {
                $('#enterpriseCity').citys({
                    required: false,
                    //nodata:'disabled',
                    onChange: function (data) {
                        vm.selectAddress.province = data.province;
                        vm.selectAddress.city = data.city;
                    }
                });
            }
        }, 13);

        vm.uploadImage = uploadImage;
        vm.save = save;

        vm.dataImage = {file: null};
        $timeout(function () {
            zoomImage(); // 缩放图片
        }, 300);

        function init() {
            if (vm.role == 2) {
                PortalManageService.getPerson({id: vm.id}, function (obj) {
                    if (obj.status != "SUCCESS") {
                        if(obj.code == 60){
                            if (obj.message) {
                                //alert(obj.message + "(" + obj.description + ")！");
                                var out = $uibModal.open({
                                    animation : true,
                                    templateUrl : "app/layouts/messageTips/messageTips.html",
                                    controller : "messageTipsController",
                                    controllerAs:"vm",
                                    size : "md",
                                    resolve : {
                                        items : function() {
                                            return obj.message + "(" + obj.description + ")！"
                                        }
                                    }
                                });
                            }
                            $state.go("userInformation");
                            return;
                        }
                    }
                    vm.personData = obj.response;
                    vm.userSexRadio = vm.personData.sex + '';
                    if (vm.personData.introduction) {
                        $scope.ueditorSetContent('personContent', vm.personData.introduction);
                    }
                    // 日期插件回显
                    $timeout(function () {
                        if (vm.personData.location) {
                            var address = JSON.parse(vm.personData.location);
                            $("#personCity").citys({
                                province: address.province,
                                city: address.city
                            });
                            vm.selectAddress.province = address.province;
                            vm.selectAddress.city = address.city;
                        }
                        if (vm.personData.homeTown) {
                            var addres = JSON.parse(vm.personData.homeTown);
                            $("#homeTownCity").citys({
                                province: addres.province,
                                city: addres.city
                            });
                            vm.homeTownAddress.province = addres.province;
                            vm.homeTownAddress.city = addres.city;
                        }
                    }, 14)

                });
            } else if (vm.role == 1) {
                PortalManageService.getEnterprise({id: vm.id}, function (obj) {
                    if (obj.status != "SUCCESS") {
                        if(obj.code == 60){
                            if (obj.message) {
                               // alert(obj.message + "(" + obj.description + ")！");
                                var out = $uibModal.open({
                                    animation : true,
                                    templateUrl : "app/layouts/messageTips/messageTips.html",
                                    controller : "messageTipsController",
                                    controllerAs:"vm",
                                    size : "md",
                                    resolve : {
                                        items : function() {
                                            return obj.message + "(" + obj.description + ")！"
                                        }
                                    }
                                });
                            }
                            $state.go("userInformation");
                            return;
                        }
                    }
                    vm.enterpriseData = obj.response;
                    if (vm.enterpriseData.enterpriseDetail) {
                        $scope.ueditorSetContent('enterpriseContent', vm.enterpriseData.enterpriseDetail);
                    }
                    // 日期插件回显
                    if (vm.enterpriseData.location) {
                        var address = JSON.parse(vm.enterpriseData.location);
                        $timeout(function () {
                            $("#enterpriseCity").citys({
                                province: address.province,
                                city: address.city
                            });
                            vm.selectAddress.province = address.province;
                            vm.selectAddress.city = address.city;
                        }, 14)


                    }
                    vm.enterpriseData.establishmentTime = new Date(vm.enterpriseData.establishmentTime);
                    vm.enterpriseData.expectFunctionCategory = vm.enterpriseData.categoryId;
                });
            }
        }

        function save() {
            if (!vm.selectAddress.province && !vm.selectAddress.city) {
               // alert("所在地址不能为空！");
                toaster.info("所在地址不能为空！");
                return;
            }
            if (vm.role == 1) {
                if (!$scope.ueditorGetContent('enterpriseContent')) {
                    //alert("详细内容不能为空！");
                    toaster.info("详细内容不能为空！");
                    return;
                }
                if (!vm.enterpriseData.headImg) {
                    //alert("头像图片不能为空！");
                    toaster.info("头像图片不能为空！");
                    return;
                }
                if (!vm.enterpriseData.expectFunctionCategory) {
                   // alert("所属行业不能为空！");
                    toaster.info("所属行业不能为空！");
                    return;
                }
                vm.enterpriseData.enterpriseDetail = $scope.ueditorGetContent('enterpriseContent');

                vm.enterpriseData.location = vm.selectAddress;
                var time = vm.enterpriseData.establishmentTime;
                vm.enterpriseData.establishmentTime = time.getFullYear() + "-" + (time.getMonth() + 1) + "-" + time.getDate() + " " + time.getHours() + ":" + time.getMinutes() + ":" + time.getSeconds();
                PortalManageService.updateEnterprise({'id': vm.id}, vm.enterpriseData, function success(obj) {
                    if (obj.status != "SUCCESS") {
                        vm.dataShow = false;
                        if (obj.message) {
                           // alert(obj.message + "(" + obj.description + ")！");
                            var out = $uibModal.open({
                                animation : true,
                                templateUrl : "app/layouts/messageTips/messageTips.html",
                                controller : "messageTipsController",
                                controllerAs:"vm",
                                size : "md",
                                resolve : {
                                    items : function() {
                                        return obj.message + "(" + obj.description + ")！"
                                    }
                                }
                            });
                        }
                        return;
                    }
                   // alert("修改成功!");
                    toaster.info("修改成功!！");
                    $state.go("userInformation");
                });
            } else if (vm.role == 0 || vm.role == 2) {
                if (!vm.selectAddress.province && !vm.selectAddress.city) {
                    //alert("所在地址不能为空！");
                    toaster.info("所在地址不能为空！");
                    return;
                }
                if (!vm.homeTownAddress.province && !vm.homeTownAddress.city) {
                    //alert("家乡地址不能为空！");
                    toaster.info("家乡地址不能为空！");
                    return;
                }
                if (!$scope.ueditorGetContent('personContent')) {
                   // alert("详细内容不能为空！");
                    toaster.info("详细内容不能为空！");
                    return;
                }
                if (!vm.personData.headImg) {
                   // alert("头像图片不能为空！");
                    toaster.info("头像图片不能为空！");
                    return;
                }
                if (!vm.personData.expectFunctionCategory) {
                   // alert("所属行业不能为空！");
                    toaster.info("所属行业不能为空！");
                    return;
                }
                vm.personData.introduction = $scope.ueditorGetContent('personContent');
                vm.personData.sex = vm.userSexRadio;

                vm.personData.location = vm.selectAddress;
                vm.personData.homeTown = vm.homeTownAddress;
                console.log(vm.personData);
                PortalManageService.updatePerson({'id': vm.id}, vm.personData, function success(obj) {
                    if (obj.status != "SUCCESS") {
                        vm.dataShow = false;
                        if (obj.message) {
                            //alert(obj.message + "(" + obj.description + ")！");
                            var out = $uibModal.open({
                                animation : true,
                                templateUrl : "app/layouts/messageTips/messageTips.html",
                                controller : "messageTipsController",
                                controllerAs:"vm",
                                size : "md",
                                resolve : {
                                    items : function() {
                                        return obj.message + "(" + obj.description + ")！"
                                    }
                                }
                            });
                        }
                        return;
                    }
                    //alert("修改成功!");
                    toaster.info("修改成功!！");
                    $state.go("userInformation");
                });
            }

        }

        function getCategorie() {
            PortalManageService.getCategories({type: 3}, function success(obj) {
                if (obj.status != "SUCCESS") {
                    return;
                }
                vm.enterpriseCategorie = obj.response;
            });
        }

        function uploadImage() {
            var url = SYDB + '/common/upload';
            var data = angular.copy(vm.dataImage || {});
            data.file = vm.dataImage.file;
            Upload.upload({
                url: url,
                data: data
            }).success(function (data) {
               // console.log(data);
                if (vm.role == 2) {
                    vm.personData.headImg = data.response.url;
                } else {
                    vm.enterpriseData.headImg = data.response.url;
                }
                zoomImage();
            }).error(function () {
            });
        }

        function zoomImage() {
            var imgs = $(".zoomImages");
            for (var i = 0; i < imgs.length; i++) {
                if (imgs[i].width > 750) {
                    imgs[i].width = 750;
                }
            }
        }
    }
})();