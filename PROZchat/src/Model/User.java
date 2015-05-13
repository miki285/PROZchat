package Model;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

/*
 * Klasa u�ytkownika chatu, zawiera unikalny numer u�ytkownika,zbi�r jego kontakt�w, dat� logowania i ostatniego pingu,
 * oraz histori� rozmowy danego u�ytkownika 
 */
public class User {
 private int uniqueID;
 private Date logIn;
 private Date lastPing;
 private HashMap<Integer, String> contacts;
 private HashMap<Integer, LinkedList<Message>> chatHistory;
}
