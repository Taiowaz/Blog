package top.canoe0.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.canoe0.blog.entity.Comment;
import top.canoe0.blog.repository.CommentRepository;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findCommentsByArticleId(int articleId) {
        return commentRepository.findCommentsByArticleId(articleId);
    }

    public Comment saveComment(String account, String commentContent, int articleId) {
        Comment comment = new Comment();
        comment.setAccount(account);
        comment.setCommentContent(commentContent);
        comment.setArticleId(articleId);

        return commentRepository.save(comment);
    }
}
