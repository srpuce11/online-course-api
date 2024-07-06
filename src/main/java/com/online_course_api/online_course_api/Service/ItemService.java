package com.online_course_api.online_course_api.Service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.online_course_api.online_course_api.ResourceNotFoundException;
import com.online_course_api.online_course_api.repository.ItemRepository;
import com.online_course_api.online_course_api.repository.entity.Item;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id " + id));
    }

    public Item save(@Valid Item item) {
        return itemRepository.save(item);
    }

    public Item update(Long id, @Valid Item itemDetails) {
        Item item = findById(id);
        item.setName(itemDetails.getName());
        item.setDescription(itemDetails.getDescription());
        return itemRepository.save(item);
    }

    public void delete(Long id) {
        Item item = findById(id);
        itemRepository.delete(item);
    }
}
