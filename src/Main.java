import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public class Main {
	private static ArrayList<String> dateList = new ArrayList<String>();
	private static ArrayList<Boolean> matchList = new ArrayList<Boolean>();
	private static int sampleSize;
	private static int trialNumber;
	
	public static void main(String[] args) throws IOException {	
		sampleSize = recordSampleSize(); 
        trialNumber = recordTrialNumber();
		for(int j = 0; j < trialNumber; j++) {
			dateList.clear();
			for (int i = 0; i < sampleSize; i++) {
				GregorianCalendar gc = new GregorianCalendar();
				int year = randomBetween(2000, 2003);
				gc.set(GregorianCalendar.YEAR, year);
				int dayOfYear = randomBetween(1, gc.getActualMaximum(GregorianCalendar.DAY_OF_YEAR));
				gc.set(GregorianCalendar.DAY_OF_YEAR, dayOfYear);
				dateList.add(((gc.get(GregorianCalendar.MONTH) + 1) + "/" + gc.get(GregorianCalendar.DAY_OF_MONTH)));
			}
			checkMatch();
		}
		calculateProbability();
	}

    public static int randomBetween(int first, int last) {
        return (int)(Math.random()*((last - first) + 1) + first);
    }
    
    public static int recordSampleSize() throws IOException {
    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    	System.out.print("Welcome to the Random Date Generator. Enter the sample size: ");
    	int sampleSize = Integer.parseInt(reader.readLine());
    	return sampleSize; 
    }
    
    public static int recordTrialNumber() throws IOException {
    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    	System.out.print("Please enter the trial number: ");
    	int trialNumber = Integer.parseInt(reader.readLine());
    	return trialNumber; 
    }
    
    public static void checkMatch() {
    	Set<String> set = new HashSet<String>(dateList);
    	if(set.size()< dateList.size()) {
    		matchList.add(true);
    	}
    	else matchList.add(false);
    }
    
    public static void calculateProbability()  {
    	int trueAmount = 0; 
    	for(Boolean m: matchList) {
    		if(m)
    			trueAmount++;
    	}
    	System.out.println("The probability of matched date is: " + trueAmount + "/" + matchList.size());
    }
}