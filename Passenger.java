public class Passenger{
  static int id=1; // to give id for every new passenger
  String name; //get input from user
  int age;// get input from user
  String berthPreference; // L, U or M get input from user
  int passengerId; //generate after booking
  String  alotted; // L, U, M, RAC, WL
  int number; //seat number
  public Passenger(String name, int age, String berthPreference){
    this.name=name;
    this.age=age;
    this.berthPreference=berthPreference;
    this.passengerId=id++;
    alotted=""; //initial dummy values
    number=-1; //initial dummy values
  }
}