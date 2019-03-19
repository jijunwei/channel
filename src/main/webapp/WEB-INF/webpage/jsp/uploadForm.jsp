<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <!-- jQuery  -->
    <%@ include file="/common/common.jsp" %>

    <!-- 如果你不想从网上下载js再导入项目可以用百度在线网址js文件   注意：电脑要联网才行 -->
    <!-- <script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script> -->

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>文件上传</title>

</head>
<body>
<h2>文件上传</h2>
<form  id="tf"  enctype="multipart/form-data" >
    <table>
        <tr>
            <td>文件描述:</td>
            <td><input type="text" name="description"></td>
        </tr>
        <tr>
            <td>请选择文件:</td>
            <td><input type="file" name="file"></td>
        </tr>
        <tr>
            <td><input type="button" value="上传" onclick="test()"></td>
        </tr>
    </table>
</form>

<script >
    function test(){
        alert("aaaaaaaaaaaa")
        var form = new FormData(document.getElementById("tf"));
        $.ajax({
            url:"/controller/upload.do",
            type:"post",
            data:form,
            processData:false,
            contentType:false,
            success:function(data){
                if(data=="1"){
                    alert("上传成功")
                }else {
                    alert("上传失败")
                }
            }
        });
    }

</script>
</body>


</html>


