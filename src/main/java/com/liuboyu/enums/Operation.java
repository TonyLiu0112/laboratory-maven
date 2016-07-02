package com.liuboyu.enums;

import java.util.EnumSet;

/**
 * 枚举用法
 * <p/>
 * Created by Tony on 2/15/16.
 */
public enum Operation {

    PLUS("+") {
        @Override
        double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        @Override
        double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        @Override
        double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        @Override
        double apply(double x, double y) {
            return x / y;
        }
    };

    private final String symbol;

    Operation(String symbol) {
        this.symbol = symbol;
    }

    abstract double apply(double x, double y);

    /**
     * 另外一种实现方式,非抽象方法方式,显然没有上面的实现优雅,可维护性也变得困难,一个改动影响所有元素
     *
     * @param x
     * @param y
     * @return
     */
    double apply2(double x, double y) {
        switch (this) {
            case PLUS:
                return x + y;
            case MINUS:
                return x - y;
            case TIMES:
                return x * y;
            default:
                return x / y;
        }
    }

    @Override
    public String toString() {
        return symbol;
    }

}

class Test {
    public static void main(String[] args) {
        System.out.println(Operation.DIVIDE.apply(2, 4));
        System.out.println(Operation.DIVIDE.apply2(2, 4));
        System.out.println(Operation.PLUS.ordinal());
        EnumSet.of(Operation.DIVIDE, Operation.PLUS);
    }
}
