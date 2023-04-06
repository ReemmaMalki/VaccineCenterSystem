/*
 * Program : King Abdulaziz University Vaccination Centers Management System
 */

package KAUVaccinationCentersManagementSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MainProgram {
    //creat array list of center to store all centers
    //creat array to store sizes of LL
   static int []arrayNo=null;
   static ArrayList<Center> allCenters = new ArrayList<>();
   
    public static void main(String[] args) throws Exception {
        //Scanners to read from both inputs and show a messege in case the file wasn't found
        File inputfile =new File("commands.txt");
        Scanner input=new Scanner(inputfile);
        
        if(!inputfile.exists()){
            System.out.println("The File: "+inputfile.getName()+" does not exist");
            System.exit(0);}
        
        File inputfile2 =new File("intialInformation.txt");
        Scanner input2=new Scanner(inputfile2);
        
        if(!inputfile2.exists()){
            System.out.println("The File: "+inputfile2.getName()+" does not exist");
            System.exit(0);}
        
        //printwriter to print in txt file
        File outputfile=new File("output2.txt");
        PrintWriter output=new PrintWriter(outputfile);
        
        String command;
        int v=0;
        String[]arraySt=null;
        Center center=null;
         
         //start loop to check the commands from the inputs
        do{
            //start taking the command from the input file
            command=input.next();
            
    //**********************************************************************************************************
        
           //check command 1
           if(command.equalsIgnoreCase("STARTUP")){
               output.print("       Welcome to the KAU Vaccination Centers Management System\n" +
                                  "       ---------------------------------------------------------\n" +
                                  "The vaccination centers are:");
               
               
               arrayNo=new int[input2.nextInt()]; //set the size for the centers capicity array
               arraySt=new String[arrayNo.length]; //set the size for the centers names array
               startupCom(input,input2,center,arraySt,output);
             output.flush(); 
           }
           
    //***********************************************************************************************************
    
    
          //display all the centers available with all pratitioners
          if(command.equalsIgnoreCase("DISPLAY_ALL_CENTERS")){
               output.println("\n\nThe first distribution for health practitioners among the vaccination centers \n" +
                                  "===================================================================================================\n");
          
               displayAllCenters(output);
               output.println("===================================================================================================");     
           output.flush();    
        }   
          
    //*********************************************************************************************************
    
           //display the number of practitioner in each number which has already been stored in arrayNo
           if(command.equalsIgnoreCase("NUM_PRACTIONERS")){
               command=input.next();
               output.print("\nNumber of practitioners in center "+command+": "+arrayNo[Integer.valueOf(command)-1]+"\n===================================================================================================\n");
                  
               output.flush();} 
       
    //*********************************************************************************************************  
               
          //display the practitioner of a specific center
           if(command.equalsIgnoreCase("DISPLAY")){
               command=input.next();
               int value= Integer.valueOf(command); //since i took the input as a string, i will convert it to integer to be able to print
               output.print("\n\n	The practitioners of center "+command +" are\n" +
"        -------------------------------------------------\n\n      			 "+arraySt[value-1].replaceAll("[-_]"," ")+"\n\n-----------------------------------------------------\n");
              display(value,output);
              output.print("=======================================================\n");
    output.flush();
           }
           
    //*********************************************************************************************************
    
           //display all practitioner of all centers based on thier status
           if(command.equalsIgnoreCase("DISPLAY_ALL_BASED_ON_STATUS")){
               command=input.next();
               DABOS(command,output);
               
             output.flush();  
           }
                   
    //*********************************************************************************************************
    
           // display practitioner of the same specefied status
           if(command.equalsIgnoreCase("DISPLAY_BASED_ON_STATUS")){
               String status=input.next(),centerNo=input.next();
               int value=Integer.valueOf(centerNo);
               if(!checkStatusInCenter(arraySt,status,value)){ //check if any practiotioner with the specefied status is available or not if yes print header
                   output.print("\nNot found any practitioners of the status "+status+" in center "+value+
                                     "\n===================================================================================================\n");
               }
               else{ 
                    output.print("\n	The practitioners of status Exist in center "+ value+" are"
                       + "    \n        -------------------------------------------------\n\n      			 "+
                       arraySt[value-1].replaceAll("[-_]", " ")+"\n\n-----------------------------------------------------\n");
               Practitioner helptr=allCenters.get(value-1).getHead();
                    while(helptr!=null){
                   if(helptr.getStatus().equals(status)){
                       output.print("		"+helptr.toString()+"\n");
           }
                   else{
                       continue;
                   }
                   helptr=helptr.getNext();
               }
                output.print("=======================================================\n");  

               }
               output.flush();
       }
            
           
    //*********************************************************************************************************
           
           //change the status of a giving practitioner to "left"
           if(command.equalsIgnoreCase("LEAVE_THE_JOB")){
               command=input.next();
               leaveTheJob( command,arraySt, output);
           }
           
    //*********************************************************************************************************
           
           //remove any practitioner with status left from the center and display the rest
           if(command.equalsIgnoreCase("REMOVE_ALL_LEFT_PRACTITIONERS")){
               removeAllLeftPract(output);
           }
           
    //*********************************************************************************************************
           
           //remove practitioner from center to another
           if(command.equalsIgnoreCase("MOVE")){
               command=input.next();
               int newCenter=input.nextInt();
               move(command,newCenter,output);
           }
           
    //*********************************************************************************************************
           
    
           if(command.equalsIgnoreCase("DELETE_CENTER")){
               command=input.next();
               deleteCenter( command, output);
           }
           
     //********************************************************************************************************
          
           //merge all the left pract from different centers together in one center
           if(command.equalsIgnoreCase("MERGE")){
               merge(output);
           }
           
    //*********************************************************************************************************
    
           //quit the program and close it
           if(command.equalsIgnoreCase("QUIT")){
                 output.println("\n\n"
                                  + "			-------------------------------------\n" +
                                    "	   	       |	        Good Bye                 |\n" +
                                    "                        -------------------------------------\n\n\n\n"); 
               output.flush();
               output.close();
               System.exit(0);
           }
           
    //*********************************************************************************************************
     
        }
        //end of do while loop
        while(input.hasNext() || input2.hasNext());
        //flush the output and close it
        output.flush();
        output.close();
    }
    
//=================================================================================================================================================================================
    
    //method to evaluate startup command
    public static void startupCom(Scanner input,Scanner input2,Center center,String[]arraySt,PrintWriter output){
        String status=null;
        
               
           //fill the array with the sizes of each center
           for(int i=1; i<=arrayNo.length;i++){
               
               arrayNo[i-1]=input2.nextInt();
               center=new Center();
               center.setCenterId(i);
               allCenters.add(center);
           }
               
              
        //fill the array with the names of the centers        
           for(int i=0;i<arraySt.length;i++){
               arraySt[i]=input2.next();
               allCenters.get(i).setCenterName(arraySt[i].replaceAll("[-_]"," "));
               //print the names of the centers
               output.print("\n   "+arraySt[i].replaceAll("[-_]"," "));
           }
           //fill the centers with the practitioners
           for(int i=0;i<arrayNo.length;i++){
                for( int j=0; j<arrayNo[i];j++){
                   status= "Exist";
                   Practitioner pract=new Practitioner(input2.next(),input2.next(),input2.next(),(i+1) ,status);
                   allCenters.get(i).addPractitioner(pract);
               }
                
                    
                }
           
           output.flush();
    }
    
//=================================================================================================================================================================================    
    
    public static void display(int value,PrintWriter output){
        
        Practitioner helptr=allCenters.get(value-1).getHead();
        while(helptr !=null){
            output.print("		"+helptr.toString()+" ");
            output.println(" ");
            helptr=helptr.getNext();
        }
        output.flush();
    }
     
//=================================================================================================================================================================================

    
// find the largest capisity to be able to print and ckeck the center cap times  
    public static int largestNode(){
        int largestNum=0;
        for(int i=0;i<arrayNo.length;i++){
            if(arrayNo[i]>largestNum){
                largestNum=arrayNo[i];
            }
            
        }
        return largestNum;
        
    }
    
//=================================================================================================================================================================================
    
    
    public static void displayAllCenters(PrintWriter output){
        for(int i=0;i<allCenters.size();i++){
            
              //print the center name
               output.print("\t"+allCenters.get(i).getCenterName()+"\t");
           }
               output.println("\n\n--------------------------------------------------------------------------------------------------");
          
               Practitioner helptr=null ;
               int cap= largestNode();
               
               //print all pract based on their centers
              for(int i=0;i<cap;i++){
                for(int j=0;j<allCenters.size();j++){
                int counter=0;
                 helptr= allCenters.get(j).getHead();
                 while(counter!=i){
                     if(helptr!=null){
                     helptr=helptr.getNext();
                     
                 }counter++;
                
                 }
                if(helptr!=null){
                    output.printf("%-31s",helptr.toString());
                }  
                else output.printf("%35s  "," ");
            }
            output.println(" ");
        }
             output.flush(); 
    }
    

//=================================================================================================================================================================================
    
    //i used this method to be able to print correctly so if it returned true it will print header
    //else it will print the not found statment
    public static boolean checkStatus(String status){
       
        for(int i=0;i<allCenters.size();i++){
             Practitioner helptr= allCenters.get(i).getHead();
            for(int j=0;j<arrayNo[i];j++){
                while(helptr!=null){
                    return helptr.getStatus().equals(status);
                }
            }
        }
   return false; }

//=================================================================================================================================================================================
    
    //this method display all practitioner based on thier center and show their status
    public static void DABOS(String command,PrintWriter output){
            if(!checkStatus(command)){
                   output.print("\nNot found any practitioners of the status "+command+"\n===================================================================================================\n");
                   
               }
               else{
                   output.println("\n	The practitioners of status "+command+" are"+
                           "\n        -------------------------------------\n");
                   for(int i=0;i<allCenters.size();i++){
               output.print("\t"+allCenters.get(i).getCenterName()+"\t");
           }
               output.println("\n\n--------------------------------------------------------------------------------------------------");
          
               Practitioner helptr=null ;
               int cap= largestNode();
                    for(int i=0;i<cap;i++){
                     for(int j=0;j<allCenters.size();j++){
                     int counter=0;
                     helptr= allCenters.get(j).getHead();
                     while(counter!=i){
                     if(helptr!=null && helptr.getStatus().equalsIgnoreCase(command)){
                     helptr=helptr.getNext();
                     
                 }
                     counter++;
                
                 }
                if(helptr!=null){
                    output.printf("%-31s",helptr.toString());
                }  
                
            }
            output.println(" ");
        }
        output.print("===================================================================================================\n");
      
               }
        output.flush();
    }
    
//=================================================================================================================================================================================
    
    
    public static boolean checkStatusInCenter(String []arraySt,String status,int value){
       Practitioner helptr=allCenters.get(value-1).getHead();
       for(int i=0; i<arrayNo[value-1];i++){
        while(helptr!=null){
           return helptr.getStatus().equals(status);
       }
         
    }
    return false;  
  }
    
//=================================================================================================================================================================================
    
    public static void move(String command,int newCenter,PrintWriter output){
               int i=0;
               for( i=0;i<allCenters.size();i++){
                   Practitioner helptr=allCenters.get(i).getHead();
                   
                   for(int j=0;j<arrayNo[i];j++){
                       if(helptr.getParciId().equalsIgnoreCase(command)){
                           helptr.setCenter(newCenter);
                           helptr.setStatus("Moved");
                           allCenters.get(newCenter-1).addPractitioner(helptr);
                           //allCenters.get(i).deletePractitionersBasedOnStatus("Moved");

                           if(helptr.getNext()!=null&& helptr.getStatus().equalsIgnoreCase("moved")){
                               helptr.setNext(helptr.getNext().getNext());
                           }
                           else{
                               helptr.setNext(null);
                           }
                       }
                       else{
                           helptr=helptr.getNext();
                       }
                   }
               }
               output.println("\n	The Practitioner of id "+command+" is moved to center "+newCenter+
                       "\n	------------------------------------------------");
               displayAllCenters(output);
               output.println("\n===================================================================================================");     
               output.flush();
    }
    

//=================================================================================================================================================================================
    
    public static void merge(PrintWriter output){
        Center newCenter = new Center();

            for (int i = 0; i < allCenters.size(); i++) {
            Practitioner helptr = allCenters.get(i).getHead();

            while (helptr != null && !helptr.getStatus().equalsIgnoreCase("left")) {
                Practitioner pract = new Practitioner(helptr.getParciId(), helptr.getFName(), helptr.getLName(), helptr.getCenter(),helptr.getStatus());
                newCenter.addPractitioner(pract);
                output.print("		"+helptr.toString()+" ");
                 output.println(" ");
                helptr = helptr.getNext();
            }
            
        }
               output.println("\n			The remaing centers ar merged\n\n--------------------------------------------------------------------------------------------------");
                                    
              
               output.println("===================================================================================================");     
       output.flush();
    }
    
    
//=================================================================================================================================================================================
    
    
    public static void deleteCenter(String command,PrintWriter output){
        int deletedCen = Integer.valueOf(command);

        boolean deleted = false;

        for (int i = 0; i < allCenters.size(); i++) {
            if (allCenters.get(i).getCenterId() == deletedCen) {
                allCenters.remove(i);
                deleted = true;
            }
        }
        if (deleted == true) {
            output.println("\n  			Center " + deletedCen + " is Closed\n" +
                    "===================================================================================================");
        }
          output.flush();
    }
 
    
//=================================================================================================================================================================================
    
    
    public static void removeAllLeftPract(PrintWriter output){
        output.println(" 	All left Practitioners are moved to new linked list\n" +
"	---------------------------------------------------\n");
               for(int i=0;i<allCenters.size();i++){
                  Practitioner helptr=allCenters.get(i).getHead();
                   for(int j=0;j<arrayNo[i];j++){
                       if(helptr.getStatus().equalsIgnoreCase("Left")){
                           output.println("   "+helptr.toString());
                           
                       }
                   }
               }
               output.println("        The remaining practitioners After remove the practitioners of status left\n" +
                        "	------------------------------------------------------------------------");
               for(int i=0;i<allCenters.size();i++){
               output.print("\t"+allCenters.get(i).getCenterName()+"\t");
           }
               output.println("\n\n--------------------------------------------------------------------------------------------------");
          
               Practitioner helptr=null ;
               int cap= largestNode();
               
        for(int i=0;i<cap;i++){
            for(int j=0;j<allCenters.size();j++){
                int counter=0;
                 helptr= allCenters.get(j).getHead();
                 while(counter!=i){
                     if(helptr!=null){
                     helptr=helptr.getNext();
                     
                 }counter++;
                
                 }
                if(helptr!=null){
                    if(helptr.getStatus().equalsIgnoreCase("Left")) {
                        output.printf("%35s"," "); 
                        continue;
                    
                    }
                    else if(!helptr.getStatus().equalsIgnoreCase("Left")){
                    output.printf("%-31s",helptr.toString());}
                }  
              //  else output.printf("%35s  "," ");
            }
            output.println(" ");
        }
               output.println("\n===================================================================================================");
             output.flush(); 
    }
    
//=================================================================================================================================================================================
    
    public static void leaveTheJob(String command,String[]arraySt,PrintWriter output){
        int index=0,i=0;
               //Practitioner theOne=null;
               for( i=0;i<allCenters.size();i++){
                   Practitioner helptr=allCenters.get(i).getHead();
                   
                   for(int j=0;j<arrayNo[i];j++){
                       if(helptr.getParciId().equalsIgnoreCase(command)){
                           helptr.setStatus("Left");
                           index=i;
                           break;
                       }
                       else{
                       helptr=helptr.getNext(); 
                       }
                   }
               }
               output.println("\nThe practitioner of id "+command+" is Left");
              
              
               output.print("\n\n	The practitioners of center "+(index+1) +" are\n" +
"        -------------------------------------------------\n\n      			 "+arraySt[index+1].replaceAll("[-_]"," ")+
                       "\n\n-----------------------------------------------------\n");
              display((index+1),output);
              output.print("=======================================================\n");
   output.flush();
    }
}
