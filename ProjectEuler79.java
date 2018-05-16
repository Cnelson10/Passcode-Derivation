/* Christopher Nelson
// 30 April 2018
// Final Assignment (11): Project Euler 79 - Passcode Derivation
*/
package projecteuler79;

import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class ProjectEuler79 {

    public static void main(String[] args) {
        int lineNum = 0;
        String fileName = "keylog.txt";
        Graph <Integer, String> passcode = new DirectedSparseGraph<Integer, String>();

        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                String keylog = scanner.nextLine();
                lineNum++;
                
                for (int i = 0; i < keylog.length(); i++){
                    char num = keylog.charAt(i);
                    int number = Character.getNumericValue(num);
                    
                    if(i == 0){
                        if(passcode.addVertex(number)){
                           passcode.addVertex(number);
                           
                        } else {
                            continue;
                        }
                        
                    } else {
                        char prev = keylog.charAt(i-1);
                        int previous = Character.getNumericValue(prev);
                        StringBuilder edge = new StringBuilder();
                        edge.append(prev);
                        edge.append(num);
                        if (passcode.addEdge(edge.toString(), previous, number)){
                            passcode.addEdge(edge.toString(), previous, number);
                            
                        } else {
                            continue;
                        }
                        
                    }
                    
                }
                
            }
            scanner.close();
            
        } catch (FileNotFoundException e1) {
            //TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        List <Integer> output = new ArrayList<>();
        Queue <Integer> inDegreeZero = new LinkedList<>();
        System.out.println(passcode);
        while(passcode.getVertexCount() > 0){
            for(Integer num : passcode.getVertices()){
                if(passcode.inDegree(num) == 0){
                    inDegreeZero.offer(num);
                }
            }
            while (!inDegreeZero.isEmpty()){
                passcode.removeVertex(inDegreeZero.peek());
                output.add(inDegreeZero.remove());
            }
            //System.out.println(output);
            //System.out.println(passcode);
        }
        System.out.println("\nThe shortest possible passcode based on these keylogs would be:");
        System.out.println(output);
    }
    
}
