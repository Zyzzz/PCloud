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
 * ��ȡAndroid���ϴ���������Ϣ
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class FileUploadAction extends ActionSupport {
    // �ϴ��ļ���
    private File image;
    // �ϴ��ļ�����
    private String imageContentType;
    // ��װ�ϴ��ļ���
    private String imageFileName;
    // ��������ע�������
    private String savePath;
    private String MyUrl;
    @Override
    public String execute() {
        HttpServletRequest request=ServletActionContext.getRequest();
        FileOutputStream fos = null;
        FileInputStream fis = null;
        try {
            System.out.println("��ȡAndroid�˴���������ͨ��Ϣ��");
            System.out.println("�û�����"+request.getParameter("email"));
            System.out.println("�ļ�����"+request.getParameter("fileName"));
            System.out.println("�ļ�����"+request.getParameter("hashCode"));
            System.out.println("��ȡAndroid�˴��������ļ���Ϣ��");
            System.out.println("�ļ����Ŀ¼: "+getSavePath());
            System.out.println("�ļ�����: "+imageFileName);
            System.out.println("�ļ���С: "+image.length());
            System.out.println("�ļ�����: "+imageContentType);
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
            System.out.println("�ļ��ϴ�ʧ��");
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
     * �ļ����Ŀ¼
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
                System.out.println("FileInputStream�ر�ʧ��");
                e.printStackTrace();
            }
        }
        if (fos != null) {
            try {
                fos.close(); 
                fis=null;
            } catch (IOException e) {
                System.out.println("FileOutputStream�ر�ʧ��");
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
