define([ 'app'], function(app) {
	app.factory('SearchService', function($http) {
		var factory = {
				searchFlights : searchFlights,
				savequery : savequery,
				setQuery : setQuery,
				getQuery : getQuery
		};

		var query = null;

		function setQuery(f){
			query = f;
		};

		function getQuery(){
			return query;
		};

		function searchFlights(query) {
			var url = "api/searchFlight";
			return $http.post(url, query).then(function(response) {
				return response.data;
			});
		};

		function savequery(flight) {
			setQuery(flight);
		};

		return factory;
	});
});