package imu.pcloud.server.action;

import imu.pcloud.server.DAO.ImageDAO;
import imu.pcloud.server.been.Image;
import imu.pcloud.server.model.ImageModel;
import imu.pcloud.server.service.ImageService;

import com.opensymphony.xwork2.ActionSupport;

public class GetImageAction extends ActionSupport {
	ImageModel result;
	Integer imageId;
	ImageService imageService = new ImageService();
	public ImageModel getResult() {
		return result;
	}
	public void setResult(ImageModel result) {
		this.result = result;
	}
	public Integer getImageId() {
		return imageId;
	}
	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Image image;
		image = imageService.getImage(imageId);
		result = new ImageModel();
		result.setImage(image);
		result.setStatus(0);
		return SUCCESS;
	}
	
}
