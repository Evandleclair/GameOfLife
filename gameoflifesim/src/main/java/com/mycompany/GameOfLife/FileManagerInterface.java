/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.GameOfLife;

/**
 *
 * @author toast
 */
public interface FileManagerInterface {
        public String convertBoardToString();
        public String convertStringToBoard();
        public void ExportBoard();
        public void ImportBoard();
        
}
