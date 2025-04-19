import java.util.LinkedList;

public class ExpressionTree implements ExpressionTreeInterface {

      private String postfixExpression;
      private ExpressionNode myExpressionTree;
      private boolean expressionSeemsInvalid;

      public ExpressionTree(String postfixExpression) {
            this.postfixExpression = postfixExpression;
            this.myExpressionTree = null;
            this.expressionSeemsInvalid = false;
            createExpressionTree();
      }

      private void createExpressionTree() throws RuntimeException {
            /* Decompose expression into its component operands and operators
               by splitting postfixExpression string on spaces */
            String[] components = this.postfixExpression.split("\\s+");
            /* Using LinkedList as stack data structure per README */
            LinkedList<ExpressionNode> expressionStack = new LinkedList<>();
            /* Count operands and operators to ensure postfixExpression is valid:
               add 1 for each operand, and subtract net 1 for each operator
               (subtract 2 due to evaluation of left and right nodes in statement
               and add 1 due to resulting answer).  If final count == 1, and the 
               count never < 0 during this loop, then the postfixExpression is 
               valid.  Credit to Stackoverflow for this understanding. */
            int postfixExpressionCounter = 0;
            for (String component : components) {
              /* Check whether component is operator or operand */
              boolean componentIsOperand = component.matches("\\d+");
              if (componentIsOperand) {
                ExpressionNode node = new ExpressionNode(Integer.valueOf(component));
                /* Push operands on to top of stack */
                expressionStack.push(node);
                /* Count operands as per comment starting on line 22 above. */
                postfixExpressionCounter += 1;
              } 
                else {
                /* Extract the operator */
                char operator = component.charAt(0);
                /* Count operators as per comment starting on line 22 above and  
                   confirm that postfixExpression thus far is valid */
                postfixExpressionCounter -= 1;
                if (postfixExpressionCounter <= 0) {
                    this.expressionSeemsInvalid = true;
                    break;
                } else if (operator != '+' && operator != '-' && operator != '/' && operator != '*') {
                    this.expressionSeemsInvalid = true;
                    break;
                }
                /* Configure operator as statement (e.g., Left Operator Right == Value)
                   before pushing on to top of stack */
                ExpressionNode right = expressionStack.pop();
                ExpressionNode left = expressionStack.pop();
                ExpressionNode node = new ExpressionNode(operator, left, right);
                expressionStack.push(node);
                  }
                }
        /* Confirm postfixExpression is valid - if not, stop here */
        if (postfixExpressionCounter != 1 || this.expressionSeemsInvalid) {
              /* Not completely sure how to handle invalid postfixExpression so will throw RuntimeException */
              throw new RuntimeException("\"" + this.postfixExpression + "\"" + " does not seem to be a valid postfix expression (per the HW3 README).");
        } 
        else {
        /* The top of the stack (which should be the only item in the stack) is 
           the resulting express tree */
          myExpressionTree = expressionStack.pop();
            }
      }

      public int eval() {
            return eval(myExpressionTree);
      }

      private int eval(ExpressionNode node) throws RuntimeException {
            if (node.nodeIsOperand) {
              return node.operand;
            } else {
              if (node.operator == '+') {
                return eval(node.left) + eval(node.right);
              }
              if (node.operator == '-') {
                return eval(node.left) - eval(node.right);
              }
              if (node.operator == '*') {
                return eval(node.left) * eval(node.right);
              }
              if (node.operator == '/') {
                return eval(node.left) / eval(node.right);
              }
              /* Invalid operators should be caught in createExpressionTree, but if 
                 for whatever reason one sneaks through, this should catch it. Not 
                 completely sure how to handle invalid operators so will throw RuntimeException */
              throw new RuntimeException("\"" + node + "\"" + "is an invalid operator (per the HW3 README).");
            }
      }

      public String postfix() {
            StringBuilder sb = new StringBuilder();
            postfix(myExpressionTree, sb);
            return sb.toString().trim();
      }

      private void postfix(ExpressionNode node, StringBuilder sb) {
            if (node.left != null) {
              postfix(node.left, sb);
            }
            if (node.right != null) {
              postfix(node.right, sb);
            }
            sb.append(node);
            sb.append(" ");
      }

      public String prefix() {
            StringBuilder sb = new StringBuilder();
            prefix(myExpressionTree, sb);
            return sb.toString().trim();
      }

      private void prefix(ExpressionNode node, StringBuilder sb) {
            sb.append(node);
            sb.append(" ");
            if (node.left != null) {
              prefix(node.left, sb);
            }
            if (node.right != null) {
              prefix(node.right, sb);
            }
      }

      public String infix() {
            StringBuilder sb = new StringBuilder();
            infix(myExpressionTree, sb);
            return sb.toString().trim();
      }

      private void infix(ExpressionNode node, StringBuilder sb) {
            if (!node.nodeIsOperand) {
              sb.append("( ");
            }
            if (node.left != null) {
              infix(node.left, sb);
            }
            sb.append(node);
            sb.append(" ");
            if (node.right != null) {
              infix(node.right, sb);
            }
            if (!node.nodeIsOperand) {
              sb.append(") ");
            }
      }

      public class ExpressionNode {

            public int operand;
            public char operator;
            public boolean nodeIsOperand;
            public ExpressionNode left;
            public ExpressionNode right;

            /* Constructor for operand node; only has one parameter, the operand */
            public ExpressionNode(int number) {
                  this.operand = number;
                  this.nodeIsOperand = true;
            }

            /* Constructor for the operator node; has three parameters, the operator and 
               the operands with which it forms a statement */
            public ExpressionNode(char operator, ExpressionNode left, ExpressionNode right) {
                  this.operator = operator;
                  this.left = left;
                  this.right = right;
                  this.nodeIsOperand = false;
            }

            /* Overwrite toString method to ensure proper data representation */
            public String toString() {
                 if (this.nodeIsOperand) {
                     return Integer.toString(this.operand);
                 } else {
                     return Character.toString(this.operator);
                 }
            }
        }
    }