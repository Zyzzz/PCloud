package com.imudges.yardsell.service;

import com.imudges.yardsell.DAO.ImageDAO;
import com.imudges.yardsell.DAO.UserDAO;
import com.imudges.yardsell.bean.Image;
import com.imudges.yardsell.bean.User;

public class ImageService {

	ImageDAO imageDao = new ImageDAO();
	Image image = new Image();
	
	public String getUrlById(int imageId) {
		
		image = imageDao.findById(imageId);
		return image.getUrl();
	}
	
	public String[] getUserImageUrl(int userId) {
		
		String[] strArr = new String[3];
		User user = new UserDAO().findById(userId);
		strArr[0] = getUrlById(user.getImage1());
		strArr[1] = getUrlById(user.getImage2());
		strArr[2] = getUrlById(user.getImage3());
		return strArr;
	}
	public int saveImage(String url,String email,String hashCode){
		image.setUrl(url);
		imageDao.save(image);
		int imageId = image.getImageId();
		UserService userService = new UserService();
		int result = userService.setImage(email, hashCode, imageId);
		return result;
	}
}
