
package acme.features.antiSpamFilter;

public class AntiSpamFilter {

	public String[] separateSpamWord(final String spamWord) {
		String[] separateSpamWord = spamWord.toLowerCase().split(",");
		return separateSpamWord;
	}

	public boolean isSpam(final String[] separateSpamWord, final String textToAnalyze, final double threshold) {
		boolean res = false;
		String textToAnalyze2 = textToAnalyze.replace(",", "").replace(";", "").replace(".", "");
		String[] separateTextToAnalyze = textToAnalyze2.toLowerCase().split(" ");
		Integer totalSpamWordInTheText = 0;
		for (String text : separateTextToAnalyze) {
			for (String spamWord : separateSpamWord) {
				if (text.equals(spamWord)) {
					totalSpamWordInTheText += 1;
				}
			}
		}

		if (totalSpamWordInTheText * 100 / separateTextToAnalyze.length >= threshold) {
			res = true;
		}
		return res;
	}
}
