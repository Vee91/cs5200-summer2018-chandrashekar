define([ 'app', 'loginService' ], function(app) {
	app.controller('loginController', [ '$location', 'LoginService', function($location, LoginService) {
		var vm = this;
		
		vm.login = login;
		
		function login(username, password) {
			LoginService.login(username, password)
             .then(function (response) {
             	if(response.code == 200) {
             		$location.url("/profile");
             	}
             });
		}

	} ]);
	return app;
});