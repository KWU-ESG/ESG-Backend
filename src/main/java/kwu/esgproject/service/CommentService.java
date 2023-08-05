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

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 댓글 생성
    public Long saveComment(Long userID, Long postID, String detail){
        User user = userRepository.findOne(userID);
        Post post = postRepository.findOne(postID);

        Comment comment = Comment.createComment(user,post,detail);
        commentRepository.save(comment);

        return comment.getId();
    }


    // 댓글 삭제 댓글이랑 회원 아이디가 일치??
    @Transactional
    public void deleteComment(Long id)
    {
        Comment comment = commentRepository.findOne(id);

        // 게시판에서 댓글 지우기

        // 회원이 쓴 댓글 지우기

        commentRepository.delete(comment);

    }


    // 댓글 수정
    public void updateComment(Long id,String detail){
        Comment comment = commentRepository.findOne(id);
        comment.setDetail(detail);
        comment.setComment_time(LocalDateTime.now()); // comment Time을 다시 수정할 것인가?

    }


    // == 조회 서비스 == //

    // 한개 조회
    public Comment findOne(Long id){
        return commentRepository.findOne(id);
    }
    // 전체 조회
    public List<Comment> findComments(){
        return commentRepository.findAll();
    }

    // Post에 있는 댓글 조회?




}
