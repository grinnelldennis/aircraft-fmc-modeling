class NavigationFix implements ProceduralFix {
  
  String ident;     // heading
  Coordinate coord;
  
  public NavigationFix(String name, Coordinate coord) {
    this.ident = name;
    this.coord = coord;
  }
  
  @Override
  public void proceduralFix(String id) {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void setAtOrAboveAltitude(int altitude) {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void setAtOrBelowAltitude(int altitude) {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void setAltitudeRestriction(String type, int altitude) {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void setSpeedRestriction(int speed) {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void setRestriction(boolean restriction) {
    // TODO Auto-generated method stub
    
  }
  @Override
  public boolean hasRestriction() {
    // TODO Auto-generated method stub
    return false;
  }
  @Override
  public boolean isCleared(Aircraft ac) {
    // TODO Auto-generated method stub
    return false;
  }
  @Override
  public boolean isVector() {
    // TODO Auto-generated method stub
    return false;
  }

  

}