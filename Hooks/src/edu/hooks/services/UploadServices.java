
package edu.hooks.services;
import java.net.MalformedURLException;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author asus
 */
public class UploadServices {

    public String uploadImage(String Path) {
        String fileNameInServer = "";

        FileUploader fu = new FileUploader("localhost/newnew/web");

        try {
            //Upload
            fileNameInServer = fu.upload(Path);
        } catch (MalformedURLException ex) {
            ex.getMessage();

        } catch (java.io.IOException ex) {
            ex.getMessage();
        }
        //System.out.println(fileNameInServer);

        return fileNameInServer;

    }
    
    
    
}