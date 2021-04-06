package com.prakash;

import java.util.Arrays;

public class Organizations {
	
	private Integer _id;
	private String url;
	private String external_id;
	private String name;
	private String domain_names;
	private String created_at;
	private String details;
	private boolean shared_tickets;
	private String tags;
	
	
	public Integer get_id() {
		return _id;
	}
	public void set_id(Integer _id) {
		this._id = _id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getExternal_id() {
		return external_id;
	}
	public void setExternal_id(String external_id) {
		this.external_id = external_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDomain_names() {
		return domain_names;
	}
	public void setDomain_names(String domain_names) {
		this.domain_names = domain_names;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public boolean getShared_tickets() {
		return shared_tickets;
	}
	public void setShared_tickets(boolean shared_tickets) {
		this.shared_tickets = shared_tickets;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	@Override
	public String toString() {
		return "Organizations [_id=" + _id + ", url=" + url + ", external_id=" + external_id + ", name=" + name
				+ ", domain_names=" + domain_names + ", created_at=" + created_at + ", details=" + details
				+ ", shared_tickets=" + shared_tickets + ", tags=" + tags + "]";
	}

}
