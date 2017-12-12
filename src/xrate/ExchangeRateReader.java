package xrate;

import java.io.*;

import java.net.*;

import javax.xml.parsers.*;

import org.xml.sax.*;

import org.w3c.dom.*;

/**
 * Provide access to basic currency exchange rate services.
 * 
 * @author PUT YOUR TEAM NAME HERE
 */
public class ExchangeRateReader {
	String baseURL;
    /**
     * Construct an exchange rate reader using the given base URL. All requests
     * will then be relative to that URL. If, for example, your source is Xavier
     * Finance, the base URL is http://api.finance.xaviermedia.com/api/ Rates
     * for specific days will be constructed from that URL by appending the
     * year, month, and day; the URL for 25 June 2010, for example, would be
     * http://api.finance.xaviermedia.com/api/2010/06/25.xml
     * 
     * @param baseURL
     *            the base URL for requests
     */
    public ExchangeRateReader(String baseURL) {
        // TODO Your code here
    	
    	this.baseURL = baseURL;
    	
    }

    /**
     * Get the exchange rate for the specified currency against the base
     * currency (the Euro) on the specified date.
     * 
     * @param currencyCode
     *            the currency code for the desired currency
     * @param year
     *            the year as a four digit integer
     * @param month
     *            the month as an integer (1=Jan, 12=Dec)
     * @param day
     *            the day of the month as an integer
     * @return the desired exchange rate
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public float getExchangeRate(String currencyCode, int year, int month, int day) {
        // TODO Your code here
        throw new UnsupportedOperationException();
        //Setting up the double digit form factor for mm/dd style
        String D;
        String M;
        D = day + "";
        M = month + "";
        
        if ( day < 10 ) {
        	D =  "0" + day;
        } else {
        	// do nothing 
        }
        
        if ( month < 10 ) {
        	M = "0" + month;
        } else {
        	//do nothing 
        }
        
        //setting up the url with the correct format and opening connections
        URL url = new URL(baseURL + year + "/" + M + "/" + D + ".xml");
        URLConnection urlConnect = regURL.openConnection();
        InputStream  stream = reqURL.openStream();
        
        //building the document
        DocumentBuilderFactory DocBuildF = DocumentBuilderFactory.newInstance();
        DocumentBuilder DocBuild = DocBuildF.newDocumentBuilder();
        Document Doc = DocBuild.parse(stream);
        Doc.getDocumentElement().normalize();
        
        //geting elements currentcy_code and rate
        NodeList curr = Doc.getElementsByTagName("currency_code");
        NodeList rate = Doc.getElementsByTagName("rate");
        
        //set return value to negative so it is always less than the new return value
        float returned = -1;
        
        //loop through curr to get the text from rate.
        for (int i = 0; i < curr.getLength(); i++) {
			if (curr.item(i).getNodeType() == curr.item(i.ELEMENT_NODE) {
				if (currencyCode.equals(curr.item(i).getTextContent())) {
					returned = new Float(rate.item(i).getTextContent());
				}
			}
		}
        return returned;
    }

    /**
     * Get the exchange rate of the first specified currency against the second
     * on the specified date.
     * 
     * @param currencyCode
     *            the currency code for the desired currency
     * @param year
     *            the year as a four digit integer
     * @param month
     *            the month as an integer (1=Jan, 12=Dec)
     * @param day
     *            the day of the month as an integer
     * @return the desired exchange rate
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public float getExchangeRate(
            String fromCurrency, String toCurrency,
            int year, int month, int day) {
        // TODO Your code here
        throw new UnsupportedOperationException();
        
        float from = this.getExchangeRate(fromCurrency, year, month, day);
        float to = this.getExchangeRate(toCurrency, year, month, day);
        
        return from + "/" + to;S
    }
}