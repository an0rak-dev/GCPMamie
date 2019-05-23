package com.github.an0rakdev.talkgcpmamie.datas;

import java.util.Map;
import java.util.HashMap;
import org.springframework.stereotype.Component;

@Component
public class StockRepository {
	private Map<String, Integer> mockDatas;

	public StockRepository() {
		this.mockDatas = new HashMap<>();
		this.mockDatas.put("p01", 99);
		this.mockDatas.put("p02", 99);
		this.mockDatas.put("p03", 99);
		this.mockDatas.put("p04", 99);
		this.mockDatas.put("p05", 99);
		this.mockDatas.put("p06", 99);
		this.mockDatas.put("p07", 99);
		this.mockDatas.put("p08", 99);
		this.mockDatas.put("p09", 99);
		this.mockDatas.put("p10", 99);
	}

	public int findQuantityOf(String code) {
		Integer value = this.mockDatas.get(code);
		return (value == null) ? 0 : value;
	}

	public void updateQuantity(String code, int newQuantity) {
		mockDatas.put(code, newQuantity);
	}
}
