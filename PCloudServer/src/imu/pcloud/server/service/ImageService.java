package imu.pcloud.server.service;

import imu.pcloud.server.DAO.ImageDAO;
import imu.pcloud.server.DAO.UserDAO;
import imu.pcloud.server.been.Image;
import imu.pcloud.server.been.User;
import imu.pcloud.server.model.ImageModel;

public class ImageService {

	ImageDAO imageDAO = new ImageDAO();
	UserDAO userDAO = new UserDAO();
	ImageModel imageModel = new ImageModel();
	Image image;
	public ImageModel getImageModel() {
		return imageModel;
	}
	public void setImageModel(ImageModel imageModel) {
		this.imageModel = imageModel;
	}
	
	public void addNewImage(String file) {
		image = new Image();
		image.setImageFile(file);
		imageDAO.save(image);
	}
	
	public User setImage(String file, int userId) {
		User user = userDAO.findById(userId);
		addNewImage(file);
		user = userDAO.findById(userId);
		user.setHeadImageId(image.getId());
		userDAO.save(user);
		return user;
	}
	
	public Image getImage(int imageId) {
		image = imageDAO.findById(imageId);
		return image;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	
	
}
