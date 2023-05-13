package WeatherWearsYou.item;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
public class ItemCrawling {
    private String category;
    private String type;
    private int productID;
    private String productName;
    private String price;
    //0: male 1: female 2: unisex
    private int gender;
    private String imgLink;
    private List<String> tags;

    public void doCrawlByCategoryID(int categoryID, int productCnt) throws InterruptedException {
        WebDriver driver = setDriver.getChromeDriver();
        String url = "https://www.musinsa.com/app/";
        String tmpUrl = "";

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
            type = driver.findElement(By.xpath("//*[@id=\"location_category_2_depth\"]")).getText();
            System.out.println("detail type : " + type);

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

                productID =  Integer.parseInt(array[5]);
                //Thread.sleep(500);
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

                productName = driver.findElement(By.xpath("//*[@id=\"page_product_detail\"]/div[3]/div[3]/span/em")).getText();

                imgLink = driver.findElement(By.xpath("//*[@id=\"detail_bigimg\"]/div"))
                        .findElement(By.tagName("img")).getAttribute("src");

                try {
                    price = driver.findElement(By.xpath("//*[@id=\"list_price\"]")).getText();
                } catch (Exception e) {
                    WebElement tmp = driver.findElement(By.xpath("//*[@id=\"goods_price\"]"));
                    String txt = tmp.getText().trim();
                    List<WebElement> children = tmp.findElements(By.xpath("./*"));
                    for (WebElement child : children)
                    {
                        txt = txt.replaceFirst(child.getText(), "").trim();
                    }
                    price = txt;
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

                System.out.println("product ID : " + productID);
                System.out.println("product Name : " + productName);
                System.out.println("product img : " + imgLink);
                System.out.println("product price : " + price);
                System.out.println("product gender : " + gender);
            }
        }
        driver.quit();
    }
}
