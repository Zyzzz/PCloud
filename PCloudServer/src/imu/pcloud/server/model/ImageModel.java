package imu.pcloud.server.model;

import imu.pcloud.server.been.Image;

public class ImageModel extends BaseModel {
	Image image;

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
}
