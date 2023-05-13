package WeatherWearsYou.item;

public class CrawlTest {
    public static final int ITEM_NUM = 5;

    public static void main(String[] args) throws InterruptedException {
        /*
        //top
        Crawling top = new Crawling();
        top.doCrawlByCategoryID(4, 5);

        //outer
        Crawling outer = new Crawling();
        outer.doCrawlByCategoryID(6, 5);

        //skirt
        Crawling skirt = new Crawling();
        skirt.doCrawlByCategoryID(12, 5);

        //sneakers
        Crawling sneakers = new Crawling();
        sneakers.doCrawlByCategoryID(14, 5);

        //shoes
        Crawling shoes = new Crawling();
        shoes.doCrawlByCategoryID(16, 5);
        */

        //pants
        ItemCrawling pants = new ItemCrawling();
        pants.doCrawlByCategoryID(8, 5);
    }
}
