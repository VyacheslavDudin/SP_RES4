package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static PatternResult isLexem(Pattern pattern, String text) {
        Matcher matcher = pattern.matcher(text);
        PatternResult res = new PatternResult();
        res.isFound = matcher.matches();
        if (matcher.find()) {
            res.start = matcher.start();
            res.end = matcher.end();
        }

        return res;
    }
    
    public static void main(String[] args) {
        try {
            Pattern numberPattern = Pattern.compile("(-)?(0|[1-9]+[0-9]*|[1-9]+\\.[0-9]+|0\\.[0-9]*[1-9]+[0-9]*)(e[+-]?[1-9]+[0-9]*)?");
            Pattern stringPattern = Pattern.compile("('[^']*'|\"[^\"]*\"|`[^`]*`)");
            Pattern operatorPattern = Pattern.compile("((\\+[+=]?)|(\\-[-=]?)|(\\*[=]?)|([\\<\\>][=]?)|(\\/[=]?)|([=]{1,3})|[!]|([&]{1,2})|([|]{1,2}))");
            Pattern idPattern = Pattern.compile("([_a-zA-Z]+[a-zA-Z_0-9]*)");
            Pattern keywordsPattern = Pattern.compile("(let|var|const|for|while|do|if|else|break|continue)");

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
                            if (Main.isLexem(keywordsPattern, "" + lexem).isFound) {
                                System.out.print(lexem);
                                System.out.println(" - keyword");
                                lexemWasPrinted = true;
                            } else if (Main.isLexem(numberPattern, lexem).isFound) {
                                System.out.print(lexem);
                                System.out.println(" - number");
                                lexemWasPrinted = true;
                            } else if (Main.isLexem(idPattern, lexem).isFound) {
                                System.out.print(lexem);
                                System.out.println(" - identificator");
                                lexemWasPrinted = true;
                            } else if (Main.isLexem(stringPattern, lexem).isFound) {
                                System.out.print(lexem);
                                System.out.println(" - string");
                                lexemWasPrinted = true;
                            } else if (Main.isLexem(operatorPattern, lexem).isFound) {
                                System.out.print(lexem);
                                System.out.println(" - operator");
                                lexemWasPrinted = true;
                            } else {
                                System.out.print(lexem);
                                System.out.println(" - Error");
                            }
                        }
                        if (!operatorWasPrinted && operator.length() > 0) {
                            if (Main.isLexem(operatorPattern, operator).isFound) {
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
                    if (Main.isLexem(keywordsPattern, "" + lexem).isFound) {
                        System.out.print(lexem);
                        System.out.println(" - keyword");
                        lexemWasPrinted = true;
                    } else
                    if (Main.isLexem(numberPattern, lexem).isFound) {
                        System.out.print(lexem);
                        System.out.println(" - number");
                        lexemWasPrinted = true;
                    } else if (Main.isLexem(idPattern, lexem).isFound) {
                        System.out.print(lexem);
                        System.out.println(" - identificator");
                        lexemWasPrinted = true;
                    } else if (Main.isLexem(stringPattern, lexem).isFound) {
                        System.out.print(lexem);
                        System.out.println(" - string");
                        lexemWasPrinted = true;
                    } else if (Main.isLexem(operatorPattern, lexem).isFound) {
                        System.out.print(lexem);
                        System.out.println(" - operator");
                        lexemWasPrinted = true;
                    } else {
                        System.out.print(lexem);
                        System.out.println(" - Error");
                    }
                }
                if (!operatorWasPrinted&& operator.length() > 0) {
                    if (Main.isLexem(operatorPattern, operator).isFound) {
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
