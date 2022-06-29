/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.personalprojects.GameOfLife.Interfaces;

import java.io.File;

/**
 *
 * @author evandleclair
 */
public interface FileManagerInterface {
        public void exportBoard(File FileToExport);
        public void importBoard(File FileToImport);
        public void showOpenInterface();
        public void showSaveInterface();
        
}
