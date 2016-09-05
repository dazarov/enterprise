module.exports = function (grunt) {

  var pkg = grunt.file.readJSON('package.json'),

      /*
      * Object which contains information about all compiled CSS files
      */
      lessFls = {},

      bowerJsComponents = [],
      appJS = [];

  // less files for all application.css
  lessFls[pkg.directories.build + "/css/application.css"] = [
    pkg.directories.build + "/less/application.less",
  ];

  // bower js libs starts here
  bowerJsComponents = [
    'angular/angular.js',
    'bower-angular-route-master/angular-route.js',
    'angular-ui-router/release/angular-ui-router.js',
    'angular-resource/angular-resource.js',
    'angular-bootstrap/ui-bootstrap.min.js',
    'angular-ui-grid/ui-grid.min.js',
    'angular-bootstrap/ui-bootstrap-tpls.min.js'
  ];
  // bower js libs ends here

  // application js files start here
  appJS = [
    'index.js',
    'views/HomeCtrl.js',
    'views/blog/BlogCtrl.js',
    'views/post/PostCtrl.js'
  ];
  // application js files ends here

  // grunt config start here
  grunt.initConfig({

    /*
    * CLEAN
    * remove build folder
    */
    clean: {
      dev: ["build"]
    },

    // COPY
    copy: {
      dev: {
        files: [
          // views folder
          {expand: true, cwd: pkg.directories.source, src: ['views/**'], dest: pkg.directories.build},
          // js folder
          {expand: true, cwd: pkg.directories.source, src: ['js/**'], dest: pkg.directories.build},
          // modules folder
          {expand: true, cwd: pkg.directories.source, src: ['modules/**'], dest: pkg.directories.build},
          // less folder
          {expand: true, cwd: pkg.directories.source, src: ['less/**'], dest: pkg.directories.build},
          // img folder
          {expand: true, cwd: pkg.directories.source, src: ['img/**'], dest: pkg.directories.build},
          // html files
          {expand: true, cwd: pkg.directories.source, src: ['*.html'], dest: pkg.directories.build},
          // js files
          {expand: true, cwd: pkg.directories.source, src: ['*.js'], dest: pkg.directories.build}
        ],
        options: {
          processContentExclude: ['**/*.{png,gif,jpg,ico,psd}'],
          
          process: function (content, srcpath) {
            //grunt.log.writeln('######## Process file - '+srcpath);
            /*
            * look at each file and replace ###{propert}###
            */

            if (srcpath.indexOf('.html') === -1) {
              return content;
            }

            var bowerScripts = '',
                applicationScripts = '';

            // replace version
            content = content.replace(/###version###/g, (new Date()).getTime());
            //content = content.replace(/###version###/g, pkg.version);

            // replace bowerJsComponents
            bowerScripts += '<script type="text/javascript" src="bower-components/';
            bowerScripts += bowerJsComponents.join('"></script>\n<script type="text/javascript" src="bower-components/');
            bowerScripts+= '"></script>';
            content = content.replace(/###bowerJsComponents###/g, bowerScripts);

            // replace appJS
            applicationScripts += '<script type="text/javascript" src="';
            applicationScripts += appJS.join('"></script>\n<script type="text/javascript" src="');
            applicationScripts+= '"></script>';
            content = content.replace(/###appJS###/g, applicationScripts);

            return content;
          }
        }
      },
      afterdev: {
        files: [
          // bower-components
          {expand: true, cwd: pkg.directories.source, src: ['bower-components/**'], dest: pkg.directories.build},
          {expand: true, src: ['source/bower-components/bootstrap/fonts/**'], flatten: true, dest: pkg.directories.build + '/fonts'},
          {expand: true, src: ['source/bower-components/components-font-awesome/fonts/**'], flatten: true, dest: pkg.directories.build + '/fonts'}

        ]
      }
    },

    // LESS
    less: {
      dev: {
        files: lessFls
      }
    },

  });
  // grunt config end here

  grunt.loadNpmTasks('grunt-contrib-clean');
  grunt.loadNpmTasks('grunt-contrib-copy');
  grunt.loadNpmTasks('grunt-contrib-less');

  // Default task(s).
  grunt.registerTask('default', [
    'clean:dev',
    'copy:dev',
    'copy:afterdev',
    'less:dev'
  ]);
};
