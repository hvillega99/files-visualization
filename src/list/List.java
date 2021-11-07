package list;

public interface List<E> {

    public boolean addFirst(E e); // inserta el elemento e al inicio // ya esta XD

    public boolean addLast(E e); // inserta el elemento e al final // ya esta XD

    public void add(int index, E element) throws Exception; // ya esta

    public E remove(int index)throws Exception;

    public E get(int index)throws Exception;

    public E set(int index, E element)throws Exception;

    public int size(); // ya esta

    public boolean isEmpty(); // ya esta

    public void clear();// ya esta

    @Override
    public String toString();// ya esta 

}
