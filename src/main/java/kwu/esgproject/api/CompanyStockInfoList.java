package kwu.esgproject.api;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


@Getter
@Setter
public class CompanyStockInfoList {

    // 원하는 것 공공데이터에서 불러와서 원하는 데이터 parsing 해서 가공된 data를 DB에다가 저장하기
    // stock info는 Entity여야함 수정이 필요해 보인다?? 2023/08/24

    static public class CompanyStockInfoListRequest{
        private String day; // api basDt 현재 날짜
        private String srtnCd; // api srtnCd 주가 번호

        private String isinCd; //api isinCd isbn 번호?

        private String itmsNm; // api itmsNm 주가 종목명

        private String mrktCtg; // api mrktCtg // KOSPI인가 아니면 KOSDAK

        private String clpr; // api clpr // 현재 주가?

        private String vs; // api vs // 등락률

        private String mkp; // api mkp // market price 시장가

        private String hipr; // api hipr // 장중 가장 높았던 주가

        private String lopr; // api lopr // 장중 가장 낮았던 주가

        private String trqu; // api trqu// 장 시작과 장 마감까지의 하루 거래량

        private String trPrc; // api trPrc // 장 시작과 장 마감까지의 하루 거래대금 아마 1원 단위?

        private String lstgStCnt; // api lstgStCnt // 상장 주식수 ? 몰루

        private String mrktToAmt; // api mrktToAmt // 회사의 시가총액


    }
    static public class CompanyStockInfo{
        private String day; // api basDt 현재 날짜
        private String srtnCd; // api strnCd 주가 번호

        private String itmsNm; // api itmsNm 주가 종목명

        private String clpr; // api clpr // 현재 주가?

        private String mrktToAmt; // api mrktToAmt // 회사의 시가총액

        private String trqu; // api trqu// 장 시작과 장 마감까지의 하루 거래량

        private String trPrc; // api trPrc // 장 시작과 장 마감까지의 하루 거래대금 아마 1원 단위?

    }
    static public class CompanyStockInfoListResponse{
        private CompanyStockInfoListResult companyStockInfoListResult;
    }

    static public class CompanyStockInfoListResult{
        private ArrayList<CompanyStockInfo> CompanyStockInfoListResult;
    }



}
