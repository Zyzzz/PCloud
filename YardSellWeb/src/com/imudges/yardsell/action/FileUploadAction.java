package com.imudges.yardsell.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.IntBuffer;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.imudges.yardsell.DAO.UserDAO;
import com.imudges.yardsell.service.ImageService;
import com.imudges.yardsell.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 获取Android端上传过来的信息
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class FileUploadAction extends ActionSupport {
    // 上传文件域
    private File image;
    // 上传文件类型
    private String imageContentType;
    // 封装上传文件名
    private String imageFileName;
    // 接受依赖注入的属性
    private String savePath;
    private String MyUrl;
    @Override
    public String execute() {
        HttpServletRequest request=ServletActionContext.getRequest();
        FileOutputStream fos = null;
        FileInputStream fis = null;
        try {
            System.out.println("获取Android端传过来的普通信息：");
            System.out.println("用户名："+request.getParameter("email"));
            System.out.println("文件名："+request.getParameter("fileName"));
            System.out.println("文件名："+request.getParameter("hashCode"));
            System.out.println("获取Android端传过来的文件信息：");
            System.out.println("文件存放目录: "+getSavePath());
            System.out.println("文件名称: "+imageFileName);
            System.out.println("文件大小: "+image.length());
            System.out.println("文件类型: "+imageContentType);
            File filePath=new File(getSavePath());
            if(!filePath.exists()){
            	filePath.mkdir();
            }
            String url = getSavePath()+"\\"+imageFileName;
            MyUrl=url;
            System.out.println("url: "+url);
            fos = new FileOutputStream(getSavePath() + "\\" + getImageFileName());
            fis = new FileInputStream(getImage());
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
           
        } catch (Exception e) {
            System.out.println("文件上传失败");
            e.printStackTrace();
        } finally {
            close(fos, fis);
        }
        try {
			rename(MyUrl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return SUCCESS;
    }

    /**
     * 文件存放目录
     * 
     * @return
     */
    public String getSavePath() throws Exception{
        return ServletActionContext.getServletContext().getRealPath(savePath); 
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    private void close(FileOutputStream fos, FileInputStream fis) {
        if (fis != null) {
            try {
                fis.close();
                fis=null;
            } catch (IOException e) {
                System.out.println("FileInputStream关闭失败");
                e.printStackTrace();
            }
        }
        if (fos != null) {
            try {
                fos.close(); 
                fis=null;
            } catch (IOException e) {
                System.out.println("FileOutputStream关闭失败");
                e.printStackTrace();
            }
        }
    }
    private String getPath(String oldpath){
    	String pathTemp="";
    	int length=oldpath.split("\\\\").length;
    	pathTemp=oldpath.split("\\\\")[length-1];
    	pathTemp="\\"+oldpath.split("\\\\")[length-2]+"\\"+pathTemp;
    	return pathTemp;
    }
    
    private String rename(String oldpath) throws Exception{
    	UserService userService=new UserService();
    	String hashCode=ServletActionContext.getRequest().getParameter("hashCode");
    	String email=ServletActionContext.getRequest().getParameter("email");
    	int userId=userService.getUserId(email, hashCode);
    	if (userId==-1) {
			File file=new File(oldpath);
			file.delete();
			return null;
		}
    	String houzhui=oldpath.split("\\\\")[oldpath.split("\\\\").length-1].split("\\.")[1];
    	String newParhStr=getSavePath()+"\\"+ServletActionContext.getRequest().getParameter("email")+"_1."+houzhui;
    	File oldFile=new File(oldpath);
    	File newFile=new File(newParhStr);
    	if (newFile.exists()) {
			newFile.delete();
		}
    	System.out.println(oldpath);
    	System.out.println(newParhStr);
    	oldFile.renameTo(newFile);
    	ImageService imageService=new ImageService();
    	imageService.saveImage(getPath(newParhStr), email, hashCode);
    	return newParhStr;
    }

}
