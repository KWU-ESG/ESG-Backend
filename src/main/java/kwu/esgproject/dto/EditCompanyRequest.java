package kwu.esgproject.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EditCompanyRequest {
    private String name;
    private List<String> tags = new ArrayList<>();

    private String description;

    private String location; // api 써서 location 찾기
    private int stock; // api 써서 주식 데이터 불러오기 ?? 아마 조금더 복잡해질지도 ??
}
