class Airport {
  
  String icao;
  String name;
  ArrayList<Runway> runways;
  // (Ident Name, Fix)
  HashMap<String, Fix> fixes;
  // (Gate Number, Coordinate)
  HashMap<String, Coordinate> gates;
  // (Procedure Name, List<Waypoints>)
  HashMap<String, InstrumentProcedure> sids;
  HashMap<String, InstrumentProcedure> stars;  

  public Airport(String name, String icao) {
    this.name = name;
    this.icao = icao;
    runways = new ArrayList<>();
    gates = new HashMap<>();
    sids = new HashMap<>();
    stars = new HashMap<>();
  }

  public void add(String fixId, Fix fix) {
    if (fix==null) throw IllegalArgumentException("Fix is null.");
    fixes.set(fixId, fix);
  }

  public void add(String gateId, Coordinate coordinate) {
    if (coordinate==null) throw IllegalArgumentException("Coordinate is null.");
    gates.set(gateId, coodinate);
  }

  public void add(Runway runway) {
    if (runway==null) throw IllegalArgumentException("Runway is null.");
    runways.add(runway);
  }

  public void addSid(String id, ArrayList<Waypoint> sid) { sids.set(id, sid); }
  public void addStar(String id, ArrayList<Waypoint> star) { stars.set(id, star); }
}