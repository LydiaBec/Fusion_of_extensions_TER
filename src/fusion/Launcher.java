package fusion;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import net.sf.jargsemsat.jargsemsat.datastructures.*;

public class Launcher {
	static Vector<Float> resultat = new Vector<>();
	static Collection<Collection<String>> vec_candidats = new Vector<>();

	public static void main(String args[]) throws IOException {

		List<String> args_d = null;
		if (args.length != 4) {
			System.out.println("Usage: jarfile <af_path> <constrain_path> <distance> <aggregation_function>");
			System.out.println("\taf_path: The path to the tgf af file.");
			System.out.println("\tconstrain_path: The path to the constrain file.");
			System.out.println("\tdistace: Distance type one of: \"HM\" for Hamming, \"LV\" for Leveinshtein.");
			System.out.println(
					"\taggregation_function: SUM for sum, MIN for Minimum, MAX for Maximum, MUL for Multiplication, MEAN for mean,"
							+ " MED for Mediane, LMIN for LexiMin, LMAX for LexiMax.");
			System.out.println(
					"The default parameters used are : <af_path> = Afs, <constrain_path> = constraint.txt, <distance> = HM, <aggregation_function> = SUM ");
			args_d = Arrays.asList("Afs", "Sat.txt", "HM", "SUM");
		} else
			args_d = Arrays.asList(args);

		if (!(new File(args_d.get(0))).isDirectory()) {
			System.out.println("\"" + args_d.get(0) + "\" is not a valid file");
			return;
		}
		if (!Files.isWritable(Paths.get(args_d.get(1)))) {
			System.out.println("\"" + args_d.get(1) + "\" is not a valid file");
			return;
		}

		Distance distance = null;
		switch (args_d.get(2)) {
		case "HM":
			distance = new DistanceHamming();
			break;
		case "LV":
			distance = new DistanceLevenshtein();
			break;
		default:
			System.err.println("Error distance \"" + args_d.get(2) + "\" not handled");
			return;
		}
		Aggregate_Function as = null;

		switch (args_d.get(3)) {
		case "SUM":
			as = new AggregateSum();
			break;
		case "MIN":
			as = new AggregateMin();
			break;
		case "MAX":
			as = new AggregateMax();
			break;
		case "MUL":
			as = new AggregateMul();
			break;
		case "MEAN":
			as = new AggregateMean();
			break;
		case "LMIN":
			as = new AggregateLexiMin();
			break;
		case "MED":
			as = new AggregateMed();
			break;
		case "LMAX":
			as = new AggregateLexiMax();
			break;
		default:
			System.err.println("Error Agregation function \"" + args_d.get(3) + "\" not handled");
			return;
		}
		//Calculation of execution time
		long tempsDebut = System.nanoTime(); 
		// Reading model
		Collection<Collection<String>> model = ReadingFiles
				.transformeModeles(ReadingFiles.readingConstrainte(args_d.get(1)));
		Models mod = new Models(model);
		String sem = new String();
		
		Vector<String> fileName = ReadingFiles.nameFile(args_d.get(0));
			boolean supported = true;
			int j = 0;
		
			if(mod.getModels().isEmpty()) { // Si il n'y a pas de modele ou pas de modele.
			for (DungAF af : ReadingFiles.Lectures(args_d.get(0))) {
				if(addMod){
                    			 model = toCollec(af.getArguments());
                   			 mod = new Models(model);
                   			 addMod = false;
               				 } 
               		 mainFunc(fileName, af, mod, sem, distance, supported, j);
               		 j++;
            		}
            		mainAgregate(mod, as, supported);
			}else { // si il y a un modele
				 for (DungAF af : ReadingFiles.Lectures(args_d.get(0))) {
                mainFunc(fileName, af, mod, sem, distance, supported, j);
                j++;
            }
            mainAgregate(mod, as, supported);
        }
    }
		    public static void mainFunc(Vector<String> fileName, DungAF af, Models mod, String sem, Distance distance, boolean supported, int j){
				if (fileName.get(j).toString().endsWith("co")) {
					sem = "CO";
				} else {
					if (fileName.get(j).toString().endsWith("gr")) {
						sem = "GR";
					} else {
						if (fileName.get(j).toString().endsWith("st")) {
							sem = "ST";
						} else {
							if (fileName.get(j).toString().endsWith("pr")) {
								sem = "PR";

							} else {
								if (fileName.get(j).toString().endsWith("txt")
										|| fileName.get(j).toString().endsWith("tgf")) {
									sem = "CO";
								} else {
									System.err.println("file extension not supported : " + fileName.get(j).toString()
											+ "\nsupported extentions: .co for complet, .pr for preferred, gr for grounded, st for stable ");
									supported = false;
									break;
								}

							}

						}
					}
				}

				// calculating distance
				CalculDistance.calculDistance(af, mod, distance, sem);
			}
		    public static void mainAgregate(Models mod, Aggregate_Function as, boolean supported){

			if (supported) {
				resultat = as.choosenAggregate(mod);
				System.out.println("Distance final apres aggregation = " + resultat);
				vec_candidats = mod.getCondidats(resultat);
				System.out.println("Le(s) candidat(s) selectioné(s) est(sont) : " + vec_candidats);

			} else {
				System.err.println("Interruped process file extension not supported ");

			}

		}
	}
		long tempsFin = System.nanoTime(); 
		double seconds = (tempsFin - tempsDebut) / 1e9; 
		System.out.println();
		System.out.println("Pour "+ args_d.get(0)+" Arguments Opération effectuée en: "+ seconds + " secondes.");
	}
	
}
