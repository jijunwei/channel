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
    <title>渠道进件平台</title>
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
	<script type="text/javascript" src="<%=path%>/resources/plug-in/jquery/ajaxfileupload.js"></script>
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
								<a href="<%=path%>/user/toTest.do">某资产测试</a>
							</li>
							<%--<li lass="active">
								<a href="<%=path%>/user/toFDD.do">房抵贷资产测试</a>
							</li>--%>
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
                <h1>渠道进件演示平台</h1>
                <p class="lead">A Platform OF Test System For platfrom-Bank.</p>
            </div>
        </header>

	<!-- ============页面主要内容============= -->
	<div class="container">
		<div class="form-group">
			<%--<label class="col-sm-2">输入查询字段</label>--%>
			<div class="col-sm-8">

                <input  value="" type="hidden" id="channelDate" size="30">
                <input  value="" type="hidden" id="channelTime" size="30">
                <input  value="" type="hidden" id="channel" size="30">
                <input  value="" type="hidden" id="channelSeq" size="30">
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
                            <label>进件号</label>
                        </td>
                        <td>
                            <input id="jinJianSeq"  type="text" size="30" value="T20000012013090900000000114"/>
                        </td>
                    </tr>
                        <tr>
                            <td>
                                <label>起始日期</label>
                            </td>
                            <td>
                                <input type="text" id="datepicker_begin" size="30">
                            </td>
                            <td>
                                <label>*建议5天内</label>
                            </td>
                            <td>

                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>结束日期</label>
                            </td>
                            <td>
                                <input type="text" id="datepicker_end" size="30">
                            </td>
                            <td>
                                <label>*建议5天内</label>
                            </td>
                            <td>

                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>请求记录数</label>
                            </td>
                            <td>
                                <input id="recordNum" type="text" size="30" value="20"/>
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
                                <input type="button" onclick="alert('后续增加功能');/*QueryJINJIAN();*/" value="【渠道进件查询】"/>
                            </td>
                        </tr>
					<tr>
                        <td colspan=4>
                            <hr width="100%">
                        </td>
                    </tr>
                    <tr>

						<td>
                            <input type="button" onclick="toTestJson();" id="getJson" value="测试【某资产进件】json"/>

                        </td>
						<td>
							<a href="<%=path%>/user/toTest.do">
								<input type="button" onclick="" value="测试【某资产自定义进件内容】页面"/>
							</a>
						</td>
						<td>
							<input type="button" onclick="alert('医院应收保理资产对接中');/*toHospitalBaoliJson();*/" value="测试【医院应收保理进件】json"/>
						</td>
						<td>
							<input type="button" onclick="alert('其他类型资产对接中');/*toElsePropertiesJson();*/" value="测试【其他类型资产进件】json"/>
						</td>

					<tr/>
					<tr>
						<td>
							<input type="button" onclick="alert('房分期资产对接中');/*toFFQJson()*/;" value="测试【房分期进件】json"/>
						</td>
						<td>
							<input type="button" onclick="alert('房抵贷资产对接中');/*toFDDJson();*/" value="测试【房抵贷进件】json"/>
						</td>
						<td>
							<input type="button" onclick="alert('车险分期资产对接中');/*toCarInsuranceJson();*/" value="测试【车险分期进件】json"/>
						</td>
						<td>
							<input type="button" onclick="alert('车抵贷资产对接中');/*toCarDiJson();*/" value="测试【车抵贷进件】json"/>
						</td>

					</tr>
					<tr>
					<td>
                            <%--<a href="<%=path%>/user/toFFQ.do">--%>
                                <input type="button" onclick="alert('房分期自定义进件页面开发中');" value="测试【房分期自定义进件】页面"/>
                           <%-- </a>--%>
                        </td>
						<td>
                            <%--<a href="<%=path%>/user/toFDD.do">--%>
                                <input type="button" onclick="alert('房抵贷自定义进件页面开发中');" value="测试【房抵贷自定义进件】页面"/>
                            <%--</a>--%>
                        </td>

                        <td>
                            <%--<a href="<%=path%>/user/toCarInsurance.do">--%>
                                <input type="button" onclick="alert('车险分期自定义进件页面开发中');" value="测试【车险分期自定义进件】页面"/>
                            <%--</a>--%>
                        </td>
                        <td>
                            <%--<a href="<%=path%>/user/toCarDi.do">--%>
                                <input type="button" onclick="alert('车抵贷自定义进件页面开发中');" value="测试【车抵贷自定义进件】页面"/>
                            <%--</a>--%>
                        </td>
                    </tr>
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
				<a class="btn btn-mini btn-danger" onclick="sendToPlatform();" id="sendButton">发送数据</a>
				<input  value="false" type="hidden" id="sendStatus" size="30">
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


            var channelCode=$("#channelCode option:selected").val();
            var head="{\"channelCode\": \""+channelCode+"\"";

            var jinJianSeq=$("#jinJianSeq").val();
            /*var jdatepicker_begin=$("#jdatepicker_begin").val();
            var datepicker_end=$("#datepicker_end").val();
            var recordNum=$("#recordNum").val();*/
            /*var data=head+"\"jinJianSeq\":\""+jinJianSeq+"\","+"\"jdatepicker_begin\":\""+jdatepicker_begin+"\","+"\"datepicker_end\":\""+datepicker_end+"\","+"\"recordNum\":"+recordNum+"}"*/
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
                    if (data.result == null) {

                    } else {
                        $("#sendStatus").val(false);
                        $("#returnResult").val("进件号：" + data.channelSeq + "处理结果" + data.result);
                    }

                },
                error: function (data, status, e)// 相当于java中catch语句块的用法
                {
                    alert("失败:" + e);

                }
            });
        }



        function toTestJson(){
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
            $("#jinJianSeq").val(channelSeq);


            var head="{\"channelCode\": \"2000007\",\"channel\": \"MaShangFinTech\",\"channelDate\": \""+channelDate+"\",\"channelTime\": \""+channelTime+"\",\"channelSeq\": \""+channelSeq+"\",";

            var data= head+"\"student\":"+"{\"name\":\"ZhangSan\","+"\"sno\":\"2009212547\","+"\"sex\":\"Female\","+"\"age\":25,"+"\"idcard\":\"412702199910082313\","+"\"phone\":\"15801183844\","+"\"bankno\":\"621708191811118298\","+"\"area\":\"HeBei\","+"\"product\":\"CarInSurance\"," +
                "\"amount\":\"5080\",\"remark\":\"马上优质客户\"},"+"\"material\":[{\"photoremotepath\":\"/信贷影像资料/渠道/马上金融/20190304/T20000012013090900000000114/car1.jpg\"},"+"{\"videoremotepath\":\"/信贷影像资料/渠道/马上金融/20190304/T20000012013090900000000114/hamilton_clip.mp4\"}]}"
            $("#JSON").val(data);
            document.getElementById("JSON").setAttribute("disabled", true);
            document.getElementById("getJson").setAttribute("disabled", true);
            console.log(data);
        }

        setInterval("QueryJINJIAN()",1000);
    </script>

</body>
</html>