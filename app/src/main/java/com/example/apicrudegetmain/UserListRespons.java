package com.example.apicrudegetmain;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class UserListRespons{

	@SerializedName("per_page")
	private int perPage;

	@SerializedName("total")
	private int total;

	@SerializedName("data")
	private List<UserModel> data;

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("support")
	private Support support;

	public int getPerPage(){
		return perPage;
	}

	public int getTotal(){
		return total;
	}

	public List<UserModel> getData(){
		return data;
	}

	public int getPage(){
		return page;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public Support getSupport(){
		return support;
	}
}