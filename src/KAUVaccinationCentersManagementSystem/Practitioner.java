package KAUVaccinationCentersManagementSystem;

public class Practitioner {
    
   private int center;
   public String fName, lName, status,parciId; 
   private Practitioner next;
   
   
//=============================================================================
   
   public Practitioner(){
       
   }
   public Practitioner(String id,String fn,String ln, int ce,String status){
       this(id,fn,ln,ce,status, null);
   }
   
   public Practitioner(String parciId,String fName,String lName, int center,String status,Practitioner next){
       this.parciId=parciId;
        this.fName=fName;
        this.lName=lName;
        this.status=status;
        this.center=center;
        this.next=next;
   }
   
//=============================================================================

//Getters and Setters   
   public void setCenter(int center){
       this.center=center;
   }
   public int getCenter(){
       return center;
   }
   public void setStatus(String status){
       this.status=status;
   }
   public String getStatus(){
       return status;
   }
   public void setNext(Practitioner next){
       this.next=next;
   }
   public Practitioner getNext(){
               return next;
    }
   public String getParciId(){
       return parciId;
   }
   public void setParciId(String parciId){
       this.parciId=parciId;
   }
   public String getFName(){
       return fName;
   }
   public void setFName(String fName){
       this.fName=fName;
   }
   public String getLName(){
       return lName;
   }
   public void setLName(String lName){
       this.lName=lName;
   }
   public String toString(){
       return parciId+" "+fName+" "+lName+" , "+status;
   }
   
}
