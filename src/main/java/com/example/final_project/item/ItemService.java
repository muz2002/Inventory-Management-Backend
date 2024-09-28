package com.example.final_project.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
     public List<Item> getItems() {
        return itemRepository.findAll();

    };

    public void addNewItem(Item item) {
        itemRepository.save(item);
    }

    public void deleteItem(Long itemId) {
        boolean exist = itemRepository.existsById(itemId);
        if (!exist) {
            throw new IllegalArgumentException("Item with id " + itemId + " does not exist");
        }
        itemRepository.deleteById(itemId);
    }
}
