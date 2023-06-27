package com.myblog.controller;

import com.myblog.payload.PostDto;
import com.myblog.payload.PostResponse;
import com.myblog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

     private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    //http://localhost:8080/api/posts

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
     public ResponseEntity<Object> createPost(@Valid  @RequestBody PostDto postDto, BindingResult result) {

        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
         PostDto dto = postService.createPost(postDto);
         return new ResponseEntity<>(dto , HttpStatus.CREATED);
     }

     //get all post from database
     //http://localhost:8080/api/posts?pageNo=0&pageSize=5&sortBy=title&sortDir=asc
     @GetMapping
     public PostResponse getAllPosts(@RequestParam(value = "pageNo", defaultValue = "0",required = false) int pageNo,
                                     @RequestParam(value = "pageSize",defaultValue = "10", required = false) int pageSize,
                                     @RequestParam(value="sortBy", defaultValue = "id", required = false) String sortBy,
                                     @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir){
         return postService.getAllPosts(pageNo,pageSize,sortBy, sortDir);



     }

     //get a record By id
     @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") Long id) {
         PostDto dto = postService.getPostById(id);
         return ResponseEntity.ok(dto);

    }

    //Update post By id rest api
    //http://localhost:8080/api/posts/1
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
     public ResponseEntity<PostDto> updatePOst(@RequestBody PostDto postDto, @PathVariable("id") Long id){
        PostDto dto = postService.updatePost(postDto, id);
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    //Delete a post By id
    //http://localhost:8080/api/posts/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable("id") Long id){
        postService.deletePostByID(id);
        return new ResponseEntity<>("Post Entity deleted!!", HttpStatus.OK);

    }
}
