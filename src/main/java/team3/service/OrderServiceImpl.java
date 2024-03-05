package team3.service;

import java.util.List;

import team3.dao.OrderDao;
import team3.entity.Item_order;

public class OrderServiceImpl implements OrderService {
	private OrderDao oDao = new OrderDao();

	@Override
	public Item_order getOrder(String uid, int status) {
		
		return oDao.getOrder(uid, status);
	}
	
	public Item_order getOrderOid(int oid) {
		return oDao.getOrderOid(oid);
	}

	@Override
	public List<Item_order> getOrderList(String uid) {
		return oDao.getOrderList(uid);
	}

	@Override
	public void insertOrder(Item_order order) {
		oDao.insertOrder(order);
	}

	@Override
	public void updateOrder(Item_order order) {
		oDao.updateOrder(order);
	}

	@Override
	public void deleteOrder(int oid) {
		oDao.deleteOrder(oid);
	}

	@Override
	public void cancelOrder(Item_order order) {
		order.setStatus(ORDER_CANCEL);
		
		oDao.updateOrder(order);
	}

}
