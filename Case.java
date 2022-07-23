//Abhijeet Purkar
//apurkar

package hw3;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Case implements Comparable<Case> {
	
	StringProperty caseDate = new SimpleStringProperty();
	StringProperty caseTitle = new SimpleStringProperty();
	StringProperty caseType = new SimpleStringProperty();
	StringProperty caseNumber = new SimpleStringProperty();
	StringProperty caseLink = new SimpleStringProperty();
	StringProperty caseCategory = new SimpleStringProperty();
	StringProperty caseNotes = new SimpleStringProperty();
	
	public Case(String caseDate, String caseTitle, String caseType, String caseNumber, String caseLink, String caseCategory, String caseNotes)
	{
		this.caseDate.set(caseDate);
		this.caseTitle.set(caseTitle);
		this.caseType.set(caseType);
		this.caseNumber.set(caseNumber);
		this.caseLink.set(caseLink);
		this.caseCategory.set(caseCategory);
		this.caseNotes.set(caseNotes);
	}
	
	//caseDate
	public String getCaseDate() { return caseDate.get(); }
	public void setCaseDate(String date) { caseDate.setValue(date); }
	public StringProperty caseDateProperty(){ return caseDate; }
	
	//caseTitle
	
	public String getCaseTitle() { return caseTitle.get(); }
	public void setCaseTitle(String Title) { caseTitle.setValue(Title); }
	public StringProperty caseTitleProperty() { return caseTitle; }
	
	//caseType
	
	public String getCaseType() {return caseType.get(); }
	public void setCaseType(String Type) {caseType.setValue(Type); }
	public StringProperty caseTypeProperty() { return caseType; }
	
	//caseNumber
	
	public String getCaseNumber() { return caseNumber.get(); }
	public void setCaseNumber(String Number) { caseNumber.setValue(Number); }
	public StringProperty caseNumberProperty() { 	return caseNumber; }
	
	//caseLink
	
	public String getCaseLink() { return caseLink.get(); }
	public void setCaseLink(String Link) { caseLink.setValue(Link); }
    public StringProperty caseLinkProperty() { return caseLink; }
	
	//caseCategory
	
	public String getCaseCategory() { return caseCategory.get(); }
	public void setCaseCategory(String Category) { caseCategory.setValue(Category); }
	public StringProperty caseCategoryProperty() { return caseCategory; }
	
	//caseNotes
	
	public String getCaseNotes() {return caseNotes.get(); }
	public void setCaseNotes(String Notes) {caseNotes.setValue(Notes); }
	public StringProperty caseNotesProperty() {return caseNotes; }

	
	
	
	@Override
	public int compareTo(Case o) {
		// TODO Auto-generated method stub
		return (this.getCaseDate().compareTo(o.getCaseDate()));
	}
	

}
