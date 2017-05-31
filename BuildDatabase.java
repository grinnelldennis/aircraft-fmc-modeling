
class BuildDatabase {
  final String path = "/data";
  // Data Storage
  HashMap<String, Airport> airports;
  HashMap<String, ArrayList<Navaid>> navaids;

  public BuildDatabase() {
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
  private void loadNavaids(File f) {
    Scanner scanf = new Scanner(f);
    while (scanf.hasNext())
      addNavaid(scanf.nextLine());
  }
  private void addNavaid(String s) {
    // TODO: fill information delimiting pattern
    String[] f = s.split("--STUB--");
    String ident = f[-1];           
    if (!navaids.contains(ident))
      navaids.add(ident, new Arraylist<Navaid>());
    navaids.set(ident, navaids.get(ident).add(new Navaid()));
  }

  // Initialize Airports from file
  private void loadAirports(File f) {
    Scanner scanf = new Scanner(f);
    while (scanf.hasNext())
      addAirport(scanf.nextLine());
  }
  private void addAirport(String s) {
    // TODO: fill information delimiting pattern
    String[] f = s.split("--STUB--");       
    String icao = f[-1];            
    Airport airport = new Airport());                  // create new entry for navaids
    File file = new File(icao);
    if (file != null) { parseAirport(airport, file); }
    airports.add(icao, airport);   
  }

  // Parses airport specific files
  // parseAirport uses Navigraph for PMDG Nav Data
  private void parseAirport(Airport airport, File file) {
    Scanner scanf = new Scanner(file);

    ArrayList<String> runways = null;
    HashMap<String, Fix> fixes;
    HashMap<String, Coordinate> gates;    
    HashMap<String, ArrayList<Waypoint>> sids;
    HashMap<String, ArrayList<Waypoint>> stars;
    HashMap<String, ArrayList<Waypoint>> approaches;
    HashMap<String, ArrayList<Waypoint>> transitions;  

    final int UNRESTRICTED_ALT = -99999;

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