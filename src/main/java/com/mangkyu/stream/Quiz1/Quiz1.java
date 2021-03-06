package com.mangkyu.stream.Quiz1;

import com.opencsv.CSVReader;
import org.jetbrains.annotations.NotNull;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.*;
import static java.util.function.Function.*;
import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toMap;

public class Quiz1 {

    public Map<String, Integer> quiz1() throws IOException {
        List<String[]> csvLines = readCsvLines();
        return csvLines.stream()
                .map(strings -> strings[1].replaceAll("\\s", ""))
                .flatMap(s -> stream(s.split(":")))
                .collect(toMap(identity(), value -> 1, Integer::sum, HashMap::new));
    }

    public Map<String, Integer> quiz2() throws IOException {
        List<String[]> csvLines = readCsvLines();
        return csvLines.stream()
                .filter(s -> s[0].replaceAll("\\s","").equals("정프로"))
                .map(strings -> strings[1].replaceAll("\\s", ""))
                .flatMap(s -> stream(s.split(":")))
                .collect(toMap(identity(), value -> 1, Integer::sum));
    }


    public int quiz3() throws IOException {
        List<String[]> csvLines = readCsvLines();
        return csvLines.stream()
                .map(strings -> strings[2])
                .mapToInt(line -> func(line, "좋아", 0))
                .sum();
    }

    private int func(@NotNull String value, String target, int index) {
        int resultInt = value.indexOf(target, index);
        return stopRecursionIfTargetNotFound(value, target, resultInt);
    }

    private int stopRecursionIfTargetNotFound(String value, String target, int resultInt) {
        if(resultInt > 0) {
            return 1 + func(value, target, resultInt + 2);
        }
        return 0;
    }


    private List<String[]> readCsvLines() throws IOException {
        CSVReader csvReader = new CSVReader(new FileReader(getClass().getResource("/user.csv").getFile()));
        csvReader.readNext();
        return csvReader.readAll();
    }

}
