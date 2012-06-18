package be.irisnet.cirb.fixmystreet.model;

import java.io.File;

import android.util.Log;

import com.google.android.maps.GeoPoint;

/**
 * FMSDossier is an object used to store a submission for application fixmystreet.
 * @author Tdesany
 *
 */
public class FMSDossier {

	private String description;
	private String type;
	private File picture;
	private GeoPoint location;

	/**
	 * Constructor.
	 */
	public FMSDossier() {
		Log.i(FMSDossier.class.getName(), "The object FMSDossier has been build");
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public File getPicture() {
		return picture;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	public GeoPoint getLocation() {
		return location;
	}

	public void setLocation(GeoPoint location) {
		this.location = location;
	}
	
}
