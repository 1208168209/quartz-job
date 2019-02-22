<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/pages/meta.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/plugins/jquery-easyui/themes/default/easyui.css?jsTimer=${jsTimer}"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/plugins/jquery-easyui/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/plugins/jquery-easyui/themes/color.css"/>
    <script type="text/javascript" src="${ctx}/js/jquery.1.12.4.js"></script>
    <script type="text/javascript" src="${ctx}/plugins/jquery-easyui/jquery.easyui.1.4.5.js"></script>
    <script type="text/javascript" src="${ctx}/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${ctx}/plugins/jquery-blockUI/jquery.blockUI.js"></script>
    <script type="text/javascript" src="${ctx}/plugins/jquery-validator/jquery.validate.js"></script>
    <script type="text/javascript" src="${ctx}/plugins/jquery-validator/jquery.validate.message_cn.js"></script>
    <script type="text/javascript" src="${ctx}/js/main.js"></script>
    <style type="text/css">
        .error{
            color: red;
        }
    </style>
</head>
<body class="easyui-layout">
    <div region="north" split="false" border="false" id="indexTop_new"
         style="padding: 0px;height: 50px;background-color:#3c8dbc ">
        <div style="text-align: center; line-height: 50px;width: 200px;background-color: #367fa9">
            <div style="font-size:18px; color: white">quartz-job管理</div>
        </div>
    </div>

    <div id="center" data-options="region:'center',border:false"
         split="false">
        <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'west',split:true,title:'导航菜单',width:200" style="width:200px;">
                <div id="leftAccordion" class="easyui-accordion" data-options="fit:true,border:false,width:200" style="width:200px;">
                    <ul class='easyui-tree' style='padding: 0px;'>
                        <li iconCls="">
                            <span><span tag='link' url='/quartzJob/job' onclick="">任务列表</span></span>;
                        </li>
                    </ul>
                </div>
            </div>
            <div data-options="region:'center'">
                <div id="tab" class="easyui-tabs" data-options="fit:true,border:false,plain:true,tools:'#tab-tools'">
                    <div title="首页" id="homeTab" data-options="tools:'#p-tools',iconCls:'acc_icon_world',tabWidth:80" style="padding:5px"></div>
                </div>
            </div>

        </div>
    </div>
    <div region="south" split="false" id="indexSouth" style="display: none">
        <div class="man_footer">鸿冠信息 www.hongguaninfo.com</div>
    </div>
    <script type="text/javascript">
        $(function () {
            $("#leftAccordion span[tag='link']").click(function(){
                var tab = $("#tab");
                var title = $(this).text();
                if (tab.tabs("exists",title)) {
                    tab.tabs("select", title);
                    tab.tabs('getSelected').panel("refresh");
                } else {
                    tab.tabs("add",{title:title,closable:true,icon:$(this).attr("icon")});
                    tab.tabs('getSelected').panel("refresh", G_CTX_PATH+$(this).attr("url"));
                }
            });
        })
    </script>
</body>
</html>