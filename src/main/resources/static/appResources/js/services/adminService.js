define([ 'app'], function(app) {
	app.factory('AdminService', function($http) {
		var factory = {
			startApp : startApp
		};

		function startApp() {
			var url = "api/admin";
            return $http.get(url)
                .then(function (response) {
                    return response.data;
                });
		};

		return factory;
	});
});