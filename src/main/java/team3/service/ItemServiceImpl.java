package team3.service;

import java.util.List;

import team3.dao.ItemDao;
import team3.entity.Item;

public class ItemServiceImpl implements ItemService {
	
	private ItemDao iDao = new ItemDao();

	@Override
	public Item getItem(int mid) {
		return iDao.getItem(mid);
	}

	@Override
	public List<Item> getItemList(String field, String query, int page) {
		int offset = (page - 1) * ItemService.COUNT_PER_PAGE;
		
		return iDao.getItemList(field, query, ItemService.COUNT_PER_PAGE, offset);
	}
	
	@Override
	public int getItemCount(String category, String query) {
		return iDao.getItemCount(category, query);
	}

	@Override
	public void insertItem(Item item) {
		iDao.insertItem(item);
	}

	@Override
	public void updateItem(Item item) {
		iDao.updateItem(item);
	}

	@Override
	public void deleteItem(int mid) {
		iDao.deleteItem(mid);
	}

}
