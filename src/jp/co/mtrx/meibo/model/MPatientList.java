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
	/* �v�f�̏W�� */
	/* List�ɂ�MPatient�݂̂����� */

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
		
		//patients�Ƃ������X�g��p�����
		//���X�g�͉ϒ�
		patients.add(p);
	}
	
}
