import java.util.Scanner;
public class Peli{
  public static void main(String[] args){
    
    //Alkuhässäkkä
    int panos;
    Scanner scanner = new Scanner(System.in);
    boolean pelataanko = true;
    boolean uusiPeli = true;
    int rahat;
    while(uusiPeli){
      rahat = 100;
      System.out.println("Money = "+ rahat);
      while(rahat>0 && pelataanko){
        System.out.print("Your bet: ");
        panos = Integer.parseInt(scanner.nextLine());
        if(rahat-panos<0){
          System.out.println("You don't have enough money");
        }else{
          System.out.println("Your bet is "+ panos);
        }
        //Jaetaan kortit
        Pokerikasi kasi = new Pokerikasi();
        System.out.println("Your hand is:\n" );
        kasi.tulostaKasi();
        kasi.kortitKuvina();
        System.out.println(kasi.kadenArvoTekstina(kasi));
        
        //Korttien vaihto
        
        System.out.println("How many cards do you want to change? 0-5");
        kasi.vaihda(Integer.parseInt(scanner.nextLine()));
        
        
        //Käden näyttö
        System.out.println("Now your hand is: ");
        kasi.tulostaKasi();
        kasi.kortitKuvina();
        System.out.println(kasi.kadenArvoTekstina(kasi));
        //Tuplaus
        
        int voitto = kasi.kadenArvo(kasi)*panos;
        if(voitto==0){
          rahat -= panos;
        }
        while(voitto>0){
          System.out.println("You won "+ voitto);
          System.out.println("Do you want to double? (Y)es, (N)o");
          String q = "";
          boolean tuplausA = true;
          while(tuplausA){
            q = scanner.nextLine();
            if(q.equals("N") || q.equals("n") || q.equals("Y") || q.equals("y")){
              tuplausA = false;
            }else{
              System.out.println("Invalid value!");
              System.out.println("Do you want to double? (Y)es, (N)o");
            }
          }
          
          
          if(q.equals("N") || q.equals("n")){
            rahat = rahat-panos+voitto;
            break;
            
          }
          if(q.equals("Y") || q.equals("y")){
            if(tuplaus()){
              voitto += voitto;
            }else{
              voitto = 0;
              rahat -= panos;
            }
          }
        }
        
        
        //Rahatilanteen muutos
        
        System.out.println("Money = "+ rahat);
        if(rahat==0){
          System.out.println("You have no money -> Game over!");
          System.out.println("Do you want to play again? (Y)es, (N)o");
          String hh = scanner.nextLine();
          if(hh.equals("N") || hh.equals("n")){
            uusiPeli = false;
          }
        }else{
          System.out.println("Continue playing? (Y)es, (N)o");
          String h = scanner.nextLine();
          if(h.equals("N") || h.equals("n")){
            pelataanko = false;
          }
        }
        
        
      }
    }
    
  }
  


  public static boolean tuplaus(){
    Scanner scanner = new Scanner(System.in);
    System.out.println("(B)ig vai (S)mall?");
    Pelikortti tuplauskortti = new Pelikortti("uusiKortti");
    String a = scanner.nextLine();
    System.out.print(tuplauskortti.annaArvo()+" of ");
    System.out.println(tuplauskortti.annaMaa());
    if(a.equals("B") || a.equals("b")){
      if(tuplauskortti.annaArvo()>7){
        System.out.println("Correct!");
        return true;
      }else{
        System.out.println("Wrong");
        return false;
      }
    }
    if(a.equals("S") || a.equals("s")){
      if(tuplauskortti.annaArvo()<7){
        System.out.println("Correct!");
        return true;
          }else{
            System.out.println("Wrong");
            return false;
          }
    }
    System.out.println("You entered invalid letter, you lost");
    return false;
  }
}


