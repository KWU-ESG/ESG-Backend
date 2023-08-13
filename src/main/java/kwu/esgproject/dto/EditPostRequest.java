package kwu.esgproject.dto;

import lombok.Data;

import java.util.List;

@Data
public class EditPostRequest {
    private String title;
    private String detail;

    private List<String> tags;
}
