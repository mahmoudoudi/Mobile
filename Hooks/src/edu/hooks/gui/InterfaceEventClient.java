/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hooks.gui;

import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
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

/**
 *
 * @author asus
 */
public class InterfaceEventClient extends Form {

    static Form currentForm;

    private EncodedImage placeHolder;
    user User = Session.getCurrentSession();

    public InterfaceEventClient(Form previous, Resources theme) {

        currentForm = this;
        currentForm.setTitle("Event");
        currentForm.setLayout(BoxLayout.y());

        for (Event c : ServicesEvent.getInstance().getAllEvents()) {
            Container InfoContainer = new Container(BoxLayout.y());
            Label nomEvent = new Label(c.getNameEvent());
            Label AddEvent = new Label(c.getAddressEvent());
            Label typee = new Label(c.getType());
            Label prix = new Label(String.valueOf(c.getPriceEvent()));
            Label Nombreplace = new Label(String.valueOf(c.getNbrPlace()));
            Label description = new Label(c.getDescriptionEvent());
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

        currentForm.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            previous.showBack();
        });
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

        nomeve.setEditable(false);
        addresseve.setEditable(false);
        typeeve.setEditable(false);
        prixeve.setEditable(false);
        nombreplaceeve.setEditable(false);
        descriptioneve.setEditable(false);

        Container Container = new Container(new FlowLayout());
        Container.addAll(nom, nomeve, address, addresseve, type, typeeve, prix, prixeve, nombreplace, nombreplaceeve, description, descriptioneve);
        EventDetail.add(img);
        EventDetail.add(Container);

        Container ButtonsContainer = new Container(new FlowLayout());

        Button participate = new Button("participate");
        Button Cancel = new Button("Cancel");

        participate.addActionListener(ev -> {

            if (Dialog.show("Confirmation", "Are u Sure ? ", "OK", "ANNULER")) {

                if (ServicesEvent.getInstance().ParticipateEvent(c, User)) {
                    Dialog.show("Success", "participation effected succesfully", new Command("OK"));
                    new InterfaceEventClient(InterfaceEventClient.currentForm, theme).show();
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }

            }

        });
        Cancel.addActionListener(ev -> {
            if (Dialog.show("Confirmation", "Are u Sure ? ", "OK", "ANNULER")) {

                if (ServicesEvent.getInstance().CancelEvent(c, User)) {
                    Dialog.show("Success", "Canceled succesfully", new Command("OK"));
                    new InterfaceEventClient(InterfaceEventClient.currentForm, theme).show();
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            }

        });
        if (!ServicesEvent.getInstance().isParticpated(c, User)) {
            ButtonsContainer.add(participate);
        } else {
            ButtonsContainer.add(Cancel);
        }

        EventDetail.add(ButtonsContainer);
        EventDetail.revalidate();

        EventDetail.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new InterfaceEventClient(Client.current, theme).show();
        });

        return EventDetail;
    }
}
