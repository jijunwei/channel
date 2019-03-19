package rst.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import org.springframework.stereotype.Service;
import rst.util.base64.Base64;

@Service
public class FileUtil {
	public File saveFile(String base64Contract,String path,String fName) {
		byte[] by = Base64.decode(base64Contract);
		FileOutputStream fileout = null;
		String fileName = path + fName;
		File file = new File(fileName);
		
		try {
			fileout = new FileOutputStream(file);
			fileout.write(by, 0, by.length);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}
	
	
	 /** *//**文件重命名 
	    * @param path 文件目录 
	    * @param oldname  原来的文件名 
	    * @param newname 新文件名 
	    */ 
	    public void renameFile(String path,String oldname,String newname){ 
	        if(!oldname.equals(newname)){//新的文件名和以前文件名不同时,才有必要进行重命名 
	            File oldfile=new File(path+"/"+oldname); 
	            File newfile=new File(path+"/"+newname); 
	            if(!oldfile.exists()){
	                return;//重命名文件不存在
	            }
	            if(newfile.exists())//若在该目录下已经有一个文件和新文件名相同，则不允许重命名 
	                System.out.println(newname+"已经存在！"); 
	            else{ 
	                oldfile.renameTo(newfile); 
	            } 
	        }else{
	            System.out.println("新文件名和旧文件名相同...");
	        }
	    }
}
