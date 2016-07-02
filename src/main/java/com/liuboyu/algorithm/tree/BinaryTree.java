package com.liuboyu.algorithm.tree;

/**
 * 二叉树
 * (完美结构)满二叉树＋完全二叉树(基本结构满足)
 * 基本公式：
 * 1. 第一个节点为1的情况下：子节点(left) = 2*父节点; 子节点(right) = 2*父节点+1;
 * 每一棵树构建过程一定是从 n/2 的位置开始
 * 2. 第二个节点为2的情况下：子节点(left) = 2*父节点+1; 子节点(right) = 2*父节点+2;
 * 每一棵树构建过程一定是从 (n-1)/2 的位置开始
 *
 * @author liuboyu
 */
public class BinaryTree {

    // 不规则的树
    private static int[] tree = {1, 2, 5, 12, 7, 17, 25, 19, 36, 99, 22, 28, 46, 92};

    /**
     * 利用一维数组
     * 构建一棵二叉树
     *
     * @param args
     */
    public static void main(String[] args) {
        int treeLength = tree.length;
        int startNodeIndex = (treeLength - 1) / 2;

        for (int i = startNodeIndex; i >= 0; i--) {
            int leftNode = 2 * startNodeIndex;
            int rightNode = leftNode + 1;
            complare(startNodeIndex, leftNode);
            complare(startNodeIndex, rightNode);
        }

        // 输出规则的完全二叉树
        for (int i = 0; i < tree.length; i++) {
            System.out.print(tree[i] + " ");
        }
    }

    public static void complare(int left, int right) {
        if (right <= tree.length && tree[left] > tree[right]) {
            int leftVal = tree[left];
            tree[left] = tree[right];
            tree[right] = leftVal;
        }
    }
}
