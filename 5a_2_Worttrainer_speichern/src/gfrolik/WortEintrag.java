package gfrolik;

/**
 * saves a word (2 characters minimum) + a URL
 * 
 * @author gioia
 * @version 2022-09-14
 */
public class WortEintrag {

	// Attributes
	private String wort;
	private String url;

	/**
	 * get word
	 * 
	 * @return gotten word
	 */
	public String getWort() {
		return wort;
	}

	/**
	 * set word
	 * 
	 * @param w word to set
	 */
	public void setWort(String w) {
		if (w.length() >= 2) {
			this.wort = w;
		} else {
			throw new IllegalArgumentException("word too short!");
		}
	}

	/**
	 * get URL
	 * 
	 * @return gotten URL
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * set URL
	 * 
	 * @param u URL to set
	 */
	public void setUrl(String u) {
		if (checkURL(u)) {
			this.url = u;
		} else {
			throw new IllegalArgumentException("URL not valid!");
		}
	}

	/**
	 * Constructor
	 * 
	 * @param w word
	 * @param u URL
	 */
	public WortEintrag(String w, String u) {
		this.setWort(w);
		this.setUrl(u);
	}

	/**
	 * checks the given url
	 * 
	 * @param u URL
	 * @return if valid (true) or not (false)
	 */
	public static boolean checkURL(String u) {
		if (u.length() >= 4) {
			// must be http
			if (u.substring(0, 4).equals("http")) {
				if (u.length() >= 8) {
					// if https://
					if (u.substring(4, 8).equals("s://")) {
						for (int i = 8; i < u.length(); i++) {
							if (i > 8 && u.charAt(i) == '.') {
								if ((u.charAt(i + 1) >= 'A' && u.charAt(i + 1) <= 'Z')
										|| (u.charAt(i + 1) >= 'a' && u.charAt(i + 1) <= 'z')) {
									// -> valid
									return true;
								}
							}
						}
					} else if (u.length() >= 4) {
						// if http://
						if (u.substring(4, 7).equals("://")) {
							for (int i = 7; i < u.length(); i++) {
								if (i > 7 && u.charAt(i) == '.') {
									if (u.charAt(i + 1) >= 65 && u.charAt(i + 1) <= 122) {
										// -> valid
										return true;
									}
								}
							}
						} // not http://

					}
				}

			} // not http
		}
		// -> NOT valid
		return false;
	}

	/**
	 * overrides to string
	 */
	@Override
	public String toString() {
		return this.wort + ";";
	}

}
