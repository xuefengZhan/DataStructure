package _02_二叉树;

import BinaryTreePrinter.src.com.mj.printer.BinaryTreeInfo;
import BinaryTreePrinter.src.com.mj.printer.BinaryTrees;

import java.util.Comparator;


public class _02_BinarySearchTree<E> extends _01_BinaryTree<E> {
    private Comparator<E> comparator;

    public _02_BinarySearchTree(){}

    public _02_BinarySearchTree(Comparator<E> comparator){
        this.comparator = comparator;
    }

    private int Compare(E e1,E e2){
        if(comparator == null){
            return ((Comparable)e1).compareTo(e2);
        }else{
            return comparator.compare(e1,e2);
        }
    }

    private void elementNotNullCheck(E element){
        if(element == null){
            throw new IllegalArgumentException("element can not be null");
        }
    }

    //todo 提供一个add后的操作，普通BST中不做任何实现
    protected void afterAdd(Node<E> node){}
    protected void afterRemove(Node<E> node,Node<E> replacement){}

    //对于二叉搜索树来说，AVL树和RBTree的节点类型都不同，因此为了兼容，createNode方法，用于给子类各种树实现，创建属于自己的Node
    protected Node<E> createNode(E element,Node<E> parent){
        return new Node<E>(element,parent);
    }

    public void add(E element){
        elementNotNullCheck(element);
        if(root == null){
            //root = new Node(element,null);
            root = createNode(element,null);
            size++;
            afterAdd(root);
            return;
        }
        //1.新节点插到哪个节点下面？
        //2.新节点插到该节点的左边还是右边？
        Node<E> node = root;
        Node<E> parent = null;
        int cmp = 0;
        while(node!=null){
            parent = node;
            cmp = Compare(node.element,element);
            if(cmp > 0){
                node = node.left;
            }else if(cmp < 0){
                node = node.right;
            }else{
                return;
            }
        }
        //Node<E> newNode = new Node(element,parent);
        Node<E> newNode = createNode(element,parent);
        if(cmp>0){
            parent.left = newNode;
        }else{
            parent.right = newNode;
        }
        size++;
        afterAdd(newNode);
    }


    //根据值找到节点
    protected Node<E> node(E element){
        elementNotNullCheck(element);
        Node<E> node = root;
        while(node!= null){
            int cmp = Compare(element,node.element);

            if(cmp > 0){
                node = node.right;
            }else if(cmp<0){
                node = node.left;
            }else{
                return node;
            }
        }
        //node == null 或者 node的值和element相同
        return null;
    }

    private void remove(Node<E> node){
        if(node == null) return;
        //先处理度为2的节点，因为也是删除度为1的节点
        Node<E> del = null;
        if(node.left != null && node.right != null){
            del = predesessor(node);//前继节点
            node.element = del.element;
        }else{
            del = node;
        }
        //del要么度为1，要那么度为0
        //child为del不为空的子节点。如果child为null，则del为叶子结点。
        Node<E> child = del.left == null ? del.right:del.left;

        if(child != null){  //del是度为1 的节点  需要做这一步
            child.parent = del.parent;
        }
        if(del.parent == null){
            root = child;
        }else{
            if(del == del.parent.left){
                del.parent.left = child;
            }else{
                del.parent.right = child;
            }
        }
        //节点真正被删除的时候删除
        afterRemove(del,child);
        size--;
    }



    public void remove(E element){
        remove(node(element));
    }

    public boolean contains(E element){
        return node(element) == null;
    }


}




