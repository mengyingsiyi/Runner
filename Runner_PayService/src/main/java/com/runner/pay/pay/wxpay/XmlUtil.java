package com.runner.pay.pay.wxpay;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @program: FmOpen
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-08-13 09:52
 */
public class XmlUtil {

    //生成
    public static String createXML(Map<String,String> map){
//        StringBuffer buffer=new StringBuffer();
//        buffer.append("<xml>");
//        for(String k:map.keySet()){
//            buffer.append("<"+k+">"+map.get(k)+"</"+k+">");
//        }
//        buffer.append("</xml>");
//        return buffer.toString();
        Document document= DocumentHelper.createDocument();
        Element root=document.addElement("xml");
        Set<String> keys=map.keySet();
        for(String k: keys){
            Element child= root.addElement(k);
            //child.setText("<![CDATA["+map.get(k)+"]]");
            child.setText(map.get(k));
        }
        return document.asXML();
    }
    //解析
    public static Map<String,String> parseXml(String xml){
        SAXReader reader=new SAXReader();
        try {
            Document document=reader.read(new ByteArrayInputStream(xml.getBytes()));
            //获取根节点
            Element root=document.getRootElement();
            List<Element> list=root.elements();
            Map<String,String> map=new HashMap<>();
            for(Element e:list){
                map.put(e.getName(),e.getTextTrim());
            }
            return map;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
