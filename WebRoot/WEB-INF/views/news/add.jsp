<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../common/header.jsp"%>

<div class="easyui-panel" title="添加新闻" iconCls="icon-add" fit="true" style="width:750px " align="center" >
    <div style="padding:10px 60px 10px 60px">
        <form id="add-form" method="post">
            <table cellpadding="5">
                <tr>
                    <td>新闻标题:</td>
                    <td><input class="wu-text easyui-textbox easyui-validatebox" type="text" name="title" data-options="required:true,missingMessage:'请填写新闻标题！'"></input></td>
                    
                </tr>
                <tr>
                    <td width="60" align="right">所属分类:</td>
                    <td>
                        <select name="categoryId" class="easyui-combobox" panelHeight="auto" style="width:268px" data-options="required:true, missingMessage:'请选择所属分类'" >
                            <c:forEach items="${newsCategoryList }" var="category">
                                <option value="${category.id }">${category.name }</option>
                            </c:forEach>
                        </select>
                    </td>
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
                        <input value="${news.photo} id="add-photo" class="wu-text easyui-textbox easyui-validatebox" type="text" placeholder="点击上传！" name="photo" readonly="readonly" data-options="required:true,missingMessage:'请上传新闻封面！'"></input>
                        <a href="javascript:void(0)" iconCls="icon-upload" class="easyui-linkbutton" onclick="uploadPhoto()">上传</a>
                        <a href="javascript:void(0)" iconCls="icon-eye" class="easyui-linkbutton" onclick="preview()">预览</a>
                    </td>
                    
                </tr>
                
                <tr>
                    <td>新闻作者:</td>
                    <td><input class="wu-text easyui-textbox easyui-validatebox" type="text" name="author" data-options="required:true,missingMessage:'请填写新闻作者！'"></input></td>
                    
                </tr>
                <tr>
                    <td>新闻内容:</td>
                    <td>
                        <textarea id="add-content" name="content" rows="6" style="width:700px;height: 300px"></textarea>
                </tr>
            </table>
        </form>
        <div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" iconCls="icon-add1" class="easyui-linkbutton" onclick="submitForm()">提交保存</a>
            <a href="javascript:void(0)" iconCls="icon-cross" class="easyui-linkbutton" onclick="clearForm()">清空</a>
            <a href="javascript:void(0)" iconCls="icon-back" class="easyui-linkbutton" onclick="back()">返回</a>
        </div>
    </div>
</div>

<%@include file="../common/footer.jsp"%>
<%--上传图片进度条--%>
<div id="process-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-upload',title:'正在上传图片'" style="width:450px; padding:10px;">
    <div id="p" class="easyui-progressbar" style="width:400px;" data-options="text:'正在上传中...'"></div>
</div>
<input type="file" id="photo-file" style="display:none;" onchange="upload()">
<!-- 预览图片 -->
<div id="preview-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-eye'" style="width:330px; padding:10px;">
        <table>
            <tr>
                <td>
                    <img id="preview-photo" src="<c:url value=""/> " width="300px" style=" overflow:scroll;" onclick="preview()">
                    
                </td>
            </tr>
        </table>
</div>
<!-- 配置文件 -->
<script type="text/javascript" src="<c:url value="/resources/admin/ueditor/ueditor.config.js"></c:url> "></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="<c:url value="/resources/admin/ueditor/ueditor.all.js"></c:url>"></script>
<!-- End of easyui-dialog -->
<script type="text/javascript">
    var ue = UE.getEditor('add-content');
    function submitForm(){
        var validate = $("#add-form").form("validate");
        var content = ue.getContent();
        if(!validate){
            $.messager.alert("消息提醒","请检查你输入的数据!","warning");
            return;
        }
        if(content == ""){
            $.messager.alert("消息提醒","请输入新闻内容!","warning");
            return;
        }
        var data = $("#add-form").serialize();
        
        $.ajax({
            url: 'add',
            type: 'post',
            dataType:'json',
            data:data,
            success:function (ret) {
                if (ret.type == 'success'){
                    $.messager.alert("消息提醒","添加成功(sucess)","info");
                    setTimeout(function () {
                        window.history.go(-1);
                    },500)
                }else{
                    $.messager.alert("消息提醒","ret.msg","warning");
                }
            }
            
        });
       
    }
    
    function clearForm(){
        $('#ff').form('clear');
    }
    function back(){
        window.history.back(-1);
    }

    function preview(){
        $('#preview-dialog').dialog({
            closed: false,
            modal:true,
            title: "图片预览",
            buttons: [{
                text: '关闭',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#preview-dialog').dialog('close');
                }
            }],
            onBeforeOpen:function(){
                //$("#add-form input").val('');
            }
        });
    }

    //上传图片
    function start(){
        var value = $('#p').progressbar('getValue');
        if (value < 100){
            value += Math.floor(Math.random() * 10);
            $('#p').progressbar('setValue', value);
        }else{
            $('#p').progressbar('setValue',0)
        }
    };
    function upload(){
        if($("#photo-file").val() == '')return;
        var formData = new FormData();
        formData.append('photo',document.getElementById('photo-file').files[0]);
        $("#process-dialog").dialog('open');
        var interval = setInterval(start,200);
        $.ajax({
            url:'upload_photo',
            type:'post',
            data:formData,
            contentType:false,
            processData:false,
            success:function(data){
                clearInterval(interval);
                $("#process-dialog").dialog('close');
                if(data.type == 'success'){
                    $("#preview-photo").attr('src',data.filepath);
                    $("#add-photo").val(data.filepath);
                }else{
                    $.messager.alert("消息提醒",data.msg,"warning");
                }
            },
            error:function(data){
                clearInterval(interval);
                $("#process-dialog").dialog('close');
                $.messager.alert("消息提醒","上传失败!","warning");
            }
        });
    }

    function uploadPhoto(){
        $("#photo-file").click();

    }
</script>