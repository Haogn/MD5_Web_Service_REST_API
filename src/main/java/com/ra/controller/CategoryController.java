package com.ra.controller;

import com.ra.entity.Category;
import com.ra.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService ;

    @GetMapping("")
    public ResponseEntity<List<Category>> getAll() {
        List<Category> list = categoryService.findAll() ;
        return new ResponseEntity<>(list, HttpStatus.OK) ;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable("id") Integer id) {
        Category category = categoryService.findById(id) ;
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) ;
        }
        return new ResponseEntity<>(category, HttpStatus.OK) ;
    }


    @PostMapping()
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        Category newCategory = categoryService.save(category) ;
        if ( newCategory == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(newCategory, HttpStatus.OK) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> editCategory(@PathVariable("id") Integer id , @RequestBody Category category) {
        Category editCategory = categoryService.findById(id) ;
        if (editCategory != null) {
            Category updateCategory =  categoryService.save(category) ;
            return new ResponseEntity<>(updateCategory, HttpStatus.OK) ;
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathVariable("id") Integer id) {
        if(categoryService.findById(id) != null){
            categoryService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
