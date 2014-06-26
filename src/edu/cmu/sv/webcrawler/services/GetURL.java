package edu.cmu.sv.webcrawler.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
public class GetURL {
	private ArrayList<String> URLs;
	private StringBuffer sBuffer;
	
	public GetURL() {
		sBuffer = new StringBuffer();
		URLs = new ArrayList<String>();
	}
	
	private void ParseURLs(boolean isCurrent) {
		int startIndex = 0, endIndex = 0;
		while(true) {			
			startIndex = sBuffer.indexOf("/Archives/edgar/data", endIndex);
			if(startIndex == -1) {
				return;
			}
			endIndex = sBuffer.indexOf("\"", startIndex);
			String str = sBuffer.substring(startIndex, endIndex);
			String year = "";
			if(isCurrent == false) {
				int index = str.indexOf("-");
				year = str.substring(index+1, index+3);
			}
			str = "http://www.sec.gov" + str;
			URLs.add("http://www.sec.gov" + this.ParseIntoURLs(str) + year);
			if(Integer.parseInt(year) <= 11) {
				break;
			}
		}
	}
	
	private String ParseIntoURLs(String urlStr) {
		StringBuffer sb = Get10kSearchPage(urlStr);
		String str = "";
		int startIndex = 0, endIndex = 0;
		while(true) {
			startIndex = sb.indexOf("/Archives/edgar/data", endIndex);
			if(startIndex == -1) {
				break;
			}
			endIndex = sb.indexOf("\"", startIndex);
			str = sb.substring(startIndex, endIndex);
			int index = str.lastIndexOf('.');
			if(index != -1) {
				break;
			}
		}
		return str;
	}
	
	public ArrayList<String> Get10kURLwithCIK(String CIK, boolean isCurrent) {
		String queryURL;
		if(isCurrent == false) {
			queryURL = "http://www.sec.gov/cgi-bin/browse-edgar?action=getcompany&type=10-K&CIK=";
			this.sBuffer = Get10kSearchPage(queryURL + CIK);
		}
		else {
			queryURL = "http://www.sec.gov/cgi-bin/current.pl?q1=1&q2=0";
			this.sBuffer = Get10kSearchPage(queryURL);
		}				
		ParseURLs(isCurrent);
		return URLs;
	}
	
	private StringBuffer Get10kSearchPage(String urlStr)
    {
        /** the length of input stream */
        int chByte = 0;
        
        URL url = null;
        
        /** HTTP connection */
        HttpURLConnection httpConn = null;
        
        InputStream in = null;
        
        StringBuffer sb = new StringBuffer("");

        try
        {
        	//int len = 0;
            url = new URL(urlStr);
            httpConn = (HttpURLConnection) url.openConnection();
            HttpURLConnection.setFollowRedirects(true);
            httpConn.setRequestMethod("GET"); 
            
            in = httpConn.getInputStream();
            
            chByte = in.read();
            while (chByte != -1)
            {
            	//len++;
            	sb.append((char)chByte);
                chByte = in.read();
            }
            //System.out.println(len);
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                in.close();
                httpConn.disconnect();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        return sb;
    }	
}