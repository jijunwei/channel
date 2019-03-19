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
<script type="text/javascript">
    var path = "<%=request.getContextPath()%>";
    var basePath = "<%=basePath%>";
</script>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>渠道-场景平台-银行某资产项目测试</title>
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
    <script type="text/javascript" src="<%=path%>/resources/plug-in/jquery/ajaxfileupload.js"></script>


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
                <h1>渠道自定义资产项目测试</h1>
                <p class="lead">A Platform OF Test System For platform-Bank.</p>
            </div>
        </header>
	
	<!-- ============页面主要内容============= -->
	<div class="container">
		<div class="form-group">
			<label class="col-sm-2" >输入字段</label>
			<div class="col-sm-8">

                <input  value="" type="hidden" id="channelDate" size="30">
                <input  value="" type="hidden" id="channelTime" size="30">
                <input  value="" type="hidden" id="channelSeq" size="30">
				<input  value="" type="hidden" id="channel" size="30">
				<input id="jinJianSeq"  type="hidden" size="30" value="T20000012013090900000000114"/>
				<table>
				<tr>
                    <td>
                        <label>渠道标识</label>
                    </td>
                    <td>
                        <select id="channelCode"<%--onchange="change();--%>">
                        <option value="2000007" selected="selected">马上消费金融</option>
                        <option value="2000008">新网</option>
                        <option value="2000009">长安新生</option>
                        <option value="2000010">蔷薇保理</option>
                        <option value="2000011">...</option>

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
					<td>
						<label>证件号码 </label>
					</td>
					<td>
						<input id="idcard" type="text" size="30" value="12010119850602003x"/>
					</td>
					<td>
						<label>
							<font color=red>*</font>
						</label>
					</td>
				</tr>
				<tr>
					<td>
						<label>手机号 </label>
					</td>
					<td>
						<input id="phone" type="text" size="30" value="18519121913"/>
					</td>
					<td>
						<label><font color=red>*</font></label>
					</td>
				</tr>
				<tr>
					<td>
						<label>银行卡号 </label>
					</td>
					<td>
						<input id="bankno" type="text" size="30" value="621700000000303"/>
					</td>
					<td>
						<label><font color=red>*</font></label>
					</td>
				</tr>
				<tr>
					<td>
						<label>地域</label>
					</td>
					<td>
						<input id="area" type="text" size="30" value="beijing"/>
					</td>
					<td>
						<label>
							<font color=red>*</font>
						</label>
					</td>
				</tr>
				<tr>
					<td><label>产品</label></td>
					<td>
					<select id="product">
						<option value="12" selected="selected">12期</option>
						<option value="6">6期</option>
					</select>
				</td>
				</tr>
				<tr>
					<td>
						<label>贷款金额</label>
					</td>
					<td><input id="amount" type="text" size="30" value="3800"/>
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
						<form  id="tf"  enctype="multipart/form-data" >
							<table>
								<tr>
									<%--<td>文件描述:</td>--%>
									<td><input type="hidden" value="photoupload" name="description"></td>
								</tr>
								<tr>
									<%--<td>请选择文件:</td>--%>
									<td><input type="file" name="file" id="photo"></td>
                                        <td><input id="photobutton" type="button" value="上传" onclick="testuploadphoto()"></td>
								</tr>
								<tr>

								</tr>
							</table>
						</form>
						<input id="photoremotepath" type="hidden" size="30" value="photopath"/>
					</td>
					<td>
						<label id="photoneed">
							<font color=red>*(jpg,jpeg,bmp,png)</font>
						</label>
					</td>
				</tr>

				<tr><!-- upload two; -->
				<td>
					<label id="videoLabel2">视频</label>
				</td>
					<td>
						<form  id="tf1"  enctype="multipart/form-data" >
							<table>
								<tr>
									<%--<td>文件描述:</td>--%>
									<td><input type="hidden" value="video2upload" name="description"></td>
								</tr>
								<tr>
									<%--<td>请选择文件:</td>--%>
                                <td><input id="video2" type="file" name="file"  /></td>
                                        <td><input id="videobutton" type="button" value="上传" onclick="testuploadvideo()"></td>

								</tr>
								<tr>


								</tr>
							</table>
						</form>

					</td>

					<td>
						<label id="videoneed">(mov,avi,mp4)</label><input type="hidden" id="videoremotepath" size="30" value="videopath"/>
						<%--<a href='javascript:;' onclick='file()' class='upd'>上传附件</a>
						<div id="factordia"></div>
					</td>--%>
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
						<input type="button" onclick="toJson();" id="getJson" value="组装【资产进件报文】"/>

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
				<a class="btn btn-mini btn-danger" onclick="sendToPlatform();" id="sendButton">发送数据</a>
                <input  value="false" type="hidden" id="sendStatus" size="30">
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
        //baowenForTest

        var myDate = new Date();
        var year=myDate.getFullYear();    //获取完整的年份(4位,1970-????)
        var month=myDate.getMonth()+1;       //获取当前月份(0-11,0代表1月)
        if(month<10)
            month="0"+month;
        var day=myDate.getDate();        //获取当前日(1-31)
        if(day<10){
            day="0"+day;
        }
        var channelDate=year+""+month+""+day;
        $( "#channelDate" ).val(channelDate);
        var hour=myDate.getHours();       //获取当前小时数(0-23)
        if(hour<10){
            hour="0"+hour;
        }
        var minute=myDate.getMinutes();     //获取当前分钟数(0-59)
        if(minute<10){
            minute="0"+minute;
        }
        var second=myDate.getSeconds();     //获取当前秒数(0-59)
        if(second<10){
            second="0"+second;
        }
        var channelTime=hour+""+minute+""+second;
        $( "#channelTime" ).val(channelTime);
        var channelSeq="T20000012013090900000000"+Math.floor(Math.random()*1000+1);
        $( "#channelSeq" ).val(channelSeq);

        var channelCode=$("#channelCode option:selected").val();
        $( "#channel" ).val(channelCode);

        function toJson(){
            var channelCode=$("#channelCode option:selected").val();
            var channel="";
            if(channelCode=="2000007"){
                channel="MaShangFinTech";

			} else
            if(channelCode=="2000008"){
                channel="XWBank";

            }else{
                channel="elseChannel";
			}

            if(channel=="elseChannel"){
                alert("请选择前两个Demo Channel");
                return false;
			}
            $( "#channel" ).val(channel);
            var channel=$( "#channel" ).val();
            $("#jinJianSeq").val(channelSeq);
            var head="{\"channelCode\": \""+channelCode+"\",\"channel\": \""+channel+"\",\"channelDate\": \""+channelDate+"\",\"channelTime\": \""+channelTime+"\",\"channelSeq\": \""+channelSeq+"\",";
            var name= $("#name").val();
            var sno = $("#sno").val();
            var sex = $("#sex").val();
            var age = $("#age").val();
            var idcard = $("#idcard").val();
            var phone = $("#phone").val();
            var bankno = $("#bankno").val();
            var area = $("#area").val();
            var product = $("#product option:selected").val();
            var amount = $("#amount").val();

            var remark = $("#remark").val();
            var photoremotepath=$("#photoremotepath").val();
            var videoremotepath=$("#videoremotepath").val();
            var data=head+"\"student\":{\"name\":\""+name+"\","+"\"sno\":\""+sno+"\","+"\"sex\":\""+sex+"\","+"\"age\":"+age+",\"idcard\":\""+idcard+"\","+"\"phone\":\""+phone+"\","+"\"bankno\":\""+bankno+"\","+"\"area\":\""+area+"\","+"\"product\":\""+product+"\"," +
                "\"amount\":\""+amount+"\",\"remark\":\""+remark+"\"}"+",\"material\":[{\"photoremotepath\":\""+photoremotepath+"\"},"+"{\"videoremotepath\":\""+videoremotepath+"\"}]}";
            $("#JSON").val(data);
            document.getElementById("JSON").setAttribute("disabled", true);
            document.getElementById("getJson").setAttribute("disabled", true);
            console.log(data);

        }

        setInterval("QueryJINJIAN()",1000);


        function testuploadphoto(){
            /*alert("upload photo")*/
            var photo=$("#photo").val()
            if (photo == null || photo == undefined || photo == "") {
                alert('系统提示:请选择要上传的文件,文件格式必须是jpg、jpeg、png、bmp!');
                return false;
            } else{
                var file = photo.split('.');
                var suffix = file[file.length-1];
                /*alert("suffix:"+suffix);*/
                if (suffix =="jpg" || suffix =="png" ||suffix =="jpeg"||suffix =="bmp") {
                    $("#photoremotepath").val("/信贷影像资料/渠道/"+channelCode+"/"+channelDate+"/"+channelSeq+"/");

                }else{
                    alert("文件格式必须是jpg、jpeg、png、bmp");
                    return false;
                }
            }
            var form = new FormData(document.getElementById("tf"));
            $.ajax({
                url:basePath+"/rst/upload.do?channelCode="+channelCode+"&channelDate="+channelDate+"&channelSeq="+channelSeq,
                type:"post",
                data:form,
                processData:false,
                contentType:false,
                success:function(data){
                    if(data.status=="1"){
                        alert("上传成功")
						$("#photoremotepath").val(data.path)
                    }else {
                        alert("上传失败")
                    }
                }
            });
        }
        function testuploadvideo(){
            /*alert("testuploadvideo")*/
            var video2=$("#video2").val()



            if (video2 == null || video2 == undefined || video2 == "") {
                alert("你未选择本地文件，无法开始上传！");

                return false;

            } else{
                document.getElementById("videobutton").style.display="block";
                var file = video2.split('.');
                var suffix = file[file.length-1];

                /*alert("suffix:"+suffix);*/
                if (suffix =="mp4" ||suffix =="avi"||suffix=="mov") {
                    $("#videoremotepath").val("/信贷影像资料/渠道/"+channelCode+"/"+channelDate+"/"+channelSeq+"/");

                }else{
                    alert("文件格式必须是mov,avi,mp4");
                    return false;
                }
            }
            var form = new FormData(document.getElementById("tf1"));
            $.ajax({
                url: basePath+"/rst/upload.do?channelCode="+channelCode+"&channelDate="+channelDate+"&channelSeq="+channelSeq,
                type:"post",
                data:form,
                processData:false,
                contentType:false,
                success:function(data){
                    if(data.status=="1"){
                        alert("上传成功")
                        $("#videoremotepath").val(data.path)
                    }else {
                        alert("上传失败")
                    }
                }
            });
        }
        function QueryJINJIAN(){


            var channelCode=$("#channelCode option:selected").val();
            var head="{\"channelCode\": \""+channelCode+"\"";

            var jinJianSeq=$("#jinJianSeq").val();
            /*var jdatepicker_begin=$("#jdatepicker_begin").val();
            var datepicker_end=$("#datepicker_end").val();
            var recordNum=$("#recordNum").val();
            var data=head+"\"jinJianSeq\":\""+jinJianSeq+"\","+"\"jdatepicker_begin\":\""+jdatepicker_begin+"\","+"\"datepicker_end\":\""+datepicker_end+"\","+"\"recordNum\":"+recordNum+"}"
            */
            var data=head+",\"jinJianSeq\":\""+jinJianSeq+"\"}"
            var status=$("#sendStatus").val();
            if((jinJianSeq == null || jinJianSeq == undefined || jinJianSeq == "" ||jinJianSeq=="T20000012013090900000000114" ||status=="false")){

            }else{
                query(data);
                console.log(data);
            }


        }
        function query(data1) {

            $.ajax({
                url: basePath + 'rst/queryJINJIAN.do', // 需要链接到服务器地址
                secureuri: false,
                type: 'post',
                data: {"jsondata": data1},
                dataType: 'json', // 服务器返回的格式，可以是json
                success: function (data, status) // 相当于java中try语句块的用法
                {
                    /*alert("成功: " + data.result);*/
                    if (data.result == null ||data.result=="") {

                    } else {
                        $("#sendStatus").val(false);
                        $("#returnResult").val("进件号：" + data.channelSeq + "处理结果" + data.result);
                    }

                },
                error: function (data, status, e)// 相当于java中catch语句块的用法
                {
                    /*alert("失败:" + e);*/
                    /*alert("data:" + data);
                    alert("status:" + status);*/
                }
            });
        }
	</script>

</body>
</html>