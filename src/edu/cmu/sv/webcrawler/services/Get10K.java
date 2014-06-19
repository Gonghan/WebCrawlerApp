package edu.cmu.sv.webcrawler.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import edu.cmu.sv.webcrawler.util.GetURL;

public class Get10K {
	/**
	 * Download 10-k file to local machine
	 * 
	 * @param urlStr
	 *            url of resource need to download
	 * @param outPath
	 *            include the name
	 */
	private void DownLoad10K(String urlStr, String outPath) {
		/** the length of input stream */
		int chByte = 0;

		/** the url of the doc to download */
		URL url = null;

		/** HTTP connection */
		HttpURLConnection httpConn = null;
		InputStream in = null;
		FileOutputStream out = null;

		try {
			url = new URL(urlStr);
			httpConn = (HttpURLConnection) url.openConnection();
			HttpURLConnection.setFollowRedirects(true);
			httpConn.setRequestMethod("GET");
			in = httpConn.getInputStream();
			out = new FileOutputStream(new File(outPath));
			chByte = in.read();
			while (chByte != -1) {
				out.write(chByte);
				chByte = in.read();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				in.close();
				httpConn.disconnect();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Download 10-k files for a given company using CIK or symbol
	 * 
	 * @param symbol
	 */
	public void Download10KbyCIK(String symbol, boolean isCurrent) {
		String path = getAppPath();
		GetURL gURL = new GetURL();
		ArrayList<String> URLs = gURL.Get10kURLwithCIK(symbol, isCurrent);
		Iterator<String> it = URLs.iterator();
		while (it.hasNext()) {
			String str = it.next();
			int index0 = str.indexOf("data");
			int index1 = str.indexOf("/", index0 + 5);
			String CIK = str.substring(index0 + 5, index1);
			int index2 = str.lastIndexOf('.');
			if (index2 <= 14)
				continue;
			String year, url;
			if (isCurrent == false) {
				url = str.substring(0, str.length() - 2);
				year = str.substring(str.length() - 2);
				if (Integer.parseInt(year) < 60) {
					year = "20" + year;
				} else {
					year = "19" + year;
				}
			} else {
				year = "2014";
				url = str;
			}
			String ext = url.substring(index2);
			String fileName = path + "/10K/" + CIK + "_" + year + ext;// + "_" +
			// index
			// + ext;
			DownLoad10K(url, fileName);
			System.out.println(fileName);
		}

	}

	/**
	 * 
	 * Download 10-k files for a list of companies
	 * 
	 * @param filename
	 */
	public void Download10KbyCIKList() {
		try {
			String filename="stocksymbol";
			String path = getAppPath();
			FileReader fr = new FileReader(path + "/" + filename);
			BufferedReader br = new BufferedReader(fr);
			String str = br.readLine();
			while (str != null) {
				Download10KbyCIK(str, false);
				str = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getAppPath() {
		//return "F:/workspace/BlueMix_workspace/WebCrawlerApp"
		return "F:/workspace/BlueMix_workspace/WebCrawlerApp";
	}

	// Main
	public static void main(String[] args) {
		System.out.println("Start crawling from www.sec.gov...");
		String CIK = "IBM"; // "ABIO"
		Get10K g10K = new Get10K();
		g10K.Download10KbyCIK(CIK, true);
		// g10K.Download10KbyCIKList();
		// String symbol = "ABIO";
		// String CIK = "IBM";
		// Get10K g10K = new Get10K();
		// g10K.Download10KbyCIK(CIK, true);
		// g10K.Download10KbyCIKList("stocksymbol", false);

		System.out.println("Finished crawling.");
	}
}
