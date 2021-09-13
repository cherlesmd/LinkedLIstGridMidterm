package lo6;

public class LinkedGridNode<E> {
	private E item;
	protected LinkedGridNode<E> nextCol;  
	protected LinkedGridNode<E> nextRow; 
//	protected LinkedGridNode<E> prevCol; 
//	protected LinkedGridNode<E> prevRow; 
	
	public LinkedGridNode() {
		
	}
	
	public LinkedGridNode(E item) {
		this.setItem(item);
	}
	

	public E getItem() {
		return item;
	}

	public void setItem(E item) {
		this.item = item;
	}
	
}