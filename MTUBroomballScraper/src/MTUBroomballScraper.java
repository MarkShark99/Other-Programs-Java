import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.SQLOutput;

public class MTUBroomballScraper
{
    public static void main(String[] args) throws IOException
    {
        Document doc = Jsoup.connect("http://www.broomball.mtu.edu/teams/view/2018").get();

        Elements conferenceList = doc.select("#main_content_container > h1");
        Elements divisionList = doc.select("#main_content_container > blockquote");
        Elements yearOptions = doc.select("[name=season] > option");

        System.out.println("Seasons");
        for (Element e : yearOptions)
        {
            System.out.println(e.text());
        }

        System.out.println("\nConferences");
        for (int i = 0; i < conferenceList.size(); i++)
        {
            System.out.println(conferenceList.get(i).text());
            for (Element division : divisionList.get(i).select("h1"))
            {
                System.out.println("\t" + division.text());
                for (Element table : divisionList.get(i).select("table"))
                {
                    for (Element row : table.select("tr"))
                    {
                        System.out.println("\t\t" + row.select("td").get(0).text());
                    }
                }
            }
        }
    }
}
