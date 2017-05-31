class Navaid {
  
  Fix fix;
  String name;
  String type;    // ADF/DME/VOR/NDB/
  double freq;   

  public Navaid (String i, String n, String t; double f, double lat, double lon) {
    this.fix = new Fix(i, lat, lon); 
    this.name = n; 
    this.type = t;
    this.f = freq; 
  }
}