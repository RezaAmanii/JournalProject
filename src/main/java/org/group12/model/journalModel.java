package org.group12.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class journalModel {

    // A list of all created journals
    private List<journalEntry> entriesList;


    // Constructor to initiate the list of journals
    public journalModel(){
        entriesList = new ArrayList<>();
    }

    public List<journalEntry> getEntriesList(){
        return entriesList;
    }


    // A method for creating journals and adding them to the list
    public void addJournalEntry(String title, String category){
        journalEntry entry = new journalEntry(title, category);
        entriesList.add(entry);
    }

    // A method to remove the journal by its ID
    public void removeJournalEntry(int id){
        for(journalEntry entry: entriesList){
            if(entry.getJournalID() == id){
                entriesList.remove(entry);
            }
        }
    }

    // A method to get journals by their ID
    public journalEntry getJournalEntryByID(int id){
        for(journalEntry entry: entriesList){
            if(entry.getJournalID() == id) return entry;
        }
        return null;
    }


    // A simple search method for the specific journal
    public List<journalEntry> searchJournal(String keyword){
        List<journalEntry> result = new ArrayList<>();
        for(journalEntry entry : entriesList){
            if(entry.getJournalTitle() == keyword){
                result.add(entry);
            }
        }
        return result;
    }









    // An Entry class to make an object of journal
    public class journalEntry{
        private int journalID = 0;
        private String journalTitle;
        private String category;
        private Date date;



        public journalEntry(String title, String category){
            this.journalTitle = title;
            this.category = category;

            // Journal ID increments of the creating a new journal with initiate number starts from 0
            this.journalID++;
        }


        public int getJournalID(){
            return journalID;
        }

        public void setJournalID(int newID){
            this.journalID = newID;
        }

        public String getJournalTitle(){
            return journalTitle;
        }

        public void setJournalTitle(String newTitle){
            this.journalTitle = newTitle;
        }

        public String getCategory(){
            return category;
        }

        public void setCategory(String newCategory){
            this.category = newCategory;
        }

        public Date getDate(){
            return date;
        }

        public void setDate(Date newDate){
            this.date = newDate;
        }
    }
}
