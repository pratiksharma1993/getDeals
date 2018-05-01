package com.getDeals.data;

public class GetOrderResponse {
	
	private String shopName;
	private long shopContactNum;
	private SaleDetails saleDetails;
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public long getShopContactNum() {
		return shopContactNum;
	}
	public void setShopContactNum(long shopContactNum) {
		this.shopContactNum = shopContactNum;
	}
	public SaleDetails getSaleDetails() {
		return saleDetails;
	}
	public void setSaleDetails(SaleDetails saleDetails) {
		this.saleDetails = saleDetails;
	}
	
	

}
