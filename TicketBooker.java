import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class TicketBooker{
  static int  availableLowerBerths=1;
  static int  availableUpperBerths=1;
  static int  availableMiddleBerths=1;
  static int  availableRacTickets=1;
  static int  availableWaitingList=1;

  static Queue<Integer> racList= new LinkedList<>();// stores id of raclist passengers
  static Queue<Integer> waitingList= new LinkedList<>();// stores id of waiting list passengers
  static Queue<Integer> bookedTicketList= new LinkedList<>();// stores id of booked passengers
  
  static List<Integer> lowerBerthsPositions = new ArrayList<>(Arrays.asList(1));
  static List<Integer> upperBerthsPositions = new ArrayList<>(Arrays.asList(1));
  static List<Integer> middleBerthsPositions = new ArrayList<>(Arrays.asList(1));
  static List<Integer> racPositions = new ArrayList<>(Arrays.asList(1));
  static List<Integer> waitingListPositions = new ArrayList<>(Arrays.asList(1));

  static Map<Integer,Passenger> passengers= new HashMap<>();//map of passenger ids to passengers

  public void bookTicket(Passenger p, int berthInfo, String alottedBerth){
    p.number=berthInfo;
    p.alotted=alottedBerth;
    passengers.put(p.passengerId, p);
    bookedTicketList.add(p.passengerId);
    System.out.println("_________________Booked Successfully________________");
  }
  public void addToRac(Passenger p, int racInfo, String alottedRac){
     p.number=racInfo;
     p.alotted=alottedRac;
     passengers.put(p.passengerId, p);
     racList.add(p.passengerId);
     availableRacTickets--;
     racPositions.remove(0);
     System.out.println("_______________Added to RAC List_______________");
  }
  public void addToWaitingList(Passenger p, int waitingListInfo, String alottedWL){
    p.number=waitingListInfo;
    p.alotted=alottedWL;
    passengers.put(p.passengerId, p);
    racList.add(p.passengerId);
    availableWaitingList--;
    waitingListPositions.remove(0);
    System.out.println("_______________Added to Waiting List_______________");
 }
 public void cancelTicket(int passengerId){
  Passenger p = passengers.get(passengerId);
  passengers.remove(Integer.valueOf(passengerId));
  int positionBooked= p.number;
  bookedTicketList.remove(Integer.valueOf(passengerId));
  System.out.println("________________Ticket cancelled successfully__________________");

  if(p.alotted.equals("L")){
    availableLowerBerths++;
    lowerBerthsPositions.add(positionBooked);
  }
  else if(p.alotted.equals("M")){
    availableMiddleBerths++;
    middleBerthsPositions.add(positionBooked);
  }
  else if(p.alotted.equals("U")){
    availableUpperBerths++;
    upperBerthsPositions.add(positionBooked);
  }
  // check if rac is available, then move rac into bookedlist
  if(racList.size()>0){
    Passenger passengerInRac = passengers.get(racList.poll());
    racList.remove(Integer.valueOf(passengerInRac.passengerId));
    int positionrac= passengerInRac.number;
    availableRacTickets++;
    racPositions.add(positionrac);
  

  if(waitingList.size()> 0){
    Passenger passengerInWaitingList = passengers.get(waitingList.poll());
    int positionWL = passengerInWaitingList.number;
    waitingListPositions.add(positionWL);
    waitingList.remove(Integer.valueOf(passengerInWaitingList.passengerId));
    
    // move waiting list to rac
    passengerInWaitingList.number= racPositions.get(0);
    passengerInWaitingList.alotted="RAC";
    racPositions.remove(0);
    racList.add(passengerInWaitingList.passengerId);
   
    availableWaitingList++;
    availableRacTickets--;

  }
  Main.bookTicket(passengerInRac);
 }
 
}

  public void printAvailable(){
    System.out.println("Available lower berths: " + availableLowerBerths);
    System.out.println("Available middle berths: " + availableMiddleBerths);
    System.out.println("Available upper berths: " + availableUpperBerths);
    System.out.println("Available RAC tickets: " + availableRacTickets);
    System.out.println("Available waiting List: " + availableWaitingList);
  }
  public void printPassengers(){
    if(passengers.size() == 0){
      System.out.println("No details of passengers");
    }
    
      for(Passenger p : passengers.values()){
        System.out.println("Passenger ID: " + p.passengerId);
        System.out.println("Name : " + p.name);
        System.out.println("Age : " + p.age);
        System.out.println("Status : " + p.number + p.alotted);
        System.out.println("-------------------------------");
      
    }
  }
}