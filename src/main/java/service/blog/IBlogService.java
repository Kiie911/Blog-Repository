package service.blog;

import model.Blog;
import model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBlogService {
    Page<Blog> findAll(Pageable pageable);

    void save(Blog blog);

    Blog findById(Long id);

    void remove(Long id);

    Iterable<Blog> findAllByCategory(Category category);

    Page<Blog> findAllByNameContaining(String name, Pageable pageable);
}
