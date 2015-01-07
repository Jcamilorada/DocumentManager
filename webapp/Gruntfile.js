// Gruntfile with the configuration of grunt-express and grunt-open. No livereload yet!
module.exports = function(grunt) {

    require('matchdep').filterDev('grunt-*').forEach(grunt.loadNpmTasks);

    grunt.initConfig({

        express: {
            all: {
                options: {
                    port: 9000,
                    hostname: "0.0.0.0",
                    bases: ['app'],
                    livereload: true
                }
            }
        },

        watch: {
            all: {
                files: ['**/*.html'],
                options: {
                    livereload: true
                }
            }
        },

        open: {
            all: {
                path: 'http://localhost:<%= express.all.options.port%>'
            }
        },

        bowercopy: {
            options: {
                srcPrefix: 'bower_components'
            },
            scripts: {
                options: {
                    destPrefix: 'app/js/vendor'
                },
                files: {
                    'angular/angular.min.js': 'angular/angular.min.js',
                    'angular-route/angular-route.min.js': 'angular-route/angular-route.min.js'
                }
            }
        },

        concat: {
            angular: {
                files: {
                    'app/js/vendor/angular/angular.min.js': ['bower_components/angular/angular.min.js']
                }
            },
            angular_route: {
                files: {
                    'app/js/vendor/angular-route/angular-route.min.js': ['bower_components/angular-route/angular-route.min.js']
                }
            },
        }
    });

    grunt.registerTask('server', [
    'bowercopy',
    'concat',
    'express',
    'open',
    'watch'
    ]);
};
