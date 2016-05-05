package com.imudges.yardsellapp.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;

import com.imudges.yardsellapp.bean.UserModel;


public class JsonToObject {
	public UserModel JsonToUserModel(String jsonString){
		JSONObject jsonObject=null;
		UserModel userModel=null;
		try {
			userModel=new UserModel();
			userModel.setStatus(500);
			userModel.setResult(new Information().getErrorInfo(500));
			jsonObject=new JSONObject(jsonString);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			userModel.setStatus(jsonObject.getInt("status"));
			userModel.setResult(jsonObject.getString("result"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			userModel.setVersion(jsonObject.getString("version"));
		} catch (Exception e) {
			// TODO: handle exception
			userModel.setVersion("hupeng@imudges");
		}
		try {
			userModel.setUserId(jsonObject.getInt("userId"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			userModel.setEmail(jsonObject.getString("email"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			userModel.setUserName(jsonObject.getString("userName"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			userModel.setPassword(jsonObject.getString("password"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			userModel.setAddress(jsonObject.getString("address"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			userModel.setPhoneNumber(jsonObject.getString("phoneNumber"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			userModel.setIsOpen(jsonObject.getInt("isOpen"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			userModel.setLatitude(jsonObject.getDouble("latitude"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			userModel.setLongtitude(jsonObject.getDouble("longtitude"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			userModel.setCommodityInfo(jsonObject.getString("commodityInfo"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			userModel.setSellDate(new Date(Long.parseLong(jsonObject.getString("sellDate"))));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			userModel.setHashCode(jsonObject.getString("hashCode"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			userModel.setEndDate(new Date(Long.parseLong(jsonObject.getString("endDate"))));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			userModel.setIsVerify(jsonObject.getInt("isVerify"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			userModel.setEmailDate(jsonObject.getString("emailDate"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			userModel.setImage1(jsonObject.getInt("image1"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			userModel.setImage2(jsonObject.getInt("image2"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			userModel.setImage3(jsonObject.getInt("image3"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return userModel;
	}
	
	
	public List JsonToUserList(String jsonString){
		JSONArray jsonArray=null;
		UserModel userModel=null;
		List list=new ArrayList();
		
		try {
			jsonArray=new JSONArray(jsonString);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<jsonArray.length();i++){
			JSONObject jsonObject=null;
			try {
				jsonObject=jsonArray.getJSONObject(i);
				userModel=new UserModel();
				userModel.setStatus(500);
				userModel.setResult(new Information().getErrorInfo(500));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				userModel.setStatus(jsonObject.getInt("status"));
				userModel.setResult(jsonObject.getString("result"));
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				userModel.setUserId(jsonObject.getInt("userId"));
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				userModel.setEmail(jsonObject.getString("email"));
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				userModel.setUserName(jsonObject.getString("userName"));
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				userModel.setPassword(jsonObject.getString("password"));
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				userModel.setAddress(jsonObject.getString("address"));
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				userModel.setPhoneNumber(jsonObject.getString("phoneNumber"));
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				userModel.setIsOpen(jsonObject.getInt("isOpen"));
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				userModel.setLatitude(jsonObject.getDouble("latitude"));
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				userModel.setLongtitude(jsonObject.getDouble("longtitude"));
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				userModel.setCommodityInfo(jsonObject.getString("commodityInfo"));
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				userModel.setSellDate(new Date(Long.parseLong(jsonObject.getString("sellDate"))));
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				userModel.setHashCode(jsonObject.getString("hashCode"));
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				userModel.setEndDate(new Date(Long.parseLong(jsonObject.getString("endDate"))));
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				userModel.setIsVerify(jsonObject.getInt("isVerify"));
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				userModel.setEmailDate(jsonObject.getString("emailDate"));
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				userModel.setImage1(jsonObject.getInt("image1"));
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				userModel.setImage2(jsonObject.getInt("image2"));
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				userModel.setImage3(jsonObject.getInt("image3"));
			} catch (Exception e) {
				// TODO: handle exception
			}
			list.add(userModel);
		}
		return list;
	}
	

}
