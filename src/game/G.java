package game;

import java.util.Random;

import util.Vector2d;
import ai_methods.AI;

public class G {

	
	public static Random rgen;
	public static World world;
	
	public static boolean oneTime = true;
	public final static boolean verbose = false;
	public final static int CUR_LOG_LEVEL = 0;
	
	public final static int NUM_SCRUM = 20;
	public final static int FOOD_INTERVAL = 100;
	
	public static boolean pauseNextIT = false;
	
	public static AI aiEngine;
	
	// Simulation and Genetic Values
	public static final double PERTURBATION_CONSTANT = 0.1;
	
	// Default Scrum Values
	public static final int D_SIZE = 10;
	public static final int D_ENERGY = 1000;
	public static final int D_ENERGY_USE_RATE = 100;
	public static final int D_NUM_SENSORS = 8;
	public static final double D_MAX_MAGNITUDE = 5;
	public static final double D_SENSOR_OFFSET = 5;
	public static final double D_SENSOR_ANGLE_SPREAD = 45;
	public static final double D_SENSOR_RANGE = 300;
	public static final double D_SENSOR_MAPPING_SLOPE = -1.0 / 40.0;
	public static final double D_WALL_BOUNCE_ENERGY_COEF = 0.96;
	public static final double D_ACCEL_K = 1.0 / 60.0;
	public static final double D_ROT_K = 180.0 / 60.0;
}
