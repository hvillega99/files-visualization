/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;
import java.io.File;
import file.Archivo;
/**
 *
 * @author User
 */
public class Tree<T> {
    private TreeNode<T> root;

    public Tree() {
        this.root = null;
    }

    public Tree(T content) {
        this.root = new TreeNode<>(content);
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(TreeNode<T> root) {
        this.root = root;
    }
    
    
    
    public boolean isEmpty() {
        return this.root == null;
    }
    public boolean isLeaf() {
        return this.root==null||this.root.getList().size()==0;
    }
    
    public static void build(Tree<Archivo> tree){
        if(tree.getRoot().getContent().getArchivo().isDirectory()){
            for (File file:tree.getRoot().getContent().getArchivo().listFiles()) {
                Tree<Archivo> t2=null;
                if(file.isDirectory()){
                    Archivo ar=new Archivo(file);
                    t2=new Tree(ar);
                    build(t2);
                }
                else{
                    Archivo ar=new Archivo(file,file.length());
                    t2=new Tree(ar);
                }
                tree.getRoot().getContent().setSize(tree.getRoot().getContent().getSize()+t2.getRoot().getContent().getSize());
                tree.getRoot().getList().addLast(t2);
            }
        }
        else{
           tree.getRoot().getContent().setSize(tree.getRoot().getContent().getArchivo().length());
        }
    }
    
   
    
    
}
