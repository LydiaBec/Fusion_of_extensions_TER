package ter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.util.Vector;

import net.sf.jargsemsat.jargsemsat.datastructures.*;

public class Launcher {
	public static void readingAF(Vector<String> argument, Vector<String[]> atts, String af_file) {
		argument.clear();
		atts.clear();
		String file_arguments = new String();// tableau pour stocker les argument du fichier
		String link = new String(af_file);// chemin vers le fichier à lire
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
		String file_model = new String();// Pour stocker les modèles 
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
		Vector<DungAF> afs = new Vector<DungAF>();
		int j=0;
	//Reading model
		Models mod = new Models(model);
		readingModels(model, ".\\modeles.txt");
	//Reading Af's files
		File dir = new File(".\\Afs");
		File[] liste = dir.listFiles();
		for (File item : liste) {
			if (item.isFile()) {
				for (int i = 1; i < liste.length - 1; i++) {
					readingAF(argument, atts, ".\\Afs\\" + item.getName());
					//Création de l'af
					DungAF af = new DungAF(argument, atts);
					//ajout de l'af a l'ensemble des afs
					afs.add(af);	
				}
			}

		}
		//calcul de la distance
		DistanceHamming dm = new DistanceHamming();
		//iterateur pour parcourir l'ensemble des afs
		Iterator<DungAF> iterator_af = afs.iterator();
        	while(iterator_af.hasNext()){
			j++;
		System.out.format("****************File:AF"+j+".tgf****************");

			//calcul de la distance
			CalculDistance.calculDistance(iterator_af.next(), mod, dl);
			//affichange de la distance
			System.out.println("Distances vector of the extension of AF" +j
				+ " with all the models: " + mod.getDistance());
        	}
		
		
		

	}
}
