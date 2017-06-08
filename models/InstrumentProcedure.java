/*
  Represents a single SID/STAR procedure
*/
class InstrumentProcedure {

  String ident;   //Procedure Name
  String type;    //SID/STAR
  ArrayList<Waypoints> procedure;   //Procedure specific procedures
  HashMap<String, ArrayList<Waypoints>> runways;  
  HashMap<String, ArrayList<Waypoints>> transitions;

  public Procedure(String ident, String type) {
    this.ident = ident; 
    this.type = type;
    procedure = new ArrayList<>();
    options = new HashMap<>();
    transitions = new HashMap<>();
  }

  public void setProcedure(String id, ArrayList<Waypoints> procedure) {
    this.procedure.put(id, procedure);
  }

  public void addRunway(String id, HashMap<String, ArrayList<Waypoints>> runway) {
    runways.put(id, runway);
  }

  public void addTransition(String id, HashMap<String, ArrayList<Waypoints>> transition) {
    transitions.put(id, transition);
  }

}