import java.io.File;
import java.util.Scanner;

public class SymbolBalance implements SymbolBalanceInterface {
    
    private File file;
    private Scanner fileScanner;
    
    public static MismatchError returnMismatch(int line, String current, char popped)
    {
        return new MismatchError(line, current.charAt(0), popped);
    }
    public static EmptyStackError returnEmpty(int line)
    {
        return new EmptyStackError(line);
    }
    public static NonEmptyStackError returnNonEmpty(char top, int size)
    {
        return new NonEmptyStackError(top, size);
    }
    public static void main(String[] args) {
        if (args.length > 0) {
            String fileName = args[0];
            SymbolBalance s = new SymbolBalance();
            s.setFile(fileName);
            s.checkFile();
        }
    }
    public void setFile(String filename) {
	    try {
	        file = new File(filename);
	    }
	    catch (Exception e) {
	        System.out.println("Unable to obtain filename.");
	        e.printStackTrace();
	    }
	}
	public BalanceError checkFile() {
        char current = ' ';
        try{
            fileScanner = new Scanner(file);
        } catch (Exception e) {
            System.out.println("Unable to read file.");
	        e.printStackTrace();
            return null;
        }
            MyStack<Character> stackEntries = new MyStack<Character>();
            boolean isLineFound = false;
            int fileLine = 0;
            while(fileScanner.hasNext())
            {
                fileLine++;
                String item = fileScanner.next();
                char tokens[] = item.toCharArray();
                try{
                for(int i = 0; i < tokens.length; i++)
                    {
                        current = tokens[i];
                        switch(tokens[i])
                        {
                            case '{':
                            case '(':
                            case '[': stackEntries.push(tokens[i]);break;
                            case '/': if(i + 1 < tokens.length)
                            {
                                if(tokens[i + 1] == '*')
                                    stackEntries.push('*');
                            }
                                break;
                            case '*': if(i + 1 < tokens.length)
                            {
                                if(tokens[i+1] == '/')                                    
                                {
                                    if(stackEntries.peek() == '*')
                                        stackEntries.pop();
                                    else returnMismatch(fileLine,"*/",stackEntries.pop());
                                }
                            }
                                break;
                            case '\"': if(isLineFound)
                            {
                                if(stackEntries.peek() == '\"')
                                {
                                    stackEntries.pop();
                                    isLineFound = false;
                                }
                                else returnMismatch(fileLine,"\"",stackEntries.pop());
                            }
                            else
                            {
                                isLineFound = true;
                                stackEntries.push(tokens[i]);
                            }
                                break;
                            case '}': if(stackEntries.peek() == '{')
                                stackEntries.pop();
                            else returnMismatch(fileLine,"}",stackEntries.pop());
                                break;
                            case ')': if(stackEntries.peek() == '(')
                                stackEntries.pop();
                            else returnMismatch(fileLine,")",stackEntries.pop());
                                break;
                            case ']': if(stackEntries.peek() == '[')
                                stackEntries.pop();
                            else returnMismatch(fileLine,"]",stackEntries.pop());
                                break;
                        }
                    }
                }catch (Exception e) {
                    return returnEmpty(fileLine);   
                }
            }
            fileScanner.close();
            if (stackEntries.size() > 0) {
                return returnNonEmpty(stackEntries.pop(), stackEntries.size());
            }
            System.out.println("Balanced.");
            return null;
	}
}