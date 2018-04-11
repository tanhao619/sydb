
var gulp = require('gulp');
var bs =require("browser-sync").create();
var gulpLoadPlugins=require('gulp-load-plugins');
var plugins = gulpLoadPlugins();
//var pageLess='about';
var pageNmae='about';

gulp.task('less', function() {
    gulp.src("./less/**/application.less")
        .pipe(plugins.less())
        //.pipe(plugins.autoprefixer({
        //    browsers:[">1%","last 2 versions","IE 9","IE 10","Firefox 4","opera 12.1"]
        //}))
        .pipe(gulp.dest('./assets/css'))
        .pipe(bs.reload({stream:true}))
});

gulp.task('watch', function() {
    gulp.watch('./webapp/less/**/*.less', ['less']);
});


gulp.task("browsersync",function(){
    // bs.init({
    //     proxy: "http://localhost:8080"
    // });
    var files=[
        "./webapp/**/*.html",
        "./webapp/**/*.js"];
    bs.init(files,{server:{baseDir:"./webapp",index:"./disthtml/about/about.html"}})
});

gulp.task('default',['browsersync', 'watch']);



gulp.task('testHtmlmin', function () {
    var options={
        removeComments: true,//???HTML???
        collapseWhitespace: true,//???HTML
        collapseBooleanAttributes: true,//???????????? <input checked="true"/> ==> <input />
        removeEmptyAttributes: true,//??????��????????? <input id="" /> ==> <input />
        removeScriptTypeAttributes: true,//???<script>??type="text/javascript"
        removeStyleLinkTypeAttributes: true,//???<style>??<link>??type="text/css"
        minifyJS: true,//??????JS
        minifyCSS: true//??????CSS
    };

    gulp.src('src/html/*.html')
        .pipe(plugins.htmlmin(options))
        .pipe(gulp.dest('dist/html'));
});


gulp.task('testCssmin', function () {
    gulp.src('src/css/**/*.css')
    .pipe(plugins.cleanCss({
     advanced: false,//?????Boolean ????true [????????????????????????]
     compatibility: 'ie7',//????ie7?????????��?? ?????String ????''or'*' [???��??????? 'ie7'??IE7????????'ie8'??IE8????????'*'??IE9+??????]
     keepBreaks: false//?????Boolean ????false [?????????]
     }))
     .pipe(gulp.dest('dist/css'))

});


gulp.task('testRev', function () {
    gulp.src('src/html/*.html')
        .pipe(plugins.revAppend())
        .pipe(gulp.dest('dist/html'));
});


gulp.task('jsmin', function () {
    gulp.src('./src/js/**/*.js')
        .pipe(plugins.livereload())
        .pipe(plugins.uglify())
        .pipe(gulp.dest('./dist/js'));
});

gulp.task('testConcat', function () {
    gulp.src('src/js/*.js')
        .pipe(plugins.concat('all.js'))
        .pipe(plugins.uglify())
        .pipe(gulp.dest('dist/js'));
});

