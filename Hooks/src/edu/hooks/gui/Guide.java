/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hooks.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import edu.hooks.entities.Event;
import edu.hooks.entities.Session;
import edu.hooks.entities.user;
import edu.hooks.services.ServicesEvent;

/**
 *
 * @author Ayadi
 */
//hedhy student 
public class Guide extends Form {

    static Form current;
    user User=Session.getCurrentSession();
    public Guide(Form previous, Resources theme) {
        current = this; //Récupérsation de l'interface(Form) en cours

        setTitle("Guide");
        setLayout(BoxLayout.y());

        getToolbar().addCommandToSideMenu("Home", null, ev -> {

        }
        );
        getToolbar().addCommandToSideMenu("Events", null, ev -> {
            new Interfaceevent(current, theme).showBack();

        });

        getToolbar().addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, ev -> {
            try {
                Session.close();
            } catch (Exception ex) {
                ex.getMessage();
            }
            previous.showBack();
        });

    }
    
    
}
