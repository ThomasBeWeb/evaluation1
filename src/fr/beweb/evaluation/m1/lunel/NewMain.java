/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.beweb.evaluation.m1.lunel;

import bwb_evaluation_algo.library.CoreEvaluation;
import bwb_evaluation_algo.library.EvaluationSystem;
import bwb_evaluation_algo.library.Tag;
import java.util.ArrayList;

/**
 *
 * @author cantinelli
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        CoreEvaluation eval = EvaluationSystem.getEvaluation();
        
        //Ajout du questionnaire
        eval.addQuestionnaire("/home/cantinelli/ServeurWeb/evaluation1/questionnaire.txt");
        
        //Ajout du fichier d sortie
        eval.addDestination("/home/cantinelli/ServeurWeb/evaluation1/index.html");
        
        Tag root = new Tag("html");
        Tag head = new Tag("head");
        Tag body = new Tag("body");
        
//        // style doit être dans le même dossier que index.html
//        head.addStylesheet("style.css");
//        // insertion de la balise head dans la balise html
//        head.insertInto(root);
//        // insertion de la balise body dans la balise html
//        body.insertInto(root);
//        
//        String codeHTML = root.getHtml();
//        
//        eval.writeFile(codeHTML);

        //Parcours du questionnaire
        
        String newLine;
        
        ArrayList<String> listeLignes = new ArrayList<>();
       
        while((newLine = eval.readLine()) != null){
            listeLignes.add(newLine);
        }
        
        
        for(int i = 0 ; i < listeLignes.size() ; i++){
            
            newLine = listeLignes.get(i);
            
            if(verifChar(newLine,'#',0)){
                
                if(verifChar(newLine,'#',1)){
                    newLine = newLine.substring(2);
                    eval.writeFile("<h2>" + newLine + "</h2>");      
                }else{
                    newLine = newLine.substring(1);
                    eval.writeFile("<h1>" + newLine + "</h1>");
                }
            }else{
                
                //Question
                String question = newLine;
                
                eval.writeFile(question + "<br>");
                
                if(i < listeLignes.size() - 1){
                    
                    if(verifChar(listeLignes.get(i+1),'-',0)){ //QCM

                        //Tableau recevant les reponses
                        String[] reponses = new String[4];

                        for(int j = 0 ; j < 4 ; j++){
                            i++;
                            reponses[j] = listeLignes.get(i);
                        }
                        eval.writeFile(eval.interact(question,reponses) + "<br>"); 
                    }else{
                        eval.writeFile(eval.interact(question) + "<br>");
                    }
                    
                }else{ 
                    eval.writeFile(eval.interact(question) + "<br>");
                }
            }     
        }    
    }
    
    public static boolean verifChar(String ligne, char caractere,int index){
        
        boolean result = ligne.charAt(index) == caractere;

        return result;
    }
    
    
}
