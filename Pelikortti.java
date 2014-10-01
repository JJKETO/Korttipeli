import java.util.Random;

public class Pelikortti {
  
  private int arvo;
  private int maa;
  public Pelikortti(){
    arvo = 0;
    maa = 0;
  }
  public Pelikortti(int a, int b){ 
    arvo = a;
    maa = b;
  }
  
  public void asetaArvo(int a){
    arvo = a;
  }
  
  public void asetaMaa(int b){
    maa = b;
  }
  
  public int annaArvo(){
    if(arvo>0 && arvo<14)
      return arvo;
    return -1;
  }
  
  public String annaMyosKuvakortit(){
    String a = "";
    if(arvo==1 || arvo>10){
      if(arvo==11){
        a="Jack";
      }
      if(arvo==12){
        a="Queen";
      }
      if(arvo==13){
        a="King";
      }
      if(arvo==14 || arvo==1){
        a="Ace";
      }
      return a;
    }
    return a+arvo;  
  }

  public int annaMaaNumerona(){
    return maa;
  }
  
  public String annaMaa(){
    if(maa==1)
      return "Spades";
    if(maa==2)
      return "Hearts";
    if(maa==3)
      return "Clubs";
    if(maa==4)
      return "Diamonds";
    return "Invalid card";
  }
  
    
    
  
  
  public Pelikortti(String u){
    Random random = new Random();
    if(u.equals("uusiKortti")){
      arvo = random.nextInt(13)+1;
      maa = random.nextInt(4)+1;
    }
  }
  
  
  
}
