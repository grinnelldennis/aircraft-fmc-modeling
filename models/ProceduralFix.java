interface ProceduralFix {
  
  final int UNRESTRICTED = -999;

  public void proceduralFix(String id);
  /**
   * Creates an "AT OR ABOVE" altitude for the procedural fix
   * @param altitude, a number
   */
  public void setAtOrAboveAltitude(int altitude);
  /**
   * Creates an "AT OR BELOW" altitude for the procedural fix
   * @param altitude, a number
   */
  public void setAtOrBelowAltitude(int altitude);
  /**
   * A catch-all method that creates an altitude restriction for this
   * procedural fix based on `type` and populate fields accordingly.
   * @param type, type of altitude restriction; acceptable types are
   *                (`AT`, `ABOVE`, `BELOW`)
   * @param altitude, a string representation of an altitude
   */
  public void setAltitudeRestriction(String type, int altitude);
  /**
   * Creates a speed restriction for the procedural fix
   * @param speed, a number
   */
  public void setSpeedRestriction(int speed);
  /**
   * Set passing restriction true
   */
  public void setRestriction(boolean restriction);
  /**
   * Returns whether fix exists a passing restriction
   * @return boolean, restriction status
   */
  public boolean hasRestriction();
  /**
   * Returns whether fix is cleared
   * @param ac, an object representing state of the aircraft
   * @return boolean, restriction status
   */
  public boolean isCleared(Aircraft ac);
  /**
   * Returns true if the waypoint is a vector
   * @return boolean, restriction status
   */
  public boolean isVector();

  
}