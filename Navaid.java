class Navaid {
  
  String id;
  String name;
  String type;    // ADF/DME/VOR/NDB/
  double freq;   
  double lat;
  double lon;

  public Navaid (String i, String n, String t; double f, double lat, double lon) {
    this.id = i; 
    this.name = n; 
    this.type = t;
    this.f = freq; 
    this.lat = lat; 
    this.lon = lon;
  }
}