package kwu.esgproject.controller;

import kwu.esgproject.domain.Comment;
import kwu.esgproject.domain.Post;
import kwu.esgproject.dto.Comment.CommentDto;
import kwu.esgproject.dto.Comment.CommentUpdateRequest;
import kwu.esgproject.dto.Comment.CommentWriteRequest;
import kwu.esgproject.service.CommentService;
import kwu.esgproject.service.PostService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;

    // 댓글 등록 Post
    @PostMapping("/comments/{post_id}")
    public WriteCommentResponse writeComment(@PathVariable("post_id") Long postId
            ,@RequestBody @Valid CommentWriteRequest request){


        Post post = postService.findPost(postId);
        Long id = commentService.saveComment(request.getUserId(), post.getId(), request.getDetail());

        return new WriteCommentResponse(id);
    }


    // 댓글 수정 update
    @PutMapping("/comments/{post_id}/{comment_id}")
    public UpdateCommentResponse updateComment(@PathVariable("post_id") Long postId,
                                               @PathVariable("comment_id") Long commentId,@RequestBody @Valid CommentUpdateRequest request){

        Post post = postService.findPost(postId);
        Comment comment = commentService.findOne(commentId);
        commentService.updateComment(post.getId(),comment.getId(),request.getDetail());

        CommentDto commentDto = new CommentDto(comment.getId(),comment.getUser(),post,comment.getDetail(),comment.getLikes(),comment.getComment_time());

        return new UpdateCommentResponse(commentDto.getId(),commentDto.getUser().getId(),commentDto.getUser().getNickname(), comment.getDetail());
    }

    // 댓글 삭제 delete
    @DeleteMapping("/comments/{post_id}/{comment_id}")
    public DeleteCommentResponse deleteComment(@PathVariable("post_id") Long postId,
                                               @PathVariable("comment_id") Long commentId){

        Post post = postService.findPost(postId);
        Comment comment = commentService.findOne(commentId);
        commentService.deleteComment(post.getId(),comment.getId());

        CommentDto commentDto = new CommentDto(comment.getId(),comment.getUser(),post,comment.getDetail(),comment.getLikes(),comment.getComment_time());

        return new DeleteCommentResponse(commentDto.getId(),commentDto.getDetail()) ;
    }
    //

    @Data
    static class WriteCommentResponse{
        private Long id;


        public WriteCommentResponse(Long id) {
            this.id = id;
        }
    }
    @Data
    static class UpdateCommentResponse{
        private Long commentId;

        private Long userId;

        private String userNickname;
        private String detail;

        public UpdateCommentResponse(Long commentId, Long userId, String userNickname, String detail) {
            this.commentId = commentId;
            this.userId = userId;
            this.userNickname = userNickname;
            this.detail = detail;
        }
    }

    @Data
    static class DeleteCommentResponse{
        private Long id;
        private String detail;

        public DeleteCommentResponse(Long id, String detail) {
            this.id = id;
            this.detail = detail;
        }
    }

}
