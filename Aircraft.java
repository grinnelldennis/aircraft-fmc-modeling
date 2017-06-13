import java.io.File;
import java.util.HashMap;

// Import statements

// Stores mutable, attitude information about aircraft's current state
class Aircraft {
  
  AircraftParameters param; //Static performance data
  AircraftNavigation nav; //Navigation data
  
  //geographical info
  Coordinate position;
  double altitude;
  double verticalSpeed;
  double heading;
  double track;
  double ias;
  
  //attitude information, in degrees
  double pitch;
  double roll;
  double yaw;
  
  //acceleration in space
  double xAcc;
  double yAcc;
  double zAcc;
    
  //performance
  int weight;
  int range;
  
  public Aircraft (String type) {
    AircraftParameters param = new AircraftParameters(type);
    
  }
  
}