package WeatherWearsYou.external;

import java.util.*;

public record CompletionRequest(String model, String prompt, double temperature, int max_tokens) {

    private static final LinkedHashMap<String, Map<String, Object>> Hashtag = new LinkedHashMap<>();
    static {
        Map<String, Object> tMap = new HashMap<String, Object>();
        tMap.put("셔츠/블라우스", Arrays.asList("크롭", "오버핏", "옥스포드", "린넨", "자수", "프릴"));
        tMap.put("긴소매 티셔츠", Arrays.asList("크롭", "오버핏", "래글런", "텐셀", "릴렉스드"));
        tMap.put("니트/스웨터", Arrays.asList("하프집업니트", "카라니트", "니트베스트", "크롭니트", "크롭 오프숄더", "린넨", "부클", "풀오버", "슬릿"));
        tMap.put("반소매 티셔츠", Arrays.asList("그래픽", "로고/레터링", "무지", "크롭", "오버핏", "자수"));
        tMap.put("맨투맨/스웨트셔츠", Arrays.asList("크롭", "오버핏", "무지", "셋업", "로고", "하프집업", "베이식", "그래픽"));
        tMap.put("민소매 티셔츠", Arrays.asList("레이어드", "크롭", "탱크탑", "뷔스티에", "골지"));
        tMap.put("후드 티셔츠", Arrays.asList("로고", "그래픽", "베이식", "하프집업", "크롭"));
        tMap.put("피케/카라 티셔츠", null);
        tMap.put("스포츠상의", null);
        tMap.put("기타상의", Arrays.asList("크롭", "하프집업", "탱크탑", "오버핏", "자수"));
        Hashtag.put("\nTop: ", tMap);

        Map<String, Object> bMap = new HashMap<String, Object>();
        bMap.put("트레이닝/조거 팬츠", Arrays.asList("와이드팬츠", "와이드핏", "오버핏", "밴딩", "트레이닝쇼츠"));
        bMap.put("숏팬츠", Arrays.asList("나일론 쇼츠", "밴딩", "오버핏", "핀턱", "치노", "시어서커", "린넨", "원턱", "투턱"));
        bMap.put("데님 팬츠", Arrays.asList("와이드핏", "부츠컷", "테이퍼드", "연청", "진청", "흑청", "생지"));
        bMap.put("코튼 팬츠", Arrays.asList("와이드핏", "테이퍼드", "치노", "원턱", "밴딩", "린넨", "오버핏"));
        bMap.put("슈트 팬츠/슬랙스", Arrays.asList("와이드팬츠"));
        bMap.put("스포츠하의", null);
        bMap.put("점프 슈트/오버올", Arrays.asList("오버핏", "린넨", "테이퍼드", "벨티드", "커팅크롭", "트윌", "하이웨이스트", "멜빵"));
        bMap.put("레깅스", Arrays.asList("요가복", "부츠컷", "하이웨이스트", "슬릿", "운동복", "타이즈", "필라테스"));
        bMap.put("미니스커트", Arrays.asList("청치마", "생지", "밴딩", "투피스", "버클", "레이어드", "슬릿"));
        bMap.put("미디스커트", Arrays.asList("슬릿", "밴딩", "레이어드", "청치마", "벨티드"));
        bMap.put("롱스커트", Arrays.asList("청치마", "밴딩", "슬릿", "핀턱", "프릴"));
        bMap.put("기타 바지", Arrays.asList("린넨", "부츠컷", "밴딩", "원턱", "테이퍼드", "오버핏", "레이어드", "와이드핏"));
        Hashtag.put("\nBottom: ", bMap);

        Map<String, Object> oMap = new HashMap<String, Object>();
        oMap.put("나일론/코치 재킷", null);
        oMap.put("스타디움 재킷", null);
        oMap.put("사파리/헌팅 재킷", null);
        oMap.put("블루종/MA-1", null);
        oMap.put("아노락 재킷", null);
        oMap.put("트러커 재킷", null);
        oMap.put("트레이닝 재킷", null);
        oMap.put("슈트/블레이저 재킷", null);
        oMap.put("레더/라이더스 재킷", null);
        oMap.put("롱패딩/롱헤비 아우터", null);
        oMap.put("카디건", Arrays.asList("베이직카디건", "크롭카디건", "볼레로", "부클", "크롭", "오버핏", "골지", "자수", "아가일"));
        oMap.put("플리스/뽀글이", null);
        oMap.put("겨울 싱글 코트", null);
        oMap.put("숏패딩/숏헤비 아우터", Arrays.asList("플라이트", "경량", "덕다운", "퀼팅", "오버핏", "크롭", "양털"));
        oMap.put("베스트", Arrays.asList("부클", "크롭", "뷔스티에", "아가일", "크로셰", "레이어드", "오버핏", "자카드", "자수", "경량"));
        oMap.put("겨울 더블 코트", null);
        oMap.put("후드 집업", Arrays.asList("로고", "베이직"));
        oMap.put("환절기 코트", null);
        oMap.put("패딩 베스트", null);
        oMap.put("겨울 기타 코트", Arrays.asList("트윌", "시어링", "알파카", "벨티드", "발마칸", "오버핏"));
        oMap.put("무스탕/퍼", Arrays.asList("크롭", "오버핏", "스웨이드", "양털", "시어링", "부클"));
        oMap.put("기타 아우터", Arrays.asList("덕다운", "오버핏", "크롭", "경량", "야상", "퀄팅", "워시드", "스웨이드"));
        Hashtag.put("\nOuter: ", oMap);

        Map<String, Object> sMap = new HashMap<String, Object>();
        sMap.put("패션스니커즈화", Arrays.asList("블랙", "어글리슈즈", "화이트", "청키", "에어", "러닝화", "라이트"));
        sMap.put("스포츠 스니커즈", null);
        sMap.put("기타 스니커즈", null);
        sMap.put("캔버스/단화", null);
        sMap.put("샌들", Arrays.asList("서머슈즈", "플랫폼", "스포츠", "웨지힐", "벨크로", "뮬", "버클", "스트랩", "레더", "플립플랍"));
        sMap.put("로퍼", Arrays.asList("서머슈즈, 베이식, 테슬, 캐주얼, 슬립온, 블로퍼, 드라이빙, 스웨이드, 페니, 가죽"));
        //sMap.put("힐/펌프스",null);
        sMap.put("플랫 슈즈", Arrays.asList("메리제인", "서머슈즈", "뮬", "리본", "슬링백", "스퀘어토", "가죽"));
        sMap.put("블로퍼", null);
        sMap.put("슬리퍼", Arrays.asList("슬라이드", "서머슈즈", "쿠션", "레더", "로고", "벨크로"));
        sMap.put("부츠", null);
        sMap.put("구두", null);
        sMap.put("모카신/보트 슈즈", Arrays.asList("드라이빙슈즈", "스웨이드"));
        sMap.put("기타신발", Arrays.asList("아쿠아", "실내화"));
        Hashtag.put("\nShoes: ", sMap);
    }

    private static final List<String> excludeItems = Arrays.asList(
            "탱크탑", "레깅스", "미니스커트", "미디스커트", "크롭 오프숄더",
            "롱스커트", "힐/펌프스", "프릴", "뷔스티에",
            "크롭", "플랫 슈즈", "골지", "웨지힐", "크롭 카디건"
    );


    private static String setOrder(String gender) {
        String str = null;

        for(Map.Entry<String, Map<String, Object>> entry : Hashtag.entrySet()) {
            str = str + entry.getKey() + "\n";
            Map<String, Object> item = entry.getValue();
            List types = new ArrayList(item.keySet());
            Collections.shuffle(types);
            List<String> tuples = new ArrayList<>();
            for(Object o : types) {
                String temp = (String) o;
                List e = (List<String>) item.get(o);
                if (e != null) {
                    if (("male".equalsIgnoreCase(gender) || "unisex".equalsIgnoreCase(gender)) && excludeItems.contains(temp.toLowerCase())) {
                        continue;
                    }
                    List<String> includedDetails = new ArrayList<>();
                    for (Object detail : e) {
                        String detailStr = (String) detail;
                        if (("male".equalsIgnoreCase(gender) || "unisex".equalsIgnoreCase(gender)) && excludeItems.contains(detailStr.toLowerCase())) {
                            continue;
                        }
                        includedDetails.add(detailStr);
                    }
                    Collections.shuffle(includedDetails);
                    temp = temp + "(" + String.join(", ", includedDetails) + ")";
                }
                tuples.add(temp);
            }
            str = str + String.join(", ", tuples);
        }

        return str;
    }

    public static CompletionRequest defaultWith(String[] words, String style) {

        String styleText = style == null ? "" : style + " style";
        String randomOrder = setOrder(words[0]);


        String prompt = String.format(
                "The available options for itemtypes for each category are the following. I want you to parse through all options and choose the items that follow all instructions simultaneously.\n" +
                        "%s"+
                        "The response must be a list separated by '#', with no additional notes. " +
                        "\nThe response format is the following.\n\n" +
                        "Top:#[Top_Item1]#[Top_Item2]#[Top_Item3]\n" +
                        "Bottom:#[Bottom_Item1]#[Bottom_Item2]#[Bottom_Item3]\n" +
                        "Outer:#[Outer_Item1]#[Outer_Item2]#[Outer_Item3]\n" +
                        "Shoes:#[Shoes_Item1]#[Shoes_Item2]#[Shoes_Item3]\n\n " +
                        "Choose the 3 itemtypes from each categories to generate a recommendation for %s %s, " +
                        "that is wearable on %s with probability of precipitation %s%%, %s weather, lowest temperature %s degree celsius and highest temperature %s degree celsius. " +
                        "(If the chosen itemtype has an additional keyword in the brackets behind the itemtype, it is optional to choose one of them with the itemtype.)" +
                        " In the response, the additional keyword should be placed behind each itemtype inside the brackets. " +
                        "Just give me the list of items without the additional notes or apologies.",

                randomOrder, styleText, words[0], words[1], words[2], words[3], words[4], words[5]);
        System.out.println(prompt);
        return new CompletionRequest("text-davinci-003", prompt, 0.7, 500);
    }
}
