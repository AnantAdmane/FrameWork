package com.comcast.crm.listenerutility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListenerImp implements IRetryAnalyzer{
	//declare global
	int count=0;
	int limit=5;
	@Override
	public boolean retry(ITestResult result) {
		
		if(count<limit)
		{
			count++;
			return true;
		}
		return false;
	}
}
