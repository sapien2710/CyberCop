//Abhijeet Purkar
//apurkar

package hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TSVCaseReader extends CaseReader{

	TSVCaseReader(String filename) {
		super(filename);
	}

	@Override
	List<Case> readCases() {
		List<Case> caseList = new ArrayList<>();
		File file = new File(filename);
		StringBuilder sb = new StringBuilder();
		Scanner sc = null;
		try {
			sc = new Scanner(file);
			while(sc.hasNextLine()) {sb.append(sc.nextLine() + "\n");}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		
		String [] content = sb.toString().split("\n");
		int n = content.length;
		
		for(int i=0; i<(n); i++)
		{
			String [] g = content[i].split("	");
			Case c = new Case(g[0].trim(), g[1].trim(), g[2].trim(), g[3].trim(), g[4].trim(), g[5].trim(), g[6].trim());
			caseList.add(c);
		}
		
		return caseList;
	}
}
