class Main {
  public static void main(String[] args) {
    try{
      for(int j = 0; j <= 1; j++){
        Problem.getInstance().type = j;
        for(int i = 0; i < 2; i++){
			    Problem.getInstance().id = i;
          for(int k = 1; k <= 30; k++){
            System.out.printf("\nResolviendo problema tipo %s instancia %s experimento %s\n", j, i, k);
            (new Swarm()).execute();
          }
        }
      }
    }catch(Exception e) {
      System.err.println(
        String.format("%s \n%s", e.getMessage(), e.getCause()));
    }
  }
}