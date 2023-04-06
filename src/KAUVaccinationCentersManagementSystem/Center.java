package KAUVaccinationCentersManagementSystem;


public class Center {
    
private int centerId;
private String centerName;
private Practitioner head;


//=============================================================================

public Center(){
    head=null;
    }

public Center(int centerId, Practitioner head){
      this.centerId=centerId;
      this.head=head;
  }
  
//=============================================================================
  
//a function that checks if the linkedlist is empty
public boolean isEmpty(){
    return head==null;
    }

//=============================================================================

//a function to add a practitioner to the specified center (at the end of the linkedlist)
public void addPractitioner(Practitioner pract){
  Practitioner helptr=head;
  if(head==null){
      head=pract;
     }
  else{
  while(helptr.getNext()!=null){
      helptr=helptr.getNext();
        }
  helptr.setNext(pract);
    }
}

//=============================================================================  
    
//a function to search for the practitioner based on his id.
public Practitioner idSearch(String id){
    Practitioner helptr = head;

        if (!isEmpty()) {
          while (helptr != null) {
            if (helptr.getParciId().equalsIgnoreCase(id)) {
                return helptr;
                }
            helptr = helptr.getNext();
            }
        }
        return null;
    }   

//=============================================================================

//a function that removes the left practitioner for specified center (linked list) into a new linked list that represents the left practitioners. 
public Practitioner deletePractitionersBasedOnStatus(String status){
    Practitioner helptr = head;

        while (helptr != null) {
            if (helptr.getStatus().equalsIgnoreCase("Left")) {
                return helptr;
            }
            else {
            helptr = helptr.getNext();
            }
        }
    return null;
    }

//=============================================================================

//a function that deletes the node from the linked list based on the practitioner id.
public void deletePractitionerById(String parcId){
Practitioner helptr=head;

          while (helptr != null) {
            if (helptr.getParciId().equalsIgnoreCase(parcId)) {
                 helptr.setNext(helptr.getNext().getNext());
                 
            }
            helptr = helptr.getNext();
        }
    }

//=============================================================================

//Getters and Setters
public int getCenterId(){
    return centerId;
    }
public void setCenterId(int centerId){
       this.centerId=centerId;
   }
public int getCenter(){
       return centerId;
   }
public void setCenterName(String centerName){
       this.centerName=centerName;
   }
public String getCenterName(){
       return centerName;
   }
public void setHead(Practitioner head){
       this.head=head;
   }
public Practitioner getHead(){
       return head;
   }
   
}