package gfrolik;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WortSpeichernLaden {
	// Attributes
	// private WortTrainer woerter; 
	private WortListe liste;
	private String pfad;
	private WortTrainer wT;

	/**
	 * constructor w/ parameters
	 * 
	 * @param liste word list
	 * @param pfad  the path
	 */
	public WortSpeichernLaden(WortListe liste, String pfad) {
		super();

		this.liste = liste;
		this.pfad = pfad;
		wT = new WortTrainer(liste, 1);
	}

	/**
	 * constructor w/o parameters
	 * 
	 * @param liste word list
	 */
	public WortSpeichernLaden(WortListe liste) {
		super();
		wT = new WortTrainer(liste, 1);
		this.liste = liste;
		this.pfad = "unknown.txt";
	}

	/**
	 * saves the word trainer in the given path this.woerter.wortAnz()
	 * (including all statistics)
	 * 
	 * @param pfad the given path
	 * @return if it worked
	 */
	public boolean speichern(String pfad) throws IOException {
		File f = new File(pfad);
		boolean worked = false;
		try (FileWriter outputStream = new FileWriter(f);) {
			// all with interface AutoClosrable

			for (int i = 0; i < this.liste.getWortListe().length; ++i) {
				outputStream.write(this.liste.getWordObject(i).getWort());
				outputStream.write(System.lineSeparator());
				outputStream.write(this.liste.getWordObject(i).getUrl());
				outputStream.write(System.lineSeparator());
			}
			outputStream.write(this.wT.getStatistik());
			worked = true;
		}
		return worked;
	}

	/**
	 * saves the word trainer in the default path
	 * (including all statistics)
	 * 
	 * @return if it worked
	 */
	public boolean speichern() throws IOException {
		boolean worked = this.speichern(this.pfad);
		return worked;
	}

	/**
	 * loads the word trainer from the given path
	 * @param pfad
	 * @return if it worked
	 */
	public boolean laden(String pfad) throws IOException {
		boolean worked = false;
		try (Scanner s = new Scanner(new BufferedReader(new FileReader(pfad)))) {
			while (s.hasNext()) {
				liste.addWord(s.next(), this.pfad);
				worked = true;
			}
		}

		return worked;
	}

	/**
	 * loads the word trainer from the default path
	 * 
	 * @return if it worked
	 */
	public boolean laden() throws IOException {
		boolean worked = this.laden(this.pfad);
		return worked;
	}

	public String statistik() {
		return wT.getStatistik();
	}
}
