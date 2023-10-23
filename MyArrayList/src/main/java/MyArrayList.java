import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class MyArrayList<E> {

    private static final int INIT_CAPACITY = 10;
    private int size;
    private Object[] elements;

    public MyArrayList() {
        init();
    }


    public void add(E element) {
        if(size == elements.length) {
            this.increaseCapacity();
        }
        elements[size++] = element;
    }

    public void add(int index, E element) {
        if(index < 0 || index >=size ){
            throw new IndexOutOfBoundsException();
        }
        if(size == elements.length) {
            this.increaseCapacity();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index + 1);
        size++;

        elements[index] = element;
    }

    public void addAll(Collection<? extends E> c){
        Object[] newElements = c.toArray();
        int newElemsNum = newElements.length;
        while(size + newElemsNum > elements.length){
            increaseCapacity();
        }
        System.arraycopy(newElements, 0, elements, size, newElemsNum);
        size += newElemsNum;
    }

    public E get(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) elements[index];
    }

    public void clear(){
        this.init();
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E remove(int index) {
        E element;
        if(index < 0 || index >= size) { 
            throw new IndexOutOfBoundsException();
        }
        element = (E) elements[index];
        for(int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;
        size--;
        return element;
    }

    public boolean remove(Object o) {
        if (o == null) return false;
        for (int i = 0; i < size; i++) {
            if (o.equals(elements[i])){
                this.remove(i);
                return true;
            }
        }
        return false;
    }

    void sort(Comparator<? super E> comparator){
        this.quickSort(comparator, 0, size - 1);
    }

    void quickSort(Comparator<? super E> comparator, int beginIndex, int endIndex){
        if (elements.length == 0 || beginIndex >= endIndex) // если длина массива равна 0 или если уже нечего делить,
            return; // завершить выполнение

        // выбор опорного элемента
        int middle = beginIndex + (endIndex - beginIndex) / 2; // берется индекс элемента в середине массива
        E pivot = (E)elements[middle]; // а значение этого элемента принимается в качестве опорного элемента

        // разделение на подмассивы, которые больше и меньше опорного элемента
        int i = beginIndex, j = endIndex; // присваивание "счетчикам" i и j индексов левой и правой границы массива соответственно
        while (i <= j) { // пока значение i не превосходит значение j,
            while(comparator.compare((E)elements[i], pivot) < 0) {  // пока значение i-го элемента массива меньше опорного
                i++; // инкремент i
            }

            while(comparator.compare((E)elements[j], pivot) > 0) { // пока значение j-го элемента массива больше опорного
                j--; // декремент j
            }

            // замена элементов местами
            if (i <= j) { // если счетчик левой границы еще не превосходит счетчик правой границы
                Object temp = elements[i]; // временной переменной присваивается значение i-го элемента
                elements[i] = elements[j]; // i-ому элементу присваивается значение j-го элемента
                elements[j] = temp; // j-ому элементу присваивается значение временной переменной
                i++; // инкремент i
                j--; // декремент j
            }
        }

        // вызов рекурсии для сортировки левой и правой части
        if (beginIndex < j) { // если индекс j остался больше beginIndex,
            quickSort(comparator, beginIndex, j); // рекурсивно вызываем сортировку для левой части массива
        }
        if (endIndex > i) { // если индекс i остался меньше endIndex,
            quickSort(comparator, i, endIndex); // рекурсивно вызываем сортировку для правой части массива
        }
    }

    @Override
    public String toString(){
        Object[] array = new Object[size];
        System.arraycopy(elements, 0, array, 0, size);
        return Arrays.toString(array) + "; size: " + this.size + "; capacity: " + elements.length;
    }

    private void increaseCapacity() {
        int newCapacity = elements.length * 2;
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }

    private void init() {
        elements = new Object[INIT_CAPACITY];
        size = 0;
    }
}
