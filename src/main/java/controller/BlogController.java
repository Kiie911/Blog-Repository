package controller;

import model.Blog;
import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.blog.IBlogService;
import service.category.ICategoryService;

import java.util.Optional;

@Controller
public class BlogController {
    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }

    @GetMapping("/blogs")
    public ModelAndView listBlogs(@RequestParam("s") Optional<String> s, @PageableDefault(size = 10, direction = Sort.Direction.DESC, sort = "id") Pageable pageable) {

        Page<Blog> blogs;
        if (s.isPresent()) {
            blogs = blogService.findAllByNameContaining(s.get(), pageable);
        } else {
            blogs = blogService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("blog/list");
        modelAndView.addObject("blogList", blogs);
        return modelAndView;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView showDetail(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("blog/detail");
        modelAndView.addObject("blog", blogService.findById(id));
        return modelAndView;
    }

    @GetMapping("/create-blog")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("blog/create");
        modelAndView.addObject("blog", new Blog());
        return modelAndView;
    }

    @PostMapping("/create-blog")
    public ModelAndView saveBlog(@ModelAttribute("blog") Blog blog){
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("blog/create");
        modelAndView.addObject("blog", new Blog());
        modelAndView.addObject("message", "New blog added successfully");
        return modelAndView;
    }

    @GetMapping("/edit-blog/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("blog/edit");
        modelAndView.addObject("blog", blogService.findById(id));
        return modelAndView;
    }

    @PostMapping("/edit-blog")
    public ModelAndView updateBlog(@ModelAttribute("blog") Blog blog) {
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("blog/edit");
        modelAndView.addObject("blog", blog);
        modelAndView.addObject("message", "Blog saved");
        return modelAndView;
    }

    @GetMapping("/delete-blog/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("blog/delete");
        modelAndView.addObject("blog", blogService.findById(id));
        return modelAndView;
    }

    @PostMapping("/delete-blog")
    public String deleteBlog(@ModelAttribute("blog") Blog blog) {
        blogService.remove(blog.getId());
        return "redirect:blogs";
    }
}
