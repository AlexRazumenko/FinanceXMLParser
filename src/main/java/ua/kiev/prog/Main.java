package ua.kiev.prog;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Main {

    public static void main(String[] args) {

        try {
            URL url = new URL("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?valcode=EUR&date=20181031");
            //BufferedReader bf = new BufferedReader((new InputStreamReader(url.openStream())));

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            InputStream stream = url.openStream();
            //InputStreamReader str = new InputStreamReader(url.openStream());
            Document document = builder.parse(stream);

            Element root = document.getDocumentElement();
            System.out.println("Root element: " + root.getNodeName());
            NodeList nodeList = root.getElementsByTagName("currency");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                Element element = (Element) node;
                System.out.println(element.getNodeName());

                System.out.println("Rate:");

                System.out.println("R030:" + element.getElementsByTagName("r030").item(0).
                        getChildNodes().item(0).getNodeValue());

                System.out.println("Currency:" + element.getElementsByTagName("txt").item(0).
                        getChildNodes().item(0).getNodeValue());

                System.out.println("Rate:" + element.getElementsByTagName("rate").item(0).
                        getChildNodes().item(0).getNodeValue());

                System.out.println("Code:" + element.getElementsByTagName("cc").item(0).
                        getChildNodes().item(0).getNodeValue());

                System.out.println("Date:" + element.getElementsByTagName("exchangedate").item(0).
                        getChildNodes().item(0).getNodeValue());
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }

}
