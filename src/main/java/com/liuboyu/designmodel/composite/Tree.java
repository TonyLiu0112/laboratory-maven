package com.liuboyu.designmodel.composite;

import java.util.List;

public class Tree {
	private TreeNode root;
	
	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}
	
	public void printTree(Tree tree) {
		TreeNode treeNode = tree.getRoot();
		printChildNode(treeNode);
	}
	
	public void printChildNode(TreeNode treeNode) {
		String result = "";
		List<TreeNode> list = treeNode.getChildNode();
		for (TreeNode node : list) {
			if (node.getChildNode().size() > 0) {
				printChildNode(node);
			}
			result += node.getName() + ",";
		}
		System.out.println(result);
	}

	public static void main(String[] args) {
//		
//		TreeNode rootTree = new TreeNode("bobo");
//		TreeNode one = new TreeNode("home");
//		
//		TreeNode tow1 = new TreeNode("bobo1");
//		TreeNode tow2 = new TreeNode("bobo2");
//		TreeNode tow3 = new TreeNode("bobo3");
//		TreeNode tow4 = new TreeNode("bobo4");
//		
//		Tree tree = new Tree();
//		tree.setRoot(rootTree);
//		
//		rootTree.addChildNode(one);
//		
//		one.addChildNode(tow1);
//		one.addChildNode(tow2);
//		one.addChildNode(tow3);
//		one.addChildNode(tow4);
//		
//		tree.printTree(tree);
	}
}
