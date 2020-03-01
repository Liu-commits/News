<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../common/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
    <!-- Begin of toolbar -->
    <div id="wu-toolbar">
        <div class="wu-toolbar-button">
            <%@include file="../common/menus.jsp"%>
        </div>
        <div class="wu-toolbar-search">
            <label>新闻标题: </label><input id="search-title" class="wu-text" style="width:100px">
            <label>新闻作者: </label><input id="search-author" class="wu-text" style="width:100px">
            <label>新闻分类: </label>
            <select id="search-category" class="easyui-combobox" panelHeight="auto" style="width:120px">
                <option value="-1">全部</option>
                <c:forEach items="${newsCategoryList }" var="category">
                    <option value="${category.id }">${category.name }</option>
                </c:forEach>
            </select>
            <a href="#" id="search-btn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
    <input type="hidden" name="id" value="${news.id}">
</div>

<%@include file="../common/footer.jsp"%>

<!-- End of easyui-dialog -->
<script type="text/javascript">

	
	/**
	* 删除记录
	*/
	function remove(){
        var item = $('#data-datagrid').datagrid('getSelections');
        if(item == null || item.length == 0){
            $.messager.alert('信息提示','请选择要删除的数据！','info');
            return;
        }
		$.messager.confirm('信息提示','确定要删除该记录？', function(result){
				$.ajax({
					url:'delete',
					dataType:'json',
					type:'post',
					data:{id:item[0].id },
					success:function(data){
						if(data.type == 'success'){
							$.messager.alert('信息提示','删除成功！','info');
							$('#data-datagrid').datagrid('reload');
						}else{
							$.messager.alert('信息提示',data.msg,'warning');
						}
					}
				});
		});
	}
	
	/**
	* Name 打开添加窗口
	*/
	function openAdd(){
        
	    window.location.href = 'add';
		//$('#add-form').form('clear');
		// $('#add-dialog').dialog({
		// 	closed: false,
		// 	modal:true,
        //     title: "添加分类信息",
        //     buttons: [{
        //         text: '确定',
        //         iconCls: 'icon-ok',
        //         handler: add
        //     }, {
        //         text: '取消',
        //         iconCls: 'icon-cancel',
        //         handler: function () {
        //             $('#add-dialog').dialog('close');                    
        //         }
        //     }],
        //     onBeforeOpen:function(){
        //     	//$("#add-form input").val('');
        //     }
        // });
	}
	
	/**
	* 打开修改窗口
	*/
	function openEdit(){
		//$('#edit-form').form('clear');
        var item = $('#data-datagrid').datagrid('getSelected');
        if(item == null || item.length == 0){
            $.messager.alert('信息提示','请选择要修改的数据！','info');
            return;
        }
        window.location.href = 'edit?id='+item.id;
	}	
	
	
	//搜索按钮监听
	$("#search-btn").click(function(){
		var option = {title:$("#search-title").val(),categoryId:$("#search-category").combobox('getValue'),author:$("#search-author").val()};

		$('#data-datagrid').datagrid('reload',option);
	});
	
	/** 
	* 载入数据
	*/
	$('#data-datagrid').datagrid({
		url:'list',
		rownumbers:true,
		singleSelect:true,
		pageSize:20,           
		pagination:true,
		multiSort:true,
		fitColumns:true,
		idField:'id',
	    treeField:'name',
		fit:true,
		columns:[[
			{ field:'chk',checkbox:true},
			{ field:'title',title:'标题',align:'center',width:100},
            { field:'categoryId',title:'分类',align:'center',width:100,formatter:function(value,row,index){
                    
                    return row.newsCategory.name;
                }},
            { field:'author',title:'作者',align:'center',width:100},
            { field:'tags',title:'标签',align:'center',width:100},
            { field:'photo',title:'封面',width:80,align:'center',formatter:function(value,row,index){
                    var img = '<img src="'+value+'" width="60px" />';
                    
                    var ImgObj = new Image(); //判断图片是否存在  
                    ImgObj.src = value;
                    //没有图片，则返回-1  
                    if (ImgObj.fileSize > 0 || (ImgObj.width > 0 && ImgObj.height > 0)) {
                        return img;
                    } else {
                        return "图片未知";
                    }
                }},
            { field:'viewNumber',title:'浏览量',align:'center',width:100,sortable:true},
            { field:'commentNumber',title:'评论量',align:'center',width:100,sortable:true},
		]],
	});
</script>