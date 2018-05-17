package quotationsdatabase;

import java.util.*;
import javax.swing.*;

public class QuotationStorage {
    private int indexNum;
    private String quoteStr;
    //Arraylist to store index of authors
    ArrayList<String> authIndex = new ArrayList();
    //arraylist for all quotes
    ArrayList<String> storage = new ArrayList();
    //parralel arraylist to store authors for each quote individually
    ArrayList<String> authorList = new ArrayList();
    
    //default constructor to fill arrayList's
    public QuotationStorage(ArrayList<String> storage_, ArrayList<String> authorList_) {
        storage = storage_;
        authorList = authorList_;
    }
    //toString to print all quotes out
    public String toString() {
        String output = "";
        for(int i = 0; i < storage.size(); i++) {
            output += "[" + i + "] " + storage.get(i) + "\n";
        }
        return output;
    }
    //method to remove quote
    public void removeQuote(String quoteStr)
    {
        indexNum = storage.indexOf(quoteStr);
        storage.remove(indexNum);
        authorList.remove(indexNum);

    }
    //method to add quote
    public void addQuote(String auth, String quote)
    {
        quoteStr = "\""+quote+"\" - "+auth;
        storage.add(quoteStr);
        authorList.add(auth.toUpperCase());
        sort();
    }
    //generates list of all authors used for search by author
    public void authorIndex()
    {
       authIndex.clear();
       for (int c = 0; c < authorList.size(); c++)
       {
           if (c == 0)
           {
               authIndex.add(authorList.get(c));
           }
           else if (!authorList.get(c).equalsIgnoreCase(authorList.get(c-1)))
           {
               authIndex.add(authorList.get(c));
           }
       }
    }
    //searches for quotes by author selected in prior method
    public void search(String V)
    {
        //sorted linear search
        int k;
        ArrayList<Integer> quoteIndex = new ArrayList();
        for(k = 0; k < authorList.size(); k++){
            if(authorList.get(k).equalsIgnoreCase(V)) {
                quoteIndex.add(k);
            }
            if(authorList.get(k).compareTo(V)> 0){
                break;
            }
        }
        //prints out all quotes by specified author.
        System.out.println("All the quotes by "+V+" are:");
        System.out.println("============================================");
        for (int n = 0; n < quoteIndex.size(); n++)
        {
            System.out.println(storage.get(quoteIndex.get(n)));
        }
        System.out.println("============================================");
        }
        //bubble sort
        public void sort() {
            for (int pass = 1; pass < authorList.size(); pass++) {
                for (int element=0;element<authorList.size()-1;element++) {
                    if (authorList.get(element).compareTo(authorList.get(element + 1)) > 0) {
                        swap(element, element + 1); 
                    }
                }
            }
      }

      public void swap(int first, int second) {
            String holdAuth = authorList.get(first);
            String holdQuot = storage.get(first);
            authorList.set(first, authorList.get(second));
            storage.set(first, storage.get(second));
            authorList.set(second, holdAuth);
            storage.set(second, holdQuot);
      } 


}