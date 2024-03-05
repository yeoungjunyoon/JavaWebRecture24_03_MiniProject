package team3.service;

import java.util.List;

import team3.entity.Item;

public interface ItemService {
	
	public static final int COUNT_PER_PAGE = 9;

	Item getItem(int mid);
	
	List<Item> getItemList(String field, String query, int page);
	
	int getItemCount(String category, String query);
	
	void insertItem(Item item);
	
	void updateItem(Item item);
	
	void deleteItem(int mid);
}
