
// Read nav and airport informations into respective hashmaps within the program
class NavigationDatabase {
  final String path = "/data"; 
  // airports; key: icao, value: Airport object
  HashMap<String, Airport> airports;
  // navaids: key: designator(non-unique), value: ArrayList<Navaid object>
  HashMap<String, ArrayList<Navaid>> navaids;

  public NavigationDatabase () {
    airports = new HashMap<>();
    navaids = new HashMap<>();
    buildDatabase();
  }
  public void buildDatabase() {
    // TODO: fill path
    readNavFromFile(new File(path+"/")); 
    readAirportsFromFile(new File(path+"/"));
  }


  // Initialize Navaids from file
  private void readNavFromFile(File f) {
    Scanner scanf = new Scanner(f);
    while (scanf.hasNext())
      addNavaidToHash(scanf.nextLine());
  }

  private void addNavaidToHash(String s) {
    // TODO: fill information delimiting pattern
    String[] f = s.split("--STUB--");
    String ident = f[-1];           
    if (!navaids.contains(ident))
      navaids.add(ident, new Arraylist<Navaid>());
    navaids.set(ident, navaids.get(ident).add(new Navaid()));
  }

  // Initialize Airports from file
  private void readAirportsFromFile(File f) {
    Scanner scanf = new Scanner(f);
    while (scanf.hasNext())
      addAirportToHash(scanf.nextLine());
  }

  private void addAirportToHash(String s) {
    // TODO: fill information delimiting pattern
    String[] f = s.split("--STUB--");       
    String icao = f[-1];            
    airports.add(icao, new Airport());                  // create new entry for navaids
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