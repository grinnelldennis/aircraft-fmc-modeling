/**
 * Scans for keywords for new procedural fixes. Recurses until cur is out of bound
 * @param arr,  array of all word fragments from a single procedure
 * @param procedure,  a list of all instrument procedures of this type
 */
private void parseProcedure(String[] arr, ArrayList<ProceduralFix> procedure) {
  if (cur >= arr.length)
    return procedure;

  ProceduralFix waypoint;

  switch(arr[cur]) {
    case "FIX":
      if (arr[cur+1].equals("OVERFLY")) {
        waypoint = new NavigationFix(arr[(wordCounter+=2)++]);
        parseRestrictions(arr, waypoint);
      } else {
        waypoint = new NavigationFix(arr[(wordCounter+=1)++]);
        parseRestrictions(arr, waypoint);
      } break;
    case "HDG":
      waypoint = new VectorFix("HDG", arr[(wordCounter+=1)++]);
      parseVector(arr, waypoint);
      break;
    case "TRK":
      waypoint = new VectorFix("TRK", arr[(wordCounter+=1)++]);
      parseVector(arr, waypoint);
      break;
    case "RNW":
      waypoint = new NavigationFix(arr[(wordCounter+=1)++]);
      parseRestrictions(arr, waypoint);
      break;
    case "HOLD":
      // TODO: Set up Hold
      break;
    case "TURN":
      //TURN (LEFT/RIGHT) DIRECT
    default:
      System.out.println (arr[cur++] + " caught");
      break;

    procedure.add(waypoint);
    parseProcedure(arr, cur+=1);
  }
}

/**
 * Parses an array of a individual words, from index of consumed 
 * vector keywords (TRK/HDG+DEG) as cur, for vectoring instructions;
 * parses for three specific forms, [unconsumed] 
 *  (1) UNTIL (ALT)
 *  (2) UNTIL (DST) FROM FIX (IDENT)
 *  (3) INTERCEPT RADIAL (DEG) TO FIX (IDENT)
 * @param arr,  array containing all words from a single instructment procedure
 * @param cur,  index of first unconsumed word from line
 * @param fix,  waypoint for which to store the vector restrictions
 * @return  index of first unconsumed word for procedure after parsing
 */
private int parseVector(String[] arr, VectorFix fix) {
  switch (arr[wordCounter]) {
    case "UNTIL":
      if (arr[wordCounter++1].contains(".")) {// '.' might cause problem
        fix.addDistanceFrom(fixes.get(arr[(wordCounter+=4)++]), arr[wordCounter-3]);
      } else {
        fix.addAltitudeRestriction("UNTIL", arr[(wordCounter+=1)++]);
    } case "INTERCEPT":
      fix.addInterceptRadial(fixes.get(arr[(wordCounter+=5)++]), arr[wordCounter-4]);
      break;
    default:
      break;
  } 
}

/**
 * Parses an array of a individual words, from immediately after
 * a keyword of vector as cur, parses for 
 *  (1) AT OR ABOVE (ALT)
 *  (2) AT OR BELOW (ALT)
 *  (3) SPEED (SPD)
 *  (4) (ALT)
 * @param arr,  array containing all words from a single instructment procedure
 * @param cur,  index of first unconsumed word from line
 * @param fix,  waypoint for which to store the vector restrictions
 * @return  index of first unconsumed word for procedure after parsing
 */
private int parseRestrictions(String[] arr, int cur, ProceduralFix fix) {
  while (!isKeyword(arr[cur])) {
    switch (arr[cur]) {
      // AT OR ABOVE & BELOW Restrictions
      case "AT":
        switch (arr[cur+2]) {
          case "ABOVE":
          case "BELOW":
            fix.addAltitudeRestriction(arr[cur+2], arr[cur+3]);
            cur+=3;
            break;
        } break;
      // Speed Restrictions always prepended by "SPEED"
      case "SPEED": 
        fix.addSpeedRestriction(arr[cur+1]);
        break;
      default:
        int altitude = Integer.parseInt(arr[cur]);
        if (altitude > 0) {
          fix.addSpeedRestriction("AT", altitude);
          cur+=1;
        }
        break;
    }
  }
}

private fixKeywords = ["FIX", "HDG", "TRK", "RNW", "HOLD", "TURN"];

private boolean isFixKeyword(String word) {
  for (int i = 0; i < fixKeywords.length; i++) 
    if (word.equalsIgnoreCase(fixKeywords[i]))
      return true;
  return false;
}