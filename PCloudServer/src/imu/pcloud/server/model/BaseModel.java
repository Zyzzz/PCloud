package imu.pcloud.server.model;

import imu.pcloud.server.utils.Information;

public class BaseModel {

	protected String result;
	protected int status;
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
		setResult(Information.getInstance().getErrorInfo(status));
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
