package top.canoe0.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.canoe0.blog.entity.Comment;
import top.canoe0.blog.service.CommentService;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/addComment")
    public Comment addComment(int userId, String userType, String commentContent, int articleId) {
        return commentService.saveComment(userId, userType, commentContent, articleId);
    }
}
