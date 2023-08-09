package kwu.esgproject.dto;

import kwu.esgproject.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
public class CreatePostRequest {
    private Long userId;

    @Size(min = 1,message = "List size must be more than 1" )
    private List<String> tags = new ArrayList<>();

    private String title;
    private String detail;
}
