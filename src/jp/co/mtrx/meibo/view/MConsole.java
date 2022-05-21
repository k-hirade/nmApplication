/**
 * 
 */
package jp.co.mtrx.meibo.view;

import java.io.BufferedReader;

import jp.co.mtrx.meibo.MApplication;
import jp.co.mtrx.meibo.model.MPatient;
import jp.co.mtrx.meibo.model.MPatientList;
import java.io.InputStreamReader;
import java.io.Serializable;

/**
 * @author k-hirade
 *
 */
public class MConsole implements Serializable {
	/**
	 * ���C�����j���[��\������
	 */

	MPatientList patientlist = new MPatientList();
	MApplication application = new MApplication();

	public void displayMainMenu() {
		System.out.println("�����I�����Ă�������");
		System.out.println("-----------------");
		System.out.println("1.���ҏ���ǉ�����");
		System.out.println("2.���ҏ����ꗗ����");
		System.out.println("3.���ҏ����X�V����");
		System.out.println("4.���ҏ����폜����");

		String input = readString();
		if ("1".equals(input)) {
			displayCreatePatient();
		} else if ("2".equals(input)) {
			displayPatient();
		} else if ("3".equals(input)) {
			updatePatient();
		} else if ("4".equals(input)) {
			deletePatient();
		} else {
			displayMainMenu();
		}
	}

	public void displayCreatePatient() {
		System.out.println("���҂�ǉ����܂�");
		System.out.println("-----------------");

		String name = null;
		double height = 0f;
		String bloodType = null;

		System.out.println("���O����͂��Ă�������");
		name = readString();

		System.out.println("�g������͂��Ă�������");
		height = Double.parseDouble(readString());

		System.out.println("���t�^����͂��Ă�������");
		bloodType = readString();

		displayCreatePatientCofirm(name, height, bloodType);
	}

	public void displayCreatePatientCofirm(String name, double height, String bloodType) {
		System.out.println("�ȉ��̓��e����������� y ����͂��Ă�������");
		System.out.println("-----------------");
		System.out.println("���O:" + name);
		System.out.println("�g��:" + height);
		System.out.println("���t�^:" + bloodType);

		String input = readString();
		if ("y".equals(input)) {
			
			patientlist.createPatient(name, height, bloodType);
			
			/* �f�[�^�ۊ� */
			try {
				application.writeObjectToFile(application.saveFileName,patientlist);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			displayMainMenu();
		} else {
			displayCreatePatient();
		}
	}

	/* �\���������̓R���\�[���ɕ\�L */
	public void displayPatient() {
		System.out.println("���҂��ꗗ���܂�");
		System.out.println("-----------------");
				
		for(int i=0;i<patientlist.getPatients().size();i++) {
			MPatient patient = patientlist.getPatients().get(i);
			String name = patient.getName();
			Double height =patient.getHeight();
			String bloodType = patient.getBloodType();
			System.out.println(i+" name: "+name+" height "+height+" bloodType "+bloodType);
		}
		displayMainMenu();
	}

	public void updatePatient() {
		System.out.println("�X�V���e����͂��Ă�������");
		System.out.println("--------------------");
		
		System.out.println("�X�V���郊�X�g�̔ԍ�����͂��ĉ�������");
		String input = readString();
		
		System.out.println("���Җ�");
		String name = readString();
		System.out.println("�g��");
		double height = Double.parseDouble(readString());
		System.out.println("���t�^");
		String bloodType = readString();
		
		int num = Integer.parseInt(input);
		MPatient patient = patientlist.getPatients().get(num);
		patient.setName(name);
		patient.setHeight(height);
		patient.setBloodType(bloodType);
		System.out.println("name:"+name+"height"+height+"bloodType"+bloodType);

		displayMainMenu();
	}

	public void deletePatient() {
		System.out.println("���҂��폜���܂�");
		System.out.println("-----------------");
		System.out.println("�폜���郊�X�g�̔ԍ�����͂��ĉ�������");
		String input = readString();
		
		int num = Integer.parseInt(input);
		MPatient patient = patientlist.getPatients().remove(num);

		displayMainMenu();
	}

	public static String readString() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			return br.readLine();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
