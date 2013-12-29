$(document).ready(function(){
	var user = new User();
	user.fetch({async : false});
	
	user = user.toJSON();
});