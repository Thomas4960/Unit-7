package quotationsdatabase;

import java.io.*;
import java.util.*;
import javax.swing.*;

public class QuotationsDatabase {

    public static void main(String[] args) throws IOException {
        //boolean to determine if user exits while adding quote
        boolean exit;
        //user input for quote selection
        String user = "";
        //user input for author name when adding quote
        String authName = "";
        //user input for quote when adding quote
        String quoteInput = "";
        //user input to store selection when removing quote
        String removeInput;
        //int to store user input on main menu
        int userDialog = 0;
        // int  to store user's choice to either create new file or exit
        int fileNew = 0;
        //arrayList to store list of authors
        ArrayList<String> authors = new ArrayList();
        //arraylist to store list of quotes
        ArrayList<String> storage = new ArrayList();
        //string to store file name of the quotes
        String fileName = "quotes2.txt";
        //string to store the directory of the file
        String location = System.getProperty("user.dir")+ "\\";
        //array to print yes/no options for input window
        String[] yN = {"Yes","No"};
        //array to display all main menu options for input window
        String[] options = {"Display all quotes", "Display random quote", "Display selected quote", "Search by Author","Add a quote", "Remove a quote", "Help", "Exit"};
        //reader to read text file and add contents to arrayList
        BufferedReader quoteReader1 = null;
        //file to create new text file if one cannot be found
        File fileN = null;
        //file writer to store the quote list in a text file
        PrintWriter fileWriter1 = null;
        

        //error check to make sure file in given directory exists
        try{
            quoteReader1 = new BufferedReader(new FileReader(location+fileName));
        }
        catch(FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, "File: \""+fileName+"\" not "
                    + "found!\nPlease make sure it is located in \""+location+"\"\n",
                    "ERROR!", JOptionPane.INFORMATION_MESSAGE);
            fileNew = JOptionPane.showOptionDialog(null, "Would you like to create a new file?", "Quotations Database", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, yN, yN[1]);
            if (fileNew == 0)
            {
                fileN = new File(location,fileName);
                fileN.createNewFile();
                quoteReader1 = new BufferedReader(new FileReader(location+fileName));
            }
            else
            {
                System.exit(0);
            }
        }	
        
        
        //Fills the storage and authors array list with respective info
        while(true)
        {
            //line reader
            String line1 = quoteReader1.readLine();
            //if line is null (end of file), exits loop
            if (line1 == null)
            {
                break;
            }
            else{
                //temp variable to dettermine first letter in a line
                String charAt = Character.toString(line1.charAt(0));
                //if the first letter is - (representing an author, adds said 
                //line to authors arraylist
                if (charAt.equalsIgnoreCase("-")){
                    authors.add(line1.substring(2, line1.length()));
                }
                //else, the line must be a quote so it adds it to storage arraylist
                else{
                    storage.add(line1);
                }
            }
        }
        //closes reader
        quoteReader1.close();
        
        
        //intitalized constructor to modify the quotations array (add, remove, etc)
        QuotationStorage quotes = new QuotationStorage(storage, authors);
        
        
        //if user clicks exit, user dialog will output 6, therefore the program exits
        while(userDialog != 7) {
            //main menu
            userDialog = JOptionPane.showOptionDialog(null, "Please choose an option", "Quotations Database", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

            // DISPLAY ALL QUOTES
            if(userDialog == 0) {
                if (quotes.storage.size() <= 0)
                {
                    JOptionPane.showMessageDialog(null, "There are no quotes to randomly display!", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    System.out.println("\nAll the quotes are:");
                    System.out.println("============================================");
                    System.out.print(quotes);
                    System.out.println("============================================");
                }
            }

            // DISPLAY RANDOM QUOTE
            else if(userDialog == 1) {
                //if file is empty, storage.size = 0, therefore this option wont work
                //error guard to prevent user from using this function
                if (quotes.storage.size() <= 0)
                {
                    JOptionPane.showMessageDialog(null, "There are no quotes to randomly display!", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    //rng variable stores random index value to choose a random quote
                    int rng = (int) (Math.random() * quotes.storage.size());
                    System.out.println("\nRandomly selecting a quote:");
                    System.out.println("============================================");
                    System.out.println(quotes.storage.get(rng));
                    System.out.println("============================================");
                }
            }

            // DISPLAY SELECTED QUOTE
            else if(userDialog == 2) {
                if (quotes.storage.size() <= 0)
                {
                    JOptionPane.showMessageDialog(null, "There are no quotes to select!", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    user = (String) JOptionPane.showInputDialog(null, "Which quote would you like to use?\nClick \"cancel\" to return to the main menu.", "Quotations Database", JOptionPane.PLAIN_MESSAGE, null, quotes.storage.toArray(), quotes.storage.get(0));
                    if (user != null){
                    System.out.println("\nThe selected quote is:");
                    System.out.println("============================================");
                    System.out.println(user);
                    System.out.println("============================================");
                    }
                }
            }
            
            //SEARCH BY AUTHOR
            else if (userDialog == 3)
            {
                if (quotes.storage.size() <= 0)
                {
                    JOptionPane.showMessageDialog(null,"There are no authors to select!", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                    {
                    //generates list of authors for users to select from
                    quotes.authorIndex();
                    //user input
                    authName = (String) JOptionPane.showInputDialog(null, "Please select the author of the quotes you are looking for.\nClick \"cancel\" to return to the main menu.", "Quotations Database", JOptionPane.PLAIN_MESSAGE, null, quotes.authIndex.toArray(), quotes.authIndex.get(0));
                    //gets list of quotes by the specified author
                    if (authName != null)
                    {
                    quotes.search(authName);
                    }
                }

            }
            
            // ADD A QUOTE
            else if(userDialog == 4) {
                exit = false;
                while (exit == false)
                {
                    quoteInput = JOptionPane.showInputDialog("Please input the quote you would like to add.\nClick \"cancel\" to return to the main menu.");
                    if ( quoteInput == null)
                    {
                        exit = true;
                    }
                    else if (quoteInput.equalsIgnoreCase(""))
                    {
                        JOptionPane.showMessageDialog(null,"This field cannot be BLANK!", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                    {
                        break;
                    }
                }
                while (exit == false)
                {
                   authName = JOptionPane.showInputDialog("Please input the author of the quote you would like to add.\nClick \"cancel\" to return to the main menu.");
                   if (authName == null)
                    {
                        exit = true;
                    }
                    else if (authName.equalsIgnoreCase(""))
                    {
                        JOptionPane.showMessageDialog(null,"This field cannot be BLANK!", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
                       
                    }
                    else
                    {
                        break;
                    }
                }
                if (exit == false)
                {
                    quotes.addQuote(authName, quoteInput);
                }
            }

            // REMOVE A QUOTE
            else if(userDialog == 5) {
                if (quotes.storage.size() <= 0)
                {
                    JOptionPane.showMessageDialog(null, "There are no quotes to remove!", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    removeInput =(String)JOptionPane.showInputDialog(null, "Which quote would you like to use?\nClick \"cancel\" to return to the main menu.", "Quotations Database", JOptionPane.PLAIN_MESSAGE, null, quotes.storage.toArray(), quotes.storage.get(0));
                    if (removeInput != null){
                        quotes.removeQuote(removeInput);
                    }
                }
            }
            else if (userDialog == 6)
            {
                JOptionPane.showMessageDialog(null, "Database Guide", "Quotations Database", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        fileNew = JOptionPane.showOptionDialog(null, "Would you like to save your changes?", "Quotations Database", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, yN, yN[1]);
        if (fileNew == 0)
        {
            fileWriter1 = new PrintWriter(new FileWriter(location+fileName));
            //counter for quotes, stops loop when there are no more quotes to add
            for (int j = 0; j < quotes.storage.size(); j++)
            {
                //adds a quote from quote arraylist
                fileWriter1.println(quotes.storage.get(j));
                //then adds its author from the author arraylist
                fileWriter1.println("- "+quotes.authorList.get(j));
            }
            //closes filewriter
            fileWriter1.close();
        }
        
        //exits program
        System.exit(0);
    }
}