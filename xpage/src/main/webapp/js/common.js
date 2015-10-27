define('#common', function(require, exports, module) {
	var handlebars = require('handlebars');
	
	exports.template = function(html) {
		html = html.replace(/\\#/g, '#');
		return handlebars.compile(html);
	};
});