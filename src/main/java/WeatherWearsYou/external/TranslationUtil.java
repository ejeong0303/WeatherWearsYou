package WeatherWearsYou.external;

import java.util.Map;
import java.util.AbstractMap.SimpleImmutableEntry;

public class TranslationUtil {

    private static final Map<String, String> ENG_TO_KOR = Map.ofEntries(
            new SimpleImmutableEntry<>("shirts/blouse(cropped)", "셔츠/블라우스(크롭)"),
            new SimpleImmutableEntry<>("shirts/blouse(overfit)", "셔츠/블라우스(오버핏)"),
            new SimpleImmutableEntry<>("shirts/blouse(oxford)", "셔츠/블라우스(옥스포드)"),
            new SimpleImmutableEntry<>("shirts/blouse(linen)", "셔츠/블라우스(린넨)"),
            new SimpleImmutableEntry<>("shirts/blouse(embroidered)", "셔츠/블라우스(자수)"),
            new SimpleImmutableEntry<>("shirts/blouse(ruffle)", "셔츠/블라우스(프릴)"),
            new SimpleImmutableEntry<>("shirts/blouse", "셔츠/블라우스"),


            new SimpleImmutableEntry<>("long-sleeve t-shirts(cropped)", "긴소매 티셔츠(크롭)"),
            new SimpleImmutableEntry<>("long-sleeve t-shirts(overfit)", "긴소매 티셔츠(오버핏)"),
            new SimpleImmutableEntry<>("long-sleeve t-shirts(raglan)", "긴소매 티셔츠(래글런)"),
            new SimpleImmutableEntry<>("long-sleeve t-shirts(tencel)", "긴소매 티셔츠(텐셀)"),
            new SimpleImmutableEntry<>("long-sleeve t-shirts(relaxed)", "긴소매 티셔츠(릴렉스드)"),
            new SimpleImmutableEntry<>("long-sleeve t-shirts", "긴소매 티셔츠"),

            new SimpleImmutableEntry<>("knit/sweater(half zip-up knit)", "니트/스웨터(하프집업니트)"),
            new SimpleImmutableEntry<>("knit/sweater(collar knit)", "니트/스웨터(카라니트)"),
            new SimpleImmutableEntry<>("knit/sweater(knit vest)", "니트/스웨터(니트베스트)"),
            new SimpleImmutableEntry<>("knit/sweater(cropped knit)", "니트/스웨터(크롭니트)"),
            new SimpleImmutableEntry<>("knit/sweater(cropped off-shoulder)", "니트/스웨터(크롭 오프숄더)"),
            new SimpleImmutableEntry<>("knit/sweater(linen)", "니트/스웨터(린넨)"),
            new SimpleImmutableEntry<>("knit/sweater(boucle)", "니트/스웨터(부클)"),
            new SimpleImmutableEntry<>("knit/sweater(pullover)", "니트/스웨터(풀오버)"),
            new SimpleImmutableEntry<>("knit/sweater(slit)", "니트/스웨터(슬릿)"),
            new SimpleImmutableEntry<>("knit/sweater", "니트/스웨터"),

            new SimpleImmutableEntry<>("short-sleeve t-shirt(graphic)", "반소매 티셔츠(그래픽)"),
            new SimpleImmutableEntry<>("short-sleeve t-shirt(logo/lettering)", "반소매 티셔츠(로고/레터링)"),
            new SimpleImmutableEntry<>("short-sleeve t-shirt(plain)", "반소매 티셔츠(무지)"),
            new SimpleImmutableEntry<>("short-sleeve t-shirt(cropped)", "반소매 티셔츠(크롭)"),
            new SimpleImmutableEntry<>("short-sleeve t-shirt(overfit)", "반소매 티셔츠(오버핏)"),
            new SimpleImmutableEntry<>("short-sleeve t-shirt(embroidery)", "반소매 티셔츠(자수)"),
            new SimpleImmutableEntry<>("short-sleeve t-shirt", "반소매 티셔츠"),

            new SimpleImmutableEntry<>("sweatshirt(cropped)", "맨투맨/스웨트셔츠(크롭)"),
            new SimpleImmutableEntry<>("sweatshirt(overfit)", "맨투맨/스웨트셔츠(오버핏)"),
            new SimpleImmutableEntry<>("sweatshirt(plain)", "맨투맨/스웨트셔츠(무지)"),
            new SimpleImmutableEntry<>("sweatshirt(setup)", "맨투맨/스웨트셔츠(셋업)"),
            new SimpleImmutableEntry<>("sweatshirt(logo)", "맨투맨/스웨트셔츠(로고)"),
            new SimpleImmutableEntry<>("sweatshirt(half zip-up)", "맨투맨/스웨트셔츠(하프집업)"),
            new SimpleImmutableEntry<>("sweatshirt(basic)", "맨투맨/스웨트셔츠(베이식)"),
            new SimpleImmutableEntry<>("sweatshirt(graphic)", "맨투맨/스웨트셔츠(그래픽)"),
            new SimpleImmutableEntry<>("sweatshirt", "맨투맨/스웨트셔츠"),

            new SimpleImmutableEntry<>("sleeveless(layered)", "민소매 티셔츠(레이어드)"),
            new SimpleImmutableEntry<>("sleeveless(cropped)", "민소매 티셔츠(크롭)"),
            new SimpleImmutableEntry<>("sleeveless(tank top)", "민소매 티셔츠(탱크탑)"),
            new SimpleImmutableEntry<>("sleeveless(bustier)", "민소매 티셔츠(뷔스티에)"),
            new SimpleImmutableEntry<>("sleeveless(ribbed)", "민소매 티셔츠(골지)"),
            new SimpleImmutableEntry<>("sleeveless", "민소매 티셔츠"),

            new SimpleImmutableEntry<>("other tops(cropped)", "기타상의(크롭)"),
            new SimpleImmutableEntry<>("other tops(half zip-up)", "기타상의(하프집업)"),
            new SimpleImmutableEntry<>("other tops(tank top)", "기타상의(탱크탑)"),
            new SimpleImmutableEntry<>("other tops(overfit)", "기타상의(오버핏)"),
            new SimpleImmutableEntry<>("other tops(embroidery)", "기타상의(자수)"),
            new SimpleImmutableEntry<>("other tops", "기타상의"),

            new SimpleImmutableEntry<>("pique/collar t-shirt", "피케/카라 티셔츠"),

            new SimpleImmutableEntry<>("hooded t-shirt(logo)", "후드 티셔츠(로고)"),
            new SimpleImmutableEntry<>("hooded t-shirt(graphic)", "후드 티셔츠(그래픽)"),
            new SimpleImmutableEntry<>("hooded t-shirt(basic)", "후드 티셔츠(베이식)"),
            new SimpleImmutableEntry<>("hooded t-shirt(half zip-up)", "후드 티셔츠(하프집업)"),
            new SimpleImmutableEntry<>("hooded t-shirt(cropped)", "후드 티셔츠(크롭)"),
            new SimpleImmutableEntry<>("hooded t-shirt", "후드 티셔츠"),

            new SimpleImmutableEntry<>("sports top", "스포츠상의"),

            new SimpleImmutableEntry<>("training/jogger pants(wide pants)", "트레이닝/조거 팬츠(와이드팬츠)"),
            new SimpleImmutableEntry<>("training/jogger pants(wide fit)", "트레이닝/조거 팬츠(와이드핏)"),
            new SimpleImmutableEntry<>("training/jogger pants(overfit)", "트레이닝/조거 팬츠(오버핏)"),
            new SimpleImmutableEntry<>("training/jogger pants(banding)", "트레이닝/조거 팬츠(밴딩)"),
            new SimpleImmutableEntry<>("training/jogger pants(training shorts)", "트레이닝/조거 팬츠(트레이닝쇼츠)"),
            new SimpleImmutableEntry<>("training/jogger pants", "트레이닝/조거 팬츠"),

            new SimpleImmutableEntry<>("short pants(nylon shorts)", "숏팬츠(나일론 쇼츠)"),
            new SimpleImmutableEntry<>("short pants(banding)", "숏팬츠(밴딩)"),
            new SimpleImmutableEntry<>("short pants(overfit)", "숏팬츠(오버핏)"),
            new SimpleImmutableEntry<>("short pants(pintuck)", "숏팬츠(핀턱)"),
            new SimpleImmutableEntry<>("short pants(chino)", "숏팬츠(치노)"),
            new SimpleImmutableEntry<>("short pants(seersucker)", "숏팬츠(시어서커)"),
            new SimpleImmutableEntry<>("short pants(linen)", "숏팬츠(린넨)"),
            new SimpleImmutableEntry<>("short pants(one tuck)", "숏팬츠(원턱)"),
            new SimpleImmutableEntry<>("short pants(two tuck)", "숏팬츠(투턱)"),
            new SimpleImmutableEntry<>("short pants", "숏팬츠"),

            new SimpleImmutableEntry<>("sports bottoms", "스포츠하의"),

            new SimpleImmutableEntry<>("denim pants(wide fit)", "데님 팬츠(와이드핏)"),
            new SimpleImmutableEntry<>("denim pants(boot cut)", "데님 팬츠(부츠컷)"),
            new SimpleImmutableEntry<>("denim pants(tapered)", "데님 팬츠(테이퍼드)"),
            new SimpleImmutableEntry<>("denim pants(light blue)", "데님 팬츠(연청)"),
            new SimpleImmutableEntry<>("denim pants(dark blue)", "데님 팬츠(진청)"),
            new SimpleImmutableEntry<>("denim pants(black blue)", "데님 팬츠(흑청)"),
            new SimpleImmutableEntry<>("denim pants(raw)", "데님 팬츠(생지)"),
            new SimpleImmutableEntry<>("denim pants", "데님 팬츠"),

            new SimpleImmutableEntry<>("cotton pants(wide fit)", "코튼 팬츠(와이드핏)"),
            new SimpleImmutableEntry<>("cotton pants(tapered)", "코튼 팬츠(테이퍼드)"),
            new SimpleImmutableEntry<>("cotton pants(chino)", "코튼 팬츠(치노)"),
            new SimpleImmutableEntry<>("cotton pants(one tuck)", "코튼 팬츠(원턱)"),
            new SimpleImmutableEntry<>("cotton pants(banding)", "코튼 팬츠(밴딩)"),
            new SimpleImmutableEntry<>("cotton pants(linen)", "코튼 팬츠(린넨)"),
            new SimpleImmutableEntry<>("cotton pants(overfit)", "코튼 팬츠(오버핏)"),
            new SimpleImmutableEntry<>("cotton pants", "코튼 팬츠"),

            new SimpleImmutableEntry<>("suit pants/slacks(wide pants)", "슈트 팬츠/슬랙스(와이드팬츠)"),

            new SimpleImmutableEntry<>("other pants(linen)", "기타 바지(린넨)"),
            new SimpleImmutableEntry<>("other pants(bootcut)", "기타 바지(부츠컷)"),
            new SimpleImmutableEntry<>("other pants(banding)", "기타 바지(밴딩)"),
            new SimpleImmutableEntry<>("other pants(one tuck)", "기타 바지(원턱)"),
            new SimpleImmutableEntry<>("other pants(tapered)", "기타 바지(테이퍼드)"),
            new SimpleImmutableEntry<>("other pants(over fit)", "기타 바지(오버핏)"),
            new SimpleImmutableEntry<>("other pants(layered)", "기타 바지(레이어드)"),
            new SimpleImmutableEntry<>("other pants(wide fit)", "기타 바지(와이드핏)"),
            new SimpleImmutableEntry<>("other pants", "기타 바지"),

            new SimpleImmutableEntry<>("jumpsuits/overalls(overfit)", "점프 슈트/오버올(오버핏)"),
            new SimpleImmutableEntry<>("jumpsuits/overalls(linen)", "점프 슈트/오버올(린넨)"),
            new SimpleImmutableEntry<>("jumpsuits/overalls(tapered)", "점프 슈트/오버올(테이퍼드)"),
            new SimpleImmutableEntry<>("jumpsuits/overalls(belted)", "점프 슈트/오버올(벨티드)"),
            new SimpleImmutableEntry<>("jumpsuits/overalls(cutting cropped)", "점프 슈트/오버올(커팅크롭)"),
            new SimpleImmutableEntry<>("jumpsuits/overalls(twill)", "점프 슈트/오버올(트윌)"),
            new SimpleImmutableEntry<>("jumpsuits/overalls(high waist)", "점프 슈트/오버올(하이웨이스트)"),
            new SimpleImmutableEntry<>("jumpsuits/overalls(suspenders)", "점프 슈트/오버올(멜빵)"),
            new SimpleImmutableEntry<>("jumpsuits/overalls", "점프 슈트/오버올"),

            new SimpleImmutableEntry<>("leggings(yoga clothes)", "레깅스(요가복)"),
            new SimpleImmutableEntry<>("leggings(bootcut)", "레깅스(부츠컷)"),
            new SimpleImmutableEntry<>("leggings(high waist)", "레깅스(하이웨이스트)"),
            new SimpleImmutableEntry<>("leggings(slit)", "레깅스(슬릿)"),
            new SimpleImmutableEntry<>("leggings(sportswear)", "레깅스(운동복)"),
            new SimpleImmutableEntry<>("leggings(tights)", "레깅스(타이즈)"),
            new SimpleImmutableEntry<>("leggings(pilates)", "레깅스(필라테스)"),
            new SimpleImmutableEntry<>("leggings", "레깅스"),

            new SimpleImmutableEntry<>("miniskirts(denim skirt)", "미니스커트(청치마)"),
            new SimpleImmutableEntry<>("miniskirts(raw denim)", "미니스커트(생지)"),
            new SimpleImmutableEntry<>("miniskirts(banding)", "미니스커트(밴딩)"),
            new SimpleImmutableEntry<>("miniskirts(two-piece)", "미니스커트(투피스)"),
            new SimpleImmutableEntry<>("miniskirts(buckle)", "미니스커트(버클)"),
            new SimpleImmutableEntry<>("miniskirts(layered)", "미니스커트(레이어드)"),
            new SimpleImmutableEntry<>("miniskirts(slit)", "미니스커트(슬릿)"),
            new SimpleImmutableEntry<>("miniskirts", "미니스커트"),

            new SimpleImmutableEntry<>("midi skirt(slit)", "미디스커트(슬릿)"),
            new SimpleImmutableEntry<>("midi skirt(banding)", "미디스커트(밴딩)"),
            new SimpleImmutableEntry<>("midi skirt(layered)", "미디스커트(레이어드)"),
            new SimpleImmutableEntry<>("midi skirt(denim skirt)", "미디스커트(청치마)"),
            new SimpleImmutableEntry<>("midi skirt(belted)", "미디스커트(벨티드)"),
            new SimpleImmutableEntry<>("midi skirt", "미디스커트"),

            new SimpleImmutableEntry<>("long skirt(denim skirt)", "롱스커트(청치마)"),
            new SimpleImmutableEntry<>("long skirt(banding)", "롱스커트(밴딩)"),
            new SimpleImmutableEntry<>("long skirt(slit)", "롱스커트(슬릿)"),
            new SimpleImmutableEntry<>("long skirt(pintuck)", "롱스커트(핀턱)"),
            new SimpleImmutableEntry<>("long skirt(ruffle)", "롱스커트(프릴)"),
            new SimpleImmutableEntry<>("long skirt", "롱스커트"),

            new SimpleImmutableEntry<>("nylon/coach jacket", "나일론/코치 재킷"),

            new SimpleImmutableEntry<>("stadium jacket", "스타디움 재킷"),

            new SimpleImmutableEntry<>("safari/hunting jacket", "사파리/헌팅 재킷"),

            new SimpleImmutableEntry<>("blouson/MA-1", "블루종/MA-1"),

            new SimpleImmutableEntry<>("anorak jacket", "아노락 재킷"),

            new SimpleImmutableEntry<>("other outerwear(duck down)", "기타 아우터(덕다운)"),
            new SimpleImmutableEntry<>("other outerwear(overfit)", "기타 아우터(오버핏)"),
            new SimpleImmutableEntry<>("other outerwear(cropped)", "기타 아우터(크롭)"),
            new SimpleImmutableEntry<>("other outerwear(lightweight)", "기타 아우터(경량)"),
            new SimpleImmutableEntry<>("other outerwear(field)", "기타 아우터(야상)"),
            new SimpleImmutableEntry<>("other outerwear(quilting)", "기타 아우터(퀼팅)"),
            new SimpleImmutableEntry<>("other outerwear(washed)", "기타 아우터(워시드)"),
            new SimpleImmutableEntry<>("other outerwear(suede)", "기타 아우터(스웨이드)"),
            new SimpleImmutableEntry<>("other outerwear", "기타 아우터"),

            new SimpleImmutableEntry<>("trucker jacket", "트러커 재킷"),

            new SimpleImmutableEntry<>("training jacket", "트레이닝 재킷"),

            new SimpleImmutableEntry<>("suit/blazer jacket", "슈트/블레이저 재킷"),

            new SimpleImmutableEntry<>("leather/riders jacket", "레더/라이더스 재킷"),

            new SimpleImmutableEntry<>("long padded/long heavy outerwear", "롱패딩/롱헤비 아우터"),

            new SimpleImmutableEntry<>("cardigan(basic cardigan)", "카디건(베이직카디건)"),
            new SimpleImmutableEntry<>("cardigan(cropped cardigan)", "카디건(크롭카디건)"),
            new SimpleImmutableEntry<>("cardigan(bolero)", "카디건(볼레로)"),
            new SimpleImmutableEntry<>("cardigan(boucle)", "카디건(부클)"),
            new SimpleImmutableEntry<>("cardigan(cropped)", "카디건(크롭)"),
            new SimpleImmutableEntry<>("cardigan(overfit)", "카디건(오버핏)"),
            new SimpleImmutableEntry<>("cardigan(ribbed)", "카디건(골지)"),
            new SimpleImmutableEntry<>("cardigan(embroidered)", "카디건(자수)"),
            new SimpleImmutableEntry<>("cardigan(argyle)", "카디건(아가일)"),
            new SimpleImmutableEntry<>("cardigan", "카디건"),

            new SimpleImmutableEntry<>("fleece/poogle jumper", "플리스/뽀글이"),

            new SimpleImmutableEntry<>("winter single coat", "겨울 싱글 코트"),

            new SimpleImmutableEntry<>("short padding/short heavy outerwear(flight)", "숏패딩/숏헤비 아우터(플라이트)"),
            new SimpleImmutableEntry<>("short padding/short heavy outerwear(lightweight)", "숏패딩/숏헤비 아우터(경량)"),
            new SimpleImmutableEntry<>("short padding/short heavy outerwear(duck down)", "숏패딩/숏헤비 아우터(덕다운)"),
            new SimpleImmutableEntry<>("short padding/short heavy outerwear(quilting)", "숏패딩/숏헤비 아우터(퀼팅)"),
            new SimpleImmutableEntry<>("short padding/short heavy outerwear(overfit)", "숏패딩/숏헤비 아우터(오버핏)"),
            new SimpleImmutableEntry<>("short padding/short heavy outerwear(crop)", "숏패딩/숏헤비 아우터(크롭)"),
            new SimpleImmutableEntry<>("short padding/short heavy outerwear(lambswool)", "숏패딩/숏헤비 아우터(양털)"),
            new SimpleImmutableEntry<>("short padding/short heavy outerwear", "숏패딩/숏헤비 아우터"),

            new SimpleImmutableEntry<>("vest(boucle)", "베스트(부클)"),
            new SimpleImmutableEntry<>("vest(crop)", "베스트(크롭)"),
            new SimpleImmutableEntry<>("vest(bustier)", "베스트(뷔스티에)"),
            new SimpleImmutableEntry<>("vest(argyle)", "베스트(아가일)"),
            new SimpleImmutableEntry<>("vest(crochet)", "베스트(크로셰)"),
            new SimpleImmutableEntry<>("vest(layered)", "베스트(레이어드)"),
            new SimpleImmutableEntry<>("vest(overfit)", "베스트(오버핏)"),
            new SimpleImmutableEntry<>("vest(jacquard)", "베스트(자카드)"),
            new SimpleImmutableEntry<>("vest(embroidery)", "베스트(자수)"),
            new SimpleImmutableEntry<>("vest(lightweight)", "베스트(경량)"),
            new SimpleImmutableEntry<>("vest", "베스트"),

            new SimpleImmutableEntry<>("winter double coat", "겨울 더블 코트"),

            new SimpleImmutableEntry<>("hooded zip-up(logo)", "후드 집업(로고)"),
            new SimpleImmutableEntry<>("hooded zip-up(basic)", "후드 집업(베이직)"),
            new SimpleImmutableEntry<>("hooded zip-up", "후드 집업"),

            new SimpleImmutableEntry<>("spring/autumn coat", "환절기 코트"),

            new SimpleImmutableEntry<>("padded vest", "패딩 베스트"),

            new SimpleImmutableEntry<>("other winter coat(twill)", "겨울 기타 코트(트윌)"),
            new SimpleImmutableEntry<>("other winter coat(shearling)", "겨울 기타 코트(시어링)"),
            new SimpleImmutableEntry<>("other winter coat(alpaca)", "겨울 기타 코트(알파카)"),
            new SimpleImmutableEntry<>("other winter coat(belted)", "겨울 기타 코트(벨티드)"),
            new SimpleImmutableEntry<>("other winter coat(balmacan)", "겨울 기타 코트(발마칸)"),
            new SimpleImmutableEntry<>("other winter coat(overfit)", "겨울 기타 코트(오버핏)"),
            new SimpleImmutableEntry<>("other winter coat", "겨울 기타 코트"),

            new SimpleImmutableEntry<>("mustang/fur(cropped)", "무스탕/퍼(크롭)"),
            new SimpleImmutableEntry<>("mustang/fur(overfit)", "무스탕/퍼(오버핏)"),
            new SimpleImmutableEntry<>("mustang/fur(suede)", "무스탕/퍼(스웨이드)"),
            new SimpleImmutableEntry<>("mustang/fur(wool)", "무스탕/퍼(양털)"),
            new SimpleImmutableEntry<>("mustang/fur(shearling)", "무스탕/퍼(시어링)"),
            new SimpleImmutableEntry<>("mustang/fur(boucle)", "무스탕/퍼(부클)"),
            new SimpleImmutableEntry<>("mustang/fur", "무스탕/퍼"),

            new SimpleImmutableEntry<>("fashion sneakers(black)", "패션스니커즈화(블랙)"),
            new SimpleImmutableEntry<>("fashion sneakers(ugly shoes)", "패션스니커즈화(어글리슈즈)"),
            new SimpleImmutableEntry<>("fashion sneakers(white)", "패션스니커즈화(화이트)"),
            new SimpleImmutableEntry<>("fashion sneakers(chunky)", "패션스니커즈화(청키)"),
            new SimpleImmutableEntry<>("fashion sneakers(air)", "패션스니커즈화(에어)"),
            new SimpleImmutableEntry<>("fashion sneakers(running shoes)", "패션스니커즈화(러닝화)"),
            new SimpleImmutableEntry<>("fashion sneakers(light)", "패션스니커즈화(라이트)"),
            new SimpleImmutableEntry<>("fashion sneakers", "패션스니커즈화"),

            new SimpleImmutableEntry<>("sports sneakers", "스포츠 스니커즈"),

            new SimpleImmutableEntry<>("other sneakers", "기타 스니커즈"),

            new SimpleImmutableEntry<>("canvas shoes/soles", "캔버스/단화"),

            new SimpleImmutableEntry<>("sandals(summer shoes)", "샌들(서머슈즈)"),
            new SimpleImmutableEntry<>("sandals(platform)", "샌들(플랫폼)"),
            new SimpleImmutableEntry<>("sandals(sport)", "샌들(스포츠)"),
            new SimpleImmutableEntry<>("sandals(wedge heel)", "샌들(웨지힐)"),
            new SimpleImmutableEntry<>("sandals(velcro)", "샌들(벨크로)"),
            new SimpleImmutableEntry<>("sandals(mule)", "샌들(뮬)"),
            new SimpleImmutableEntry<>("sandals(buckle)", "샌들(버클)"),
            new SimpleImmutableEntry<>("sandals(strap)", "샌들(스트랩)"),
            new SimpleImmutableEntry<>("sandals(leather)", "샌들(레더)"),
            new SimpleImmutableEntry<>("sandals(flip flop)", "샌들(플립플랍)"),
            new SimpleImmutableEntry<>("sandals", "샌들"),

            new SimpleImmutableEntry<>("loafers(summer shoes)", "로퍼(서머슈즈)"),
            new SimpleImmutableEntry<>("loafers(basic)", "로퍼(베이식)"),
            new SimpleImmutableEntry<>("loafers(tassel)", "로퍼(테슬)"),
            new SimpleImmutableEntry<>("loafers(casual)", "로퍼(캐주얼)"),
            new SimpleImmutableEntry<>("loafers(slip-on)", "로퍼(슬립온)"),
            new SimpleImmutableEntry<>("loafers(bloafer)", "로퍼(블로퍼)"),
            new SimpleImmutableEntry<>("loafers(driving)", "로퍼(드라이빙)"),
            new SimpleImmutableEntry<>("loafers(suede)", "로퍼(스웨이드)"),
            new SimpleImmutableEntry<>("loafers(penny)", "로퍼(페니)"),
            new SimpleImmutableEntry<>("loafers(leather)", "로퍼(가죽)"),
            new SimpleImmutableEntry<>("loafers", "로퍼"),

            new SimpleImmutableEntry<>("heels/pumps", "힐/펌프스"),

            new SimpleImmutableEntry<>("flat shoes(mary jane)", "플랫 슈즈(메리제인)"),
            new SimpleImmutableEntry<>("flat shoes(summer shoes)", "플랫 슈즈(서머슈즈)"),
            new SimpleImmutableEntry<>("flat shoes(mule)", "플랫 슈즈(뮬)"),
            new SimpleImmutableEntry<>("flat shoes(ribbon)", "플랫 슈즈(리본)"),
            new SimpleImmutableEntry<>("flat shoes(slingback)", "플랫 슈즈(슬링백)"),
            new SimpleImmutableEntry<>("flat shoes(square toe)", "플랫 슈즈(스퀘어토)"),
            new SimpleImmutableEntry<>("flat shoes(leather)", "플랫 슈즈(가죽)"),
            new SimpleImmutableEntry<>("flat shoes", "플랫 슈즈"),

            new SimpleImmutableEntry<>("bloafers", "블로퍼"),

            new SimpleImmutableEntry<>("slippers(slides)", "슬리퍼(슬라이드)"),
            new SimpleImmutableEntry<>("slippers(summer shoes)", "슬리퍼(서머슈즈)"),
            new SimpleImmutableEntry<>("slippers(cushion)", "슬리퍼(쿠션)"),
            new SimpleImmutableEntry<>("slippers(leather)", "슬리퍼(레더)"),
            new SimpleImmutableEntry<>("slippers(logo)", "슬리퍼(로고)"),
            new SimpleImmutableEntry<>("slippers(velcro)", "슬리퍼(벨크로)"),
            new SimpleImmutableEntry<>("slippers", "슬리퍼"),

            new SimpleImmutableEntry<>("boots", "부츠"),

            new SimpleImmutableEntry<>("dress shoes", "구두"),

            new SimpleImmutableEntry<>("moccasins/boat shoes(driving shoes)", "모카신/보트 슈즈(드라이빙슈즈)"),
            new SimpleImmutableEntry<>("moccasins/boat shoes(suede)", "모카신/보트 슈즈(스웨이드)"),

            new SimpleImmutableEntry<>("other shoes(aqua)", "기타신발(아쿠아)"),
            new SimpleImmutableEntry<>("other shoes(indoor)", "기타신발(실내화)")

    );

    public static String translateEngToKor(String englishTerm) {
        return ENG_TO_KOR.getOrDefault(englishTerm, englishTerm);
    }
}

