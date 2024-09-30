package com.example.final_project.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PostMapping
    public Item createItem(@RequestBody Item item) {
        itemService.addNewItem(item);
        return item;
    }

    @GetMapping
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