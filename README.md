# King Abdulaziz University Vaccination Centers Management System

## Background

At KAU, there are many distributed vaccination centers with different capacity. The capacity of these centers is different, where the
distribution of health practitioners is based on the capcity of each center. 
The responsible management of vaccine determine the number of existing centers at KAU and the capacity of each center.
The Practitioners can move to aonther center or to leave the center. Your task is to write a program to do the task of distribution of
health practitioners over the available centers on behalf of the Vaccination management.


## Algorithm

* create class practitioner for the nodes
* create class center for the linked list
* create an array of centres with size of number of the centres 
* fill the center array with values
* take input the number of the center that want to be displayed 
* search for the center that was asked to be displayed 
* if not found, keep looking array.length times.
* if found, create help pointer from class practitioner that has the value of the head, so the head wouldn’t be lost.
* start displaying the practitioner information for all practitioner in that center until it finis (==null).
* create each after displaying the information, let help pointer point at the next node.
* once the right center found and the info displayed, break out of the loop so that it doesn’t take more time since the right center has been found already.
