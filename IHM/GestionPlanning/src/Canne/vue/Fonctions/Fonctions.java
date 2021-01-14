/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Canne.vue.Fonctions;

//Autres
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

//Projet :
import Canne.dao.Maria.*; // BD : Datasource, Salle, Films, Vip, Cat
import Canne.dao.modele.*; // Les classes : Film, Seance, Vip
import java.util.HashMap;

/**
 *
 * @author loann
 */
public class Fonctions {

    DataSource dataSourceDAO;
    Connection connexionBD;
    static MariaFilmDao filmDao = new MariaFilmDao();
    static List<Film> listFilms;
    
    static MariaSalleDao salleDao = new MariaSalleDao();
    static List<Salle> listSalles;
    
    private int idPlanning;
    
    //Utiles en static
    HashMap<String, List<Salle>> listeSallesParCat;
    HashMap<String, List<Categorie>> listeCatsParCreneaux ;
    HashMap<String, String> horaireCreneaux;
    String day = "Jour 1";
    
    //Finale
    List <List<Seance>> jours = new ArrayList<List<Seance>>();
    
    public Fonctions(DataSource data, Connection connectionBD, int idPlanning)
    {
    	this.idPlanning = idPlanning;
        this.dataSourceDAO = data;
        this.connexionBD = connectionBD;
    }
    
    public void genererPlaning() throws SQLException
    {
        //connexion a la BD
        connexionBD = dataSourceDAO.getConnection();
        
        //Films
        filmDao.setDataSource(dataSourceDAO);
        filmDao.setConnection(connexionBD);
        listFilms = filmDao.listeDesFilms();
        
        //Salles
        salleDao.setDataSource(dataSourceDAO);
        salleDao.setConnection(connexionBD);
        listSalles = salleDao.getPlaceOrdered();
        
        
        //Un certain regard
        
        jours = generer();
			     
    }
    
    
    public List <List<Seance>> generer()
    /*
            CM --> salle DEBussy ou Buñuel
            Debussy : 1,2,3,4,5,6,7,8
            Bunuel : 1,2,3,4,5,6,7,8
            
            Seance(int idSenace, int idPlanning, int idFilm, int idSalle, String jour, String horaire)
            Film(int id, String nomFilm, int idRealisateur, int duree, String categorie)
    */
    {

       //On trie toutes les salles par Cat 
       listeSallesParCat = listeSallesParCat();
       
       //On trie les Catégorie par Créneaux horaires
       listeCatsParCreneaux = listeCatParCreneaux();
       
       //Horaire des crénaux
       horaireCreneaux = horaireCreneaux();
       List <List<Seance>> jours = new ArrayList<List<Seance>>(); //Va avoir toutes les séances par jour jour 15 = CM
       
       //On regarde quand cette catégorie peut être joué
       //addDay("Jour 1");
       //Si c'est un Seance du lendemain = on la reprogramme au lendemain (LM ou UCR)
       
       
       // 1 Planning Des 14 Premiers jours (Pas des cours métrage)
       
       // 2 Planning des cours métrage (que le 15)
       jours.add(creerJournee15());
      
        return jours;
        
    }
    
    public static String ajouterHeure(String Heure,int Duree)
        /*
        Ajouter des minutes à une Heure == Ajouter une seance
        <24 --> xxhyy
        >24 --> null
        */
    {
        
        String heureFinale = "00h00";
        
        //Heure et minutes de Durée
        int min = Duree%60;
        int heure = (Duree-min)/60;
        
        //Heure et minutes de Heure
        int heure2 = Integer.parseInt(Heure.substring(0, 2));
        int min2 = Integer.parseInt(Heure.substring(3, 5));
       
        //Addition
        min = min+min2;
        heure = heure+heure2;
        
        //Format Horaire respecter ?
        if (min >= 60 && min<=120)
        {
            min = min%60;
            heure++;
        }
        if (heure >= 24) 
        {
            return null;
        }
        
        //concacténation : Forme horaire a respecter : xxhyy
        if (min < 10)
            heureFinale = heureFinale.replace("h00", "h0"+min);
        else
            heureFinale = heureFinale.replace("h00", "h"+min);
        if (heure < 10)
            heureFinale = heureFinale.replace("00h", "0"+heure+"h");
        else
            heureFinale = heureFinale.replace("00h", heure+"h");
            
        //System.out.println(heureFinale);
        
        return heureFinale;
    }
    
    public static HashMap<String, List<Salle>> listeSallesParCat()
            /*
            Renvoit une liste de listes contenant toutes les salles disponibles pour cette catégorie
            Exemples : toutes les salles qui acceptent les long métrages
            */
    {
        List<Salle> listeSallesLM = new ArrayList<Salle>(); // Long Métrage
        List<Salle> listeSallesHC = new ArrayList<Salle>(); //Hors Compet
        List<Salle> listeSallesUCR = new ArrayList<Salle>(); // Un Certain regard
        List<Salle> listeSallesCM = new ArrayList<Salle>(); // Court Métrage
        List<Salle> listeSallesPP = new ArrayList<Salle>(); //  Projections de Presse
        List<Salle> listeSallesSL = new ArrayList<Salle>(); //  Séance du lendemain
        
        HashMap<String, List<Salle>> map = new HashMap<String, List<Salle>>(); // Va stocker les salles == dictionnaire
        
        for(Salle s : listSalles)
        {
            //On parcourt les catégorie des salles puis on les classes
           for(Categorie c :s.getListCategorie())
           {
               switch(c)
               {
                    case LM:
                        listeSallesLM.add(s);
                        break;
                    case HC:
                        listeSallesHC.add(s);
                        break;
                    case UCR:
                        listeSallesUCR.add(s);
                        break;
                    case CM:
                        listeSallesCM.add(s);
                        break;
                    case PP:
                        listeSallesPP.add(s);
                        break;
                    case SL:
                        listeSallesSL.add(s);
                        break;
                       
               }   
            }
        }
        //Ajout des listes
        map.put("LM", listeSallesLM);
        map.put("HC", listeSallesHC);
        map.put("UCR", listeSallesUCR);
        map.put("CM", listeSallesCM);
        map.put("PP", listeSallesPP);
        map.put("SL", listeSallesSL);
        
        //Parcourir le dico
        /*
        for (HashMap.Entry<String, List<Salle>> entry : map.entrySet()) {
            System.out.println(entry.getKey()); // Clé
            
            for(Salle s : entry.getValue())
            {  
                System.out.println( s); 
            }
        }*/
        
        return map;
       
    }
    
    public static HashMap<String, List<Categorie>> listeCatParCreneaux()
    {
        String [] crenaux = {"matin","finMatin","midi","milieuAprem","finAprem","soiree"};
        HashMap<String, List<Categorie>> map = new HashMap<String, List<Categorie>>();
        
        

        
        for(String c : crenaux)
        {
            List<Categorie> parCreneaux = new ArrayList<Categorie>();
            switch(c)
            {
                case "matin":
                    parCreneaux.add(Categorie.LM);
                    parCreneaux.add(Categorie.UCR);
                    break;
                case "finMatin":
                    parCreneaux.add(Categorie.HC);
                    parCreneaux.add(Categorie.SL);
                    break;
                case "midi":
                    parCreneaux.add(Categorie.LM);
                    parCreneaux.add(Categorie.UCR);
                    parCreneaux.add(Categorie.SL);
                    break;
                case "milieuAprem":
                    parCreneaux.add(Categorie.LM);
                    parCreneaux.add(Categorie.UCR);
                    parCreneaux.add(Categorie.SL);
                    break;
                    
                case "FinAprem":
                case "soiree":
                    parCreneaux.add(Categorie.LM);
                    parCreneaux.add(Categorie.HC);
                    parCreneaux.add(Categorie.UCR);
                    parCreneaux.add(Categorie.SL);
                    break;    
            }
            
            map.put(c, parCreneaux);
        }
        //Parcourir le dico
        /*
        for (HashMap.Entry<String, List<Categorie>> entry : map.entrySet()) {
            System.out.println(entry.getKey()); // Clé
            
            for(Categorie c : entry.getValue())
            {  
                System.out.println(c); 
            }
        }*/
        return map;
    }
    
    public static HashMap<String, String> horaireCreneaux()
    {
        HashMap<String, String> map = new HashMap<String, String>();
        //Ajout des horaires
        map.put("matin", "08h30");
        map.put("finMatin", "10h00");
        map.put("midi", "11h30");
        map.put("milieuAprem", "15h00");                //Pause entre 14h et 15h
        map.put("finAprem", "18h00");                   //Pause entre 16h et 18h
        map.put("soiree", "21h00");
        return map;
        
    }
    
    public  List<String> getCreneaux(Film f)
            /*
            return les creneaux possible pour ce Film
            */
    {
        List<String> catsDuFilm = new ArrayList<String>();
        
        //On parcourt les créneaux 
        for (HashMap.Entry<String, List<Categorie>> entry : listeCatsParCreneaux.entrySet()) {
            
            //On vérifie les Catégorie possible pour ce créneau
            for(Categorie c : entry.getValue())
            {  
                if(f.getCategorie().equals(c))
                {
                    catsDuFilm.add(entry.getKey()); // On ajoute le créneau si sa catégorie est bon
                    break; // Les films ont qu'une seul cat
                }  
            }
        }
        //Debug
        /*for(String c : catsDuFilm)
        {
            System.out.println(c);
        }*/
        return catsDuFilm;
    }
    
    public  List<Salle> getSalles(Film f)
            /*
            return les salles possible pour ce film
            */
    {
        List<Salle> salles = new ArrayList<Salle>();
        
        //On va récupérer les salles pour sa catégorie
        for (HashMap.Entry<String, List<Salle>> entry : listeSallesParCat.entrySet()) 
        {
            if(f.getCategorie().toString().equals(entry.getKey()))
            {
                //On ajoute les salles
                for(Salle s : entry.getValue())
                {  
                    salles.add(s);
                }
                break; // Une cat par film
            }
            
        }
        return salles;
    }

    
    @SuppressWarnings("unlikely-arg-type")
	public  List<Seance> creerJournee()
            /*
            return une liste de seance pour la journee
            */
    {
        List<Seance> seanceDuJour= new ArrayList<Seance>();
        
        //5 créneaux = Matin, Fin matin, Midi, milieu aprem, fin aprem, soiree,
        
        int compteur = 0; //Tant que tt les films restant n'ont pas été tester
        int idSeance =0;
        while(seanceDuJour.size()<=5 || compteur != listFilms.size())
        {
            for(int c=0; c < 5; c++) // 0=matin / 1=finMatin...
            {
                //Parcourt des films disponible
                for(Film f : listFilms)
                {
                    //On récupère ses créneaux horaires possibles
                    List<String> crenauxPossible = getCreneaux(f);
                    getSalles(f);


                    //On fait par crenaux

                    //Si c'est le premier film de la journée
                    if(seanceDuJour.isEmpty())
                    {
                        if(crenauxPossible.contains(c) && c==0)
							new Seance(idSeance++,0,f.getId(),0,day,"08h30");
                    }
                    else
                    {
                        //if(crenauxPossible.contains(c))
                            
                    }
            }
            }
        }
        day = add1Day(day);
        /*
        for(Film f : listFilms)
       {
           System.out.println(f);
           System.out.println(listFilms.lastIndexOf(f));
       }*/
        return seanceDuJour;
    }
    
    public List<Seance> creerJournee15()
    /*
    pour les CM 2 salles en tout (Debussy = 1000p / Bunuel = 500p)
    */
	{
		int compteur = 0; //Pour tester sur tt les fims
		boolean first = false;
		boolean already1 = false;
		boolean already2 = false;
		Seance seance; // Debussy
		Seance seance2; //Bunuel elle commencera décalé (Après 4 seances)
		List<Film> listFilmsCM =new ArrayList<Film>();
		
		List<Seance> DebussySeances = new ArrayList<Seance>(); //Contiendra ces séances
		List<Seance> BunuelSeances = new ArrayList<Seance>();
		
		List<Seance> jour15 = new ArrayList<Seance>(); //Contiendra les 2
		List<String> lastHour = new ArrayList<String>(); //[0] = Debussy [1]=Bunuel 
		
		lastHour.add("08h30");
		lastHour.add("10h00");
		
		//Récupérer tous les CM
		for(Film f : listFilms)
		{
		    if(f.getCategorie().equals(Categorie.CM))
		        listFilmsCM.add(f);
		}
		
		
		
		int nbAjout = 0;
		while(compteur!=listFilmsCM.size())
		{
		   
		    for(Film f : listFilmsCM) 
		    {
		       //On arrête si tous les films dépasse 24h
		        if(compteur == listFilmsCM.size())
		            break;
		        
		        //On récupère ses salles possibles
		        List<Salle> sallesPossible = getSalles(f);
		
		        seance = null;
		        seance2 = null;
		        //La première séance commence à 8h30
		        //Debussy
		        if(lastHour.get(0).equals("08h30") && !already1)
		        {
		            seance = new Seance(-1,idPlanning,f.getId(),sallesPossible.get(0).getIdSalle(),"Jour 15","08h30");
		            DebussySeances.add(seance);
		            first = true;
		            already1 = true;
		            compteur = 0; // On rénitialise (car un film a été trouvé)
		            nbAjout++;
		            continue; //On passe a la prochaine seance
		        }
		        //Bunuel
		        if(lastHour.get(1).equals("10h00") && nbAjout>4 && !already2)
		        {
		            seance2 = new Seance(-1,idPlanning,f.getId(),sallesPossible.get(1).getIdSalle(),"Jour 15","10h00");
		            BunuelSeances.add(seance2);
		            first = true;
		            already2 = true;
		            compteur = 0; // On rénitialise (car un film a été trouvé)
		            continue; //On passe a la prochaine seance
		        }  
		        //Sinon
		        if(first)
		        {
		            //Salle Debussy
		            //Récupération De l'heure de la dernière séance, et la durée
		            int lastId =DebussySeances.size()-1; // derniere seance
		            String heure1 = DebussySeances.get(lastId).getHoraire(); // son heure
		            int duree = f.getDuree(); // durée du film
		
		            //Manipulation
		            heure1 = ajouterHeure(heure1,duree);
		            if(heure1 == null)
		            {
		                compteur++;
		                continue; //On passe au prochain film
		            }
		            else
		            {
		                lastHour.set(0, heure1);
		            }
		
		
		            //Sinon on ajoute
		            seance = new Seance(-1,idPlanning,f.getId(),sallesPossible.get(0).getIdSalle(),"Jour 15",heure1);
		            if(nbAjout>4)
		            {
		                //Salle Bunuel
		                //Récupération De l'heure de la dernière séance, et la durée
		                lastId =BunuelSeances.size()-1; // denriere seance
		                String heure2 = BunuelSeances.get(lastId).getHoraire(); // son heure
		                duree = f.getDuree(); // durée du film
		
		                //Manipulation
		                heure2 = ajouterHeure(heure2,duree);
		                if(heure2 == null)
		                {
		                    compteur++;
		                    continue; //On passe au prochain film
		                }
		                else
		                {
		                    lastHour.set(1, heure2);
		                }
		                    seance2 = new Seance(-1,idPlanning,f.getId(),sallesPossible.get(1).getIdSalle(),"Jour 15",heure2);
		            }
		
		            if(seance != null)
		            {
		                DebussySeances.add(seance);
		                compteur = 0; // On rénitialise (car un film a été trouvé)
		                nbAjout++;
		            }
		            if(seance2 != null)
		                BunuelSeances.add(seance2);
		            }  
		        }
		
		        // On continu tant que sa dépasse pas 24h
		       
		}
   
		jour15.addAll(DebussySeances);
		jour15.addAll(BunuelSeances);
		return jour15;
	}
    
    
    
    public String add1Day(String jour)
    {
        int jourInt;
        String[] mot = jour.split(" ");
        
        jourInt = Integer.parseInt(mot[1]);
        jourInt++;
        
        String jour2="Jour "+jourInt;
        //System.out.println(jour2);
        return jour2;
    }
    
    public static List<List<Seance>> SeanceLendemain()
            /*
            return une liste de seance pour la journee
            */
    {
        return null;
    }
    
    public List<List<Seance>> getPlanning()
    {
        return jours;
    }
}