package com.buzzters.hotspotz.model;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.datastore.GeoPt;
import com.google.appengine.api.datastore.Key;


@PersistenceCapable
public class UserLocation {

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	@Persistent
	private String group;
	
	@Persistent
	private Email userEmail;
	
	@Persistent
	private GeoPt geoPt;	
	
	@Persistent
	private Date date;
	
	public UserLocation(String group, Email userEmail, GeoPt geoPt, Date date)
	{
		this.group = group;
		this.userEmail = userEmail;		
		this.geoPt = geoPt;
		this.date = date;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Email getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(Email userEmail) {
		this.userEmail = userEmail;
	}

	public GeoPt getGeoPt() {
		return geoPt;
	}
	
	public void setGeoPt(GeoPt geoPt) {
		this.geoPt = geoPt;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String toSerializedString(){
		return group + ":" + userEmail.getEmail() + ":" + geoPt.getLatitude() + ":" + geoPt.getLongitude(); 
	}
		
}
