package com.example.final_project.service;

import com.example.final_project.entity.Item;
import com.example.final_project.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void updateItem(Long itemId, Item itemDetails) {
        Item existingItem = itemRepository.findById(itemId)
                .orElseThrow(()-> new IllegalArgumentException("Item with id " + itemId + " does not exist"));

        if (itemDetails.getName() != null && !itemDetails.getName().isEmpty()) {
            existingItem.setName(itemDetails.getName());
        }

        if (itemDetails.getQuantity() != null){
            existingItem.setQuantity(itemDetails.getQuantity());
        };
        if (itemDetails.getPrice() != null) {
            existingItem.setPrice(itemDetails.getPrice());
        };

        itemRepository.save(existingItem);
    };
}
