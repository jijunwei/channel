/*
 * 非引用js
 * author: WUYY
 * */
$(document).ready(function(){ 
	$("#myModalButton").click(function(){
		window.frames["myModalFrame"].save();   //弹出框表单 保存按钮操作
	});
	
	//用于每次刷新时控制IFRAME高度初始化 
    $("#mainFrame").load(function(){ 
	    $(this).height(0); 
	    var height = $(this).contents().height() + 10; 
	    $(this).height( height < 500 ? 500 : height ); 
  	}); 
    
}); 

//对话框dialog
var WDialog = function () {
	this.URL1 = "";   //后台处理
	this.URL2 = "";    //处理完成后跳转url
	this.Target = "mainFrame"; //处理完成后刷新的iframe页面
	this.Title = ""; 
	this.Height = null; 
	this.Size = null; 
} 

//确认框confirm
var CDialog = function () {
	this.URL1 = "";   //后台处理
	this.URL2 = "";  //处理完成后跳转页面
	this.Target = "mainFrame"; //处理完成后刷新的iframe页面
	this.Text = ""; 
} 

//打开对话框
WDialog.prototype.openDialog = function () { 
	var url = this.URL2;
	var target = this.Target;
	var changeclass = "modal-dialog" + " " + this.Size;
	
	$("#myModalButton").attr("flag", "");
	$("#document").removeClass();
	$("#document").addClass(changeclass);
	$("#myModalbody").css("height", this.Height + "px");
	$("#myModalFrame").attr("src", this.URL1);
    $("#myModalLabel").text(this.Title);                //标题
	$("#myModal").modal({backdrop: 'static', keyboard: false});
	$("#myModal").on('hide.bs.modal', function () {   //模态框hide时回调函数
		if($("#myModalButton").attr("flag")=="click" && '' != url){
			if(target=="mainFrame"){
				$("#mainFrame").attr("src", url);     //刷新iframe页面1
			}else{
				$("#mainFrame").contents().find("#"+target).attr("src", url); //刷新iframe页面2
			}
		}
	});
	
	$("#myModal").on('hidden.bs.modal', function () {   //模态框hidden时回调函数
		if($("#myModalButton").is(":hidden")){
			$("#myModalButton").show();    //如果保存按钮元素为隐藏,则将它显现
		}
	});
}

//关闭确认框
function closeDialog(){
	$("#myModalButton").attr("flag", "click");
	$("#myModal").modal('hide');
}

//打开确认框
CDialog.prototype.openDialog = function () { 
	var url1 = this.URL1;
	var url2 = this.URL2;
	var target = this.Target;
	bootbox.confirm(this.Text, function(result) {
		if(result) {
			$.get(url1, function(data){
				if("success" == data.state){
					bootbox.dialog({
						message: "<span class='bigger-110'>"+ data.message + "</span>",
						buttons: 			
						{
							"button" :
							{
								"label" : "确定",
								"className" : "btn-sm btn-primary"
							}
						}
					});
					if('' != url2){
						if(target=="mainFrame"){
							//document.getElementById("mainFrame").src=url2;     //刷新iframe页面
							$("#mainFrame").attr("src", url2);     //刷新iframe页面1
						}else{
							$("#mainFrame").contents().find("#"+target).attr("src", url2); //刷新iframe页面2
						}
					}
				}else {
					bootbox.dialog({
						message: "<span class='bigger-110'>"+ data.message + "</span>",
						buttons: 			
						{
							"button" :
							{
								"label" : "确定",
								"className" : "btn-sm btn-primary"
							}
						}
					});
				}
			});
		}
	});
}

