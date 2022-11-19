package gfrolik;

import java.io.IOException;

/**
 * Tests the methods WortEintrag, WortListe & WortTrainer
 * @author gioia
 * @version 2022-09-15
 */
public class WortTest { 

	/**
	 * main method
	 * 
	 * @param args basic parameter
	 * @throws IOException ex
	 */
	public static void main(String args[]) throws IOException {

		WortListe wL = new WortListe();

		// add words (checkURL)
		System.out.println("--->ADD WORDS");
		System.out.println("addWord: " + wL.addWord("Fenster",
				"http://clipartstation.com/wp-content/uploads/2017/11/fenster-schlie%C3%9Fen-clipart-1.jpg"));
		System.out.println("addWord: "
				+ wL.addWord("Buecherregal", "http://egress.storeden.net/jpg/5f3d723cbe7ea09f9f8b6473/file.jpg"));
		System.out.println("addWord: " + wL.addWord("Kugelschreiber",
				"https://cdn.shopify.com/s/files/1/0782/2043/products/Pilot-acroball-black_grande.jpg?v=1530364894"));
		System.out.println("addWord: " + wL.addWord("Igel",
				"https://4.bp.blogspot.com/-Y2eGhoh0HZs/UoR8UuoaRlI/AAAAAAAABCg/lyQjEiBYqNw/s1600/Igel-Foto+Gustav+Bergman.jpg"));
		System.out.println("addWord: "
				+ wL.addWord("Minute", "http://tinyhouseadvisor.com/wp-content/uploads/1-minute-400x345.jpg"));
		try {
			System.out.println("addWord: " + wL.addWord("Baum", "baum.de"));
			System.out.println("addWord: " + wL.addWord("p", "https://pngimg.com/uploads/letter_p/letter_p_PNG48.png"));
		} catch (IllegalArgumentException ex) {
			System.err.println("not valid: " + ex);
		}
		WortTrainer wT = new WortTrainer(wL, 0);

		System.out.println("\n--->WORD LIST");
		System.out.println(wT.getWordList().toString());

		// delete
		System.out.println("--->DELETE WORDS");
		System.out.println("deleteWord (Minute): " + wL.deleteWord("Minute"));
		System.out.println("deleteWord (Blume): " + wL.deleteWord("Blume"));

		System.out.println("\n--->WORD LIST");
		System.out.println(wT.getWordList().toString());

		// toString (WortEintrag)
		System.out.println("--->TO STRING SINGLE WORD");
		try {
			System.out.println(wT.getWordList().getWordObject(0).toString());
			System.out.println(wT.getWordList().getWordObject(-1).toString());
			System.out.println(wT.getWordList().getWordObject(11).toString());

		} catch (IndexOutOfBoundsException ex) {
			String e = ex.toString();
			System.err.println("Index not valid: " + e);
		}

		System.out.println("\n------------------\n");

		// getWordObject, getWort, getUrl
		System.out.println("\n--->GET WORD OBJECT");
		try {
			System.out.println("getWord: " + wT.getWordList().getWordObject(1).getWort() + "; "
					+ wT.getWordList().getWordObject(1).getUrl());
			System.out.println("getWord: " + wT.getWordList().getWordObject(2).getWort() + "; "
					+ wT.getWordList().getWordObject(2).getUrl());
			System.out.println("getWord: " + wT.getWordList().getWordObject(22).getWort() + "; "
					+ wT.getWordList().getWordObject(22).getUrl());
		} catch (IndexOutOfBoundsException ex) {
			String e = ex.toString();
			System.err.println("Index not valid: " + e);
		}

		// randomWordString & randomWord
		System.out.println("\n--->RANDOM WORD STRING");
		System.out.println(wT.randomWordString());

		// check
		System.out.println("\n--->CHECK");
		System.out.println("Fenster: " + wT.check("Fenster"));
		System.out.println("Igel: " + wT.check("Igel"));
		System.out.println("Buecherregal: " + wT.check("Buecherregal"));

		System.out.println("\n--->CHECK IGNORE CASE");
		System.out.println("FeNstER: " + wT.checkIgnoreCase("FeNstER"));
		System.out.println("igel: " + wT.checkIgnoreCase("igel"));
		System.out.println("BUECHERREGAL: " + wT.checkIgnoreCase("BUECHERREGAL"));

		System.out.println("\n--->STATISTIK");
		System.out.println("Methode getStatistik:\n" + wT.getStatistik());

		// save & load
		WortSpeichernLaden wSL = new WortSpeichernLaden(wL, "save/test11.txt");
		System.out.println("\n--->SPEICHERN, LADEN");
		System.out.println("speichern: " + wSL.speichern("save/test11.txt"));
		System.out.println("laden: " + wSL.laden("save/test11.txt"));

		WortSpeichernLaden wSL2 = new WortSpeichernLaden(wL);
		System.out.println("2nd\nspeichern: " + wSL2.speichern());
		System.out.println("laden: " + wSL2.laden());
		System.out.println("Statistik: " + wSL2.statistik());

	}

}
