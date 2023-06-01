package WeatherWearsYou.external;

public record CompletionRequest(String model, String prompt, double temperature, int max_tokens) {

    public static CompletionRequest defaultWith(String[] words, String style) {

        String styleText = style == null ? "" : " " + style + " style";
        /*String prompt = String.format("Recommend me the trendy south korean style clothing that best fits the weather%s. Give me a word(don't describe with adjectives or colors) for each category " +
                        "(outer, top, bottom, and shoes with 3 examples each) in a comma-separated list for a " +
                        "%s in Seoul on %s with probability of precipitation %s%% , %s weather, lowest temperature %s degree celsius and highest temperature %s degree celsius. " +
                        "Give me only the list, don't tell me additional notes. Also provide each outer, top, bottom, and shoes in separate lines.",
                // Please provide responses except outer, top, bottom, and shoes in korean
                styleText, words[0], words[1], words[2], words[3], words[4], words[5]);*/
        String prompt = String.format("The available options for itemtypes for each category are the following. I want you to parse through all options and choose the items that best fits the instructions.\n"+
                                "Top\n"+
                                "ItemTypes: 셔츠/블라우스(크롭, 오버핏, 옥스포드, 린넨, 자수, 프릴), 민소매 티셔츠(레이어드, 크롭, 탱크탑, 뷔스티에, 골지), 긴소매 티셔츠(크롭, 오버핏, 래글런, 텐셀, 릴렉스드), 니트/스웨터(하프집업니트, 카라니트, 니트베스트, 크롭니트, 크롭 오프숄더, 린넨, 부클, 풀오버, 슬릿), 반소매 티셔츠(그래픽, 로고/레터링, 무지, 크롭, 오버핏, 자수), 맨투맨/스웨트셔츠(크롭, 오버핏, 무지, 셋업, 로고, 하프집업, 베이식, 그래픽),  기타상의(크롭, 하프집업, 탱크탑, 오버핏, 자수), 피케/카라 티셔츠, 후드 티셔츠(로고, 그래픽, 베이식, 하프집업, 크롭), 스포츠상의\n"+
                                "Bottom\n"+
                                "ItemTypes: 트레이닝/조거 팬츠(와이드팬츠, 와이드핏, 오버핏, 밴딩, 트레이닝쇼츠), 숏팬츠(나일론 쇼츠, 밴딩, 오버핏, 핀턱, 치노, 시어서커, 린넨, 원턱, 투턱), 스포츠하의, 데님 팬츠(와이드핏, 부츠컷, 테이퍼드, 연청, 진청, 흑청, 생지), 코튼 팬츠(와이드핏, 테이퍼드, 치노, 원턱, 밴딩, 린넨, 오버핏), 숏팬츠(나일론쇼츠, 핀턱, 밴딩, 오버핏, 투턱, 치노, 린넨, 원턱, 시어서커), 슈트 팬츠/슬랙스(와이드팬츠), 기타 바지(린넨, 부츠컷, 밴딩, 원턱, 테이퍼드, 오버핏, 레이어드, 와이드핏), 스포츠하의, 점프 슈트/오버올(오버핏, 린넨, 테이퍼드, 벨티드, 커팅크롭, 트윌, 하이웨이스트, 멜빵), 레깅스(요가복, 부츠컷, 하이웨이스트, 슬릿, 운동복, 타이즈, 필라테스), 미니스커트(청치마, 생지, 밴딩, 투피스, 버클, 레이어드, 슬릿), 미디스커트(슬릿, 밴딩, 레이어드, 청치마, 벨티드), 롱스커트(청치마, 밴딩, 슬릿, 핀턱, 프릴)\n" +
                                "Outer\n"+
                                "ItemTypes: 나일론/코치 재킷, 스타디움 재킷, 사파리/헌팅 재킷, 블루종/MA-1, 아노락 재킷, 기타 아우터(덕다운, 오버핏, 크롭, 경량, 야상, 퀄팅, 워시드, 스웨이드), 트러커 재킷, 트레이닝 재킷, 슈트/블레이저 재킷, 레더/라이더스 재킷, 롱패딩/롱헤비 아우터, 카디건(베이직카디건, 크롭카디건, 볼레로, 부클, 크롭, 오버핏, 골지, 자수, 아가일), 플리스/뽀글이, 겨울 싱글 코트, 숏패딩/숏헤비 아우터(플라이트, 경량, 덕다운, 퀼팅, 오버핏, 크롭, 양털),  베스트(부클, 크롭, 뷔스티에, 아가일, 크로셰, 레이어드, 오버핏, 자카드, 자수, 경량), 겨울 더블 코트, 후드 집업(로고, 베이직), 환절기 코트, 패딩 베스트, 겨울 기타 코트(트윌, 시어링, 알파카, 벨티드, 발마칸, 오버핏), 무스탕/퍼(크롭, 오버핏, 스웨이드, 양털, 시어링, 부클)\n"+
                                "Shoes\n"+
                                "ItemTypes: 패션스니커즈화(블랙, 어글리슈즈, 화이트, 청키, 에어, 러닝화, 라이트), 스포츠 스니커즈,  기타 스니커즈, 캔버스/단화, 샌들(서머슈즈, 플랫폼, 스포츠, 웨지힐, 벨크로, 뮬, 버클, 스트랩, 레더, 플립플랍), 로퍼(서머슈즈, 베이식, 테슬, 캐주얼, 슬립온, 블로퍼, 드라이빙, 스웨이드, 페니, 가죽), 힐/펌프스, 플랫 슈즈(메리제인, 서머슈즈, 뮬, 리본, 슬링백, 스퀘어토, 가죽), 블로퍼, 슬리퍼(슬라이드, 서머슈즈, 쿠션, 레더, 로고, 벨크로), 부츠, 구두, 모카신/보트 슈즈(드라이빙슈즈, 스웨이드), 기타신발(아쿠아, 실내화)\n"+
                                "The response must be a list seperated by '#', with no additional notes. " +
                                "The response format is the following.\n" +
                                "Top:#[Top_Item1]#[Top_Item2]#[Top_Item3]\n" +
                                "Bottom:#[Bottom_Item1]#[Bottom_Item2]#[Bottom_Item3]\n" +
                                "Outer:#[Outer_Item1]#[Outer_Item2]#[Outer_Item3]\n" +
                                "Shoes:#[Shoes_Item1]#[Shoes_Item2]#[Shoes_Item3]\n Choose the 3 itemtypes from each categories to generate a recommendation for %s, " +
                        "that best fits %s in the weather on %s with probability of precipitation %s%%, %s weather, lowest temperature %s degree celsius and highest temperature %s degree celsius. " +
                        "(If the chosen itemtype has an additional keyword in the brackets behind the itemtype, it is optional to choose one of them with the itemtype.)" +
                        " In the response, the additional keyword should be placed behind each itemtype with brackets. " +
                        "Just give me the list of items without the additional notes or apologies.",

                words[0], styleText,  words[1], words[2], words[3], words[4], words[5]);
        return new CompletionRequest("text-davinci-003", prompt, 0.7, 500);
    }
}
