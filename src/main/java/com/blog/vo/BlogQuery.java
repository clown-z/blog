package com.blog.vo;

public class BlogQuery {
	
	private String title;
	private Long typeId;
	private boolean isRecommend;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public boolean isRecommend() {
		return isRecommend;
	}
	public void setRecommend(boolean isRecommend) {
		this.isRecommend = isRecommend;
	}

}
