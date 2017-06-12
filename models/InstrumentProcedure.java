import java.util.ArrayList;
import java.util.HashMap;

/*
  Represents a single SID/STAR procedure
*/
class InstrumentProcedure {

  String ident;   //Procedure Name
  String type;    //SID/STAR
  ArrayList<ProceduralFix> baseProcedure;   
  HashMap<String, ArrayList<ProceduralFix>> runwayProcedures;  
  HashMap<String, ArrayList<ProceduralFix>> transitions;

  public InstrumentProcedure(String ident, String type) {
    this.ident = ident; 
    this.type = type;
    baseProcedure = new ArrayList<>();
    runwayProcedures = new HashMap<>();
    transitions = new HashMap<>();
  }

  public void setBaseProcedure(ArrayList<ProceduralFix> proc) { 
    this.baseProcedure = proc; 
  }
  
  public void addBaseProcedureFix(ProceduralFix fixProc) {
    this.baseProcedure.add(fixProc);
  }
  
  public void setProcedure(String type, String id, ArrayList<ProceduralFix> proc) {
	  if (type.equals("Transition")) 
	    addTransition(id, proc);
	  else if (type.equals("Runway"))
	    addRunwayProcedure(id, proc);
  }

  public void addRunwayProcedure(String id, ArrayList<ProceduralFix> runwayProc) {
    this.runwayProcedures.put(id, runwayProc);
  }

  public void addTransition(String id, ArrayList<ProceduralFix> transition) {
    this.transitions.put(id, transition);
  }

}