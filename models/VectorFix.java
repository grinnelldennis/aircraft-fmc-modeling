class VectorFix implements ProceduralFix {

  String type;     // HDG or TRK
  int degrees;

  Fix toFix;
  double distanceUntil;
  int altitudeUntil;
  int interceptRadial;
  
  boolean hasRestriction;
  int aboveAltitude;
  int belowAltitude;

  public VectorFix (String type, String degrees) {
    this(type, Integer.parseInt(degrees));
  }
  
  public VectorFix(String type, int degrees) {
    this.type = type;
    this.degrees = degrees;
  }

  public void setAltitudeRestriction(String type, String altitude){
    setAltitudeRestriction(type, Integer.parseInt(altitude));
  }
  
  @Override
  public void setAltitudeRestriction(String type, int altitude){
    altitudeUntil = altitude;
  }
  

  public void addDistanceFrom(Fix from, String distance) {
    addDistanceFrom(from, Double.parseDouble(distance));
  }
  
  public void addDistanceFrom(Fix from, double distance) {
    this.toFix = from;
    this.distanceUntil = distance;
  }

  @Override public boolean isVector() { return true; }
  @Override public boolean isCleared(Aircraft ac) { 
    return false; 
  }

  public boolean isTrack() { return type.equals("TRK"); }
  public boolean isHeading() { return type.equals("HDG"); }

  @Override
  public void proceduralFix(String id) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setAtOrAboveAltitude(int altitude) {
    this.aboveAltitude = altitude;
  }

  @Override
  public void setAtOrBelowAltitude(int altitude) {
    this.belowAltitude = altitude;
  }

  @Override
  public void setSpeedRestriction(int speed) {
    
  }

  @Override
  public void setRestriction(boolean restriction) {
    
  }

  @Override
  public boolean hasRestriction() {
    // TODO Auto-generated method stub
    return false;
  }

  
}