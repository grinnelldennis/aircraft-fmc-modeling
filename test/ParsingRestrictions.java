

// cur is tracks current position within arr 

private void parseProcedure(String[] arr, int cur, 
                              ArrayList<ProceduralFix> procedure) {
  if (cur >= arr.length)
    return procedure;

  ProceduralFix waypoint;

  switch(arr[cur]) {
    case "FIX":
      if (arr[cur+1].equals("OVERFLY")) {
        waypoint = new NavigationFix(arr[cur+2]);
        cur+=2;
        parseRestrictions(arr, cur, fix);
      } else {
        waypoint = new NavigationFix(arr[cur+1]);
        cur+=1;
      } break;
    case "HDG":
      //HDG (DEG) UNTIL (ALT)
      waypoint = new VectorFix();
      break;
    case "TRK":
      //TRK (DEG) UNTIL (DST) FROM FIX (IDENT)
      //TRK (DEG) INTERCEPT RADIAL (DEG) TO 
      waypoint = new VectorFix();
      break;
    case "RNW":
      waypoint = new NavigationFix(arr[cur+1]);
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
private int parseVector(String[] arr, int cur, VectorFix fix) {
  switch (arr[cur]) {
    case "UNTIL":
      if (arr[cur+1].contains(".")) {// '.' might cause problem
        fix.addDistanceFrom(fixes.get(arr[cur+4]), arr[cur+1]);
        cur+=5;
      } else {
        fix.addAltitudeRestriction("UNTIL", arr[cur+1]);
        cur+=2;
    } case "INTERCEPT":
      fix.addInterceptRadial(fixes.get(arr[cur+5]), arr[cur+2]);
      cur+=5;
      break;
    default:
      break;
  } 
}

/**
 * Parses an array of a individual words, from immediately after
 * a keyword of vector as cur
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