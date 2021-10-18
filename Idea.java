public class Idea {

    private Problem instance = Problem.getInstance();
    protected final int type = instance.type; 
    private final int id = instance.id;
    protected final int nVariables = instance.dimension[id]; 

	  private int[] x = new int[nVariables];

    public Idea() {
        for (int j = 0; j < nVariables; j++) {
            x[j] = StdRandom.uniform(2);  
        }
    }

    protected boolean isBetterThan(Idea g){
      return fitness() > g.fitness();
    }

    private float fitness() { 
      float beneficio = 0;
      for(int i = 0; i < nVariables; i++){
        beneficio += x[i] * instance.prices[id][i];
      }
      return beneficio;
    }

    protected boolean isFeasible() {
		return type == 0 ? isFeasibleCapability() : isFeasibleMultidimensionalCapability();
    }

    private boolean isFeasibleCapability() {
       int suma = 0;
      for(int i = 0; i < nVariables; i++){
        suma += x[i] * instance.weights[id][0][i];
      }
      return suma <= instance.capacity[id][0];
    }

	  private boolean isFeasibleMultidimensionalCapability() {
		  return true;
      /*int suma;
      for(int j = 0; j < instance.numMochilas; j++){
        for(int i = 0; i < nVariables; i++){
          suma = 0;
          suma += instance.weights[id][j][i];
        }
     }
      return suma >= instance.capacity[id][j];*/
    }

	protected void repare() {
    
	}

  /*protected void move(Idea g, float w, float c1, float c2) {
    for (int j = 0; j < nVariables; j++) {
          /* update velocity */
          //v[j] = v[j] * w
                  //+ c1 * StdRandom.uniform() * (pBest[j] - x[j])
          //        + c2 * StdRandom.uniform() * (g.x[j] - x[j]);
          /* update position */
        //  x[j] = toBinary(x[j] + v[j]);
      //}
    //}

	private float optimal() {
		return instance.optimal[id];
	}

	private float diff() {
		return optimal() - fitness();
	}

	private float rpd() {
		return diff() / optimal() * 100f;
	}

    protected void copy(Object object)  {
        if (object instanceof Idea) {
            System.arraycopy(((Idea) object).x, 0, this.x, 0, nVariables);
        }
    }

    private int toBinary(double x) {
        return StdRandom.uniform() <= (1 / (1 + Math.pow(Math.E, -x))) ? 1 : 0;
    }

    @Override
    public String toString() {
		return String.format(
			"optimal value: %.1f, fitness: %.1f, diff: %s, rpd: %.2f%%, x: %s", 
			optimal(), 
			fitness(), 
			diff(),
			rpd(),
			java.util.Arrays.toString(x)
		);
    }
}