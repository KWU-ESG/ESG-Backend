package kwu.esgproject.service;

import kwu.esgproject.domain.Post;
import kwu.esgproject.domain.User;
import kwu.esgproject.repository.CommentRepository;
import kwu.esgproject.repository.PostRepository;
import kwu.esgproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    // 게시글 작성
    @Transactional
    public Long savePost(Post post){
        // 중복 방지 함수
        //validateDuplicatePost(post);
        postRepository.save(post);
        //
        return post.getId();
    }

    public List<Post> findAllPost(){
        return postRepository.findAll();
    }

    public Post findPost(Long postId){
        return postRepository.findOne(postId);
    }

    public void validateDuplicatePost(Post post){
        List<Post> findPost = postRepository.findById(post.getId());

    }

    //삭제
    @Transactional
    public void deletePost(Long id){
        Post post = postRepository.findOne(id);
        // 사용자

        //
        postRepository.remove(post);

    }


    //수정??
    @Transactional
    public void updatePost(Long id , String detail, String ...Tags){
        Post post = postRepository.findOne(id);
        post.setDetail(detail);
        // 수정한 날짜를 다시 PostTime 으로 할 것인지 아니면 수정한 PostTime의 로그를 저장할 것인지 ?
        post.setPost_time(LocalDateTime.now());

        // Tag 를 전부 날려버리고 다시 넣어주기 -> 그냥 set해도 괜찮을지도?
        // post.deleteTags();
        // tags 다시 delete하고 set하기 ??
        // 예상되는 문제점 list로 했을 때 중간에 delete 하면
        // null 값으로 들어가고 size만 계속 늘어나는 문제 있는지



        // 그냥 set 으로 하는게 좋을거 같긴하다

        //List<String> inputTags = Arrays.asList(Tags);
        post.setTags(Arrays.asList(Tags));


    }


}
