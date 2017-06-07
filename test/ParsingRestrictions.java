

// cur is tracks current position within arr 

private void parseProcedure(String[] arr, int cur) {
  if (cur >= arr.length)
    return;

  ProceduralFix waypoint;

  switch(arr[cur]) {
    case "FIX":
      if (arr[cur+1].equals("OVERFLY")) {
        waypoint = new NavigationFix(arr[cur+2]);
        cur+=2;
      } else {
        waypoint = new NavigationFix(arr[cur+1]);
        cur+=1;
      } break;
    case "HDG":
      waypoint = new VectorFix();
      break;
    case "TRK":
      //TRK (DEG) UNTIL (ALT)
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

    parseProcedure(arr, cur+=1);

  }

}

// returns cur; increment/consume cur iff parsed
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