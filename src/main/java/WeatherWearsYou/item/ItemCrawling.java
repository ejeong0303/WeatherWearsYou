package WeatherWearsYou.item;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static WeatherWearsYou.item.Item.*;

public class ItemCrawling {
    private static Integer category;
    private static String itemType;
    private static Integer rank;
    private static int itemId;
    private static String itemName;
    private static Integer price;
    //0: male 1: female 2: unisex
    private static Integer gender;
    private static String imgLink;
    private static String tags;

    public static void doCrawlingByCategoryID(Integer categoryID, Integer productCnt, ItemRepository itemRepository) throws InterruptedException {
        WebDriver driver = setDriver.getChromeDriver();
        String url = "https://www.musinsa.com/app/";
        String tmpUrl;
        String ctg;
        Integer savedCnt;
        Integer page;

        //ctg = driver.findElement(By.id("ui-id-" + (categoryID - 1)))
        //        .findElement(By.tagName("strong")).getAttribute("textContent");

        switch(categoryID) {
            case 4: category = TOP;
            break;
            case 6: category = OUTER;
            break;
            case 8:
            case 12: category = BOTTOM;
            break;
            case 14:
            case 16: category = SHOES;
            break;
            default: category = -1;
        }


        savedCnt = productCnt;
        page = 1;
        while(savedCnt > 0) {
            try {
                driver.get(url);
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

                List<WebElement> categories = driver.findElement(By.id("ui-id-" + categoryID))
                        .findElements(By.tagName("ul"));

                //add "li"tags of types
                List<WebElement> types = new ArrayList<>();
                for (WebElement category : categories) {
                    types.addAll(category.findElements(By.tagName("li")));
                }

                //add all urls of types
                List<String> typeUrls = new ArrayList<>();
                for (WebElement type : types) {
                    tmpUrl = type.findElement(By.tagName("a")).getAttribute("href");
                    ctg = tmpUrl.substring(tmpUrl.length() - 6, tmpUrl.length());
                    typeUrls.add(tmpUrl + "?d_cat_cd=" + ctg +
                            "&brand=&list_kind=small&sort=sale_high&sub_sort=1y&page=" + page +
                            "&display_cnt=90&group_sale=&exclusive_yn=" +
                            "&sale_goods=&timesale_yn=&ex_soldout=&plusDeliveryYn=&kids=&color=&price1=&price2=" +
                            "&shoeSizeOption=&tags=&campaign_id=&includeKeywords=&measure=");
                }

                //traverse types
                for (String typeUrl : typeUrls) {
                    try {
                        rank = 1;
                        driver.navigate().to(typeUrl);
                        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                        itemType = driver.findElement(By.xpath("//*[@id=\"location_category_2_depth\"]")).getText();

                        List<WebElement> products = driver.findElement(By.id("searchList"))
                                .findElements(By.xpath("//*[@id=\"searchList\"]/li[position()<" + (productCnt + 1) + "]"));

                        //add all urls of products
                        List<String> productUrls = new ArrayList<>();
                        for (WebElement product : products) {
                            tmpUrl = product.findElement(By.tagName("a")).getAttribute("href");
                            productUrls.add(tmpUrl);
                        }

                        //traverse products
                        for (String productUrl : productUrls) {
                            try {
                                driver.navigate().to(productUrl);
                                String[] array = productUrl.split("/");

                                itemId = Integer.parseInt(array[5]);
                                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

                                itemName = driver.findElement(By.xpath("//*[@id=\"page_product_detail\"]/div[3]/div[3]/span/em")).getText();

                                imgLink = driver.findElement(By.xpath("//*[@id=\"detail_bigimg\"]/div"))
                                        .findElement(By.tagName("img")).getAttribute("src");

                                try {
                                    String txt = driver.findElement(By.xpath("//*[@id=\"sPrice\"]/ul/li[1]/span[2]")).getAttribute("textContent");
                                    txt = txt.replaceAll("[^0-9]", "");
                                    price = Integer.parseInt(txt);
                                } catch (Exception e) {
                                    try {
                                        WebElement tmp = driver.findElement(By.xpath("//*[@id=\"goods_price\"]"));
                                        String txt = tmp.getText().trim();
                                        List<WebElement> children = tmp.findElements(By.xpath("./*"));
                                        for (WebElement child : children) {
                                            txt = txt.replaceFirst(child.getText(), "").trim();
                                        }
                                        txt = txt.replaceAll("[^0-9]", "");
                                        price = Integer.parseInt(txt);
                                    } catch (Exception f) {
                                        try {
                                            String txt = driver.findElement(By.xpath("//*[@id=\"product_order_info\"]/div[4]/ul/li[3]/div/span[1]")).getAttribute("textContent");
                                            txt = txt.replaceAll("[^0-9]", "");
                                            price = Integer.parseInt(txt);
                                        } catch (Exception g) {
                                            continue;
                                        }
                                    }
                                }

                                List<WebElement> genders = driver.findElement(By.className("txt_gender")).findElements(By.tagName("span"));
                                if (genders.size() == 2) {
                                    gender = 2;
                                } else if (genders.get(0).getText().equals("남")) {
                                    gender = 0;
                                } else {
                                    gender = 1;
                                }
                                try {
                                    List<WebElement> styletags = driver.findElement(By.cssSelector(".article-tag-list.list"))
                                            .findElement(By.tagName("p")).findElements(By.tagName("a"));
                                    tags = "";
                                    for (WebElement tag : styletags) {
                                        tags = tags + tag.getAttribute("textContent");
                                    }
                                } catch (Exception e) {
                                    tags = "";
                                }

                                Item item = new Item(itemId, category, rank, itemType, itemName, price, gender, imgLink, tags);
                                itemRepository.save(item);

                                System.out.println("product Name : " + itemName);
                                rank++;
                            } catch (Exception e) {
                                e.printStackTrace();
                                rank++;
                                continue;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        continue;
                    }
                }
                page++;
                savedCnt -= 90;
            } catch (Exception e) {
                e.printStackTrace();
                page++;
                continue;
            }
        }
        driver.quit();
    }
}
