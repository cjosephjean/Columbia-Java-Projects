import java.lang.Math;

public class BetterBST<T extends Comparable<? super T>> extends BinarySearchTree<T>{
    
    public BetterBST() {
		root = null;
	}

	public BetterBST(BinaryNode<T> node) {
		root = node;
	}
	
	private T findMax( BinaryNode<T> t )
    {
        if( t == null )
            return null;
        else if( t.right == null )
            return t.data;
        return findMax( t.right );
    }
    
    public int height() {
        int depth = -1;
        return height(root, depth);
    }
    
    private int height(BinaryNode<T> node, int depth) {
        depth += 1;
        if (node == null) {
            return depth;
        } 
        int leftDepth = height(node.left, depth);
        int rightDepth = height(node.right, depth);
        if (leftDepth < rightDepth) {
            return rightDepth;
        } else {
            return leftDepth;
        }
    }
    
    public T imbalance() {
        return imbalance(root);
    }
    
    private T imbalance(BinaryNode<T> node) {
        if (node == null) {
            return null;
        } else if (node.left == null && node.right == null) {
            return null;
        } else if (node.left == null || node.right == null) {
            return node.data;
        } else if (Math.abs(height(node.left, 0) - height(node.right, 0)) > 1) {
            return node.data;
        }
        return imbalance(node.left);
    }
    
    public boolean greaterThanTarget(T target, T comparator) {
        int comp = target.compareTo(comparator);
        if (comp > 0) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean targetGreaterThanOrEqualToCurrentNode(T target, T currentNode) {
        int comp = target.compareTo(currentNode);
        if (comp < 0) {
            return false;
        } else {
            return true;
        }
    }
    
    public T smallestGreaterThan(T t) {
        return smallestGreaterThan(t, root); 
    }
    
    private T smallestGreaterThan(T target, BinaryNode<T> node) {
        if (node == null) {
            //System.out.println("Target " + target + " but node is null... ");
            return null;
        } else if(targetGreaterThanOrEqualToCurrentNode(target, node.data)) {
            //System.out.println("Target " + target + " > " + "node " + node.data);
            return smallestGreaterThan(target, node.right);
        } else {
            //System.out.println("Target " + target + " < " + " node " + node.data);
            if (node.left == null) {
                //System.out.println("...and nothing smaller than node " + node.data + " so it is RETURNED.");
                return node.data;
            } else if(!targetGreaterThanOrEqualToCurrentNode(target, node.left.data)) {
                //System.out.println("Target " + target + " < " + " L-node " + node.left.data);
                return smallestGreaterThan(target, node.left);
            } else {
                //System.out.println("Target " + target + " > " + " L-node " + node.left.data);
                if (node.left.right == null) {
                    //System.out.println("...and nothing BETWEEN L-node " + node.left.data + " and node " + node.data + " so it is RETURNED.");
                    return node.data;
                } else if (targetGreaterThanOrEqualToCurrentNode(target, node.left.right.data)) {
                    /* If there is a node > target and < current node, go find it (or something < it which also > target) */
                    T maximum = findMax(node.left.right.right);
                    if (maximum != null && !targetGreaterThanOrEqualToCurrentNode(target, maximum)) {
                        return smallestGreaterThan(target, node.left.right);
                    } else {
                        return node.data;
                    }
                } else {
                    return smallestGreaterThan(target, node.left.right);
                }
            }
        }
    }
    
    public BinarySearchTree<T> mirror() {
        return mirror(root); 
    }
    
    private BinarySearchTree<T> mirror(BinaryNode<T> node) {
        if (node == null) {
            return new BetterBST<T>(node);
        } else {
            return new BetterBST<T>(mirrorChildren(node));
        }
    }
    
    private BinaryNode<T> mirrorChildren(BinaryNode<T> node) {
        if (node == null) {
            return null;
        } else if (node.left == null && node.right == null) {
            return new BinaryNode(node.data);
        } else if (node.left == null) {
            return new BinaryNode(node.data, mirrorChildren(node.right), null);
        } else if (node.right == null) {;
            return new BinaryNode(node.data, null, mirrorChildren(node.left));
        } else {
            return new BinaryNode(node.data, mirrorChildren(node.right), mirrorChildren(node.left));
        }
    }
    
    public void prettyPrint() {
        if (root == null) {
            System.out.println("There is nothing to print, let alone prettyPrint.");
        } else {
            int length = height();
            int baseWidth = ((int)Math.pow(2,length));
            int termWidth = String.valueOf(root.data).length();
            int width = baseWidth * termWidth;
            prettyPrint(root, buildTreeGridForPrinting(length, width, termWidth), length, width);
        }
    }
    
    private void prettyPrint(BinaryNode<T> node, String[][] grid, int length, int width) {
        for (int row = 0; row < length; row++){
			for (int col = 0; col < width; col++){
				System.out.print(grid[row][col]);
				if (col == width - 1) {
					System.out.print("\n");
				}
			}
		}
    }
    
    private String[][] buildTreeGridForPrinting(int length, int width, int termWidth) {
        String defaultValue = " ";
        String[][] arrayGrid = new String[length][width];
        for (int row = 0; row < length; row++) {
            for (int col = 0; col < width; col++) {
                arrayGrid[row][col] = defaultValue;
            }
        }
        int rootColPosition = ((int)((width/2)-termWidth));
        int rootLevel = 0;
        return populateGrid(arrayGrid, rootLevel, rootColPosition, length, root);
    }
    
    private String[][] populateGrid(String[][] emptyGrid, int row, int col, int depth, BinaryNode<T> node) {
        String[][] grid = emptyGrid;
        int termWidth = 0;
        String val = " ";
        if (node != null) {
            termWidth = String.valueOf(node.data).length();
            val = String.valueOf(node.data);
        }
        grid[row][col] = val;
        row += 1;
        if (node.left != null) {
            int newLeftCol = col-termWidth*(int)(Math.pow(2,depth-row-2));
            populateGrid(grid, row, newLeftCol, depth, node.left);
        }
        if (node.right != null) {
            int newRightCol = col+termWidth*(int)(Math.pow(2,depth-row-2));
            populateGrid(grid, row, newRightCol, depth, node.right);
        }
        return grid;
    }    
}
