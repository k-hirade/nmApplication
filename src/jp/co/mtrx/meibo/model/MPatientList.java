/**
 * 
 */
package jp.co.mtrx.meibo.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author k-hirade
 *
 */
public class MPatientList implements Serializable {
	
	private ArrayList<MPatient> patients = new ArrayList<MPatient>();
	/* 要素の集合 */
	/* ListにはMPatientのみが入る */

	/**
	 * @return the parients
	 */
	public ArrayList<MPatient> getPatients() {
		return patients;
	}
	
	/**
	 * 
	 * @param name
	 * @param height
	 * @param bloodType
	 */
	public void createPatient(String name,double height,String bloodType){
		MPatient p=new MPatient();
		p.setName(name);
		p.setHeight(height);
		p.setBloodType(bloodType);
		
		//patientsというリストにpを入力
		//リストは可変調
		patients.add(p);
	}
	
}
