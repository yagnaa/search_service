package com.zenefits.db.models;

import java.util.HashMap;

public class Node {
//	private Character c;
	private HashMap<Character,Node> children;
	
	public Node(Character c, HashMap<Character, Node> children) {
		super();
	//	this.c = c;
		this.children = children;
	}
	
	public Node(Character c) {
		this(c,new HashMap<Character, Node>());
	}
	
	public Node() {
		this(' ',new HashMap<Character, Node>());
	}
	
//	public Character getC() {
//		return c;
//	}
//	public void setC(Character c) {
//		this.c = c;
//	}
	public HashMap<Character, Node> getChildren() {
		return children;
	}
	public void setChildren(HashMap<Character, Node> children) {
		this.children = children;
	}
	
	
}
