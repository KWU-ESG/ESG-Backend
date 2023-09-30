package kwu.esgproject.repository;

import kwu.esgproject.domain.Interest;
import kwu.esgproject.dto.PostListDto;

import java.util.List;

public interface PostDataRepositoryCustom {
    List<PostListDto> searchByInterest(Interest interest);
}
