package team3.service;

import java.util.List;

import team3.dao.ItemDao;
import team3.dao.OrderDao;
import team3.dao.SalesDao;
import team3.entity.Item;
import team3.entity.Item_order;
import team3.entity.Sales;

public class SalesServiceImpl implements SalesService {
	
	private SalesDao sDao = new SalesDao();
	private OrderDao oDao = new OrderDao();
	private ItemDao iDao = new ItemDao();

	@Override
	public Sales getSales(int sid) {
		return sDao.getSales(sid);
	}

	@Override
	public Sales getSalesOid(int oid, int mid) {
		return sDao.getSalesOid(oid, mid);
	}
	
	
	@Override
	public List<Sales> getSalesList(int oid) {
		return sDao.getSalesList(oid);
	}

	@Override
	public void insertSales(Sales sales) {
		
		Item item = iDao.getItem(sales.getMid());
		Item_order order = oDao.getOrderOid(sales.getOid());
		order.setTotalPrice(sales.getQuantity() * item.getPrice() + order.getTotalPrice());
		
		sDao.insertSales(sales);
		oDao.updateOrder(order);
		
	}

	@Override
	public void updateSales(Sales sales) {
		int pre_quantity = (sDao.getSales(sales.getSid())).getQuantity();
		int quantity = sales.getQuantity();
		
		Item item = iDao.getItem(sales.getMid());
		Item_order order = oDao.getOrderOid(sales.getOid());
		order.setTotalPrice((quantity - pre_quantity) * item.getPrice() + order.getTotalPrice());
		
		sDao.updateSales(sales);
		oDao.updateOrder(order);
	}

	@Override
	public void deleteSales(Sales sales) {
		Item item = iDao.getItem(sales.getMid());
		Item_order order = oDao.getOrderOid(sales.getOid());
		order.setTotalPrice(order.getTotalPrice() - sales.getQuantity() * item.getPrice());
		
		sDao.deleteSales(sales.getSid());
		oDao.updateOrder(order);
	}


}
