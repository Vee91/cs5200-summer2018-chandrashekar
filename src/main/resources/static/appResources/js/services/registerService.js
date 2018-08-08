define([ 'app' ], function(app) {
	app.factory('RegisterService', function($http) {
		
		var factory = {
			register : register
		};

		function register(user) {
			var url = "api/register";
			return $http.post(url, user).then(function(response) {
				return response.data;
			});
		};

		return factory;
	});
});