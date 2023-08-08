package kwu.esgproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class CompanyListDto {
    private String name;
    private List<String> tags = new ArrayList<>();

    private String location; // api 써서 location 찾기
    private int stock; // api 써서 주식 데이터 불러오기 ?? 아마 조금더 복잡해질지도 ??
    private int total_donation;
}
