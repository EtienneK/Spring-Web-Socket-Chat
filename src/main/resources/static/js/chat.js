$(function() {
	var ChatViewModel = {};

	ChatViewModel.channelName = CHANNEL_NAME;
	ChatViewModel.joined = ko.observable(false);
	ChatViewModel.users = ko.observableArray();

	ko.applyBindings(ChatViewModel);

	$.get('/chat/' + ChatViewModel.channelName + "/join", function() {
		$.get('/chat/' + ChatViewModel.channelName + "/users", function(userList) {
			ChatViewModel.users.push(userList);
			ChatViewModel.joined(true);
		});
	});
});