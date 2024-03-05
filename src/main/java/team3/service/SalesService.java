package team3.service;

import java.util.List;

import team3.entity.Sales;

public interface SalesService {

	Sales getSales(int sid);
	
	Sales getSalesOid(int oid, int mid);
	
	
	// 오더에 포함된 구매 정보 리스트 찾기
	List<Sales> getSalesList(int oid);
	// 구매 목록의 가격 찾기 (보류)
	//int getItemPrice(int mid);

	void insertSales(Sales sales);
	
	void updateSales(Sales sales);
	
	void deleteSales(Sales sales);
}
