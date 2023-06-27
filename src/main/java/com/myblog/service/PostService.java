package com.myblog.service;

import com.myblog.payload.PostDto;
import com.myblog.payload.PostResponse;

import java.util.List;

public interface PostService {

   PostDto createPost(PostDto postDto);

   PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

   PostDto getPostById(Long id);

   PostDto updatePost(PostDto postDto, Long id);

   void deletePostByID(Long id);
}
