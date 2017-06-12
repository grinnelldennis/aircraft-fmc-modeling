import java.util.ArrayList;
import java.util.HashMap;

// Read nav and airport informations into respective hashmaps within the program
class NavigationDatabase {
  // airports; key: icao, value: Airport object
  HashMap<String, Airport> airports;
  // navaids: key: designator(non-unique), value: ArrayList<Navaid object>
  HashMap<String, ArrayList<Navaid>> navaids;

  public NavigationDatabase () {
    
  }


  // Export entries associated with ident
  public ArrayList<Navaid> getNavaid(String ident) {
    if (navaids.containsKey(ident))
      return navaids.get(ident);
    else  return null;
  }
  public Airport getAirport(String icao) {
    if (airports.containsKey(icao))
      return airports.get(icao);
    else  return null;
  }

  public HashMap<String, ArrayList<Navaid>> getNavaids() { return navaids; }
  public HashMap<String, Airport> getAirports() { return airports; }

}