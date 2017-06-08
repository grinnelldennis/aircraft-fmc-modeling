class VectorFix extends ProceduralFix {

  String ident;     // heading
  int degrees;

  Fix toFx;
  int distanceUntil;
  int altitudeUntil;
  int interceptRadial;

  private void addDistanceFrom(Fix from, String distance) {
    this(from, Double.parseDouble(distance));
  }
  
  private void addDistanceFrom(Fix from, int distance) {
    this.fromFix = from;
    this.distanceUntil = distance;
  }

  @Override
  private void setAltitudeRestriction(String type, String altitude){
    this.(type, Integer.parseInt(altitude));
  }

  @Override
  private void setAltitudeRestriction(String type, int altitude){
    altitudeUntil = altitude;
  }

  @Override  private boolean isVector(Aircraft ac) { return true; }

  @Override  private boolean isCleared() { return false; )

  }

}