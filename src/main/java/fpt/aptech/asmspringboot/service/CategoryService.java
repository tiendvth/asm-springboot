package fpt.aptech.asmspringboot.service;

import fpt.aptech.asmspringboot.entity.Category;
import fpt.aptech.asmspringboot.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    final CategoryRepository categoryRepository;

    public Page<?> findAll(int page, int limit, String sortBy) {
        return categoryRepository.findAll(PageRequest.of(page - 1, limit, Sort.by(sortBy).descending()));
    }

    public Optional<Category> findById(long id) {
        return categoryRepository.findById(id);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(long id) {
        categoryRepository.deleteById(id);
    }

    public boolean update(long id, Category categoryUpdate) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()) {
            categoryRepository.save(categoryUpdate);
            return true;
        }
        return false;
    }
}