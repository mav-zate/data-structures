package trees;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AvlTreeTest {
    AvlTree<Integer> avlTree;

    @Nested
    @DisplayName("::delete")
    class DeleteTest {
        @BeforeEach
        void init() {
            avlTree = new AvlTree<>(5);
        }

        @Test
        @DisplayName("throws npe if key null")
        void testDeleteExceptionThrow() {
            Assertions.assertThrows(NullPointerException.class, () -> avlTree.insert(null));
        }

        @Test
        @DisplayName("does not delete from empty tree or if key not in tree")
        void testDeleteNoNodes() {
            boolean deletion1 = avlTree.delete(6);
            boolean deletion2 = avlTree.delete(5);
            boolean deletion3 = avlTree.delete(ThreadLocalRandom.current().nextInt(0, 10));

            Assertions.assertAll(
                () -> Assertions.assertEquals(false, deletion1),
                () -> Assertions.assertEquals(true, deletion2),
                () -> Assertions.assertEquals(false, deletion3)
            );
        }

        @Test
        @DisplayName("balances left side deletions")
        void testDeleteLeftBalancing() {
            List<Integer> expectedOrder = Lists.newArrayList(3, 4, 5);
            avlTree.insert(6);
            avlTree.insert(3);
            avlTree.insert(4);

            avlTree.delete(6);

            Assertions.assertAll(
                () -> Assertions.assertEquals(expectedOrder, avlTree.depthFirstInOrder()),
                () -> Assertions.assertEquals(1, avlTree.height())
            );
        }

        @Test
        @DisplayName("balances right side deletions")
        void testDeleteRightBalancing() {
            List<Integer> expectedOrder = Lists.newArrayList(5, 6, 7);
            avlTree.insert(4);
            avlTree.insert(7);
            avlTree.insert(6);

            avlTree.delete(4);

            Assertions.assertAll(
                () -> Assertions.assertEquals(expectedOrder, avlTree.depthFirstInOrder()),
                () -> Assertions.assertEquals(1, avlTree.height())
            );
        }
    }

    @Nested
    @DisplayName("::insert")
    class InsertTest {
        @BeforeEach
        void init() {
            avlTree = new AvlTree<>(5);
        }

        @Test
        @DisplayName("balances left-left")
        void testInsertLeftLeft() {
            List<Integer> expectedOrder = Lists.newArrayList(3, 4, 5);
            avlTree.insert(4);
            avlTree.insert(3);

            Assertions.assertAll(
                () -> Assertions.assertEquals(expectedOrder, avlTree.depthFirstInOrder()),
                () -> Assertions.assertEquals(1, avlTree.height())
            );
        }

        @Test
        @DisplayName("balances right-left")
        void testInsertRightLeft() {
            List<Integer> expectedOrder = Lists.newArrayList(3, 4, 5);
            avlTree.insert(3);
            avlTree.insert(4);

            Assertions.assertAll(
                () -> Assertions.assertEquals(expectedOrder, avlTree.depthFirstInOrder()),
                () -> Assertions.assertEquals(1, avlTree.height())
            );
        }

        @Test
        @DisplayName("balances right-right")
        void testInsertRightRight() {
            List<Integer> expectedOrder = Lists.newArrayList(5, 6, 7);
            avlTree.insert(6);
            avlTree.insert(7);

            Assertions.assertAll(
                () -> Assertions.assertEquals(expectedOrder, avlTree.depthFirstInOrder()),
                () -> Assertions.assertEquals(1, avlTree.height())
            );
        }

        @Test
        @DisplayName("balances left-right")
        void testInsertLeftRight() {
            List<Integer> expectedOrder = Lists.newArrayList(5, 6, 7);
            avlTree.insert(7);
            avlTree.insert(6);

            Assertions.assertAll(
                () -> Assertions.assertEquals(expectedOrder, avlTree.depthFirstInOrder()),
                () -> Assertions.assertEquals(1, avlTree.height())
            );
        }
    }

    @Nested
    @DisplayName("::contains")
    class ContainsTest {
        @BeforeEach
        void init() {
            avlTree = new AvlTree<>(5);
        }

        @Test
        @DisplayName("throws NullPointerException if key is null")
        void testContainsThrowsNpe() {
            Assertions.assertThrows(NullPointerException.class, () -> avlTree.contains(null));
        }

        @Test
        @DisplayName("return false if tree empty or if key not in tree")
        void testContainsNoNodes() {
            boolean existenceCheck1 = avlTree.contains(6);
            boolean existenceCheck2 = avlTree.contains(5);
            avlTree.delete(5);
            boolean existenceCheck3 = avlTree.contains(5);

            Assertions.assertAll(
                () -> Assertions.assertEquals(false, existenceCheck1),
                () -> Assertions.assertEquals(true, existenceCheck2),
                () -> Assertions.assertEquals(false, existenceCheck3)
            );
        }
    }
}