import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;


public class CsvGen{
	

	public static void main(String[] args) throws IOException{
		String name = "ListOfPeople";
		CsvGen csv = new CsvGen(name);
    }
	
	public CsvGen(String name) throws IOException {
		readFile(name);
	}
	
	public void readFile(String name) throws IOException {
		int fileCount = 0;
		int lineCount = 1;
		int peopleCount = 0;
		Vector<Integer> oldFiles = new Vector<Integer>();
		Vector<Integer> sumFiles = new Vector<Integer>();
		Vector<String> nameFiles = new Vector<String>();
		int oldAll = 0;
		int sumAll = 0;
		String nameAll = null;
		int tempSum = 0;
		boolean first = true;
		try {
		while(true) {
			first = true;
			File file = new File(name + (fileCount+1) +".txt"); 
			if (!file.exists()) {
			     break;
			  }
			fileCount++;
			  
			  BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(file));
			} catch (FileNotFoundException e1) {
				System.out.println("File not found.");
				e1.printStackTrace();
			} 
			  
			  String st; 
			  String tempName;
			  while ((st = br.readLine()) != null) {
				  if(first) {
					  nameFiles.add(fileCount-1,st);
					  st = br.readLine();
					  st = br.readLine();
					  oldFiles.add(fileCount-1,Integer.parseInt(st));
					  st = br.readLine();
					  tempSum += Integer.parseInt(st);
					  peopleCount++;
					  lineCount = 0;
					  first = false;
				  }
				  else {
					  if(lineCount % 1 == 0) {
						  tempName = st;
						  st = br.readLine();
						  st = br.readLine();
						  if(Integer.parseInt(st) > oldFiles.get(fileCount-1)) {
							  oldFiles.set(fileCount-1, Integer.parseInt(st));
							  nameFiles.set(fileCount-1,tempName);
						  }
						  st = br.readLine();
						  tempSum += Integer.parseInt(st);
						  peopleCount++;
						  lineCount = 0;
					  }
				  }
				  lineCount++;
			  }
			  sumFiles.add(fileCount-1,tempSum/peopleCount);
			  tempSum = 0;
			  peopleCount = 0;
		}
		for(int i = 0;i<oldFiles.size(); i++) {
			if(oldFiles.get(i) > oldAll) {
				oldAll = oldFiles.get(i);
				nameAll = nameFiles.get(i);
			}
		}
		
		for(int i = 0;i<sumFiles.size(); i++) {
			sumAll += sumFiles.get(i);
		}
		sumAll = sumAll/fileCount;
		writeCsv(name, oldFiles, sumFiles, nameFiles, sumAll, nameAll, fileCount);
		}catch (ArrayIndexOutOfBoundsException e1) {
			System.out.println(fileCount);
			System.out.println(oldFiles.size());
			e1.printStackTrace();
		}
	}
	
	public void writeCsv(String name, Vector<Integer> oldFiles, Vector<Integer> sumFiles, Vector<String> nameFiles, int sumAll, String nameAll, int fileCount) {
		try {
			PrintWriter pw = new PrintWriter(new File("csv.csv"));
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < fileCount; i++) {
				sb.append("Oldest "+(i+1)+" file");
				sb.append(';');
				sb.append(nameFiles.get(i));
				sb.append("\r\n");
				sb.append("Average "+(i+1)+" file");
				sb.append(';');
				sb.append(sumFiles.get(i));
				sb.append("\r\n");
			}
			sb.append("Oldest all");
			sb.append(';');
			sb.append(nameAll);
			sb.append("\r\n");
			sb.append("Average all");
			sb.append(';');
			sb.append(sumAll);
			sb.append("\r\n");
			
			pw.write(sb.toString());
			pw.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		}
	}
	
}

