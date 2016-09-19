package imu.pcloud.server.action;

import imu.pcloud.server.been.User;
import imu.pcloud.server.model.BaseModel;
import imu.pcloud.server.model.ImageModel;
import imu.pcloud.server.model.UserModel;
import imu.pcloud.server.service.ImageService;
import imu.pcloud.server.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class SetUserHeaderAction extends ActionSupport {
	
	String imageFile;
	String cookies;
	ImageModel result = new ImageModel();
	ImageService imageService = new ImageService();
	UserService userService = new UserService();
	public String getImageFile() {
		return imageFile;
	}
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	public String getCookies() {
		return cookies;
	}
	public void setCookies(String cookies) {
		this.cookies = cookies;
	}
	public ImageModel getResult() {
		return result;
	}
	public void setResult(ImageModel result) {
		this.result = result;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(imageFile);
		User user;
		int status = userService.reLogin(cookies);
		if(status != 0) {
			result.setStatus(status);
			return SUCCESS;
		}
		user = userService.getUser();
		result.setStatus(0);
		imageService.setImage(imageFile, user.getId());
		result.setImage(imageService.getImage());
		return SUCCESS;
	}
	
}
