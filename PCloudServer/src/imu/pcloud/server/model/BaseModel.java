package imu.pcloud.server.model;

public class BaseModel {

	protected String result;
	protected int status;
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
