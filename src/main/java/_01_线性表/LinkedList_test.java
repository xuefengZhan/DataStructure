package _01_线性表;


import org.junit.Test;

public class LinkedList_test {
    @Test
    public void test_LinkedList(){
        LinkedList_02<Integer> list = new LinkedList_02();

        list.add(1);
        list.add(2);


        list.add(0,3);
        System.out.println(list); //3 1 2


        list.set(1,4);
        System.out.println(list); //3 4 2

        //System.out.println(list.get(3));


        System.out.println(list.remove(2));  //2
        System.out.println(list);//3 4


        System.out.println(list.indexOf(4));//1

        list.clear();
        System.out.println(list.size());//0

        System.out.println(list);


        list.add(1);
        list.add(2);
        list.add(null);
        list.add(3);
        list.add(4);

        System.out.println(list);

        System.out.println(list.indexOf(4));
    }

    @Test
    public void test_DoubleLinkedList(){
        DoubleLinkedList<Integer> list = new DoubleLinkedList();

        list.add(1);
        list.add(2);


        list.add(0,3);
        System.out.println(list); //3 1 2


        list.set(1,4);
        System.out.println(list); //3 4 2

        //System.out.println(list.get(3));


        System.out.println(list.remove(2));  //2
        System.out.println(list);//3 4


        System.out.println(list.indexOf(4));//1

        list.clear();
        System.out.println(list.size());//0

        System.out.println(list);


        list.add(1);
        list.add(2);
        list.add(null);
        list.add(3);
        list.add(4);

        System.out.println(list);


        System.out.println(list.indexOf(null));
        System.out.println(list.indexOf(4));
    }
}
