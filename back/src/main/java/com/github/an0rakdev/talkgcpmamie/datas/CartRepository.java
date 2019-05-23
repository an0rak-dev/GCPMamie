package com.github.an0rakdev.talkgcpmamie.datas;

import java.util.Map;
import java.util.HashMap;
import org.springframework.stereotype.Component;

@Component
public class CartRepository {
	private Map<String, Integer> mockDatas = new HashMap<>();

	public int findQuantityOf(String code) {
		Integer value = this.mockDatas.get(code);
		return (value == null) ? 0 : value;
	}

	public void updateQuantity(String code, int newQuantity) {
		mockDatas.put(code, newQuantity);
	}

	public void insertQuantity(String code, int quantity) {
		mockDatas.put(code, quantity);
	}

	public int countAll() {
		int result = 0;
		for (Integer v : mockDatas.values()) {
			result += v;
		}
		return result;
	}
}
