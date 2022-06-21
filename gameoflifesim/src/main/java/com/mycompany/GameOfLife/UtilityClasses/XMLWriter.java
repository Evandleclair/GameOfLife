/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.GameOfLife.UtilityClasses;

import com.mycompany.GameOfLife.BoardObject;
import com.mycompany.mavenproject1.DataTypes.RulesBundle;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author evandleclair
 */
public class XMLWriter 
{
        static String filePathXML = "";
        DocumentBuilderFactory docFactory;
        DocumentBuilder docuBuilder;
        public XMLWriter() throws ParserConfigurationException
                {
                 docFactory = DocumentBuilderFactory.newInstance();
                 docuBuilder = docFactory.newDocumentBuilder();
                }
        public void createFileFromBoard(BoardObject bOb, File file) throws TransformerException
        {
            Document doc = docuBuilder.newDocument();
            RulesBundle rb = bOb.getMyRules();
            Element rootElement = doc.createElement("Board");
            doc.appendChild(rootElement);
           
            Element rulesSet = doc.createElement("Rules");
            rulesSet.setAttribute("StarveNumber", String.valueOf(rb.getStarveNumber()));
            rulesSet.setAttribute("AliveNumber", String.valueOf(rb.getAliveNumber()));
            rulesSet.setAttribute("ReviveNumber", String.valueOf(rb.getReviveNumber()));
            rulesSet.setAttribute("OverpopulationNumber", String.valueOf(rb.getOverpopNumber()));
            rootElement.appendChild(rulesSet);
            Element boardSet = doc.createElement("BoardSettings");
            boardSet.setAttribute("Dimensions", String.valueOf(bOb.getDimensions()));
            //boardSet.setAttribute("TickSpeed", String.valueOf(bOb.getTickSpeed()));
            boardSet.setAttribute("CurrentGen", String.valueOf(bOb.getCurrentGen()));
            rootElement.appendChild(boardSet);
            Element boardData = doc.createElement("BoardData");
            boardData.setAttribute("BoardState", String.valueOf(bOb.getOneLineBoardString()));
            rootElement.appendChild(boardData);
            //rootElement.appendChild(doc.createElement("Rules Set").setAttribute("Alive", ));
              try (FileOutputStream output =
                     new FileOutputStream(file.getPath())) {
            writeXml(doc, output);
        } 
        catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
         
    private static void writeXml(Document doc, OutputStream output)throws TransformerException 
    {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);
        transformer.transform(source, result);
    }
    
    public BoardObject getBoardFromXML(File file) throws SAXException, ParserConfigurationException, IOException
    {
        RulesBundle rules=null;
       
        int  dimensions=0, currentGen=0;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
       //an instance of builder to parse the specified xml file  
        DocumentBuilder db = dbf.newDocumentBuilder();  
        Document doc = db.parse(file);  
        doc.getDocumentElement().normalize();
        Element board = doc.getDocumentElement();

        
        NamedNodeMap rulesMap=board.getElementsByTagName("Rules").item(0).getAttributes();
        int starveNumber=Integer.parseInt(rulesMap.getNamedItem("StarveNumber").getNodeValue());
        int aliveNumber=Integer.parseInt(rulesMap.getNamedItem("AliveNumber").getNodeValue());
        int reviveNumber=Integer.parseInt(rulesMap.getNamedItem("ReviveNumber").getNodeValue());
        int overpopNumber=Integer.parseInt(rulesMap.getNamedItem("OverpopulationNumber").getNodeValue());
        rules = new RulesBundle(starveNumber,aliveNumber,reviveNumber,overpopNumber);
        NamedNodeMap settingsMap=board.getElementsByTagName("BoardSettings").item(0).getAttributes();
        currentGen=Integer.parseInt(settingsMap.getNamedItem("CurrentGen").getNodeValue());
        dimensions=Integer.parseInt(settingsMap.getNamedItem("Dimensions").getNodeValue());
        String boardString = board.getElementsByTagName("BoardData").item(0).getAttributes().getNamedItem("BoardState").getNodeValue();
        int[][] myBoardState=convertStringToBoardMatrix(dimensions,boardString);
        BoardObject bOb = new BoardObject(dimensions, myBoardState, rules, 100, currentGen);
        return bOb;
    }
    
    int[][] convertStringToBoardMatrix(int dimensions, String board)
    {
        int[][] retVal = new int[dimensions][dimensions];
        List<String> rowStrings = splitStringAtEqualPoints(board,dimensions);
        for (int r=0; r<retVal.length ; r++) 
        {
            //System.out.println(rowStrings.get(r));
            for (int c=0; c<retVal[0].length; c++)
            {
                int cellVal = Character.getNumericValue(rowStrings.get(r).charAt(c));
                retVal[r][c]=cellVal;
            }
        }
        return retVal;
    }
    
    public List<String> splitStringAtEqualPoints(String s, int i)
    {
        List<String> ret = new ArrayList<String>((s.length() + i - 1) / i);
        for (int start = 0; start < s.length(); start += i) 
        {
            ret.add(s.substring(start, Math.min(s.length(), start + i)));
        }
        return ret;
    }
}
