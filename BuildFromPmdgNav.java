import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/* Import Statements Goes Here */

class BuildPmdgNav extends NavigationDatabase {
  
  final String PATH = "../aircraft-data/NavPmdg";
  final int NAV_LINE_LENGTH = 61;
  final int APT_LINE_LENGTH = 74;
  
  HashMap<String, Airport> airports;
  HashMap<String, ArrayList<Navaid>> navaids;

  public BuildPmdgNav() throws FileNotFoundException {
    airports = new HashMap<>();
    navaids = new HashMap<>();

    loadNavaids(new File(PATH+"/NAVDATA/wpNavAID.txt")); 
    loadAirports(new File(PATH+"/NAVDATA/wpNavAPT.txt"));
  }

  /**
   * Driver that iterates the entire wpNavAID file, calls addLineToNavaids with
   * individual lines within the text file
   * @param f     file containing all navaids
   * @throws FileNotFoundException 
   * @see addLineToNavaids
   */
  private void loadNavaids(File f) throws FileNotFoundException {
    Scanner scanf = new Scanner(f);
    while (scanf.hasNext())
      addLineToNavaids(scanf.nextLine());
  }

  private void addLineToNavaids(String s) {
    if (s.startsWith(";")) return;
    if (s.length() != 61) 
      throw new IllegalStateException("Invalid Navaid Entry");

    // Decompose each line in Navaid file
    String brief = s.substring(0, 24); // 24 characters-long
    String ident = s.substring(24, 29); // 5 characters-long
    String type = s.substring(29, 33); // 4 characters-long
    double lat = Double.parseDouble(s.substring(33, 43)); // 10 characters-long
    double lon =  Double.parseDouble(s.substring(43, 54)); // 11 characters-long
    double freq = Double.parseDouble(s.substring(54, 60)); // 6 characters-long
    char desig = s.charAt(60); // 1 characters-long
    
    Navaid nav = new Navaid(new Fix(ident, lat, lon), brief, type, freq, desig);
    if (!navaids.containsKey(ident)) 
      navaids.put(ident, new ArrayList<Navaid>());
    ArrayList<Navaid> updateNav = navaids.get(ident);
    updateNav.add(nav);
    navaids.put(ident, updateNav);
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
    if (s.length() > index+characters) throw new ArrayIndexOutOfBoundsException("Index Out of Bound");
    return s.substring(index, index+characters+1);
  }

  /**
   * Driver that iterates the entire wpNavAPT file, calls addLineToAriports with
   * individual lines within the text file
   * @param f     file containing all airports
   * @throws FileNotFoundException 
   * @see addLineTiAirport
   */
  private void loadAirports(File f) throws FileNotFoundException {
    Scanner scanf = new Scanner(f);
    while (scanf.hasNext())
      addAirport(scanf.nextLine());
  }

  /**
   * Parses a line of the airport text file containing a single runway information
   * and extract the runway information, creates mapping airport object the key 
   * (airport icao) does not already exist in hashtable; otherwise adds the runway
   * to the hashtable. 
   * @param s   text from airports database, contains one runway info for airport
   * @throws FileNotFoundException 
   */
  private void addAirport(String s) throws FileNotFoundException {
    if (s.startsWith(";"))  return;
    if (s.length() != APT_LINE_LENGTH) // Add Logging Option
      throw new IllegalStateException("Invalid Airport Entry");
    String icao = s.substring(24, 28); // 4 charactesr-long
    ArrayList<Fix> runways = null;

    if (!airports.containsKey(icao)) { 
      String name = s.substring(0, 24);
      Airport newAirport = new Airport(name, icao);
      // Check if Airport Has SID/STAR Procedures
      File file = new File(PATH+"/SIDSTARS/"+icao+".txt");
      if (file != null) 
        parseAirportFile(newAirport, file);
      airports.put(icao, newAirport);      
    }
    Airport updateAirport = airports.get(icao);
    updateAirport.add(createRunway(s));
    airports.put(icao, updateAirport);
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

  /**
   * Parses an entire file specific to a single airport containing SID/STARs.
   * @param airport   Airport the procedures will be stored in
   * @param file      The entire SIDSTAR file to read from
   * @throws FileNotFoundException 
   */
  private void parseAirportFile(Airport airport, File file) throws FileNotFoundException {
    Scanner scanf = new Scanner(file);

    while (scanf.hasNext()) {
      switch (scanf.nextLine()) {
      case COMMENT:
        break;
      case "\n":
        break;
      case FIXES:
        createFixes(scanf, airport);
        break;
      case RUNWAYS:
        break;
      case SIDS:
        createSids(scanf, airport);
        break;
      case STARS:
        createStars(scanf, airport);
        break;
      case APPROACHES:
        createApproaches(scanf, airport);
        break;
      case GATES:
        createGates(scanf, airport);
        break;
      }
    }
  }

  // PMDG-Navigraph Constants
  final String FIXES = "FIXES";
  final String RUNWAYS = "RNWS";
  final String SIDS = "SIDS";
  final String STARS = "STARS";
  final String APPROACHES = "APPROACHES";
  final String GATES = "GATES";
  final String COMMENT = ";";
  final String EO = "END";  // 'End Of'

  private void createApproaches(Scanner scanf, Airport airport) {
    
  }
  
  /**
   * Parses block text within the GATES tag to create gates.
   * @param scanf     file reader at the beginning of GATES tag
   * @param airport   airport object
   */
  private void createGates(Scanner scanf, Airport airport) {
    while (scanf.hasNext()) {
      String[] f = scanf.nextLine().split(" "); 
      if ((f.length == 8) && f[0].equals("GATE")) 
        airport.add(f[1], new Coordinate(f[2], f[3], f[4], f[5], f[6], f[7]));
    }
  }

  /** 
   * Consume blocks of text within the FIX tag until scanner reaches ENDFIXES
   * e.g. FIXES 
   *      (block of stuff) 
   *      ENDFIXES
   * @param scanf     the current point in file within Scanner objetc
   * @param airport   airport object to add fixes to
   */
  private void createFixes(Scanner scanf, Airport airport) {
    String next = scanf.nextLine();
    while (!next.equals(EO+FIXES)) {
      Fix fix = createFix(scanf.nextLine());
      airport.add(fix.ident, fix);
    }
  }

  /**
   * Parses a single line of text within (icao).txt to create a Fix object 
   * e.g. (line)  FIX TROUT LATLON N 21 47.916676 E 114 16.21667
   *               0    1      2   3 -4     5     6  7       8
   * @param s     a line of text (of a fix) to parse
   * @return fix  a new fix based on the string of text
   */
  private Fix createFix(String ss) {
    if (!ss.startsWith("FIX")) throw new IllegalStateException("Invalid Entry in FIX Section");
    String[] s = ss.split(" ");
    return new Fix(s[1], s[3], s[4], s[5], s[6], s[7], s[8]);
  }

  int wordCounter = 0;

  private void createSids(Scanner scanf, Airport airport) {
    HashMap<String, ArrayList<ProceduralFix>> sids = new HashMap<>();
    parseProcedures("SID", scanf);
  }

  private void createStars(Scanner scanf, Airport airport) {
    HashMap<String, ArrayList<ProceduralFix>> sids = new HashMap<>();
    parseProcedures("STAR", scanf);
  }

  /**
   * Parses an entire block of SID or STAR procedure
   *
   */
  private void parseProcedures(String procedureType, Scanner scanf) {
    String s = scanf.nextLine();
    InstrumentProcedure proc = null;
    while (!s.equals(EO+procedureType)) {
      //consumes entire line
      String[] ss = s.split(" ");
      if (s.startsWith(procedureType+" ")) {
        proc = new InstrumentProcedure(procedureType, ss[2]);
        wordCounter++;
      } else if (proc == null) {
        throw new IllegalStateException("Procedure is null.");
      } else if (s.startsWith(" ")) {
        proc.addRunwayProcedure(ss[1], parseProcedure(ss, new ArrayList<ProceduralFix>()));
      } else if (s.startsWith("  ")) {
        proc.addTransition(ss[2], parseProcedure(ss, new ArrayList<ProceduralFix>()));
      }
      
    }
  }


  /* ------------From ParsingRestrictions.java------------ */
  
  String icao;
  int wordCount = 0;
  
  /**
   * Scans for keywords for new procedural fixes. Recurses until wordCounter is out of bound
   * @param arr,  array of all word fragments from a single procedure
   * @param procedure,  a list of all instrument procedures of this type
   */
  private ArrayList<ProceduralFix> parseProcedure(String[] arr, ArrayList<ProceduralFix> waypoints) {
    if (wordCounter >= arr.length)
      return waypoints;

    ProceduralFix waypoint = null;
    String fixIdent;

    switch(arr[wordCounter]) {
    case "FIX":
      if (arr[wordCounter+1].equals("OVERFLY")) {
        fixIdent = arr[wordCount+=2];
        waypoint = new NavigationFix(fixIdent, getFix(fixIdent).coord);
        parseRestrictions(arr, waypoint);
      } else {
        fixIdent = arr[wordCount+=1];
        waypoint = new NavigationFix(fixIdent, getFix(fixIdent).coord);
        parseRestrictions(arr, waypoint);
      } break;
    case "HDG":
      waypoint = new VectorFix("HDG", arr[wordCount+=1]);
      parseVector(arr, (VectorFix) waypoint);
      break;
    case "TRK":
      waypoint = new VectorFix("TRK", arr[wordCount+=1]);
      parseVector(arr, (VectorFix) waypoint);
      break;
    case "RNW":
      fixIdent = arr[wordCount+=1];
      waypoint = new NavigationFix(fixIdent, getFix(fixIdent).coord);;
      parseRestrictions(arr, waypoint);
      break;  
    case "HOLD":
      // TODO: Set up Hold
      break;
    case "TURN":
      //TURN (LEFT/RIGHT) DIRECT
    case "VECTORS":
      //VECTORS
    default:
      System.out.println (arr[wordCounter++] + " caught");
      break;
    }
    waypoints.add(waypoint);
    return parseProcedure(arr, waypoints);
  }
  

  /**
   * Parses an array of a individual words, from index of consumed 
   * vector keywords (TRK/HDG+DEG) as wordCounter, for vectoring instructions;
   * parses for three specific forms, [unconsumed] 
   *  (1) UNTIL (ALT)
   *  (2) UNTIL (DST) FROM FIX (IDENT)
   *  (3) INTERCEPT RADIAL (DEG) TO FIX (IDENT)
   * @param arr,  array containing all words from a single instrument procedure
   * @param fix,  waypoint for which to store the vector restrictions
   * @return  index of first un-consumed word for procedure after parsing
   */
  private void parseVector(String[] arr, VectorFix waypoint) {
    switch (arr[wordCounter]) {
    case "UNTIL":
      if (arr[wordCounter++].contains(".")) {// '.' might cause problem
        
        waypoint.addDistanceFrom(getFix(arr[wordCounter+=4]), arr[wordCounter-3]);
      } else {
        waypoint.setAltitudeRestriction("UNTIL", convertToInt(arr[wordCounter+=1]));
      } case "INTERCEPT":
        waypoint.addInterceptRadial(getFix(arr[wordCounter+=5]), Integer.parseInt(arr[wordCounter-4]));
        break;
      default:
        break;
    } 
    wordCounter++;
  }

  /**
   * Parses an array of a individual words, from immediately after
   * a keyword of vector as wordCounter, parses for 
   *  (1) AT OR ABOVE (ALT)
   *  (2) AT OR BELOW (ALT)
   *  (3) SPEED (SPD)
   *  (4) (ALT)
   * @param arr,  array containing all words from a single instructment procedure
   * @param fix,  waypoint for which to store the vector restrictions
   * @return  index of first unconsumed word for procedure after parsing
   */
  private int parseRestrictions(String[] arr, ProceduralFix fix) {
    while (!isFixKeyword(arr[wordCounter])) {
      switch (arr[wordCounter]) {
      case "AT":
        switch (arr[wordCounter+2]) {
        case "ABOVE":
        case "BELOW":
          fix.setAltitudeRestriction(arr[wordCounter+=2], convertToInt(arr[wordCounter+=1]));
          break;
        } break;
      case "SPEED": 
        fix.setSpeedRestriction(convertToInt(arr[wordCounter+=1]));
        break;
      default:
        int altitude = Integer.parseInt(arr[wordCounter]);
        if (altitude > 0)
          fix.setAltitudeRestriction("AT", altitude);
        break;
      }
      wordCounter++;
    }
    return wordCounter;
  }

  
  /* ------------ Helpers ------------ */
  private double convertToDouble(String s) {
    return Double.parseDouble(s);
  }
  private int convertToInt(String s) {
    return Integer.parseInt(s);
  }

  private String[] fixKeywords = {"FIX", "HDG", "TRK", "RNW", "HOLD", "TURN", "VECTORS"};

  private boolean isFixKeyword(String word) {
    for (String keyword : fixKeywords)
      if (word.equalsIgnoreCase(keyword)) 
        return true;
    return false;
  }
  
  private Fix getFix(String ident) {
    if (airports.containsKey(icao)) 
      if (airports.get(icao).fixes.containsKey(ident))
        return airports.get(icao).fixes.get(ident);
    return null;
  }

}