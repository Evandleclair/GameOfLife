/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.personalprojects.GameOfLife.UtilityClasses;

import com.personalprojects.GameOfLife.BoardObject;
import com.personalprojects.GameOfLife.DataTypes.RulesBundle;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * This class is responsible for writing to and reading from XML files. 
 * @author evandleclair
 */
public class XMLWriter 
{
    static String filePathXML = "";
    private final DocumentBuilderFactory docFactory;
    private final DocumentBuilder docuBuilder;
    public XMLWriter() throws ParserConfigurationException
    {
        docFactory = DocumentBuilderFactory.newInstance();
        docuBuilder = docFactory.newDocumentBuilder();
    }
    
    /**
     * Takes a BoardObject object, and a file object, and then saves the board to that file.
     * @param bOb the board object. 
     * @param file the file object, usually retreived from a file save interface
     * @throws TransformerException
     */
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
        boardSet.setAttribute("CurrentGen", String.valueOf(bOb.getCurrentGen()));
        rootElement.appendChild(boardSet);
        
        Element boardData = doc.createElement("BoardData");
        boardData.setAttribute("BoardState", String.valueOf(bOb.getOneLineBoardString()));
        rootElement.appendChild(boardData);
        
        try ( FileOutputStream output
                = new FileOutputStream(file.getPath())) {
            writeXml(doc, output);
        } catch (IOException e) {
            LoggingClass.WriteToLog(e, "Severe IO error when loading XML file into board", "SEVERE");
        }
    }
         
    /**
     *  Takes a document object, and an output stream to use, and writes the document using that output stream. 
     * @param doc
     * @param output
     * @throws TransformerException 
     */
    private static void writeXml(Document doc, OutputStream output)throws TransformerException 
    {
        try 
        {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(output);
            transformer.transform(source, result);
        }//end try//
        catch (TransformerException e)
        {
            LoggingClass.WriteToLog(e, "Transformer exception during write method", "SEVERE");
        }//end catch//
    }//end writeXml
    
    /**
     * Using a file object, loads a board from an XML file into a board object that can be used by our program
     * @param file
     * @return a board object that is usable by our program. 
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws URISyntaxException
     */
    public BoardObject getBoardFromXML(File file) throws SAXException, ParserConfigurationException, IOException, URISyntaxException
    {
        BoardObject bOb = null;
        try
        {
            if (validateXMLagainstXSD(file.getAbsolutePath()))
            {
                RulesBundle rules;
                int  dimensions, currentGen;
                
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
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
                bOb = new BoardObject(dimensions, myBoardState, rules, 100, currentGen);
            }//end if//
            else
            {
                System.out.println("not a valid XML");
            }//end else/
            return bOb;
        }//end try//
        catch (SAXException | ParserConfigurationException | IOException e)
        {
            LoggingClass.WriteToLog(e, "Exception when loading board from XML", "SEVERE");
            return null; //if we return a null, other functions that use this one know that it failed to load a board//
        }//end catch//
    }//end getBoardFromXml/
    
    /**
     * Takes a string, and using an integer representing the square dimensions of the board it should be, turns it into a matrix of that size.//
     * @param dimensions the integer representing the height and width of the matrix to be created. All boards are square in this program.
     * @param board the single line string representing the board, to be split up according to row size.
     * @return a matrix of integers representing a board state./
     */
    int[][] convertStringToBoardMatrix(int dimensions, String board)
    {
        int[][] retVal = new int[dimensions][dimensions]; //boards are always square, so this empty matrix will be too.//
        List<String> rowStrings = splitStringAtEqualPoints(board,dimensions); //uses a sub function to split the string//
        for (int r=0; r<retVal.length ; r++)  //iterate through the empty matrix and make its values match our list of strings
        {
            //System.out.println(rowStrings.get(r));
            for (int c=0; c<retVal[0].length; c++)
            {
                int cellVal = Character.getNumericValue(rowStrings.get(r).charAt(c));
                retVal[r][c]=cellVal;
            }
        }
        return retVal;
    }//end convertStringToBoardMatrix//
    
    /**
     * splits a string at equal points and returns it as a list. 
     * @param s the string to be split
     * @param i we split it every time we have passed this many digits. 
     * @return a list of strings representing the split string. 
     */
    public List<String> splitStringAtEqualPoints(String s, int i)
    {
        List<String> ret = new ArrayList<String>((s.length() + i - 1) / i);
        for (int start = 0; start < s.length(); start += i) 
        {
            ret.add(s.substring(start, Math.min(s.length(), start + i)));
        }
        return ret;
    }//end splitStringAtEqualPoints//
    
    /**
     * using an XSD, we verify that a file is correct XML before we will even try to load it as a board. 
     * @param xmlPath the path of the XML file we are checking
     * @return a boolean if the document was valid or not. 
     * @throws URISyntaxException
     */
    public boolean validateXMLagainstXSD(String xmlPath) throws URISyntaxException 
    {
        try 
        {
            URL resource = getClass().getClassLoader().getResource("gameOfLifeSchema.xsd"); //XSD file is kept in resources.//
            File xsdFile = Paths.get(resource.toURI()).toFile();
            SchemaFactory factory
                    = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } 
        catch (IOException e) 
        {
            System.out.println("Exception: " + e.getMessage());
            LoggingClass.WriteToLog(e, "IO Exception when trying to compare XML to XSD", "SEVERE"); //considered severe as it prevents program function//
            return false;
        } 
        catch (SAXException e) 
        {
            LoggingClass.WriteToLog(e, "SAX Exception when trying to compare XML to XSD", "SEVERE");
            return false;
        }//end catch//
        return true;
    }//end validateXMLagainstXSD
}//end class//
