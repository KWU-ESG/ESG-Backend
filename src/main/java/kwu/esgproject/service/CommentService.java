package kwu.esgproject.service;

import kwu.esgproject.domain.Comment;
import kwu.esgproject.domain.Post;
import kwu.esgproject.domain.User;
import kwu.esgproject.repository.CommentDataRepository;
import kwu.esgproject.repository.PostDataRepository;
import kwu.esgproject.repository.UserDataRepository;
import kwu.esgproject.repository.init.CommentRepository;
import kwu.esgproject.repository.init.PostRepository;
import kwu.esgproject.repository.init.UserRepository;
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
    private final CommentDataRepository commentDataRepository;
    private final PostDataRepository postDataRepository;
    private final UserDataRepository userDataRepository;

    public Long saveComment(Comment comment){
        commentDataRepository.save(comment);
        return comment.getId();
    }

    // 댓글 생성
    public Long saveComment(Long userID, Long postID, String detail){
        User user = userDataRepository.findById(userID).get();
        Post post = postDataRepository.findById(postID).get();

        Comment comment = Comment.createComment(user,post,detail);
        commentDataRepository.save(comment);

        return comment.getId();
    }


    // 댓글 삭제 댓글이랑 회원 아이디가 일치??
    @Transactional
    public void deleteComment(Long postId, Long commentId)
    {
        validateUpdateComment(postId);
        Comment comment = commentDataRepository.findById(commentId).get();
        commentDataRepository.delete(comment);

    }
    public void validateDeleteComment(Long postId ,Long commentId){
        Post findPost= postDataRepository.findById(postId).get();
        if(findPost == null){
            throw new IllegalStateException("이미 삭제된 게시글입니다");
        }
        Comment comment = commentDataRepository.findById(commentId).get();
        if(comment == null){
            throw new IllegalStateException("이미 삭제된 댓글입니다");
        }

    }

    // 댓글 수정

    @Transactional
    public void updateComment(Long postId, Long commentId, String detail){

        validateUpdateComment(postId);
        Comment comment = commentDataRepository.findById(commentId).get();
        comment.setDetail(detail);
        comment.setComment_time(LocalDateTime.now()); // comment Time을 다시 수정할 것인가?


    }

    // 게시글이 먼저 삭제 되었을 때
    public void validateUpdateComment(Long postId)
    {
        Post findPost= postDataRepository.findById(postId).get();
        if(findPost == null){
            throw new IllegalStateException("이미 삭제된 게시글입니다");
        }

    }


    // == 조회 서비스 == //

    // 한개 조회
    public Comment findOne(Long id){
        return commentDataRepository.findById(id).orElseThrow();
    }
    // 전체 조회
    public List<Comment> findComments(){
        return commentDataRepository.findAll();
    }

    // Post에 있는 댓글 조회?




}
