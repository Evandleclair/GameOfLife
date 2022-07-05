/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.personalprojects.GameOfLife.DataTypes;

import com.personalprojects.GameOfLife.SimCanvasWindow;

/**
 * Stores the ID and the matching Sim Window together so they can be passed as one. 
 * @author evandleclair
 * @param <String>
 * @param <SimCanvasWindow>
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
