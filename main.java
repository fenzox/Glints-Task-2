package glint2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        String[] temp = {};
        int N;
        try {
        	// TO-DO change file path
            File file = new File("C:\\Users\\gaura\\Documents\\Chegg\\Rachel\\preference.txt");
            @SuppressWarnings("resource")
			Scanner fileReader = new Scanner(file);
            String line;
          //Map to store mapping from job and candidate id to index
            Map<String, Integer> map = new HashMap<>(); 
            int i = 0;

            //Read the whole file line by line
            while (fileReader.hasNextLine()) {
                line = fileReader.nextLine();
                if(line.trim().isEmpty()) {
                    //If the line is empty continue the loop
                    continue;
                }
              //Map the job or candidate id i.e. ci or ji to i
                map.put(line.split(":")[0], i);     
              //Number of preferences, that will denote total number of candidates and jobs
                temp = line.split(":")[1].trim().split(" ");   
              //Increment the id assigned for ci and ji so that we have unique assignments
                i++;    
            }
          //N is number of candidates and jobs
            N = temp.length;    
            fileReader.close();
            fileReader = new Scanner(file);

            //2D array to hold preference list of candidates and jobs
            int[][] preference = new int[N+N][N];
            while (fileReader.hasNextLine()) {
                line = fileReader.nextLine();

                //Process only if line is not empty
                if(!line.trim().isEmpty()) {
                	//Find index stored for ci or ji in the map
                    int index = map.get(line.split(":")[0]);    
                  //Preference list of ci or ji
                    temp = line.split(":")[1].trim().split(" ");    
                    //Loop to store preference list in 2D array
                    for(int j = 0; j < N; j++) {
                        preference[index][j] = map.get(temp[j]);
                    }
                }
            }

            //Call jobMatching to find a stable matching of candidates to jobs
            jobMatching(preference, N);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }
    }

    public static void jobMatching(int[][] preference, int N) {
        //Storing mapping of candidate to job
        int[] candidates = new int[N];

        //Boolean array for availability of jobs
        boolean[] freeJob = new boolean[N];

        //Initialize candidates to job mapping to -1
        //As there will be no matches initially
        for(int i=0 ; i < N; i++)
            candidates[i] = -1;

        int count = N;      //Variable to denote count of free jobs

        //Loop until there are unassigned jobs
        while (count > 0) {
            int freeJobIndex;
            //Find the first index of job that is not assigned yet
            for (freeJobIndex = 0; freeJobIndex < N; freeJobIndex++) {
                if (!freeJob[freeJobIndex]) {
                    break;
                }
            }

            //Loop over preference list of candidate for the job picked above
            for (int i = 0; i < N && !freeJob[freeJobIndex]; i++) {
                int candidate = preference[freeJobIndex][i];

                //If preferred candidate is free, assign job to candidate
                if (candidates[candidate - N] == -1) {
                	 //Match candidate to free job
                    candidates[candidate - N] = freeJobIndex;  
                    //Mark job as assigned
                    freeJob[freeJobIndex] = true;  
                  //Number of unassigned jobs decrease after assignment
                    count--;        
                } else {
                    //Find current job for preferred candidate
                    int job1 = candidates[candidate - N];

                    //Find if current job is preferred over new free job picked
                    boolean preferedJob = false;
                    for (int j = 0; j < N; j++) {
                        if (preference[candidate][j] == job1) {
                            preferedJob = true;
                            break;
                        } if (preference[candidate][j] == freeJobIndex) {
                            break;
                        }
                    }

                    //If the new free job picked is preferred over old job
                    //Then match new job to candidate and mark old job as free
                    if(!preferedJob) {
                        candidates[candidate - N] = freeJobIndex;
                        freeJob[freeJobIndex] = true;
                        freeJob[job1] = false;
                    }
                }
            }
        }

        //Print the stable match found for candidates and jobs
        for (int i = 0; i < N; i++) {
            System.out.println("c" + (i + 1) + "  j" + (candidates[i] + 1));
        }
    }
}


