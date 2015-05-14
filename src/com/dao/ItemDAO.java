package com.dao;

/**
 * Created by SRawla on 4/13/2015.
 */
import com.model.Item;
import com.model.Category;
import java.util.Collection;

public interface ItemDAO {

    public Item findbyID(String id);

    public Item findByName(String name);

    public Collection<Item> getItemsByCategory(Category cat);

    public Collection<Item> getItemsLikeName(String Name);


}