package com.workintech.twitter.controller;

import com.workintech.twitter.dto.CommentRequest;
import com.workintech.twitter.dto.CommentResponse;
import com.workintech.twitter.entity.Comment;
import com.workintech.twitter.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<CommentResponse> getAll(){
        return commentService.getAll()
                .stream()
                .map(comment -> new CommentResponse(comment.getContent()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{commentId}")
    public CommentResponse getById(@PathVariable("commentId") Long id){
        Comment comment = commentService.getById(id);
        return new CommentResponse(comment.getContent());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponse create (@Validated @RequestBody CommentRequest commentRequest){
        Comment comment = new Comment();
        comment.setContent(commentRequest.getContent());

        commentService.save(comment);
        return new CommentResponse(comment.getContent());
    }

    @PutMapping("/{commentId}")
    public CommentResponse replaceOrCreate(@PathVariable("commentId") Long id,@Validated @RequestBody CommentRequest commentRequest){
        Comment comment = new Comment();
        comment.setContent(commentRequest.getContent());

        commentService.replaceOrCreate(id, comment);

        return new CommentResponse(comment.getContent());
    }

    @PatchMapping("/{commentId}")
    public CommentResponse update(@PathVariable("commentId") Long id,@Validated @RequestBody CommentRequest commentRequest){
        Comment comment = new Comment();
        comment.setContent(commentRequest.getContent());
        commentService.replaceOrCreate(id, comment);

        return new CommentResponse(comment.getContent());
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("commentId") Long id){
        commentService.deleteById(id);
    }
}
