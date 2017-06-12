class Runway {
  
  String id;
  int length;
  int elevation;
  double ilsFrequency;
  Coordinate coord;
  
  public Runway (String runwayId, int length, Coordinate coord, double ilsRadio, int elevation) {
    this.id = runwayId;
    this.length = length;
    this.elevation = elevation;
    this.ilsFrequency = ilsRadio;
    this.coord = coord;
  }
  
}