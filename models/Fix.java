class Fix {  

  String ident;
  Coordinate coord;

  public Fix (String i, double l1, double l2){
    this.ident = i; 
    this.coord = new Coordinate(l1, l2);
  }

  public Fix (String i, String ns, String hh1, String mm1, String ew, String hh2, String mm2) {
    this.ident = i;
    this.coord = new Coordinate(ns, hh1, mm1, ew, hh2, mm2);
  }

  
}