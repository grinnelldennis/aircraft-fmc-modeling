class VectorFix extends ProceduralFix {

  String type;     // HDG or TRK
  int degrees;

  Fix toFx;
  int distanceUntil;
  int altitudeUntil;
  int interceptRadial;

  public VectorFix (String type, String degrees) {
    this(type, Integer.parseInt(degrees));
  }
  public VectorFix(String type, int degrees) {
    this.type = type;
    this.degrees = degrees;
  }

  @Override
  private void setAltitudeRestriction(String type, String altitude){
    this.(type, Integer.parseInt(altitude));
  }

  @Override
  private void setAltitudeRestriction(String type, int altitude){
    altitudeUntil = altitude;
  }

  @Override  private boolean isVector() { return true; }
  @Override  private boolean isCleared(Aircraft ac) { 
    return false; 
  }

  private boolean isTrack() { return type.equals("TRK"); }
  private boolean isHeading() { return type.equals("HDG"); }

  private void addDistanceFrom(Fix from, String distance) {
    this(from, Double.parseDouble(distance));
  }
  
  private void addDistanceFrom(Fix from, int distance) {
    this.fromFix = from;
    this.distanceUntil = distance;
  }

}