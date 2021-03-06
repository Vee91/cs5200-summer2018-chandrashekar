define([ 'app'], function(app) {
	app.factory('BookingService', function($http) {
		var factory = {
				book : book,
				findAllCards : findAllCards,
				bookWithCard : bookWithCard,
				getQuery : getQuery
		};


		function book(p, c, q) {
			var url = "api/book/itinerary";
			var body = { "passengers" : p, 
					"card" : c,
					"itinerary" : q};
			return $http.post(url, body)
			.then(function (response) {
				return response.data;
			});
		};

		function findAllCards() {
			var url = "api/card";
			return $http.get(url)
			.then(function (response) {
				return response.data;
			});
		};

		function bookWithCard(p, c, q) {
			var url = "api/book/itinerary/card";
			var body = { "passengers" : p, 
					"cardId" : c,
					"itinerary" : q};
			return $http.post(url, body)
			.then(function (response) {
				return response.data;
			});
		};

		function getQuery() {
			var url = "api/getSession";
			return $http.get(url)
			.then(function (response) {
				return response.data;
			});
		}


		return factory;
	});
});