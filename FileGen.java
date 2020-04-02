import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class FileGen{
	public static void main(String[] args) throws IOException
	{	
		String name = "ListOfPeople";
		FileGen files = new FileGen(4,name);
		files.writeNames(name);
	}
	
	
	public FileGen(int num, String name) {
		for(int i = 0;i<num;i++)
		{
			try {
			      File myObj = new File(name + (i+1) + ".txt");
			      if (myObj.createNewFile()) {
			        System.out.println("File created: " + myObj.getName());
			      } else {
			        System.out.println("File already exists.");
			      }
			    } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
		}
	}
	
	public String randName() throws IOException
	{
		String fileName = "names.txt";
		ArrayList<String> allNames = new ArrayList<String>();
		Random r = new Random();

		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		} 
		while (in.ready()) { 
		     allNames.add( in.readLine() );
		}
		in.close();

		String randomName = allNames.get(r.nextInt(allNames.size()));
		
		return randomName;
	}
	
	public String randSurname() throws IOException
	{
		String fileName = "surnames.txt";
		ArrayList<String> allSurnames = new ArrayList<String>();
		Random r = new Random();

		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		} 
		while (in.ready()) { 
		     allSurnames.add( in.readLine() );
		}
		in.close();

		String randomSurname = allSurnames.get(r.nextInt(allSurnames.size()));
		
		return randomSurname;
	}
	
	public static int randAge()
	{
		return (int)(Math.random() * ((65 - 18) + 1)) + 18;

	}
	
	public static int randSalary()
	{
		Random r = new Random();
		int x = r.ints(1500, (5000)).limit(1).findFirst().getAsInt();
		
		return x;
	}
	
	public void writeNames(String name) {
		BufferedWriter bw = null;
		int counter = 1;
		while(true) {
		      try {
			 File file = new File(name+counter+".txt");
			 counter++;
	
			  if (!file.exists()) {
			     break;
			  }
	
			  FileWriter fw = new FileWriter(file, true);
			  String line;
			  bw = new BufferedWriter(fw);
			  for(int i = 0;i<10;i++) {
				  bw.write(randName());
				  bw.newLine();
				  bw.write(randSurname());
				  bw.newLine();
				  line = Integer.toString(randAge());
				  bw.write(line);
				  bw.newLine();
				  line = Integer.toString(randSalary());
				  bw.write(line);
				  bw.newLine();
			  }
	
		      } catch (IOException ioe) {
			   ioe.printStackTrace();
			}
			finally
			{ 
			   try{
			      if(bw!=null)
				 bw.close();
			   }catch(Exception ex){
			       System.out.println("Error in closing the BufferedWriter"+ex);
			    }
			}
		}
	}
	
	
}