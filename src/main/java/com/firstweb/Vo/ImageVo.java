package com.firstweb.Vo;

public class ImageVo {
	String image_name;
	String origin_name;
	String path;
	public String getImage_name() {
		return image_name;
	}
	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}
	public String getOrigin_name() {
		return origin_name;
	}
	public void setOrigin_name(String origin_name) {
		this.origin_name = origin_name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public ImageVo(String image_name, String origin_name, String path) {
		super();
		this.image_name = image_name;
		this.origin_name = origin_name;
		this.path = path;
	}
	
	
}
