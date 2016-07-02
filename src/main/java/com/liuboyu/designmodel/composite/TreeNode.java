package com.liuboyu.designmodel.composite;

import java.util.LinkedList;
import java.util.List;

/**
 * 组合模式-构建树结构
 * 树节点
 * @author Bob
 * @Feb 25, 2015
 */
public class TreeNode {
	
	/**
	 * 当前节点
	 */
	private String name;
	
	/**
	 * 父节点
	 */
	private TreeNode parentNade;
	
	/**
	 * 儿子节点
	 */
	private List<TreeNode> childNode = new LinkedList<TreeNode>();
	
	/**
	 * 左边兄弟节点
	 */
	private TreeNode leftBrotherNode;
	
	/**
	 * 右边兄弟节点
	 */
	private TreeNode rightBrotherNode;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TreeNode getParentNade() {
		return parentNade;
	}

	public void setParentNade(TreeNode parentNade) {
		this.parentNade = parentNade;
	}

	public List<TreeNode> getChildNode() {
		return childNode;
	}

	public void setChildNode(List<TreeNode> childNode) {
		this.childNode = childNode;
	}

	public TreeNode getLeftBrotherNode() {
		return leftBrotherNode;
	}

	public void setLeftBrotherNode(TreeNode leftBrotherNode) {
		this.leftBrotherNode = leftBrotherNode;
	}

	public TreeNode getRightBrotherNode() {
		return rightBrotherNode;
	}

	public void setRightBrotherNode(TreeNode rightBrotherNode) {
		this.rightBrotherNode = rightBrotherNode;
	}
	
}
