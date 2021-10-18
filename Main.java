class Main {
  public static void main(String[] args) {
    try{
      for(int i = 0; i < 8; i++){
			  Problem.getInstance().id = i;
        System.out.printf("\nResolviendo problema %s\n", i);
        (new Swarm()).execute();
      }
    }catch(Exception e) {
      System.err.println(
        String.format("%s \n%s", e.getMessage(), e.getCause()));
    }
  }
}