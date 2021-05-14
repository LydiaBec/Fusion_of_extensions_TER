Ce travail a été réalisé par Akroun Yanis-Fady et Bechroune Lydia

De l'Université de Paris

encadré par Jean-Guy Mailly et Jérôme Delobelle.

Pour la fusion de systèmes d'argumentation basée sur les extensions.


Le dossier comporte:

-Les différentes classes de notre projet, la classe "Luncher" étant la classe main.

-Un dossier "Afs" regroupant les systèmes d'argumentation voulant etre fusionés.

-Un fichier Contrainte.txt, qui représente la contrainte d'intégrité.

-Ainsi que différente bibliothèque tel que Dung-1.4.jar etorg.sat4j.core-2.3.1.jar, qui contribuent au bon fonctionnement de notre programme.

Les fichiers des AF ainsi que la contrainte d'intégrité doivent suivre un format spécifique.


Format du fichier AF:

Nous avons suivi un format largement utilisé par la communauté qui est  le format tgf pour Trivial Graph Format. 

Il consiste en une énumération de tous lesnoeuds en premier lieu, et d’une  énumération de tous les arcs en second lieu, les deuxparties étant séparé  par le caractère '#'.



Format du fichier de la contraint d'integrité:

Pour le format de notre contrainte d’intégrité, nous avons choisi le format DIMACS CNF.

Exemple:

Pour la formule ( a1 ou non(a3) ) et ( a2 ou a3 ou non(a1) )

le fichier sera :

p cnf 3 2

1 -3 0

2 3 -1 0

Comment utiliser:

jarfile <af_path> <contrainte_path> <distance> <aggregation_function>
	
	-af_path: le chemin vers le dossier contenant les AFs.
	
	-contrainte_path: le chemin vers le fichier de la contraintre d'intégrité.
	
	-distace: Distance voulu: 
		HM pou Hamming.
	
	-aggregation_function: la fonction d'agrégation voulu :
	
	SUM pour la somme, 
	
	MIN pour le minimum, 
	
	MAX pour le maximum, 
	
	MUL pour la multiplication, 
	
	MEAN pour la moyenne, 
	
	MED pour la mediane, 
	
	LMIN pour LexiMin, 
	
	LMAX pour LexiMax.
