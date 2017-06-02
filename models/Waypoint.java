class Waypoint {

  final int UNRESTRICTED = -9999;

  ProceduralFix fix;
  int speed;
  int altAbove;
  int altBelow;
  int altUntil;

  public Waypoint(Fix fix, int spd, int a, int b, int u) {
    this.fix = fix;
    this.speed = spd;
    this.aboveAlt = a;
    this.belowAlt = b;
  }

  public Waypoint(String i, int l1, int l2, int spd, int a, int b, int u) {
    this(new Fix(i, l1, l2), spd, a, b, u);
  }

  public Waypoint(String i, int l1, int l2) {
    this(new Fix(i, l1, l2), UNRESTRICTED, UNRESTRICTED, UNRESTRICTED, UNRESTRICTED);
  }

  public void setRestrictions(String s, int restriction) {
    if (s.equalsIgnoreCase("ABOVE"))
      altAbove = restriction;
    else (s.equalsIgnoreCase("BELOW"))
      altBelow = restriction;
    else if (s.equalsIgnoreCase("SPEED"))
      speed = restriction;
    else if (s.equalsIgnoreCase("UNTIL"))
      altUntil = restriction;
    else
      System.out.println ("setRestriction called but none set.");
  }
}