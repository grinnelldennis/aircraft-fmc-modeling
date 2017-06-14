import java.util.ArrayList;
import java.util.HashMap;

class Airport {
  
  String icao;
  String name;
  ArrayList<Runway> runways;
  HashMap<String, Fix> fixes;
  HashMap<String, Coordinate> gates;
  HashMap<String, InstrumentProcedure> sids;
  HashMap<String, InstrumentProcedure> stars;  

  public Airport(String name, String icao) {
    this.name = name;
    this.icao = icao;
    runways = new ArrayList<>();
    gates = new HashMap<>();
    sids = new HashMap<>();
    stars = new HashMap<>();
  }

  public void add(String fixId, Fix fix) {
    if (fix==null) throw new IllegalArgumentException("Fix is null.");
    fixes.put(fixId, fix);
  }

  public void add(String gateId, Coordinate coordinate) {
    if (coordinate==null) throw new IllegalArgumentException("Coordinate is null.");
    gates.put(gateId, coordinate);
  }

  public void add(Runway runway) {
    if (runway==null) throw new IllegalArgumentException("Runway is null.");
    runways.add(runway);
  }
  
  public void addSid(String id, InstrumentProcedure sid) { sids.put(id, sid); }
  public void addStar(String id, InstrumentProcedure star) { stars.put(id, star); }
  
}