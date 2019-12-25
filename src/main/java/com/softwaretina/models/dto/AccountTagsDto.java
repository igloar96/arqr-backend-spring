package com.softwaretina.models.dto;

import java.util.List;

import com.softwaretina.models.entities.Account;
import com.softwaretina.models.entities.Tag;

public class AccountTagsDto {
	private Account account;
	private List<Tag> tags;
	
	public Account getAccount() {
		return this.account;
	}
	public List<Tag> getTags(){
		return this.tags;
	}
	public void setAccount(Account acnt) {
		this.account = acnt;
	}
	public void setTags(List<Tag> tags){
		this.tags = tags;
	}
}
