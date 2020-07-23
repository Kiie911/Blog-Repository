package controller;

import model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.blog.IBlogService;

@RestController
@RequestMapping("/api/blogs/")
public class RestBlogController {

    @Autowired
    IBlogService iBlogService;

    @GetMapping
    public ResponseEntity<Iterable<Blog>> findAllBlogs(){
        return new ResponseEntity<>(iBlogService.findAllBlog(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog){
        iBlogService.save(blog);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable Long id){
        if (iBlogService.findById(id)==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/id")
    public ResponseEntity<Blog> updateBlog(@PathVariable Long id,@RequestBody Blog blog){
        updateBlog(id,blog);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Blog>> findAll(Pageable pageable) {
        Page<Blog> blogs = iBlogService.findAll(pageable);
        return new ResponseEntity<>(blogs,HttpStatus.OK);
    }
}
