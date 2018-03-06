package com.mobiclixgroup.image_gallery.futures.main;

import android.content.Context;

import com.mobiclixgroup.image_gallery.architecture.BasePresenter;
import com.mobiclixgroup.image_gallery.network.OnListener;
import com.mobiclixgroup.image_gallery.interactor.GetAllImageByNameInteractor;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;


public class MainPresenter extends BasePresenter<MainView, MainRouter> {

    Context context;
    GetAllImageByNameInteractor getAllImageByNameInteractor;

    @Inject
    public MainPresenter(Context context) {
        this.context = context;
    }

    public void getListImage(OnListener onListener, String searchName){
        getAllImageByNameInteractor = new GetAllImageByNameInteractor(context);
        getAllImageByNameInteractor.onResponse(onListener).execute(searchName);
    }

    public List<String> readHTML(String html) {
        List<String> imageList = new ArrayList<>();
        TagNode tagNode = new HtmlCleaner().clean(html);
        try {
            Document  doc = new DomSerializer(
                    new CleanerProperties()).createDOM(tagNode);
            XPath xpath = XPathFactory.newInstance().newXPath();
            String IMAGE_XPATH = "//@data-source";
            XPathExpression expr = xpath.compile(IMAGE_XPATH);
            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodes.getLength(); i++) {
                imageList.add(nodes.item(i).getNodeValue());
            }

        } catch (ParserConfigurationException e) {

        } catch (XPathExpressionException e) {

        }

        return imageList;
    }
}
