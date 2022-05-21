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
			/* 病院の初期化 */
			if (isFilePathExists(saveFileName)) {
				System.out.println("読み込まれました");
				/* データファイルが存在する場合ファイルから読み込む */
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

	/* ファイルまたはフォルダが存在するかを確認 */
	/**
	 * 
	 * @param filePath ファイルパス
	 * @return 存在する場合true
	 */
	public static boolean isFilePathExists(String filePath) {
		File file = new File(filePath);

		return file.exists();
	}

	/* オブジェクトをファイルから読み込む */
	/**
	 * 
	 * @param filePath 入力ファイルパス
	 * @return よみこんだ オブジェクト
	 * @throws Exception
	 */
	public static Object readObjectFromFile(String filePath) throws Exception {
		ObjectInputStream stream = new ObjectInputStream(new FileInputStream(filePath));
		Object o = stream.readObject();
		stream.close();

		return o;
	}

	/* オブジェクトをファイルに書きだす */
	/**
	 * 
	 * @param filePath 出力ファイルパス
	 * @param o        出力対象オブジェ九tp
	 * @throws Exception
	 */
	public static void writeObjectToFile(String filePath, Object o) throws Exception {
		ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(filePath));
		stream.writeObject(o);
		stream.close();
	}

	/* プロパティファイルからプロパティを読み込む */
	public static Properties readPropertiesFromFile(String filePath) throws Exception {
		Properties properties = new Properties();
		InputStream in = new BufferedInputStream(new FileInputStream(filePath));
		properties.load(in);
		in.close();

		return properties;
	}
}
