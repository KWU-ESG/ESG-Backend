package kwu.esgproject.controller;

import kwu.esgproject.domain.Company;
import kwu.esgproject.domain.Post;
import kwu.esgproject.domain.User;
import kwu.esgproject.dto.*;
import kwu.esgproject.service.PostService;
import kwu.esgproject.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PostController{
    private final PostService postService;
    private final UserService userService;

    @GetMapping("/forum/list")
    @ResponseBody
    public Result companyList() {
        List<Post> postList = postService.findAllPost();
        List<PostListDto> collect = postList.stream()
                .map(p -> new PostListDto(p.getUser().getNickname(), p.getTitle(), p.getDetail(), p.getTags(), p.getViews(), p.getLikes(), p.getShare(), p.getCommentList().size(), p.getPost_time()))
                .collect(Collectors.toList());

        return new Result<>(collect.size(), collect);
    }

    @GetMapping("/forum/{id}")
    @ResponseBody
    public PostDto companyList(@PathVariable("id") Long postId) {
        Post findPost = postService.findPost(postId);
        return new PostDto(findPost.getUser().getNickname(), findPost.getTitle(), findPost.getDetail(), findPost.getTags(), findPost.getViews(), findPost.getLikes(), findPost.getShare(), findPost.getCommentList().size(), findPost.getCommentList(), findPost.getPost_time());
    }

    @PostMapping("/post/publish")
    public CreateCompanyResponse registrationCompany(@RequestBody @Valid CreatePostRequest request){
        User user = userService.findOne(request.getUserId());
        Post post = Post.createPost(user, request.getTitle(), request.getDetail());
        for (String tag : request.getTags()) {
            post.addTag(tag);
        }
        Long postId = postService.savePost(post);

        return new CreateCompanyResponse(postId);
    }
    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T company;
    }

    @Data
    static class CreateCompanyResponse {
        private Long id;

        public CreateCompanyResponse(Long id) {
            this.id = id;
        }
    }

}
