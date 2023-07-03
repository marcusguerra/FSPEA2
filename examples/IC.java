

import java.io.IOException;
import java.lang.Math;
import java.util.Random;
import org.moeaframework.*;
import org.moeaframework.Analyzer;
import org.moeaframework.core.Problem;
import org.moeaframework.core.Solution;
import org.moeaframework.core.variable.BinaryIntegerVariable;
import org.moeaframework.core.variable.EncodingUtils;
import org.moeaframework.problem.AbstractProblem;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  

public class IC {

	public static class NextRealeaseProblem extends AbstractProblem {

		
		public NextRealeaseProblem() {
			super(500, 2, 0, 0);
		}
		private int custoRequisitos[]  = new int[500];
		private int importanciaRequisitos[]  = new int[750];
		private int importanciaClientes[]  = new int[750];

		

		public void iniciaCR(int numRequisitos){
			Random gerador = new Random();
			for (int i = 0; i < numRequisitos; i++){
            	custoRequisitos[i] = gerador.nextInt(5) + 1;
        	}
		}

		public void iniciaIR(int numRequisitos){
			Random gerador = new Random();
			for (int i = 0; i < numRequisitos; i++){
            	importanciaRequisitos[i] = gerador.nextInt(5) + 1;
        	}
		}
		
		public void iniciaIC(int numClientes){
			Random gerador = new Random();
			for (int i = 0; i < numClientes; i++){
            	importanciaClientes[i] = gerador.nextInt(5) + 1;
        	}
		}

		public int custoTotal(int []x, int numRequisitos) {
        	int custoTotal = 0;
        	for (int i = 0; i < numRequisitos; i++){
            	custoTotal += custoRequisitos[i] * Math.round(x[i]);
        	}
			return custoTotal;
		}
		
		public int scorei(int [] x, int numRequisitos, int numClientes){
       	 	int scorei = 0;
        	for (int i =0; i < numRequisitos; i++){
        		int score=0;
            for (int k = 0; k <numClientes; k++){
                score += importanciaRequisitos[i] * importanciaClientes[k];
            scorei += score * Math.round(x[i]);
        	}
		}
		return -scorei;
		}
		
		@Override
		public void evaluate(Solution solution) {
			int numRequisitos = 500;
			int numClientes = 20;
			int[] x = EncodingUtils.getInt(solution);
			int ct = custoTotal(x, numRequisitos);
			int sci = scorei(x, numRequisitos, numClientes);
			solution.setObjective(0, ct);
			solution.setObjective(1, sci);
		}

		public void addq(){
			this.q++;
		}
		public int getq(){
			return q;
		}
		@Override
		public Solution newSolution() {
			int numRequisitos = 500;
			int numClientes = 20;
			if(q ==0){
				iniciaCR(numRequisitos);
				iniciaIR(numRequisitos);
				iniciaIC(numClientes);
			}
			Solution solution = new Solution(numRequisitos, 2, 0);
			for (int i = 0; i < numRequisitos; i++){
           	 solution.setVariable( i , new BinaryIntegerVariable(0, 1));
        	}
			
			if(q == 0)
				addq();
			return solution;
		}

		
	
		
	}
	
	public static void main(String[] args) throws IOException {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		System.out.println(dtf.format(now));  	

		for(int i=0; i < 1; i++){
		System.out.println(i);

		Problem problem = new NextRealeaseProblem();	
		String[] algorithms = {"SPEA2",
								"ISPEA3"
								};
			Executor executor = new Executor()
			.withProblem(problem)
			.withMaxEvaluations(1000);

			Analyzer analyzer = new Analyzer()
			.withProblem(problem)
			.includeHypervolume()
			.includeGenerationalDistance()
			.includeInvertedGenerationalDistance()
			.includeMaximumParetoFrontError()
			.includeSpacing()
			.includeAdditiveEpsilonIndicator()
			.includeR2()
			.includeR3()
			.showStatisticalSignificance();
		
		for (String algorithm : algorithms) {
			analyzer.addAll(algorithm, 
				executor.withAlgorithm(algorithm).runSeeds(1));
		}
		analyzer.display();
	}
		LocalDateTime now2 = LocalDateTime.now();  
		System.out.println(dtf.format(now));  	
		System.out.println(dtf.format(now2));  	

	}
}

		