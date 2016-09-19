package imu.pcloud.server.action;

import java.util.ArrayList;

import imu.pcloud.server.been.Discover;
import imu.pcloud.server.model.DiscoverModel;
import imu.pcloud.server.service.DiscoverService;

import com.opensymphony.xwork2.ActionSupport;

public class DiscoverAction extends ActionSupport {
	DiscoverModel result = new DiscoverModel();
	DiscoverService discoverService = new DiscoverService();
	public DiscoverModel getResult() {
		return result;
	}

	public void setResult(DiscoverModel result) {
		this.result = result;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Discover> dList = discoverService.findAll(); 
		result.setDiscoverList(dList);
		return SUCCESS;
	}
	
	

}
