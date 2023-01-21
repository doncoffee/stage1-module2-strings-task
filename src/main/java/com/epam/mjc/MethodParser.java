package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String[] arr = signatureString.split("\\s+");
        String accessModifier = null;
        String returnType = null;
        String methodName = null;
        List<MethodSignature.Argument> listOfArguments = new ArrayList<>();
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i].equals("public") || arr[i].equals("private")
                    || arr[i].equals("protected")) {
                accessModifier = arr[i];
            } else if (arr[i].contains("(")) {
                methodName = arr[i].substring(0, arr[i].indexOf("("));
                returnType = arr[i - 1];
                i++;
            }
        }
        String[] arrayOfArgs = signatureString.substring(signatureString.indexOf("(") + 1,
                signatureString.length()).split("\\s+");
        for (int i = 0; i < arrayOfArgs.length; i++) {
            if (i % 2 == 0 && arrayOfArgs.length > 1) {
                String type = arrayOfArgs[i];
                String argument = arrayOfArgs[++i].substring(0, arrayOfArgs[i].length() - 1);
                listOfArguments.add(new MethodSignature.Argument(type, argument));
            }
        }
        return new MethodSignature(accessModifier, returnType, methodName, listOfArguments);
    }
}
