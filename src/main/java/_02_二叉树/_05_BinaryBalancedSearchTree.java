package _02_二叉树;

import java.util.Comparator;

public class _05_BinaryBalancedSearchTree<E> extends _02_BinarySearchTree<E>{
    public _05_BinaryBalancedSearchTree() {
    }

    public _05_BinaryBalancedSearchTree(Comparator<E> comparator) {
        super(comparator);
    }

    //左旋转
    protected void rotateLeft(Node<E> node) {
        Node<E> parent = node.right;
        Node<E> child =  parent.left;

        //1.更改左右子节点
        parent.left = node;
        node.right = child;

        afterRotate(node,parent,child);
        //更新高度 更新高度只有AVL树需要做，红黑树不需要，因此公共接口中不写
        // updateHeight(node);
        // updateHeight(p);
    }

    //右旋转
    protected void rotateRight(Node<E> node){
        Node<E> parent = node.left;
        Node<E> child =  parent.right;

        parent.right = node;
        node.left = child;

        afterRotate(node,parent,child);

        //更新高度  更新高度只有AVL树需要做
        //updateHeight(node);
        //updateHeight(p);
    }


    //旋转之后 更新父子关系
    protected void afterRotate(Node<E> grand,Node<E> parent,Node<E> child){
        //todo 1.更新parent的 父子关系
        parent.parent = grand.parent;
        if(grand.isLeftChild()){
            grand.parent.left = parent;
        }else if(grand.isRightChild()){
            grand.parent.right = parent;
        }else{
            root = parent;
        }
        //todo 2.更新child的父子关系
        if(child != null){
            child.parent = grand;
        }
        //todo 3.更新grand的父子关系
        grand.parent = parent;
    }


    //统一旋转操作
    protected void rotate(
            Node<E> r, // 子树的根节点
            Node<E> b, Node<E> c,
            Node<E> d,
            Node<E> e, Node<E> f) {
        // 让d成为这颗子树的根结点
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else {
            root = d;
        }
        // b-c的关系
        b.right = c;
        if (c != null) {
            c.parent = b;
        }
        //updateHeight(b);

        // e-f
        f.left = e;
        if (e != null) {
            e.parent = f;
        }
        //updateHeight(f);

        // b-d-f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
        //updateHeight(d);

        //AVL树中需要更新节点高度
        //注意：更新高度的顺序必须是先更新低的节点
    }


}
