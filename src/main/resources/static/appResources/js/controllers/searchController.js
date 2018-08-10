define(['jquery', 'angular', 'app', 'homeService', 'searchService'], function(jquery, angular, app) {
	app.controller('searchController', [ '$location', 'HomeService', 'SearchService', function($location, HomeService, SearchService) {
		var vm = this;
		vm.message = null;
		vm.tiles = null;
		function init() {
			vm.query = HomeService.getSearchQuery();
			vm.tiles = [1,2,4];
			$('.burger, .overlay').click(function(){
				$('.burger').toggleClass('clicked');
				$('.overlay').toggleClass('show');
				$('nav').toggleClass('show');
				$('body').toggleClass('overflow');
			});
			
			SearchService.searchFlights(vm.query).then(function (response) {
				if(response.code == 200) {
					console.log(response.success);
				} else {
					vm.message = response.message;
				}
			});
			
		}

		init();

	}]);
	return app;
});