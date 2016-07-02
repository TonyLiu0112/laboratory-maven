package com.liuboyu.enums.finals;

/**
 * 用接口模拟可扩展的枚举
 * <p/>
 * Created by Tony on 3/1/16.
 */
public interface Operation {

    double apply(double x, double y);

}

enum BasicOperation implements Operation {

    PLUS("+") {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        @Override
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        @Override
        public double apply(double x, double y) {
            return x / y;
        }
    };

    private final String c;
    BasicOperation(String c) {
        this.c = c;
    }

}

class Test {
    public static void main(String[] args) {
        double x = 10, y = 5;
        tests(BasicOperation.class, x, y);
    }

    public static <T extends Enum<T> & Operation> void tests(Class<T> clazz, double x, double y) {
        for (Operation operation : clazz.getEnumConstants())
            System.out.println(operation.apply(x, y));
    }
}
