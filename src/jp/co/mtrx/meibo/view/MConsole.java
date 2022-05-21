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
	 * メインメニューを表示する
	 */

	MPatientList patientlist = new MPatientList();
	MApplication application = new MApplication();

	public void displayMainMenu() {
		System.out.println("操作を選択してください");
		System.out.println("-----------------");
		System.out.println("1.患者情報を追加する");
		System.out.println("2.患者情報を一覧する");
		System.out.println("3.患者情報を更新する");
		System.out.println("4.患者情報を削除する");

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
		System.out.println("患者を追加します");
		System.out.println("-----------------");

		String name = null;
		double height = 0f;
		String bloodType = null;

		System.out.println("名前を入力してください");
		name = readString();

		System.out.println("身長を入力してください");
		height = Double.parseDouble(readString());

		System.out.println("血液型を入力してください");
		bloodType = readString();

		displayCreatePatientCofirm(name, height, bloodType);
	}

	public void displayCreatePatientCofirm(String name, double height, String bloodType) {
		System.out.println("以下の内容が正しければ y を入力してください");
		System.out.println("-----------------");
		System.out.println("名前:" + name);
		System.out.println("身長:" + height);
		System.out.println("血液型:" + bloodType);

		String input = readString();
		if ("y".equals(input)) {
			
			patientlist.createPatient(name, height, bloodType);
			
			/* データ保管 */
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

	/* 表示部部分はコンソールに表記 */
	public void displayPatient() {
		System.out.println("患者を一覧します");
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
		System.out.println("更新内容を入力してください");
		System.out.println("--------------------");
		
		System.out.println("更新するリストの番号を入力して下しさい");
		String input = readString();
		
		System.out.println("患者名");
		String name = readString();
		System.out.println("身長");
		double height = Double.parseDouble(readString());
		System.out.println("血液型");
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
		System.out.println("患者を削除します");
		System.out.println("-----------------");
		System.out.println("削除するリストの番号を入力して下しさい");
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
