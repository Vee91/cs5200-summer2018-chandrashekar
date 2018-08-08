define([ 'app' ], function(app) {
	app.factory('LoginService', function($http) {
		
		var factory = {
			login : login
		};

		function login(username, password) {
			var apiUrl = "api/login";
			return $http.post(apiUrl,{
				"username":username,
				"password" : password}).then(function(response) {
				console.log(response);
				return response.data;
			});
		};

		return factory;
	});
});