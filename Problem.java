public class Problem {

  private static Problem problem;
	public int id;
  protected int type = 0;

    // Dim1: Numero de variables
    protected int[] dimension = 
	{
		5,6,7,7,8,10,15,24
	};
    
  // Dim1: instancia
  // Dim2: Valor  
	protected double[][] prices = 
	{
		{12,7,11,8,9},
		{56,59,80,64,75,17},
 		{31,10,20,19,4,3,6},
		{41,50,49,59,55,57,60},
    {25,35,45,5,25,3,2,2},
		{23,31,29,44,53,38,63,85,89,82},
		{70,73,77,80,82,87,90,94,98,106,110,113,115,118,120},
		{825594,1677009,1676628,1523970,943972,97426,69666,1296457,1679693,1902996,1844992,1049289,1252836,1319836,953277,2067538,675367,853655,1826027,65731,901489,577243,466257,369261}
    };

    // Dim1: instancia
    // Dim2: Posicion
    // Dim3: Pesos
    protected double[][][] weights = {  
	{
		{24,13,23,15,16},
	},{
		{50,50,64,46,50,5},
    },{
		{70,20,39,37,7,5,10}
    },{
		{442,525,511,593,546,564,617}
	},{
		{350,400,450,20,70,8,5,5}
	},{
		{92,57,49,68,60,43,67,84,87,72}
	},{
		{135,139,149,150,156,163,173,184,192,201,210,214,221,229,240},
	},{
		{382745,799601,909247,729069,467902,44328,34610,698150,823460,903959,853665,551830,610856,670702,488960,951111,323046,446298,931161,31385,496951,264724,224916,169684},
	}};

  // Dim1: instancia
  // Dim2: Valor
    protected double[][] capacity = 
	{
		{51},{150},{107},{1735},{900},{309},{1747},{6404180} 
	};

  // Dim1: Valor
	protected float[] optimal = //solucion final de cada instancia
	{
		26f,198f,55f,172f,104f,309f,916f,13549094f
	};

  public static Problem getInstance() {
      if (problem == null) {
          createInstance();
      }
      return problem;
  }

  private static void createInstance() {
    synchronized (Problem.class) {
        if (problem == null) {
          problem = new Problem();
        }
    }
  }
}