$.addTemplateFormatter({
	moduleStatus : function(value) {
		if (value == 0) {
			return "禁用";
		} else if (value == 1) {
			return "启用";
		}
		return "N/A";
	}
});

$("#addModuleForm").submit(function() { 
	$(this).ajaxSubmit({
		type: "post",
		dataType: "json",
		success: function(data) {
			$("#addModuleTemplate").modal("hide");
			var pageId = $("#pageId").val();
			fillModuleTable(pageId);
		},
		error: function(data) {
			alert(data);
		}
	});
	return false;
});

$('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
	var ul = $(e.target).closest("ul");
	var pageId = ul.data("page_id");
	var target = $(e.target).attr("href");
	if (target == "#plugin_manage") {
		fillModuleTable(pageId);
	}
});

$("#editModuleTemplate").on(
		"show.bs.modal",
		function(event) {
			var tr = $(event.relatedTarget).closest('tr');
			var id = tr.data("id");
			var pageId = tr.data("page_id");
			var pluginId = tr.data("plugin_id");
			var name = tr.data("name");
			var status = tr.data("status");
			var modal = $(this);
			modal.find(".modal-content form").attr("action",
					pageId + "/modules/" + id);
			modal.find(".modal-body input[name='name']").val(name);
			modal.find(".modal-body select[name='pluginId']").val(pluginId);
			modal.find(".modal-body select[name='pluginId']").attr("disabled",
					"disabled");
			modal.find(".modal-body select[name='status']").val(status);
		});

$("#editModuleForm").submit(function() {
	$(this).ajaxSubmit({
		type : "post",
		dataType : "json",
		success : function(data) {
			$("#editModuleTemplate").modal("hide");
			var pageId = $("#pageId").val();
			fillModuleTable(pageId);
		},
		error : function(data) {
			alert(data);
		}
	});
	return false;
});

function fillModuleTable(pageId) {
	$.getJSON(CONTEXT_PATH + "/pages/" + pageId + "/modules", function(data) {
		$("#modules_table tbody").loadTemplate(
				CONTEXT_PATH + "/templates/modules.html", data, {
					success : deleteModuleCallback
				});
	});
}

function deleteModuleCallback() {
	$(".deleteModuleLink").bind("click", function(event) {
		var result = confirm("确认删除吗？");
		if (! result) {
			return;
		}
		var tr = $(event.target).closest('tr');
		var id = tr.data("id");
		var pageId = tr.data("page_id");
		jQuery.ajax({
			url : pageId + "/modules/" + id,
			type : "DELETE",
			success : function(data) {
				fillModuleTable(pageId);
			}
		});
	});
}

$("#bindData100Template").on(
		"show.bs.modal",
		function(event) {
			var div = $(event.relatedTarget).closest(".panel-collapse");
			var moduleId = div.data("moduleid");
			var pluginId = div.data("pluginid");
			var modal = $(this);
			modal.find(".modal-content form").attr("action",
					CONTEXT_PATH + "/modules/" + moduleId  + "/data");
			modal.find(".modal-body input[name='pluginId']").val(pluginId);
			modal.find(".modal-body input[name='moduleId']").val(moduleId);
		});

$("#bindData100Form").submit(function() { 
	$(this).ajaxSubmit({
		type: "post",
		dataType: "json",
		success: function(data) {
			var moduleId = $("input[name='moduleId']").val();
			var pluginId = $("input[name='pluginId']").val();
			$("#bindData100Form")[0].reset();
			$("#bindData100Template").modal("hide");
			fillBindedData(moduleId, pluginId);
		},
		error: function(data) {
			alert(data);
		}
	});
	return false;
});

/*$(".bindedDataLink").bind("click", function(event) {
	
});*/
$(".collapse").on("show.bs.collapse", function(event) {
	var div = $(event.target);
	var moduleId = div.data("moduleid");
	$.getJSON(CONTEXT_PATH + "/modules/" + moduleId+ "/data", function(data) {
		var html = $("#data100").loadTemplate(CONTEXT_PATH + "/templates/data100.html", data);
		console.log(html);
	});
});

$(".collapse").on("shown.bs.collapse", function(event) {
	var div = $(event.target);
	var moduleId = div.data("moduleid");
	var pluginId = div.data("pluginid");
	callAfterfillBindedData(moduleId, pluginId);
});

function fillBindedData(moduleId, pluginId) {
	$.getJSON(CONTEXT_PATH + "/modules/" + moduleId+ "/data", function(data) {
		var html = $("#data100").loadTemplate(CONTEXT_PATH + "/templates/data100.html", data);
		console.log(html);
		callAfterfillBindedData(moduleId, pluginId);
	});
}

function callAfterfillBindedData(moduleId, pluginId) {
	$(".del-md-btn").bind("click", function(event) {
		var mdId = $(event.target).data("id");
		jQuery.ajax({
			url : CONTEXT_PATH + "/modules/" + moduleId + "/data/" + mdId,
			type : "DELETE",
			success : function(data) {
				fillBindedData(moduleId, pluginId);
			}
		});
	});
	$(".edit-md-btn").bind("click", function(event) {
		$("#editBindedData100Template").modal("show");
		var et = $("#editBindedData100Template");
		var a = $(event.target);
		et.find(".modal-content form").attr("action",
				CONTEXT_PATH + "/modules/" + moduleId  + "/data/"  + a.data("id") );
		et.find("input[name=moduleId]").val(a.data("moduleid"));
		et.find("input[name=pluginId]").val(a.data("pluginid"));
		et.find("input[name=name]").val(a.data("name"));
		et.find("input[name=pic]").val(a.data("pic"));
		et.find("input[name=url]").val(a.data("url"));
		et.find("textarea[name=desc]").val(a.data("desc"));
	});
}

$("#editBindedData100Form").submit(function() { 
	$(this).ajaxSubmit({
		type: "post",
		dataType: "json",
		success: function(data) {
			var et = $("#editBindedData100Template");
			var moduleId = et.find("input[name=moduleId]").val();
			var pluginId = et.find("input[name=pluginId]").val();
			$("#editBindedData100Form")[0].reset();
			$("#editBindedData100Template").modal("hide");
			fillBindedData(moduleId, pluginId);
		},
		error: function(data) {
			alert(data);
		}
	});
	return false;
});