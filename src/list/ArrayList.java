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
public class ArrayList<E> implements List<E> {
    private E elements[];
    private int capacity = 10;
    private int effective;
    

    public ArrayList() {
        elements = (E[]) new Object[capacity];
        effective = 0;
    }
    

    @Override
    public boolean addFirst(E element) {
        if (element == null) {
            return false;
        } 
        else if (this.isEmpty()) {
            elements[effective++] = element;
            return true;
        } 
        else if (this.isFull()) {
            addCapacity();
        }
        for (int i = effective - 1; i >= 0; i--) {
            elements[i + 1] = elements[i];
        }
        elements[0] = element;
        effective++;
        return true;
    }
    

    @Override
    public boolean addLast(E element) {
        if (element == null) {
            return false;
        } 
        else if (this.isFull()) {
            addCapacity();
        }
        elements[effective++] = element;
        return true;
    }
    

    @Override
    public void add(int index, E element) throws Exception{
        if(index==0 && effective==0){
            elements[index]=element;
            effective++;
        }
        else if(element==null){
            System.out.println("No se permite el ingreso de null");
        }
        else if((index < 0)||(effective-1 < index )) {
             throw new Exception ( "Indice fuera de rango") ;  
        }        
        else if(index == capacity -1){
            addCapacity();
            for(int i = effective ; i>index ;i--){
                elements[i] = elements[i-1];
            } 
            elements[index] = element;
            effective ++;
        }
        else{
            for(int i = effective ; i>index ;i--){
                elements[i] = elements[i-1];
            } 
            elements[index] = element;
            effective ++;           
        }
        
               
    }

    @Override
    public E remove(int index)throws Exception {
        E eliminated=null;
        if((index < 0)||(effective-1 < index )){
            throw new Exception ( "Indice fuera de rango") ; 
        }
        else if(index==effective-1){
            eliminated=elements[index];
            elements[effective-1]=null;
            effective--;
            return eliminated;
            
        }
        eliminated=elements[index];
       for(int i =index; i<effective -1; i++){
           elements[i] = elements[i+1]; 
       }
            
        elements[effective-1]=null;
        effective--;
        if(this.isEmpty()){
            clear();
        }
        return eliminated;
    }

    @Override
    public E get(int index)throws Exception {
        if((index < 0)||(effective-1 < index )){
            throw new Exception ( "Indice fuera de rango") ; 
        }
        return elements[index];
    
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
        elements[index]=element;
        return elements[index];
    }

    @Override
    public int size() {
        return effective;
    }

    @Override
    public boolean isEmpty() {
        return effective == 0;
    }

    @Override
    public void clear() {
        effective=0;
        capacity=10;
        elements=(E[]) new Object[capacity];
    }
    
    
    public String toString(){
        if(effective == 0){ 
            return "[ ]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i=0; i<effective ;i++){
            sb.append(elements[i] + ",");
        }
        String a = sb.substring(0,sb.length()-1);  
        return a+"]";
    }
    
    
    private void addCapacity() {
        E[] copy = (E[]) new Object[capacity * 2];
        for (int i = 0; i < capacity; i++) {
            copy[i] = elements[i];
        }
        elements = copy;
        capacity = capacity * 2;
    }
    
    
    public boolean isFull() {
        return effective == capacity;
    }
    
}
