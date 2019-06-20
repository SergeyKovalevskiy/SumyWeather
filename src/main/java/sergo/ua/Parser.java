package sergo.ua;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;




public class Parser {



    private static Document getPage() throws IOException {
        String url = "https://sinoptik.ua/%D0%BF%D0%BE%D0%B3%D0%BE%D0%B4%D0%B0-%D1%81%D1%83%D0%BC%D1%8B";
        Document page = Jsoup.parse(new URL(url), 3000);
        return page;
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        Document page = getPage();
        Element tab = page.select("div#bd1").first();
        String wday = tab.select("p.day-link").first().text();
        String date = tab.select("p.date").first().text();
        String month = tab.select("p.month").first().text();
        String heatMin = tab.select("div.temperature .min").first().text();
        String heatMax = tab.select("div.temperature .max").first().text();
        String fen = tab.select("div.weatherIco").first().attr("title");
        String imgS = tab.select("img.weatherImg").first().attr("src");
        System.out.println("   День: "+wday+"  Число: "+date+" "+month+" Температура: "+heatMin+"  "+heatMax+" Погода: "+fen);

        SimpleGUI app = new SimpleGUI(wday+" "+date+" "+month, heatMin+"   "+heatMax, fen, imgS);
        app.setVisible(true);

    }
}
