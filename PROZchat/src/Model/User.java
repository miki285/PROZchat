package Model;


import java.util.HashMap;
import java.util.LinkedList;

/*
 * Klasa u¿ytkownika chatu, zawiera unikalny numer u¿ytkownika,zbiór jego kontaktów, datê logowania i ostatniego pingu,
 * oraz historiê rozmowy danego u¿ytkownika 
 */
public class User {
 private int uniqueID;
 private String logIn;
 private HashMap<Integer, String> contacts;
 private HashMap<Integer, LinkedList<Message>> chatHistory;
}
