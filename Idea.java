import java.util.Arrays;
import java.util.Random;

public class Idea {

    private Problem instance = Problem.getInstance();
    protected final int type = instance.type; 
    private final int id = instance.id;
    protected final int nVariables = instance.dimension[type][id]; 

	  private int[] x = new int[nVariables];

    public Idea() {
        for (int i = 0; i < nVariables; i++) {
            x[i] = StdRandom.uniform(2);  
        }
    }

    protected boolean isBetterThan(Idea g){
      return fitness() > g.fitness();
    }

    private float fitness() { 
      float beneficio = 0;
      for(int i = 0; i < nVariables; i++){
        beneficio += x[i] * instance.prices[type][id][i];
      }
      return beneficio;
    }

    protected boolean isFeasible() {
	  	return type == 0 ? isFeasibleCapability() : isFeasibleMultidimensionalCapability();
    }

    private boolean isFeasibleCapability() {
      int suma = 0;
      for(int i = 0; i < nVariables; i++){
        suma += x[i] * instance.weights[type][id][0][i];
      }
      return suma <= instance.capacity[type][id][0];
    }

	  private boolean isFeasibleMultidimensionalCapability() {
      //System.out.println("\n");
      int j = 0;
      int suma;
      boolean F = true;
      while(j < instance.backpacks[type][id] && F){ 
        suma = 0; 
        for(int i = 0; i < nVariables; i++){
          suma += x[i] * instance.weights[type][id][j][i];
        }
        //System.out.printf("Mochila %s, Suma: %s, capacidad: %s\n", j,suma, instance.capacity[type][id][j]);
        F = suma <= instance.capacity[type][id][j];
        j++;
     }
     return F;
    }

    protected void repare() {
      System.out.println(Arrays.toString(x));
      int size = 0;
      int sum;
      int k = 0;
      for(int i = 0; i < nVariables; i++){
        if (x[i] == 1){
          size++;
        }
      }
      int values[] = new int[size];
       for(int i = 0; i < nVariables; i++){
        if (x[i] == 1){
          values[k] = i;
          k++;
        }
      }
      do{
        Random generator = new Random();
        int randomIndex = values[generator.nextInt(values.length)];
        sum = 0;  
        for(int j = 0; j < instance.backpacks[type][id]; j++){
          for(int i = 0; i < nVariables; i++){
              System.out.printf("Reemplazando el 1 en la posición %s de %s\n",randomIndex,Arrays.toString(values));
              x[randomIndex] = 0;
              break;
            }
        }
        //System.out.printf("Nuevo valor: %s\n",Arrays.toString(x));
      } while(!isFeasible());
        //System.out.println("Idea factible\n");
        values = null;
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
		return instance.optimal[type][id];
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