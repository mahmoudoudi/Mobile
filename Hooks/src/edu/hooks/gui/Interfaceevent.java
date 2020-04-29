/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hooks.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import edu.hooks.entities.Event;
import edu.hooks.entities.Session;
import edu.hooks.entities.user;
import edu.hooks.services.ServicesEvent;
import edu.hooks.statistique.EventPieChart;

/**
 *
 * @author Ayadi
 */
public class Interfaceevent extends Form {
    static Form  currentForm;
    
    
    private EncodedImage placeHolder;
    user User = Session.getCurrentSession();

    public Interfaceevent(Form previous, Resources theme) {

        currentForm = this;
        currentForm.setTitle("Event");
        currentForm.setLayout(BoxLayout.y());

        for (Event c : ServicesEvent.getInstance().getAllEvents()) {
            Container InfoContainer = new Container(BoxLayout.y());
            Label nomEvent = new Label(c.getNameEvent());
            Label AddEvent = new Label (c.getAddressEvent());
            Label typee = new Label (c.getType());
            Label prix = new Label(String.valueOf(c.getPriceEvent()));
            Label Nombreplace = new Label(String.valueOf(c.getNbrPlace()));
            Label description = new Label (c.getDescriptionEvent());
            //Label email = new Label(c.getEmail());
            //Label tel = new Label(String.valueOf(c.getTel()));
            //Label Date = new Label(c.getDate());
            
            InfoContainer.add(nomEvent);
            InfoContainer.add(AddEvent);
            InfoContainer.add(typee);
            InfoContainer.add(prix);
            InfoContainer.add(Nombreplace);
            InfoContainer.add(description);
            Container Container = new Container(BoxLayout.x());

            placeHolder = EncodedImage.createFromImage(theme.getImage("7.jpg"), true);
            String url = "http://localhost/newnew/web/photos/" + c.getImage();
            ConnectionRequest connection = new ConnectionRequest();
            connection.setUrl(url);
            URLImage imgurl = URLImage.createToStorage(placeHolder, url, url);

            ImageViewer img = new ImageViewer(imgurl.scaled(imgurl.getWidth() * 1, imgurl.getHeight() * 1));
            Container.add(img);
            Container.add(InfoContainer);
            currentForm.add(Container);

            img.addPointerReleasedListener(ev -> {
                EventDetail(c, theme).show();
            });
        }

        currentForm.getToolbar().addCommandToOverflowMenu("Add Event", null, ev -> {
           AddEvent(theme).show();
        });
        
        currentForm.getToolbar().addCommandToOverflowMenu("Stat Events", null, ev -> {
                                StatEvent(theme).show();


        });

        currentForm.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            previous.showBack();
        });

    }
    

    
    
    
    
    
  
  public Form AddEvent(Resources theme) {

        Form AddEvent = new Form("ADD", BoxLayout.y());

        Label nom = new Label("nom");
        Label address = new Label("adress");
        Label type = new Label("type");
        Label prix = new Label("prix");
        Label nombreplace = new Label("nombreplace");
        Label description = new Label("description");
        Label image = new Label("image");

        
        TextField nomeve = new TextField(null, "nomeve");
        TextField addresseve = new TextField(null, "addresseve");
        TextField typeeve = new TextField(null, "typeeve");
        TextField prixeve = new TextField(null, "prixeve");
        TextField nombreplaceeve = new TextField(null, "nombreplaceeve");
        TextField descriptioneve = new TextField(null, "descriptioneve");
        TextField imageeve = new TextField(null, "imageeve");

        
        Button Save = new Button("Save");

        AddEvent.addAll(nom, nomeve, address, addresseve, type, typeeve, prix, prixeve, nombreplace, nombreplaceeve,description,descriptioneve,image,imageeve,Save);

        Save.addActionListener(ev -> {
            if ((nomeve.getText().length() == 0) || (addresseve.getText().length() == 0) || typeeve.getText().length() == 0
                    || prixeve.getText().length() == 0 || nombreplaceeve.getText().length() == 0 || (descriptioneve.getText().length() == 0) || (imageeve.getText().length() == 0)) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {
                try {
                    Event c = new Event();
                    c.setNameEvent(nomeve.getText());
                    c.setAddressEvent(addresseve.getText());
                    c.setType(typeeve.getText());
                    
                    float price = Float.parseFloat(prixeve.getText());
                    c.setPriceEvent(price);
                    int nombre = Integer.parseInt(nombreplaceeve.getText());
                     c.setNbrPlace(nombre);
                       c.setDescriptionEvent(descriptioneve.getText());
                       c.setImage(imageeve.getText());
                       
                   

                    if (ServicesEvent.getInstance().AddEvent(c)) {
                        Dialog.show("Success", "Event Added", new Command("OK"));
                        new Interfaceevent(Interfaceevent.currentForm, theme).show();
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Number places  must be a number", new Command("OK"));
                }

            }

        });

        AddEvent.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new Interfaceevent(Interfaceevent.currentForm, theme).show();
        });

        return AddEvent;
    }
  
  public Form EventDetail(Event c, Resources theme) {

        Form EventDetail = new Form(c.getNameEvent(), BoxLayout.y());

        placeHolder = EncodedImage.createFromImage(theme.getImage("7.jpg"), true);
        String url = "http://localhost/newnew/web/photos/" + c.getImage();
        ConnectionRequest connection = new ConnectionRequest();
        connection.setUrl(url);
        URLImage imgurl = URLImage.createToStorage(placeHolder, url, url);

        ImageViewer img = new ImageViewer(imgurl.scaled(imgurl.getWidth() * 1, imgurl.getHeight() * 1));

        Label nom = new Label("nom");
        Label address = new Label("adress");
        Label type = new Label("type");
        Label prix = new Label("prix");
        Label nombreplace = new Label("nombreplace");
        Label description = new Label("description");
        //Label image = new Label("image");

        //SpanLabel Message = new SpanLabel("Descrption: \n" + c.getDescription() + "\n" + "Created AT: " + c.getDate() + "\n" + "Members: " + c.getNombreplace());

        TextField EventNameField = new TextField(null, "Name");

        EventNameField.setText(c.getNameEvent());

        TextField nomeve = new TextField(null, "nom");
        nomeve.setText(c.getNameEvent());
        TextField addresseve = new TextField(null, "Adresse");
        addresseve.setText(String.valueOf(c.getAddressEvent()));
        TextField typeeve = new TextField(null, "Type");
        typeeve.setText(c.getType());
        TextField prixeve = new TextField(null, "Price");
        prixeve.setText(String.valueOf(c.getPriceEvent()));
        TextField nombreplaceeve = new TextField(null, "places");
        nombreplaceeve.setText(String.valueOf(c.getNbrPlace()));
         TextField descriptioneve = new TextField(null, "description");
        descriptioneve.setText(c.getDescriptionEvent());

        Container Container = new Container(new FlowLayout());
        Container.addAll(nom, nomeve, address, addresseve, type, typeeve,prix ,prixeve,nombreplace,nombreplaceeve,description,descriptioneve);
        EventDetail.add(img);
        EventDetail.add(Container);

        Container ButtonsContainer = new Container(new FlowLayout());

        Button Delete = new Button("Delete");
        Button Edit = new Button("Edit");
        ButtonsContainer.addAll(Edit, Delete);

        EventDetail.add(ButtonsContainer);
        EventDetail.revalidate();
        Delete.addActionListener(ev -> {
            String result = ServicesEvent.getInstance().DeleteEvent(c);
            if (!result.equals("Error")) {
                Dialog.show("Success", result, new Command("OK"));
                new Interfaceevent(Interfaceevent.currentForm, theme).show();
            } else {
                Dialog.show("ERROR", "Server error", new Command("OK"));
            }

        });

        Edit.addActionListener(ev -> {
            c.setNameEvent(EventNameField.getText());
            c.setAddressEvent(addresseve.getText());
            
            c.setType(typeeve.getText());
            c.setPriceEvent(Float.parseFloat(prixeve.getText()));
            c.setNbrPlace(Integer.parseInt(nombreplaceeve.getText()));
            c.setDescriptionEvent(descriptioneve.getText());
            
            if (ServicesEvent.getInstance().EditEvent(c)) {
                Dialog.show("Success", "Club Edited", new Command("OK"));
                new Interfaceevent(Interfaceevent.currentForm, theme).show();
            } else {
                Dialog.show("ERROR", "Server error", new Command("OK"));
            }
        });

        EventDetail.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new Interfaceevent(Guide.current, theme).show();
        });

        return EventDetail;
    }
  
  public Form StatEvent(Resources theme) {

               EventPieChart a = new EventPieChart();
                        Form stats_Form =a.execute();
                        SpanLabel test_SpanLabel = new SpanLabel("Hiiii");
                        Class cls = EventPieChart.class;
        stats_Form.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new Interfaceevent(Guide.current, theme).show();
        });

        return stats_Form;
    }
    
    
    
    
    
    
    
    
    
}
