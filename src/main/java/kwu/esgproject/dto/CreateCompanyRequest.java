package kwu.esgproject.dto;

import lombok.Data;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
public class CreateCompanyRequest {
    private String name;

    @Size(min = 1,message = "List size must be more than 1" )
    private List<String> tags = new ArrayList<>();

    private String location; // api 써서 location 찾기
    private int stock; // api 써서 주식 데이터 불러오기 ?? 아마 조금더 복잡해질지도 ??
}
