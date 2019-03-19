package rst.tcp.ftp;

import rst.util.GsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FTPUtil {

    public static String sentByFtpClient(String date, String seq, List<String> localfiles, List<String> remotepaths){
        List<String> successList=new ArrayList<>();
        try {
            ContinueFTP2 myFtp = new ContinueFTP2("127.0.0.1", "ftp", "ftp", "21", "/信贷影像资料/归档/car1.jpg", "d:/car1.jpg");
            myFtp.connect(myFtp.ftpURL, new java.lang.Integer(myFtp.ftpport), myFtp.username, myFtp.pwd);
            //myFtp.ftpClient.makeDirectory(new String("信贷影像资料".getBytes("GBK"), "iso-8859-1"));
            for(int i=0;i<localfiles.size();i++){
                myFtp.ftpClient.changeWorkingDirectory(new String("/".getBytes("GBK"), "iso-8859-1"));
                ContinueFTP2.UploadStatus result=myFtp.upload(localfiles.get(i), remotepaths.get(i));
                if (result==ContinueFTP2.UploadStatus.Upload_New_File_Success) {
                    successList.add(remotepaths.get(i));
                }
                System.out.println(result);
            }
            myFtp.disconnect();
        } catch (IOException e) {
            System.out.println("连接FTP出错：" + e.getMessage());
        }
        return GsonUtil.toJSONString(successList);
    }
}
