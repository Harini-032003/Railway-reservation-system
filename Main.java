
import java.util.*;
public class Main
{
  public static void bookTicket(Passenger p){
    TicketBooker booker=new TicketBooker();
    if(TicketBooker.availableWaitingList == 0){
      System.out.println("No available tickets");
      return;
    }
    if((p.berthPreference.equals("L") && TicketBooker.availableLowerBerths > 0 ) ||( p.berthPreference.equals("U") && TicketBooker.availableUpperBerths > 0 ) || (p.berthPreference.equals("M") && TicketBooker.availableMiddleBerths > 0 )){
        // preferred berth available
         System.out.println("Preferred Berth available");
         if(p.berthPreference.equals("L")){
            System.out.println("Lower Berth given");
            booker.bookTicket(p, TicketBooker.lowerBerthsPositions.get(0),"L");
            TicketBooker.lowerBerthsPositions.remove(0);
            TicketBooker.availableLowerBerths--;
         }
          else if(p.berthPreference.equals("M")){
          System.out.println("Middle Berth given");
          booker.bookTicket(p, TicketBooker.middleBerthsPositions.get(0),"M");
          TicketBooker.middleBerthsPositions.remove(0);
          TicketBooker.availableMiddleBerths--;
       }
        else if(p.berthPreference.equals("U")){
        System.out.println("Upper Berth given");
        booker.bookTicket(p, TicketBooker.upperBerthsPositions.get(0),"U");
        TicketBooker.upperBerthsPositions.remove(0);
        TicketBooker.availableUpperBerths--;
     }
    }
    // if preferred berth not available
    else if(TicketBooker.availableLowerBerths>0){
      System.out.println("Lower Berth given");
      booker.bookTicket(p,TicketBooker.lowerBerthsPositions.get(0), "L");
      TicketBooker.lowerBerthsPositions.remove(0);
      TicketBooker.availableLowerBerths--;
    }
    else if(TicketBooker.availableMiddleBerths>0){
      System.out.println("Middle Berth given");
      booker.bookTicket(p,TicketBooker.middleBerthsPositions.get(0), "M");
      TicketBooker.middleBerthsPositions.remove(0);
      TicketBooker.availableMiddleBerths--;
    }
    else if(TicketBooker.availableUpperBerths>0){
      System.out.println("Lower Berth given");
      booker.bookTicket(p,(TicketBooker.upperBerthsPositions.get(0)), "L");
      TicketBooker.upperBerthsPositions.remove(0);
      TicketBooker.availableUpperBerths--;
    }
    else if(TicketBooker.availableRacTickets > 0){
      System.out.println("RAC available");
      booker.addToRac(p, (TicketBooker.racPositions.get(0)), "RAC");

    }
    else if(TicketBooker.availableWaitingList > 0){
      System.out.println("Waiting List available");
      booker.addToWaitingList(p, (TicketBooker.waitingListPositions.get(0)), "WL");
      
    }
  }
  public static void cancelTicket(int id){
    TicketBooker booker = new TicketBooker();
    if(!booker.passengers.containsKey(id)){
      System.out.println("Passenger details unknown");
    }
    else{
      booker.cancelTicket(id);
    }
  }
  public static void main(String[] args){
    Scanner scan=new Scanner(System.in);
    boolean loop = true;
     while(loop){
      System.out.println("1.Book Ticket\n2.Cancel Ticket\n3.Available Tickets\n4.Booked Tickets\n5.Exit");
      int choice = scan.nextInt();
       switch (choice) {
        case 1:
            {
              System.out.println("Enter passenger name, age, berth preference (L, M or U)");
              String name= scan.next();
              int age= scan.nextInt();
              String berthPreference=scan.next();
              Passenger p= new Passenger(name, age, berthPreference);
              bookTicket(p);
            }
          break;
        case 2:
        {
          System.out.println("Enter passenger ID to cancel");
          int id= scan.nextInt();
          cancelTicket(id);
        }
        break;
        case 3:
        {
          TicketBooker booker = new TicketBooker();
          booker.printAvailable();
        }
        break;
        case 4: 
        {
           TicketBooker booker = new TicketBooker();
           booker.printPassengers();
        }
        break;
        case 5:
        {
          loop=false;
        }
        break;
        default:
          break;
       }
     }
  }
 }
