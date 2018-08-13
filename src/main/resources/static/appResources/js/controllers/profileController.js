define(['jquery', 'app', 'homeService', 'profileService'], function(jquery, app) {
	app.controller('profileController', ['$location', '$route', '$mdDialog', '$routeParams', 'HomeService', 'ProfileService', function($location, $route, $mdDialog, $routeParams, HomeService, ProfileService) {
		var vm = this;
		vm.profile = null;
		vm.updateProfile = updateProfile;

		function init() {
			var username = $routeParams.username;
			if(username != undefined || username != null || username != ''){
				vm.success = "Account created successfully. Your username is "+username;
				vm.username = username;
			}

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

			ProfileService.profile().then(function (response) {
				console.log(response);
				vm.profile = response.success.profile;
			}, function () {
				vm.message = "Error loading your profile. Please try again later."
			});
		}
		
		function updateProfile(ev, profile) {
			var confirm = $mdDialog.confirm()
			.title('Update')
			.textContent('Are you sure you want to update your profile?')
			.ariaLabel('update')
			.targetEvent(ev)
			.ok('Update')
			.cancel('Cancel');

			$mdDialog.show(confirm).then(function() {
				ProfileService.update(profile).then(function (response) {
					if(response.code == 200) {
						vm.success = "Profile updated successfully";
						vm.message = null;
					}
					else if(response.code == 400) {
						vm.message = response.message;
						vm.success = null;
					}
				}, function () {
					vm.message = "Something went wrong while updating your profile. Please try again later";
				});
			}, function() {
				
			});
		}

		init();
	}]);
	return app;
});