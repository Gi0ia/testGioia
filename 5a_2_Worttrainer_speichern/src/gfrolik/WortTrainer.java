package gfrolik;

/**
 * A word trainer for kids
 * @author gioia
 * @version 2022-09-15
 */
public class WortTrainer {
	
	// Attributes
	private WortListe wordList;
	private int currentIndex;
	private int richtigAbg;
	private int abgefragt;
	private int falschAbg;
	
	public WortTrainer(WortListe wordList, int currentIndex) {
		super();
		this.wordList = wordList;
		this.currentIndex = currentIndex;
		richtigAbg = 0;
		abgefragt = 0;
	}

	/**
	 * get word list
	 * @return word list
	 */
	public WortListe getWordList() {
		return wordList;
	}

	/**
	 * setter
	 * @param wordList word list to set
	 */
	public void setWordList(WortListe wordList) {
		this.wordList = wordList;
	}

	/**
	 * get current index
	 * @return	current index
	 */
	public int getCurrentIndex() {
		return currentIndex;
	}

	/**
	 * getter of the derived (abgeleitetes) Attribute
	 * @return the wrong answered Questions
	 */
	public void setFalschAbge() {
		this.falschAbg = this.abgefragt - this.richtigAbg;
	}
	/**
	 * set current index
	 * @param currentIndex the index to set
	 */
	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}
	
	public String getStatistik() {
		int abg = 0;
		if (this.abgefragt > 0) {
			abg = (this.richtigAbg*100)/this.abgefragt;
		}
		
		return "Es wurden " + this.abgefragt + " Wörter abgefragt,  von welchen " + this.richtigAbg + " korrekt und " +
				this.falschAbg + " falsch waren." +	"\nDas entspricht einer Richtigkeitsquote von " + 
				abg + "%.";
	}
	public void statistik(boolean correct) {
		this.abgefragt++;
		if(correct) {
			this.richtigAbg++;
		}
		this.setFalschAbge();
	}

	/**
	 * Returns the word object (randomly)
	 * @return the random object from the list
	 */
	public WortEintrag randomWord() {
		int l = this.wordList.getWortListe().length - 1;
		double r = Math.random()*l;
		int random = (int) r;
		currentIndex = random;
		return this.wordList.getWordObject(random);
	}
	
	/**
	 * returns a random word (string) that is randomly chosen
	 * @param we the given object
	 * @return a random word
	 */
	public String randomWordString() {
		return this.wordList.getWordObject(this.currentIndex).getWort();
	}
	
	/**
	 * checks if the intern word equals the given
	 * @param wordExtern the external word
	 * @return if the entered word equals the intern
	 */
	public boolean check(String wordExtern) {
		String wordIntern = this.wordList.getWordObject(currentIndex).getWort();
		// check if correct
		boolean corr = wordIntern.equals(wordExtern);
		statistik(corr);
		if(corr) {
			
			return true;
		}
		return false;
	}
	
	/**
	 * checks if the intern word equals the given + ignore case
	 * @param wordExtern the external word
	 * @return if the entered word equals the intern
	 */
	public boolean checkIgnoreCase(String wordExtern) {
		String wordIntern = this.wordList.getWordObject(currentIndex).getWort();
		// to lower case
		wordExtern = wordExtern.toLowerCase();
		wordIntern = wordIntern.toLowerCase();
		// check if correct
		boolean corr = wordIntern.equals(wordExtern);
		statistik(corr);
		if(corr) {
			return true;
		}
		return false;
	}
}

