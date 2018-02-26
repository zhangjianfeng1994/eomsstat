package com.boco.eoms;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "aa");
		map.put("b", "bb");
		map.put("c", "cc");
		map.put("a", "dd");
		System.out.println(map.size());
	}
}
