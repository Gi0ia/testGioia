package gfrolik;

/**
 * An array of word entries
 * @author gioia
 * @version 2022-09-14
 */
public class WortListe {
	// Attribute
	private WortEintrag[] wortListe = new WortEintrag[0];
	
	
	/**
	 * get word list
	 * @return gotten word list
	 */
	public WortEintrag[] getWortListe() {
		return wortListe;
	}

	/**
	 * set word list
	 * @param wL word list to set
	 */
	public void setWortListe(WortEintrag[] wL) {
		this.wortListe = wL;
	}
	
	/**
	 * adds a word to the array
	 * @param we	the word
	 * @return if it worked
	 */
	public boolean addWord(String word, String url) {
		// check if parameters are valid
		if(word.length() > 1 && WortEintrag.checkURL(url)) {
			// add another index at the end
			int n = wortListe.length;
			// create a new (longer) array
			WortEintrag[] newArr = new WortEintrag[n+1];
			for(int i = 0; i < n; i++) {
				newArr[i] = wortListe[i];
			}
			newArr[n] = new WortEintrag(word, url);
			this.setWortListe(newArr);
			return true;
		}
		return false;
		
	}

	/**
	 * returns the word of the given index
	 * @param index	the given index
	 * @return	the word
	 */
	public WortEintrag getWordObject (int index) {
		WortEintrag w = this.wortListe[index];
		return w;
	}
	
	/**
	 * deletes a word 
	 * @param w the word to delete
	 * @return	if it worked
	 */
	public boolean deleteWord(String w) {
		String currentW;
		int c = 0;
		boolean alright = false;
		
		for(int i = 0; i < this.wortListe.length; i++) {
			currentW = this.wortListe[i].getWort();
			if(currentW.equals(w)) {
				this.wortListe[i] = null;
				alright = true;
				c++;
			}
		}
		int p = 0;
		WortEintrag[] newArr = new WortEintrag[this.wortListe.length-c];
		for(int i = 0; i < this.wortListe.length; i++) {
			if(this.wortListe[i] != null) {
				newArr[p] = this.wortListe[i];
				p++;
			}
		}
		this.wortListe = newArr;
		return alright;
	}
	
	/**
	 * Overrides toString methode
	 * @return the list as String
	 */
	@Override
	public String toString() {
		String r = "";
		for(int i = 0; i < this.wortListe.length; i++) {
			if(this.wortListe[i] != null) {
				r += (i+1) + ") " + this.wortListe[i].getWort() + "\n";
			}
		}
		return r;
	}
	
}
