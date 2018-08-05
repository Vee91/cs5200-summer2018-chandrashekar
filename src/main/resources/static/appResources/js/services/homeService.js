define([ 'app', 'jqueryui'], function(app) {
	app.factory('HomeService', function($http) {
		var factory = {
			startApp : startApp
		};

		function startApp() {
			var url = "api/hello/object";
            return $http.get(url)
                .then(function (response) {
                    return response.data;
                });
		};

		return factory;
	});
});