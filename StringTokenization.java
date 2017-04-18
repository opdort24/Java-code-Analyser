import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StringTokenization{
    
    Scanner input;
    private final String[] keywordArray = {
       "for","int","String","while","do","break","continue","implements",
        "abstract","new","switch","assert","default","goto","package",
        "synchronized","boolean","if","private","this","break","double",
        "protected","throw","byte","else","import","public","throws","case",
        "enum","instanceof","return","transient","catch","extends","int","short",
        "try","char","final","interface","static","void","class","finally",
        "long","strictfp","volatile","const","float","native","super","while"
    };
    
    private final String[] primitiveTypeArray = {"int","byte","long","short",
        "float","double","char"};
    
    private final String[] operatorArray = {"==","!=","=","+","-","/","%","%",
    "*","++","--","!",">",">=","<","<=","&&","||","?:","~","<<",">>",">>>","&",
    "^","|"};
    
    private final String[] seperatorArray = {"{","}",",",".",";",":","(",")",
        "[","]","()","{}","[]"};
    
    //variable to check if primitive data type has previously been found 
    private boolean prevIsPrimitive; 
    private StringBuilder keyword = new StringBuilder();
    private StringBuilder primitiveType = new StringBuilder();
    private StringBuilder operator = new StringBuilder();
    private StringBuilder seperator = new StringBuilder();
    private StringBuilder identifier = new StringBuilder(); 
    private StringBuilder literal = new StringBuilder();
    
    //variable to hold the number of each type that has been found in the program
    int keywordCount=0, primitiveTypeCount=0, operatorCount=0, seperatorCount=0;
    int identifierCount=0, literalCount=0;
    
    // method to open the file containing any Java Code
    public void openJavaFile(){
        try{
            input = new Scanner(new File("C:\\Users\\opeyemi\\Documents\\"
                    + "NetBeansProjects\\300 level class assignments\\"
                    + "src\\BankDatabase.java"));
        }
        catch(FileNotFoundException e) {
            
            System.err.print("File not found "+e.getMessage());
            System.exit(1);
            
        } // close catch
    } // end method openJavaFile
    
    // method to read and process the file content
    public void readContent(){
        try{
            String nextString;
            while(input.hasNext()){
                
                nextString = input.next();
              if (testForKeyword(nextString)){
                  keyword.append(nextString+(++keywordCount%10==0?"\n":" "));
                  if (testForPrimitiveType(nextString)){
                      primitiveType.append(nextString+(++primitiveTypeCount%10==0?"\n":" "));
                      prevIsPrimitive = true;
                  }        
              }
              else if(testForOperator(nextString)){
                  operator.append(nextString+(++operatorCount%20==0?"\n":" "));
              }
              else if (testForSeperator(nextString)){
                  seperator.append(nextString+(++seperatorCount%20==0?"\n":" "));
              }
              
              else if (testForIdentifier(nextString)){
                  identifier.append(nextString+(++identifierCount % 5==0?"\n":"  "));
              }
              else{
                  literal.append(nextString+(++literalCount%20==0?"\n":" "));
              }
                   
              
               
            } // end while
            
            System.out.printf("\t\tKEYWORDS IN THE PROGRAM ARE\n%s"
                    + "\n\n \t\tPRIMITIVE TYPES IN THE PROGRAM ARE:\n%s"
                    + "\n\n \t\tOPERATORS IN THE PROGRAM ARE:\n%s"
                    + "\n\n \t\tSEPERATORS IN THE PROGRAM ARE:\n%s"
                    + "\n\n \t\tIDENTIFIERS IN THE PROGRAM ARE:\n%s"
                    + "\n\n \t\tLITERALS IN THE PROGRAM ARE:\n%s",
                    keyword, primitiveType, operator,seperator,identifier, literal);
            
        }
        catch(Exception e){
            System.err.print("read error "+e.getMessage());
        }
    } // end of method readContent 
    // method to test for keyword in the file
    public  boolean testForKeyword(String nextString){
        boolean found = false; 
        for (String x : keywordArray){
            if (x.equals(nextString))
                found = true;
        }
        
        return found;
    } // end of method testForKeyWord
    // method to test for the existence of primitive data type in the file
    public boolean testForPrimitiveType(String nextString){
        boolean found = false;
        for (String x : primitiveTypeArray)
            if(x.equals(nextString))
                found = true;
        return found;
    }
    // method to test for the operators existence in the file
    public boolean testForOperator(String nextString){
        boolean found = false;
        for (String x : operatorArray)
            if (x.equals(nextString))
                found = true;
        return found;
    } 
    //method to test for delimieters in the file
    public boolean testForSeperator(String nextString) {
        boolean found = false;
        for(String x : seperatorArray)
            if (x.equals(nextString))
                found = true;
        return found;
    }
    //method to test for identifiers in the file
    public boolean testForIdentifier(String nextString){
        boolean found = false;
        char firstChar = nextString.charAt(0);
        
        if (Character.isJavaIdentifierStart(firstChar) || prevIsPrimitive == true)
            found = true;
        return found;
    }
    // method to close the file after complete looping through the file content
    public void closeFile() {
        if (input != null) {
            input.close();
            System.exit(0);
        }
    }
    // main method
    public static void main (String [] args){
        StringTokenization app = new StringTokenization();
        app.openJavaFile();
        app.readContent();
        app.closeFile();
    }
}