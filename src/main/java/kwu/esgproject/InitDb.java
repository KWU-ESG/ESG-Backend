package kwu.esgproject;

import kwu.esgproject.domain.*;
import kwu.esgproject.service.*;
import kwu.esgproject.domain.*;
import kwu.esgproject.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

import static kwu.esgproject.domain.Interest.*;

/*@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        private final UserService userService;
        private final PostService postService;
        private final CommentService commentService;
        private final CompanyService companyService;
        private final NewsService newsService;
        private final DonationService donationService;
        public void dbInit1(){
            User user1 = User.createUser("Shin", "Jammin", "020220", "sour_jam0220@naver.com", "qweasd123", E);
            User user2 = User.createUser("Ye", "Yomi", "020720", "yeye@naver.com", "qweasd123", S);
            User user3 = User.createUser("Sul", "Cucu", "990526", "sulyou@naver.com", "qweasd123", G);
            User user4 = User.createUser("Gu", "Gook", "021020", "jungwon@naver.com", "qweasd123", E);
            Long userId1 = userService.join(user1);
            Long userId2 = userService.join(user2);
            Long userId3 = userService.join(user3);
            Long userId4 = userService.join(user4);

            Post post1 = Post.createPost(user1, "재민이의 주짓수 대회1", "재민이가 8월 20일에 대전 주짓수 대회를 나가요!");
            Post post2 = Post.createPost(user2, "재민이의 주짓수 대회2", "재민이가 8월 26일에 서강대 주짓수 대회를 나가요!");
            Post post3 = Post.createPost(user3, "방구 대소동", "방구를 뿡 뿡 뿡");
            Post post4 = Post.createPost(user4, "주술회전 극장판", "고죠 사토루가 갑자기 어려졌다고?! 궁금하다면 나도 몰라요");
            Long postId1 = postService.savePost(post1);
            Long postId2 = postService.savePost(post2);
            Long postId3 = postService.savePost(post3);
            Long postId4 = postService.savePost(post4);

            Comment comment1 = Comment.createComment(user1, post1, "헐 꼭 이기길 빌게요!!");
            Comment comment2 = Comment.createComment(user2, post2, "응원 가고싶은데ㅜㅜ 알바가 있네,,,");
            Comment comment3 = Comment.createComment(user3, post2, "응월 갈겡 뚜들겨 맞는거 구경해야지~");
            Comment comment4 = Comment.createComment(user4, post2, "나도 갈래갈래갈래갈래");
            Long commentId1 = commentService.saveComment(comment1);
            Long commentId2 = commentService.saveComment(comment2);
            Long commentId3 = commentService.saveComment(comment3);
            Long commentId4 = commentService.saveComment(comment4);

            Company company1 = Company.createCompany("넷플릭스", "Ott기반 영상 서비스", "한국 어딘가", 10000);
            Company company2 = Company.createCompany("라프텔", "Ott기반 영상 서비스", "한국 어딘가", 50000);
            Long companyId1 = companyService.registration(company1);
            Long companyId2 = companyService.registration(company2);

            News news1 = News.createNews("넷플릭스 너무 비싸", company1, "너무 비싼데 재밌어서 본다고 전해져,,", E);
            News news2 = News.createNews("라프텔 귀멸의 칼날 이번달까지", company2, "최대 애니 OTT인 라프텔에서 귀멸의 칼날을 이번달까지만 공개한다고 전해져,,", E);
            News news3 = News.createNews("뉴스3", company2, "재민이의", E);
            News news4 = News.createNews("뉴스4", company2, "사랑은", S);
            News news5 = News.createNews("뉴스5", company2, "여기까지..", G);
            Long newsId1 = newsService.registration(news1);
            Long newsId2 = newsService.registration(news2);
            Long newsId3 = newsService.registration(news3);
            Long newsId4 = newsService.registration(news4);
            Long newsId5 = newsService.registration(news5);

            Donate donate1 = Donate.createDonate(user1, company1, 50000);
            Donate donate2 = Donate.createDonate(user3, company2, 100000);
            Donate donate3 = Donate.createDonate(user2, company2, 10000);
            Donate donate4 = Donate.createDonate(user4, company2, 70000);
            Donate donate5 = Donate.createDonate(user3, company1, 90000);
            Donate donate6 = Donate.createDonate(user3, company2, 190000);

            Long donationId1 = donationService.donation(donate1);
            Long donationId2 = donationService.donation(donate2);
            Long donationId3 = donationService.donation(donate3);
            Long donationId4 = donationService.donation(donate4);
            Long donationId5 = donationService.donation(donate5);
            Long donationId6 = donationService.donation(donate6);

        }
    }
}
*/