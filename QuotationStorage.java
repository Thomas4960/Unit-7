package quotationsdatabase;

import java.util.*;


public class QuotationStorage {
    private int indexNum;
    private String quoteStr;
    private String output;
    //Arraylist to store index of authors
    private ArrayList<String> authIndex = new ArrayList();
    //arraylist for all quotes
    private ArrayList<String> storage = new ArrayList();
    //parralel arraylist to store authors for each quote individually
    private ArrayList<String> authorList = new ArrayList();
    
    public QuotationStorage() 
    {
    }
    public void setQuotes(ArrayList<String> quotes)
    {
        storage = quotes;
    }
    public void setAuthors(ArrayList<String> authors)
    {
        authorList = authors;
    }
    public ArrayList<String> getQuotes()
    {
        return storage;
    }
    public ArrayList<String> getAuthIndex()
    {
        return authIndex;
    }
    public ArrayList<String> getAuthors()
    {
        return authorList;
    }
    //toString to print all quotes out
    public String toString() {
        output = "";
        for(int i = 0; i < storage.size(); i++) {
            output += "[" + i + "] " + storage.get(i)+"\n";
        }
        return output;
    }
    
    //method to remove quote
    public void removeQuote(String quoteStr)
    {
        //user inputs a selected quote. The index value of said quote is obtained
        indexNum = storage.indexOf(quoteStr);
        //.remove is used with the index position to remove both the author and the quote
        storage.remove(indexNum);
        authorList.remove(indexNum);

    }
    
    //method to add quote
    public void addQuote(String auth, String quote)
    {
        //gets the user inputted quote and name
        //adds "" marks around the quote followed by - author and adds it to the
        //quotes arraylist
        quoteStr = "\""+quote+"\" - "+auth;
        storage.add(quoteStr);
        //authors name is added to the author list array
        authorList.add(auth.toUpperCase());
        sort();
    }
    
    //generates list of all authors used for search by author
    public void authorIndex()
    {
       authIndex.clear();
       //counter to look through all authors
       //ensures author name doesnt appear more than once
       for (int c = 0; c < authorList.size(); c++)
       {
           if (c == 0)
           {
               //when the rogram first starts, the first object is added because
               //there is no prior object to compare to to check for duplicates
               authIndex.add(authorList.get(c));
           }
            //since array is sorted, if the author's name has already been printed,
            //it which is shown by c-1, it will skip it so that there will be no
            //duplciate author names
           else if (!authorList.get(c).equalsIgnoreCase(authorList.get(c-1)))
           {
               authIndex.add(authorList.get(c));
           }
       }
    }
    public String help()
    {
        output = "                                          ~Database Guide~";
        output += "\n-When first running the program you must add"
                + " a quote.\n\n-To add a quote, click the \"add a quote"
                + "\" button then proceed to enter\nthe quote you would like to "
                + "add in the window that opens. Hit enter and\nyou will be then "
                + "prompted with another window to enter the author's name."; 
        output += "\n\n-To remove a quote, click the \"remove a quote\" button then"
                + " select a quote\nfrom the window that opens. Hit the \"enter\""
                + " key or the \"ok\" button to\nconfirm your selection.\n";
        output += "\n-To search for quotes by a specific author, click the \"search"
                + "\" by author\nbutton then select one of the authors from the "
                + "window that opens. Only\nauthors that are present in the database"
                + " will be present in the list. After\nselecting an author, the"
                + " quotes by that author will be printed in the output\nwindow.";

        return output;
    }
    //searches for quotes by author selected in prior method
    public void search(String V)
    {
        //sorted linear search
        int k;
        //intitailizes temporary arraylist to store the index positions of quotes
        //by a specific author
        ArrayList<Integer> quoteIndex = new ArrayList();
        for(k = 0; k < authorList.size(); k++){
            // if a quote is said by the author they are looking for, it gets
            //the index position represented by k
            if(authorList.get(k).equalsIgnoreCase(V)) {
                quoteIndex.add(k);
            }
            //if the author's name value is passed, it will break the loop
            if(authorList.get(k).compareTo(V)> 0){
                break;
            }
        }
        //prints out all quotes by specified author.
        System.out.println("\nAll the quotes by "+V+" are:");
        System.out.println("============================================");
        for (int n = 0; n < quoteIndex.size(); n++)
        {
            System.out.println(storage.get(quoteIndex.get(n)));
        }
        System.out.println("============================================");
        }
        //bubble sort
        public void sort() {
            //sorts the author array by comparing its values
            //goes to swap after determining that element > element+1
            for (int pass = 1; pass < authorList.size(); pass++) {
                for (int element=0;element<authorList.size()-1;element++) {
                    if (authorList.get(element).compareTo(authorList.get(element + 1)) > 0) {
                        swap(element, element + 1); 
                    }
                }
            }
      }
        
        //swap method for sort
      public void swap(int first, int second) {
          //two swaps are performed, one to swap author list and the other to
          //swap the quotes because they are stored seperately
          //first strings hold the first author's name and the first quote
            String holdAuth = authorList.get(first);
            String holdQuot = storage.get(first);
            //sets the quote and author name in the first position to the 
            //quote and author in the second postition
            authorList.set(first, authorList.get(second));
            storage.set(first, storage.get(second));
            //sets the quote and author in the second  position to the quote
            //and author in the first position
            authorList.set(second, holdAuth);
            storage.set(second, holdQuot);
      } 


}