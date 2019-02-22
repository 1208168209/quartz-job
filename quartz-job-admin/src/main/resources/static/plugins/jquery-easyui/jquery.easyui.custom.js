/**
 * easyui 自定义扩展。
 */
(function(){
	
	var defaultAjaxError = function (XMLHttpRequest, textStatus, thrownError){
		HgUtil.defaultAjaxError.apply(this,arguments);
	};
	/**
	 * easyui 异常默认方法重写。
	 */
	$.fn.tree.defaults.onLoadError = defaultAjaxError;
	$.fn.panel.defaults.onLoadError = defaultAjaxError;
	$.fn.form.defaults.onLoadError = defaultAjaxError;
	$.fn.datagrid.defaults.onLoadError = defaultAjaxError;
	$.fn.treegrid.defaults.onLoadError = defaultAjaxError;
	$.fn.combobox.defaults.onLoadError = defaultAjaxError;

    /**
     * easyui form 默认为ajax提交
     */
    $.fn.form.defaults.iframe = false;
	
    /**
     * 默认datagrid的列标题居中
     */
    $.fn.datagrid.defaults.columnhalign = "center";
    
	
	/**
	 * 扩展datagrid的renderRow方法，支持字段为对象属性，eg ：user.userName
	 * jquery.easyui.1.4.5.js 10665行 的 renderRow 方法   
	 * _7c8:target, _7c9:fields, _7ca:frozen, _7cb:rowIndex, _7cc:rowData
	 */
	$.fn.datagrid.defaults.view.renderRow = function (_7c8,_7c9,_7ca,_7cb,_7cc) {

    	var opts=$.data(_7c8,"datagrid").options;
    	var cc=[];
    	if(_7ca&&opts.rownumbers){
	    	var _7cd=_7cb+1;
	    	if(opts.pagination){
	    		_7cd+=(opts.pageNumber-1)*opts.pageSize;
	    	}
	    	cc.push("<td class=\"datagrid-td-rownumber\"><div class=\"datagrid-cell-rownumber\">"+_7cd+"</div></td>");
    	}
    	for(var i=0;i<_7c9.length;i++){
	    	var _7ce=_7c9[i];
	    	var col=$(_7c8).datagrid("getColumnOption",_7ce);
	    	if(col){
	    		//jquery.easyui.1.4.5.js 的 字段取值 修改为 取最终属性的值
		    	//var _7cf=_7cc[_7ce];
	    		var _7cf=_7cc[_7ce];
	    		if(_7ce != undefined && _7ce != null && _7ce.indexOf(".") > -1){
	    			var fieldChilds = _7ce.split(".");
	    			_7cf = _7cc[fieldChilds[0]];
	    			for (var j = 1; j < fieldChilds.length; j++) {
	    				if (_7cf == undefined || _7cf == null){
	    					break;
	    				}
	    				_7cf = _7cf[fieldChilds[j]];
	    			}
	    		}
		    	
		    	
		    	
		    	var css=col.styler?(col.styler(_7cf,_7cc,_7cb)||""):"";
		    	var cs=this.getStyleValue(css);
		    	var cls=cs.c?"class=\""+cs.c+"\"":"";
		    	var _7d0=col.hidden?"style=\"display:none;"+cs.s+"\"":(cs.s?"style=\""+cs.s+"\"":"");
		    	cc.push("<td field=\""+_7ce+"\" "+cls+" "+_7d0+">");
		    	var _7d0="";
		    	if(!col.checkbox){
			    	if(col.align){
			    		_7d0+="text-align:"+col.align+";";
			    	}
			    	if(!opts.nowrap){
			    		_7d0+="white-space:normal;height:auto;";
			    	}else{
			    		if(opts.autoRowHeight){
			    			_7d0+="height:auto;";
			    		}
			    	}
		    	}
		    	cc.push("<div style=\""+_7d0+"\" ");
		    	cc.push(col.checkbox?"class=\"datagrid-cell-check\"":"class=\"datagrid-cell "+col.cellClass+"\"");
		    	cc.push(">");
		    	if(col.checkbox){
		    		cc.push("<input type=\"checkbox\" "+(_7cc.checked?"checked=\"checked\"":""));
		    		cc.push(" name=\""+_7ce+"\" value=\""+(_7cf!=undefined?_7cf:"")+"\">");
		    	}else{
		    		if(col.formatter){
		    			cc.push(col.formatter(_7cf,_7cc,_7cb));
		    		}else{
		    			cc.push(_7cf);
		    		}
		    	}
		    	cc.push("</div>");
		    	cc.push("</td>");
	    	}
    	}
    	return cc.join("");

    };
	
	/**
	 * 扩展treegrid的renderRow方法，支持字段为对象属性，eg ：user.userName
	 * jquery.easyui.1.4.5.js 12231行 的 renderRow 方法
	 * _93d:target, _93e:fields, _93f:frozen, _940:rowIndex, row:rowData
	 */
	$.fn.treegrid.defaults.view.renderRow = function (_93d,_93e,_93f,_940,row) {
    	var _941=$.data(_93d,"treegrid");
    	var opts=_941.options;
    	var cc=[];
    	if(_93f&&opts.rownumbers){
    		cc.push("<td class=\"datagrid-td-rownumber\"><div class=\"datagrid-cell-rownumber\">0</div></td>");
    	}
    	for(var i=0;i<_93e.length;i++){
	    	var _942=_93e[i];//field,row[_942]即为值，需要把所有用到row[_942]的都改掉
	    	var col=$(_93d).datagrid("getColumnOption",_942);
	    	if(col){
	    		//字段取值 为 取最终属性的值
	    		var _treegridRowCellValue=row[_942];
	    		if(_942 != undefined && _942 != null && _942.indexOf(".") > -1){
	    			var fieldChilds = _942.split(".");
	    			_treegridRowCellValue = row[fieldChilds[0]];
	    			for (var j = 1; j < fieldChilds.length; j++) {
	    				if (_treegridRowCellValue == undefined || _treegridRowCellValue == null){
	    					break;
	    				}
	    				_treegridRowCellValue = _treegridRowCellValue[fieldChilds[j]];
	    			}
	    		}
	    		
	    		
	    		//jquery.easyui.1.4.5.js 的 row[_942] 改为：_treegridRowCellValue
		    	//var css=col.styler?(col.styler(row[_942],row)||""):"";
	    		var css=col.styler?(col.styler(_treegridRowCellValue,row)||""):"";
		    	var cs=this.getStyleValue(css);
		    	var cls=cs.c?"class=\""+cs.c+"\"":"";
		    	var _943=col.hidden?"style=\"display:none;"+cs.s+"\"":(cs.s?"style=\""+cs.s+"\"":"");
		    	cc.push("<td field=\""+_942+"\" "+cls+" "+_943+">");
		    	var _943="";
		    	if(!col.checkbox){
			    	if(col.align){
			    		_943+="text-align:"+col.align+";";
			    	}
			    	if(!opts.nowrap){
			    		_943+="white-space:normal;height:auto;";
			    	}else{
			    		if(opts.autoRowHeight){
			    			_943+="height:auto;";
			    		}
			    	}
		    	}
		    	cc.push("<div style=\""+_943+"\" ");
		    	if(col.checkbox){
		    		cc.push("class=\"datagrid-cell-check ");
		    	}else{
		    		cc.push("class=\"datagrid-cell "+col.cellClass);
		    	}
		    	cc.push("\">");
		    	if(col.checkbox){
		    		if(row.checked){
		    			cc.push("<input type=\"checkbox\" checked=\"checked\"");
		    		}else{
		    			cc.push("<input type=\"checkbox\"");
		    		}
		    		//jquery.easyui.1.4.5.js 的 row[_942] 改为：_treegridRowCellValue
		    		//cc.push(" name=\""+_942+"\" value=\""+(row[_942]!=undefined?row[_942]:"")+"\">");
		    		cc.push(" name=\""+_942+"\" value=\""+(_treegridRowCellValue!=undefined?_treegridRowCellValue:"")+"\">");
		    	}else{
			    	var val=null;
			    	if(col.formatter){
			    		//jquery.easyui.1.4.5.js 的 row[_942] 改为：_treegridRowCellValue
			    		//val=col.formatter(row[_942],row);
			    		val=col.formatter(_treegridRowCellValue,row);
			    	}else{
			    		//jquery.easyui.1.4.5.js 的 row[_942] 改为：_treegridRowCellValue
			    		//val=row[_942];
			    		val=_treegridRowCellValue;
			    	}
			    	if(_942==opts.treeField){
				    	for(var j=0;j<_940;j++){
				    		cc.push("<span class=\"tree-indent\"></span>");
				    	}
				    	if(row.state=="closed"){
				    		cc.push("<span class=\"tree-hit tree-collapsed\"></span>");
				    		cc.push("<span class=\"tree-icon tree-folder "+(row.iconCls?row.iconCls:"")+"\"></span>");
				    	}else{
				    		if(row.children&&row.children.length){
				    			cc.push("<span class=\"tree-hit tree-expanded\"></span>");
				    			cc.push("<span class=\"tree-icon tree-folder tree-folder-open "+(row.iconCls?row.iconCls:"")+"\"></span>");
				    		}else{
				    			cc.push("<span class=\"tree-indent\"></span>");
				    			cc.push("<span class=\"tree-icon tree-file "+(row.iconCls?row.iconCls:"")+"\"></span>");
				    		}
				    	}
				    	if(this.hasCheckbox(_93d,row)){
					    	var flag=0;
					    	var crow=$.easyui.getArrayItem(_941.checkedRows,opts.idField,row[opts.idField]);
					    	if(crow){
					    		flag=crow.checkState=="checked"?1:2;
					    	}else{
						    	var prow=$.easyui.getArrayItem(_941.checkedRows,opts.idField,row._parentId);
						    	if(prow&&prow.checkState=="checked"&&opts.cascadeCheck){
						    		flag=1;
						    		row.checked=true;
						    		$.easyui.addArrayItem(_941.checkedRows,opts.idField,row);
						    	}else{
						    		if(row.checked){
						    			$.easyui.addArrayItem(_941.tmpIds,row[opts.idField]);
						    		}
						    	}
						    	row.checkState=flag?"checked":"unchecked";
					    	}
					    	cc.push("<span class=\"tree-checkbox tree-checkbox"+flag+"\"></span>");
				    	}else{
				    		row.checkState=undefined;
				    		row.checked=undefined;
				    	}
				    	cc.push("<span class=\"tree-title\">"+val+"</span>");
			    	}else{
			    		cc.push(val);
			    	}
		    	}
		    	cc.push("</div>");
		    	cc.push("</td>");
	    	}
    	}
    	return cc.join("");

    };
    
    /**
     * 修改textbox的blur事件，通过blurNoChangeValue参数判断，是否blur时重置value。
     * 使用场景：只有textbox加button事件 选择数据 且 不可手动编辑。
     */
    $.fn.textbox.defaults.inputEvents.blur = function(e) {
    	var t = $(e.data.target);
    	var opts = t.textbox("options");
    	//只有设置了blurNoChangeValue = true时才不修改
    	if (!opts || !opts.blurNoChangeValue) {
    		t.textbox("setValue",opts.value);
    	}
    };
    
    /**
     * 修改layout的collapsedContent方法，支持IE8左右收缩竖向标题的显示
     */
    $.fn.layout.paneldefaults.collapsedContent = function(_3f6) {
	    var p = $(this);
	    var opts = p.panel("options");
	    if (opts.region == "north" || opts.region == "south"){
		    return _3f6;
	    }
	    var size = opts.collapsedSize - 2;
	    var left = (size - 16) / 2;
	    var titleOtherStyle = "";
	    //ie9以下不支持旋转
	    if (HgUtil.browser.ie && HgUtil.browser.version < 9) {
    		titleOtherStyle = " writing-mode:tb-rl;";
	    } else {
	    	left = size - left;
	    }
	    
	    
	    var cc = [];
	    if (opts.iconCls){
		    cc.push("<div class=\"panel-icon " + opts.iconCls + "\"></div>");
	    }
	    cc.push("<div class=\"panel-title layout-expand-title");
	    cc.push(opts.iconCls ? " layout-expand-with-icon" : "");
	    if (HgUtil.browser.ie && HgUtil.browser.version == 9) {
	    	cc.push("\" style=\" " + titleOtherStyle + "\">");
	    } else {
	    	cc.push("\" style=\"left:" + left + "px; " + titleOtherStyle + "\">");
	    }
	    cc.push(_3f6);
	    cc.push("</div>");
	    return cc.join("");
    }
})()