package WeatherWearsYou.item;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static WeatherWearsYou.item.Item.BOTTOM;
import static WeatherWearsYou.item.Item.MALE;

public class ItemCrawling {
    private static String category;
    private static String itemType;
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

        driver.get(url);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        category = driver.findElement(By.id("ui-id-" + (categoryID - 1)))
                .findElement(By.tagName("strong")).getAttribute("textContent");

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
            typeUrls.add(tmpUrl);
        }

        //traverse types
        for(String typeUrl : typeUrls) {
            driver.navigate().to(typeUrl);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            itemType = driver.findElement(By.xpath("//*[@id=\"location_category_2_depth\"]")).getText();

            List<WebElement> products = driver.findElement(By.id("searchList"))
                    .findElements(By.xpath("//*[@id=\"searchList\"]/li[position()<" + (productCnt + 1) + "]"));

            //add all urls of products
            List<String> productUrls = new ArrayList<>();
            for(WebElement product : products) {
                tmpUrl = product.findElement(By.tagName("a")).getAttribute("href");
                productUrls.add(tmpUrl);
            }

            //traverse products
            for(String productUrl : productUrls) {
                driver.navigate().to(productUrl);
                String[] array = productUrl.split("/");

                itemId =  Integer.parseInt(array[5]);
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

                itemName = driver.findElement(By.xpath("//*[@id=\"page_product_detail\"]/div[3]/div[3]/span/em")).getText();

                imgLink = driver.findElement(By.xpath("//*[@id=\"detail_bigimg\"]/div"))
                        .findElement(By.tagName("img")).getAttribute("src");

                try {
                    String txt = driver.findElement(By.xpath("//*[@id=\"sPrice\"]/ul/li[1]/span[2]")).getAttribute("textContent");
                    txt = txt.replaceAll("[^0-9]","");
                    price = Integer.parseInt(txt);
                } catch (Exception e) {
                    WebElement tmp = driver.findElement(By.xpath("//*[@id=\"goods_price\"]"));
                    String txt = tmp.getText().trim();
                    List<WebElement> children = tmp.findElements(By.xpath("./*"));
                    for (WebElement child : children)
                    {
                        txt = txt.replaceFirst(child.getText(), "").trim();
                    }
                    txt = txt.replaceAll("[^0-9]","");
                    price = Integer.parseInt(txt);
                }

                List<WebElement> genders = driver.findElement(By.className("txt_gender")).findElements(By.tagName("span"));
                if(genders.size() == 2) {
                    gender = 2;
                }
                else if(genders.get(0).getText().equals("남")){
                    gender = 0;
                }
                else {
                    gender = 1;
                }

                List<WebElement> styletags = driver.findElement(By.cssSelector(".article-tag-list.list"))
                        .findElement(By.tagName("p")).findElements(By.tagName("a"));
                tags = "";
                for (WebElement tag : styletags)
                {
                    tags = tags + tag.getAttribute("textContent");
                }
                Item item = new Item(itemId, categoryID, itemType, itemName, price, gender, imgLink, tags);
                itemRepository.save(item);


                System.out.println("product Name : " + itemName);
                //System.out.println("taglist :" + tags);
                //System.out.println("product ID : " + itemId);
                //System.out.println("product img : " + imgLink);
                //System.out.println("product price : " + price);
                //System.out.println("product gender : " + gender);
            }
        }
        driver.quit();
    }
}
