package Parsers.service;

import Parsers.model.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PageHtmlReader {

    private static final String USER_AGENT = "Chrome/4.0.249.0 Safari/532.5";
    private static final String REFERRER = "http://www.google.com";
    private static final String HTML_DISCOUNT_FIELD = ".mp0t_ji.mpof_vs._9c44d_1VS-Y._9c44d_3_DDQ.mpof_vs._9c44d_2MDwk";
    private static final String HTML_CURRENT_PRICE = "._1svub._lf05o";
    private static final String HTML_URLS = ".msts_9u.mg9e_0.mvrt_0.mj7a_0.mh36_0.mpof_ki.m389_6m.mx4z_6m.m7f5_6m.mse2_k4.m7er_k4._9c44d_1ILhl";
    private static final String HTML_PRODUCT_DESCRIPTION = ".opbox-sheet._26e29_11PCu.card._9f0v0.msts_n7";



    private static final String HTML_PRODUCT_ELEMENT = ".mpof_ki.mqen_m6.mp7g_oh.mh36_0.mvrt_0";

    private static final int MIN_COUNT_PRODUCT_WITH_DISCOUNT = 100;
    private static final String NEXT_PAGE = "&p=";
    private static final String LINKS = "a[href]";
    private static final String IMAGE_SRC = "[src]";
    private static final String ABS_SRC = "abs:src";
    private static final String ABS_HREF = "abs:href";


    public List<Product> extractProductsInfo (List<Element> elements) {
        List<Product> result = new ArrayList<>();
        for (Element element : elements){

            result.add(
                    transformElementToProduct(
                            element
                    )
            );
        }
        return result;
    }

    public Product transformElementToProduct (Element element){
        String oldPriceInfo;
        String currentPriceInfo;
        String imageUrl;
        String productUrl;
        String description;

        oldPriceInfo = element.select(HTML_DISCOUNT_FIELD).text();
        currentPriceInfo = element.select(HTML_CURRENT_PRICE).text();
        Elements urls = element.select(HTML_URLS);

        Elements links = urls.select(LINKS);
        Elements media = urls.select(IMAGE_SRC);

        imageUrl = media.attr(ABS_SRC);
        productUrl = links.attr(ABS_HREF);

        description = getProductDescription(productUrl);

        return new Product(oldPriceInfo, currentPriceInfo, imageUrl, productUrl, description);
    }

    public String getProductDescription (String productURL){
        String result="";

        try {
            Document doc = Jsoup.connect(productURL)
                    .userAgent(USER_AGENT)
                    .referrer(REFERRER)
                    .get();

            result = doc.select(HTML_PRODUCT_DESCRIPTION).text();
            Thread.sleep(5000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Element> extract100ProductsFromCategory (String category){

        ArrayList<Element> result = new ArrayList<>();
        String nextPage = category;
        int numberOfNextPage = 2;

        do {
            try {
                Document doc = Jsoup.connect(nextPage)
                        .userAgent(USER_AGENT)
                        .referrer(REFERRER)
                        .get();


                Elements prodFildsList = doc.select(HTML_PRODUCT_ELEMENT);

                for (Element element : prodFildsList) {

                    Elements select = element.select(HTML_DISCOUNT_FIELD);
                    if (!select.isEmpty()) {
                        result.add(element);

                        if (MIN_COUNT_PRODUCT_WITH_DISCOUNT <= result.size()){
                            return result;
                        }
                    }
                }
                Thread.sleep(5000);
                nextPage = category + NEXT_PAGE + numberOfNextPage;
                ++numberOfNextPage;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (MIN_COUNT_PRODUCT_WITH_DISCOUNT > result.size());

        return result;
    }
}
