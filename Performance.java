
class Performance {

  Aircraft ac;

  public Performance (Aircraft ac) {
    this.ac = ac;
  }

  public getDistanceWithMinutes(double minutes) {
    
  }

  public getTopOfClimb() {}

  public getTopOfDescend(int top, int bottom, int glideSlope) { return (top - bottom) / glideSlope; }

  public getRateOfDescend(int groundSpeed) { return groundSpeed * 5; };

  public getRateOfDescend(int tas, int top, int bottom, int distance) { 
    return (tas * (top - bottom))/ distance; 
  }
  
  public getDescendPoint() {}

  public getVref () {}

}