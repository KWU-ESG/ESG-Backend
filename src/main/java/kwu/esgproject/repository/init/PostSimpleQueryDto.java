package kwu.esgproject.repository.init;

import kwu.esgproject.domain.Open;
import lombok.Data;

@Data
public class PostSimpleQueryDto {

    private Long id;

    private String detail;
    private int views;
    private int likes;
    private int share;
    private Open open;

    public PostSimpleQueryDto(Long id,  String detail, int views, int likes, int share, Open open) {
        this.id = id;
        this.detail = detail;
        this.views = views;
        this.likes = likes;
        this.share = share;
        this.open = open;
    }
}
