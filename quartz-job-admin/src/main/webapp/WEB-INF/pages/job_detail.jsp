<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户详细页面</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" id="jobDetail_layout">
		<div data-options="region:'center'" style="padding: 10 10 10 20px;" title="">
			<form id="job_form" class="hgform">
				<table class="hgtable">
					<tr>
						<td>任务名称<font color="red">*</font>:</td>
						<td><input  type="text" name="jobName" value="${job.jobName}" style="width: 580px;"
									<c:if test="${mode != 'add'}">readonly</c:if>></input></td>
					</tr>
					<tr>
						<td>任务组名称<font color="red">*</font>:</td>
						<td>
							<input  type="text" name="jobGroup" value="${job.jobGroup}" style="width: 580px;"
									<c:if test="${mode != 'add'}">readonly</c:if>></input>
						</td>
					</tr>
					<tr>
						<td>规则表达式<font color="red">*</font>:</td>
						<td><input  type="text" name="cronExpression" value="${job.cronExpression}" style="width: 580px;"
									<c:if test="${mode == 'view'}">readonly</c:if>></input></td>
					</tr>
					<tr>
						<td>运行模式<font color="red">*</font>:</td>
						<td>
							<select  name="glueType" <c:if test="${mode != 'add'}">disabled</c:if> style="width: 180px;">
								<option value="BEAN" <c:if test="${job.glueType == 'BEAN'}">selected</c:if>>BEAN</option>
								<option value="GLUE_JAVA" <c:if test="${job.glueType == 'GLUE_JAVA'}">selected</c:if>>GLUE(Java)</option>
							</select>
					</tr>
					<tr id="jobClassNameTr">
						<td>任务类名:</td>
						<td><input  type="text" name="jobClassName" value="${job.jobClassName}" style="width: 580px;"
									<c:if test="${mode != 'add'}">readonly</c:if>></input></td>
					</tr>
					<tr id="glueJavaTr" style="display: none">
						<td>job源码:</td>
						<td>
							<textarea name="glueJava" rows="10" cols="80" <c:if test="${mode != 'add'}">readonly</c:if>>${job.glueJava}</textarea>
						</td>
					</tr>
					<tr>
						<td>任务描述:</td>
						<td>
							<textarea name="description" rows="10" cols="80" <c:if test="${mode != 'add'}">readonly</c:if>>${job.description}</textarea>
					</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'south',border:false" style="text-align: right; padding: 5px 5px 5px; background-color: #e0e0e0;">
			<c:if test="${mode != 'view'}">
				<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" tag="ok">保存</a>
			</c:if>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" tag="cancel">取消</a>
		</div>
		
	</div>
	<script type="text/javascript">
		var mode = "${mode}";
		$(function () {
			$("#jobDetail_layout [tag='ok']").click(function(){
				if (mode == 'add') {
					saveJob(true);
				} else {
					saveJob(false);
				}

			});
			$("#jobDetail_layout [tag='cancel']").click(function(){
				$("#jobDetail_layout").parent().window("close");
			});

			$("#jobDetail_layout #job_form [name='glueType']").click(function(){
				if (this.value == 'BEAN'){
					$('#jobClassNameTr').show();
					$('#glueJavaTr').hide();
				}else {
					$('#glueJavaTr').show();
					$('#jobClassNameTr').hide();
				}
			});

			if("${job.glueType}" == "GLUE_JAVA"){
				$('#glueJavaTr').show();
				$('#jobClassNameTr').hide();
			}else {
				$('#jobClassNameTr').show();
				$('#glueJavaTr').hide();
			}

			$("#job_form").validate({
				rules: {
					jobName: "required",
					jobGroup: "required",
					cronExpression: "required",
					glueType: "required"
				}
			})
		})

		//私有页面方法------------------------------------------------------------------------------------------------------
		function saveJob(isAdd) {
			//提交数据--------------------------------------------------
			var submitUrl = "/quartzJob/addCronJob";
			if (!isAdd) submitUrl = "/quartzJob/rescheduleJob";
			if(!$("#job_form").validate().form()){
				return false;
			}
			$("#jobDetail_layout").block();
			Hg.post(submitUrl,$("#job_form").serializeArray(),function(data){
				if (!data.success) {
					$("#jobDetail_layout").unblock();
					Hg.showErrorMsg("job_form",data.data);
				} else {
					$.messager.ok("保存数据成功!",function(){
						$("#jobDetail_layout").parent().window("close");
						$('#job_grid').datagrid("reload");
					});
				}
			});
		}
	</script>
</body>

</html>
