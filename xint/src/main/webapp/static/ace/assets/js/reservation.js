function passed_review(reservation_id, review_remark) {
    if (is_nan(reservation_id)) {
        return;
    }
    var data = {
        ids: reservation_id,
        review_remark: review_remark
    };
    var type = "POST";
    $.ajax({
        url: '/v1/reservations/review',
        data: data,
        type: type,
        cache: false,
        dataType: 'json',
        beforeSend: show_loading,
        complete: hide_loading,
        success: function (data) {
            var succ_num = data.data.succ_num;
            var err_num = data.data.err_num;
            var err_msg = data.data.err_msg;
            var alert_msg = "成功:" + succ_num + ", 失败:" + err_num + "<br>";
            if (err_msg.length > 0) {
                for (var i = 0; i < err_msg.length; i++) {
                    alert_msg += err_msg[i] + "<br>";
                }
            }

            bootbox.confirm(alert_msg, function (r) {
                gridReload();
            });
        },
        error: function (data) {
            var dataObj = eval("(" + data.responseText + ")");
            bootbox.alert(dataObj.data.message);

        }
    });
}


function to_batch_freeze(reservation_id, freeze_remark) {
    if (is_nan(reservation_id)) {
        return;
    }
    var data = {
        reservation_ids: reservation_id,
        review_remark: freeze_remark
    };
    var type = "POST";
    $.ajax({
        url: '/v1/reservations/freeze',
        data: data,
        type: type,
        cache: false,
        dataType: 'json',
        beforeSend: show_loading,
        complete: hide_loading,
        success: function (data) {
            if (data.result == 0) {
                bootbox.alert("修改成功！");
                gridReload();
            } else {
                bootbox.alert(data.data.message);
            }
        },
        error: function (data) {
            var dataObj = eval("(" + data.responseText + ")");
            bootbox.alert(dataObj.data.message);
        }
    });

}


function to_batch_unfreeze(reservation_id, unfreeze_remark) {
    if (is_nan(reservation_id)) {
        return;
    }
    var data = {
        reservation_ids: reservation_id,
        review_remark: unfreeze_remark
    };
    var type = "POST";
    $.ajax({
        url: '/v1/reservations/unfreeze',
        data: data,
        type: type,
        cache: false,
        dataType: 'json',
        beforeSend: show_loading,
        complete: hide_loading,
        success: function (data) {
            if (data.result == 0) {
                bootbox.alert("修改成功！");
                gridReload();
            } else {
                bootbox.alert(data.data.message);
            }
        },
        error: function (data) {
            var dataObj = eval("(" + data.responseText + ")");
            bootbox.alert(dataObj.data.message);
        }
    });

}