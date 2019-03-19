package rst.tcp.client;



import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rst.tcp.ftp.FTPUtil;
import rst.tcp.model.Student;
import rst.tcp.model.route.SourceMsg;
import rst.util.GsonUtil;
import rst.util.JaXmlBeanUtil;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;


public class TCPSynchronizeClientSource {
    static Logger logger= LoggerFactory.getLogger(TCPSynchronizeClientSource.class);
    public static void sent(Socket sck,String content){
        logger.info("send begin:"+content);

        OutputStream os=null;
        InputStream is = null;
        try {
            byte[] bstream = content.getBytes("GBK");  //转化为字节流
            os= sck.getOutputStream();   //输出流
            os.write(bstream);
            os.flush();//调用flush()方法将缓冲输出
            sck.shutdownOutput();
            logger.info("send end.");
            logger.info("receive start:");

            StringBuffer reqSb = new StringBuffer();
            //获取输入流，并读取客户端信息
            is = sck.getInputStream();
            byte[] buff = new byte[1024];
            byte[] all = new byte[0];
            int len = 0;
            while ((len = is.read(buff)) != -1) {
                all = ArrayUtils.addAll(all, ArrayUtils.subarray(buff, 0, len));
            }

            String respxml = new String(all, "gbk");
            logger.info("result:"+respxml);
            logger.info("receive end");
        } catch (IOException e) {
            logger.info(e.getMessage());
        } finally {
            //关闭资源
            try {
                if (os != null) {
                    os.close();
                }
                if (is != null) {
                    is.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
    public static void main(String[] args) throws IOException {

        // TODO Auto-generated method stub
        //1.建立TCP连接
        String ip = "localhost";   //服务器端ip地址
        int port = 5000;        //端口号
        Socket sck = new Socket(ip, port);
        //2.传输内容
        SimpleDateFormat sdf=new SimpleDateFormat("YYYYmmdd");
        long now=System.currentTimeMillis();
        String date=sdf.format(now);
        SimpleDateFormat sdf2=new SimpleDateFormat("HHmmss");
        String time=sdf2.format(now);
        String seq="T20000012013090900000000"+new Random().nextInt(1000);
        String localfile1="d:/file/car2.jpg";
        String localfile2="d:/file/hamilton_clip.mp4";
        String localfile3="d:/file/car1.jpg";
        String localfile4="d:/file/orders.csv";
        List<String> localfiles=new ArrayList();
        localfiles.add(localfile1);
        localfiles.add(localfile2);
        localfiles.add(localfile3);
        localfiles.add(localfile4);
        String remotepath1="/信贷影像资料/归档/新网/"+date+"/"+seq+"/car2.jpg";
        String remotepath2="/信贷影像资料/渠道/新网/"+date+"/"+seq+"/hamilton_clip.mp4";
        String remotepath3="/信贷影像资料/渠道/新网/"+date+"/"+seq+"/car1.jpg";
        String remotepath4="/信贷影像资料/渠道/新网/"+date+"/"+seq+"/order.csv";
        List<String> remotepaths=new ArrayList<>();
        remotepaths.add(remotepath1);
        remotepaths.add(remotepath2);
        remotepaths.add(remotepath3);
        remotepaths.add(remotepath4);
        Student client = new Student();
        client.setSno("008");
        client.setSex("male");
        client.setName("test");
        String studentXml = JaXmlBeanUtil.parseBeanToXml(Student.class, client);
        SourceMsg sourceMsg = new SourceMsg();
        sourceMsg.setChannelCode("XWBank");

        sourceMsg.setChannelSeq(seq);
        sourceMsg.setChannelDate(date);
        sourceMsg.setChannelTime(time);
        sourceMsg.setMsg(studentXml);
        sourceMsg.setMaterial(GsonUtil.toJSONString(FTPUtil.sentByFtpClient(date,seq,localfiles,remotepaths)));
        sourceMsg.setCallbackUrl("http://localhost:8099/rst/callback");
        String content = JaXmlBeanUtil.parseBeanToXml(SourceMsg.class, sourceMsg);
        sent(sck,content);

        client = new Student();
        client.setSno("009");
        client.setSex("female");
        client.setName("zz");
        studentXml = JaXmlBeanUtil.parseBeanToXml(Student.class, client);
        sourceMsg = new SourceMsg();
        sourceMsg.setChannelCode("MaShangFinTech");
        seq="T20000012013090900000000"+new Random().nextInt(1000);
        localfile1="d:/file/car1.jpg";
        localfile2="d:/file/hamilton_clip.mp4";
        localfile3="d:/file/car1.jpg";

        localfiles=new ArrayList();
        localfiles.add(localfile1);
        localfiles.add(localfile2);
        localfiles.add(localfile3);
        remotepath1="/信贷影像资料/归档/马上金融/"+date+"/"+seq+"/car1.jpg";
        remotepath2="/信贷影像资料/渠道/马上金融/"+date+"/"+seq+"/hamilton_clip.mp4";
        remotepath3="/信贷影像资料/渠道/马上金融/"+date+"/"+seq+"/car1.jpg";
        remotepaths=new ArrayList<>();
        remotepaths.add(remotepath1);
        remotepaths.add(remotepath2);
        remotepaths.add(remotepath3);

        sourceMsg.setChannelSeq(seq);
        sourceMsg.setChannelDate(date);
        sourceMsg.setChannelTime(time);
        sourceMsg.setMsg(studentXml);
        sourceMsg.setMaterial(GsonUtil.toJSONString(FTPUtil.sentByFtpClient(date,seq,localfiles,remotepaths)));
        /*sourceMsg.setMaterial(GsonUtil.toJSONString(remotepaths));*/
        sourceMsg.setCallbackUrl("http://localhost:8099/rst/callback");
        content = JaXmlBeanUtil.parseBeanToXml(SourceMsg.class, sourceMsg);
        sent(sck,content);

    }



}



