class Coordinate{

  double latitude;
  double longitude;

  public Coordinates(double lat, double lon) {
    this.latitude = lat; this.longitude = lon;
  }

  public Coordinates(String ns, double hh1, double mm1, String ew, double hh2, double mm2) {
    latitude = combineHourMinute(hh1, mm1);
    longitude = combineHourMinute(hh2, mm2);
    if (ns.equals("S")) latitude*=-1;
    if (ew.equals("W")) longitude*=-1;
  }

  private double combineHourMinute(double hh, double mm) {
    return hh + (double) Double.parseDouble("."+Double.toString(i).replace(".",""));
  }



}