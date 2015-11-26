 
/**
 * 
 * 
 * 错误信息显示
 * 
 * 依赖 jquery,bootbox
 * 
 * @param obj
 * @param msg
 */
function ace_error(obj,msg){
	 bootbox.alert(msg);
	 $("html,body").animate({scrollTop:obj.offset().top-100},100);
	 original_border = obj.css("border");
	 obj.css({"border":"1px solid red"});
	 window.setTimeout(function(){
		 obj.css({"border":original_border});
	 },3000);
}


function ace_show(obj){
	 $("html,body").animate({scrollTop:obj.offset().top-100},100);
	 obj.show();
	 window.setTimeout(function(){
		 obj.hide();
	 },3000);
}

function ace_error_tip(obj){
    title = obj.attr("my_title");
    ace_error(obj,title);
}

function is_null(jquery_obj){
    msg = jquery_obj.val();
    if(msg == "" || msg == undefined){
        ace_error_tip(jquery_obj);
        return true;
    }else{
        return false;
    }
}

function is_nan(msg){
    if(msg == "" || msg == undefined || msg == "undefined"){
       return true;
    }else{
       return false;
    }
}

function check_num(value){  
    var reg = /^\d+$/;
    if( value.constructor === String ){
        var re = value.match( reg );
        return true;
    }
    return false;
}


function cmp_date(s_date,e_date){
     a = Date.parse(s_date);
     b = Date.parse(e_date);
     if(a>b){
        return 1;
     }else if(a<b){
         return -1
     }else{
         return 0;
     }

}

//增加身份证验证
function isIdCardNo(num) {
    var factorArr = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
    var parityBit = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
    var varArray = new Array();
    var intValue;
    var lngProduct = 0;
    var intCheckDigit;
    var intStrLen = num.length;
    var idNumber = num;
    // initialize
    if ((intStrLen != 15) && (intStrLen != 18)) {
        return false;
    }
    // check and set value
    for (i = 0; i < intStrLen; i++) {
        varArray[i] = idNumber.charAt(i);
        if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
            return false;
        } else if (i < 17) {
            varArray[i] = varArray[i] * factorArr[i];
        }
    }

    if (intStrLen == 18) {
        //check date
        var date8 = idNumber.substring(6, 14);
        if (isDate8(date8) == false) {
            return false;
        }
        // calculate the sum of the products
        for (i = 0; i < 17; i++) {
            lngProduct = lngProduct + varArray[i];
        }
        // calculate the check digit
        intCheckDigit = parityBit[lngProduct % 11];
        // check last digit
        if (varArray[17] != intCheckDigit) {
            return false;
        }
    }
    else {        //length is 15
        //check date
        var date6 = idNumber.substring(6, 12);
        if (isDate6(date6) == false) {
            return false;
        }
    }
    return true;
}

function isDate6(sDate){
    if(!/^[0-9]{6}$/.test(sDate)){
        return false;
    }
    var year,month,day;
    year=sDate.substring(0,4);
    month=sDate.substring(4,6);
    if(year<1700||year>2500) return false;
    if(month<1||month>12) return false;
    return true;
}

function isDate8(sDate){
    if(!/^[0-9]{8}$/.test(sDate)){
         return false;
    }
    var year,month,day;
    year=sDate.substring(0,4);
    month=sDate.substring(4,6);
    day=sDate.substring(6,8);
    var iaMonthDays=[31,28,31,30,31,30,31,31,30,31,30,31]
    if(year<1700||year>2500) return false;
    if(((year%4==0)&&(year%100!=0))||(year%400==0)) iaMonthDays[1]=29;
    if(month<1||month>12)return false;
    if(day<1||day>iaMonthDays[month-1])return false;
    return true;
}


//网址正则表达式验证
function IsURL(str_url){
    var strRegex = /^((https|http|ftp|rtsp|mms|rtmp)?:\/\/)?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?(([0-9]{1,3}\.){3}[0-9]{1,3}|([0-9a-z_!~*'()-]+\.)*([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\.[a-z]{2,6})(:[0-9]{1,4})?((\/?)|(\/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+\/?)$/;
    var re=new RegExp(strRegex);
    //re.test()
    if (re.test(str_url)){
        return (true);
    }else{
        return (false);
    }
}

function refresh(){
	window.location.reload();
}	


function checkMobile(s){  
	var regu =/^[1][0-9][0-9]{9}$/;
	var re = new RegExp(regu);
	if (re.test(s)) {
	return true;
	}else{
	return false;
 }
}

function isEmail(str) { 
       var myReg = /^[-._A-Za-z0-9]+@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/; 
       if (myReg.test(str)){
             return true; 
        }  
         return false; 
}


function isDouble(){
	var myReg = /^\d+\.\d+$/;
	 if (myReg.test(str)){
         return true; 
    }  
     return false; 
}

function tojump(url){
	 window.location.href=url;
}

function is_sku(sku){
    return true;
/*	var myReg =/^[0-9]{13}$/;
	 if (myReg.test(sku)){
        return true; 
   }  
    return false;*/
}


function is_num(num){
    if(num == ""){
         return false;
    }
    if(isNaN(num)){
        return false;
    }
    return true;
}

function no_minus(num){
    if(is_num(num)){
        if(parseFloat(num) <0){
            return false;
        }else{
            return true;
        }
    }else{
        return false;
    }
}
function get_text_len(str_v){
   return str_v.length;
}


function aceSwitch( cellvalue, options, cell ) {
    setTimeout(function(){
        $(cell) .find('input[type=checkbox]')
                .wrap('<label class="inline" />')
            .addClass('ace ace-switch ace-switch-5')
            .after('<span class="lbl"></span>');
    }, 0);
}

function pickDate( cellvalue, options, cell ) {
    setTimeout(function(){
        $(cell) .find('input[type=text]')
                .datepicker({format:'yyyy-mm-dd' , autoclose:true});
    }, 0);
}


function style_edit_form(form) {
    //enable datepicker on "sdate" field and switches for "stock" field
    form.find('input[name=sdate]').datepicker({format:'yyyy-mm-dd' , autoclose:true})
        .end().find('input[name=stock]')
              .addClass('ace ace-switch ace-switch-5').wrap('<label class="inline" />').after('<span class="lbl"></span>');

    //update buttons classes
    var buttons = form.next().find('.EditButton .fm-button');
    buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
    buttons.eq(0).addClass('btn-primary').prepend('<i class="icon-ok"></i>');
    buttons.eq(1).prepend('<i class="icon-remove"></i>')

    buttons = form.next().find('.navButton a');
    buttons.find('.ui-icon').remove();
    buttons.eq(0).append('<i class="icon-chevron-left"></i>');
    buttons.eq(1).append('<i class="icon-chevron-right"></i>');
}

function style_delete_form(form) {
    var buttons = form.next().find('.EditButton .fm-button');
    buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
    buttons.eq(0).addClass('btn-danger').prepend('<i class="icon-trash"></i>');
    buttons.eq(1).prepend('<i class="icon-remove"></i>')
}

function style_search_filters(form) {
    form.find('.delete-rule').val('X');
    form.find('.add-rule').addClass('btn btn-xs btn-primary');
    form.find('.add-group').addClass('btn btn-xs btn-success');
    form.find('.delete-group').addClass('btn btn-xs btn-danger');
}
function style_search_form(form) {
    var dialog = form.closest('.ui-jqdialog');
    var buttons = dialog.find('.EditTable')
    buttons.find('.EditButton a[id*="_reset"]').addClass('btn btn-sm btn-info').find('.ui-icon').attr('class', 'icon-retweet');
    buttons.find('.EditButton a[id*="_query"]').addClass('btn btn-sm btn-inverse').find('.ui-icon').attr('class', 'icon-comment-alt');
    buttons.find('.EditButton a[id*="_search"]').addClass('btn btn-sm btn-purple').find('.ui-icon').attr('class', 'icon-search');
}

function beforeDeleteCallback(e) {
    var form = $(e[0]);
    if(form.data('styled')) return false;

    form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
    style_delete_form(form);

    form.data('styled', true);
}

function beforeEditCallback(e) {
    var form = $(e[0]);
    form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
    style_edit_form(form);
}


function styleCheckbox(table) {
/**
    $(table).find('input:checkbox').addClass('ace')
    .wrap('<label />')
    .after('<span class="lbl align-top" />')


    $('.ui-jqgrid-labels th[id*="_cb"]:first-child')
    .find('input.cbox[type=checkbox]').addClass('ace')
    .wrap('<label />').after('<span class="lbl align-top" />');
*/
}



function updateActionIcons(table) {
    /**
    var replacement =
    {
        'ui-icon-pencil' : 'icon-pencil blue',
        'ui-icon-trash' : 'icon-trash red',
        'ui-icon-disk' : 'icon-ok green',
        'ui-icon-cancel' : 'icon-remove red'
    };
    $(table).find('.ui-pg-div span.ui-icon').each(function(){
        var icon = $(this);
        var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
        if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
    })
    */
}

//replace icons with FontAwesome icons like above
function updatePagerIcons(table) {
    var replacement =
    {
        'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
        'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
        'ui-icon-seek-next' : 'icon-angle-right bigger-140',
        'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
    };
    $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
        var icon = $(this);
        var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

        if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
    })
}

function enableTooltips(table) {
    $('.navtable .ui-pg-button').tooltip({container:'body'});
    $(table).find('.ui-pg-div').tooltip({container:'body'});
}


try{
    $(function(){
         $("#close_page_btn").on("click",function(){close_page()});
         $("#histort_btn").on("click",function(){window.history.go(-1);});
    });

}catch (e){

}




function close_page(){
 if (navigator.userAgent.indexOf("MSIE") > 0) {
  if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
   window.opener = null;
   window.close();
  } else {
   window.open('', '_top');
   window.top.close();
  }
 }
 else if (navigator.userAgent.indexOf("Firefox") > 0) {
  window.location.href = 'about:blank ';
 } else {
  window.opener = null;
  window.open('', '_self', '');
  window.close();
 }
}


jQuery.fn.center = function () {
    this.css("position","absolute");
    this.css("top", ( $(window).height() - this.height() ) / 2+$(window).scrollTop() + "px");
    this.css("left", ( $(window).width() - this.width() ) / 2+$(window).scrollLeft() + "px");
    return this;
}


function show_loading(){
    $("#loading").center();
    $("#loading").show();
}


function show_progress(){
     $("#progress_00000 .progress-val").eq(0).html("0%");
    $("#progress_00000 .progress-in").eq(0).css("width",'0%');
    $("#progress_00000").center();
    $("#progress_00000").show();
}

function hide_progress(){
    $("#progress_00000").hide();
}

function hide_loading(){
    $("#loading").hide();
}

function ace_error_info(str){
    return "<span style='color: red;'>ERORR,"+str+"</span>"
}


function ace_sucess_info(str){
    return "<span style='color: darkgreen;'>SUCCESS,"+str+"</span>"
}

function common_ajax_error_handel(data){

    try{hide_loading();}catch (e){}

    try{
      var dataObj = eval("(" + data.responseText + ")");
        bootbox.alert(dataObj.data.message);
    }catch (e){
        bootbox.alert(ace_error_info("未知错误！"));
    }

    //var dataObj = eval("(" + data.responseText + ")");
    //bootbox.alert(dataObj.data.message);
}

try{
    $.extend($.jgrid.defaults, {
        loadError: function(xhr,status,error){
            common_ajax_error_handel(xhr);
        }
    });
}catch (e){

}

Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    }
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}



//jQueryajax全局设置同步
jQuery.ajaxSettings.async = false;



/***
*
*功能：客户端cookie函数相关操作组件,标准第三方组件
*使用方法：
* @设置cokie.
* @example $.cookie('the_cookie', 'the_value', { expires: 7, path: '/', domain: 'jquery.com', secure: true });
* @desc Create a cookie with all available options.
* @example $.cookie('the_cookie', 'the_value');
* @desc Create a session cookie.
* @example $.cookie('the_cookie', null);
*
*/

jQuery.cookie = function(name, value, options) {
   if (typeof value != 'undefined') {
       options = options || {};
       if (value === null) {
           value = '';
           options.expires = -1;
       }
       var expires = '';
       if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
           var date;
           if (typeof options.expires == 'number') {
               date = new Date();
               date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
           } else {
               date = options.expires;
           }
           expires = '; expires=' + date.toUTCString(); // use expires attribute, max-age is not supported by IE
       }
       var path = options.path ? '; path=' + (options.path) : '';
       var domain = options.domain ? '; domain=' + (options.domain) : '';
       var secure = options.secure ? '; secure' : '';
       document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
   } else {
       var cookieValue = null;
       if (document.cookie && document.cookie != '') {
           var cookies = document.cookie.split(';');
           for (var i = 0; i < cookies.length; i++) {
               var cookie = jQuery.trim(cookies[i]);
               // Does this cookie string begin with the name we want?
               if (cookie.substring(0, name.length + 1) == (name + '=')) {
                   cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                   break;
               }
           }
       }
       return cookieValue;
   }
};



function grid_jump_url_with_from(id,jump_url){
    var params =  $("#"+id).jqGrid('getGridParam');
    var page =  params.page;
    var rowNum = params.rowNum;
    var url = params.url;
    if(url == "/v1/catalogs/ajax"){
        window.open(jump_url);
    }else{
        window.open(jump_url + "from="+$.base64.encode(params.url+"&page="+page+"&rows="+rowNum));
    }

}

function load_grid_ajax_url(from_base64){
    return $.base64.decode(from_base64);
}
function get_url_param(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
}


function get_url_str_param(url,name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = url.match(reg);
        if (r != null) return unescape(r[2]); return null;
}

function replaceParamVal(paramName,replaceWith) {
    var oUrl = this.location.href.toString();
    var re=eval('/('+ paramName+'=)([^&]*)/gi');
    var nUrl = oUrl.replace(re,paramName+'='+replaceWith);
    this.location = nUrl;
}


function url_param_del(url, ref) {
    var str = "";
    if (url.indexOf('?') != -1)
        str = url.substr(url.indexOf('?') + 1);
    else
        return url;
    var arr = "";
    var returnurl = "";
    var setparam = "";
    if (str.indexOf('&') != -1) {
        arr = str.split('&');
        for (i in arr) {
            if (arr[i].split('=')[0] != ref) {
                returnurl = returnurl + arr[i].split('=')[0] + "=" + arr[i].split('=')[1] + "&";
            }
        }
        return url.substr(0, url.indexOf('?')) + "?" + returnurl.substr(0, returnurl.length - 1);
    }
    else {
        arr = str.split('=');
        if (arr[0] == ref)
            return url.substr(0, url.indexOf('?'));
        else
            return url;
    }
}
