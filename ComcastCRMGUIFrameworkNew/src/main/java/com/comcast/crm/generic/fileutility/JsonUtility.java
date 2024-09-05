package com.comcast.crm.generic.fileutility;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonUtility 
{
	public String getDataFromJsonFile(String key) throws Throwable
	{
		FileReader fir=new FileReader("./configueAppData/appCommonData.json");
		JSONParser parser = new JSONParser();
		Object ob=parser.parse(fir);
		JSONObject map=(JSONObject)ob;
		String data=(String) map.get(key);
		return data;
	}
}
