package kwu.esgproject.service;

import kwu.esgproject.domain.Comment;
import kwu.esgproject.domain.Post;
import kwu.esgproject.domain.User;
import kwu.esgproject.repository.CommentRepository;
import kwu.esgproject.repository.PostRepository;
import kwu.esgproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

//    private final CommentRepository commentRepository;
//    private final PostRepository postRepository;
//    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Long saveComment(Comment comment){
        commentRepository.save(comment);
        return comment.getId();
    }

    // 댓글 생성
    public Long saveComment(Long userID, Long postID, String detail){
        User user = userRepository.findById(userID).get();
        Post post = postRepository.findById(postID).get();

        Comment comment = Comment.createComment(user,post,detail);
        commentRepository.save(comment);

        return comment.getId();
    }


    // 댓글 삭제 댓글이랑 회원 아이디가 일치??
    @Transactional
    public void deleteComment(Long postId, Long commentId)
    {
        validateUpdateComment(postId);
        Comment comment = commentRepository.findById(commentId).get();
        commentRepository.delete(comment);

    }
    public void validateDeleteComment(Long postId ,Long commentId){
        Post findPost= postRepository.findById(postId).get();
        if(findPost == null){
            throw new IllegalStateException("이미 삭제된 게시글입니다");
        }
        Comment comment = commentRepository.findById(commentId).get();
        if(comment == null){
            throw new IllegalStateException("이미 삭제된 댓글입니다");
        }

    }

    // 댓글 수정

    @Transactional
    public void updateComment(Long postId, Long commentId, String detail){

        validateUpdateComment(postId);
        Comment comment = commentRepository.findById(commentId).get();
        comment.setDetail(detail);
        comment.setComment_time(LocalDateTime.now()); // comment Time을 다시 수정할 것인가?


    }

    // 게시글이 먼저 삭제 되었을 때
    public void validateUpdateComment(Long postId)
    {
        Post findPost= postRepository.findById(postId).get();
        if(findPost == null){
            throw new IllegalStateException("이미 삭제된 게시글입니다");
        }

    }


    // == 조회 서비스 == //

    // 한개 조회
    public Comment findOne(Long id){
        return commentRepository.findById(id).orElseThrow();
    }
    // 전체 조회
    public List<Comment> findComments(){
        return commentRepository.findAll();
    }

    // Post에 있는 댓글 조회?




}
