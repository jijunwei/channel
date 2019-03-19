package rst.tcp.client;


import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rst.tcp.model.Student;
import rst.tcp.model.route.SourceMsg;
import rst.util.JaXmlBeanUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class TCPAnsynchronizeClientSource {
    static Logger logger= LoggerFactory.getLogger(TCPAnsynchronizeClientSource.class);
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
                if (sck != null) {
                    sck.close();
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

        Student client = new Student();
        client.setSno("008");
        client.setSex("male");
        client.setName("test");
        String studentXml = JaXmlBeanUtil.parseBeanToXml(Student.class, client);
        SourceMsg sourceMsg = new SourceMsg();
        sourceMsg.setChannelCode("XWBank");
        sourceMsg.setChannelSeq("T200000120130909000000001234");
        sourceMsg.setChannelDate("20181226");
        sourceMsg.setChannelTime("102109");
        sourceMsg.setMsg(studentXml);




        String content = JaXmlBeanUtil.parseBeanToXml(SourceMsg.class, sourceMsg);
        //sent(sck,content);


        client = new Student();
        client.setSno("009");
        client.setSex("female");
        client.setName("zz");
        studentXml = JaXmlBeanUtil.parseBeanToXml(Student.class, client);
        sourceMsg = new SourceMsg();
        sourceMsg.setChannelCode("HengShuiBank");
        sourceMsg.setChannelSeq("T200000120130909000000001235");
        sourceMsg.setChannelDate("20181227");
        sourceMsg.setChannelTime("102110");
        sourceMsg.setMsg(studentXml);
        content = JaXmlBeanUtil.parseBeanToXml(SourceMsg.class, sourceMsg);
        sent(sck,content);

    }
}



