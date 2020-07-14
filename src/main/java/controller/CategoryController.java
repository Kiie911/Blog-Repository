package controller;

import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import service.blog.IBlogService;
import service.category.ICategoryService;

@Controller
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IBlogService blogService;

    @GetMapping("/categories")
    public ModelAndView listCategories() {
        Iterable<Category> categories = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView("category/list");
        modelAndView.addObject("categoryList", categories);
        return modelAndView;
    }

    @GetMapping("/detail-category/{id}")
    public ModelAndView showDetail(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("category/detail");
        modelAndView.addObject("category", categoryService.findById(id));
        modelAndView.addObject("blogList", blogService.findAllByCategory(categoryService.findById(id)));
        return modelAndView;
    }

    @GetMapping("/create-category")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("category/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }

    @PostMapping("/create-category")
    public ModelAndView saveCategory(@ModelAttribute("category") Category category){
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("category/create");
        modelAndView.addObject("category", new Category());
        modelAndView.addObject("message", "New category added successfully");
        return modelAndView;
    }

    @GetMapping("/edit-category/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("category/edit");
        modelAndView.addObject("category", categoryService.findById(id));
        return modelAndView;
    }

    @PostMapping("/edit-category")
    public ModelAndView updateCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("category/edit");
        modelAndView.addObject("category", category);
        modelAndView.addObject("message", "Category saved");
        return modelAndView;
    }

    @GetMapping("/delete-category/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("category/delete");
        modelAndView.addObject("category", categoryService.findById(id));
        return modelAndView;
    }

    @PostMapping("/delete-category")
    public String deleteCategory(@ModelAttribute("category") Category category) {
        categoryService.remove(category.getId());
        return "redirect:categories";
    }
}
