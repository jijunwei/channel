package rst.tcp.socket;

import com.itextpdf.xmp.impl.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rst.service.SourceMsgService;
import rst.service.impl.SourceMsgServiceImpl;
import rst.tcp.ftp.FTPUtil;
import rst.tcp.model.Student;
import rst.tcp.model.route.SourceMsg;
import rst.util.GsonUtil;
import rst.util.JaXmlBeanUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Client {
    private static Logger logger = LoggerFactory.getLogger(Client.class);

    public static String sendXWContent(String date, String time) {
        //传输内容

        Student client = new Student();
        client.setSno("008");
        client.setSex("male");
        client.setName("test");
        String studentXml = JaXmlBeanUtil.parseBeanToXml(Student.class, client);
        SourceMsg sourceMsg = new SourceMsg();
        sourceMsg.setChannelCode("2000008");
        String seq = "T20000012013090900000000" + new Random().nextInt(1000);
        String localfile1 = "d:/file/car2.jpg";
        String localfile2 = "d:/file/hamilton_clip.mp4";
        String localfile3 = "d:/file/car1.jpg";
        String localfile4 = "d:/file/orders.csv";
        List<String> localfiles = new ArrayList();
        localfiles.add(localfile1);
        localfiles.add(localfile2);
        localfiles.add(localfile3);
        localfiles.add(localfile4);
        String remotepath1 = "/信贷影像资料/归档/新网/" + date + "/" + seq + "/car2.jpg";
        String remotepath2 = "/信贷影像资料/渠道/新网/" + date + "/" + seq + "/hamilton_clip.mp4";
        String remotepath3 = "/信贷影像资料/渠道/新网/" + date + "/" + seq + "/car1.jpg";
        String remotepath4 = "/信贷影像资料/渠道/新网/" + date + "/" + seq + "/order.csv";
        List<String> remotepaths = new ArrayList<>();
        remotepaths.add(remotepath1);
        remotepaths.add(remotepath2);
        remotepaths.add(remotepath3);
        remotepaths.add(remotepath4);


        sourceMsg.setChannelSeq(seq);
        sourceMsg.setChannelDate(date);
        sourceMsg.setChannelTime(time);
        sourceMsg.setMsg(studentXml);
        SourceMsgService sourceMsgService=new SourceMsgServiceImpl();
        int i=sourceMsgService.addSourceMsgInfodb(sourceMsg);
        String addIntodbResult;
        if (i == 1) {
            addIntodbResult = " channel add into sourceMsg db success!";
        } else {
            addIntodbResult = "channel add into sourceMsg fail!";
        }
        logger.info(addIntodbResult);
        String material = FTPUtil.sentByFtpClient(date, seq, localfiles, remotepaths);
        logger.info(material);
        String b64 = Base64.encode(material);
        sourceMsg.setMaterial(b64);
        //dev
        //sourceMsg.setCallbackUrl("http://localhost:8099/rst/callbackByPlatform.do");
        //test
        sourceMsg.setCallbackUrl("http://10.29.32.85:8099/channel-1.0-SNAPSHOT/rst/callbackByPlatform.do");
        String content = JaXmlBeanUtil.parseBeanToXml(SourceMsg.class, sourceMsg);

        return content;


    }

    public static String sendMaShangFinTech(String date, String time) {

        Student client = new Student();
        client.setSno("009");
        client.setSex("female");
        client.setName("zz");
        String studentXml = JaXmlBeanUtil.parseBeanToXml(Student.class, client);
        SourceMsg sourceMsg = new SourceMsg();
        sourceMsg.setChannelCode("2000007");
        String seq = "T20000012013090900000000" + new Random().nextInt(1000);
        String localfile1 = "d:/file/car1.jpg";
        String localfile2 = "d:/file/hamilton_clip.mp4";
        String localfile3 = "d:/file/car1.jpg";

        List<String> localfiles = new ArrayList();
        localfiles.add(localfile1);
        localfiles.add(localfile2);
        localfiles.add(localfile3);
        String remotepath1 = "/信贷影像资料/归档/马上金融/" + date + "/" + seq + "/car1.jpg";
        String remotepath2 = "/信贷影像资料/渠道/马上金融/" + date + "/" + seq + "/hamilton_clip.mp4";
        String remotepath3 = "/信贷影像资料/渠道/马上金融/" + date + "/" + seq + "/car1.jpg";
        List<String> remotepaths = new ArrayList<>();
        remotepaths.add(remotepath1);
        remotepaths.add(remotepath2);
        remotepaths.add(remotepath3);

        sourceMsg.setChannelSeq(seq);
        sourceMsg.setChannelDate(date);
        sourceMsg.setChannelTime(time);
        sourceMsg.setMsg(studentXml);
        SourceMsgService sourceMsgService=new SourceMsgServiceImpl();
        int i=sourceMsgService.addSourceMsgInfodb(sourceMsg);
        String addIntodbResult;
        if (i == 1) {
            addIntodbResult = " channel add into sourceMsg db success!";
        } else {
            addIntodbResult = "channel add into sourceMsg fail!";
        }
        logger.info("channel ops:"+addIntodbResult);
        String material = GsonUtil.toJSONString(FTPUtil.sentByFtpClient(date, seq, localfiles, remotepaths));
        logger.info(material);
        String b64 = Base64.encode(material);
        sourceMsg.setMaterial(b64);
        /*sourceMsg.setMaterial(GsonUtil.toJSONString(remotepaths));*/
        //dev
       // sourceMsg.setCallbackUrl("http://localhost:8099/rst/callbackByPlatform.do");
        //test
        sourceMsg.setCallbackUrl("http://10.29.32.85:8099/channel-1.0-SNAPSHOT/rst/callbackByPlatform.do");
        String content = JaXmlBeanUtil.parseBeanToXml(SourceMsg.class, sourceMsg);
        return content;
    }

    public  static String sendToSocket(String date, String time, String channelCode,String type,String content) {
        String data = null;
        try {
            //创建客户端Socket，指定服务器地址和端口
            //dev
            // Socket socket = new Socket("localhost", 5000);
            //test
            Socket socket = new Socket("10.29.32.86", 5000);
            //建立连接后，获取输出流，向服务器端发送信息
            OutputStream os = socket.getOutputStream();
            //输出流包装为打印流
            PrintWriter pw = new PrintWriter(os);
            //向服务器端发送信息
            if(type.equals("PC")){
                if (channelCode.equals("2000008")) {
                    pw.write(sendXWContent(date, time));//写入内存缓冲区
                }
                if (channelCode.equals("2000007")) {
                    pw.write(sendMaShangFinTech(date, time));//写入内存缓冲区
                }
            }
            if(type.equals("Web")){
                pw.write(content);
            }


            pw.flush();//刷新缓存，向服务器端输出信息
            socket.shutdownOutput();//关闭输出流

            //获取输入流，接收服务器端响应信息
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "GBK"));

            while ((data = br.readLine()) != null) {
                if (channelCode.equals("2000008")) {
                    logger.info("我是新网客户端，场景平台服务器端返回信息为：" + data);
                }
                if (channelCode.equals("2000007")) {
                    logger.info("我是马上金融客户端，场景平台服务器端返回信息为：" + data);
                }

            }
            //关闭其他资源
            //关闭资源即相关socket

            if (pw != null) {
                pw.close();
            }
            if (os != null) {
                os.close();
            }
            if (br != null) {
                br.close();
            }

            if (is != null) {
                is.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //long now=System.currentTimeMillis();
        Date now = new Date();
        String date = sdf.format(now);
        SimpleDateFormat sdf2 = new SimpleDateFormat("hhmmss");
        String time = sdf2.format(now);
        sendToSocket(date,time,"XWBank","PC",sendXWContent(date,time));
        sendToSocket(date,time,"MaShangFinTech","PC",sendMaShangFinTech(date,time));

    }
}
