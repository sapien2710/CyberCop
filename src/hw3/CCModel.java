//Abhijeet Purkar
//apurkar

package hw3;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;



public class CCModel {
	ObservableList<Case> caseList = null; //FXCollections.observableArrayList();	//a list of case objects
	ObservableMap<String, Case> caseMap = FXCollections.observableHashMap();		//map with caseNumber as key and Case as value
	ObservableMap<String, List<Case>> yearMap = FXCollections.observableHashMap();	//map with each year as a key and a list of all cases dated in that year as value. 
	ObservableList<String> yearList = null; //FXCollections.observableArrayList();			//list of years to populate the yearComboBox in ccView

	/** readCases() performs the following functions:
	 * It creates an instance of CaseReaderFactory, 
	 * invokes its createReader() method by passing the filename to it, 
	 * and invokes the caseReader's readCases() method. 
	 * The caseList returned by readCases() is sorted 
	 * in the order of caseDate for initial display in caseTableView. 
	 * Finally, it loads caseMap with cases in caseList. 
	 * This caseMap will be used to make sure that no duplicate cases are added to data
	 * @param filename
	 */
	void readCases(String filename) {
		
		
		CaseReaderFactory caseReaderFactory = new CaseReaderFactory();
		List<Case> caseList1 = caseReaderFactory.createReader(filename).readCases();
		caseList = FXCollections.observableArrayList(caseList1);
		Collections.sort(caseList);
		Iterator<Case> iter = caseList.iterator();
		while(iter.hasNext())
		{
			Case sample = (Case)iter.next();
			this.caseMap.put(sample.getCaseNumber(), sample);	
		}
	}

	/** buildYearMapAndList() performs the following functions:
	 * 1. It builds yearMap that will be used for analysis purposes in Cyber Cop 3.0
	 * 2. It creates yearList which will be used to populate yearComboBox in ccView
	 * Note that yearList can be created simply by using the keySet of yearMap.
	 */
	void buildYearMapAndList() {
		
		int caseListSize = caseList.size();
		List<String> repeatingYears = new ArrayList<>();
		
		for(int i=0; i<caseListSize; i++)
		{
			Case test = caseList.get(i);
			String year = test.caseDate.get().substring(0, 4);
			repeatingYears.add(year);
		}
		
		Set<String> yearSet = new HashSet<String>(repeatingYears);  //Getting unique years
		Iterator<String> yearSetIterator = yearSet.iterator();
		List<String> yearList1 = new ArrayList<>();
		
		while(yearSetIterator.hasNext())
		{
			String thisYear = yearSetIterator.next();
			List<Case> yearCases = new ArrayList<>();
 			
			for(int k=0; k<caseListSize; k++)
			{
				Case thisCase = caseList.get(k);
				String thisCaseYear = thisCase.caseDate.get().substring(0, 4).trim();
				
				if(thisYear.equalsIgnoreCase(thisCaseYear))
				{
					yearCases.add(thisCase);
				}
			}
			
			yearMap.put(thisYear, yearCases);
			yearList1.add(thisYear);
		}
		
		yearList = FXCollections.observableArrayList(yearList1);
		Collections.sort(yearList);			
	}

	/**searchCases() takes search criteria and 
	 * iterates through the caseList to find the matching cases. 
	 * It returns a list of matching cases.
	 */
	List<Case> searchCases(String title, String caseType, String year, String caseNumber) {
		
		List<Case> result = new ArrayList<>();
		
		for(int i=0; i<caseList.size(); i++)
		{
			Case thisCase = caseList.get(i);
			
			String thisTitle = thisCase.caseTitle.get().toLowerCase().trim();
			String thisCaseType = thisCase.caseType.get().toLowerCase().trim();
			String thisYear = thisCase.caseDate.get().substring(0, 4).toLowerCase().trim();
			String thisCaseNumber = thisCase.caseNumber.get().toLowerCase().trim();
			
			if((title != null) && (year != null) && (caseType != null) && (caseNumber != null)) 
			{if((thisTitle.contains(title.toLowerCase())) && (thisCaseType.contains(caseType.toLowerCase())) && (thisYear.contains(year.toLowerCase())) && (thisCaseNumber.contains(caseNumber.toLowerCase()))) {result.add(thisCase);}}
			
			if((title == null) && (year != null) && (caseType != null) && (caseNumber != null)) 
			{if((thisCaseType.contains(caseType.toLowerCase())) && (thisYear.contains(year.toLowerCase())) && (thisCaseNumber.contains(caseNumber.toLowerCase()))) {result.add(thisCase);}}
			
			if((title != null) && (year == null) && (caseType != null) && (caseNumber != null)) 
			{if((thisCaseType.contains(caseType.toLowerCase())) && (thisTitle.contains(title.toLowerCase())) && (thisCaseNumber.contains(caseNumber.toLowerCase()))) {result.add(thisCase);}}
			
			if((title != null) && (year != null) && (caseType == null) && (caseNumber != null)) 
			{if((thisYear.contains(year.toLowerCase())) && (thisTitle.contains(title.toLowerCase())) && (thisCaseNumber.contains(caseNumber.toLowerCase()))) {result.add(thisCase);}}
			
			if((title != null) && (year != null) && (caseType != null) && (caseNumber == null)) 
			{if((thisTitle.contains(title.toLowerCase())) && (thisCaseType.contains(caseType.toLowerCase())) && (thisYear.contains(year.toLowerCase()))) {result.add(thisCase);}} //5
			
			if((title == null) && (year == null) && (caseType != null) && (caseNumber != null)) 
			{if((thisCaseType.contains(caseType.toLowerCase())) && (thisCaseNumber.contains(caseNumber.toLowerCase()))) {result.add(thisCase);}}
			
			if((title != null) && (year == null) && (caseType == null) && (caseNumber != null)) 
			{if((thisTitle.contains(title.toLowerCase())) && (thisCaseNumber.contains(caseNumber.toLowerCase()))) {result.add(thisCase);}}
			
			if((title != null) && (year != null) && (caseType == null) && (caseNumber == null)) 
			{if((thisTitle.contains(title.toLowerCase())) && (thisYear.contains(year.toLowerCase()))) {result.add(thisCase);}}
			
			if((title == null) && (year != null) && (caseType != null) && (caseNumber == null)) 
			{if((thisCaseType.contains(caseType.toLowerCase())) && (thisYear.contains(year.toLowerCase()))) {result.add(thisCase);}}
			
			if((title == null) && (year != null) && (caseType == null) && (caseNumber != null)) 
			{if((thisYear.contains(year.toLowerCase())) && (thisCaseNumber.contains(caseNumber.toLowerCase()))) {result.add(thisCase);}}
			
			if((title != null) && (year == null) && (caseType != null) && (caseNumber == null)) 
			{if((thisTitle.contains(title.toLowerCase())) && (thisCaseType.contains(caseType.toLowerCase()))) {result.add(thisCase);}} //11
			
			if((title == null) && (year != null) && (caseType == null) && (caseNumber == null)) 
			{if((thisYear.contains(year.toLowerCase()))) {result.add(thisCase);}}
			
			if((title == null) && (year == null) && (caseType == null) && (caseNumber != null)) 
			{if((thisCaseNumber.contains(caseNumber.toLowerCase()))) {result.add(thisCase);}}
			
			if((title != null) && (year == null) && (caseType == null) && (caseNumber == null)) 
			{if((thisTitle.contains(title.toLowerCase()))) {result.add(thisCase);}}
			
			if((title == null) && (year == null) && (caseType != null) && (caseNumber == null)) 
			{if((thisCaseType.contains(caseType.toLowerCase()))) {result.add(thisCase);}}
			
			if((title == null) && (year == null) && (caseType == null) && (caseNumber == null)) 
			{{return caseList;}}
			
			}
	
		return result;
	}
	
	boolean writeCases(String filename)
	{
		File file = new File(CyberCop.DEFAULT_PATH + "/" + filename);
		try {
			PrintWriter writer = new PrintWriter(file);
			
			for (Case c : caseList) {
				String title = c.getCaseTitle();
				String number = c.getCaseNumber();
				String type = c.getCaseType();
				String notes = c.getCaseNotes();
				if(notes.length() == 0)  //adding whitespace if the field is empty
				{
					notes = " ";
				}
				String date = c.getCaseDate();
				String category = c.getCaseCategory();
				if(category.length() == 0)   //adding whitespace if the field is empty
				{
					category = " ";
				}
				String link = c.getCaseLink();
				if(link.length() == 0)  //adding whitespace if the field is empty
				{
					link = " ";
				}
				
                writer.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s", date, title, type, number, link, category, notes);
                writer.println();
            }
            
            writer.close();
            
        } 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
