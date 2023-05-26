package top.canoe0.blog.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.canoe0.blog.entity.Comment;
import top.canoe0.blog.repository.CommentRepository;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    public JSONArray findCommentJSONListByArticleId(int articleId) {
        List<Comment> commentList = commentRepository.findCommentsByArticleId(articleId);
        if (commentList == null) return null;

        JSONArray commentJSONArray = new JSONArray();

        for (Comment comment : commentList) {
            JSONObject commentJSON = new JSONObject();

            String account;
            String avatarUrl;
            if (comment.getUserType().equals("admin")) {
                account = userService.findAdminById(comment.getUserId()).getAccount();
                avatarUrl = userService.findAdminById(comment.getUserId()).getAvatarUrl();
            } else {
                account = userService.findRegularUserById(comment.getUserId()).getAccount();
                avatarUrl = userService.findRegularUserById(comment.getUserId()).getAvatarUrl();
            }

            commentJSON.put("account", account);
            commentJSON.put("avatarUrl", avatarUrl);
            commentJSON.put("commentContent", comment.getCommentContent());

            commentJSONArray.add(commentJSON);
        }
        return commentJSONArray;
    }

    public Comment saveComment(int userId, String usertype, String commentContent, int articleId) {
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setUserType(usertype);
        comment.setCommentContent(commentContent);
        comment.setArticleId(articleId);

        return commentRepository.save(comment);
    }
}
