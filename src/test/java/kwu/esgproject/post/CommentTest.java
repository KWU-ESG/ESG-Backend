package kwu.esgproject.post;

import kwu.esgproject.domain.Comment;
import kwu.esgproject.domain.Post;
import kwu.esgproject.domain.User;
import kwu.esgproject.repository.CommentRepository;
import kwu.esgproject.service.CommentService;
import kwu.esgproject.service.PostService;
import kwu.esgproject.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CommentTest {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentService commentService;

    @Test
    public void createComment() throws Exception {
        //given
        User user1 = User.createUser("pye", "yenii", "021011", "socute1011@naver.com", "qwe123!");
        User user2 = User.createUser("jam", "jammin", "020220", "sour_jam0220@naver.com", "qwe123!");
        Long userId1 = userService.join(user1);
        Long userId2 = userService.join(user2);

        Post post1 = Post.createPost(user1, "노원을지대학병원 실습", "처음으로 실습 나가요!!");
        //when
        Comment comment = Comment.createComment(user2, post1, "헐 놀러가야지~");
        //then

        Assert.assertEquals("등록한 유저가 동일해야 한다.", user2, comment.getUser());
        Assert.assertEquals("등록한 게시물이 동일해야 한다.", post1, comment.getPost());
        Assert.assertEquals("등록한 내용이 동일해야 한다", "헐 놀러가야지~", comment.getDetail());
    }

    @Test
    public void repositoryTest() throws Exception {
        //given
        User user1 = User.createUser("pye", "yenii", "021011", "socute1011@naver.com", "qwe123!");
        User user2 = User.createUser("jam", "jammin", "020220", "sour_jam0220@naver.com", "qwe123!");
        User user3 = User.createUser("syj", "sul", "990208", "QndQkd@naver.com", "qwe123!");
        Long userId1 = userService.join(user1);
        Long userId2 = userService.join(user2);
        Long userId3 = userService.join(user3);

        Post post1 = Post.createPost(user1, "노원을지대학병원 실습", "처음으로 실습 나가요!!");
        Long postId = postService.savePost(post1);

        Comment comment1 = Comment.createComment(user2, post1, "헐 놀러가야지~");
        Comment comment2 = Comment.createComment(user3, post1, "나도갈뤱");
        commentRepository.save(comment1);
        commentRepository.save(comment2);

        //when
        Comment find1 = commentRepository.findOne(comment1.getId());
        Comment find2 = commentRepository.findOne(comment2.getId());
        List<Comment> all = commentRepository.findAll();

        //then
        Assert.assertEquals("코멘트가 제대로 등록되었는지 확인1", comment1, find1);
        Assert.assertEquals("코멘트가 제대로 등록되었는지 확인2", comment2, find2);

        Assert.assertEquals("등록된 코멘트 게시물 확인1", postId, comment1.getPost().getId());
        Assert.assertEquals("등록된 코멘트 게시물 확인2", postId, comment2.getPost().getId());

        Assert.assertEquals("등록된 코멘트 내용 확인", "헐 놀러가야지~", comment1.getDetail());
        Assert.assertEquals("등록된 코멘트 내용 확인", "나도갈뤱", comment2.getDetail());

        Assert.assertEquals("등록된 코멘트 수 확인", 6, all.size());
    }

    @Test
    public void serviceTest() throws Exception {
        //given
        User user1 = User.createUser("pye", "yenii", "021011", "socute1011@naver.com", "qwe123!");
        User user2 = User.createUser("jam", "jammin", "020220", "sour_jam0220@naver.com", "qwe123!");
        User user3 = User.createUser("syj", "sul", "990208", "QndQkd@naver.com", "qwe123!");
        Long userId1 = userService.join(user1);
        Long userId2 = userService.join(user2);
        Long userId3 = userService.join(user3);

        Post post1 = Post.createPost(user1, "노원을지대학병원 실습", "처음으로 실습 나가요!!");
        Long postId = postService.savePost(post1);

        Comment comment1 = Comment.createComment(user2, post1, "헐 놀러가야지~");
        Comment comment2 = Comment.createComment(user3, post1, "나도갈뤱");
        //when
        Long commentId1 = commentService.saveComment(comment1);
        Long commentId2 = commentService.saveComment(comment2);

        Comment find1 = commentService.findOne(commentId1);
        Comment find2 = commentService.findOne(commentId2);

        List<Comment> comments = commentService.findComments();

        commentService.updateComment(postId, commentId2, "파이팅이야~");
        //then
        Assert.assertEquals("저장된 comment_id와 저장시킨 comment_id가 같아야 한다1.", comment1.getId(), commentId1);
        Assert.assertEquals("저장된 comment_id와 저장시킨 comment_id가 같아야 한다2.", comment2.getId(), commentId2);

        Assert.assertEquals("id로 찾은 comment와 등록한 comment id가 같아야 한다1.", comment1, find1);
        Assert.assertEquals("id로 찾은 comment와 등록한 comment id가 같아야 한다2.", comment2, find2);

        Assert.assertEquals("등록된 코멘트 수 확인", 6, comments.size());

        Assert.assertEquals("수정된 내용이 동일해야 한다.", "파이팅이야~", find2.getDetail());
    }

}
