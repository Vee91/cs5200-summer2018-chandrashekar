define([ 'app'], function(app) {
	app.factory('SearchService', function($http) {
		var factory = {
				searchFlights : searchFlights
		};
		
		function searchFlights(query) {
			var url = "api/searchFlight";
			return $http.post(url, query).then(function(response) {
				return response.data;
			});
		};

		return factory;
	});
});