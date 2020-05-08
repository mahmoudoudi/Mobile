/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hooks.gui;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import edu.hooks.entities.Session;
import edu.hooks.entities.user;
import static edu.hooks.gui.Guide.current;

/**
 *
 * @author Ayadi
 */
//hedhy teacher
public class Client extends Form {
        static Form current;

    user User=Session.getCurrentSession();

    public Client(Form previous,Resources theme) {
        current = this; //Récupérsation de l'interface(Form) en cours

        setTitle("Client");

        setLayout(BoxLayout.y());

        getToolbar().addCommandToSideMenu("Home", null, ev -> {

        }
        );
        getToolbar().addCommandToSideMenu("About", null, ev -> {

        });
          getToolbar().addCommandToSideMenu("Events", null, ev -> {
            new InterfaceEventClient(current, theme).showBack();

        });

        getToolbar().addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, ev->{
            try {
                Session.close();
            } catch (Exception ex) {
                ex.getMessage();
            }
            previous.showBack();
               
                
                        });

    }

}
