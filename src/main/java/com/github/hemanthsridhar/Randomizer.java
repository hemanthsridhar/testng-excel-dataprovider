package com.github.hemanthsridhar;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

abstract class Randomizer {

    public String checkIfRandomAndInvoke(String data) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        RandomizerConstants matchedValue = RandomizerConstants.ifContains(data);
        String randomData = null;
        if (matchedValue != null) {
            switch (matchedValue) {
                case RANDOM_ALPHABETIC:
                    randomData = invokeRandom(data, RandomizerConstants.RANDOM_ALPHABETIC.toString());
                    break;
                case RANDOM:
                    randomData = invokeRandom(data, RandomizerConstants.RANDOM.toString());
                    break;
                case RANDOM_ALPHANUMERIC:
                    randomData = invokeRandom(data, RandomizerConstants.RANDOM_ALPHANUMERIC.toString());
                    break;
                case RANDOM_ASCII:
                    randomData = invokeRandom(data, RandomizerConstants.RANDOM_ASCII.toString());
                    break;
                case RANDOM_GRAPH:
                    randomData = invokeRandom(data, RandomizerConstants.RANDOM_GRAPH.toString());
                    break;
                case RANDOM_NUMERIC:
                    randomData = invokeRandom(data, RandomizerConstants.RANDOM_NUMERIC.toString());
                    break;
                default:
                    randomData = data;
                    break;
            }
        }
        return randomData;
    }

    private String invokeRandom(String data, String methodName) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        String[] randomArray = data.split("\\(");
        String[] params = randomArray[1].replaceAll("\\)", "").split(":");
        Class c = Class.forName("org.apache.commons.lang3.RandomStringUtils");
        Object obj = c.newInstance();
        if(params.length == 1){
            Method method = c.getDeclaredMethod(methodName, int.class);
            method.setAccessible(true);
            return method.invoke(obj, Integer.parseInt(params[0])).toString();
        }
        else if(params.length == 2){
            Method method = c.getDeclaredMethod(methodName, int.class, int.class);
            method.setAccessible(true);
            return method.invoke(obj, Integer.parseInt(params[0]), Integer.parseInt(params[1])).toString();
        }
        else{
            throw new IllegalArgumentException("Cannot have more than 2 parameters");
        }
    }
}
