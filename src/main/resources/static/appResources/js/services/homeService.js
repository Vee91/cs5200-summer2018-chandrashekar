define([ 'app'], function(app) {
	app.factory('HomeService', function($http) {
		var factory = {
			startApp : startApp,
			autocomplete : autocomplete
		};

		function startApp() {
			var url = "api/init";
            return $http.get(url)
                .then(function (response) {
                    return response.data;
                });
		};
		
		function autocomplete(query) {
			var url = "api/autocomplete/"+query;
            return $http.get(url)
                .then(function (response) {
                    return response.data;
                });
		};

		return factory;
	});
});