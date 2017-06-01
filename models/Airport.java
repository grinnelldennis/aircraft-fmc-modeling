class Airport {
  
  String icao;
  String name;
  Coordinate coord;
  int elevation;
  // (Ident Name, Fix)
  HashMap<String, Fix> fixes;
  // (Gate Number, Coordinate)
  HashMap<String, Coordinate> gates;
  // (Procedure Name, List<Waypoints>)
  HashMap<String, ArrayList<Waypoint>> sids;
  HashMap<String, ArrayList<Waypoint>> stars;  

  public Airport(String name, String icao, double l1, double l2) {
    parseLine(s);
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


}