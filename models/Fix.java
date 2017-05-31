class Fix {

  String ident;
  Coodinate coord;

  public Fix (String i, double l1, double l2){
    this.ident = i; 
    this.coord = new Coodinate(l1, l2);
  }

  public Fix (String i, String ns, double hh1, double mm1, String ew, double hh2, double mm2) {
    this.ident = i;
    this.coord = new Coordinate(ns, hh1, mm1, ew, hh2, mm2);
  }

  
}