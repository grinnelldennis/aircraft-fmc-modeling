
// Read nav and airport informations into respective hashmaps within the program
class NavigationDatabase {
  // airports; key: icao, value: Airport object
  HashMap<String, Airport> airports;
  // navaids: key: designator(non-unique), value: ArrayList<Navaid object>
  HashMap<String, ArrayList<Navaid>> navaids;

  public NavigationDatabase () {
    BuildDatabase build = new BuildDatabase();
  }


  // Export entries associated with ident
  public ArrayList<Navaid> getNavaid(String ident) {
    if (navaids.contains(ident))
      return navaids.get(ident);
    else  return null;
  }
  public Airport getAirport(String icao) {
    if (airports.contains(icao))
      return airport.get(icao);
    else  return null;
  }

  public HashMap<String, Navaid> getNavaids() { return navaids; }
  public HashMap<String, Airport> getAirports() { return airports; }

}