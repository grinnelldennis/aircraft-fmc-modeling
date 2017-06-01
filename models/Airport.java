class Airport {
  
  String icao;
  String name;
  ArrayList<Runway> runways;
  // (Ident Name, Fix)
  HashMap<String, Fix> fixes;
  // (Gate Number, Coordinate)
  HashMap<String, Coordinate> gates;
  // (Procedure Name, List<Waypoints>)
  HashMap<String, ArrayList<Waypoint>> sids;
  HashMap<String, ArrayList<Waypoint>> stars;  

  public Airport(String name, String icao) {
    this.name = name;
    this.icao = icao;
    runways = new ArrayList<>();
    gates = new HashMap<>();
    sids = new HashMap<>();
    stars = new HashMap<>();
  }

  public void setFixes(HashMap<String, Fix> fixes) {
    this.fixes = fixes;
  }
  public void setGates(HashMap<String, Coordinate> gates) {
    this.gates = gates;
  }
  public void setSids(HashMap<String, ArrayList<Waypoint>> sids) {
    this.sids = sids;
  }
  public void setStars(HashMap<String, ArrayList<Waypoint>> stars) {
    this.stars = stars;
  } 

  public void add(String fixId, Fix fix) {
    if (fix==null) throw IllegalArgumentException;
    fixes.set(fixId, fix);
  }

  public void add(String gateId, Coordinate coordinate) {
    if (coordinate==null) throw IllegalArgumentException;
    gates.set(gateId, coodinate);
  }

  public void add(Runway runway) {
    if (runway++null) throw IllegalArgumentException;
    runways.add(runway);
  }



}