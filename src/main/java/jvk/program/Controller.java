package jvk.program;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Controller {
    public static void main(String[] args) throws Exception {
        CrawlConfig config = new CrawlConfig();

        config.setCrawlStorageFolder("C:\\Users\\julia\\OneDrive\\Documents\\Programms\\webCrawler\\Storage");

        config.setPolitenessDelay(1000);

        config.setMaxDepthOfCrawling(1);

        config.setMaxPagesToFetch(3);

        config.setIncludeBinaryContentInCrawling(false);

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

//        controller.addSeed("https://www.metallica.com/");
//        controller.addSeed("https://nos.nl/");
        controller.addSeed("https://www.nu.nl/");

        int numberOfCrawlers = 8;

        CrawlController.WebCrawlerFactory<BasicCrawler> factory = BasicCrawler::new;

        controller.start(factory, numberOfCrawlers);
    }
}
