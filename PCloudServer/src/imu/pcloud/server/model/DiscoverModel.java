package imu.pcloud.server.model;

import imu.pcloud.server.been.Discover;

import java.util.ArrayList;

public class DiscoverModel extends BaseModel {
	ArrayList<Discover> discoverList;

	public ArrayList<Discover> getDiscoverList() {
		return discoverList;
	}

	public void setDiscoverList(ArrayList<Discover> discoverList) {
		this.discoverList = discoverList;
	}
	
}
