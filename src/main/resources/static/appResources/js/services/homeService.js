define([ 'app'], function(app) {
	app.factory('HomeService', function($http) {
		var factory = {
				startApp : startApp,
				autocomplete : autocomplete,
				saveSearch : saveSearch,
				setSearchQuery : setSearchQuery,
				getSearchQuery : getSearchQuery
		};

		var searchQuery = {};

		function setSearchQuery(f, t, d ,r, n){
			searchQuery.origin = f;
			searchQuery.destination = t;
			searchQuery.departureDate = d;
			searchQuery.returnDate = r;
			searchQuery.nonstop = n;
		}
		function getSearchQuery(){
			return searchQuery;
		}

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

		function saveSearch(from, to, dDate, rDate, nonstop) {
			setSearchQuery(from, to, dDate, rDate, nonstop);
		};

		return factory;
	});
});