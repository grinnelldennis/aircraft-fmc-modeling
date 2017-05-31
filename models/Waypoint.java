class Waypoint {

  Fix fix;
  // Restrictions
  int speed;
  int aboveAlt;
  int belowAlt;

  public Waypoint(String i, int l1, int l2, int spd, int aAlt, int bAlt) {
    this.fix = new Fix(i, l1, l2);
    this.speed = spd;
    this.aboveAlt = aAlt;
    this.belowAlt = bAlt;
  }
}