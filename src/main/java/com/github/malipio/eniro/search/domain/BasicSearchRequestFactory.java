package com.github.malipio.eniro.search.domain;

import java.util.HashMap;
import java.util.Map;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class BasicSearchRequestFactory {
	 
	private BasicSearchRequestFactory() {
		
	}
	
	@GeneratePojoBuilder(withName="BasicSearchRequestBuilder", intoPackage="com.github.malipio.eniro.search.domain")
	public static Map<String, String> createRequest(
			String profile, String key, String country, String version, String searchWord, 
			Integer fromList, Integer toList) {
		String params[] = {"profile", "key", "country", "version", "search_word", "from_list", "to_list"};
		String values[] = {profile, key, country, version, searchWord, 
				fromList == null ? null : fromList.toString(), toList == null ? null : toList.toString() };
		
		Map<String,String> map = new HashMap<String, String>();
		
		for(int i = 0; i < params.length; ++i)
			if(values[i] != null)
				map.put(params[i], values[i]);
		return map;
	}
}
