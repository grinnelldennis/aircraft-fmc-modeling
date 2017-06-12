
class Performance {

  final int UNDEFINED = -999;
  Aircraft ac;

  public Performance (Aircraft ac) {
    this.ac = ac;
  }

  public double getDistanceWithMinutes(double minutes) {
    return UNDEFINED;
  }

  public double getTopOfClimb() {
    return UNDEFINED;
  }

  public double getTopOfDescend(int top, int bottom, int glideSlope) { 
    return (top - bottom) / glideSlope; 
  }

  public int getRateOfDescend(int groundSpeed) { 
    return groundSpeed * 5; 
  }

  public int getRateOfDescend(int tas, int top, int bottom, int distance) { 
    return (int) (tas * (top - bottom))/ distance; 
  }
  
  public int getDescendPoint() {
    return UNDEFINED;
  }

  public int getVreference() {
    return UNDEFINED;
  }
  
  public int getVApproach() {
    return UNDEFINED;
  }

}