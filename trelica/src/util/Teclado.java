package util;
import java.io.IOException;

public class Teclado {

// Le e retorna uma String do teclado
public static String lerString () {
  String palavra = "";
  int letra;

  try {
  	
  	do {
      letra = System.in.read();
      palavra = palavra + (char) letra;
    } while ( (char) letra != '\n' );
  	
  	palavra = palavra.replaceAll("\\r\\n|\\n", "");   // Removing new line for Windows and Linux
    return palavra ;
    
  }
  catch ( IOException e ) {
    return palavra;
  }

} 


// Le e retorna um numero inteiro
public static int lerInt () {
  String int_text;
  boolean Ok;
//  Integer num = new Integer( 0 ); // criando o objeto Integer
  Integer num = Integer.valueOf(0);

  do {  	
  	Ok = true;
  	
    try {    	
      int_text = lerString(); // lendo uma string pelo teclado
      int_text = int_text.trim(); // removendo os espacos no inicio da string
      num = Integer.valueOf ( int_text.trim() ); // converte string para inteiro            
    }
    catch ( NumberFormatException e ) {
  	  Ok = false;
  	  System.out.println("\nLerInt(): Erro na entrada de dados\n");
    }
    
  } while ( !Ok );  
    
  return num.intValue(); // retornando o numero lido
} 


// le e retorna um double
public static double lerDouble() {
  String double_text;
  boolean Ok;
//  Double num = new Double( 0.0 ); // Criando um objeto Double
  Double num = Double.valueOf(0);

  do {
  	Ok = true;
  
    try {
      double_text = lerString(); // lendo uma string pelo teclado
      double_text = double_text.trim(); // removendo espacos no inicio da string
      num = Double.valueOf ( double_text ); // convertendo string para double
    }
    catch ( NumberFormatException e ) {
  	  Ok = false;
  	  System.out.println("\nLerDouble(): Erro na entrada de dados\n");
    }
    
  } while ( !Ok );  
    
  return num.doubleValue(); // retornando o numero lido 
}


public static void tecleEnter() {
  int letra;

  try {  

    do {
      letra = System.in.read();
    } while ( (char)letra != '\n' ); // repete ate' letra ser ENTER

  }
  catch( IOException e ) {
  }
 
}


public static void pausar () {
  System.out.print("\n <- Tecle Enter -> ");
  tecleEnter();
}


} // fim da classe teclado
