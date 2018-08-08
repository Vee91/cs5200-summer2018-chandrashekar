require.config({
	baseUrl : '',
	// paths: maps ids with paths (no extension)
	paths : {
		'jquery' : 'extResources/js/jquery-3.2.1.min',
		'angular' : 'extResources/js/angular.min',
		'domReady' : 'extResources/js/domready',
		'angularRoute' : 'extResources/js/angular-route',
		'angularSanitize' : 'extResources/js/angular-sanitize',
		'bootstrap' : 'extResources/js/bootstrap.min',
		'uibootstrap' : 'extResources/js/ui-bootstrap-tpls-0.9.0',
		'uibootstrap_upd' : 'extResources/js/ui-bootstrap-0.13.4',
		'routes' : 'appResources/js/routes',
		'app' : 'appResources/js/app',
		'homeController' : 'appResources/js/controllers/homeController',
		'homeService': 'appResources/js/services/homeService',
		'loginController' : 'appResources/js/controllers/loginController',
		'loginService': 'appResources/js/services/loginService',
		'registerController' : 'appResources/js/controllers/registerController',
		'registerService': 'appResources/js/services/registerService',
		'profileController' : 'appResources/js/controllers/profileController',
		'adminController' : 'appResources/js/controllers/adminController',
		'adminService': 'appResources/js/services/adminService',
	},
	// shim: makes external libraries reachable
	shim : {
		'angular' : {
			'deps' : [ 'jquery' ],
			'exports' : 'angular'
		},
		'bootstrap' : {
			'deps' : [ 'jquery' ]
		},
		'uibootstrap' : {
			'deps' : [ 'jquery', 'angular' ]
		},
		'angularRoute' : {
			'deps' : [ 'angular' ]
		},
		'angularSanitize' : {
			'deps' : [ 'angular' ]
		}
	}
});

// Angular Bootstrap
require([ 'angular', 'app' ], function(angular, app) {
	angular.element().ready(function() {
		// bootstrap the app manually
		require([ 'domReady' ], function() {
			angular.bootstrap(document, [ 'Travel' ]);
		});

	});
});