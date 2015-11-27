 $.addTemplateFormatter({
	 date: function(value) {
		 return $.formatDateTime('yy-mm-dd hh:ii:ss', new Date(value));
	 }
 });