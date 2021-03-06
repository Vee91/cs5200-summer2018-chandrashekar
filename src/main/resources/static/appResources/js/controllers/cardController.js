define(['jquery', 'angular', 'app', 'homeService', 'cardService'], function(jquery, angular, app) {
	app.controller('cardController', [ '$location', '$route', '$mdDialog', 'HomeService', 'CardService', function($location, $route, $mdDialog, HomeService, CardService) {
		var vm           = this;
		vm.loggedin    = false;
		vm.user        = null;
		vm.admin       = null;
		vm.logout      = logout;
		vm.cards         = null; 
		vm.selectedCard  = null;
		vm.update        = update;
		vm.addCard       = addCard;
		vm.add     = null;
		vm.deleteCard    = deleteCard; 
		vm.addNewCard    = addNewCard; 
		vm.cancel    = cancel; 

		function init() {

			$('.burger, .overlay').click(function(){
				$('.burger').toggleClass('clicked');
				$('.overlay').toggleClass('show');
				$('nav').toggleClass('show');
				$('body').toggleClass('overflow');
			});

			HomeService.startApp()
			.then(function (response) {
				vm.loggedin = response.loggedin;
				var role = response.message;
				if(role == 'ROLE_USER')
					vm.user = true;
				if(role == 'ROLE_ADMIN')
					vm.admin = true;
			});

			CardService.findAllCards().then(function (response) {
				if(response.code == 200) {
					vm.cards = response.success.cards;
				}
			}, function (error) {
				vm.message = "Something went wrong while fetching your cards. Please try again later"
			});

		}

		function update(ev, card) {
			var confirm = $mdDialog.confirm()
			.title('Update')
			.textContent('Are you sure you want to update the card?')
			.ariaLabel('update')
			.targetEvent(ev)
			.ok('Update')
			.cancel('Cancel');

			$mdDialog.show(confirm).then(function() {
				CardService.update(card).then(function (response) {
					if(response.code == 200) {
						vm.message = "Card updated successfully";
					}
					else if(response.code == 400) {
						vm.message = response.message;
					}
				}, function () {
					vm.message = "Something went wrong while updating your cards. Please try again later"
				});
			}, function() {

			});
		}

		function deleteCard(ev, cardId) {
			var confirm = $mdDialog.confirm()
			.title('Delete')
			.textContent('Are you sure you want to delete the card?')
			.ariaLabel('delete')
			.targetEvent(ev)
			.ok('Delete')
			.cancel('Cancel');

			$mdDialog.show(confirm).then(function() {
				CardService.deleteCard(cardId).then(function (response) {
					if(response.code == 200) {
						$route.reload();
					}
					else if(response.code == 400) {
						vm.message = response.message;
					}
				}, function () {
					vm.message = "Something went wrong while Deleting your card. Please try again later"
				});
			}, function() {

			});
		}

		function logout() {
			HomeService.logout().then(function (response) {
				if(response == 200) {
					$location.path('/login');
				}
			});
		}

		function addCard() {
			vm.selectedCard = null;
			vm.add = true;
		}

		function cancel() {
			vm.add = false;
		}

		function addNewCard(ev, card) {
			var confirm = $mdDialog.confirm()
			.title('Add Card')
			.textContent('Are you sure you want to add this card?')
			.ariaLabel('add')
			.targetEvent(ev)
			.ok('Add')
			.cancel('Cancel');

			$mdDialog.show(confirm).then(function() {
				console.log('add');
				console.log(card);
				CardService.addNewCard(vm.cards.cardNumber, vm.cards.fullName, vm.cards.expMonth, vm.cards.expYear, vm.cards.securityCode).then(function (response) {
					if(response.code == 200) {
						$route.reload();
					}
					else if(response.code == 400) {
						vm.message = response.message;
					}
				}, function () {
					vm.message = "Something went wrong while adding your card. Please try again later"
				});
			}, function() {
				vm.add = false;
			});
		}


		init();

	} ]);
	return app;
});