package controller;

import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.category.ICategoryService;

@RestController
@RequestMapping("/api/categories/")
public class RestCategoryController {

    @Autowired
    ICategoryService iCategoryService;

    @GetMapping
    public ResponseEntity<Iterable<Category>> findAllCategories(){
        return new ResponseEntity<>(iCategoryService.findAll(), HttpStatus.OK);
    }
}
