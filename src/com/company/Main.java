package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Automat numberAutomat = new Automat("numberAutomat.txt");
            Automat stringAutomat = new Automat("stringAutomat.txt");
            Automat operatorAutomat = new Automat("operatorAutomat.txt");
            Automat idAutomat = new Automat("idAutomat.txt");

            File file = new File("program.txt");
            Scanner fileReader = new Scanner(file);

            String delimeters ="({,)};";
            String operators ="+-*/<=&|";
            String[] keywordsArr = new String[]{"let", "var", "const", "for", "while", "do", "if", "else", "break", "continue"};
            List<String> keywords = Arrays.asList(keywordsArr);

            while (fileReader.hasNextLine()) {
                String line = fileReader.next();

                boolean notDelimeterOccurs = false;
                boolean lexemWasPrinted = false;
                boolean operatorWasPrinted = false;
                String lexem = "";
                String operator = "";
                for(char ch: line.toCharArray()) {
                    if (Character.isWhitespace(ch)) {
                        continue;
                    }
                    if (!notDelimeterOccurs && delimeters.contains("" + ch)) {
                        System.out.print(ch);
                        System.out.println(" - additional operator");
                        continue;
                    }
                    if (!delimeters.contains("" + ch)) {
                        if(operators.contains("" + ch)) {
                            operator += Character.toString(ch);
                            notDelimeterOccurs = true;
                        } else {
                            lexem += Character.toString(ch);
                            notDelimeterOccurs = true;
                        }
                    } else {
                        if (!lexemWasPrinted && lexem.length() > 0) {
                            if (keywords.contains("" + lexem)) {
                                System.out.print(lexem);
                                System.out.println(" - keyword");
                                lexemWasPrinted = true;
                            } else if (numberAutomat.isLexem(lexem)) {
                                System.out.print(lexem);
                                System.out.println(" - number");
                                lexemWasPrinted = true;
                            } else if (idAutomat.isLexem(lexem)) {
                                System.out.print(lexem);
                                System.out.println(" - identificator");
                                lexemWasPrinted = true;
                            } else if (stringAutomat.isLexem(lexem)) {
                                System.out.print(lexem);
                                System.out.println(" - string");
                                lexemWasPrinted = true;
                            } else if (operatorAutomat.isLexem(lexem)) {
                                System.out.print(lexem);
                                System.out.println(" - operator");
                                lexemWasPrinted = true;
                            } else {
                                System.out.print(lexem);
                                System.out.println(" - Error");
                            }
                        }
                        if (!operatorWasPrinted && operator.length() > 0) {
                            if (operatorAutomat.isLexem(operator)) {
                                System.out.print(operator);
                                System.out.println(" - operator");
                                operatorWasPrinted = true;
                            }
                        }
                        System.out.print(ch);
                        System.out.println(" - additional operator");
                        continue;
                    }
                }
                if (!lexemWasPrinted && lexem.length() > 0) {
                    if (keywords.contains("" + lexem)) {
                        System.out.print(lexem);
                        System.out.println(" - keyword");
                        lexemWasPrinted = true;
                    } else
                    if (numberAutomat.isLexem(lexem)) {
                        System.out.print(lexem);
                        System.out.println(" - number");
                        lexemWasPrinted = true;
                    } else if (idAutomat.isLexem(lexem)) {
                        System.out.print(lexem);
                        System.out.println(" - identificator");
                        lexemWasPrinted = true;
                    } else if (stringAutomat.isLexem(lexem)) {
                        System.out.print(lexem);
                        System.out.println(" - string");
                        lexemWasPrinted = true;
                    } else if (operatorAutomat.isLexem(lexem)) {
                        System.out.print(lexem);
                        System.out.println(" - operator");
                        lexemWasPrinted = true;
                    } else {
                        System.out.print(lexem);
                        System.out.println(" - Error");
                    }
                }
                if (!operatorWasPrinted&& operator.length() > 0) {
                    if (operatorAutomat.isLexem(operator)) {
                        System.out.print(operator);
                        System.out.println(" - operator");
                    }
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
