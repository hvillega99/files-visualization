/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

import list.LinkedList;

/**
 *
 * @author User
 */
public class TreeNode<T> {
    private T content;
    private LinkedList<Tree<T>> list;

    
    
    public TreeNode() {
        this(null,null);
    }
    public TreeNode(T content) {
        this.content = content;
        this.list=new LinkedList<Tree<T>>() ;
    }
    public TreeNode(T content, LinkedList<Tree<T>> list){
        this.content=content;
        this.list=list;
    }
    

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public LinkedList<Tree<T>> getList() {
        return list;
    }

    public void setList(LinkedList<Tree<T>> list) {
        this.list = list;
    }
    
    
    
    
    
}
