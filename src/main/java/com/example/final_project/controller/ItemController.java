package com.example.final_project.controller;

import com.example.final_project.entity.Item;
import com.example.final_project.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/item")

public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }
    @PostMapping(path = "/create-item")
    public Item createItem(@RequestBody Item item) {
        itemService.addNewItem(item);
        return item;
    }
    @GetMapping(path = "/list-items")
    public List<Item> getItems () {
        return  itemService.getItems();
    }
    @DeleteMapping(path = "{itemId}")
    public void deleteItem(@PathVariable("itemId") Long itemId) {
         itemService.deleteItem(itemId);
    }
    @PutMapping(path = "{itemId}")
    public ResponseEntity<Item> updateItem(@PathVariable("itemId") Long itemId,
                                     @RequestParam(required = false)@RequestBody Item itemDetails) {
        itemService.updateItem(itemId, itemDetails);
        return ResponseEntity.ok().build();
    };

}
