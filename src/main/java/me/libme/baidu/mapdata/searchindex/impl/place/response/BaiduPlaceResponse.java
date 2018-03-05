package me.libme.baidu.mapdata.searchindex.impl.place.response;

import java.util.List;

public class BaiduPlaceResponse {
	private int status;
	
	private String message;
	
	private int total;
	
	private List<BaiduPlace> results;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<BaiduPlace> getResults() {
		return results;
	}

	public void setResults(List<BaiduPlace> results) {
		this.results = results;
	}

}
