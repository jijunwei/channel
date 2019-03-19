<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
	<base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>渠道-场景平台-银行项目测试</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="A Platform Management System (v1.0)">

    <!-- Le styles -->
	<link rel="stylesheet" href="<%=path%>/resources/bootstrap/assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=path%>/resources/bootstrap/assets/css/font-awesome.min.css"  />
	<link rel="stylesheet" href="<%=path%>/resources/bootstrap/assets/css/docs.css" >
	<link rel="stylesheet" href="<%=path%>/resources/css/style.css">
	<link rel="stylesheet" href="<%=path%>/resources/plug-in/jquery-ui/css/ui-lightness/jquery-ui-1.9.2.custom.css">
	<link rel="stylesheet" href="<%=path%>/resources/plug-in/My97DatePicker/skin/WdatePicker.css">
    <link rel="stylesheet" href="<%=path%>/resources/css/custom-theme/jquery-ui-timepicker-addon.css">
    <!-- Le fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<%=path%>/resources/bootstrap/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<%=path%>/resources/bootstrap/assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<%=path%>/resources/bootstrap/assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="<%=path%>/resources/bootstrap/assets/ico/apple-touch-icon-57-precomposed.png">
    <link rel="shortcut icon" href="<%=path%>/resources/bootstrap/assets/ico/favicon.png">
    <style type="text/css">
       	.modal-open{
   			overflow: initial !important ;
		}
    </style>
    <!-- 引入javascript -->
	<script src="<%=path%>/resources/js/index/baowen.js"></script>
	<script src="<%=path%>/resources/js/common/jquery-1.11.3.min.js"></script>
	<script src="<%=path%>/resources/js/common/jquery-ui-1.10.0.custom.min.js"></script>
	<script src="<%=path%>/resources/bootstrap/assets/js/bootstrap.js"></script>
	<script src="<%=path%>/resources/bootstrap/assets/js/bootbox.js"></script>
	<script src="<%=path%>/resources/bootstrap/assets/js/docs.js"></script>

	<script type="text/javascript" src="<%=path%>/resources/plug-in/My97DatePicker/WdatePicker.js"></script>

	<script>
        $(function() {
            $("#tranTime").datetimepicker({
                //showOn: "button",
                //buttonImage: "<%=path%>/resources/css/custom-theme/images/icon_calendar.gif",
                //buttonImageOnly: true,
                showSecond: true,
                dateFormat: 'yymmdd',
                timeFormat: 'hhmmss',
                stepHour: 1,
                stepMinute: 1,
                stepSecond: 1


            });

        });
    </script>

</head>

<body>
    <!-- ==============页面顶部=============== -->
	  <div class="navbar navbar-inverse navbar-fixed-top" style="font-family:'Microsoft YaHei','微软雅黑',tahoma,arial,simsun,'宋体';">
            <div class="navbar-inner">
                <div class="container">
                    <a class="brand" href="#"></a>
                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav">
                            <li lass="active">
                                <a href="#">home</a>
                            </li>
                        </ul>
                       <!--  <div id="twitter-share" class="pull-right" style="padding-top:16px;">
                            <a href="share" class="twitter-share-button" data-url="#" data-text="" data-via="wuyy" data-size="large">test</a>
                        </div> -->
                    </div>
                </div>
            </div>
        </div>


        <!-- Subhead
        ================================================== -->
        <header class="jumbotron subhead" id="overview">
            <div class="container" style="font-family:'Microsoft YaHei','微软雅黑',tahoma,arial,simsun,'宋体';">
                <h1>渠道-场景平台-银行某资产项目测试</h1>
                <p class="lead">A Platform OF Test System For platform-Bank.</p>
            </div>
        </header>
	
	<!-- ============页面主要内容============= -->
	<div class="container">
		<div class="form-group">
			<label class="col-sm-2" >输入字段</label>
			<div class="col-sm-8">
				<table>
				<tr>
					<td>
						<label>渠道</label>
					</td>
					<td>
						<select id="channel" onchange="change();">
						<option value="2000007" selected="selected">马上消费金融</option>
						<option value="2000008">新网</option>
					</select>
					</td>
				</tr>
				<tr>
					<td>
						<label>姓名</label>
					</td>
					<td>
						<input id="name" type="text" size="30" value="张三"/>
					</td>
					<td>
						<label>
							<font color=red>*</font>
						</label>
					</td>
				</tr>
				<tr>
					<td>
						<label>学号</label>
					</td>
					<td>
						<input id="sno" type="text" size="30" value="006"/>
					</td>
					<td>
						<label>
							<font color=red>*</font>
						</label>
					</td>
				</tr>
				<tr>
					<td>
						<label>性别</label>
					</td>
					<td>
						<input id="sex" type="text" size="30" value="female"/>
					</td>
					<td>
						<label>
							<font color=red>*</font>
						</label>
					</td>
				</tr>
				<tr>
					<td>
						<label>年龄</label>
					</td>
					<td>
						<input id="age" type="text" size="30" value="25"/>
					</td>
					<td>
						<label>
							<font color=red>*</font>
						</label>
					</td>
				</tr>
				<tr>
					<td><label>证件号码 </label></td><td><input id="idcard" type="text" size="25" value="12010119850602003x"/></td><td><label><font color=red>*</font></label> </td>
				</tr>
				<tr>
					<td><label>手机号 </label></td><td><input id="phone" type="text" size="25" value="18519121913"/></td><td><label><font color=red>*</font></label> </td>
				</tr>
				<tr>
					<td><label>银行卡号 </label></td><td><input id="bankno" type="text" size="25" value="621700000000303"/></td><td><label><font color=red>*</font></label> </td>
				</tr>
				<tr>
					<td><label>地域</label></td><td><input id="area" type="text" size="30" value="beijing"/></td><td><label><font color=red>*</font></label> </td>
				</tr>
				<tr><td><label>产品</label></td><td>
					<select id="product" onchange="change();">
						<option value="12" selected="selected">12期</option>
						<option value="6">6期</option>
					</select>
				</td>
				<tr>
				<tr>
					<td>
						<label>贷款金额</label>
					</td><td><input id="amount" type="text" size="30" value="3800"/>
				</td>
					<td>
						<label>
							<font color=red>*</font>
						</label>
					</td>
				</tr>
				<tr><!-- upload one; -->
					<td>
						<label id="photoLabel">照片</label>
					</td>
					<td>
						<input id="photo" type="file" name="photofile" />
					</td>
					<td>
						<label id="photoneed">
							<font color=red>*(jpg,jpeg,bmp,png)</font>
						</label>
					</td>
				</tr>
				<tr><%--<td><input id="photohidden" type="hidden" value=""/></td>--%>
					<td colspan="2">
						<input id="photoremotepath" type="hidden"/>
					</td>
				</tr>
				<tr><!-- upload two; -->
				<td>
					<label id="videoLabel2">视频</label>
				</td>
					<td>
						<input id="video2" type="file" name="videofile" />
					</td>
					<td>
						<label id="videoneed">(mov,avi,mp4)</label>
					</td>
				</tr>
				<tr><%--<td><input id="videohidden" type="hidden" value=""/></td>--%>
					<td colspan="2">
						<input id="videoremotepath" type="hidden"/>
					</td>
				</tr>
				<tr>
					<td>
						<label>备注</label>
					</td>
					<td>
						<textarea name="remark" id="remark"	cols="30" rows="10"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan=4>
						<hr>
					</td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td>
						<input type="button" onclick="toJson();" value="组装【资产进件报文】"/>
					</td>
				</tr>

				</table>
				</div>
			</div>	
		</div>
	
	<div class="container">
		<div class="form-group">
			<label class="col-sm-2" >JSON数据:</label>
			<div class="col-sm-8">
				<div>
					<textarea class="form-control" id="JSON" rows="6" cols="10" ></textarea>
				</div>
			</div>
		</div>
		<div class="clearfix form-actions" style="padding-left: 738px;">
			<div class="col-md-offset-3 col-md-9">
				<a class="btn btn-mini btn-primary" onclick="toBase64();">简单编码</a>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2" >编码处理结果:</label>
			<div class="col-sm-8">
				<div>
					<textarea class="form-control" id="encodeAndSignresult" rows="15" cols="10"></textarea>
				</div>
			</div>
		</div>
		<div class="clearfix form-actions" style="padding-left: 738px;">
			<div class="col-md-offset-3 col-md-9">
				<a class="btn btn-mini btn-danger" onclick="sendToPlatform();">发送数据到平台</a>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2" >平台处理结果:</label>
			<div class="col-sm-8">
				<div>
					<textarea class="form-control" id="returnResult" rows="15" cols="10"></textarea>
				</div>
			</div>
		</div>
	</div>
	
	
	
	<!-- ===============页面底部 ==============-->
	<footer class="footer">
	    <div class="container" style="font-family:'Microsoft YaHei','微软雅黑',tahoma,arial,simsun,'宋体';">
	        <p>
				场景集市平台测试系统  &copy; 2019 - 2022.
	        </p>
	    </div>
	</footer>

	<script type="text/javascript">

        function toJson(){
            if (photo == null || photo == undefined || photo == "") {
                alert('系统提示:请选择要上传的文件!');
                return false;
            } else{
                var file = photo.split('.');
                var suffix = file[file.length-1];
                //alert("suffix:"+suffix);
                if (suffix =="jpg" || suffix =="png" ||suffix =="jpeg"||suffix =="bmp") {
                    var photoremotepath = $("#photoremotepath").val();
                    uploadPhotofile();
                }
                alert("文件格式必须是jpg、jpeg、png、bmp");
                return;
            }
            if (video2 == null || video2 == undefined || video2 == "") {



            } else{
                var file = video2.split('.');
                var suffix = file[file.length-1];
                var videoremotepath = $("#videoremotepath").val();
                //alert("suffix:"+suffix);
                if (suffix =="mp4" ||suffix =="avi"||suffix=="mov") {
                    /*uploadMultifile();*/
                    uploadVideofile();
                }
                alert("文件格式必须是mov,avi,mp4");
                return;
            }


            var channel=$("#channel").val();
            var head="{\"channel\": \""+channel+"\"";
            var name= $("#name").val();
            var sno = $("#sno").val();
            var sex = $("#sex").val();
            var idcard = $("#idcard").val();
            var phone = $("#phone").val();
            var bankno = $("#bankno").val();
            var area = $("#area").val();
            var product = $("#product").val();
            var amount = $("#amount").val();

            var remark = $("#remark").val();

            var data=head+"\"name\":\""+name+"\","+"\"sno\":\""+sno+"\","+"\"sex\":\""+sex+"\","+"\"idcard\":\""+idcard+"\","+"\"phone\":\""+phone+"\","+"\"bankno\":\""+bankno+"\","+"\"area\":\""+area+"\","+"\"product\":\""+product+"\"," +
				"\"amount\":\""+amount+"\","+"\"photoremotepath\":\""+photoremotepath+"\","+"\"videoremotepath\":\""+videoremotepath+"\",\"remark\":\""+remark+"\"}";
            $("#JSON").val(data);
            console.log(data);

        }
	</script>

</body>
</html>