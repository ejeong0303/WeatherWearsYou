package WeatherWearsYou.external;

public record CompletionRequest(String model, String prompt, double temperature, int max_tokens) {

    public static CompletionRequest defaultWith(String[] words, String style) {

        String styleText = style == null ? "" : " " + style + " style";

        String prompt = String.format("The available options for ItemTypes for each category are the following. Parse all options randomly and choose the items that best fits the instructions based on style, weather, gender.\n"+
                                "Top\n"+
                                "ItemTypes: shirts/blouse(cropped, overfit, oxford, linen, embroidered, ruffle), long-sleeve T-shirts(cropped, overfit, raglan, tencel, relaxed), knit/sweater(half zip-up knit, collar knit, knit vest, cropped knit, cropped off-shoulder, linen, boucle, pullover, slit), short-sleeve T-shirt(graphic, logo/lettering, plain, cropped, overfit, embroidery), sweatshirt(cropped, overfit, plain, setup, logo, half zip-up, basic, graphic), sleeveless(layered, cropped, tank top, bustier, ribbed), other tops(cropped, half zip-up, tank top, overfit, embroidery), pique/collar T-shirt, hooded T-shirt(logo, graphic, basic, half zip-up, cropped), sports top\n"+
                                "Bottom\n"+
                                "ItemTypes: training/jogger pants(wide pants, wide fit, overfit, banding, training shorts), short pants(nylon shorts, banding, overfit, pintuck, chino, seersucker, linen, one tuck, two tuck), sports bottoms, denim pants(wide fit, boot cut, tapered, light blue, dark blue, black blue, raw), cotton pants(wide fit, tapered, chino, one tuck, banding, linen, overfit), short pants(nylon shorts, pintuck, banding, over fit, two tuck, chinos, linen, one tuck, seersucker), suit pants/slacks(wide pants), other pants(linen, bootcut, banding, one tuck, tapered, over fit, layered, wide fit), sports bottoms, jumpsuits/overalls(overfit, linen, tapered, belted, cutting cropped, twill, high waist, suspenders), leggings(yoga clothes, bootcut, high waist, slit, sportswear, tights, pilates), miniskirts(denim skirt, raw denim, banding, two-piece, buckle, layered, slit), midi skirt(slit, banding, layered, denim skirt, belted), long skirt(denim skirt, banding, slit, pintuck, ruffle)\n" +
                                "Outer\n"+
                                "ItemTypes: nylon/coach jacket, stadium jacket, safari/hunting jacket, blouson/MA-1, anorak jacket, other outerwear(duck down, overfit, cropped, lightweight, field, quilting, washed, suede), trucker jacket, training jacket, suit/blazer jacket, leather/riders jacket, long padded/long heavy outerwear, cardigan(basic cardigan, cropped cardigan, bolero, boucle, cropped, overfit, ribbed, embroidered, argyle), fleece/poogle jumper, winter single coat, short padding/short heavy outerwear(flight, lightweight, duck down, quilting, overfit, crop, lambswool), vest(boucle, crop, bustier, argyle, crochet, layered, overfit, jacquard, embroidery, lightweight), winter double coat, hooded zip-up (logo, basic), spring/autumn coat, padded vest, other winter coat (twill, shearling, alpaca, belted, balmacan, overfit), mustang/fur (cropped, overfit, suede, wool, shearling, boucle)\n"+
                                "Shoes\n"+
                                "ItemTypes: fashion sneakers (black, ugly shoes, white, chunky, air, running shoes, light), sports sneakers, other sneakers, canvas shoes/soles, sandals(summer shoes, platform, sport, wedge heel, velcro, mule, buckle, strap, leather, flip flop), loafers(summer shoes, basic, tassel, casual, slip-on, bloafer, driving, suede, penny, leather), heels/pumps, flat shoes(mary jane, summer shoes, mule, ribbon, slingback, square toe, leather), bloafers, slippers(slides, summer shoes, cushion, leather, logo, velcro), boots, dress shoes, moccasins/boat shoes (driving shoes, suede), other shoes (aqua, indoor)\n"+
                                "The response must be a list separated by '#', with no additional notes. " +
                                "The response format is the following.\n" +
                                "Top:#[Top_Item1]#[Top_Item2]#[Top_Item3]\n" +
                                "Bottom:#[Bottom_Item1]#[Bottom_Item2]#[Bottom_Item3]\n" +
                                "Outer:#[Outer_Item1]#[Outer_Item2]#[Outer_Item3]\n" +
                                "Shoes:#[Shoes_Item1]#[Shoes_Item2]#[Shoes_Item3]\n Choose the 3 ItemTypes from each categories to generate a recommendation for %s, " +
                        "that best fits %s in the weather on %s with probability of precipitation %s%%, %s weather, lowest temperature %s degree celsius and highest temperature %s degree celsius. " +
                        "(If the chosen ItemType has an additional keyword in the brackets behind the ItemType, it is optional to choose one of them with the ItemType.)" +
                        " In the response, the additional keyword should be placed behind each ItemType with brackets. " +
                        "Just give me the list of items without the additional notes or apologies.",

                words[0], styleText,  words[1], words[2], words[3], words[4], words[5]);
        return new CompletionRequest("text-davinci-003", prompt, 0.7, 500);
    }
}
