package WeatherWearsYou.external;

import java.util.*;

public record CompletionRequest(String model, String prompt, double temperature, int max_tokens) {

    private static final LinkedHashMap<String, Map<String, Object>> Hashtag = new LinkedHashMap<>();
    static {
        Map<String, Object> tMap = new HashMap<String, Object>();
        tMap.put("shirts/blouse", Arrays.asList("cropped", "overfit", "oxford", "linen", "embroidered", "ruffle"));
        tMap.put("long-sleeve T-shirts", Arrays.asList("cropped", "overfit", "raglan", "tencel", "relaxed"));
        tMap.put("knit/sweater", Arrays.asList("half zip-up knit", "collar knit", "knit vest", "cropped knit", "cropped off-shoulder", "linen", "boucle", "pullover", "slit"));
        tMap.put("short-sleeve T-shirt", Arrays.asList("graphic", "logo/lettering", "plain", "cropped", "overfit", "embroidery"));
        tMap.put("sweatshirt", Arrays.asList("cropped", "overfit", "plain", "setup", "logo", "half zip-up", "basic", "graphic"));
        tMap.put("sleeveless", Arrays.asList("layered", "cropped", "tank top", "bustier", "ribbed"));
        tMap.put("hooded T-shirt", Arrays.asList("logo", "graphic", "basic", "half zip-up", "cropped"));
        tMap.put("pique/collar T-shirt", null);
        tMap.put("sports top", null);
        tMap.put("other tops", Arrays.asList("cropped", "half zip-up", "tank top", "overfit", "embroidery"));
        Hashtag.put("Top", tMap);

        Map<String, Object> bMap = new HashMap<String, Object>();
        bMap.put("training/jogger pants", Arrays.asList("wide pants", "wide fit", "overfit", "banding", "training shorts"));
        bMap.put("short pants", Arrays.asList("nylon shorts", "banding", "overfit", "pintuck", "chino", "seersucker", "linen", "one tuck", "two tuck"));
        bMap.put("denim pants", Arrays.asList("wide fit", "boot cut", "tapered", "light blue", "dark blue", "black blue", "raw"));
        bMap.put("cotton pants", Arrays.asList("wide fit", "tapered", "chino", "one tuck", "banding", "linen", "overfit"));
        bMap.put("suit pants/slacks", Arrays.asList("wide pants"));
        bMap.put("sports bottoms", null);
        bMap.put("jumpsuits/overalls", Arrays.asList("overfit", "linen", "tapered", "belted", "cutting cropped", "twill", "high waist", "suspenders"));
        bMap.put("leggings", Arrays.asList("yoga clothes", "bootcut", "high waist", "slit", "sportswear", "tights", "pilates"));
        bMap.put("miniskirts", Arrays.asList("denim skirt", "raw denim", "banding", "two-piece", "buckle", "layered", "slit"));
        bMap.put("midi skirt", Arrays.asList("slit", "banding", "layered", "denim skirt", "belted"));
        bMap.put("long skirt", Arrays.asList("denim skirt", "banding", "slit", "pintuck", "ruffle"));
        bMap.put("other pants", Arrays.asList("linen", "bootcut", "banding", "one tuck", "tapered", "over fit", "layered", "wide fit"));
        Hashtag.put("Bottom", bMap);

        Map<String, Object> oMap = new HashMap<String, Object>();
        oMap.put("nylon/coach jacket", null);
        oMap.put("stadium jacket", null);
        oMap.put("safari/hunting jacket", null);
        oMap.put("blouson/MA-1", null);
        oMap.put("anorak jacket", null);
        oMap.put("rucker jacket", null);
        oMap.put("training jacket", null);
        oMap.put("suit/blazer jacket", null);
        oMap.put("leather/riders jacket", null);
        oMap.put("long padded/long heavy outerwear", null);
        oMap.put("cardigan", Arrays.asList("basic cardigan", "cropped cardigan", "bolero", "boucle", "cropped", "overfit", "ribbed", "embroidered", "argyle"));
        oMap.put("fleece/poogle jumper", null);
        oMap.put("winter single coat", null);
        oMap.put("short padding/short heavy outerwear", Arrays.asList("flight", "lightweight", "duck down", "quilting", "overfit", "crop", "lambswool"));
        oMap.put("vest", Arrays.asList("boucle", "crop", "bustier", "argyle", "crochet", "layered", "overfit", "jacquard", "embroidery", "lightweight"));
        oMap.put("winter double coat", null);
        oMap.put("hooded zip-up", Arrays.asList("logo", "basic"));
        oMap.put("spring/autumn coat", null);
        oMap.put("padded vest", null);
        oMap.put("other winter coat", Arrays.asList("twill", "shearling", "alpaca", "belted", "balmacan", "overfit"));
        oMap.put("mustang/fur", Arrays.asList("cropped", "overfit", "suede", "wool", "shearling", "boucle"));
        oMap.put("other outerwear", Arrays.asList("duck down", "overfit", "cropped", "lightweight", "field", "quilting", "washed", "suede"));
        Hashtag.put("Outer", oMap);

        Map<String, Object> sMap = new HashMap<String, Object>();
        sMap.put("fashion sneakers", Arrays.asList("black", "ugly shoes", "white", "chunky", "air", "running shoes", "light"));
        sMap.put("sports sneakers", null);
        sMap.put("other sneakers", null);
        sMap.put("canvas shoes/soles", null);
        sMap.put("sandals", Arrays.asList("summer shoes", "platform", "sport", "wedge heel", "velcro", "mule", "buckle", "strap", "leather", "flip flop"));
        sMap.put("loafers", Arrays.asList("summer shoes", "basic", "tassel", "casual", "slip-on", "bloafer", "driving", "suede", "penny", "leather"));
        sMap.put("heels/pumps",null);
        sMap.put("flat shoes", Arrays.asList("mary jane", "summer shoes", "mule", "ribbon", "slingback", "square toe", "leather"));
        sMap.put("bloafers", null);
        sMap.put("slippers", Arrays.asList("slides", "summer shoes", "cushion", "leather", "logo", "velcro"));
        sMap.put("boots", null);
        sMap.put("dress shoes", null);
        sMap.put("moccasins/boat shoes", Arrays.asList("driving shoes", "suede"));
        sMap.put("other shoes", Arrays.asList("aqua", "indoor"));
        Hashtag.put("Shoes", sMap);
    }

    private static String setOrder() {
        String str = null;

        for(Map.Entry<String, Map<String, Object>> entry : Hashtag.entrySet()) {
            str = str + entry.getKey() + "\n" + "ItemTypes: ";
            Map<String, Object> item = entry.getValue();
            List types = new ArrayList(item.keySet());
            Collections.shuffle(types);
            List<String> tuples = new ArrayList<>();
            for(Object o : types) {
                String temp = (String) o;
                List e = (List<String>) item.get(o);
                if(e != null) {
                    Collections.shuffle(e);
                    temp = temp + "(" + String.join(", ", e) + ")";
                }
                tuples.add(temp);
            }
            str = str + String.join(", ", tuples);
        }

        return str;
    }

    public static CompletionRequest defaultWith(String[] words, String style) {

        String styleText = style == null ? "" : " " + style + " style";
        String randomOrder = setOrder();

        String prompt = String.format("The available options for ItemTypes for each top, bottom, outer, shoes are the following. Parse all options randomly and choose the items that best fits the instructions based on style, weather, gender.\n"+
                                "%s" +
                                "The response must be a list separated by '#', must be fully lower case, and without additional notes. " +
                                "The response format is the following.\n" +
                                "Top:#[Top_Item1]#[Top_Item2]#[Top_Item3]\n" +
                                "Bottom:#[Bottom_Item1]#[Bottom_Item2]#[Bottom_Item3]\n" +
                                "Outer:#[Outer_Item1]#[Outer_Item2]#[Outer_Item3]\n" +
                                "Shoes:#[Shoes_Item1]#[Shoes_Item2]#[Shoes_Item3]\n Choose the 3 ItemTypes from each categories to generate a recommendation for %s, " +
                        "that best fits %s in the weather on %s with probability of precipitation %s%%, %s weather, lowest temperature %s degree celsius and highest temperature %s degree celsius. " +
                        "(If the chosen ItemType has an additional keyword in the brackets behind the ItemType, it is optional to choose one of them with the ItemType.)" +
                        " In the response, the chosen 'additional keyword' in brackets should be placed behind each ItemType inside the brackets. " +
                        "Just give me the list of items without the additional notes or apologies.",

                randomOrder, words[0], styleText,  words[1], words[2], words[3], words[4], words[5]);
        System.out.println(prompt);
        return new CompletionRequest("text-davinci-003", prompt, 0.7, 500);
    }
}
