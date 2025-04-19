public class BigO implements BigOInterface{
  
    public void cubic(int n){
        int l = 0;
        for(int i = 0; i < n; i++ )
            for(int j = 0; j < n; j++ )
                for(int k = 0; k < n; k++ )
                l++;
    }
  
    public void exp(int n){
        if(n <= 1)
        return ;
        else exp(n - 2);
    }
  
    public void constant(int n){
    System.out.print(n);  
    }
    //To overcome a compiler error which asks for a main method to be defined for an unknown reason:
    public static void main(String[] args) {        
    }
}