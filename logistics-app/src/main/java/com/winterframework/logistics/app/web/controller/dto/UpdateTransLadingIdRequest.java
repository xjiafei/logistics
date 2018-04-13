package com.winterframework.logistics.app.web.controller.dto;

import java.io.Serializable;

public class UpdateTransLadingIdRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6534284666181633946L;
	private Long id;
	private String ladingId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLadingId() {
		return ladingId;
	}

	public void setLadingId(String ladingId) {
		this.ladingId = ladingId;
	}

}
