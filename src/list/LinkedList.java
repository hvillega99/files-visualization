/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package list;

/**
 *
 * @author User
 */
public class LinkedList<E> implements List<E> {
    private Node<E> first;
    private Node<E> last;
    private int effective;

    public LinkedList() {
         first = last = null;
         effective = 0;
    }

    @Override
    public boolean addFirst(E element) {
        Node<E> node = new Node<>(element); //Solo crea el nodo pero no conecta el nodo con los otros nodos
        if(element == null){
            return false;
        }
        else if(isEmpty()){ //No se puede directamente ingresar la info, se debe crear un nodo que contenga la data
            first = last = node;
        }
        else{
            node.setNext(first);
            first = node;
        }
        effective++;
        return true;
    }

    @Override
    public boolean addLast(E element) {
        Node<E> node = new Node<>(element);
        if(element == null){
            return false;
        }
        else if(isEmpty()){
            first = last = node;
        }
        else{
            last.setNext(node);
            last = node;
        }
        effective++;
        return true;
    }

    @Override
    public void add(int index, E element)throws Exception {
        if(element==null){
            System.out.println("No se permite el ingreso de null");
        }
        else if(index==0){
            addFirst(element);
        }
        else if((index < 0)||(effective-1 < index )){// falta en caso de que ingrese null
            throw new Exception ( "Indice fuera de rango") ; 
        }
        else if(index==effective-1){
            addLast(element);
        }
        else{
            Node<E> node = new Node<>(element);
            int i=0;
            for (Node<E> n = this.first; n != null; n = n.getNext()){
                if(i==index-1){
                    Node<E> nodeAgg=n.getNext();
                    node.setNext(nodeAgg);
                    n.setNext(node);
                }
                i++;
            // n es el nodo actual
            //E content = n.getContent();
            //Resolver este metodo mas tarde
            }
            effective++;
        }
    }

    @Override
    public E remove(int index)throws Exception {
        Node<E> replace = null;
        if((index < 0)||(effective-1 < index )){
            throw new Exception ( "Indice fuera de rango") ; 
        }
        else if(index==0){
            first=first.getNext();
            effective--;
            return first.getContent();
        }
        else{  
            int i=0;
            for (Node<E> n = this.first; n != null; n = n.getNext()){
                if(i==index-1){
                    replace=n.getNext().getNext();
                    n.setNext(replace);
                }
                i++;
            }
            effective--;
            return replace.getContent();
        } 
    }

    @Override
    public E get(int index)throws Exception {
        if((index < 0)||(effective-1 < index )){
            throw new Exception ( "Indice fuera de rango") ; 
        }
        E target = null;
        int i=0;
        for (Node<E> n = this.first; n != null; n = n.getNext()){
            if(i==index){
                target=n.getContent();
            }
            i++;
        }
        return target;
    }

    @Override
    public E set(int index, E element)throws Exception {
        if(element==null){
            System.out.println("No se permite el ingreso de null");
            return null;
        }
        else if((index < 0)||(effective-1 < index )){// falta en caso de que ingrese null
            throw new Exception ( "Indice fuera de rango") ; 
        }
        E target = null;
        int i=0;
        for (Node<E> n = this.first; n != null; n = n.getNext()){
            if(i==index){
                n.setContent(element);
                target=n.getContent();
            }
            i++;
        }
        return target;
    }

    @Override
    public int size() {
        return effective;
    }

    @Override
    public boolean isEmpty() {
        return(first == null)&&(last == null);
    }

    @Override
    public void clear() {
        first = last = null;
        effective = 0;
    }
    
    
    public String toString(){
        if(effective == 0){ 
            return "[ ]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Node<E> n = this.first; n != null; n = n.getNext()){
            E content = n.getContent();
            sb.append(content + ",");
        }
        String a = sb.substring(0,sb.length()-1);  
        return a+"]";
    }
    
    
}
