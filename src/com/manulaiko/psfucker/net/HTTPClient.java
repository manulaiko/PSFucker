package com.manulaiko.psfucker.net;

import java.net.*;
import java.io.*;
import java.util.*;

/**
 * HTTP Client
 * 
 * Socket client for executing simple HTTP request
 * 
 * @author Manulaiko
 * @package com.manulaiko.net
 */
public class HTTPClient
{
	/* Start response codes */
	public static final int RESPONSE_CODE_OK = 200;
	public static final int RESPONSE_CODE_NOT_FOUND = 404;
	/* End response codes */
	/**
	 * Cookies
	 */
	public List<String> cookies;

	/**
	 * The connection object
	 */
	private HttpURLConnection conn;

	/**
	 * User-Agent header
	 */
	public String userAgent = "Mozilla/5.0";
	
	/**
	 * Server's url
	 */
	private String server = "";

	/**
	 * Last executed request result
	 */
	public int lastResponseCode = 0;

	/**
	 * Constructor
	 */
	public HTTPClient(String server)
	{
		if(!server.startsWith("http://") || !server.startsWith("https://")) {
			server = "http://"+server;
		}
		if(!server.endsWith("/")) {
			server = server+"/";
		}

		this.server = server;
	}
	
	/**
	 * Returns whether a path/file in the server exists or not
	 * 
	 * @param path Path to check
	 * @return True if exists, false if not
	 */
	public boolean pageExists(String path)
	{
		this.execute("GET", path);
		return (this.lastResponseCode != RESPONSE_CODE_NOT_FOUND);
	}
	
	/**
	 * Executes a request to the server
	 * 
	 * @param method Request method
	 * @param path
	 * @param headers
	 * @param cookies
	 */
	public boolean execute(String path, String method, Map<String, String> headers, List<String> cookies)
	{

		String url = this.server+path;
		try {
			if(com.manulaiko.psfucker.Main.DEBUG) {
				System.out.println("Sending 'GET' request to URL: " + url);
			}

			URL obj = new URL(url);
			conn = (HttpURLConnection) obj.openConnection();
		 
			// default is GET
			conn.setRequestMethod(method);
			conn.setUseCaches(false);
		 
			// act like a browser
			for(Map.Entry<String, String> header : headers.entrySet()) {
				conn.setRequestProperty(header.getKey(), header.getValue());
			}

            if(cookies != null) {
                for (String cookie : cookies) {
                    if (com.manulaiko.psfucker.Main.DEBUG) {
                        System.out.println("Setting cookie: " + cookie);
                    }
                    conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
                }
            }
			
			this.lastResponseCode = conn.getResponseCode();
			if(com.manulaiko.psfucker.Main.DEBUG) {
				System.out.println("Response Code: "+this.lastResponseCode);
			}

            this.cookies = conn.getHeaderFields().get("Set-Cookie");
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Executes a request to the server
	 * 
	 * @param method Request method
	 * @param path
	 */
	public boolean execute(String path, String method)
	{
		Map<String, String> headers = new TreeMap<String, String>();
		headers.put("User-Agent", userAgent);
		headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		headers.put("Accept-Language", "en-US,en;q=0.5");
		
		return this.execute(path, method, headers, this.cookies);
	}

	/**
	 * Returns the source of a given path
	 */
	public String getPageContent(String path)
	{
		this.execute(path, "GET");
		try {
			BufferedReader in = new BufferedReader(
										new InputStreamReader(conn.getInputStream())
									);
			String inputLine;
			StringBuffer response = new StringBuffer();
		 
			while((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		 
			// Get the response cookies
			this.cookies = conn.getHeaderFields().get("Set-Cookie");

			if(this.lastResponseCode != 200) {
				if(com.manulaiko.psfucker.Main.DEBUG) {
					System.out.println("There was an error executing the HTTP request!");
					System.out.println(response.toString());
				}
				return "";
			}

			return response.toString();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return "";
	}
}