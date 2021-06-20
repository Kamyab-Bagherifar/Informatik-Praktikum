package tree.bst;

import list.List;
import net.jqwik.api.*;
import tree.bst.tree.bst.Tree;

import static tree.bst.tree.bst.Tree.*;

public class TreeJqwikTest {



    @Data
    Iterable<Tuple.Tuple3<Tree<Integer>, Integer, Integer>> tree_data_test() {
        return Table.of(
                Tuple.of(tree(List.list(4, 2, 1, 3, 6, 5, 7)), 7, 8), Tuple.of(tree(List.list(4, 2, 1, 3, 6, 5, 7)), 2, 11),
                Tuple.of(tree(List.list(4, 2, 1, 3, 6, 5, 7)), 5, 10), Tuple.of(tree(List.list(4, 2, 1, 3, 6, 5, 7)), 1, 0),
                Tuple.of(tree(List.list(4, 2, 1, 3, 6, 5, 7)), 7, 15)
        );
    }

    @Property
    @FromData("tree_data_test")
    boolean member_Test(@ForAll Tree<Integer> t, @ForAll int isMember, @ForAll int isNotMember) {
        return t.member(isMember) && !t.member(isNotMember);
    }


    @Property
    @FromData("tree_data_test")
    boolean Insert_Size_Test(@ForAll Tree<Integer> t, @ForAll int isMember, @ForAll int isNotMember) {
        return t.insert(isNotMember).size() == t.size() + 1;
    }

    @Property
    @FromData("tree_data_test")
    boolean Insert_Member(@ForAll Tree<Integer> t, @ForAll int isMember, @ForAll int isNotMember) {
        return t.insert(isNotMember).member(isNotMember);
    }

    @Property
    @FromData("tree_data_test")
    boolean Remove_Member(@ForAll Tree<Integer> t, @ForAll int isMember, @ForAll int isNotMember) {
        return !t.remove(isMember).member(isMember);
    }

    @Property
    @FromData("tree_data_test")
    boolean Height_Size(@ForAll Tree<Integer> t, @ForAll int isMember, @ForAll int isNotMember) {
        return t.size() >= t.height();
    }




    @Data
    Iterable<Tuple.Tuple3<Tree<Integer>, Integer, Integer>> dataFind() {
        return Table.of(
                Tuple.of(tree(List.list(4, 2, 1, 3, 6, 5, 7)), 1, 7), Tuple.of(tree(List.list(4, 2, 1, 3, 6, 5, 10)), 1, 10),
                Tuple.of(tree(List.list(4, 2, 1, 3, 6, 5, 15)), 1, 15), Tuple.of(tree(List.list(17, 2, 1, 3, 6, 5, 7)), 1, 17),
                Tuple.of(tree(List.list(4, 2, 1, 20, 6, 5, 7)), 1, 20)
        );
    }

    @Property
    @FromData("dataFind")
    boolean find_Max_Min_test(@ForAll Tree<Integer> t, @ForAll int min, @ForAll int max) {
        return t.findMax() == max && t.findMin() == min;
    }


    @Property
    @FromData("dataFind")
    boolean Max_is_bigger_than_Min_test(@ForAll Tree<Integer> t, @ForAll int min, @ForAll int max) {
        return t.findMax() >= t.findMin();
    }


    @Data
    Iterable<Tuple.Tuple4<Tree<Integer>, Integer, Integer, Integer>> dataFind1() {
        return Table.of(
                Tuple.of(tree(List.list(8, 7, 9, 11, 2, 3)), 2, 3, 1), Tuple.of(tree(List.list(6, 3, 4, 7, 1, 2, 5, 9, 8)), 3, 4, 2),
                Tuple.of(tree(List.list(3, 4, 7, 2, 11, 6)), 3, 1, 2), Tuple.of(tree(List.list(17, 15, 18, 33, 55, 26, 44, 66)), 4, 1, 3),
                Tuple.of(tree(List.list(14, 7, 13, 24, 36, 6, 8)), 3, 2, 2), Tuple.of(tree(List.list(1, 2, 3, 7, 5, 8, 12)), 2, 4, 1)
        );
    }

    @Property
    @FromData("dataFind1")
    boolean sizeLeaf_sizeEmpty(@ForAll Tree<Integer> t, @ForAll int e, @ForAll int half, @ForAll int full) {
        return t.sizeEmpty() == e && t.sizeHalf() == half && t.sizeFull() == full;
    }

    @Property
    @FromData("dataFind1")
    boolean sizeLeaf_SizeInner(@ForAll Tree<Integer> t, @ForAll int e, @ForAll int half, @ForAll int full) {
        return t.size() - t.sizeLeaf() == t.sizeInner();
    }

    @Data
    Iterable<Tuple.Tuple2<Tree<Integer>, List<Integer>>> tree_Data_PostOrder() {
        return Table.of(
                Tuple.of(tree(List.list(8, 7, 9, 11, 2, 3)), List.list(3, 2, 7, 11, 9, 8)),
                Tuple.of(tree(List.list(5, 8, 3, 11, 9, 2)), List.list(2, 3, 9, 11, 8, 5)),
                Tuple.of(tree(List.list(9, 5, 15, 8, 2, 1)), List.list(1, 2, 8, 5, 15, 9)),
                Tuple.of(tree(List.list(2, 1, 4, 5, 3, 0)), List.list(0, 1, 3, 5, 4, 2)),
                Tuple.of(tree(List.list(3, 24, 44, 76, 17, 92)), List.list(17, 92, 76, 44, 24, 3))
        );
    }

    @Property
    @FromData("tree_Data_PostOrder")
    boolean postorder(@ForAll Tree<Integer> t, @ForAll List<Integer> l) {
        return t.postorder().isEqualTo(l);
    }

    @Data
    Iterable<Tuple.Tuple2<Tree<Integer>, List<Integer>>> tree_Data_PreOrder() {
        return Table.of(
                Tuple.of(tree(List.list(8, 7, 9, 11, 2, 3)), List.list(8, 7, 2, 3, 9, 11)),
                Tuple.of(tree(List.list(5, 8, 3, 11, 9, 2)), List.list(5, 3, 2, 8, 11, 9)),
                Tuple.of(tree(List.list(9, 5, 15, 8, 2, 1)), List.list(9, 5, 2, 1, 8, 15)),
                Tuple.of(tree(List.list(2, 1, 4, 5, 3, 0)), List.list(2, 1, 0, 4, 3, 5)),
                Tuple.of(tree(List.list(3, 24, 44, 76, 17, 92)), List.list(3, 24, 17, 44, 76, 92))
        );
    }

    @Property
    @FromData("tree_Data_PreOrder")
    boolean preOrder(@ForAll Tree<Integer> t, @ForAll List<Integer> l) {
        return t.preorder().isEqualTo(l);
    }

    @Data
    Iterable<Tuple.Tuple2<Tree<Integer>, List<Integer>>> tree_Data_Inorder() {
        return Table.of(
                Tuple.of(tree(List.list(8, 7, 9, 11, 2, 3)), List.list(2, 3, 7, 8, 9, 11)),
                Tuple.of(tree(List.list(5, 8, 3, 11, 9, 2)), List.list(2, 3, 5, 8, 9, 11)),
                Tuple.of(tree(List.list(9, 5, 15, 8, 2, 1)), List.list(1, 2, 5, 8, 9, 15)),
                Tuple.of(tree(List.list(2, 1, 4, 5, 3, 0)), List.list(0, 1, 2, 3, 4, 5)),
                Tuple.of(tree(List.list(3, 24, 44, 76, 17, 92)), List.list(3, 17, 24, 44, 76, 92))
        );
    }

    @Property
    @FromData("tree_Data_Inorder")
    boolean Inorder(@ForAll Tree<Integer> t, @ForAll List<Integer> l) {
        return t.inorder().isEqualTo(l);
    }



    @Data
    Iterable<Tuple.Tuple3<List<Integer>, Integer, Integer>> data_for_Min_Max() {
        return Table.of(
                Tuple.of((List.list(4, 2, 1, 3, 6, 5, 7)), 1, 7), Tuple.of((List.list(4, 2, 1, 3, 6, 5, 10)), 1, 10),
                Tuple.of((List.list(4, 2, 1, 3, 6, 5, 15)), 1, 15), Tuple.of((List.list(17, 2, 1, 3, 6, 5, 7)), 1, 17),
                Tuple.of((List.list(4, 2, 1, 20, 6, 5, 7)), 1, 20)
        );
    }

    @Property
    @FromData("data_for_Min_Max")
    boolean Maximum_Minimum(@ForAll List<Integer> t, @ForAll int min, @ForAll int max) {
        return maximum(t) == max && minimum(t) == min;
    }



}