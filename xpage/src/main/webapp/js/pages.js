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

$("#bindData100Form").submit(function() { 
	var url = CONTEXT_PATH + "/pages/" + $("#pageId").val() + "/data";
	$(this).ajaxSubmit({
		url: url,
		type: "post",
		dataType: "json",
		success: function(data) {
			$("#bindData100Template").modal("hide");
			var pageId = $("#pageId").val();
			fillModuleTable(pageId);
		},
		error: function(data) {
			alert(data);
		}
	});
	return false;
});