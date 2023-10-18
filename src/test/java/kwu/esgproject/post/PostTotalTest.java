package kwu.esgproject.post;

import kwu.esgproject.domain.Interest;
import kwu.esgproject.domain.Post;
import kwu.esgproject.domain.User;
import kwu.esgproject.repository.init.PostJpaRepository;
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
public class PostTotalTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PostJpaRepository postJpaRepository;
    @Autowired
    private PostService postService;

    @Test
    public void createPost() throws Exception {
        //given
        User user = User.createUser("pye", "yenii", "021011", "socute1011@naver.com", "qwe123!", Interest.E);
        User user1 = User.createUser("jam", "jammin", "020220", "sour_jam0220@naver.com", "qwe123!", Interest.S);
        Long userId = userService.join(user);
        Long userId1 = userService.join(user1);

        //when
        Post post = Post.createPost(user, "노원을지대학병원 실습", "처음으로 실습 나가는데 준비해야할게 뭐가 있을까요ㅜㅜ?");
        for(int i=0;i<10;i++){
            post.addLike();
            post.addView();
            post.addShare();
        }

        //then
        Assert.assertEquals("등록한 유저가 같아야 한다.", user, post.getUser());
        Assert.assertEquals("유저가 등록한 포스트의 제목이 같아야 한다.", "노원을지대학병원 실습", post.getTitle());
        Assert.assertEquals("유저가 등록한 포스트의 내용이 같아야 한다.", "처음으로 실습 나가는데 준비해야할게 뭐가 있을까요ㅜㅜ?", post.getDetail());
        Assert.assertEquals("종아요 수가 10이여야 한다..", 10, post.getLikes());
        Assert.assertEquals("조회수가 10이여야 한다..", 10, post.getViews());
        Assert.assertEquals("공유 수가 10이여야 한다..", 10, post.getShare());
    }

    @Test
    public void repositoryTest() throws Exception {
        //given
        User user1 = User.createUser("pye", "yenii", "021011", "socute1011@naver.com", "qwe123!", Interest.E);
        User user2 = User.createUser("jam", "jammin", "020220", "sour_jam0220@naver.com", "qwe123!", Interest.S);
        Long userId1 = userService.join(user1);
        Long userId2 = userService.join(user2);

        Post post1 = Post.createPost(user1, "노원을지대학병원 실습", "처음으로 실습 나가는데 준비해야할게 뭐가 있을까요ㅜㅜ?");
        Post post2 = Post.createPost(user1, "나이키 매장 면접", "꿀팁좀 주세요");
        Post post3 = Post.createPost(user2, "주짓수 대회 처음 나가는데", "꿀팁 주세요ㅎㅎ");
        //when
        postJpaRepository.save(post1);
        postJpaRepository.save(post2);
        postJpaRepository.save(post3);

        Post findOne1 = postJpaRepository.findOne(post1.getId());
        Post findOne2 = postJpaRepository.findOne(post2.getId());
        postJpaRepository.remove(post3);
        List<Post> all = postJpaRepository.findAll();
//        Post findOne3 = postRepository.findOne(post3.getId());

        //then
        Assert.assertEquals("3개의 Post 모두 정상적으로 등록되었는지 확인", post1, findOne1);
        Assert.assertEquals("3개의 Post 모두 정상적으로 등록되었는지 확인", post2, findOne2);
//        Assert.assertEquals("3개의 Post 모두 정상적으로 등록되었는지 확인", post3, findOne3);

        Assert.assertEquals("등록한 유저가 같아야 한다.", user1, findOne1.getUser());
        Assert.assertEquals("유저가 등록한 포스트의 제목이 같아야 한다.", "노원을지대학병원 실습", findOne1.getTitle());
        Assert.assertEquals("유저가 등록한 포스트의 내용이 같아야 한다.", "처음으로 실습 나가는데 준비해야할게 뭐가 있을까요ㅜㅜ?", findOne1.getDetail());

//        Assert.assertEquals("모두 조회했을 때 총 7개가 나와야 한다(InitDb로 미리 저장해 둔 것).", 7, all.size());
        Assert.assertEquals("(삭제 후)모두 조회했을 때 총 6개가 나와야 한다(InitDb로 미리 저장해 둔 것).", 6, all.size());

    }

    @Test
    public void serviceTest() throws Exception {
        //given
        User user1 = User.createUser("pye", "yenii", "021011", "socute1011@naver.com", "qwe123!", Interest.E);
        User user2 = User.createUser("jam", "jammin", "020220", "sour_jam0220@naver.com", "qwe123!", Interest.S);
        Long userId1 = userService.join(user1);
        Long userId2 = userService.join(user2);

        Post post1 = Post.createPost(user1, "노원을지대학병원 실습", "처음으로 실습 나가는데 준비해야할게 뭐가 있을까요ㅜㅜ?");
        Post post2 = Post.createPost(user1, "나이키 매장 면접", "꿀팁좀 주세요");
        Post post3 = Post.createPost(user2, "주짓수 대회 처음 나가는데", "꿀팁 주세요ㅎㅎ");
        //when
        Long postId1 = postService.savePost(post1);
        Long postId2 = postService.savePost(post2);
        Long postId3 = postService.savePost(post3);

        Post findPost = postService.findPost(postId1);

        List<Post> allPost = postService.findAllPost();

        postService.editPost(postId3, "주짓수 대회 꿀팁", "싱대를 죽이겠다는 마음으로 임한다.");

        Post findPost3 = postService.findPost(postId3);

        //then
        Assert.assertEquals("3개의 Post 모두 정상적으로 등록되었는지 확인", post1, findPost);

        Assert.assertEquals("등록한 유저가 같아야 한다.", user1, findPost.getUser());
        Assert.assertEquals("유저가 등록한 포스트의 제목이 같아야 한다.", "노원을지대학병원 실습", findPost.getTitle());
        Assert.assertEquals("유저가 등록한 포스트의 내용이 같아야 한다.", "처음으로 실습 나가는데 준비해야할게 뭐가 있을까요ㅜㅜ?", findPost.getDetail());

        Assert.assertEquals("모두 조회했을 때 총 7개가 나와야 한다(InitDb로 미리 저장해 둔 것).", 7, allPost.size());

        Assert.assertEquals("수정된 제목이 변경되어야 한다.", "주짓수 대회 꿀팁", findPost3.getTitle());
        Assert.assertEquals("수정된 내용이 변경되어야 한다.", "싱대를 죽이겠다는 마음으로 임한다.", findPost3.getDetail());
    }


}


