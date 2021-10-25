import java.util.ArrayList;

public class Swarm {

	private int nIdeas = 20;
  private final int T = 10; //Cantidad iteraciones
  private final double PReplace = 0.5; /* Probabilidad de cambiar     el centro del cluster por una idea aleatoria */
  private final double PGeneration = 0.5; /* Probabilidad que define   el metodo de generacion ideas entre OneCluster y TwoCluster  */
  private final double POneCluster = 0.5; /* Probabilidad que determina el como se va a generar una nueva idea cuando se usa OneCluster */
  private final double PTwoCluster = 0.5; /* Probabilidad que determina el como se va a generar una nueva idea cuando se usa TwoCluster */

  private ArrayList<Idea> swarm = null;
  private Idea g = null;

	private long sTime, eTime;

    public void execute() {
		  startTime();
      init();
      run();
		  endTime();
      log();
    }

	private void startTime() {
		sTime = System.currentTimeMillis();
	}

  private void init() {
    swarm = new ArrayList<>();
    g = new Idea();
    Idea p = null;
    for (int i = 0; i <= nIdeas; i++) {
      p = new Idea();
      if (!p.isFeasible()){ 
       //System.out.printf("Reparando idea %s: ",i);
       p.repare();
      }
      else{
        //System.out.printf("Idea %s pasa sin problemas\n",i);
      }
      swarm.add(p);
    }
    g.copy(swarm.get(0));
    /*for (int i = 1; i < nIdeas; i++) {
      if (swarm.get(i).isBetterThan(g)) {
          g.copy(swarm.get(i));
      }
    }*/
    System.out.printf("Primera idea: %s\n",g);
  }

  private void run() {
    NumClusters();
    int t = 1;
    while (t <= T) {
      for (int i = 0; i < nIdeas; i++) {
        if (swarm.get(i).isBetterThan(g)) {
          g.copy(swarm.get(i));
        }
      }
      //for (int i = 0; i < nIdeas; i++) {
        //do{
          //  swarm.get(i).move(g, w, c1, c2);
        //}while(!swarm.get(i).isFeasible());
        /*swarm.get(i).move(g, w, c1, c2);
        if (!swarm.get(i).isFeasible()) {
          swarm.get(i).repare();
        }*/
    t++;   
    }
                
  }

  private int NumClusters(){
    int k = 0;
    if(nIdeas <= 10){
      k = 2;
    }
    if(nIdeas > 10 && nIdeas <= 30){
      k = 3;
    }
    if(nIdeas > 30){
      k = 4;
    }
    return k;
  }


	private void endTime() {
		eTime = System.currentTimeMillis();
	}

    private void log() {
        StdOut.printf("Mejor idea: %s\n",g);
        //StdOut.printf("%s, s: %s, t: %s\n", g, StdRandom.getSeed(), (eTime - sTime));
    }
}