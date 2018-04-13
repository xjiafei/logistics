package com.winterframework.logistics.portal.dto;


public class PtlCompanyWebResponse {
	private Long id;

    private String name;

    private String logo;

    private String intro;

    private String philo;

    private String cooper;

    private String [] homePics;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getPhilo() {
		return philo;
	}

	public void setPhilo(String philo) {
		this.philo = philo;
	}

	public String getCooper() {
		return cooper;
	}

	public void setCooper(String cooper) {
		this.cooper = cooper;
	}

	public String[] getHomePics() {
		return homePics;
	}

	public void setHomePics(String[] homePics) {
		this.homePics = homePics;
	}
	
    
}
