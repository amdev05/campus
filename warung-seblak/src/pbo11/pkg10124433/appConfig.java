/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbo11.pkg10124433;

/**
 *
 * @author ademuchl17
 */
public class appConfig {
    private static String tema = "LIGHT";

    public static void setSelectedTheme(String theme) {
        tema = theme;
    }

    public static String getSelectedTheme() {
        return tema;
    }
}
