package com.zenefits.db.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.zenefits.Constants;

public class Trie {
	
	private Node head;
	
	public void init()
	{
		head = new Node();
	}
	
	public void addString(String stringToAdd)
	{
		if(stringToAdd!=null && !stringToAdd.isEmpty())
		{
			if(head==null)
			{
				init();
			}
			
			Node temp = head;
			int i=0;
			for(;i<stringToAdd.length();i++)
			{
				//Iterate till the point where you want to add
				if(temp.getChildren().containsKey(stringToAdd.charAt(i)))
				{
					temp = temp.getChildren().get(stringToAdd.charAt(i));
				}
				else
				{
					//Add a new node with ith character 
					break;
				}
			}
			
			if(i != stringToAdd.length())
			{
				//Add a new node with ith character 
				addNodeInternal(temp,stringToAdd,i);
			}
		}
	}
	
	private void addNodeInternal(Node parentNode, String stringToAdd,int index)
	{
		if(stringToAdd==null || stringToAdd.isEmpty() || index<0 || index>=stringToAdd.length())
		{
			return;
		}
		for(int i=index;i<stringToAdd.length();i++)
		{
			Node newNode = new Node(stringToAdd.charAt(i));
			parentNode.getChildren().put(stringToAdd.charAt(i), newNode);
			parentNode = newNode;
		}
		//Add a terminating node
		addTerminatingNode(parentNode);
		
	}
	
	private void addTerminatingNode(Node node)
	{
		Node newNode = new Node(Constants.TERMINATING_SYMBOL);
		node.getChildren().put(Constants.TERMINATING_SYMBOL, newNode);
	}
	
	public void deleteString()
	{
		
	}
	
	
	public List<String> getString(String prefix)
	{
		List<String> returnValue = new ArrayList<>();
		if(prefix!=null && !prefix.isEmpty() && prefix.length()>Constants.MIN_PREFIX_LENGTH)
		{
			if(head==null)
			{
				init();
			}
			
			Node temp = head;
			int i=0;
			for(;i<prefix.length();i++)
			{
				if(temp.getChildren().containsKey(prefix.charAt(i)))
				{
					temp = temp.getChildren().get(prefix.charAt(i));
				}
				else
				{
					break;
				}
			}
			
			if(i < prefix.length())
			{
				returnValue.add(prefix);
			}
			else
			{
				returnValue.addAll(getStringsInternal(prefix,temp));
			}
		}
		
		return returnValue;
	}
	
	public List<String> getStringsInternal(String prefix,Node node)
	{
		List<String> returnValue = new ArrayList<>();
		
		if(node.getChildren().isEmpty())
		{
			returnValue.add(prefix);
		}else
		{
			for(Entry<Character, Node> entry:node.getChildren().entrySet())
			{
				if(entry.getKey() != Constants.TERMINATING_SYMBOL)
				{
					returnValue.addAll(getStringsInternal(prefix+entry.getKey(),entry.getValue()));
				}
				else
				{
					returnValue.add(prefix);
				}
				
			}
			
//			for(Node temp:node.getChildren().values())
//			{
//				if(temp.getC() != Constants.TERMINATING_SYMBOL)
//				{
//					returnValue.addAll(getStringsInternal(prefix+temp.getC(),temp));
//				}
//				else
//				{
//					returnValue.add(prefix);
//				}
//				
//			}
		}
		
		return returnValue;	
	}
	
	

}
