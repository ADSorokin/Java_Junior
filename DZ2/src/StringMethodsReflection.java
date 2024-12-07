package src;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class StringMethodsReflection {
    public static void main(String[] args) {
        Class<?> stringClass = String.class;
        Method[] methods = stringClass.getDeclaredMethods();
        System.out.println("Методы класса String:");
        for (Method method : methods) {
            System.out.println("######################################");
            String modifiers = Modifier.toString(method.getModifiers());
            System.out.println("Модификаторы: " + modifiers);
            System.out.println("Имя метода: " + method.getName());
            System.out.println("Возвращаемый тип: " + method.getReturnType().getName());

            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length == 0) {
                System.out.println("Параметры: Метод не принимает параметров");
            } else {
                System.out.print("Параметры: ");
                for (Class<?> paramType : parameterTypes) {
                    System.out.print(paramType.getName() + "  ");
                }
                System.out.println();
            }
        }
    }
}