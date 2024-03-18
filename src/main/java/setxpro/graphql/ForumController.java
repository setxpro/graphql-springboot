package setxpro.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
public class ForumController {

    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;

    @QueryMapping()
    public Post postById(@Argument String id) {
        return postService.postById(id);
    }

    @MutationMapping()
    public Collection<Post> createPost(@Argument String content) { // Tipo de retorno alterado para Post
        return postService.createPost(content);
    }

    @MutationMapping()
    public Collection<Comment> createComment(@Argument String content, @Argument String postId) {
        return commentService.createComment(content, postId);
    }

    @SchemaMapping()
    public Collection<Comment> comments(Post post) { // Considere usar List<Comment> se esse for o tipo pretendido
        return commentService.findByPost(post.id());
    }
}