package team3.service;

import java.util.List;

import team3.entity.Item_order;

public interface OrderService {

	public static final int ORDER_WAITING = 0;
	public static final int ORDER_REQUEST = 1;
	public static final int ORDER_DELIVERY = 2;
	public static final int ORDER_FINISH = 3;
	public static final int ORDER_CANCEL = -1;
	
	// ID로 오더 받기
	Item_order getOrder(String uid, int status);
	
	// 주문번호로 오더 받기
	Item_order getOrderOid(int oid);
	
	// 오더 리스트
	List<Item_order> getOrderList(String uid);
	
	// 오더 추가
	void insertOrder(Item_order order);
	
	// 오더 갱신
	void updateOrder(Item_order order);
	
	// 오더 삭제
	void deleteOrder(int oid);
	
	// 오더 취소
	void cancelOrder(Item_order order);
	
	// 전체 가격
	//int getTotalPrice();
}
