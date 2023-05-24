package eu.glowacki.utp.assignment04;

import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class InputParser {

    // 1. Use regular expresssions (Pattern) for validating input data
    //    U¿yæ regularnych wyra¿eñ (Pattern) do walidacji danych wejœciowych
    //
    // 2. Convert input string representing date using SimpleDateFormat "yyyy-MM-dd"
    //    Konwersjê wejœciowego ci¹gu znaków reprezentuj¹cego datê nale¿y oprzeæ np. DateFormat
    //    SimpleDateFormat format "yyyy-MM-dd"
    private final static String regexExpression = "([A-Z][a-z]+[ ]*)+[0-9]{4}-[0-9]{2}-[0-9]{2}";
    private final static Pattern pattern = Pattern.compile(regexExpression);
    private final static String datePattern = "yyyy-MM-dd";
    private final static String filePath = "C:\\Study\\skeletons\\assignment-04\\src\\main\\java\\eu\\glowacki\\utp\\assignment04\\comparators\\binarytest";
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);

//    public static List<Person> parse(File file) {
//        try {
//            Stream<String> lines = Files.lines(Path.of(file.getAbsolutePath()));
//            return lines.filter(InputParser::isCorrectOutputData)
//                    .map(InputParser::getPerson)
//                    .filter(Objects::nonNull)
//                    .collect(Collectors.toList());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    //Assignment 08




    public static List<Person> parse2() throws IOException {

        return getSingleLines(getData(filePath)).stream().filter(InputParser::isCorrectOutputData)
                .map(InputParser::getPerson)
                .filter(Objects::nonNull).collect(Collectors.toList());
    }



//        public static String getData(String filePath) throws IOException {
//        String resultText = "";
//        FileInputStream input = new FileInputStream(filePath);
//        DataInputStream dataInput = new DataInputStream(input);
//        int byteCount = dataInput.available();
//        byte[] bytes = new byte[byteCount];
//        dataInput.read(bytes);
//        for (byte singleByte : bytes) {
//            resultText += (char)singleByte;
//        }
//        return resultText;
//    }
public static String getData(String filePath) throws IOException {
    String readDataBinary = "";
    String result = "";
    FileInputStream inputStream = new FileInputStream(filePath);
    DataInputStream dataInputStr = new DataInputStream(inputStream);
    int count = inputStream.available();
    byte[] b = new byte[count];
    dataInputStr.read(b);
    for (byte by : b) {
        readDataBinary += (char) by;
    }
    for (String s : readDataBinary.split(" ")) {
        int charNum = Integer.parseInt(s,2);
        result += Character.toString((char) charNum);
    }
    return result;
}

    public static List<String> getSingleLines(String text){
        return Arrays.stream(text.split("\n")).toList();
    }


    //---------------------Assignment 08

    private static boolean isCorrectOutputData(String textLine) {
        return pattern.matcher(textLine).find();
    }

    private static Person getPerson(String textLine) {
        String[] words = textLine.split(" ");
        if (words.length != 3)
            return null;
        try {
            return new Person(words[0], words[1], dateFormat.parse(words[2]));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}