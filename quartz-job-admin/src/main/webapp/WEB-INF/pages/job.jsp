<%--
  Created by IntelliJ IDEA.
  User: chenqinglong
  Date: 2019/2/13 0013
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/meta.jsp"%>
<html>
<head>
    <title>任务列表页面</title>
</head>
<body>
<div  title="任务列表">
    <table id="job_grid" singleSelect=true  toolbar='#job_toolbar' class="easyui-datagrid"   fitColumns=true
           data-options="url:'${ctx}/quartzJob/jobList'"
           pagination="true" pageSize="${defaultPageSize}" pageList="${defaultPageList}">
        <thead>
        <tr>
            <th data-options="field:'jobName',width:50">任务名称</th>
            <th data-options="field:'jobGroup',width:80">任务所属分组</th>
            <th data-options="field:'jobClassName',width:80">任务执行类</th>
            <th data-options="field:'triggerName',width:30">触发器名称</th>
            <th data-options="field:'triggerGroup',width:70">触发器所属分组</th>
            <th data-options="field:'prevFireTime',width:70">上次执行时间</th>
            <th data-options="field:'nextFireTime',width:70">下次执行时间</th>
            <th data-options="field:'startTime',width:70">创建时间</th>
            <th data-options="field:'triggerState',width:70">触发器状态</th>
            <th data-options="field:'cronExpression',width:80">任务执行规则表达式</th>
            <%--<th data-options="field:'timeZoneId',width:80">时区</th>--%>
        </tr>
        </thead>
    </table>
    <div id="job_toolbar">
        <div class="gridSearchBar" style="height: 40px;">
            <form id="jobSearchForm">
                <table width="100%">
                    <tr>
                        <td width="60px" align="right"><span>job名称:</span></td>
                        <td width="120px"><input name="jobNameQuery"></td>
                        <td width="120px" align="right"><span>job分组:</span></td>
                        <td width="120px"><input name="jobGroupQuery"></td>
                        <td style="text-align: right;">
                            <a class="easyui-linkbutton" iconCls="m-icon-search" tag="search">查询</a>
                            &nbsp;
                            <a class="easyui-linkbutton" iconCls="m-icon-clear" tag="clear">清空</a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-person-add" plain="true" tag="add">添加</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-person-edit" plain="true" tag="edit">修改</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-person-edit" plain="true" tag="pause">暂停</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-person-edit" plain="true" tag="resume">恢复</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-person-del" plain="true" tag="del">删除</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-list" plain="true" tag="view">查看详情</a>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        //查询
        $("#job_toolbar [tag='search']").click(function(){
            $('#job_grid').datagrid('reload',{jobNameQuery:$('#jobSearchForm [name="jobNameQuery"]').val(), jobGroupQuery:$('#jobSearchForm [name="jobGroupQuery"]').val()});
        });

        $("#job_toolbar [tag='clear']").click(function(){
            $('#jobSearchForm')[0].reset();
            $('#job_grid').datagrid('reload',{});
        });

        $("#job_toolbar [tag='add']").click(function(){
            var iconCls = $(this).attr("iconCls");
            var sysUserDetailWin = new HgWin({id:"jobDetailWin",title:"添加任务",width:850,height:550,iconCls:iconCls,url:"/quartzJob/jobDetail/0/0/add"});
        });

        //暂停
        $("#job_toolbar [tag='pause']").click(function(){
            var row = $("#job_grid").datagrid("getSelected");
            if (!row) {
                $.messager.alert("提示","请选择一条数据","warning");
                return;
            }
            if (row.triggerState == 'PAUSED') {
                $.messager.alert("提示","任务已暂停","warning");
                return;
            }
            $.messager.confirm("暂停确认", "确认暂停此任务?", function(r){
                if (r){
                    Hg.post("/quartzJob/pause",{jobName:row.jobName,jobGroup:row.jobGroup},function(data){
                        if (data.success) {
                            $.messager.ok("暂停成功!",function(){
                                $('#job_grid').datagrid("reload");
                            });
                        } else {
                            $.messager.alert("暂停失败!",data.data);
                        }
                    });
                }
            });
        });

        //恢复任务
        $("#job_toolbar [tag='resume']").click(function(){
            var row = $("#job_grid").datagrid("getSelected");
            if (!row) {
                $.messager.alert("提示","请选择一条数据","warning");
                return;
            }
//            if (row.triggerState != 'PAUSED') {
//                $.messager.alert("提示","任务状态正常，无需恢复","warning");
//                return;
//            }
            $.messager.confirm("暂停确认", "确认恢复此任务?", function(r){
                if (r){
                    Hg.post("/quartzJob/resume",{jobName:row.jobName,jobGroup:row.jobGroup},function(data){
                        if (data.success) {
                            $.messager.ok("恢复成功!",function(){
                                $('#job_grid').datagrid("reload");
                            });
                        } else {
                            $.messager.alert("恢复失败!",data.data);
                        }
                    });
                }
            });
        });

        $("#job_toolbar [tag='edit']").click(function(){
            var row = $("#job_grid").datagrid("getSelected");
            if (!row) {
                $.messager.alert("提示","请选择一条数据","warning");
                return;
            }
//            if (row.triggerState != "PAUSED") {
//                $.messager.alert("提示","请先暂停次任务","warning");
//                return;
//            }
            var editUrl = "/quartzJob/jobDetail/"+row.jobName+"/"+row.jobGroup+"/edit";
            var sysUserDetailWin = new HgWin({id:"jobDetailWin",title:"修改任务",width:850,height:550,iconCls:'',url:editUrl});
        });
//        //-------------------------------------------查看详情---------------------------------------------------
        $("#job_toolbar [tag='view']").click(function(){
            var row = $("#job_grid").datagrid("getSelected");
            if (!row) {
                $.messager.alert("提示","请选择一条数据","warning");
                return;
            }
            var editUrl = "/quartzJob/jobDetail/"+row.jobName+"/"+row.jobGroup+"/view";
            var sysUserDetailWin = new HgWin({id:"jobDetailWin",title:"查看任务",width:850,height:550,iconCls:'',url:editUrl});
        });
//
        $("#job_toolbar [tag='del']").click(function(){
            var row = $("#job_grid").datagrid("getSelected");
            if (!row) {
                $.messager.alert("提示","请选择一条数据","warning");
                return;
            }
            $.messager.confirm("删除确认", "确认删除此任务吗?", function(r){
                if (r){
                    Hg.post("/quartzJob/delete",{jobName:row.jobName,jobGroup:row.jobGroup},function(data){
                        if (data.success) {
                            $.messager.ok("删除成功!",function(){
                                $('#job_grid').datagrid("reload");
                            });
                        } else {
                            $.messager.alert("删除失败!",data.data);
                        }
                    });
                }
            });
        });
    })
</script>

</body>

</html>