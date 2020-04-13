package com.util;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class XMLReader {

  public static String getRequestPageData(String selectItem) {
	  String value = "";
	  try {
			File fXmlFile = new File(System.getProperty("user.dir") +"\\target\\test-classes\\com\\upgrade\\testdata\\testdata.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);			
					
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
					
			NodeList nList = doc.getElementsByTagName("requestPage");
			Node nNode = nList.item(0);						
			System.out.println("\nCurrent Element :" + nNode.getNodeName());						
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				if(selectItem.equals("amount")){
					value = eElement.getElementsByTagName("requestAmount").item(0).getTextContent();
				}
			}
			
	  } catch (Exception e) {
			e.printStackTrace();
	  }  
	  return value;
  }
  
  public static String getBasicInfoPageData(String selectItem) {
	  String value = "";
	  try {
			File fXmlFile = new File(System.getProperty("user.dir") +"/data/testdata.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);			
					
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
					
			NodeList nList = doc.getElementsByTagName("BasicInfoPage");
			Node nNode = nList.item(0);						
			System.out.println("\nCurrent Element :" + nNode.getNodeName());						
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				switch(selectItem) {
					case "firstName":
						value = eElement.getElementsByTagName("firstName").item(0).getTextContent();
						break;
					case "lastName":
						value = eElement.getElementsByTagName("lastName").item(0).getTextContent();
						break;
					case "email":
						value = eElement.getElementsByTagName("email").item(0).getTextContent();
						break;
					case "password":
						value = eElement.getElementsByTagName("password").item(0).getTextContent();
						break;
					case "address":
				}
				if(selectItem.equals("amount")){
					value = eElement.getElementsByTagName("requestAmount").item(0).getTextContent();
					System.out.println("Request amount : " + value);
				}
			}			
	  } catch (Exception e) {
			e.printStackTrace();
	  }  
	  return value;
  }
}
