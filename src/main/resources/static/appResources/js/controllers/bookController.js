define(['jquery', 'angular', 'app', 'homeService', 'searchService'], function(jquery, angular, app) {
	app.controller('bookController', [ '$location', 'HomeService', 'SearchService', function($location, HomeService, SearchService) {
		var vm = this;
		vm.selectedIndex = 0;
		vm.size = 0;
		vm.passenger1 = null;
		vm.passenger2 = null;
		vm.passenger3 = null;
		
		function init() {
			vm.query = SearchService.getQuery();
			$('.burger, .overlay').click(function(){
				$('.burger').toggleClass('clicked');
				$('.overlay').toggleClass('show');
				$('nav').toggleClass('show');
				$('body').toggleClass('overflow');
			});
		}
		
		init();

	}]);
	return app;
});