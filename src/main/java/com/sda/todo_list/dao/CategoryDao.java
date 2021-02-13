package com.sda.todo_list.dao;

import com.sda.todo_list.domain.Category;

import java.util.List;

public interface CategoryDao {

    void saveOrUpdate(Category category);
    List<Category> getCategories();
    void deleteCategoryByName(String categoryName);
    void deleteCategory(Category category);
}
