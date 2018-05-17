package quotationsdatabase;

import java.util.*;
import javax.swing.*;

public class QuotationStorage {
        private int indexNum;
        private String quoteStr;
	ArrayList<String> storage = new ArrayList();
        ArrayList<String> authorList = new ArrayList();
	public QuotationStorage(ArrayList<String> storage_, ArrayList<String> authorList_) {
		storage = storage_;
                authorList = authorList_;
	}
	
	public String toString() {
		String output = "";
		for(int i = 0; i < storage.size(); i++) {
			output += "[" + i + "] " + storage.get(i) + "\n";
                        
		}
		return output;
	}
        public void removeQuote(String quoteStr)
        {
            indexNum = storage.indexOf(quoteStr);
                storage.remove(indexNum);
                authorList.remove(indexNum);
            
        }
        public void addQuote(String auth, String quote)
        {
            quoteStr = "\""+quote+"\" - "+auth;
            storage.add(quoteStr);
            authorList.add(auth);
        }
        public void search(String V)
        {
            int k;
            ArrayList<Integer> quoteIndex = new ArrayList();
            int counter = 0;
            for(k = 0; k < authorList.size(); k++){
                if(authorList.get(k).equalsIgnoreCase(V)) {
                    counter++;
                    quoteIndex.add(k);
                }
                if(authorList.get(k).compareTo(V)> 0){
                    break;
                }
            }
            if (counter > 0)
            {
                System.out.println("All the quotes by "+V+" are:");
                System.out.println("============================================");
                for (int n = 0; n < quoteIndex.size(); n++)
                {
                    System.out.println(storage.get(quoteIndex.get(n)));
                }
                System.out.println("============================================");
            }
            else
            {
                JOptionPane.showMessageDialog(null,"The author: "+V+" could not be found!");
            }
        }
        
        
        
}