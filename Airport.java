class Airport {
  
  String icao;
  String name;
  int elevation;

  // SIDs & STARs
  HashMap<String, ArrayList<Waypoint>> sids;
  HashMap<String, ArrayList<Waypoint>> stars;  
}