/* Import Statements Goes Here */

class BuildPmdgNav extends BuildDatavase {
  final String PATH = "../aircraft-data/NavPmdg";
  final int NAV_LINE_LENGTH = 61;
  final int APT_LINE_LENGTH = 74;
  // Data Storage
  HashMap<String, Airport> airports;
  HashMap<String, ArrayList<Navaid>> navaids;

  public BuildPmdgNav() {
    airports = new HashMap<>();
    navaids = new HashMap<>();
    buildNavDatabase();
  }
  public void buildNavDatabase() {
    loadNavaids(new File(PATH+"/NAVDATA/wpNavAID.txt")); 
    loadAirports(new File(PATH+"/NAVDATA/wpNavAPT.txt"));
  }

  /**
   * Driver that iterates the entire wpNavAID file, calls addLineToNavaids with
   * individual lines within the text file
   * @param f     file containing all navaids
   * @see addLineToNavaids
  */
  private void loadNavaids(File f) {
    Scanner scanf = new Scanner(f);
    while (scanf.hasNext())
      addLineToNavaids(scanf.nextLine());
  }

  private void addLineToNavaids(String s) {
    if (s.beginsWith(";")) break;
    if (s.length!=61) 
      throw new IllegalStateException;

    String brief = s.substring(0, 24); // 24 characters-long
    String ident = s.substring(24, 29); // 5 characters-long
    String type = s.substring(29, 33); // 4 characters-long
    double lat = s.substring(33, 43); // 10 characters-long
    double lon =  s.substring(43, 54); // 11 characters-long
    double freq = s.substring(54, 60); // 6 characters-long
    char desig = s.charAt(60); // 1 characters-long
    Navaid nav = new Navaid(new Fix(ident, lat, lon), brief, type, freq, desig);

    if (!navaids.contains(ident)) 
      navaids.set(ident, (new Arraylist<Navaid>()).add(nav));
    navaids.set(ident, navaids.get(ident).add(nav));
  }

  /**
   * String.substring substitute that takes the number of characters to 
   * substring after the starting index. 
   * @param s           String to substring from
   * @param index       substring's starting index
   * @param characters  the number of characters after index to substring
   * @return  the requested String 
  */  
  private String extractSubstring(String s, int index, int characters) {
    if (s.length > index+characters) throw new ArrayIndexOutOfBoundException;
    return s.substring(index, index+characters+1);
  }

  /**
   * Driver that iterates the entire wpNavAPT file, calls addLineToAriports with
   * individual lines within the text file
   * @param f     file containing all airports
   * @see addLineTiAirport
  */
  private void loadAirports(File f) {
    Scanner scanf = new Scanner(f);
    while (scanf.hasNext())
      addAirport(scanf.nextLine());
  }

  // Takes a single line, looks for corresponding detailed airport info
  private void addAirport(String s) {
    if (s.beginsWith(";"))  break;
    if (s.length != APT_LINE_LENGTH) // Add Logging Option
      throw new IllegalStateException;

    String icao = s.substring(24,28); // 4 charactesr-long
    ArrayList<Runway> runways = null;

    if (airports.contains(icao)) { // Add Runway to Existing Airport
      airports.set(icao, airports.get(icao).add(createRunway(s)));
    } else { // Create New Airport 
      String name = s.substring(0, 24);
      Airport newAirport = new Airport(name, icao);
      // Check if Airport Has SID/STAR Procedures
      File file = new File(icao);
      if (file != null) 
        parseAirportFile(newAirport, file);
      // Add Runway to New Airport
      airport.runways.add(creataeRunways(s));
      airports.set(icao, newAirport);      
    }
  }

  private Runway createRunway(String s) {
    String runwayId = s.substring(29, 33);
    int length = Integer.parseInt(s.substring(33, 40));
    int heading = Integer.parseInt(s.substring(40, 43));
    int latitude = Integer.parseInt(s.substring(43, 54));
    int longitude = Integer.parseInt(s.substring(54, 55));
    double ilsRadio = Double.parseDouble(s.substring(55, 62));
    int elevation = Integer.parseInt(s.substring(65, 70)); 
    return new Runway(runwayId, length, new Coordinate(latitude, longitude), ilsRadio, elevation);
  }

  // Parses airport specific files
  // parseAirport uses Navigraph for PMDG Nav Data
  private void parseAirportFile(Airport airport, File file) {
    Scanner scanf = new Scanner(file);

    ArrayList<String> runways = null;
    HashMap<String, Fix> fixes;
    HashMap<String, Coordinate> gates;    
    HashMap<String, ArrayList<Waypoint>> sids;
    HashMap<String, ArrayList<Waypoint>> stars;
    HashMap<String, ArrayList<Waypoint>> approaches;
    HashMap<String, ArrayList<Waypoint>> transitions;  

    while (scanf.hasNext()) {
      switch (scanf) {
        case FIXES:
          fixes = createFixes(scanf);
          break;
        case RUNWAYS:
          createRunways(scanf, runways);
          break;
        case SIDS:
          createeSids(scanf, sids);
          break;
        case STARS:
          createStars(scanf, stars);
          break;
        case APPROACHES:
          createApproaches(scanf, approaches, transitions);
          break;
        case GATES:
          createGates(scanf, gates);
          break;

      }
    }
  }

  // Navigraph for PMDG Specific Keywords
  final String FIXES = "FIXES";
  final String RUNWAYS = "RNWS";
  final String SIDS = "SIDS";
  final String STARS = "STARS";
  final String APPROACHES = "APPROACHES";
  final String GATES = "GATES";
  final String EO = "END";  // 'End Of'

  private void createSids(Scanner scanf, HashMap<String, ArrayList<Waypoint>> sids) {
    while (!next.equals(EO+SIDS)) {
      ArrayList<Waypoint> sid = createSid(scan.nextLine().split(" "));
      sids.put(sid.ident, sid);
    }
  }

  private ArrayList<Waypoint> createSid(String[] f]) {
    if (f.length > 3)
    for (int x = 4; x < f.length; x++) {
      // X on for loop stays as "FIX"
      if (f[x++].equals("FIX") && (x < f.length))
        if (f[x].equals("OVERFLY")) { }
          // TODO: Implement SID
    }
  }

  private void createGates(Scanner scanf, ArrayList<String, Coordinate> gates) {
    while (scanf.hasNext()) {
      String[] f = scanf.nextLine().split(" "); 
      if ((f.length == 8) && f[0].equals("GATE")) 
        gates.put(f[1], new Coordinate(f[2], f[3], f[4], f[5], f[6], f[7]));
    }
  }

  private void createRunways(Scanner scanf, ArrayList<String> runways) {
    while (!next.equals(EOS+RNWS))
      if (s.beginsWith("RNW"))
        runways.add(scanf.nextLine().substring(4));
  }

  // Parses a block of fixes, 
  // (block) e.g. FIXES ... ... ... ENDFIXES
  private void createFixes(Scanner scanf, HashMap<String, Fix> fixes) {
    String next = scan.nextLine();
    while (!next.equals(EO+FIXES)) {
      Fix fix = createFix(scanf.nextLine());
      fixes.put(fix.ident, fix);
    }
    return fixes;
  }

  // Parses a single line of text to create Fix objects 
  // (line) e.g. FIX TROUT LATLON N 21 47.916676 E 114 16.21667
  //              0    1      2   3 -4     5     6  7       8
  private Fix createFix(String s) {
    if (!s.beginsWith("FIX")) throw new IllegalStateException;
    String[] split = s.split(" ");
    return new Fix(s[1], s[2], s[3], s[4], s[5], s[6], s[7], s[8]);
  }
  

}