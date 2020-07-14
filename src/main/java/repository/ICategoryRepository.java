package repository;

import model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICategoryRepository extends PagingAndSortingRepository<Category, Long> {
    Category findByName(String name);
}
