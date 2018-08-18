define([ 'app'], function(app) {
	app.factory('SearchService', function($http) {
		var factory = {
				searchFlights : searchFlights,
				savequery : savequery
		};

		function searchFlights(query) {
			var url = "api/searchFlight";
			return $http.post(url, query).then(function(response) {
				return response.data;
			});
		};

		function savequery(flight) {
			var url = "api/saveSession";
			return $http.post(url, flight).then(function(response) {
				return response.data;
			});
		};

		return factory;
	});
});