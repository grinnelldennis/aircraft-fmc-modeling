interface ProceduralFix {

  /**
   * Creates an "AT OR ABOVE" altitude for the procedural fix
   * @param altitude, a number
   */
  public boolean addAtOrAboveAltitude(String altitude);
  public boolean addAtOrAboveAltitude(int altitude);
  /**
   * Creates an "AT OR BELOW" altitude for the procedural fix
   * @param altitude, a number
   */
  public boolean addAtOrBelowAltitude(String altitude);
  public boolean addAtOrAboveAltitude(int altitude);
  /**
   * A catch-all method that creates an altitude restriction for this
   * procedural fix based on `type` and populate fields accordingly.
   * @param type, type of altitude restriction; acceptable types are
   *                (`AT`, `ABOVE`, `BELOW`)
   * @param altitude, a string representation of an altitude
   */
  public boolean addAltitudeRestriction(String type, String altitude);
  public boolean addAltitudeRestriction(String type, int altitude);
  /**
   * Creates a speed restriction for the procedural fix
   * @param speed, a number
   */
  public void addSpeedRestriction(String speed);
  public void addSpeedRestriction(int speed);
  /**
   * Returns whether fix exists a passing restriction
   * @return boolean, restriction status
   */
  public boolean hasRestriction();
  /**
   * Returns whether fix is cleared
   * @return boolean, restriction status
   */
  public boolean isCleared();
  /**
   * Returns true if the waypoint is a vector
   * @return boolean, restriction status
   */
  public boolean isVector();
  
}