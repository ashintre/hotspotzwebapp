package com.buzzters.hotspotz.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.GeoPt;
import com.google.appengine.api.datastore.Key;


@PersistenceCapable
public class MeetingPlace {

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	@Persistent
	private String nameOfPlace;
	
	@Persistent
	private String tag;
	
	@Persistent
	private GeoPt geoPt;			
	
	public MeetingPlace(String tag, String nameOfPlace, GeoPt geoPt)
	{
		this.nameOfPlace = nameOfPlace;
		this.tag = tag;		
		this.geoPt = geoPt;		
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getNameOfPlace() {
		return nameOfPlace;
	}

	public void setNameOfPlace(String nameOfPlace) {
		this.nameOfPlace = nameOfPlace;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public GeoPt getGeoPt() {
		return geoPt;
	}

	public void setGeoPt(GeoPt geoPt) {
		this.geoPt = geoPt;
	}

	public String toSerializedString(){
		return tag + ":" + nameOfPlace + ":" + geoPt.getLatitude() + ":" + geoPt.getLongitude(); 
	}		
}
