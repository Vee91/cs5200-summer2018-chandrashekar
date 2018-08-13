define([ 'app'], function(app) {
	app.factory('CardService', function($http) {
		var factory = {
				findAllCards : findAllCards,
				update : update,
				deleteCard : deleteCard
		};

		function findAllCards() {
			var url = "api/card";
			return $http.get(url)
			.then(function (response) {
				return response.data;
			});
		};
		
		function update(card) {
			var url = "api/card/update";
			return $http.post(url, card)
			.then(function (response) {
				return response.data;
			});
		};
		
		function deleteCard(id) {
			var url = "api/card/delete";
			return $http.post(url, id)
			.then(function (response) {
				return response.data;
			});
		};

		return factory;
	});
});