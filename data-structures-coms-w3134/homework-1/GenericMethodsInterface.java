public interface GenericMethodsInterface {

	public <AnyType extends Comparable<AnyType>> int binarySearch(AnyType[] a, AnyType x);
	public <AnyType extends Comparable<AnyType>> int linearSearch(AnyType[] a, AnyType x);
    
}
