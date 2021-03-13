package ter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.util.Vector;

import net.sf.jargsemsat.jargsemsat.datastructures.*;

public class TER {

	public static void readingAF(Vector<String> argument, Vector<String[]> atts, String af_file) {
		argument.clear();
		atts.clear();
		String file_arguments = new String();// tableau pour stocker les argument du fichier
		String link = new String(af_file);// chemin vers le fichier � lire
		String[] splited = new String[2];
		Boolean attaque = false;
		BufferedReader reader;

		try {
			reader = new BufferedReader(new FileReader(link));
			String line = reader.readLine();
			while (line != null) {
				try {
					if (line.equals("#")) {
						attaque = true;
					} else {
						file_arguments = line;
						if (attaque == false) {
							argument.add(file_arguments);
							// System.out.println("ajout d'un argument.");
						} else {
							splited = file_arguments.split(" ");
							atts.add(splited);
							// System.out.println("ajout d'une attaque.");
						}
					}
				} catch (NumberFormatException e) {
					System.err.println("Erreur dans le try : " + line + ".");
				}
				line = reader.readLine();
			}
		} catch (Exception ex) {
			System.err.println(ex.getMessage() + "Excep 2");
		}

	}

	public static void readingModels(Vector<String> models, String af_file) {
		models.clear();
		String file_model = new String();// Pour stocker les mod�les 
		String link = new String(af_file);// path to the file to read
		BufferedReader reader;

		try {
			reader = new BufferedReader(new FileReader(link));
			String line = reader.readLine();
			while (line != null) {
				try {
					file_model = line;
					models.add(file_model);
					// System.out.println("model added.");
				} catch (NumberFormatException e) {
					System.err.println("Error : " + line + ".");
				}
				line = reader.readLine();
			}
		} catch (Exception ex) {
			System.err.println(ex.getMessage() + "Excep 2");
		}

	}

	public static void main(String args[]) {
		Vector<String> argument = new Vector<String>();
		Vector<String> model = new Vector<String>();
		Vector<String[]> atts = new Vector<String[]>();
	//Reading Af's files and calculate the distances
		File dir = new File(".\\Afs");
		File[] liste = dir.listFiles();
		for (File item : liste) {
			if (item.isFile()) {
				for (int i = 1; i < liste.length - 1; i++) {
					System.out.format("****************File: %s%n", item.getName() + "****************");
					readingAF(argument, atts, ".\\Afs\\" + item.getName());
					readingModels(model, ".\\modeles.txt");
					DungAF af = new DungAF(argument, atts);
					Models mod = new Models(model);
					DistanceHamming dm = new DistanceHamming();
					CalculDistance.calculDistance(af, mod, dm);
					System.out.println("Distances vector of the extension of " + item.getName()
							+ " with all the models: " + mod.getDistance());
				}
			}

		}

	}
}
