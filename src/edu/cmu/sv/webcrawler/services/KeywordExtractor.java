package edu.cmu.sv.webcrawler.services;

import java.util.*;

import edu.cmu.sv.webcrawler.models.Keywords;

public class KeywordExtractor implements KeywordsService{
	Set<String> keywords;
	ArrayList<String> sentences;
	HashMap<String, KeywordInfo> keywordExtracted;
	private HashMap<String,Integer>result;
	private String risk;

	public KeywordExtractor(String risk) {
		Keywords key=new Keywords();
		this.risk=risk;
		keywords = new HashSet<String>();
		result=new HashMap<String,Integer>();
		sentences = new ArrayList<String>();
		keywordExtracted = new HashMap<String, KeywordInfo>();
		keywords=key.getKeywords();
		this.getKeywords();
	}

	private void getSentences(String risk) {
		int start = 0, end = 0, last = 0;
		last = start = risk.indexOf(".", end);
		while (start != -1 && end != -1) {
			end = risk.indexOf(".", last + 1);
			if (end - last > 10) {
				sentences.add(risk.substring(start + 1, end));
				start = end;
			}
			last = end;
		}
	}

	private void getKeywordsInSentence(String sen, int index) {
		Iterator<String> it = keywords.iterator();
		while (it.hasNext()) {
			String s = it.next();
			if (sen.indexOf(s) != -1) {
				if (!keywordExtracted.containsKey(s)) {
					KeywordInfo ki = new KeywordInfo();
					keywordExtracted.put(s, ki);
				}
				keywordExtracted.get(s).num++;
				keywordExtracted.get(s).indexOfSentence.add(index);
			}
		}
	}

	private void getKeywords() {
		getSentences(risk);
		for (int i = 0; i < sentences.size(); i++) {
			String sen = this.sentences.get(i);
			getKeywordsInSentence(sen, i);
		}

		Iterator<String> iter = keywordExtracted.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			System.out.print(key + " ");
			KeywordInfo ki = keywordExtracted.get(key);
			result.put(key, ki.num);
//			System.out.print(ki.num);
//			System.out.print("[");
//			for (int i = 0; i < ki.indexOfSentence.size(); i++) {
//				System.out.print(ki.indexOfSentence.get(i) + " ");
//			}
//			System.out.println("]");
		}

	}

	public HashMap<String, Integer> getKeywordsBySymbol() {
		return result;
	}

	/**
	 * @param args
	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		KeywordExtractor KE = new KeywordExtractor(".");
//		try {
//			// Get the risk description
//			String pathname = "2491_2011.txt";
//			File filename = new File(pathname);
//			InputStreamReader reader = new InputStreamReader(
//					new FileInputStream(filename));
//			BufferedReader br = new BufferedReader(reader);
//			String riskStr = br.readLine();
//			// System.out.println(riskStr.substring(0, 1000));
//
//			KE.getKeywords(riskStr);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
	
}

class KeywordInfo {
	public int num; // number of the word that appears
	public ArrayList<Integer> indexOfSentence;

	KeywordInfo() {
		num = 0;
		indexOfSentence = new ArrayList<Integer>();
	}
};