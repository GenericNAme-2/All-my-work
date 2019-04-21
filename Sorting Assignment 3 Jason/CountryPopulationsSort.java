import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.*;

/* Jason Li (group with Andrey and Ethan)
 * 2019/04/02
 * Ms. Krasteva
 * This is a program to read country info including name, capital, area and population then sort by
 * name and population. It also formats the populations when printing to have commas.
 */

public class CountryPopulationsSort
{
  //main
    public static void main (String[] main)
    {
     ArrayList<String> name = new ArrayList<String>();// country name
     ArrayList<String> capital = new ArrayList<String>();// capital
     ArrayList<String> area = new ArrayList<String>(); //area
     ArrayList<String> nameCapitalArea = new ArrayList<String>();//this is used to hold in the line for processing
     ArrayList<Integer> populations = new ArrayList<Integer>(); //population
     int terms = 0;
     try {
     Scanner s = new Scanner(new File("Countries-Population.txt"));//File
     while (s.hasNext()){//Adds nameCapitalArea and populations to Array Lists
         String line, nameTemp, capitalTemp, areaTemp;
         String popStr = "";
         int i = 0, digits = 0; 
         boolean inPopulation = false;
         line = s.nextLine();
         if(line.charAt(line.length()-1) == ' '){
           line = line.substring(0, line.length()-1);
         }
         //hard coding special country and capital names because otherwise it would be impossible to differentiate
         //country and capital
         if(line.equals("Antigua and Barbuda St. John's 171 69,108")){
           nameTemp = "Antigua and Barbuda";
           capitalTemp = "St. John's";
           areaTemp = "171";
         }
         else if (line.equals("Argentina Buenos Aires 1,068,296 39,921,83")){nameTemp = "Argentina"; capitalTemp = "Buenos Aires"; areaTemp ="1,068,296";}
         else if (line.equals("Belarus Mensk (Minsk) 80,154 10,293,011")){nameTemp = "Belarus"; capitalTemp = "Mensk (Minsk)"; areaTemp ="80,154";}
         else if (line.equals("Bolivia La Paz 424,162 8,989,046")){nameTemp = "Bolivia"; capitalTemp = "La Paz"; areaTemp ="424,162";}
         else if (line.equals("Bosnia and Herzegovina Sarajevo 19,741 4,498,976")){nameTemp = "Bosnia and Herzegovina"; capitalTemp = "Sarajevo"; areaTemp ="19,741";}
         else if (line.equals("Brunei Bandar Seri Begawan 2,228 379,444")){nameTemp = "Brunei"; capitalTemp = "Bandar Seri Begawan"; areaTemp = "2,228";}
         else if (line.equals("Burkina Faso Ouagadougou 105,869 13,902,972")){nameTemp = "Burkina Faso"; capitalTemp = "Ouagadougou"; areaTemp ="105,869";}
         else if (line.equals("Central African Republic Bangui 240,534 4,303,356")){nameTemp = "Central African Republic"; capitalTemp = "Bangui"; areaTemp ="240,534";}
         else if (line.equals("Congo, Republic of Brazzaville 132,046 3,702,314")){nameTemp = "Congo, Republic of Brazzaville"; capitalTemp = ""; areaTemp ="132,046";}
         else if (line.equals("Congo, Democratic Republic of the Kinshasa 905,563 62,660,551")){nameTemp = "Congo, Democratic Republic of the Kinshasa"; capitalTemp = ""; areaTemp ="905,563";}
         else if (line.equals("Costa Rica San José 19,730 4,075,261")){nameTemp = "Costa Rica"; capitalTemp = "San José "; areaTemp ="19,730";}
         else if (line.equals("Côte d'Ivoire Yamoussoukro 124,502 17,654,843")){nameTemp = "Côte d'Ivoire"; capitalTemp = "Yamoussoukro"; areaTemp ="124,502";}
         else if (line.equals("Czech Republic Prague 30,450 10,235,455")){nameTemp = "Czech Republic"; capitalTemp = "Prague"; areaTemp ="30,450";}
         else if (line.equals("Dominican Republic Santo Domingo 18,815 9,183,984 ")){nameTemp = "Dominican Republic"; capitalTemp = "Santo Domingo "; areaTemp ="18,815";}
         else if (line.equals("East Timor Dili 5,794 1,062,777")){nameTemp = "East Timor"; capitalTemp = "Dili"; areaTemp ="5,794";}
         else if (line.equals("El Salvador San Salvador 8,124 6,822,378")){nameTemp = "El Salvador"; capitalTemp = "San Salvador"; areaTemp ="8,124";}
         else if (line.equals("Equatorial Guinea Malabo 10,830 540,109")){nameTemp = "Equatorial Guinea"; capitalTemp = "Malabo"; areaTemp ="10,830";}
         else if (line.equals("Ethiopia Addis Ababa 435,184 74,777,981")){nameTemp = "Ethiopia"; capitalTemp = "Addis Ababa"; areaTemp ="435,184";}
         else if (line.equals("Grenada St. George's 133 89,703")){nameTemp = "Grenada"; capitalTemp = "St. George's"; areaTemp ="133";}
         else if (line.equals("Guatemala Guatemala City 42,042 12,293,545")){nameTemp = "Guatemala"; capitalTemp = "Guatemala City"; areaTemp ="42,042";}
         else if (line.equals("India New Delhi 1,269,338 1,095,351,995")){nameTemp = "India"; capitalTemp = "New Delhi"; areaTemp ="1,269,338";}
         else if (line.equals("Korea, North Pyongyang 46,540 23,113,019")){nameTemp = "Korea, North"; capitalTemp = "Pyongyang"; areaTemp ="46,540";}
         else if (line.equals("Korea, South Seoul 38,023 48,846,823")){nameTemp = "Korea, South"; capitalTemp = "Seoul"; areaTemp ="38,023";}
         else if (line.equals("Kuwait Kuwait City 6,880 2,418,393")){nameTemp = "Kuwait"; capitalTemp = "Kuwait City"; areaTemp ="6,880";}
         else if (line.equals("Malaysia Kuala Lumpur 127,316 24,385,858")){nameTemp = "Malaysia"; capitalTemp = "Kuala Lumpur"; areaTemp ="127,316";}
         else if (line.equals("Marshall Islands Majuro 4,577 60,422")){nameTemp = "Marshall Islands"; capitalTemp = "Majuro"; areaTemp ="4,577";}
         else if (line.equals("Mauritius Port Louis 788 1,240,827")){nameTemp = "Mauritius"; capitalTemp = "Port Louis"; areaTemp ="788";}
         else if (line.equals("Mexico Mexico City 761,602 107,449,525")){nameTemp = "Mexico"; capitalTemp = "Mexico City"; areaTemp ="761,602";}
         else if (line.equals("Mongolia Ulan Bator 603,905 2,832,224")){nameTemp = "Mongolia"; capitalTemp = "Ulan Bator"; areaTemp ="603,905";}
         else if (line.equals("Myanmar (Burma) Rangoon 261,969 47,382,633")){nameTemp = "Myanmar (Burma)"; capitalTemp = "Rangoon"; areaTemp ="261,969";}
         else if (line.equals("New Zealand Wellington 103,737 4,076,140")){nameTemp = "New Zealand"; capitalTemp = "Wellington"; areaTemp ="103,737";}
         else if (line.equals("Panama Panama City 30,193 3,191,319")){nameTemp = "Panama"; capitalTemp = "Panama City"; areaTemp ="30,193";}
         else if (line.equals("Papua New Guinea Port Moresby 178,703 5,670,544")){nameTemp = "Papua New Guinea"; capitalTemp = "Port Moresby"; areaTemp ="178,703";}
         else if (line.equals("St. Kitts and Nevis Basseterre 101 39,129")){nameTemp = "St. Kitts and Nevis"; capitalTemp = "Basseterre"; areaTemp ="101";}
         else if (line.equals("St. Lucia Castries 238 168,458")){nameTemp = "St. Lucia"; capitalTemp = "Castries"; areaTemp ="238";}
         else if (line.equals("St. Vincent and the Grenadines Kingstown 150 117,848")){nameTemp = "St. Vincent and the Grenadines"; capitalTemp = "Kingstown"; areaTemp ="150";}
         else if (line.equals("San Marino San Marino 24 29,251")){nameTemp = "San Marino"; capitalTemp = "San Marino"; areaTemp ="24";}
         else if (line.equals("São Tomé and Príncipe São Tomé 386 193,413")){nameTemp = "Sao Tome and Principe"; capitalTemp = "Sao Tome"; areaTemp ="386";}
         else if (line.equals("Saudi Arabia Riyadh 756,981 27,019,731")){nameTemp = "Saudi Arabia"; capitalTemp = "Riyadh"; areaTemp ="756,981";}
         else if (line.equals("Sierra Leone Freetown 27,699 6,005,250")){nameTemp = "Sierra Leone"; capitalTemp = "Freetown"; areaTemp ="27,699";}
         else if (line.equals("Solomon Islands Honiara 10,985 552,438")){nameTemp = "Solomon Islands"; capitalTemp = "Honiara"; areaTemp ="10,985";}
         else if (line.equals("South Africa Pretoria 471,008 44,187,637")){nameTemp = "South Africa"; capitalTemp = "Pretoria"; areaTemp ="471,008";}
         else if (line.equals("Sri Lanka Colombo 25,332 20,222,240")){nameTemp = "Sri Lanka"; capitalTemp = "Colombo"; areaTemp ="25,332";}
         else if (line.equals("Trinidad and Tobago Port-of-Spain 1,980 1,065,842")){nameTemp = "Trinidad and Tobago"; capitalTemp = "Port-of-Spain"; areaTemp ="1,980";}
         else if (line.equals("United Arab Emirates Abu Dhabi 32,000 2,602,713")){nameTemp = "United Arab Emirates"; capitalTemp = "Abu Dhabi"; areaTemp ="32,000";}
         else if (line.equals("United Kingdom London 94,525 60,609,153")){nameTemp = "United Kingdom"; capitalTemp = "London"; areaTemp ="94,525";}
         else if (line.equals("United States Washington, D.C. 3,718,691 298,444,215")){nameTemp = "United States"; capitalTemp = "Washington, D.C."; areaTemp ="3,718,691";}
         else if (line.equals("Vatican City 0.17 932")){nameTemp = "Vatican City"; capitalTemp = "Vatican City"; areaTemp ="0.17";}
         else{ 
           int begin = 0, end1, end2;
           //this loop figures out where each break is for normal lines to split a line into name, capital and area
           while(true){
             if(line.charAt(begin) == ' ')
               break;
             begin++;
           }
           end1 = begin + 1;
           while(true){
             if(line.charAt(end1) == ' ')
               break;
             end1++;
           }
           end2 = end1 + 1;
           while(true){
             if(line.charAt(end2) == ' ')
               break;
             end2++;
           }
           nameTemp = line.substring(0, begin);
           capitalTemp = line.substring(begin, end1);
           areaTemp = line.substring(end1, end2);
         }
         
         name.add(nameTemp);
         capital.add(capitalTemp);
         area.add(areaTemp);
         
         
         
         for(int x = 0; x < line.length() -1; x++){
           if(line.charAt(x)==' ' && line.charAt(x-1)<= '9' && line.charAt(x-1)>= '0' && line.charAt(x+1)<= '9' && line.charAt(x+1)>= '0'){
             inPopulation = true;
             i = x -1;
           }
           if(inPopulation && line.charAt(x)>= '0' && line.charAt(x)<= '9'){
             popStr += line.charAt(x);
           }
         }
         if(popStr.charAt(popStr.length()-1) != line.charAt(line.length()-1)){
           popStr += line.charAt(line.length()-1);
         }
         populations.add(Integer.parseInt(popStr));
         nameCapitalArea.add(line.substring(0,i));
         terms++;
         
         
         
     }
     s.close();//Closes file
     }
     
     catch(Exception e) {}
     int length = nameCapitalArea.size();
     
         try{
           PrintWriter fw = new PrintWriter (new FileWriter ("sortedByCountry.txt"));
           for(int y = 0; y < terms; y ++){
            
             int tabs =  (int)(8- Math.floor(name.get(y).length()/8));// equation to find \ts needed to allign 
             
             
             fw.print(name.get(y));
             for(int x = 0; x < tabs; x++){
               fw.print("\t");
             }
             
             // this if structure determines the formatting for adding commas to numbers
             if(populations.get(y) < 1000)
               fw.println(populations.get(y));
             else if(populations.get(y) < 1000000){
              fw.print((populations.get(y) - populations.get(y)%1000)/1000 + ",");
              if(populations.get(y)%1000 < 10)
                fw.println("00" + populations.get(y)%1000);
              else if(populations.get(y)%1000 < 100)
                fw.println("0" + populations.get(y)%1000);
              else
                fw.println(populations.get(y)%1000);
             }
             else if(populations.get(y) < 1000000000){
               int millions, thousands, ones;
               ones = populations.get(y)% 1000;
               thousands = (populations.get(y)%1000000 - ones)/1000;
               millions = (populations.get(y) - ones - thousands*1000)/1000000;
               fw.print(millions + ",");
               
               if(thousands < 10)
                fw.print("00" + thousands);
              else if(thousands < 100)
                fw.print("0" + thousands);
              else
                fw.print(thousands);
              fw.print(",");
              
              if(ones < 10)
                fw.println("00" + ones);
              else if(ones < 100)
                fw.println("0" + ones);
              else
                fw.println(ones);
              
             }
             else{
               int billions, millions, thousands, ones;
               ones = populations.get(y)% 1000;
               thousands = (populations.get(y)%1000000 - ones)/1000;
               millions = (populations.get(y)%1000000000 - ones - thousands)/1000000;
               billions = (populations.get(y) - ones - thousands - millions)/1000000000;
               
               fw.print(billions + ",");
               if(millions < 10)
                fw.print("00" + millions);
              else if(millions < 100)
                fw.print("0" + millions);
              else
                fw.print(millions);
              fw.print(",");
              
               if(thousands < 10)
                fw.print("00" + thousands);
              else if(thousands < 100)
                fw.print("0" + thousands);
              else
                fw.print(thousands);
              fw.print(",");
              
              if(ones < 10)
                fw.println("00" + ones);
              else if(ones < 100)
                fw.println("0" + ones);
              else
                fw.println(ones);
             }
              
           }
          fw.close();
         }
         catch(Exception e){}
     //Selection Sorting for populations
        for (int i = 0; i < length-1; i++) 
        { 
            int min = i; //Default Minimum
            for (int x = i+1; x < length; x++) 
                if (populations.get(x)>populations.get(min)) {//Checks to find smallest mark)
                    min = x; 
                }
            String tempString = name.get(min); //Temporary String and Integer variables
            Integer tempInt = populations.get(min); 
            name.set(min, name.get(i)); //Swaps minimum String with first index
            name.set(i, tempString);
            populations.set(min, populations.get(i)); //Swaps smallest int with first index
            populations.set(i, tempInt);
        }
        
        try{
          //prints into the file
           PrintWriter fw = new PrintWriter (new FileWriter ("sortedByPopulation.txt"));
           
           for(int y = 0; y < terms; y ++){
            
             int tabs =  (int)(8- Math.floor(name.get(y).length()/8)); // equation to find amount of tabs needed to allign 
             
             
             fw.print(name.get(y));
             for(int x = 0; x < tabs; x++){ // tabs
               fw.print("\t");
             }
             // this if structure determines the formatting for adding commas to numbers
             if(populations.get(y) < 1000) // small population in the hundreds
               fw.println(populations.get(y));
             else if(populations.get(y) < 1000000){// small population in the thousands
              fw.print((populations.get(y) - populations.get(y)%1000)/1000 + ",");
              if(populations.get(y)%1000 < 10)
                fw.println("00" + populations.get(y)%1000);
              else if(populations.get(y)%1000 < 100)
                fw.println("0" + populations.get(y)%1000);
              else
                fw.println(populations.get(y)%1000);
             }
             else if(populations.get(y) < 1000000000){ //population in the millions
               int millions, thousands, ones;
               ones = populations.get(y)% 1000;
               thousands = (populations.get(y)%1000000 - ones)/1000;
               millions = (populations.get(y) - ones - thousands*1000)/1000000;
               fw.print(millions + ",");
               
               if(thousands < 10)
                fw.print("00" + thousands);
              else if(thousands < 100)
                fw.print("0" + thousands);
              else
                fw.print(thousands);
              fw.print(",");
              
              if(ones < 10)
                fw.println("00" + ones);
              else if(ones < 100)
                fw.println("0" + ones);
              else
                fw.println(ones);
              
             }
             else{ // population in the billions
               int billions, millions, thousands, ones;
               ones = populations.get(y)% 1000;
               thousands = (populations.get(y)%1000000 - ones)/1000;
               millions = (populations.get(y)%1000000000 - ones - thousands)/1000000;
               billions = (populations.get(y) - ones - thousands - millions)/1000000000;
               
               fw.print(billions + ",");
               if(millions < 10)
                fw.print("00" + millions);
              else if(millions < 100)
                fw.print("0" + millions);
              else
                fw.print(millions);
              fw.print(",");
              
               if(thousands < 10)
                fw.print("00" + thousands);
              else if(thousands < 100)
                fw.print("0" + thousands);
              else
                fw.print(thousands);
              fw.print(",");
              
              if(ones < 10)
                fw.println("00" + ones);
              else if(ones < 100)
                fw.println("0" + ones);
              else
                fw.println(ones);
             }
              
           }
          fw.close(); // fileWriter close
         }
         catch(Exception e){}
     
    }
}