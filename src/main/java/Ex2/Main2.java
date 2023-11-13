package Ex2;

import Ex1.Carte;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main2 {
    public static void scriere(Set<InstrumentMuzical> set) {
        try {
            ObjectMapper mapper=new ObjectMapper();
            mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator());
            File file=new File("src/main/resources/instrumente.json");
            mapper.writeValue(file,set);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Set<InstrumentMuzical> citire() {
        try {
            File file=new File("src/main/resources/instrumente.json");
            ObjectMapper mapper=new ObjectMapper();
            mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator());
            Set<InstrumentMuzical> instrumente = mapper
                    .readValue(file, new TypeReference<HashSet<InstrumentMuzical>>(){});
            return instrumente;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        Set<InstrumentMuzical> setinstrumente = new HashSet<>();
        InstrumentMuzical i1 = new Chitara("1",1,TipChitara.ACUSTICA,4);
        InstrumentMuzical i2 = new Chitara("2",2,TipChitara.ACUSTICA,4);
        InstrumentMuzical i3 = new Chitara("3",3,TipChitara.ACUSTICA,8);
        InstrumentMuzical i4 = new SetTobe("4",4,TipTobe.ACUSTICE,4,4);
        InstrumentMuzical i5 = new SetTobe("5",5,TipTobe.ACUSTICE,4,5);
        InstrumentMuzical i6 = new SetTobe("6",6,TipTobe.ACUSTICE,4,6);
        setinstrumente.add(i1);
        setinstrumente.add(i2);
        setinstrumente.add(i3);
        setinstrumente.add(i4);
        setinstrumente.add(i5);
        setinstrumente.add(i6);
        scriere(setinstrumente);
        Set<InstrumentMuzical> setinstrumente2 = citire();
        setinstrumente2.forEach(System.out::println);
        System.out.println();

        setinstrumente.removeIf(instrumentMuzical -> instrumentMuzical.pret>3000);
        setinstrumente.stream()
                .filter(instrumentMuzical -> instrumentMuzical instanceof Chitara)
                .forEach(System.out::println);

        System.out.println();
        setinstrumente.stream()
                .filter(instrumentMuzical -> instrumentMuzical.getClass()==SetTobe.class)
                .forEach(System.out::println);

        System.out.println();
        Optional<InstrumentMuzical> corzi;
        corzi=setinstrumente.stream()
                .filter(instrumentMuzical -> instrumentMuzical.getClass()==Chitara.class)
                .sorted(Comparator.comparing(instrumentMuzical -> ((Chitara) instrumentMuzical).nr_corzi).reversed())
                .findFirst();
        System.out.println();
        System.out.println("Cele mai multe corzi"+ corzi.get());
        System.out.println();

        setinstrumente.stream()
                .filter(instrumentMuzical -> instrumentMuzical.getClass()==SetTobe.class)
                .sorted(Comparator.comparing(instrumentMuzical -> ((SetTobe) instrumentMuzical).getNr_tobe()))
                .forEach(System.out::println);
    if(setinstrumente.add(i1))
    {
        System.out.println("Element was added");
    }
    else
        System.out.println("Element could not be added");
    }

}
