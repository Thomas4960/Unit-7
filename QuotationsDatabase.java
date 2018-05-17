package quotationsdatabase;

import java.io.*;
import java.util.*;
import javax.swing.*;

public class QuotationsDatabase {

	public static void main(String[] args) throws IOException {
		
		String user = "";
                String authName;
                String quoteInput;
                String removeInput;
		int userDialog = 0;
                String location = "C:/";
		String fileName = "quotes2.txt";
		String[] options = {"Display all quotes", "Display random quote", "Display selected quote", "Search by Author","Add a quote", "Remove a quote", "Exit"};
		BufferedReader quoteReader1 = null;
		try{
                    quoteReader1 = new BufferedReader(new FileReader(location+fileName));
                }
                catch(FileNotFoundException e)
                {
                    JOptionPane.showMessageDialog(null, "File: \""+fileName+"\" not "
                            + "found!\nPlease make sure it is located in \""+location+"\"");
                    System.exit(0);
                }
		ArrayList<String> authors = new ArrayList();
		ArrayList<String> storage = new ArrayList();					
		while(true)
                {
			String line1 = quoteReader1.readLine();
                        if (line1 == null)
                        {
                            break;
                        }
                        else{
                            String charAt = Character.toString(line1.charAt(0));
                            if (charAt.equalsIgnoreCase("-")){
                                authors.add(line1.substring(2, line1.length()));
                            }
                            else{
                                storage.add(line1);
                            }
                        }
		}
                quoteReader1.close();
		QuotationStorage quotes = new QuotationStorage(storage, authors);
		
		while(userDialog != 6) {
			
			userDialog = JOptionPane.showOptionDialog(null, "Please choose an option", "Quotations Database", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
			
			// DISPLAY ALL QUOTES
			if(userDialog == 0) {
				
				System.out.println("============================================");
				System.out.println(quotes);
				System.out.println("============================================");
				
			}
			
			// DISPLAY RANDOM QUOTE
			else if(userDialog == 1) {
				int rng = (int) (Math.random() * quotes.storage.size());
				System.out.println("============================================");
				System.out.println(quotes.storage.get(rng));
				System.out.println("============================================");
				
			}
		
			// DISPLAY SELECTED QUOTE
			else if(userDialog == 2) {
				user = (String) JOptionPane.showInputDialog(null, "Which quote would you like to use?", "Quotations Database", JOptionPane.PLAIN_MESSAGE, null, quotes.storage.toArray(), quotes.storage.get(0));
				System.out.println("============================================");
				System.out.println(user);
				System.out.println("============================================");
			}
			//SEARCH BY AUTHOR
                        else if (userDialog == 3)
                        {
                            while (true)
                            {
                               authName = JOptionPane.showInputDialog("Please input the author of the quote you would like to add.");
                               if (authName.equalsIgnoreCase(null) || authName.equalsIgnoreCase("")){
                                   JOptionPane.showMessageDialog(null,"Error! this field cannot be BLANK!");
                               }
                               else{
                                   break;
                               }
                            }
                            quotes.search(authName);
                        }
			// ADD A QUOTE
			else if(userDialog == 4) {
                            while (true)
                            {
                                quoteInput = JOptionPane.showInputDialog("Please input the quote you would like to add.");
                                if (quoteInput.equalsIgnoreCase(null) || quoteInput.equalsIgnoreCase(""))
                                {
                                    JOptionPane.showMessageDialog(null,"Error! this field cannot be BLANK!");
                                }
                                else
                                {
                                    break;
                                }
                            }
                            while (true)
                            {
                               authName = JOptionPane.showInputDialog("Please input the author of the quote you would like to add.");
                               if (authName.equalsIgnoreCase(null) || authName.equalsIgnoreCase("")){
                                   JOptionPane.showMessageDialog(null,"Error! this field cannot be BLANK!");
                               }
                               else{
                                   break;
                               }
                            }
                            quotes.addQuote(authName, quoteInput);
			}
			
			// REMOVE A QUOTE
			else if(userDialog == 5) {
                                removeInput =(String)JOptionPane.showInputDialog(null, "Which quote would you like to use?", "Quotations Database", JOptionPane.PLAIN_MESSAGE, null, quotes.storage.toArray(), quotes.storage.get(0));
                                quotes.removeQuote(removeInput);
			}
                        //EXIT MENU AND THEN REWRITE TXT FILE
                        
			
		}
                System.exit(0);
		
	}

}