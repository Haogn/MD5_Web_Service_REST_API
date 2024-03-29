package com.ra.service;

import com.ra.entity.Category;
import com.ra.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceIMPL implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository ;
    @Override
    public List<Category> findAll() {
        List<Category> list = (List<Category>) categoryRepository.findAll();
        return  list;
    }

    @Override
    public Category findById(Integer id) {
        Optional<Category> category =  categoryRepository.findById(id);
        return category.orElse(null);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }
}
