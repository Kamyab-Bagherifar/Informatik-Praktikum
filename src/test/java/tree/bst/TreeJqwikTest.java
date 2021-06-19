package tree.bst;

import list.List;
import net.jqwik.api.*;
import tree.bst.tree.bst.Tree;
import static tree.bst.tree.bst.Tree.*;

public class TreeJqwikTest {
  // Hier Data-Driven-Tests für alle Methoden der Klasse Tree programmieren.


    @Data
    Iterable<Tuple.Tuple3<Tree<Integer>, Tree<Integer>, Integer>> preStored1() {
        return Table.of(
                Tuple.of(tree(List.list(3,2,1)), tree(List.list(3, 2, 1, 4)), 4),
                Tuple.of(tree(List.list(4,2,6,5)), tree(List.list(4,2,6,5,3)), 3),
                Tuple.of(tree(List.list(4,2,7,5,9)), tree(List.list(4,2,7,5,9,6)), 6),
                Tuple.of(tree(List.list(2,1,5,7,4)), tree(List.list(2,1,5,7,4,3)), 3),
                Tuple.of(tree(List.list(1,7,3,9)), tree(List.list(1,7,3,9,0)), 0)
        );
    }
    @Property
    @FromData("preStored1")
    boolean insert_test (@ForAll Tree<Integer> tree1, @ForAll Tree<Integer> result, @ForAll Integer n) {
        return tree1.insert(n).inorder().isEqualTo(result.inorder())
                && tree1.insert(n).remove(n).inorder().isEqualTo(tree1.inorder());
    }

    @FromData("preStored1")
    boolean member_test (@ForAll Tree<Integer> tree1, @ForAll Tree<Integer> result, @ForAll Integer n) {
        return tree1.insert(n).inorder().isEqualTo(result.inorder())
                && tree1.insert(n).remove(n).inorder().isEqualTo(tree1.inorder());
    }








}
