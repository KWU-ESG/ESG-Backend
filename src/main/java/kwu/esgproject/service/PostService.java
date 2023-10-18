package kwu.esgproject.service;

import kwu.esgproject.domain.Interest;
import kwu.esgproject.domain.Post;
import kwu.esgproject.dto.PostListDto;
import kwu.esgproject.repository.PostRepository;
import kwu.esgproject.repository.init.PostJpaRepository;
import kwu.esgproject.repository.init.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostJpaRepository postJpaRepository;
    private final UserJpaRepository userJpaRepository;

    private final PostRepository postRepository;
    // 게시글 작성
    @Transactional
    public Long savePost(Post post){
        // 중복 방지 함수
        postRepository.save(post);

        return post.getId();
    }

    public List<Post> findAllPost(){
        return postRepository.findAll();
    }

    public Post findPost(Long postId){
        return postRepository.findById(postId).orElseThrow();
    }

    public Long closePost(Long postId){
        Post findOne = postRepository.findById(postId).get();
        findOne.setClose();

        return postId;
    }

    public Long openPost(Long postId){
        Post findOne = postRepository.findById(postId).get();
        findOne.setOpen();

        return postId;
    }

    public List<PostListDto> searchByInterest(Interest interest){
        return postRepository.searchByInterest(interest);
    }

    //삭제
    @Transactional
    public void deletePost(Long id){
        Post post = postRepository.findById(id).get();
        // 사용자

        //
        postRepository.delete(post);

    }


    //수정??
    @Transactional
    public void editPost(Long id , String title, String detail){
        Post post = postRepository.findById(id).get();
        post.setTitle(title);
        post.setDetail(detail);
        // 수정한 날짜를 다시 PostTime 으로 할 것인지 아니면 수정한 PostTime의 로그를 저장할 것인지 ?
        post.setPost_time(LocalDateTime.now());

        // Tag 를 전부 날려버리고 다시 넣어주기 -> 그냥 set해도 괜찮을지도?
        // post.deleteTags();
        // tags 다시 delete하고 set하기 ??
        // 예상되는 문제점 list로 했을 때 중간에 delete 하면
        // null 값으로 들어가고 size만 계속 늘어나는 문제 있는지
    }



}
