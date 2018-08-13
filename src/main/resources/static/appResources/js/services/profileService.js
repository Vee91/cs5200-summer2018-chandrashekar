define([ 'app'], function(app) {
	app.factory('ProfileService', function($http) {
		var factory = {
				profile : profile,
				update  : update
		};

		function profile() {
			var url = "api/profile";
			return $http.get(url)
			.then(function (response) {
				return response.data;
			});
		};
		
		function update(profile) {
			var url = "api/update/profile";
			return $http.post(url, profile)
			.then(function (response) {
				return response.data;
			});
		};


		return factory;
	});
});