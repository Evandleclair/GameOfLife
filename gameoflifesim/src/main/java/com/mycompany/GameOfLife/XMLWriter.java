/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.GameOfLife;

import com.mycompany.mavenproject1.DataTypes.RulesBundle;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author toast
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
            boardSet.setAttribute("TickSpeed", String.valueOf(bOb.getTickSpeed()));
            boardSet.setAttribute("CurrentGen", String.valueOf(bOb.getCurrentGen()));
            rootElement.appendChild(boardSet);
            Element boardData = doc.createElement("boardData");
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
          // write doc to output stream
    private static void writeXml(Document doc,
                                 OutputStream output)
            throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);
        transformer.transform(source, result);
    }
       // public 
}
