define(['jquery', 'angular', 'app', 'homeService', 'searchService'], function(jquery, angular, app) {
	app.controller('searchController', [ '$location', 'HomeService', 'SearchService', function($location, HomeService, SearchService) {
		var vm = this;
		vm.message = null;
		vm.tiles = null;
		vm.results = null;
		vm.info = null;
		vm.loading = true;
		vm.book = book;
		
		function init() {
			vm.query = HomeService.getSearchQuery();
			$('.burger, .overlay').click(function(){
				$('.burger').toggleClass('clicked');
				$('.overlay').toggleClass('show');
				$('nav').toggleClass('show');
				$('body').toggleClass('overflow');
			});
			
			SearchService.searchFlights(vm.query).then(function (response) {
				if(response.code == 200) {
					vm.results = response.success;
					vm.info = response.infoMap;
					vm.loading = false;
				} else {
					vm.message = response.message;
					vm.loading = false;
				}
			});
			
		}
		
		function book(flight) {
			console.log(flight);
			SearchService.savequery(flight);
			$location.path('/book');
		}

		init();

	}]);
	return app;
});