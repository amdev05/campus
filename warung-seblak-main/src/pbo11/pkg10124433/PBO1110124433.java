/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pbo11.pkg10124433;

import com.formdev.flatlaf.*;
import javax.swing.*;

/**
 *
 * @author Ademuchl
 */
public class PBO1110124433 {


    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
          
        frm_login login= new frm_login();
        login.setVisible(true);
    }
    
}
