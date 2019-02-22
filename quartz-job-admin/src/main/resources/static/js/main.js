/*-----------------------------------------------------------------------------
| 默认设置
------------------------------------------------------------------------------*/
var defaultSetting = {
		G_NOTIFY_FLUSH_SWITCH : true, // 定时刷新通知开关
		G_NOTIFY_FLUSH_RATE : 300000, // 定时刷新通知频率
		G_HOME_TABS_LIMIT : 10  //首页TAB上限
};

/*-----------------------------------------------------------------------------
 | 全局变量
 ------------------------------------------------------------------------------*/
var G_V = {};

function HgWin(option) {
	var defaults = {
		width : 600,
		height : 400,
		modal : true,
		minimizable : false,
		maximizable : true,
		collapsible : true,
		resizable : false,
		iconCls : "m-icon-win"
	};
	var _$winObj = null;
	if ($("#" + option.id).length > 0) {
		_$winObj = $("#" + option.id);
	} else {
		_$winObj = $("<div id='" + option.id + "'></div>");
	}
	var win = _$winObj.window($.extend({}, defaults, option || {})).window(
		"open");
	if (option.url){
		// if(option.url.indexOf("?") > -1 && option.url.indexOf("=") > -1){
		// 	option.url = option.url + "&time="+new Date().getTime();
		// }else {
		// 	option.url = option.url + "?time="+new Date().getTime();
		// }
		win.window("refresh", G_CTX_PATH + option.url);
	}
	return win;
}

function HgDialog(option) {
	var defaults = {
		width : 600,
		height : 400,
		modal : true,
		minimizable : false,
		maximizable : false,
		collapsible : false,
		iconCls : "m-icon-win"
	};
	var _$digObj = $("<div></div>");
	if (option.id)
		_$winObj = $("#" + option.id);
	var win = _$winObj.window($.extend({}, defaults, option || {})).window(
			"open");
	if (option.url)
		win.window("refresh", G_CTX_PATH + option.url);
	return win;
}

function HgTab(title, url, type, icon) {
	if (typeof icon == 'undefined') {
		icon = "m-icon-door";
	}
	var tab = $("#tab");
	if (tab.tabs("exists", title)) {
		tab.tabs("select", title);
	} else {
		tab.tabs("add", {
			title : title,
			closable : true,
			icon : icon
		});
		url = G_CTX_PATH + url;
		if (typeof type == 'undefined' || type == 'iframe') {
			var subtab = tab.tabs('getSelected');
			var content = '<iframe scrolling="auto" frameborder="0"  src="'
					+ url + '" style="width:100%;height:100%;"></iframe>';
			tab.tabs('update', {
				tab : subtab,
				options : {
					content : content
				}
			});
		} else {
			tab.tabs('getSelected').panel("refresh", url);
		}
	}
}

/*----------------------------------------------------------------------------
 |  页面函数
 ------------------------------------------------------------------------------*/
function calPageTotalNum(totalCount, pageSize) {
	var totalNum = 0;
	if (totalCount % pageSize == 0) {
		totalNum = totalCount / pageSize;
	} else {
		totalNum = parseInt(totalCount / pageSize) + 1;
	}
	return totalNum;
}

function showLoadMsg(targetId) {
	$("#" + targetId).append(
			"<div id='" + targetId + "_loadMsg'><img  src='" + G_CTX_PATH
					+ "/static/images/icons/loading.gif' />数据加载中...</div>");
}

function rmLoadMsg(targetId) {
	$("#" + targetId + "_loadMsg").remove();
}

var G_VAL = {

}

// Hg 构造器
var Hg = {

}

Hg.ok = function(msg, callback) {
	$.messager.alert("", "&nbsp;&nbsp;<img src='" + G_CTX_PATH
			+ "/static/images/icons/ok.png' />&nbsp;&nbsp;" + msg, null,
			callback);
}

Hg.alert = function(msg, callback) {
	$.messager.alert("", "&nbsp;&nbsp;<img src='" + G_CTX_PATH
			+ "/static/images/icons/note_error.gif' />&nbsp;&nbsp;" + msg, null,
			callback);
}
$.messager.ok = function(msg, callback) {
	$.messager.alert("提示", "&nbsp;&nbsp;<img src='" + G_CTX_PATH
			+ "/static/images/icons/ok.png' />&nbsp;&nbsp;" + msg, null,
			callback);
}

Hg.fixTableHeight = function(tableId, hasParentPanel) {
	var height = $("body").height() - $("#" + tableId).offset().top - 2 - 30;
	if (hasParentPanel)
		height = height - 30;
	$("#" + tableId).height(height);
}

Hg.initFreshPanel = function(tableId) {
	$("#" + tableId).parent().panel({
		fit : true,
		tools : [ {
			iconCls : 'icon-reload',
			handler : function() {
				$("#tab").tabs('getSelected').panel("refresh");
			}
		} ]
	});
}

// ajax获取json数据
Hg.getJson = function(path, data, callback) {
	$.getJSON(G_CTX_PATH + path + "?etc=" + new Date().getTime(), data,
			callback);
       
	
}

// post请求（大数据请求json无法处理时,用此方法）,POST 请求功能以取代复杂 $.ajax
// 。请求成功时可调用回调函数。如果需要在出错时执行函数，请使用 $.ajax。
Hg.post = function(path, data, callback) {
	$.post(G_CTX_PATH + path + "?etc=" + new Date().getTime(), data, callback);
}

// 防止表单重复提交
Hg.refRepeatSubmit = function(formId) {
	$("#" + formId).append("<input type='hidden' name='submitToken'/>");
	Hg.getJson("/refSubmitToken", {}, function(data) {
		$("#" + formId).find("[name='submitToken']").val(data.data);
	});
}

// 刷新提交token
Hg.refreshSubmitToken = function(formId) {
	Hg.getJson("/refSubmitToken", {}, function(data) {
		$("#" + formId).find("[name='submitToken']").val(data.data);
	});
}

// 提交遮罩
$.blockUI.defaults = $.extend({}, $.blockUI.defaults, {
	message : "<img src='" + G_CTX_PATH
			+ "/static/images/icons/loading1.gif'/>",
	css : {
		border : 'none',
		padding : '15px',
		width : "20px",
		top : "0px",
		left : "0px",
		opacity : 1
	},
	overlayCSS : {
		backgroundColor : '#ddd',
		opacity : 0.6,
		cursor : 'wait'
	}
});

// 拓展treegrid
$.extend($.fn.treegrid.methods, {
	cascadeCheckBox : function(target, idField) {
		var checkBoxObj = $(target).treegrid("getPanel").find("[node-id]")
				.find("input[type='checkbox']");
		checkBoxObj.change(function() {
			var id = $(this).parents("[node-id]").attr("node-id");
			var checkParentNode = function(currentObj, id) {
				var parentNode = $(target).treegrid("getParent", id);
				if (parentNode) {
					var parentId = parentNode[idField];
					if (currentObj.is(":checked")) {
						var parentObj = $(target).treegrid("getCheckBox",
								parentId).attr("checked", "checked");
						checkParentNode(parentObj, parentId);
					}

				}
			};
			checkParentNode($(this), id);

		});
	},
	getCheckBox : function(target, id) {
		var checkBoxObj = $(target).treegrid("getPanel").find(
				"[node-id='" + id + "']").find("input[type='checkbox']");
		return checkBoxObj;
	},
	getCheckedIds : function(target) {
		var authIdsAry = [];
		var panelObj = $(target).treegrid("getPanel");
		panelObj.find("[node-id]").each(function(i) {
			if ($(this).find("input[type='checkbox']:checked").length > 0) {
				authIdsAry.push($(this).attr("node-id"));
			}
		});
		return authIdsAry.join(",");
	}

});

// 拓展datagrid
$
		.extend(
				$.fn.datagrid.methods,
				{
					getCheckBox : function(target, indexId) {
						var checkBoxObj = $(target).datagrid("getPanel").find(
								"[datagrid-row-index='" + indexId + "']").find(
								"input[type='checkbox']");
						return checkBoxObj;
					},
					getCheckedIds : function(target, idFieldName) {
						var idsAry = [];
						var panelObj = $(target).datagrid("getPanel");
						panelObj
								.find("[datagrid-row-index]")
								.each(
										function(i) {
											if ($(this)
													.find(
															"input[type='checkbox']:checked").length > 0) {
												idsAry.push($(this).find(
														"[field='"
																+ idFieldName
																+ "']").text());
											}
										});
						return idsAry.join(",");
					},
					getNativeCheckedIds : function(target, idFieldName) {
						var checkedRows = $(target).datagrid("getChecked");
						var idsAry = [];
						for (var i = 0; i < checkedRows.length; i++) {
							idsAry.push(checkedRows[i][idFieldName]);
						}
						return idsAry.join(",");
					},
					showCellTip : function(target, fieldName) {
						$(target).datagrid(
								{
									onLoadSuccess : function() {
										var cellObj = $(target).datagrid(
												"getPanel")
												.find(
														"td[field='"
																+ fieldName
																+ "']");
										cellObj.each(function(i) {
											var content = $(this).text();
											$(this).tooltip({
												content : content
											});
										});

									}
								});

					}
				});

// 拓展validate
$.validator
		.addMethod(
				"mobile",
				function(value, element) {
					var length = value.length;
					var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(14[0-9]{1})|18[0-9]{1})+\d{8})$/;//手机号码验证
					var telephoneRule = /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;//固话号码
					return this.optional(element)
							|| (length == 11 && mobile.test(value)) || (telephoneRule.test(value));
				}, "手机号码或固定电话号码格式错误，固定电话号码格式：0[区号]-[号码]-[分机号]");
$.validator.addMethod(
		"multiple",
		function(value, element) {
			var length = value.length;
			return length > 0;
		}, "必填");

// 设置validator的默认属性
$.validator.setDefaults({
	wrapper : "div",
	errorPlacement : function(error, element) {
		error.appendTo(element.parents("td:eq(0)"));
	}
});

Hg.showErrorMsg = function(formId, errorJson) {
	// 系统错误
	if (!errorJson.match("^\{(.+:.+,*){1,}\}$")) {
		$.messager.alert("操作失败", errorJson);
	} else {// 校验错误
		var errorResult = $.parseJSON(errorJson);
		if (errorResult.field) {
			var field = errorResult.field;
			var message = errorResult.message;
			var fidldElm = $("#" + formId + " [name='" + field + "']");
			fidldElm.after("<label class='error' for='" + field
					+ "' generated='true'>" + message + "</label>");
			fidldElm.keydown(function() {
				$(this).parent().find("label[for='" + field + "']").remove();
			});
		}
	}
}

Hg.showVaildMsg = function(formId, errorJson, win) {

	// 销毁tip对象
	var bindDestoryTip = function(vaild) {
		if (win) {
			win.window({
				onClose : function() {
					vaild.destory(vaild.tip);
				}
			});
		}
	};

	var errorResult = $.parseJSON(errorJson);
	// 数组---------------------
	if ($.isArray(errorResult)) {
		$.each(errorResult, function() {
			if (this.field) {
				bindDestoryTip($(
						"#" + formId + " input[name='" + this.field + "']")
						.showVaildTip(this.message));
			}

		});
	} else {
		if (errorResult.field) {
			bindDestoryTip($(
					"#" + formId + " input[name='" + errorResult.field + "']")
					.showVaildTip(errorResult.message));
		}
	}

}

$.fn.extend({
	showVaildTip : function(message) {
		var aryTipObj = [];
		this.each(function() {
			var tip = $(this).tooltip({
				position : 'right',
				content : "<span>" + message + "</span>",
				onShow : function() {
					$(this).tooltip('tip').css({
						color : "#000",
						borderColor : "#CC9933",
						backgroundColor : "#FFFFCC"
					});
				}
			}).tooltip("show");
			aryTipObj.push(tip);

		});
		var destoryTipObj = function(aryTipObj) {
			for (var i = 0; i < aryTipObj.length; aryTipObj++) {
				aryTipObj[i].tooltip("destroy");
			}
		};
		return {
			tip : aryTipObj,
			destory : destoryTipObj
		};

	}
});



var userCenter = {

};

/*-----------------------------------------------------------------------------
 |  公共函数
 ------------------------------------------------------------------------------*/
String.format = function() {
	if (arguments.length == 0)
		return null;

	var str = arguments[0];
	for (var i = 1; i < arguments.length; i++) {
		var re = new RegExp('\\{' + (i - 1) + '\\}', 'gm');
		str = str.replace(re, arguments[i]);
	}
	return str;
}

// 字符串连接
function StringBuffer(str) {
	this.tmp = new Array();
}
StringBuffer.prototype.append = function(value) {
	this.tmp.push(value);
	return this;
}
StringBuffer.prototype.clear = function() {
	tmp.length = 1;
}
StringBuffer.prototype.toString = function() {
	return this.tmp.join('');
}

Array.prototype.find = function(val) {
	for (var i = 0, len = this.length; i < len; i++) {
		if (this[i] == val) {
			return this[i];
		}
	}
	return null;
}

function setCookie(name,value)
{
	var Days = 30; //此 cookie 将被保存 30 天
	var exp  = new Date();    //new Date("December 31, 9998");
	exp.setTime(exp.getTime() + Days*24*60*60*1000);
	document.cookie = name + "="+ escape(value) +";expires="+ exp.toGMTString();
}
function getCookie(name)
{
	var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
	if(arr != null) return unescape(arr[2]); return null;
}
function delCookie(name)
{
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval=getCookie(name);
	if(cval!=null) document.cookie=name +"="+cval+";expires="+exp.toGMTString();
}


/*-----------------------------------------------------------------------------
|  oa
------------------------------------------------------------------------------*/
var Oa = {};
Oa.mrkDepartAreaCascade = function(departObj, AreaObj, callback,
		isNeedNullOption) {
	var crtAreaOption = function(departId) {
		if (isNeedNullOption) {
			AreaObj.append("<option value=''>-----请选择-----</option>");
		}
		if (!departId) {
			if (callback) {
				callback();
				callback = null;
			}
			return false;
		}
		//有特殊方法需要请选择的value='0'
		if(departId == 0 || departId == '0'){
			departId = '';
		}
		Hg.getJson("/oaOrgInfo/getAreaList", {
			departId : departId
		}, function(data) {
			if (data.data) {
				var list = data.data;
				if (!isNeedNullOption) {
					AreaObj.empty();
				}
				for (var i = 0; i < list.length; i++) {
					AreaObj.append("<option value='" + list[i].departId + "'>"
							+ list[i].departName + "</option>");
				}
			}
			if (callback) {
				callback();
				callback = null;
			}

		});
	};
	departObj.change(function() {
		AreaObj.empty();
		crtAreaOption($(this).val());
	});
	AreaObj.empty();
	crtAreaOption(departObj.val());

};
Oa.mrkDepartPrincipaCascade = function(departObj, principaObj, callback,
		isNeedNullOption) {
	var crtPrincipaOption = function(departId) {
		if (isNeedNullOption) {
			principaObj.append("<option value=''>-----请选择-----</option>");
		}
		if (!departId) {
			if (callback) {
				callback();
				callback = null;
			}
			return false;
		}
		Hg.getJson("/oaOrgInfo/getPrincipaList", {
			departId : departId
		}, function(data) {
			if (data.data) {
				var list = data.data;
				if (!isNeedNullOption) {
					principaObj.empty();
				}
				for (var i = 0; i < list.length; i++) {
					principaObj.append("<option value='" + list[i].userId
							+ "'>" + list[i].userName + "</option>");
				}
			}
			if (callback) {
				callback();
				callback = null;
			}
		});
	};

	departObj.change(function() {
		principaObj.empty();
		crtPrincipaOption($(this).val());
	});
	principaObj.empty();
	crtPrincipaOption(departObj.val());

};

Oa.mrkDepartUserCascade = function(departObj, userObj, callback,
		isNeedNullOption) {
	var crtUserOption = function(departId) {
		if (isNeedNullOption) {
			userObj.append("<option value=''>-----请选择-----</option>");
		}
		if (!departId) {
			if (callback) {
				callback();
				callback = null;
			}
			return false;
		}
		Hg.getJson("/oaOrgInfo/getUserListByDepartId", {
			departId : departId
		}, function(data) {
			if (data.data) {
				var list = data.data;
				if (!isNeedNullOption) {
					userObj.empty();
				}
				for (var i = 0; i < list.length; i++) {
					userObj.append("<option value='" + list[i].userId + "'>"
							+ list[i].userName + "</option>");
				}
			}
			if (callback) {
				callback();
				callback = null;
			}
		});
	};
	departObj.change(function() {
		userObj.empty();
		crtUserOption($(this).val());
	});
	userObj.empty();
	crtUserOption(departObj.val());

};

Oa.mrkNormalDepartUserCascade = function(departObj, userObj, callback,
		isNeedNullOption) {
	var crtUserOption = function(departId) {
		if (isNeedNullOption) {
			userObj.append("<option value=''>-----请选择-----</option>");
		}
		if (!departId) {
			if (callback) {
				callback();
				callback = null;
			}
			return false;
		}
		Hg.getJson("/oaOrgInfo/getNormalUserListByDepartId", {
			departId : departId
		}, function(data) {
			if (data.data) {
				var list = data.data;
				if (!isNeedNullOption) {
					userObj.empty();
				}
				for (var i = 0; i < list.length; i++) {
					userObj.append("<option value='" + list[i].userId + "'>"
							+ list[i].userName + "</option>");
				}
			}
			if (callback) {
				callback();
				callback = null;
			}
		});
	};
	departObj.change(function() {
		userObj.empty();
		crtUserOption($(this).val());
	});
	userObj.empty();
	crtUserOption(departObj.val());

};
Oa.TASK_KEY_RECRUIT_DEPT_LEADER_AUDIT = "deptLeaderAudit";
Oa.TASK_KEY_RECRUIT_ADJUST_RECRUIT = "adjustRecruit";
Oa.TASK_KEY_RECRUIT_HR_LEADER_AUDIT = "hrLeaderAudit";
Oa.TASK_KEY_RECRUIT_TOP_LEADER_AUDIT = "topLeaderAudit";
Oa.TASK_KEY_RECRUIT_HR_SUBMIT_INFO = "hrSubmitInfo";
Oa.TASK_KEY_RECRUIT_HR_LEADER_COMMAND = "hrLeaderCommand";
Oa.TASK_KEY_DIARY_LEADER_APPROVAL = "leaderApproval";

Oa.TASK_KEY_ESPRO_DEPT_AUDIT = "deptLeaderAudit";
Oa.TASK_KEY_ESPRO_BUS_AUDIT = "busLeaderAudit";
Oa.TASK_KEY_ESPRO_TOP_AUDIT = "topLeaderAudit";
Oa.TASK_KEY_ESPRO_RE_FILL = "reFill";


Oa.PRO_TYPE_DEVELOP = 1;//系统开发
Oa.PRO_TYPE_INTEG = 2;//系统集成
Oa.PRO_TYPE_MAINTAIN = 3;//系统运维
Oa.PRO_TYPE_IT = 4;//it维保
Oa.PRO_TYPE_SHOW = 5;//展示
Oa.PRO_TYPE_ADV_SERVICE = 6;//咨询服务
Oa.PRO_TYPE_PEOPLE_OUTPUT = 7;//人员外包
Oa.PRO_TYPE_PEOPLE_FIX = 8;//人员长固
Oa.PRO_TYPE_GD = 9;//过单
Oa.PRO_TYPE_HARDWARE_DELIV = 10;//硬件供货
Oa.PRO_TYPE_SOFTWARE_DELIV = 11;//软件供货

Oa.mrkDiaryDetailHtml = function(data) {
	var content = new StringBuffer();
	content.append("<table class='need-border'>");
	content
			.append("    <tr><td colspan='2' align='center' style='border-bottom:1px solid #000;'><b>日报</b></td></tr>");
	content.append("    <tr><td><b>填报人:</b></td><td>" + data.userName
			+ "</td></tr>");
	content.append("    <tr><td><b>工作项:</b></td><td>" + data.workItemStr
			+ "</td></tr>");
	if (data.workItem == 1 || (data.workItem == 2 && data.opportunityId == null)) {
		content.append("    <tr><td><b>项目:</b></td><td>" + data.projectName
				+ "</td></tr>");
		content.append("    <tr><td><b>项目阶段:</b></td><td>" + data.workTypeStr
				+ "</td></tr>");
	}
	if (data.workItem == 2 && data.projectId == null) {
		content.append("    <tr><td><b>商机:</b></td><td>" + data.opportunityName
				+ "</td></tr>");
	}
	if (data.workItem == 4) {
		content.append("    <tr><td><b>假期类型:</b></td><td>"
				+ data.holidayTypeStr + "</td></tr>");
	}
	content.append("    <tr><td><b>日期:</b></td><td>" + data.diaryDate
			+ "</td></tr>");
	content.append("    <tr><td><b>工时:</b></td><td>" + data.workDuration
			+ "</td></tr>");
	content.append("    <tr><td><b>工作内容:</b></td><td>" + data.workContent
			+ "</td></tr>");
	content.append("</table>");
	return "<div>" + content.toString() + "</div>";
};

Oa.mrkRecruitDetailHtml = function(data) {
	var content = new StringBuffer();
	content.append("<table class='need-border'>");
	content
			.append("    <tr><td colspan='2' align='center' style='border-bottom:1px solid #000;'><b>招聘</b></td></tr>");
	content.append("    <tr><td><b>提出人:</b></td><td>" + data.userName
			+ "</td></tr>");
	content.append("    <tr><td><b>部门:</b></td><td>" + data.departName
			+ "</td></tr>");
	content.append("    <tr><td><b>项目:</b></td><td>" + data.projectName
			+ "</td></tr>");
	content.append("    <tr><td><b>内部订单号:</b></td><td>" + data.internalOrderId
			+ "</td></tr>");
	content.append("    <tr><td><b>期望到岗日期:</b></td><td>" + data.expectEntryDate
			+ "</td></tr>");
	content.append("    <tr><td><b>职位:</b></td><td>" + data.position
			+ "</td></tr>");
	content.append("    <tr><td><b>需求人数:</b></td><td>" + data.numbers
			+ "</td></tr>");
	content.append("    <tr><td><b>申请原因:</b></td><td>" + data.applyReason
			+ "</td></tr>");
	content.append("</table>");
	return "<div>" + content.toString() + "</div>";
}

Oa.mrkProjectDetailHtml = function(data) {
	var content = new StringBuffer();
	content.append("<table class='need-border'>");
	content
			.append("    <tr><td colspan='2' align='center' style='border-bottom:1px solid #000;'><b>项目</b></td></tr>");
	content.append("    <tr><td><b>发起人:</b></td><td>" + data.crtUserName
			+ "</td></tr>");
	content.append("    <tr><td><b>业务部门:</b></td><td>" + data.departName
			+ "</td></tr>");
	if (data.areaName != null) {
		content.append("    <tr><td><b>业务域:</b></td><td>" + data.areaName
				+ "</td></tr>");
	}	
	content.append("    <tr><td><b>项目负责人:</b></td><td>" + data.userName
			+ "</td></tr>");
	content.append("    <tr><td><b>项目名称:</b></td><td>" + data.projectName
			+ "</td></tr>");
	content.append("</table>");
	return "<div>" + content.toString() + "</div>";
}

Oa.mrkProjectCompleteDetailHtml = function(data) {
	var content = new StringBuffer();
	content.append("<table class='need-border'>");
	content
			.append("    <tr><td colspan='2' align='center' style='border-bottom:1px solid #000;'><b>结项信息</b></td></tr>");
	content.append("    <tr><td><b>项目名称:</b></td><td>" + data.pro.projectName
			+ "</td></tr>");
	content.append("    <tr><td><b>结项说明:</b></td><td>" + data.proCom.remark
			+ "</td></tr>");
	content.append("</table>");
	return "<div>" + content.toString() + "</div>";
	
};

Oa.mrkLeaveProcessDetailHtml = function(data) {
	var content = new StringBuffer();
	content.append("<table class='need-border'>");
	content
			.append("    <tr><td colspan='2' align='center' style='border-bottom:1px solid #000;'><b>请假申请信息</b></td></tr>");
	content.append("    <tr><td><b>申请人:</b></td><td>" + data.userName
			+ "</td></tr>");
	content.append("    <tr><td><b>总请假天数:</b></td><td>" + data.totalDays
			+ "</td></tr>");
	content.append("    <tr><td><b>请假事由:</b></td><td>" + data.leaveReason
			+ "</td></tr>");
	content.append("    <tr><td><b>工作安排:</b></td><td>" + data.workArrange
			+ "</td></tr>");

	content.append("</table>");
	return "<div>" + content.toString() + "</div>";
	
};

Oa.mrkPurchaseProcessDetailHtml = function(data) {
	var content = new StringBuffer();
	content.append("<table class='need-border'>");
	content
			.append("    <tr><td colspan='2' align='center' style='border-bottom:1px solid #000;'><b>采购订单信息</b></td></tr>");
	content.append("    <tr><td><b>采购订单编号:</b></td><td>" + data.purchaseNo
			+ "</td></tr>");
	content.append("    <tr><td><b>需求部门:</b></td><td>" + data.departName
			+ "</td></tr>");
	content.append("    <tr><td><b>采购金额:</b></td><td>￥" + Oa.formatPriceFO(data.purchasePrice)
			+ "</td></tr>");
	content.append("    <tr><td><b>申请人:</b></td><td>" + data.crtUserName
			+ "</td></tr>");
	content.append("    <tr><td><b>申请日期:</b></td><td>" + data.applyDate
			+ "</td></tr>");
	content.append("</table>");
	return "<div>" + content.toString() + "</div>";
	
};
Oa.mrkPurchaseCancelProcessDetailHtml = function(data) {
	var content = new StringBuffer();
	content.append("<table class='need-border'>");
	content
			.append("    <tr><td colspan='2' align='center' style='border-bottom:1px solid #000;'><b>采购订单取消信息</b></td></tr>");
	content.append("    <tr><td><b>采购订单编号:</b></td><td>" + data.purchaseNo
			+ "</td></tr>");
	content.append("    <tr><td><b>申请人:</b></td><td>" + data.crtUserName
			+ "</td></tr>");
	content.append("    <tr><td><b>取消日期:</b></td><td>" + data.cancelDate
			+ "</td></tr>");
	content.append("</table>");
	return "<div>" + content.toString() + "</div>";
	
};


Oa.tipShowDiaryRecruitDetail = function(gridId) {
	$("#" + gridId).datagrid("getPanel").find("tr.datagrid-row").each(
			function() {
				var processInstanceId = $(this).find("td[field='processInstanceId']").text();
				var processDefinitionId = $(this).find("td[field='processDefinitionId']").text().split(":")[0];
				$(this).tooltip({onShow : function() {
					var _this = $(this);
					if (processInstanceId != "") {
						var url = "/oa/diary/getOne";
						if (processDefinitionId == "recruitProcess") {
							url = "/oa/recruit/getOne";
						} else if (processDefinitionId == "esProProcess") {
							url = "/oa/pro/getOne";
						} else if (processDefinitionId == "hpChangeProProcess") {
							url = "/oa/pro/getOneByHpProcessId";
						} else if (processDefinitionId == "busChangeProProcess") {
							url = "/oa/pro/getOneByBusProcessId";
						} else if (processDefinitionId == "completeProProcess") {
							url = "/workflow/completePro/getOne";
						} else if (processDefinitionId == "purchaseCancelProcess") {
							url = "/oa/purchase/getPurchaseCancel";
						} else if (processDefinitionId == "purchaseProcess") {
							url = "/oa/purchase/getOne";
						} else if (processDefinitionId == "leaveProcess") {
							url = "/oa/leave/getByProcessId";
						}
						Hg.getJson(url,{processInstanceId : processInstanceId},function(data) {
							var content ;
							if (processDefinitionId == "recruitProcess") {
								content = Oa.mrkRecruitDetailHtml(data.data);
							} else if (processDefinitionId == "esProProcess") {
								content = Oa.mrkProjectDetailHtml(data.data);
							} else if (processDefinitionId == "hpChangeProProcess") {
								content = Oa.mrkProjectDetailHtml(data.data);
							} else if (processDefinitionId == "busChangeProProcess") {
								content = Oa.mrkProjectDetailHtml(data.data);
							} else if (processDefinitionId == "completeProProcess") {
								content = Oa.mrkProjectCompleteDetailHtml(data);
							} else if (processDefinitionId == "purchaseCancelProcess") {
								content = Oa.mrkPurchaseCancelProcessDetailHtml(data.data);
							} else if (processDefinitionId == "purchaseProcess") {
								content = Oa.mrkPurchaseProcessDetailHtml(data.data);
							} else if (processDefinitionId == "leaveProcess") {
								content = Oa.mrkLeaveProcessDetailHtml(data.data);
							}
							else{
								content = Oa.mrkDiaryDetailHtml(data.data)
							}
							_this.tooltip('tip').html(content);
						});
					}
				}
			});
		});
};

Oa.showMsgRowNotSaved = function(gridId) {
	$("#" + gridId)
			.datagrid("getPanel")
			.block(
					{
						message : "<div style='float:left;'><img  src='"
								+ G_CTX_PATH
								+ "/static/images/icons/error.gif'/></div><div style='float:left;'>&nbsp;有一条数据未保存！</div>",
						overlayCSS : {
							backgroundColor : "#fff;",
							opacity : 0
						},
						css : {
							padding : '10px',
							width : "180px",
							height : "20px",
							top : "0px",
							left : "0px",
							color : "red",
							border : 'solid red 2px',
							backgroundColor : "#fff;",
							opacity : 1
						},
						fadeIn : 1000,
						timeout : 1500
					});
};

Oa.showMsgRowNotValid = function(gridId,validMsg) {
	var msg = new StringBuffer();
	msg.append("<div style='float:left;'>");
	msg.append("    <img  src='"+ G_CTX_PATH + "/static/images/icons/error.gif'/>");
	msg.append("</div>");
	msg.append("<div style='float:left;'>");
	if (validMsg) {
		msg.append("&nbsp;"+validMsg);
	} else {
		msg.append("&nbsp;有一条数据未校验通过,");
	}
	msg.append("请检查！");
	msg.append("</div>");
	$("#" + gridId)
			.datagrid("getPanel")
			.block(
					{
						message :msg.toString(),
						overlayCSS : {
							backgroundColor : "#fff;",
							opacity : 0
						},
						css : {
							padding : '10px',
							width : "220px",
							height : "20px",
							top : "0px",
							left : "0px",
							color : "red",
							border : 'solid red 2px',
							backgroundColor : "#fff;",
							opacity : 1
						},
						fadeIn : 1000,
						timeout : 1500
					});
};

Oa.showGridMsg = function(gridId, msg) {
	$("#" + gridId)
			.datagrid("getPanel")
			.block(
					{
						message : "<div style='float:left;'><img  src='"
								+ G_CTX_PATH
								+ "/static/images/icons/error.gif'/></div><div style='float:left;'>&nbsp;"
								+ msg + "</div>",
						overlayCSS : {
							backgroundColor : "#fff;",
							opacity : 0
						},
						css : {
							padding : '10px',
							width : "220px",
							height : "20px",
							top : "0px",
							left : "0px",
							color : "red",
							border : 'solid red 2px',
							backgroundColor : "#fff;",
							opacity : 1
						},
						fadeIn : 1000,
						timeout : 1500
					});
};
Oa.showGridCusErrorMsg = function(gridId,msg) {
	$("#" + gridId)
			.datagrid("getPanel")
			.block(
					{
						message : "<div style='float:left;'><img  src='"
								+ G_CTX_PATH
								+ "/static/images/icons/cancel.gif'/></div><div style='float:left;'>&nbsp;"+msg+"</div>",
						overlayCSS : {
							backgroundColor : "#fff;",
							opacity : 0
						},
						css : {
							padding : '10px',
							width : "300px",
							height : "20px",
							top : "0px",
							left : "0px",
							color : "red",
							border : 'solid red 2px',
							backgroundColor : "#fff;",
							opacity : 1
						},
						fadeIn : 1000,
						timeout : 1500
					});
};

Oa.showGridCusErrorMsgBig = function(gridId,msg,width) {
	if(!width){
		width = 330;
	}
	var b_width = width*1 +25;
	$("#" + gridId)
			.datagrid("getPanel")
			.block(
					{
						message : "<div style='float:left;'><img  src='"
								+ G_CTX_PATH
								+ "/static/images/icons/cancel.gif'/></div><div style='float:left;padding-left:5px;width:"+width+"px'>"+msg+"</div>",
						overlayCSS : {
							backgroundColor : "#fff;",
							opacity : 0
						},
						css : {
							padding : '10px',
							width : b_width,
							height : "20px",
							top : "0px",
							left : "0px",
							color : "red",
							border : 'solid red 2px',
							backgroundColor : "#fff;",
							opacity : 1
						},
						fadeIn : 1000,
						timeout : 2500
					});
};


Oa.showGridErrorMsg = function(gridId) {
	$("#" + gridId)
			.datagrid("getPanel")
			.block(
					{
						message : "<div style='float:left;'><img  src='"
								+ G_CTX_PATH
								+ "/static/images/icons/cancel.gif'/></div><div style='float:left;'>&nbsp;数据保存失败！</div>",
						overlayCSS : {
							backgroundColor : "#fff;",
							opacity : 0
						},
						css : {
							padding : '10px',
							width : "180px",
							height : "20px",
							top : "0px",
							left : "0px",
							color : "red",
							border : 'solid red 2px',
							backgroundColor : "#fff;",
							opacity : 1
						},
						fadeIn : 1000,
						timeout : 1500
					});
};

Oa.showInnerPanelOkMsg = function(obj){
	obj.block(
			{
				message : "<div style='float:left;'><img  src='"
						+ G_CTX_PATH
						+ "/static/images/icons/accept.gif'/></div><div style='float:left;'>&nbsp;数据保存成功</div>",
				overlayCSS : {
					backgroundColor : "#fff;",
					opacity : 0
				},
				css : {
					padding : '10px',
					width : "180px",
					height : "20px",
					top : "0px",
					left : "0px",
					color : "green",
					border : 'solid green 2px',
					backgroundColor : "#fff;",
					opacity : 1
				},
				fadeIn : 1000,
				timeout : 1500
			});
};

Oa.showGridOkMsg = function(gridId) {
	$("#" + gridId)
			.datagrid("getPanel")
			.block(
					{
						message : "<div style='float:left;'><img  src='"
								+ G_CTX_PATH
								+ "/static/images/icons/accept.gif'/></div><div style='float:left;'>&nbsp;数据保存成功</div>",
						overlayCSS : {
							backgroundColor : "#fff;",
							opacity : 0
						},
						css : {
							padding : '5px',
							width : "150px",
							height : "15px",
							top : "0px",
							left : "0px",
							color : "green",
							border : 'solid green 2px',
							backgroundColor : "#fff;",
							opacity : 1
						},
						fadeIn : 1000,
						timeout : 1500
					});
};

Oa.getGridComboTextbox = function(gridId, field) {
	var obj = $("#" + gridId).datagrid("getPanel").find(
			"td[field=" + field + "] .textbox-text");
	return obj;
};
Oa.dateboxButtons = $.extend([], $.fn.datebox.defaults.buttons);
Oa.dateboxButtons.splice(1, 0, {
	text : '清空',
	handler : function(target) {
		$(target).datebox("setValue", "");
		$(this).closest("div.combo-panel").panel("close");
	}
});

Oa.formatPrice = function(val, row) {
	if (!val) {
		return "";
	}
	if (!isNaN(val)) {
		return formatCurrency(val);
	}
	return val;
};
//datagrid之外的引用，如果空，则返回0
Oa.formatPriceFO = function(val) {
	if (!val) {
		return "0";
	}
	if (!isNaN(val)) {
		return formatCurrency(val);
	}
	return val;
};

//保留2位小数
Oa.formatPriceTo2 = function(val){
	if (!val) {
		return "0.00";
	}
	if (!isNaN(val)) {
		return formatCurrency(val);
	}
	return val;
}
Oa.percentFmter = function(val,row) {
	var width = (row.complete/row.plan)*100;
	var html = "<div style='background-color:#cccccc;'><div style='background-color:#0099cc;width:"+width+"%;'>&nbsp;</div></div>"
	return html;
};

Oa.gridBindPupupTextArea = function(gridObj,field,index, width, height) {
	var fieldEditor = gridObj.datagrid('getEditor', {index : index,field : field});  
	$(fieldEditor.target).textbox("textbox").click(
			function() {
				var width = $(this).width() + 20;
				var top = $(this).offset().top + 23;
				var left = $(this).offset().left;
				var val = $(fieldEditor.target).textbox("getValue");
				if (!width)
					width = width + "px";
				if (!height)
					height = "100px";
				var textHtml = "<textarea style='width:" + width + ";height:"
						+ height + ";' class='pupText'>" + val + "</textarea>";
				$.blockUI({
					message : textHtml,
					showOverlay : false,
					css : {
						position : 'absolute',
						padding : '0px',
						width : width,
						height : height,
						top : top,
						left : left,
						border : null
					}
				});

				$(".pupText").blur(function() {
					$.unblockUI();
				});
				$(".datagrid-body").scroll(function(){
					$.unblockUI();
				});

				$(".pupText").keyup(function() {
					var text = $(this).val();
					$(fieldEditor.target).textbox("setValue", text);
					//Oa.updateGridCellTip(gridId,field,index,text);
				});
			});
};
Oa.tableBindPupupTextArea = function(tableId,inputName, width, height) {
	var inputTarget = $("#" + tableId + " [name='" + inputName + "']");  
	inputTarget.textbox().textbox("textbox").click(
			function() {
				var width = $(this).width() + 20;
				var top = $(this).offset().top + 23;
				var left = $(this).offset().left;
				var val = inputTarget.textbox("getValue");
				if (!width)
					width = width + "px";
				if (!height)
					height = "100px";
				var textHtml = "<textarea style='width:" + width + ";height:"
						+ height + ";' class='pupText'>" + val + "</textarea>";
				$.blockUI({
					message : textHtml,
					showOverlay : false,
					css : {
						position : 'absolute',
						padding : '0px',
						width : width,
						height : height,
						top : top,
						left : left,
						border : null
					}
				});

				$(".pupText").blur(function() {
					$.unblockUI();
				});

				$(".pupText").keyup(function() {
					var text = $(this).val();
					inputTarget.textbox("setValue", text);
					Oa.updateTableCellTip(tableId,inputName,text);
				});
			});
};
Oa.fillGridEmptyCell = function(gridObj,index,fieldName) {
	var cell = gridObj.datagrid("getPanel").find("tr[datagrid-row-index="+index+"]").find("td[field='" + fieldName + "']");
	cell.children().text("(无需填写)");
};


Oa.showTableCellTip = function(tableId, fieldName, position) {
	var tipPosition = "top";//默认在上方
	if(position){
		tipPosition = position;
	}
	var cellObj = $("#tab").tabs('getSelected').find("table[id='" + tableId+"']").find("td[field='" + fieldName + "']");
	var width = cellObj.width();
	cellObj.each(function(i) {
		var text = $(this).find("input[name='" + fieldName + "']").val();
		if (text) {
			var content = "<div style='width:"+width+"px;'>"+text+"</div>";
			$(this).tooltip({
				content : content,
				position : tipPosition,
			    onShow: function(){
			    	$(this).tooltip('tip').css({
                       borderColor: '#0099cc'
	                    });
	                }
			});
		}
	});
};

Oa.showGridCellTip = function(gridId, fieldName) {
	var cellObj = $("#" + gridId).datagrid("getPanel").find("td[field='" + fieldName + "']");
	var width = cellObj.width();
	cellObj.each(function(i) {
		var text = $(this).text();
		if (text) {
			var content = "<div style='width:"+width+"px;'>"+text+"</div>";
			$(this).tooltip({
				content : content,
				position :"top"
			});
		}
		
	});
};

Oa.updateGridCellTip = function(gridObj,fieldName,index,content) {
	var cellObj = gridObj.datagrid("getPanel").find("tr[datagrid-row-index='"+index+"']").find("td[field='" + fieldName + "']");
	var width = cellObj.width();
	var content = "<div style='width:"+width+"px;'>"+content+"</div>";
	cellObj.tooltip("update",content);
};
Oa.updateTableCellTip = function(tableId,fieldName,content) {
	var cellObj = $("#tab").tabs('getSelected').find("table[id='" + tableId+"']").find("td[field='" + fieldName + "']");;
	var width = cellObj.width();
	var content = "<div style='width:"+width+"px;'>"+content+"</div>";
	try{
		cellObj.tooltip("update",content);
	}catch(err){
		cellObj.tooltip({
			content : content,
			position :"top"
		});
	}
};

Oa.showPageNotSave = function(title) {
	$("body").block(
			{
				message : "<div style='float:left;'><img  src='"
						+ G_CTX_PATH
						+ "/static/images/icons/error.gif'/></div><div style='float:left;'>&nbsp;您在页面“"+title+"”上，有一条数据未保存！</div>",
				overlayCSS : {
					backgroundColor : "#fff;",
					opacity : 0
				},
				css : {
					padding : '50px',
					width : "300px",
					height : "50px",
					top : "0px",
					left : "0px",
					color : "red",
					border : 'solid red 2px',
					backgroundColor : "#fff;",
					opacity : 1
				},
				fadeIn : 1000,
				timeout : 2000
			});
};


Oa.tabOnUnSelect = function(title,index) {
	if (title == '商机管理') {
		if (oppoGrid.isEditing || recordGrid.isEditing) {
			Oa.showPageNotSave(title);
		}		
	}
	
	if (title == '客户管理') {
		if (custGrid.isEditing || linkGrid.isEditing) {
			Oa.showPageNotSave(title);
		}
		
	}
};

//客户与联系人联动
Oa.cust_select = function(data,targetEd,isNotRowEdit) {
	var linkmanObj = targetEd;
	if (!isNotRowEdit) linkmanObj = $(targetEd.target);
	linkmanObj.combobox("clear");
	//设置主联系人
	linkmanObj.combobox({onLoadSuccess:function(){
		Hg.getJson("/oa/cust/getById", {custId:data.custId}, function(data) {
			if (data.success) {
				linkmanObj.combobox("setValue",data.data.mainLinkmanId);
			}
				
		});
		linkmanObj.combobox({onLoadSuccess:function(){}});

	}});
	linkmanObj.combobox("reload", G_CTX_PATH+"/oa/linkman/getListByCust/"+data.custId);
	
};

//客户与部门联动
Oa.cust_select2 = function(data,targetEd) {
	var departObj = targetEd;
	departObj.combobox("clear");	
	departObj.combobox("reload", G_CTX_PATH+"/oa/linkman/getDepartListByCust/"+data.custId);
};

Oa.depart_select = function(data,targetEd){
	$(targetEd.target).combobox("clear");	
	$(targetEd.target).combobox({onLoadSuccess:function(){
		
	}});
	$(targetEd.target).combobox("reload", G_CTX_PATH+"/oaOrgInfo/getCureAreaList/"+data.departId);
	
};

//供应商与联系人联动
Oa.supplierSelect = function(data,targetEd,isNotRowEdit) {
	var linkmanObj = targetEd;
	if (!isNotRowEdit) linkmanObj = $(targetEd.target);
	linkmanObj.combobox("clear");
	//设置主联系人
	linkmanObj.combobox({onLoadSuccess:function(){
		Hg.getJson("/oa/supplier/getById", {supplierId:data.supplierId}, function(data) {
			if (data.success) {
				linkmanObj.combobox("setValue",data.data.mainLinkmanId);
			}
		});
		linkmanObj.combobox({onLoadSuccess:function(){}});
	}});
	linkmanObj.combobox("reload", G_CTX_PATH+"/oa/supplierLinkman/getList?supplierId="+data.supplierId);
	
};

Oa.redirectToProChangeToDo = function(taskId, taskkey, taskName, processInstanceId,processDefinitionId){
	var url = "/oa/pro/getTmpProIdByBusProcessId";
	if ( processDefinitionId == "hpChangeProProcess") {
		 url = "/oa/pro/getTmpProIdByHpProcessId";
	}
	Hg.getJson(url,{processInstanceId : processInstanceId},function(data) {
		if (!data.success) return;
		var tab = $("#tab");
		var title = "项目详情(" + data.tmpProId + ")";
		if (tab.tabs("exists", title)) {
			tab.tabs("select", title);
		} else {
			tab.tabs("add", {
				title : title,
				closable : true
			});
		}
		var taskUrl = G_CTX_PATH + "/workflow/changePro/showToDo/";
		if (taskkey == "fillout" || taskkey == "reFill") {
			taskUrl = G_CTX_PATH + "/workflow/changePro/showFillout/";
		}
		tab.tabs('getSelected').panel("refresh",taskUrl+taskkey+"/"+taskId+"/"+processInstanceId+"/"+processDefinitionId);			
	});
};

Oa.redirectToPurchaseToDo = function(taskId, taskkey, taskName, processInstanceId,processDefinitionId){
	var url = "/oa/purchase/getPurchaseIdByProcessId";
	Hg.getJson(url,{processInstanceId : processInstanceId},function(data) {
		if (!data.success) return;
		var tab = $("#tab");
		var title = "采购订单详情(" + data.purchaseId + ")";
		if (tab.tabs("exists", title)) {
			tab.tabs("select", title);
		} else {
			tab.tabs("add", {
				title : title,
				closable : true
			});
		}
		var taskUrl = G_CTX_PATH + "/workflow/purchase/showToDo/";
		if (taskkey == "reFill") {
			taskUrl = G_CTX_PATH + "/workflow/purchase/showToReFill/";
		}
		tab.tabs('getSelected').panel("refresh",taskUrl+taskkey+"/"+taskId+"/"+processInstanceId);			
	});
};
Oa.redirectToPurchaseCancelToDo = function(taskId, taskkey, taskName, processInstanceId,processDefinitionId){
	var url = "/oa/purchase/getPurchaseCancelIdByProcessId";
	Hg.getJson(url,{processInstanceId : processInstanceId},function(data) {
		if (!data.success) return;
		var tab = $("#tab");
		var title = "采购取消详情(" + data.purchaseCancelId + ")";
		if (tab.tabs("exists", title)) {
			tab.tabs("select", title);
		} else {
			tab.tabs("add", {
				title : title,
				closable : true
			});
		}
		var taskUrl = G_CTX_PATH + "/workflow/purchase/showCancelToDo/";
		tab.tabs('getSelected').panel("refresh",taskUrl+taskkey+"/"+taskId+"/"+processInstanceId);			
	});
};

Oa.redirectToLeaveToDo = function(taskId, taskkey, taskName, processInstanceId,processDefinitionId){
	var tag = "showToDo";
	if (taskkey == 'reFill') {
		tag = "showToReFill";
	}
	var taskToDoWin = new HgWin({
		id : "oaLeaveWin",
		title : "办理流程-" + taskName,
		width : 900,
		height : 520,
		iconCls : 'm-icon-control-play-blue',
		url : "/workflow/leave/"+tag+"/" + taskkey + "/" + taskId + "/"
				+ processInstanceId
	});
};


var oaSelUser = {};
var userCenter = {

};

Oa.proSelUserCallback = {
	
};
//关闭当前选项卡
Oa.closeCurrTabs = function(){
	var currentTab = $("#tab").tabs('getSelected');
   var currentTabIndex = $("#tab").tabs('getTabIndex',currentTab);
	$("#tab").tabs('close',currentTabIndex);
}

var doOaDiaryExpiredInit = function(value, row, index) {
	if (row.expiredTag == 2) {
		return "<div style='background-color:#ddd;width:100%;height:100%'>过期</div>";
	}
	return "";
};

//供应商超链接
Oa.formatSupplierHref = function(val, row) {
	var html = "";
	if(val){
		html = "<a href='javascript:Oa.showSupplierInfo(" + row.supplierId + ");'>" + val + "</a>";
	}
	return html;
};
//实际供应商，通过actSupplierId
Oa.formatActSupplierHref = function(val, row) {
	var html = "";
	if(val && row.actSupplierId){
		html = "<a href='javascript:Oa.showSupplierInfo(" + row.actSupplierId + ");'>" + val + "</a>";
	}
	return html;
};
Oa.showSupplierInfo = function(supplierId) {
	var tab = $("#tab");
	var title = "供应商管理";
	var url = G_CTX_PATH + "/oa/supplier/show?supplierId="+supplierId;
	if (tab.tabs("exists", title)) {
		tab.tabs("select", title);
		tab.tabs('getSelected').panel("refresh",url);
	} else {
		tab.tabs("add", {
			title : title,
			closable : true
		});
		tab.tabs('getSelected').panel("refresh",url);
	}
};
//采购订单超链接
Oa.formatPurchaseHref = function(val, row) {
	var html = "";
	if(val){
		html = "<a href='javascript:Oa.showPurchaseInfo(" + row.purchaseId + ");'>" + val + "</a>";
	}
	return html;
};
Oa.showPurchaseInfo = function(purchaseId) {
	var tab = $("#tab");
	var title = "采购订单管理";
	var url = G_CTX_PATH + "/oa/purchase/show?purchaseId="+purchaseId;
	if (tab.tabs("exists", title)) {
		tab.tabs("select", title);
		tab.tabs('getSelected').panel("refresh",url);
	} else {
		tab.tabs("add", {
			title : title,
			closable : true
		});
		tab.tabs('getSelected').panel("refresh",url);
	}
};

//采购合同超链接
Oa.formatPurchaseContractHref = function(val, row) {
	var html = "";
	if(val){
		html = "<a href='javascript:Oa.showPurchaseContractInfo(" + row.purchaseContractId + ");'>" + val + "</a>";
	}
	return html;
};
Oa.showPurchaseContractInfo = function(contractId) {
	var tab = $("#tab");
	var title = "采购合同";
	var url = G_CTX_PATH + "/oa/purchaseContract/show?contractId="+contractId;
	if (tab.tabs("exists", title)) {
		tab.tabs("select", title);
		tab.tabs('getSelected').panel("refresh",url);
	} else {
		tab.tabs("add", {
			title : title,
			closable : true
		});
		tab.tabs('getSelected').panel("refresh",url);
	}
};

//客户超链接
Oa.formatCustHref = function(val, row) {
	var html = "";
	if(val){
		html = "<a href='javascript:Oa.showCustInfo(" + row.custId + ");'>" + val + "</a>";
	}
	return html;
};
Oa.showCustInfo = function(custId) {
	var tab = $("#tab");
	var title = "客户管理";
	var url = G_CTX_PATH + "/oa/cust/show?custId="+custId;
	if (tab.tabs("exists", title)) {
		tab.tabs("select", title);
		tab.tabs('getSelected').panel("refresh",url);
	} else {
		tab.tabs("add", {
			title : title,
			closable : true
		});
		tab.tabs('getSelected').panel("refresh",url);
	}
};

//合同超链接
Oa.formatContractHref = function(val, row) {
	var html = "";
	if(val){
		if(row.contractId){
			html = "<a href='javascript:Oa.showContractInfo(" + row.contractId + ");'>" + val + "</a>";
		}else{
			html = val;
		}
	}
	return html;
};
Oa.showContractInfo = function(contractId) {
	var tab = $("#tab");
	var title = "销售合同管理";
	var url = G_CTX_PATH + "/oa/contract/show?contractId="+contractId;
	if (tab.tabs("exists", title)) {
		tab.tabs("select", title);
		tab.tabs('getSelected').panel("refresh",url);
	} else {
		tab.tabs("add", {
			title : title,
			closable : true
		});
		tab.tabs('getSelected').panel("refresh",url);
	}
};

//商机超链接
Oa.formatOaOpportunityHref = function(val, row) {
	var html = "";
	if(val){
		if(row.opportunityId){
			html = "<a href='javascript:Oa.showOaOpportunityInfo(" + row.opportunityId + ");'>" + val + "</a>";
		}else{
			html = val;
		}
	}
	return html;
};
Oa.showOaOpportunityInfo = function(opportunityId) {
	var tab = $("#tab");
	var title = "商机管理";
	var url = G_CTX_PATH + "/oa/opportunity/show?opportunityId="+opportunityId;
	if (tab.tabs("exists", title)) {
		tab.tabs("select", title);
		tab.tabs('getSelected').panel("refresh",url);
	} else {
		tab.tabs("add", {
			title : title,
			closable : true
		});
		tab.tabs('getSelected').panel("refresh",url);
	}
};

//合同商务应收超链接
Oa.formatContractBusNeedHref = function(val, row) {
	var html = "";
	if(val){
		if(row.contractId){
			var busVar=val;
			if (!isNaN(val)) {
				busVar = formatCurrency(val);
			}
			html = "<a href='javascript:Oa.showContractBusNeedInfo(" + row.contractId + ");'>" + busVar + "</a>";
		}else{
			html = val;
		}
	}else if(val == '0'){
		html = val;
	}
	return html;
};
Oa.showContractBusNeedInfo = function(contractId) {
	var tab = $("#tab");
	var title = "商务应收明细";
	var url = G_CTX_PATH + "/oa/contract/showBusNeedMoneyReport?fromFlag=2&contractId="+contractId;
	if (tab.tabs("exists", title)) {
		tab.tabs("select", title);
		tab.tabs('getSelected').panel("refresh",url);
	} else {
		tab.tabs("add", {
			title : title,
			closable : true
		});
		tab.tabs('getSelected').panel("refresh",url);
	}
};

//合同财务应收超链接
Oa.formatContractFinNeedHref = function(val, row) {
	var html = "";
	if(val){
		if(row.contractId){
			var busVar=val;
			if (!isNaN(val)) {
				busVar = formatCurrency(val);
			}
			html = "<a href='javascript:Oa.showContractFinNeedInfo(" + row.contractId + ");'>" + busVar + "</a>";
		}else{
			html = val;
		}
	}else if(val == '0'){
		html = val;
	}
	return html;
};
Oa.showContractFinNeedInfo = function(contractId) {
	var tab = $("#tab");
	var title = "财务应收明细";
	var url = G_CTX_PATH + "/oa/contract/showFinNeedMoneyReport?fromFlag=2&contractId="+contractId;
	if (tab.tabs("exists", title)) {
		tab.tabs("select", title);
		tab.tabs('getSelected').panel("refresh",url);
	} else {
		tab.tabs("add", {
			title : title,
			closable : true
		});
		tab.tabs('getSelected').panel("refresh",url);
	}
};

//项目已购超链接
Oa.formatProBoughtHref = function(val, row) {
	var html = "";
	if(val && !isNaN(val)){
		html = "<a href='javascript:Oa.showProBoughtPurchaseInfo(" + row.projectId + ");'>" + formatCurrency(val) + "</a>";
	}
	return html;
};
Oa.showProBoughtPurchaseInfo = function(projectId) {
	var tab = $("#tab");
	var title = "项目采购明细";
	var url = G_CTX_PATH + "/oa/purchaseDetail/showProPurchaseDetailReport?projectId="+projectId;
	if (tab.tabs("exists", title)) {
		tab.tabs("select", title);
		tab.tabs('getSelected').panel("refresh",url);
	} else {
		tab.tabs("add", {
			title : title,
			closable : true
		});
		tab.tabs('getSelected').panel("refresh",url);
	}
};

Oa.fileNameFormat = function(value,row,index) {
	var html = "";
	if(row.attId){
		html = "<a href='javaScript:void(0)' onclick='Hg.downAttachFile(\""+row.attId+"\")'>" + value +"</a>";
	}else{
		html = value;
	}
	return html;
};

Oa.successRateRowStyler = function(index,row) {
	if (row.successRate == 0) {
		return 'background-color:#ffcc99;color:#fff;font-weight:bold;';
	}
};

Oa.openFindPasswordDialog = function(loginName,validateCode){
	var parameter = (loginName)?"?loginName="+loginName+"&validateCode="+validateCode:"";
	var findPasswordWin = new HgWin({
		id : "openFindPasswordDialog",
		title : "找回密码",
		width : 550,
		height : 300,
		iconCls : "m-icon-personal",
		url : "/anonPages/findpassword/show"+parameter
	});
}




/*-----------------------------------------------------------------------------
|  公共函数
------------------------------------------------------------------------------*/
/**
* 将数值四舍五入(保留2位小数)后格式化成金额形式
* 
* @param num
*            数值(Number或者String)
* @return 金额格式的字符串,如'1,234,567.45'
* @type String
*/
function formatCurrency(num) {
	num = num.toString().replace(/\$|\,/g, '');
	if (isNaN(num))
		num = "0";
	sign = (num == (num = Math.abs(num)));
	num = Math.floor(num * 100 + 0.50000000001);
	cents = num % 100;
	num = Math.floor(num / 100).toString();
	if (cents < 10)
		cents = "0" + cents;
	for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
		num = num.substring(0, num.length - (4 * i + 3)) + ','
				+ num.substring(num.length - (4 * i + 3));
	return (((sign) ? '' : '-') + num + '.' + cents);
};



 


 
