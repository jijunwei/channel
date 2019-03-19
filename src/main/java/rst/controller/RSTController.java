package rst.controller;

import com.itextpdf.xmp.impl.Base64;
import net.sf.json.JSONObject;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rst.entity.ResponseInfo;
import rst.service.SourceMsgService;
import rst.service.StudentService;
import rst.tcp.ftp.FTPUtil;
import rst.tcp.model.Channel;
import rst.tcp.model.QueryMsg;
import rst.tcp.model.Student;
import rst.tcp.model.route.ChannelMsg;
import rst.tcp.model.route.SourceMsg;
import rst.tcp.socket.Client;
import rst.util.GsonUtil;
import rst.util.JaXmlBeanUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/rst")
@Controller
public class RSTController {

    @Autowired
    @Qualifier("sourceMsgService")
    SourceMsgService sourceMsgService;

    @Autowired
    private StudentService studentService;
    //dev
   // private static final String callbackByPlatformUrl="http://localhost:8099/rst/callbackByPlatform.do";
    //test
    private static final String callbackByPlatformUrl="http://10.29.32.85:8099/channel-1.0-SNAPSHOT/rst/callbackByPlatform.do";
	private Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(value = "/callbackByPlatform", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object callback(@RequestBody String jsonData) {
        logger.info("in tcpClient callback method!");
        logger.info("receive from platform callback:"+jsonData);
        SourceMsg sourceMsg=GsonUtil.toBean(jsonData,SourceMsg.class);
        String result=sourceMsg.getResult();
        /*sourceMsgService.updateSourceMsgString(sourceMsg,result);*/
        int i=sourceMsgService.updateSourceMsgBeanInfodb(sourceMsg,result);
        String updateIntodbResult;
        if (i == 1) {
            updateIntodbResult = " channel update status into sourceMsg db success!";
        } else {
            updateIntodbResult = "channel update status into sourceMsg fail!";
        }
        JSONObject jsonObject= new JSONObject();
        jsonObject.put("status", "success");
        jsonObject.put("from", "channel has change status");
        jsonObject.put("updateIntodbResult", "updateIntodbResult");
        logger.info("jsonObject:"+jsonObject.toString());
        return jsonObject;

    }

    //上传文件会自动绑定到MultipartFile中
    @RequestMapping("/upload")
    @ResponseBody
    public Object upload(HttpServletRequest request,
                         @RequestParam("description") String description,
                         @RequestParam("file") MultipartFile file) throws Exception {
        JSONObject jsonObject= new JSONObject();
        //如果文件不为空，写入上传路径
        if(!file.isEmpty()) {
            //上传文件路径
            String path = request.getSession().getServletContext().getRealPath("/images/");

            System.out.println(path);
            //上传文件名
            String filename = file.getOriginalFilename();
            File filepath = new File(path,filename);
            //判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            file.transferTo(new File(path + File.separator + filename));
            //输出文件上传最终的路径 测试查看
            String localpath=path + File.separator + filename;
            System.out.println(localpath);
            logger.info("in upldataoadFTP method!");
            String channelCode=request.getParameter("channelCode").toString();


            String date=request.getParameter("channelDate").toString();
            String seq=request.getParameter("channelSeq").toString();


            String remotepath1="/信贷影像资料/渠道/"+channelCode+"/"+date+"/"+seq+"/"+filename;
            List<String> remotePaths=new ArrayList<>();
            remotePaths.add(remotepath1);

            String localfile=localpath;
            List<String> localfileList=new ArrayList<>();
            localfileList.add(localfile);

            FTPUtil.sentByFtpClient(date,seq,localfileList,remotePaths);

            jsonObject.put("status", "1");
            jsonObject.put("path", remotepath1);
            logger.info("jsonObject:"+jsonObject.toString());



        } else {

            jsonObject.put("status", "0");
            logger.info("jsonObject:"+jsonObject.toString());

        }
        return jsonObject;

    }
    /**
     * 上传一个文件
     *
     * @param source
     * @param request
     * @return
     */
    @RequestMapping("/uploadFTP")
    @ResponseBody
    public Object addFile(MultipartFile source, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String realPath = request.getSession().getServletContext().getRealPath("/");
            String webappsPath = new File(realPath).getParent();
            String oldName = source.getOriginalFilename();
            String extension = FilenameUtils.getExtension(oldName);
            String f = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String newName = oldName + "-" +f+"."+extension;

            File file = new File(webappsPath + "/file/tmp");
            if (!file.exists()) {
                file.mkdirs();
            }
            source.transferTo(new File(webappsPath + "/file/tmp" + newName));
            String networkPath = "/file/tmp" + newName;



            MultipartFile image = source;
            logger.info("in uploadFTP method!");
            String channelCode=request.getParameter("channelCode");
            String channel= Channel.getName(channelCode);
            String date=request.getParameter("channelDate");
            String seq=request.getParameter("channelSeq");


            String remotepath1="/信贷影像资料/渠道/"+channel+"/"+date+"/"+seq+"/"+newName;
            List<String> remotePaths=new ArrayList<>();
            remotePaths.add(remotepath1);
            String filename=image.getOriginalFilename();
            String localfile=webappsPath + "/file/tmp" + newName;
            List<String> localfileList=new ArrayList<>();
            localfileList.add(localfile);

            FTPUtil.sentByFtpClient(date,seq,localfileList,remotePaths);
            JSONObject jsonObject= new JSONObject();
            jsonObject.put("path", remotepath1);

            jsonObject.put("channel",channel);
            jsonObject.put("filename", filename);
            logger.info("jsonObject:"+jsonObject.toString());

            return jsonObject;

        } catch (Exception e) {
            e.printStackTrace();

        }
        return map;
    }

    /**
     * 对报文进行Base64编码返回
     * @param jsonData
     * @return
     * @throws Exception
     */
   @RequestMapping(value = "toBase64", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object toBase64(@RequestBody String jsonData) throws Exception{
		if(jsonData.contains("%7B%22")){
			jsonData= URLDecoder.decode(jsonData,"utf-8");
		}
		if(jsonData.contains("jsondata=")){
			jsonData=jsonData.replace("jsondata=", "");
		}
		ResponseInfo model=new ResponseInfo();
		try{

            ChannelMsg channelMsg= GsonUtil.toBean(jsonData,ChannelMsg.class);
            Student student=channelMsg.getStudent();
            List<String> material=channelMsg.getMaterial();
            String studentXml = JaXmlBeanUtil.parseBeanToXml(Student.class, student);

            SourceMsg sourceMsg=new SourceMsg();
            sourceMsg.setMsg(studentXml);

            String materialJson=GsonUtil.toJSONString(material);

            String base64Txt= Base64.encode(materialJson);
            sourceMsg.setMaterial(base64Txt);
            sourceMsg.setCallbackUrl(callbackByPlatformUrl);

            sourceMsg.setChannelCode(channelMsg.getChannelCode());
            sourceMsg.setChannelSeq(channelMsg.getChannelSeq());
            sourceMsg.setChannelDate(channelMsg.getChannelDate());
            sourceMsg.setChannelTime(channelMsg.getChannelTime());

            sourceMsg.setChannel(channelMsg.getChannel());
            sourceMsg.setResult(null);
            String result=GsonUtil.toJSONString(sourceMsg);
            logger.info("ToBase64:"+result);
     		model.setStatus("s");
		    model.setResult(result);

		}catch(Exception e){
            logger.info("ToBase64 fail");
	        model.setStatus("f");
	        model.setResult("ToBase64 fail");
	        e.printStackTrace();
		}
		return model;

	}



	


    /**
     * 发向场景集市平台的报文
     * @param jsonData
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sendToPlatform", produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object send(@RequestBody String jsonData) throws Exception {
        logger.info(System.getProperty("file.encoding"));
        if(jsonData.contains("%7B%22")){
            jsonData= URLDecoder.decode(jsonData,"utf-8");
        }
        if(jsonData.contains("jsondata=")){
            jsonData=jsonData.replace("jsondata=", "");
        }
        logger.info("发向场景集市平台的报文" + jsonData);
        SourceMsg sourceMsg= GsonUtil.toBean(jsonData,SourceMsg.class);
        logger.info(sourceMsg.toString());
        int i=sourceMsgService.addSourceMsgInfodb(sourceMsg);
        String channelCode=sourceMsg.getChannelCode();
        String addIntodbResult;
        if (i == 1) {
            addIntodbResult = " channel add into sourceMsg db success!";
        } else {
            addIntodbResult = "channel add into sourceMsg fail!";
        }
        JSONObject jsonObject= new JSONObject();

        jsonObject.put("addIntodbResult", addIntodbResult);
        String channel=sourceMsg.getChannel();
        String content = JaXmlBeanUtil.parseBeanToXml(SourceMsg.class, sourceMsg);

        Client.sendToSocket(sourceMsg.getChannelDate(),sourceMsg.getChannelTime(),channelCode,"Web",content);
        /*sourceMsgService.addSourceMsgString(content);*/

        return jsonObject;

    }


    /**
     * 发向场景集市平台的查询报文
     * @param jsonData
     * @return
     * @throws Exception
     */
   @RequestMapping(value = "/queryJINJIAN", produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object queryJINJIAN(@RequestBody String jsonData) throws Exception {

        logger.info(System.getProperty("file.encoding"));
       if(jsonData.contains("%7B%22")){
           jsonData= URLDecoder.decode(jsonData,"utf-8");
       }
       if(jsonData.contains("jsondata=")){
           jsonData=jsonData.replace("jsondata=", "");
       }
        logger.info("发向场景集市平台的查询某件报文" + jsonData);
        QueryMsg queryMsg= GsonUtil.toBean(jsonData,QueryMsg.class);

       SourceMsg sourceMsg=sourceMsgService.getSourceMsgResultByChannelSeq(queryMsg.getJinJianSeq());

        JSONObject jsonObject= new JSONObject();
        jsonObject.put("result", sourceMsg.getResult());
       jsonObject.put("channelSeq", sourceMsg.getChannelSeq());
        return jsonObject;

    }
}
