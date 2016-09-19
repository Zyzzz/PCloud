package imu.pcloud.server.been;

/**
 * Image entity. @author MyEclipse Persistence Tools
 */

public class Image implements java.io.Serializable {

	// Fields

	private Integer id;
	private String imageFile;

	// Constructors

	/** default constructor */
	public Image() {
	}

	/** full constructor */
	public Image(String imageFile) {
		this.imageFile = imageFile;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImageFile() {
		return this.imageFile;
	}

	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}

}