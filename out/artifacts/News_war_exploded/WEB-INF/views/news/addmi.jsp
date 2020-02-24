<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../common/header.jsp"%>

<div class="easyui-panel" title="添加新闻" iconCls="icon-add" fit="true" style="width:750px " align="center" >
    <div style="padding:20px 60px 20px 60px">
        <form id="ff" method="post">
            <table cellpadding="5">
                <tr>
                    <td>新闻标题:</td>
                    <td><input class="wu-text easyui-textbox easyui-validatebox" type="text" name="title" data-options="required:true,missingMessage:'请填写新闻标题！'"></input></td>
                    
                </tr>
                <tr>
                    <td>摘要:</td>
                    <td>
                        <textarea name="abstrs" rows="3" class="wu-textarea easyui-textbox easyui-validatebox" data-options="required:true,missingMessage:'请填写摘要！'" style="width:260px"></textarea>
                    
                </tr>
                <tr>
                    <td>新闻标签:</td>
                    <td><input class="wu-text easyui-textbox easyui-validatebox" type="text" name="tags" data-options="required:true,missingMessage:'请填写新闻标签！'"></input></td>
                    
                </tr>
                <tr>
                    <td>新闻封面:</td>
                    <td >
                        <input class="wu-text easyui-textbox easyui-validatebox" type="text" value="<c:url value="/resources/upload/news-pic.jpg"/>" name="photo" readonly="readonly" data-options="required:true,missingMessage:'请上传新闻封面！'"></input>
                        <a href="javascript:void(0)" iconCls="icon-upload" class="easyui-linkbutton" onclick="submitForm()">上传</a>
                    </td>
                    <td style="height:150px; width:148px" rowspan="3">
                        <div style="height:100%;width:100%;overflow:auto">
                            <img src="<c:url value="/resources/upload/news-pic.jpg"/> " width="150px" style=" overflow:scroll;">
                        </div>
                    </td>
                </tr>
                
                <tr>
                    <td>新闻作者:</td>
                    <td><input class="wu-text easyui-textbox easyui-validatebox" type="text" name="author" data-options="required:true,missingMessage:'请填写新闻作者！'"></input></td>
                    
                </tr>
                <tr>
                    <td>新闻内容:</td>
                    <td>
                        <textarea id="add-content" name="content" rows="6" class="wu-textarea easyui-textbox easyui-validatebox" data-options="required:true,missingMessage:'请填写内容！'" style="width:260px"></textarea>
                </tr>
            </table>
        </form>
        <div style="text-align:center;padding:6px">
            <a href="javascript:void(0)" iconCls="icon-add1" class="easyui-linkbutton" onclick="submitForm()">提交保存</a>
            <a href="javascript:void(0)" iconCls="icon-cross" class="easyui-linkbutton" onclick="clearForm()">清空</a>
            <a href="javascript:void(0)" iconCls="icon-back" class="easyui-linkbutton" onclick="back()">返回</a>
        </div>
    </div>
</div>

<%@include file="../common/footer.jsp"%>

<!-- End of easyui-dialog -->
<script type="text/javascript">
    function submitForm(){
        $('#ff').form('submit');
    }
    function clearForm(){
        $('#ff').form('clear');
    }
    function back(){
        window.history.back(-1);
    }
</script>