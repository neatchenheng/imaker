define('#plugin', function(require, exports, module) {
	var common = require('common');
	var editPluginTemplate = common.template($('#editPluginTemplate').html());
	
	
	$("#editPluginTemplate").on("show.bs.modal",function(event) {
		var tr = $(event.relatedTarget).closest('tr');
		var id = tr.data("id");
		var pluginId = tr.data("plugin_id");
		var name = tr.data("name");
		var deviceTypes = tr.data('de')
		var model = $(this);
		model.find(".modal-content form").attr("action","../plugins/" + tr.data("id"));
		model.find(".modal-body input[name='pluginId']").val(tr.data("plugin_id"));
		model.find(".modal-body input[name='name']").val(tr.data("name"));
		var dts = tr.data("device_types");
		jQuery.each(dts.split(","),function(i,v) {
			if (v != "") {
				model.find(".modal-body input[name='deviceTypes'][value=" + v +"]").attr("checked",true);
			}
		});
	});
	
});