/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.GameOfLife.popupWindows;

import java.io.File;

/**
 *
 * @author evandleclair
 */
public interface FileManagerInterface {
        public String convertBoardToString();
        public String convertStringToBoard();
        public void ExportBoard(File FileToExport);
        public void ImportBoard(File FileToImport);
        public void ShowOpenInterface();
        public void ShowSaveInterface();
        
}
