define(['app'], function(app) {
	app.controller('profileController', ['$location', '$routeParams', function($location, $routeParams) {
		var vm = this;
		
		function init() {
			var username = $routeParams.username;
			if(username != undefined || username != null || username != ''){
				vm.success = "Account created successfully. Your username is "+username;
				vm.username = username;
			}
		}

		init();
	}]);
	return app;
});