package service.category;

import model.Category;

public interface ICategoryService {
    Iterable<Category> findAll();

    void save(Category category);

    Category findById(Long id);

    Category findByName(String name);

    void remove(Long id);
}
