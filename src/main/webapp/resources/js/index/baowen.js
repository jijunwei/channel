<!-- 引入javascript -->
/*function change(){

    var method = $("#method").val();
    if(method=="mode66"){
        document.getElementById("photoLabel").style.display="block";
        document.getElementById("photo").style.display="block";
        document.getElementById("photoneed").style.display="block";
        document.getElementById("video2").style.display="block";
        document.getElementById("videoneed2").style.display="block";

    }else if(method=="mode64"){
        document.getElementById("photoLabel").style.display="none";
        document.getElementById("photo").style.display="none";
        document.getElementById("photoneed").style.display="none";
        document.getElementById("video2").style.display="none";
        document.getElementById("videoneed2").style.display="none";
        document.getElementById("photo").value="";
        document.getElementById("photohidden").value="";
        document.getElementById("base64").value="";
    }
}*/


function uploadPhotofile(channelCode,channelDate,channelSeq) {


    $.ajaxFileUpload({
        url : basePath+'rst/uploadFTP.do',
        secureuri : false,

        fileElementId : 'photo', // 文件选择框的id属性
       data : {
            "channelCode":channelCode,
            "channelDate":channelDate,
            "channelSeq":channelSeq
        },
        dataType : 'json', // 服务器返回的格式，可以是json
        success : function(data, status) // 相当于java中try语句块的用法
        {
            /*alert("成功: " + data.path);*/
            $("#photoremotepath").val(data.path);
            $("#channel").val(data.channel);
            /*$("#channelDate").val(data.channelDate);
            $("#channelTime").val(data.channelTime);
            $("#channelSeq").val(data.channelSeq);*/



        },
        error : function(data, status, e)// 相当于java中catch语句块的用法
        {
            alert("失败:"+e);
           /* alert("data:"+data);*/
           /* alert("status:"+status);*/
        }
    });
}

function uploadVideofile(channelCode,channelDate,channelSeq) {



    $.ajaxFileUpload({
        url : basePath+'rst/uploadFTP.do', // 需要链接到服务器地址
        secureuri : false,

        fileElementId : 'video2', // 文件选择框的id属性
        data : {
            "channelCode":channelCode,
            "channelDate":channelDate,
            "channelSeq":channelSeq
        },
        dataType : 'json', // 服务器返回的格式，可以是json
        success : function(data, status) // 相当于java中try语句块的用法
        {
            alert("成功: " + data.path);
            $("#videoremotepath").val(data.path);


        },
        error : function(data, status, e)// 相当于java中catch语句块的用法
        {
            alert("失败:"+e);
            alert("data:"+data);
            alert("status:"+status);
        }
    });
}



function toBase64(){
   /* alert("in toBase64");
    alert(basePath);
    alert(basePath+'rst/toBase64.do');*/


    var data1=$("#JSON").val()
        console.log(data1);

        //alert("here! in toBase64");
        $.ajax({
            type: "POST",
            url: basePath+'rst/toBase64.do',
            data:{"jsondata": data1},
            dataType:'json',
            cache: false,
            success: function(data2){
               $("#encodeAndSignresult").val(data2.result);
                console.log(data2);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {//XMLHttpRequest, textStatus, errorThrown
                alert("请求失败");
                alert(XMLHttpRequest.status);


            }
        });
    }


    function sendToPlatform(){
        var data1 = $("#encodeAndSignresult").val().trim();
        console.log(data1);
        document.getElementById("sendButton").setAttribute("disabled", true);
        /*alert("here! in sendToPlatform()");*/
        $.ajax({
            type: "POST",
            url: basePath+'rst/sendToPlatform.do',
            data:{"jsondata": data1},
            dataType:'json',
            cache: false,
            success: function(data2){
                alert("success! send to platform for processing");
                $("#returnResult").val(data2.result);
                $("#sendStatus").val("true");
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {//XMLHttpRequest, textStatus, errorThrown
                alert("请求失败");
                /*alert(XMLHttpRequest.status);*/
                /*alert(XMLHttpRequest.readyState);
                alert(textStatus);*/

            }
        });


}