//Abhijeet Purkar
//apurkar

package hw2;

public class CaseReaderFactory {

	public CaseReader createReader(String filename)
	{
		CaseReader result = null;
		int len = filename.length();
		String type = filename.substring(len-3);   //Checking for the file type
		switch(type)
		{
		case "csv":
			result = new CSVCaseReader(filename);
			break;
		case "tsv":
			result = new TSVCaseReader(filename);
			break;
		default:
			break;	
		}
		return result;
		
	}

}
