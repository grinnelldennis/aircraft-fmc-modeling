class Navaid {
  
  Fix fix;
  String brief;
  String type;    // ADF/DME/VOR/NDB/
  double freq; 
  char designator;  


  public Navaid (String i, String b, String t, double f, double lat, double lon) {
    this.fix = new Fix(i, lat, lon); 
    this.brief = b; 
    this.type = t;
    this.freq = freq; 
  }

  public Navaid (Fix fix, String b, String t, double f, char d) {
    this.fix = fix;
    this.brief = b;
    this.type = t;
    this.freq = f;
    this.designator = d;
  }

}