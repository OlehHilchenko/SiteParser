package Parsers;

import Parsers.model.Product;
import Parsers.service.CsvWriter;
import Parsers.service.PageHtmlReader;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

public class AppStarter {

    private static final String TELEFONY_I_AKCESORIA_CATEGORY = "https://allegro.pl/kategoria/telefony-i-akcesoria?bmatch=cl-e2101-d3681-c3682-ele-1-1-0304";
    private static final String LABTOP_491_CATEGORY = "https://allegro.pl/kategoria/laptopy-491?bmatch=cl-e2101-d3681-c3682-ele-1-1-0304";
    private static final String KOMPUTERY_STACJONARNE_CATEGORY = "https://allegro.pl/kategoria/komputery-stacjonarne-486?bmatch=cl-e2101-d3681-c3682-ele-1-1-0304";


    public static void main(String[] args) {
        CsvWriter csvWriter = new CsvWriter();
        PageHtmlReader pageHtmlReader = new PageHtmlReader();
        List<Product> products = new ArrayList<>();

        List<Element> elementsFirstCategory = pageHtmlReader.extract100ProductsFromCategory(TELEFONY_I_AKCESORIA_CATEGORY);

        List<Element> elementsSecondCategory = pageHtmlReader.extract100ProductsFromCategory(LABTOP_491_CATEGORY);

        List<Element> elementsThirdCategory = pageHtmlReader.extract100ProductsFromCategory(KOMPUTERY_STACJONARNE_CATEGORY);

        products.addAll(
                pageHtmlReader
                        .extractProductsInfo(elementsFirstCategory)
        );

        products.addAll(
                pageHtmlReader
                        .extractProductsInfo(elementsSecondCategory)
        );

        products.addAll(
                pageHtmlReader
                        .extractProductsInfo(elementsThirdCategory)
        );

        csvWriter.createCSVFile(products);
    }
}
