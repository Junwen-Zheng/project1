/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NaiveBayesFilter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 *
 * @author Antoun
 */
public class SpamFillterTester {
    public static void main(String[] args) throws FileNotFoundException, IOException{
        NaiveBayesianAnalyzer analyzer = new NaiveBayesianAnalyzer();
        Reader stream;
        for (int i = 0; i < 400; i++) {
            stream = new FileReader("dataset\\ham\\ham ("+(i+1)+").txt");
            analyzer.addHam(stream);
        }
        for (int i = 0; i < 100; i++) {
            stream = new FileReader("dataset\\spam\\spam ("+(i+1)+").txt");
            analyzer.addSpam(stream);
        }
        analyzer.buildCorpus();
        
        
        
        int spam_counter =0;
        double output;
        for (int i = 0; i < 100; i++) {
            stream = new FileReader("dataset\\spam\\spam ("+(i+1)+").txt");
            output = analyzer.computeSpamProbability(stream);
            if(output>0.9){
                spam_counter++;
            }
        }
        System.out.println("On Training dataset: spam correctly identified = "+spam_counter+" out of 100");
        
        int ham_counter = 0;
        for (int i = 0; i < 400; i++) {
            stream = new FileReader("dataset\\ham\\ham ("+(i+1)+").txt");
            output = analyzer.computeSpamProbability(stream);
            if(output<0.9){
                ham_counter++;
            }
        }
        
        System.out.println("On Training dataset: ham correctly identified = "+ham_counter+" out of 400");   
        
        System.out.println("");
        
        spam_counter =0;
        
        for (int i = 0; i < 40; i++) {
            stream = new FileReader("dataset\\test\\spam\\spam ("+(i+1)+").txt");
            output = analyzer.computeSpamProbability(stream);
            if(output>0.9){
                spam_counter++;
            }
        }
        System.out.println("On Testing dataset: spam correctly identified = "+spam_counter+" out of 40");
        
        ham_counter = 0;
        for (int i = 0; i < 160; i++) {
            stream = new FileReader("dataset\\test\\ham\\ham ("+(i+1)+").txt");
            output = analyzer.computeSpamProbability(stream);
            if(output<0.9){
                ham_counter++;
            }
        }
        
        System.out.println("On Testing dataset: ham correctly identified = "+ham_counter+" out of 160");   
    }
}
