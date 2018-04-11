/**
 * Created by PC-45 on 2017/1/10.
 */
(function () {
    'use strict';
        angular
            .module('cdyoue.components.ztree',[])
            .directive('trees', createTree)        //自定义指令生成树形
            .factory('TreeService', TreeEvents); //树形封装工厂

        TreeEvents.$inject=['$rootScope'];
        createTree.$inject=['TreeService','$compile'];

        function createTree(TreeService,$compile) {
            return {
                restrict: 'E',
                scope: {
                    data: '=',
                },
                link: function($scope, element, attrs){
                    var customConfig=TreeService.getCustomConfig();
                    var template=' <div class=""><ul class="list">'
                        +'<li class="title taskTreeAll"    indexTree="{{data.indexTree}}"  id="citySel{{data.indexTree}}" >'
                        +'<span ng-repeat="item in data.weidu" class="treeDelete">{{item.name}}<a class="delWd" wdInde="{{$index}}" indexTree="{{data.indexTree}}">×</a></span>'
                        template +='</span><button type="button"><span class="glyphicon glyphicon-chevron-down"></span></button>'
                            +'</li></ul></div>';
                        template+='<div id="menuContent{{data.indexTree}}" class="menuContent{{data.indexTree}}" style="display:none;width: 100%;border: 1px solid #ccc;padding: 10px;position: absolute;z-index: 999;background: #fff;">'
                        if(customConfig.isSearch){
                            template +='<div class="input-group"><input id="searchTest" type="text" class="form-control"><span data="{{data.indexTree}}" class="input-group-addon searchWd"><i class="glyphicon glyphicon-search"></i></span></div>'
                        }
                        template+='<ul id="treeDemo{{data.indexTree}}" class="ztree" style="width: 100%;position: relative;z-index: 999;"></ul></div>';
                    var content=$compile(template)($scope);
                    element.append(content);
                }
            }
        }

        function TreeEvents($rootScope) {
            var configs=[];//所有配置文件数组
            var indexTree=0;//自加下标
            var zjIndex="";//当前点击事件下标
            var searchName="";
            var config={
                treeConfig:{
                    data:{
                        key:{name:"name"},
                        simpleData:{
                            idKey:'code',
                            pIdKey:'parentCode'
                        }
                    }
                },
                customConfig:{           //----------------功能API---------------------
                    isCategory:false,   //是否需要分类
                    isAllTree:false,    //是否为异步加载 (如果为异步加载 需要添加字段 )
                    isParent:'isParent',  //异步加载是否是最后一层
                    isSingle:true,     //是否为单选
                    seleteData:null,   //异步加载数据  第一层数据
                    seleteDataChild:null, // 异步加载数据  后面
                    isSearch:false      //是否需要添加搜索功能
                }
            };//默认配置文件
            var showTreeAll=[];//所有生成树形对象数组
            var datas=[];//获取传入数据数组

            var service = {
                setConfig: setConfig,//设置配置文件
                getCustomConfig:getCustomConfig,//获取配置文件
                init:init,//初始化参数
                getSearchName:getSearchName//对外提供查询名字
            };

            return service;

            function init(){

                taskTreeAll();
                del();
                if(config.customConfig.isSearch){
                    search();
                    searchOnClick();
                }
            }//初始化数据

            function getSearchName(){
                return searchName;
            }

            function search(){
                window.document.onkeydown = disableRefresh;
                function disableRefresh(evt){
                    evt = (evt) ? evt : window.event
                    if (evt.keyCode) {
                        if(evt.keyCode == 13){
                            if($("input:focus").next().attr('data')){
                                var index=$("input:focus").next().attr('data');
                                var name=$("#searchTest").val();//通过名字查找数据
                                disposeData(index,name)
                            }
                        }
                    }
                }

                $("body").on('click','.searchWd',function(){
                    var index=$(this).attr('data');
                    var name=$("#searchTest").val();//通过名字查找数据
                    disposeData(index,name)
                });

                function disposeData(index,name){
                    searchName=name;
                    var cfg=getIndexTree(index);
                    showTreeAll[index]=$.fn.zTree.init($('#treeDemo'+index), cfg.treeConfig, null);
                    var obj=cfg.customConfig.searchService();
                    var strA="";
                    if(obj.length>0){
                        for(var i=0;i<obj.length;i++){
                            strA+='<li><a class="wdOnClick" wdCode="'+obj[i].code+'">'+obj[i].name+'</a></li>'
                        }
                    }else{
                        strA="<li><span>无数据</span></li>"
                    }
                    $("#menuContent"+index).html('<div class="input-group"> <input id="searchTest" type="text" class="form-control"><span data="'+index+'" class="input-group-addon searchWd"><i class="glyphicon glyphicon-search"></i></span></div><ul id="treeDemo'+index+'" class="ztree" style="width: 100%;position: relative;z-index: 999;">'+strA+'</ul>')
                    showMenuAll(index);
                }

            }   //搜索事件

            function searchOnClick(){
                $("body").on('click','.wdOnClick',function(){
                    var code=$(this).attr('wdCode');
                    var name=$(this).html();
                    var data=getData(zjIndex);
                    var cfs=getIndexTree(zjIndex);
                    if(cfs.customConfig.isSingle){
                        for(var i= 0,item;item=data.weidu[i];i++){
                            item.code=code
                            item.name=name
                        }
                    }else{
                        var wd=new youe();
                        wd.code=code;
                        wd.name=name;
                        data.weidu.push(wd);
                    }
                    refreshData();
                    $("#menuContent"+zjIndex+"").slideUp("fast");
                });
            }   //搜索点击事件

            function getIndexTree(indexTree){
                for(var i=0;i<configs.length;i++){
                    if(configs[i].indexTree=indexTree){
                        return configs[i];
                    }
                }
            }// 获取当前配置文件

            function getData(indexTree){
                for(var i=0;i<datas.length;i++){
                    if(datas[i].indexTree==indexTree){
                        return datas[i];
                    }
                }
            }//获取数据

            function taskTreeAll(){
                $("body").off("click",'.taskTreeAll');
                $("body").on('click','.taskTreeAll',function() {
                    $(this).append('<span id="gifId"><img src="/vendor/Ztree-test/img/1.gif" style="width: 40px;height: 40px"></span>')
                    var indexTree= $(this).attr('indexTree');
                    var presentConfig=getIndexTree(indexTree);
                    zjIndex=indexTree;
                    if(!presentConfig.customConfig.isAllTree){// 是否一次性加载完
                        try{
                            var data=presentConfig.customConfig.seleteData();//获取数据
                            var gifId=$("#gifId").remove();
                        }catch(e) {
                            var gifId=$("#gifId").remove();
                        }
                        showTreeAllWay(indexTree,data); //生成结构
                        showMenuAll(indexTree);         //打开下拉框
                    }else{
                        presentConfig.treeConfig.callback.onCollapse=onExpand;
                        presentConfig.treeConfig.callback.onExpand=onCollapse;
                        try{
                            var data=presentConfig.customConfig.seleteData();//获取数据
                            var gifId=$("#gifId").remove();
                        }catch(e) {
                            var gifId=$("#gifId").remove();
                        }
                        showTreeAllWay(indexTree,data); //生成结构
                        showMenuAll(indexTree);         //打开下拉框
                    }
                })
            }//展示数据

            function del(){
                $("body").on('click','.delWd',function(event){
                    var wdInde=$(this).attr('wdInde');
                    zjIndex= $(this).attr('indexTree');
                    getData(zjIndex).weidu.splice(wdInde,1);
                    refreshData();
                    event.stopPropagation();
                });
            }//删除数据

            function onExpand(event,treeId,treeNode){
                showTreeAll[zjIndex].removeChildNodes(treeNode);
            }//删除节点

            function onCollapse(event,treeId,treeNode){
                var data=getIndexTree(zjIndex).customConfig.seleteDataChild();//获取数据
                showTreeAll[zjIndex].addNodes(treeNode,data);
            }//打开节点

            function  showTreeAllWay(indexTree,data){
                var test=getIndexTree(indexTree).treeConfig;
                var zTreessAll= $.fn.zTree.init($('#treeDemo'+indexTree), test, data);
                showTreeAll[indexTree]=zTreessAll;
            }//生成数据结构

            function showMenuAll(indexTree){
                var menuContent="#menuContent"+indexTree;
                $(menuContent).slideDown("fast");
                $("body").bind("mousedown", onBodyDownAll);
            }//打开弹窗

            function onBodyDownAll(event) {
                if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent"+zjIndex+"").length>0)) {
                    hideMenuAll();
                }
            }

            function hideMenuAll() {
                $("#menuContent"+zjIndex+"").fadeOut("fast");
                $("body").unbind("mousedown", onBodyDownAll);
            }

            function getCustomConfig(){
                return config.customConfig;
            }// 提供获取配置方法

            function setConfig(Zconfig,data){
                indexTree++;
                extend(config.customConfig,Zconfig.customConfig);//  跟新最新配置文件
                Zconfig.customConfig=config.customConfig;
                data.indexTree=indexTree;
                Zconfig.indexTree=indexTree;
                Zconfig.treeConfig.callback=
                {
                    onClick: onClickAll,        //点击节点事件
                    onExpand:null,              //缩短节点事件
                    onCollapse:null             //展开节点事件
                };
                configs.push(Zconfig);
                datas.push(data);
            }//设置配置

            function onClickAll(event,treeId,treeNode){
                var data=getData(zjIndex);
                var cfs=getIndexTree(zjIndex);
                if(cfs.customConfig.isSingle){
                    for(var i=0;i<data.weidu.length;i++){
                        data.weidu[i].code=treeNode[cfs.treeConfig.data.simpleData.idKey];
                        data.weidu[i].name=treeNode[cfs.treeConfig.data.key.name];
                    }
                    refreshData();
                }else{
                    var weidu=new youe();
                    weidu.code= treeNode[cfs.treeConfig.data.simpleData.idKey];
                    weidu.name=treeNode[cfs.treeConfig.data.key.name];
                    data.weidu.push(weidu);
                    refreshData();
                }
                $("#menuContent"+zjIndex+"").slideUp("fast");
            } //点击数据事件

            function youe(){
                return{
                    code:'',
                    name:''
                }
            }

            function refreshData(){
                $rootScope.$broadcast('refreshData');
            }//刷新事件

            function extend(target, source) {
                for (var key in source) {
                    if (source.hasOwnProperty(key)) {
                        target[key] = source[key];
                    }
                }
            }//互换数据

        }
})()