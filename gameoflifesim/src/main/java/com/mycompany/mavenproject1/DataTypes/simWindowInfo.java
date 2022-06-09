/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.DataTypes;

import com.mycompany.GameOfLife.SimulatorWindow;

/**
 *
 * @author toast
 */
public class simWindowInfo<String,SimulatorWindow> 
{
    private String id;
    private SimulatorWindow obj;
    public simWindowInfo(String id, SimulatorWindow obj){
        this.id = id;
        this.obj = obj;
    }
    public String getID(){ return id; }
    public SimulatorWindow getOBJ(){ return obj; }
    public void setID(String id){ this.id = id; }
    public void setOBJ(SimulatorWindow obj){ this.obj = obj; }
}
