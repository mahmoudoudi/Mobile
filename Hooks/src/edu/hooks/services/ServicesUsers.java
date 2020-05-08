/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hooks.services;

//import edu.gestudent.utils.DataBase;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import edu.hooks.entities.user;
import static edu.hooks.utils.Statics.BASE_URL;
import java.io.IOException;
import java.util.Map;

//import java.sql.Connection;
//import java.sql.Statement;
/**
 *
 * @author Ayadi
 */
public class ServicesUsers {

    public static ServicesUsers instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public user User = new user();

    private ServicesUsers() {
        req = new ConnectionRequest();
    }

    public static ServicesUsers getInstance() {

        if (instance == null) {
            instance = new ServicesUsers();
        }
        return instance;
    }

    public user parseUser(String jsonText) {
   
        user UserL = new user();
        try {
            JSONParser j = new JSONParser();

            Map<String, Object> UserListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            if (UserListJson.get("type").equals("Login succeed")) {

                float id = Float.parseFloat(UserListJson.get("id").toString());
                UserL.setId((int) (id));
                
                
                UserL.setEmail(UserListJson.get("email").toString());
                UserL.setUsername(UserListJson.get("username").toString());

                if (UserListJson.get("role").toString().contains("ROLE_CLIENT")) {
                    UserL.setRoles("CLIENT");
                } else {
                    UserL.setRoles("GUIDE");
                }

            } else {
                return null;
            }

        } catch (IOException ex) {
                ex.getMessage();
        }

        return UserL;
    }

    public user Login(String username,String password) {
        String url =BASE_URL +"loginMobile/"+username+"/"+password;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                User = parseUser(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return User;
    }

}
