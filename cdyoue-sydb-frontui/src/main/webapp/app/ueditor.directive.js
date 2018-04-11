(function () {
    'use strict';
    var page = angular.module('ueditor.directive', []);

    page.directive('ueditor', ['$templateCache',
        function ($templateCache) {
            return {
                restrict: 'AE',
                template: '<script id="ueditorId" name="content" type="text/plain">这里写你的初始化内容</script>',
                scope: false,
                compile: function (element, attr) {
                    return {
                        pre: function (scope, iElement, iAttrs, controller) {
                            var editorFunctions = window.UEDITOR_CONFIG.toolbars;
                            scope.ueditorId = attr.id;
                            scope.config = {};
                            if (attr.config != '' && attr.config != undefined) {
                                scope.config = $.parseJSON(attr.config);
                                editorFunctions = editorFunctions.concat($.parseJSON(attr.config).functions);
                            }
                            UE.delEditor(scope.ueditorId);

                            // 动态获取宽度
                            if($(element[0].parentNode).width()>0){
                                scope.config.initialFrameWidth = $(element[0].parentNode).width();
                            }

                            var editor = UE.getEditor(scope.ueditorId, {
                                toolbars: editorFunctions,
                                initialFrameWidth:scope.config.initialFrameWidth?scope.config.initialFrameWidth:565,  //初始化编辑器宽度,默认565
                                initialFrameHeight:scope.config.initialFrameHeight?scope.config.initialFrameHeight:300, //初始化编辑器高度,默认300
                            });

                            editor.ready(function () {

                            });

                            scope.ueditorSetContent = function (id, value) {
                                UE.getEditor(id).ready(function () {
                                    this.setContent(value)
                                });
                            };

                            scope.ueditorGetContent = function (id) {
                                var editor = UE.getEditor(id);
                                return editor.getContent();
                            };
                            scope.getInitConfig = function () {
                                return window.UEDITOR_CONFIG;
                            };
                            scope.ueditorGetContentTxt = function (id) {

                                var editor = UE.getEditor(id);
                                return editor.getContentTxt();
                            }
                        },
                        post: function (scope, iElement, iAttrs, controller) {

                        }
                    }
                }
            }
        }]);
})();