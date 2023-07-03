/* Copyright 2009-2022 David Hadka
 *
 * This file is part of the MOEA Framework.
 *
 * The MOEA Framework is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * The MOEA Framework is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with the MOEA Framework.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.moeaframework.util.progress;

import java.io.Serializable;

import org.moeaframework.Executor;
import org.moeaframework.core.Algorithm;

/**
 * A progress report, including the percent complete, elapsed time, and
 * remaining time.  These reports are generated by {@link ProgressHelper}.
 */
public class ProgressEvent implements Serializable {
	
	private static final long serialVersionUID = -1133068166971961110L;
	
	/**
	 * The executor from which these progress reports originate.
	 */
	private final Executor executor;

	/**
	 * The current {@link Algorithm} being run. 
	 */
	private final Algorithm currentAlgorithm;
	
	/**
	 * The current seed being evaluated, starting at 1.
	 */
	private final int currentSeed;
	
	/**
	 * The total number of seeds to be evaluated.
	 */
	private final int totalSeeds;
	
	/**
	 * {@code true} if this event was created in response to a seed finishing;
	 * {@code false} otherwise.  This indicates that new results are available
	 * in the executor.
	 */
	private final boolean isSeedFinished;
	
	/**
	 * The current number of objective function evaluations for the current
	 * seed.
	 */
	private final int currentNFE;
	
	/**
	 * The maximum number of objective function evaluations per seed.  If
	 * {@code -1}, then no maximum NFE is set.
	 */
	private final int maxNFE;
	
	/**
	 * The percent complete as a fraction between {@code 0} and {@code 1}.
	 */
	private final double percentComplete;
	
	/**
	 * The elapsed time in seconds.
	 */
	private final double elapsedTime;
	
	/**
	 * The estimated remaining time in seconds.
	 */
	private final double remainingTime;
	
	/**
	 * The maximum elapsed time per seed in seconds.  If {@code -1}, then no
	 * maximum time is set.
	 */
	private final double maxTime;

	/**
	 * Constructs a new progress report with the given values.
	 * 
	 * @param executor the executor from which these progress reports originate
	 * @param algorithm the current algorithm the executor is running
	 * @param currentSeed the current seed being evaluated, starting at 1
	 * @param totalSeeds the total number of seeds to be evaluated
	 * @param isSeedFinished {@code true} if this event was created in response
	 *        to a seed finishing; {@code false} otherwise
	 * @param currentNFE the current number of objective function evaluations
	 *        for the current seed.
	 * @param maxNFE the maximum number of objective function evaluations per
	 *        seed, or {@code -1} if not set
	 * @param percentComplete the percent complete as a fraction between
	 *        {@code 0} and {@code 1}
	 * @param elapsedTime the elapsed time in seconds
	 * @param remainingTime the estimated remaining time in seconds
	 * @param maxTime the maximum elapsed time per seed in seconds, or
	 *        {@code -1} if not set
	 */
	public ProgressEvent(Executor executor, Algorithm algorithm, 
			int currentSeed, int totalSeeds, boolean isSeedFinished, 
			int currentNFE,	int maxNFE, double percentComplete,
			double elapsedTime, double remainingTime, double maxTime) {
		super();
		this.executor = executor;
		this.currentSeed = currentSeed;
		this.currentAlgorithm = algorithm;
		this.totalSeeds = totalSeeds;
		this.isSeedFinished = isSeedFinished;
		this.currentNFE = currentNFE;
		this.maxNFE = maxNFE;
		this.percentComplete = percentComplete;
		this.elapsedTime = elapsedTime;
		this.remainingTime = remainingTime;
		this.maxTime = maxTime;
	}
	
	/**
	 * Returns the executor from which these progress reports originate.
	 * 
	 * @return the executor from which these progress reports originate
	 */
	public Executor getExecutor() {
		return executor;
	}

	/**
	 * Returns the algorithm that is currently running
	 * 
	 * @return the algorithm currently running
	 */
	public Algorithm getCurrentAlgorithm() {
		return currentAlgorithm;
	}

	/**
	 * Returns the current seed being evaluated, starting at 1.  Note that after the last seed completes, this value
	 * will return 1 + {@code getTotalSeeds()}.
	 * 
	 * @return the current seed being evaluated, starting at 1
	 */
	public int getCurrentSeed() {
		return currentSeed;
	}

	/**
	 * Returns the total number of seeds to be evaluated.
	 * 
	 * @return the total number of seeds to be evaluated
	 */
	public int getTotalSeeds() {
		return totalSeeds;
	}

	/**
	 * Returns {@code true} if this event was created in response to a seed
	 * finishing; {@code false} otherwise.  This indicates that new results are
	 * available in the executor.
	 * 
	 * @return {@code true} if this event was created in response to a seed
	 *         finishing; {@code false} otherwise
	 */
	public boolean isSeedFinished() {
		return isSeedFinished;
	}

	/**
	 * Returns the current number of objective function evaluations for the
	 * current seed.
	 * 
	 * @return the current number of objective function evaluations for the
	 *         current seed
	 */
	public int getCurrentNFE() {
		return currentNFE;
	}

	/**
	 * Returns the maximum number of objective function evaluations per seed,
	 * or {@code -1} if not set.
	 * 
	 * @return the maximum number of objective function evaluations per seed
	 */
	public int getMaxNFE() {
		return maxNFE;
	}

	/**
	 * Returns the percent complete as a fraction between {@code 0} and
	 * {@code 1}.
	 * 
	 * @return the percent complete as a fraction between {@code 0} and
	 *         {@code 1}
	 */
	public double getPercentComplete() {
		return percentComplete;
	}

	/**
	 * Returns the elapsed time in seconds.
	 * 
	 * @return the elapsed time in seconds
	 */
	public double getElapsedTime() {
		return elapsedTime;
	}

	/**
	 * Returns the estimated remaining time in seconds.
	 * 
	 * @return the estimated remaining time in seconds
	 */
	public double getRemainingTime() {
		return remainingTime;
	}
	
	/**
	 * Returns the maximum elapsed time per seed, or {@code -1} if not set.
	 * 
	 * @return the maximum elapsed time per seed
	 */
	public double getMaxTime() {
		return maxTime;
	}

}
