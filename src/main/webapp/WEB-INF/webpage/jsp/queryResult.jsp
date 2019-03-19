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
    <title>渠道-场景平台-银行项目测试查询结果</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="A Platform Management System (v1.0)">


    <!-- Le styles -->
	<link rel="stylesheet" href="<%=path%>/resources/bootstrap/assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=path%>/resources/bootstrap/assets/css/font-awesome.min.css"  />
	<link rel="stylesheet" href="<%=path%>/resources/bootstrap/assets/css/docs.css" >
	<link rel="stylesheet" href="<%=path%>/resources/css/style.css">
	<link rel="stylesheet" href="<%=path%>/resources/plug-in/jquery-ui/css/ui-lightness/jquery-ui-1.9.2.custom.css">
	<link rel="stylesheet" href="<%=path%>/resources/plug-in/My97DatePicker/skin/WdatePicker.css">
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
	<script src="<%=path%>/resources/js/index/baowen.js"></script>
	<script src="<%=path%>/resources/js/common/jquery-1.11.3.min.js"></script>
	<script src="<%=path%>/resources/js/common/jquery-ui-1.10.0.custom.min.js"></script>
	<script src="<%=path%>/resources/bootstrap/assets/js/bootstrap.js"></script>
	<script src="<%=path%>/resources/bootstrap/assets/js/bootbox.js"></script>
	<script src="<%=path%>/resources/bootstrap/assets/js/docs.js"></script>

	<script src="<%=path%>/resources/js/index/baowen.js"></script>

	<script>
        $(function() {
            $( "#datepicker_begin" ).datepicker();
            $( "#datepicker_begin" ).datepicker("option", "dateFormat", "yymmdd");
            $( "#datepicker_end" ).datepicker();
            $( "#datepicker_end" ).datepicker("option", "dateFormat", "yymmdd");
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
                            <li lass="active">
                                <a href="<%=path%>/user/toFFQ.do">房分期资产测试</a>
                            </li>
                            <li lass="active">
                                <a href="<%=path%>/user/toFDD.do">房抵贷资产测试</a>
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
                <h1>平台-渠道-银行项目测试</h1>
                <p class="lead">A Platform OF Test System For platfrom-Bank.</p>
            </div>
        </header>

	<!-- ============页面主要内容============= -->
	<div class="container">
		<div class="form-group">
			<label class="col-sm-2">查询结果</label>
			<div class="col-sm-8">

					<table>

                  </table>
				</div>
			</div>
		</div>

	<div class="container">
		<div class="form-group">
			<label class="col-sm-2">JSON数据:</label>
			<div class="col-sm-8">
				<div>
					<textarea class="form-control" id="JSON" rows="6" cols="10" ></textarea>
				</div>
			</div>
		</div>
		<div class="clearfix form-actions" style="padding-left: 738px;">
			<div class="col-md-offset-3 col-md-9">
				<a class="btn btn-mini btn-primary" onclick="toBase64();/*toEncodeAndSignClick();*/">简单编码<%--加签加密--%></a>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2">编码处理结果:</label>
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
			<label class="col-sm-2">平台处理结果:</label>
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

        function QueryJINJIAN(){
            var channel=$("#channel").val();
            var head="{\"channel\": \""+channel+"\"";


            var jinJianSeq=$("#channel").val();
            var jdatepicker_begin=$("#jdatepicker_begin").val();
            var datepicker_end=$("#datepicker_end").val();
            var recordNum=$("#recordNum").val();
            var data=head+"\"jinJianSeq\":\""+jinJianSeq+"\","+"\"jdatepicker_begin\":\""+jdatepicker_begin+"\","+"\"datepicker_end\":\""+datepicker_end+"\","+"\"recordNum\":\""+recordNum+"\"}"
            $("#JSON").val(data);
            query(data);
            console.log(data);

        }

        function toFFQJson(){
            var channel=$("#channel").val();
            var head="{\"channel\": \""+channel+"\"";
            var data= head+"\"name\":\""+name+"\","+"\"sno\":\""+sno+"\","+"\"sex\":\""+sex+"\","+"\"idcard\":\""+idcard+"\","+"\"phone\":\""+phone+"\","+"\"bankno\":\""+bankno+"\","+"\"area\":\""+area+"\","+"\"product\":\""+product+"\"," +
                "\"amount\":\""+amount+"\","+"\"photoremotepath\":\""+photoremotepath+"\","+"\"videoremotepath\":\""+videoremotepath+"\",\"remark\":\""+remark+"\"}"
            $("#JSON").val(data);
            console.log(data);
        }
        function toFDDJson(){
            var channel=$("#channel").val();
            var head="{\"channel\": \""+channel+"\"";
            var data= head+"\"name\":\""+name+"\","+"\"sno\":\""+sno+"\","+"\"sex\":\""+sex+"\","+"\"idcard\":\""+idcard+"\","+"\"phone\":\""+phone+"\","+"\"bankno\":\""+bankno+"\","+"\"area\":\""+area+"\","+"\"product\":\""+product+"\"," +
                "\"amount\":\""+amount+"\","+"\"photoremotepath\":\""+photoremotepath+"\","+"\"videoremotepath\":\""+videoremotepath+"\",\"remark\":\""+remark+"\"}"
            $("#JSON").val(data);
            console.log(data);
            $("#JSON").val(data);
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
                    alert("成功: " + data.result);
                    if (data.result == null) {

                    } else {
                        $("#returnResult").val("进件号：" + data.channelSeq + "处理结果" + data.result);
                    }

                },
                error: function (data, status, e)// 相当于java中catch语句块的用法
                {
                    alert("失败:" + e);
                    alert("data:" + data);
                    alert("status:" + status);
                }
            });
        }



	</script>

</body>
</html>