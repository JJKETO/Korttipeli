import java.util.Random;
import java.util.Scanner;
public class Pokerikasi {
  private Pelikortti[] kasi = new Pelikortti[5];
  
  public Pokerikasi(){
    Random random = new Random();
    int a;
    int b;
    do{
      for(int i=0;i<5;i++){
        a = random.nextInt(13)+1;
        b = random.nextInt(4)+1;
        kasi[i] = new Pelikortti(a, b);
      }
    }while(virheellinenKasi(kasi));
  }
  
  public void tulostaKasi(){
    for(int i=0;i<5;i++){
      System.out.print(kasi[i].annaMyosKuvakortit()+" of ");
      System.out.println(kasi[i].annaMaa());
      
    }
    System.out.print("\n");
  }
  
  
  public void vaihda(int maara){
    Scanner scanner = new Scanner(System.in);
    while(maara>5 || maara<0){
      System.out.println("You can only change 0-5 cards");
      maara = Integer.parseInt(scanner.nextLine());
    }
    Pelikortti[] kaytetty = new Pelikortti[5+maara];
    int vaihdettava=0;
    //Vanha käsi käytettyihin
    for(int n=0;n<5;n++){
      kaytetty[n] = kasi[n];
    }
    
    //käydään vaihtoja läpi haluttu määrä
    for(int i=1; i<= maara; i++){
      if(i==1){
        System.out.println("Which card do you want to change?");
      }else{
        System.out.println("Which card do you want to change next?");
      }
      boolean a=true;
      int s;
      while(a){
        s = Integer.parseInt(scanner.nextLine())-1;
        if(s>-1 && s<5){
          vaihdettava = s;
          a = false;
        }else{
          System.out.println("Give a value between 1 and 5");
        }
      }
      
      Pelikortti uuscard = new Pelikortti("uusiKortti");
      
      
      //Katsotaan onko kortti jo käytetty
      for(int n=0; n<4+i;n++){
        
        
        if(kaytetty[n].annaArvo() != uuscard.annaArvo() || kaytetty[n].annaMaaNumerona() != uuscard.annaMaaNumerona()){
          if(n==3+i){
            kaytetty[4+i]=uuscard;
            kasi[vaihdettava] = uuscard;
          }
        }else{
          n=0;
          uuscard = new Pelikortti("uusiKortti");
        }  
      }
    }  
  }
  
  
  public String kadenArvoTekstina(Pokerikasi kasi){
    int[] arvot = kasi.kadenNumerot();
    int[] maat = kadenMaat();
    
    if(vari(maat) && suora(arvot)){
      return "Straightflush!";
    }
    if(neloset(arvot)){
      return "Four of a Kind";
    }
    if(tayskasi(arvot)){
      return "Full House";
    }
    if(vari(maat)){
      return "Flush";
    }
    if(suora(arvot)){
      return "Straight";
    }
    if(kolmoset(arvot)){
      return "Three of a Kind";
    }
    if(kaksiParia(arvot)){
      return "Two pairs";
    }
    if(pari(arvot)){
      return "a pair";
    }
    
    
    return "High card";
  }
  
  public int kadenArvo(Pokerikasi kasi){
    int[] arvot = kasi.kadenNumerot();
    int[] maat = kadenMaat();
    
    if(vari(maat) && suora(arvot)){
      return 50;
    }
    if(neloset(arvot)){
      return 25;
    }
    if(tayskasi(arvot)){
      return 10;
    }
    if(vari(maat)){
      return 8;
    }
    if(suora(arvot)){
      return 6;
    }
    if(kolmoset(arvot)){
      return 3;
    }
    if(kaksiParia(arvot)){
      return 2;
    }
    if(pari(arvot)){
      return 0;
    }
    
    
    return 0;
  }
  
  public static boolean vari(int[] maat){
    for(int i=1;i<5;i++){
      if(maat[i]==5)
        return true;
    }
    return false;
  }
  
  public static boolean neloset(int[] arvot){
    for(int i=1;i<14;i++){
      if(arvot[i]==4){
        return true;
      }
    }
    return false;
  }
  
  public static boolean kolmoset(int[] arvot){
    for(int i=1;i<14;i++){
      if(arvot[i]==3){
        return true;
      }
    }
    return false;
  }
  public static boolean kaksiParia(int[] arvot){
    int a= 0;
    for(int i=1;i<14;i++){
      if(arvot[i]==2){
        a++;
        if(a==2)
          return true;
      }
    }
    return false;
  }
  
  
  public static boolean pari(int[] arvot){
    for(int i=1;i<14;i++){
      if(arvot[i]==2){
        return true;
      }
    }
    return false;
  }
  public static boolean tayskasi(int[] arvot){
    for(int i=1;i<14;i++){
      if(arvot[i]==3){
        for(int f=1;f<14;f++){
          if(arvot[f]==2)
            return true;
        }
      }
    }
    return false;
  }
  
  public static boolean suora(int[] arvot){
    int a=0;
    for(int i=1;i<14;i++){
      if(arvot[i]>0 && arvot[i+1]>0){
        a++;
      }
    }
    return a==4;
  }
  
  public int[] kadenNumerot(){
    int[] arvot = new int[15];
    for(int i = 0;i<5;i++){
      arvot[kasi[i].annaArvo()]++;
      if(kasi[i].annaArvo()==1){
        arvot[14]++;
      }
    }
    return arvot;
  }
  
  public int[] kadenMaat(){
    int[] maat = new int[5];
    for(int i=0;i<5;i++){
      maat[kasi[i].annaMaaNumerona()]++;
    }
    return maat;
  }
  
  public static boolean virheellinenKasi(Pelikortti[] käsi){
    
    for(int i=0; i<4; i++){
      for(int n=i+1; n<5;n++){
        if(käsi[i].annaMaa()==käsi[n].annaMaa() && käsi[i].annaArvo() == käsi[n].annaArvo()){
          return true;
        }
      }
    } 
    return false;
  }
  
  
  
  
  
  //Kortit kuvina
  
  
  
  
  
  
  public void kortitKuvina(){
    String a = "";
    String b = "";
    
    //String-korttitaulukko:
    String[] kortit = new String[90];
    
    //2
    kortit[2]="|     X     |";
    kortit[2+15]="|           |";
    kortit[2+15+15]="|           |";
    kortit[2+15+15+15]="|           |";
    kortit[2+15+15+15+15]="|     X     |";
    kortit[2+15+15+15+15+15]="|           |";
    
    //3
    kortit[3]="|     X     |";
    kortit[3+15]="|           |";
    kortit[3+15+15]="|     X     |";
    kortit[3+15+15+15]="|           |";
    kortit[3+15+15+15+15]="|     X     |";
    kortit[3+15+15+15+15+15]="|           |";
    
    //4
    kortit[4]="|   X   X   |";
    kortit[4+15]="|           |";
    kortit[4+15+15]="|           |";
    kortit[4+15+15+15]="|           |";
    kortit[4+15+15+15+15]="|   X   X   |";
    kortit[4+15+15+15+15+15]="|           |";
    
    //5
    kortit[5]="|   X   X   |";
    kortit[5+15]="|           |";
    kortit[5+15+15]="|     X     |";
    kortit[5+15+15+15]="|           |";
    kortit[5+15+15+15+15]="|   X   X   |";
    kortit[5+15+15+15+15+15]="|           |";
    
    //6
    kortit[6]="|   X   X   |";
    kortit[6+15]="|           |";
    kortit[6+15+15]="|   X   X   |";
    kortit[6+15+15+15]="|           |";
    kortit[6+15+15+15+15]="|   X   X   |";
    kortit[6+15+15+15+15+15]="|           |";
    
    //7
    kortit[7]="|   X   X   |";
    kortit[7+15]="|     X     |";
    kortit[7+15+15]="|   X   X   |";
    kortit[7+15+15+15]="|           |";
    kortit[7+15+15+15+15]="|   X   X   |";
    kortit[7+15+15+15+15+15]="|           |";
    
    //8
    kortit[8]="|   X   X   |";
    kortit[8+15]="|     X     |";
    kortit[8+15+15]="|   X   X   |";
    kortit[8+15+15+15]="|     X     |";
    kortit[8+15+15+15+15]="|   X   X   |";
    kortit[8+15+15+15+15+15]="|           |";
    
    //9
    kortit[9]="|   X   X   |";
    kortit[9+15]="|   X   X   |";
    kortit[9+15+15]="|     X     |";
    kortit[9+15+15+15]="|   X   X   |";
    kortit[9+15+15+15+15]="|   X   X   |";
    kortit[9+15+15+15+15+15]="|           |";
    
    //10
    kortit[10]="|   X   X   |";
    kortit[10+15]="|     X     |";
    kortit[10+15+15]="|   X   X   |";
    kortit[10+15+15+15]="|   X   X   |";
    kortit[10+15+15+15+15]="|     X     |";
    kortit[10+15+15+15+15+15]="|   X   X   |";
    
    //J
    kortit[11]="|    Ss_    |";
    kortit[11+15]="|   d'_'b   |";
    kortit[11+15+15]="|  _/   \\_  |";
    kortit[11+15+15+15]="|   \\ _ /   |";
    kortit[11+15+15+15+15]="|   q' 'p   |";
    kortit[11+15+15+15+15+15]="|    -sS    |";
    
    //Q
    kortit[12]="|   |\\x/|   |";
    kortit[12+15]="|   (*-*}   |";
    kortit[12+15+15]="|  _/   \\_  |";
    kortit[12+15+15+15]="|   \\ _ /   |";
    kortit[12+15+15+15+15]="|   {* *)   |";
    kortit[12+15+15+15+15+15]="|   |/x\\|   |";
    
    //K
    kortit[13]="|   |/\\/\\|  |";
    kortit[13+15]="|   ('- ')  |";
    kortit[13+15+15]="|  _/    \\_ |";
    kortit[13+15+15+15]="|   \\    /  |";
    kortit[13+15+15+15+15]="|   (. -.)  |";
    kortit[13+15+15+15+15+15]="|   |\\/\\/|  |";
    
    //A
    kortit[14]="|           |";
    kortit[14+15]="|    /\\     |";
    kortit[14+15+15]="|   /  \\    |";
    kortit[14+15+15+15]="|  /----\\   |";
    kortit[14+15+15+15+15]="| /      \\  |";
    kortit[14+15+15+15+15+15]="|           |";
    kortit[1]="|           |";
    kortit[1+15]="|    /\\     |";
    kortit[1+15+15]="|   /  \\    |";
    kortit[1+15+15+15]="|  /----\\   |";
    kortit[1+15+15+15+15]="| /      \\  |";
    kortit[1+15+15+15+15+15]="|           |";
    
    //tulostus
    
    //ekarivi
    System.out.print(" ___________     ___________     ___________     ___________     ___________\n");
    
    //tokarivi
    for(int f=0;f<5;f++){
      b=""+kasi[f].annaArvo();
      if(kasi[f].annaMaaNumerona()==1){
        a="S";
      }
      if(kasi[f].annaMaaNumerona()==2){
        a="H";
      }
      if(kasi[f].annaMaaNumerona()==3){
        a="C";
      }
      if(kasi[f].annaMaaNumerona()==4){
        a="D";
      }
      if(kasi[f].annaArvo()==14 || kasi[f].annaArvo()==1){
        b="A";
      }
      if(kasi[f].annaArvo()==13){
        b="K";
      }
      if(kasi[f].annaArvo()==12){
        b="Q";
      }
      if(kasi[f].annaArvo()==11){
        b="J";
      }
      if(kasi[f].annaArvo()==10){
        System.out.print("|("+a+")      "+b+"|   ");
      }else{
      System.out.print("|("+a+")       "+b+"|   ");
      }
    }
    System.out.print("\n");
    
    //rivit 3-7
    int eka = kasi[0].annaArvo();
    int toka = kasi[1].annaArvo();
    int kolone = kasi[2].annaArvo();
    int nelone = kasi[3].annaArvo();
    int vitone = kasi[4].annaArvo();
    for(int f=0;f<6;f++){
    System.out.println(kortit[eka]+"   "+kortit[toka]+"   "+kortit[kolone]+"   "+kortit[nelone]+"   "+kortit[vitone]);
    eka=eka+15;
    toka=toka+15;
    kolone=kolone+15;
    nelone=nelone+15;
    vitone=vitone+15;
    }
    //viimine rivi
    for(int f=0;f<5;f++){
      b=""+kasi[f].annaArvo();
      if(kasi[f].annaMaaNumerona()==1){
        a="S";
      }
      if(kasi[f].annaMaaNumerona()==2){
        a="H";
      }
      if(kasi[f].annaMaaNumerona()==3){
        a="C";
      }
      if(kasi[f].annaMaaNumerona()==4){
        a="D";
      }
      if(kasi[f].annaArvo()==14 || kasi[f].annaArvo()==1){
        b="J";
      }
      if(kasi[f].annaArvo()==13){
        b="K";
      }
      if(kasi[f].annaArvo()==12){
        b="Q";
      }
      if(kasi[f].annaArvo()==11){
        b="J";
      }
      if(kasi[f].annaArvo()==10){
        System.out.print("|"+b+"______("+a+")|   ");
      }else{
        System.out.print("|"+b+"_______("+a+")|   ");
      }
      
    }
    System.out.print("\n");
    
    //numerorivi
    System.out.println("     [1]             [2]             [3]             [4]             [5]     ");
  }
  
  
  
}