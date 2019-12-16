
package acme.features.antiSpamFilter;

public class AntiSpamFilter {

	public String[] separateSpamWord(final String spamWord) {
		String[] separateSpamWord = spamWord.toLowerCase().split(",");
		return separateSpamWord;
	}

	public boolean isSpam(final String[] separateSpamWord, String textToAnalyze, final double threshold) {
		boolean res = false;
		String[] separateTextToAnalyze = textToAnalyze.split(" ");
		textToAnalyze = textToAnalyze.toLowerCase();
		Integer totalSpamWordInTheText = 0;
		for (String spamWord : separateSpamWord) {
			Integer lengthCompleteText = textToAnalyze.length();
			if (textToAnalyze.contains(spamWord)) {
				textToAnalyze = textToAnalyze.replace(spamWord, "");
				Integer lengthReplaceText = textToAnalyze.length();
				totalSpamWordInTheText = totalSpamWordInTheText + (lengthCompleteText - lengthReplaceText) / spamWord.length();
			}
		}

		if (totalSpamWordInTheText * 100 / separateTextToAnalyze.length >= threshold) {
			res = true;
		}
		return res;
	}
}
