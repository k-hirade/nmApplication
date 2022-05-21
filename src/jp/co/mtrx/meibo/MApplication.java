/**
 * 
 */
package jp.co.mtrx.meibo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Properties;

import jp.co.mtrx.meibo.model.MPatientList;
import jp.co.mtrx.meibo.view.MConsole;

/**
 * @author k-hirade
 *
 */
public class MApplication implements Serializable {

	/**
	 * @param args
	 */
	static MConsole console = new MConsole();
	public static String saveFileName = null;
	public static MConsole mconsole = null;
	public static MPatientList mpatientlist = null;

	public static void main(String[] args) {

		try {
			Properties prop = readPropertiesFromFile("hms.properties");

			saveFileName = prop.getProperty("hms.saveDate.seliarize.filePath");

//			System.out.println(isFilePathExists(saveFileName));
			/* �a�@�̏����� */
			if (isFilePathExists(saveFileName)) {
				System.out.println("�ǂݍ��܂�܂���");
				/* �f�[�^�t�@�C�������݂���ꍇ�t�@�C������ǂݍ��� */
				mpatientlist =(MPatientList)readObjectFromFile(saveFileName);
				mconsole = new MConsole();
			}
			else {
				mconsole = new MConsole();
			}

			MConsole console = new MConsole();
			console.displayMainMenu();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* �t�@�C���܂��̓t�H���_�����݂��邩���m�F */
	/**
	 * 
	 * @param filePath �t�@�C���p�X
	 * @return ���݂���ꍇtrue
	 */
	public static boolean isFilePathExists(String filePath) {
		File file = new File(filePath);

		return file.exists();
	}

	/* �I�u�W�F�N�g���t�@�C������ǂݍ��� */
	/**
	 * 
	 * @param filePath ���̓t�@�C���p�X
	 * @return ��݂��� �I�u�W�F�N�g
	 * @throws Exception
	 */
	public static Object readObjectFromFile(String filePath) throws Exception {
		ObjectInputStream stream = new ObjectInputStream(new FileInputStream(filePath));
		Object o = stream.readObject();
		stream.close();

		return o;
	}

	/* �I�u�W�F�N�g���t�@�C���ɏ������� */
	/**
	 * 
	 * @param filePath �o�̓t�@�C���p�X
	 * @param o        �o�͑ΏۃI�u�W�F��tp
	 * @throws Exception
	 */
	public static void writeObjectToFile(String filePath, Object o) throws Exception {
		ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(filePath));
		stream.writeObject(o);
		stream.close();
	}

	/* �v���p�e�B�t�@�C������v���p�e�B��ǂݍ��� */
	public static Properties readPropertiesFromFile(String filePath) throws Exception {
		Properties properties = new Properties();
		InputStream in = new BufferedInputStream(new FileInputStream(filePath));
		properties.load(in);
		in.close();

		return properties;
	}
}
