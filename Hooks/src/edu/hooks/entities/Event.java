/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.hooks.entities;

//import java.sql.Timestamp;

/**
 *
 * @author asus
 */
public class Event {
     int IdEvent ;
    String NameEvent;
    String AddressEvent;
    String Type;
    float PriceEvent;
    int NbrPlace;
    String DescriptionEvent;
    String Image;
    //Timestamp DateD;
    //Timestamp DateF;  

    public int getIdEvent() {
        return IdEvent;
    }

    public void setIdEvent(int IdEvent) {
        this.IdEvent = IdEvent;
    }

    public String getNameEvent() {
        return NameEvent;
    }

    public void setNameEvent(String NameEvent) {
        this.NameEvent = NameEvent;
    }

    public String getAddressEvent() {
        return AddressEvent;
    }

    public void setAddressEvent(String AddressEvent) {
        this.AddressEvent = AddressEvent;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public float getPriceEvent() {
        return PriceEvent;
    }

    public void setPriceEvent(float PriceEvent) {
        this.PriceEvent = PriceEvent;
    }

    public int getNbrPlace() {
        return NbrPlace;
    }

    public void setNbrPlace(int NbrPlace) {
        this.NbrPlace = NbrPlace;
    }

    public String getDescriptionEvent() {
        return DescriptionEvent;
    }

    public void setDescriptionEvent(String DescriptionEvent) {
        this.DescriptionEvent = DescriptionEvent;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    //public Timestamp getDateD() {
      //  return DateD;
    //}

    /*public void setDateD(Timestamp DateD) {
        this.DateD = DateD;
    }*/
    

//    public Timestamp getDateF() {
//        return DateF;
//    }
//
//    public void setDateF(Timestamp DateF) {
//        this.DateF = DateF;
//    }

    public Event() {
    }

    @Override
    public String toString() {
        return "Event{" + "IdEvent=" + IdEvent + ", NameEvent=" + NameEvent + ", AddressEvent=" + AddressEvent + ", Type=" + Type + ", PriceEvent=" + PriceEvent + ", NbrPlace=" + NbrPlace + ", DescriptionEvent=" + DescriptionEvent + ", Image=" + Image +/* ", DateD=" + DateD + ", DateF=" + DateF +*/ '}';
    }
    
    
}
