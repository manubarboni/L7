package Ex1;

import Ex1.Carte;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class MainApp {

    public static void scriere(Map<Integer, Carte> map) {
        try {
            ObjectMapper mapper=new ObjectMapper();
            File file=new File("src/main/resources/carti.json");
            mapper.writeValue(file,map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Map<Integer, Carte> citire() {
        try {
            File file=new File("src/main/resources/carti.json");
            ObjectMapper mapper=new ObjectMapper();
            Map<Integer, Carte> carti = mapper
                    .readValue(file, new TypeReference<Map<Integer,Carte>>(){});
            return carti;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        Map<Integer,Carte> map = citire();
        var entryset=map.entrySet();
        var it=entryset.iterator();
        while(it.hasNext())
        {
            var c = it.next();
            System.out.println(c.getKey() + "   " + c.getValue());
            if (c.getKey()==4)
                it.remove();
        }

        map.putIfAbsent(4,new Carte("Amintiri din copilarie","Ion Creanga",1850));
        scriere(map);
        System.out.println();
        map.forEach((key,value)->{
            System.out.println(key + "   " + value);
        });

        System.out.println();
        Set<Carte> setCarti = map.values().stream()
                .filter(carte->carte.autorul().equalsIgnoreCase("Yuval Noah Harari"))
                .collect(Collectors.toSet());
        setCarti.forEach(System.out::println);

        System.out.println();
        setCarti.stream()
                .sorted((carte1,carte2)-> carte1.titlul().compareTo(carte2.titlul()))
                .forEach(System.out::println);

        Optional<Carte> oldest;
        oldest=setCarti.stream()
                .sorted(Comparator.comparing(Carte::anul))
                .findFirst();
        System.out.println("Cea mai veche carte "+ oldest.get());
    }
}
