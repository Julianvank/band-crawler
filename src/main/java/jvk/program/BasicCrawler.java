package jvk.program;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.apache.http.Header;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class BasicCrawler extends WebCrawler {
    FileWriter writer = new FileWriter("Storage/links.txt");

    private static final Pattern IMAGE_EXTENSIONS = Pattern.compile(".*\\.(bmp|gif|jpg|png)$");

    public BasicCrawler() throws IOException {
    }

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url){
        String href = url.getURL().toLowerCase();


        return true;
    }

    @Override
    public void visit(Page page){
        int docid = page.getWebURL().getDocid();
        String url = page.getWebURL().getURL();
        String domain = page.getWebURL().getDomain();
        String path = page.getWebURL().getPath();
        String subDomain = page.getWebURL().getSubDomain();
        String parentUrl = page.getWebURL().getParentUrl();
        String anchor = page.getWebURL().getAnchor();

        try {
            writer.write(domain);
            writer.write("///////////////");
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.debug("Docid: {}", docid);
        logger.info("URL: {}", url);
        logger.debug("Domain: '{}'", domain);
        logger.debug("Sub-domain: '{}'", subDomain);
        logger.debug("Path: '{}'", path);
        logger.debug("Parent page: {}", parentUrl);
        logger.debug("Anchor text: {}", anchor);

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String text = htmlParseData.getText();
            String html = htmlParseData.getHtml();
            Map<String, String> metaTags = htmlParseData.getMetaTags();
            String title = htmlParseData.getTitle();

            Set<WebURL> links = htmlParseData.getOutgoingUrls();

            try {
////                writer.write(html);
                writer.write(text);
////                writer.write(String.valueOf(metaTags));
//                writer.write("///////////////////////////");
//                writer.write(title);
            } catch (IOException e) {
                e.printStackTrace();
            }


            logger.debug("Text length: {}", text.length());
            logger.debug("Html length: {}", html.length());
            logger.debug("Number of outgoing links: {}", links.size());
        }

        Header[] responseHeaders = page.getFetchResponseHeaders();
        if (responseHeaders != null) {
            logger.debug("Response headers:");
            for (Header header : responseHeaders) {
                logger.debug("\t{}: {}", header.getName(), header.getValue());
            }
        }

        try {

            writer.write(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.debug("=============");

    }
}
