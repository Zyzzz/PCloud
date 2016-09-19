package imu.pcloud.server.action;

import imu.pcloud.server.been.Discover;
import imu.pcloud.server.model.DiscoverModel;
import imu.pcloud.server.service.DiscoverService;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

public class UserDiscoverAction extends ActionSupport {
	DiscoverModel result = new DiscoverModel();
	DiscoverService discoverService = new DiscoverService();
	int userId;
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public DiscoverModel getResult() {
		return result;
	}

	public void setResult(DiscoverModel result) {
		this.result = result;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Discover> dList = discoverService.findByUserId(userId); 
		result.setDiscoverList(dList);
		return SUCCESS;
	}
	
}
