/**
 * 
 */
package jp.co.mtrx.meibo.model;

import java.io.Serializable;

/**
 * @author k-hirade
 *
 */
public class MPatient implements Serializable {
	
	/* éÅñº */
	private String name = null;

	/* êgí∑ */
	private double height = 0f;

	/* ååâtå^ */
	private String bloodType = null;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * @return the bloodType
	 */
	public String getBloodType() {
		return bloodType;
	}

	/**
	 * @param bloodType the bloodType to set
	 */
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	
}
