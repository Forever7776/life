var gulp = require('gulp'),  //基础
    runSequence = require('run-sequence'), //顺序
    cssmin = require('gulp-minify-css'), //css压缩
    cssver = require('gulp-make-css-url-version'),
    jshint = require('gulp-jshint'),//js检查
    uglify = require('gulp-uglify'), //js压缩
    concat = require('gulp-concat'),//文件合并
    clean = require('gulp-clean'),//清空文件夹
    rename = require('gulp-rename'),//更改文件名
    rev = require('gulp-rev'),//更改版本名
    filter = require('gulp-filter'),//文件过滤
    zip = require('gulp-zip'), //zip插件
    revCollector = require('gulp-rev-collector');//gulp-rev的插件 ，用于html模板更改引用路径


var project = 'life-admin';
var path = {
    target : 'target',
    base : 'target/' + project,
    resources : 'target/' + project + '/WEB-INF/classes/static',
    css: 'target/' + project + '/WEB-INF/classes/static'+'/css',
    js: 'target/' + project + '/WEB-INF/classes/static'+'/js',
    templates:'target/' + project + '/WEB-INF/classes/static'+'/templates',
    static_build_path: 'target/build',
};

/**
 * 清空
 */
gulp.task('clean', function () {
    return gulp.src([path.static_build_path], {read: false}).pipe(clean());
});

/**
 * 压缩css
 */
gulp.task('cssmin', function () {
    const f = filter([path.css + '/**/*.css'
            , '!' + path.css + '/zTreeStyle/**'],
        {restore: true});
    return gulp.src(
        [
            path.css + '/**/*'
        ])
        .pipe(f)
        .pipe(cssver())
        .pipe(cssmin({
            advanced: false,//类型：Boolean 默认：true [是否开启高级优化（合并选择器等）]
            compatibility: 'ie7',//保留ie7及以下兼容写法 类型：String 默认：''or'*' [启用兼容模式； 'ie7'：IE7兼容模式，'ie8'：IE8兼容模式，'*'：IE9+兼容模式]
            keepBreaks: false,//类型：Boolean 默认：false [是否保留换行]
            keepSpecialComments: '*'
            //保留所有特殊前缀 当你用autoprefixer生成的浏览器前缀，如果不加这个参数，有可能将会删除你的部分前缀
        }))
        .pipe(rename(function (path) {
            //path.basename += ".min";
            path.extname = ".css";
        }))
        .pipe(rev())
        .pipe(f.restore)
        .pipe(gulp.dest(path.static_build_path + "/resources/css"))
        .pipe(rev.manifest())
        .pipe(gulp.dest(path.static_build_path + "/rev/css"))
        ;
});

/** 检查js */
gulp.task('lint', function () {
    return gulp.src(
        [
            path.js + '/**/*.js'
        ]
    )
    .pipe(jshint())
    .pipe(jshint.reporter('default'))
});


/** 压缩js文件 */
gulp.task('js', function () {
    const f = filter([path.js + '/**/*.js','!' + path.js +'/tools/**'],
        {restore: true});
    return gulp.src(
        [
            path.js + '/**/*',
        ]
    )
    .pipe(f)
    .pipe(uglify())
    .pipe(rev())
    .pipe(f.restore)
    .pipe(gulp.dest(path.static_build_path + "/resources/js"))
    .pipe(rev.manifest())
    .pipe(gulp.dest(path.static_build_path + "/rev/js"));
});

/**
 * 静态文件替换
 */
gulp.task('rev', function () {
    return gulp.src(
        [path.static_build_path + '/rev/**/*.json',
            path.base + '/**/*.ftl'])
        .pipe(revCollector({
            replaceReved: true,
        }))
        .pipe(gulp.dest(path.base));
});


/**
 * 删除原始文件
 */
gulp.task('del', function () {
    return gulp.src(
        [path.js,
        path.css],
        {read: false}).pipe(clean());
});


/**
 * 复制打包后文件
 */
gulp.task('copy',  function() {
    return gulp.src(path.static_build_path + '/resources/**/*')
        .pipe(gulp.dest(path.resources));
});

/**
 * 重新压缩war包
 * */
gulp.task('zip', function() {
    return gulp.src(path.base + '/**/*.*')
        .pipe(zip('ROOT.war'))
        .pipe(gulp.dest(path.target));
});

gulp.task('default', function () {
    console.log("start...");
    runSequence(
        ['clean'],
        ['cssmin'],
        //['lint'],
        ['js'],
        ['rev'],
        ['del'],
        ['copy'],
        ['zip']
    )
});