/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.DataTypes;

import com.mycompany.mavenproject1.SimulatorWindow;

/**
 *
 * @author toast
 */
public class simWindowInfo<ID,OBJ> {
    private ID id;
    private OBJ obj;
    public simWindowInfo(ID id, OBJ obj){
        this.id = id;
        this.obj = obj;
    }
    public ID getID(){ return id; }
    public OBJ getOBJ(){ return obj; }
    public void setID(ID id){ this.id = id; }
    public void setOBJ(OBJ obj){ this.obj = obj; }
}
