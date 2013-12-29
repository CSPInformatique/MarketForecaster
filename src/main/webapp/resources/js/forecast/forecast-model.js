window.Forecast = Backbone.Model.extend({
	idAttribute: "id",
	url : function() {
		var base = ctx + '/forecast';
		if (this.isNew() || this.id == null) return base + ".json";
		return base + (base.charAt(base.length - 1) == '/' ? '' : '/') + this.id + ".json";
	},
});