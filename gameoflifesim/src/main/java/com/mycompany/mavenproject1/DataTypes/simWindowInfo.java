/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.DataTypes;

import com.mycompany.GameOfLife.SimCanvasWindow;

/**
 *
 * @author evandleclair
 */
public class simWindowInfo<String,SimCanvasWindow> 
{
    private String id;
    private SimCanvasWindow obj;
    public simWindowInfo(String id, SimCanvasWindow obj){
        this.id = id;
        this.obj = obj;
    }
    public String getID(){ return id; }
    public SimCanvasWindow getOBJ(){ return obj; }
    public void setID(String id){ this.id = id; }
    public void setOBJ(SimCanvasWindow obj){ this.obj = obj; }
}
