package lo6;

public class LinkedGrid<E> {
	
	private int rows;  
	private int cols; 
	private LinkedGridNode<E> head;
	private LinkedGridNode<E> horizontalTail; 
	private LinkedGridNode<E> verticalTail;
	
	

	public LinkedGrid() {
		this.rows = 0;
		this.cols = 0;
		this.head = null; 
		this.horizontalTail = null; 
		this.verticalTail = null;
	}
	
	
	public LinkedGrid(int rows, int cols) { 
		if(rows == cols) {
			this.rows = rows - cols  ; 
			this.cols = cols - rows;
			for(int c = 0; c < rows; c++) {
				this.addFirstRow();
			}
			for( int r = 0; r < cols -1 ; r++) {
				this.addFirstCol(); 
			}
		}
		else {
			this.rows = rows - rows; 
			this.cols = cols - cols; 
			for(int c = 0; c < rows; c++) {
				this.addLastRow(); 
			}
			for( int r = 0; r < cols - 1; r++) {
				this.addLastCol();
			}
		
			
		}
	 
	}
	
	
	public LinkedGrid(E[][] grid) {
		buildGrid(grid, 0, 0);
	}
	
	// Working
  private LinkedGridNode<E> buildGrid(E[][] grid, int i, int j) {  
	  rows = grid.length;
	  cols = grid[0].length;    
	  // return if i or j is out of bounds
	  if (i > grid.length - 1 || j > grid[0].length - 1)
		  return null;

	  // create a new node for current i and j
	  LinkedGridNode<E> current = new LinkedGridNode<>();
	  current.setItem(grid[i][j]);
	  if(i == 0 && j == 0) {
		  this.head = current;
	  }
	  else if(i == 0) {
		  horizontalTail = current;
	  }
	  else if(i == grid.length-1 && j == 0) {
		  verticalTail = current;
	  }
    
	  current.nextCol = buildGrid(grid, i, j + 1);
	  current.nextRow = buildGrid(grid, i + 1, j);
	  	return current;  
  }

	
	public void addFirstCol() {
		LinkedGridNode<E> newColNode = new LinkedGridNode<>();  
		if(null == this.head) {
			this.head = newColNode; 
			this.verticalTail = newColNode; 
			this.horizontalTail = newColNode;
			this.rows++; 

		}
		else if (this.rows == 1){
			newColNode.nextCol = this.head; 
			this.head = newColNode; 
			verticalTail = newColNode;
		}
		else {
			LinkedGridNode<E> newColHead = new LinkedGridNode<>();
			
			newColNode = newColHead;
			for(int i = 1; i < this.rows; i++) {
				newColNode.nextRow = new LinkedGridNode<E>();
				newColNode = newColNode.nextRow;
			}
		   
			LinkedGridNode<E> keepLeftNode = new LinkedGridNode<>();

			for(int j = 0; j < this.rows; j++) {
            if(j == 0) {
               newColHead.nextCol = this.head;
               this.head = newColHead;
               keepLeftNode = newColHead.nextCol;
               keepLeftNode = keepLeftNode.nextRow;
               newColHead = newColHead.nextRow;
            }	
            else{
               newColHead.nextCol = keepLeftNode;
               if(newColHead.nextCol == verticalTail){
                  verticalTail = newColHead;
               }
               newColHead = newColHead.nextRow;
               keepLeftNode = keepLeftNode.nextRow; 
            }   
			}
		}
		this.cols++; 
	}
	
	public void addFirstRow() {
		LinkedGridNode <E> newRowNode = new LinkedGridNode<>(); 
		if(null == this.head) {
			this.head = newRowNode; 
			this.horizontalTail = newRowNode; 
			this.verticalTail = newRowNode; 
			this.cols++; 
			
		}
		else if(this.cols == 1) {
			newRowNode.nextRow = this.head; 
			this.head = newRowNode; 
		}
		else {
			LinkedGridNode<E> newRowHead = new LinkedGridNode<>();
			
			newRowNode = newRowHead;
			
			for(int i = 1; i < this.cols; i++) {
				newRowNode.nextCol = new LinkedGridNode<E>();
				newRowNode = newRowNode.nextCol;
			}
			
			LinkedGridNode<E> keepBottomNode = new LinkedGridNode<>();

			for(int j = 0; j < this.cols; j++) {
	            if(j == 0) {
	            	newRowHead.nextRow = this.head;
	            	this.head = newRowHead;
	            	keepBottomNode = newRowHead.nextRow;
	            	keepBottomNode = keepBottomNode.nextCol;
	            	newRowHead = newRowHead.nextCol;
	            }	
	            else{
	            	newRowHead.nextRow = keepBottomNode;
	            	if(newRowHead.nextRow == horizontalTail){
	            		horizontalTail = newRowHead;
	            	}
	            	newRowHead = newRowHead.nextCol;
	            	keepBottomNode = keepBottomNode.nextCol;
	            }
			}
		}
		this.rows++;
 
	}
	
	public void addLastCol() {
		LinkedGridNode<E> newColNode = new LinkedGridNode<>(); 
		
		if(null == this.head) {
			this.head = newColNode; 
			this.verticalTail = newColNode; 
			this.horizontalTail = newColNode; 
			this.rows++; 
		}
		else if(this.rows == 1) {
			this.horizontalTail.nextCol = newColNode; 
			this.horizontalTail = newColNode; 
		}
		else {
			LinkedGridNode<E> lastColHead = new LinkedGridNode<>();
			
			newColNode = lastColHead;
			
			
			for(int i = 1; i < this.rows; i++) {
				newColNode.nextRow = new LinkedGridNode<E>();
				newColNode = newColNode.nextRow;
			}
		   
			LinkedGridNode<E> keepLeftNode = new LinkedGridNode<>();

			for(int j = 0; j < this.rows; j++) {
	            if(j == 0) {
	            	horizontalTail.nextCol = lastColHead;
	            	keepLeftNode = horizontalTail;
	            	horizontalTail = lastColHead;
	            	keepLeftNode = keepLeftNode.nextRow;
	            	lastColHead = lastColHead.nextRow;
	            }	
	            else{
	            	keepLeftNode.nextCol = lastColHead;
	            	keepLeftNode = keepLeftNode.nextRow;
	            	lastColHead = lastColHead.nextRow;
	            }
            
			}
		}
		this.cols++; 
	}
	
	
	public void addLastRow() { 
		LinkedGridNode<E> newRowNode = new LinkedGridNode<>();  
		if(null == this.head) {
			this.head = newRowNode; 
			this.horizontalTail = newRowNode; 
			this.verticalTail = newRowNode;
			this.cols++; 
			 
		}
		else if (this.cols == 1) {
			this.verticalTail.nextRow = newRowNode; 
			this.verticalTail = newRowNode; 
		}
		else {
			LinkedGridNode<E> lastRowHead = new LinkedGridNode<>();
			
			newRowNode = lastRowHead;
			
			
			for(int i = 1; i < this.rows; i++) {
				newRowNode.nextCol = new LinkedGridNode<E>();
				newRowNode = newRowNode.nextCol;
			}
		   
			LinkedGridNode<E> keepTopNode = new LinkedGridNode<>();

			for(int j = 0; j < this.cols; j++) {
	            if(j == 0) {
	            	verticalTail.nextRow = lastRowHead;
	            	keepTopNode = verticalTail;
	            	verticalTail = lastRowHead;
	            	keepTopNode = keepTopNode.nextCol;
	            	lastRowHead = lastRowHead.nextCol;
	            }	
	            else{
	            	keepTopNode.nextRow = lastRowHead;
	            	keepTopNode = keepTopNode.nextCol;
	            	lastRowHead = lastRowHead.nextCol;
	            }
            
			}
		}
		this.rows++; 
	}
	
	public void insertCol(int index) {
		LinkedGridNode<E> newColNode  = new LinkedGridNode<>(); 
		LinkedGridNode<E> at = new LinkedGridNode<>(); 
		if(index < 0 || this.rows < index) {
			throw new IndexOutOfBoundsException("Out of bound try again");
		}
		else if(index == 0) {
			this.addFirstCol(); 
		}
		else if(index == cols) {
			this.addLastCol();
		}
		else {
			for(int i = 0; i < this.cols; i++) {
				at = this.head; 
				int count = 0; 
				while(count < index - 1) {
					at = at.nextCol; 
					count++;
			}
			}
			newColNode.nextCol = at.nextCol;
			at.nextCol = newColNode;
			this.cols++;
		}
	}
	
	public void insertRow(int index) {
		LinkedGridNode<E> newRowNode  = new LinkedGridNode<>(); 
		LinkedGridNode<E> at = new LinkedGridNode<>(); 
		if(index < 0 || this.cols < index) {
			throw new IndexOutOfBoundsException("Out of bound try again");
		}
		else if(index == 0 ) {
			this.addFirstRow();
		}
		else if(index == rows) {
			this.addLastRow();
		}
		else {
			at = this.head; 
			int count = 0; 
			while(count < index - 1) {
				at = at.nextRow; 
				count++;
			}
			newRowNode.nextRow = at.nextRow;
			at.nextRow = newRowNode;
			this.rows++;
		}
	}
	
	
	public void deleteFirstCol() { 
		if(null == this.head) {
			System.out.println("There is no column to remove"); 
		}
		else if(this.cols == 1) {
			this.head = null; 
			this.verticalTail = null; 
			this.rows = 0; 
		}
		else {
			this.head = head.nextCol;
		}
		this.cols--; 
	}
	public void deleteLastCol() {
		LinkedGridNode<E> at = new LinkedGridNode<>(); 
		LinkedGridNode<E> behind = new LinkedGridNode<>(); 
		if(null == this.head) {
			System.out.println("There is no column to delete"); 
		}
		else if(this.cols == 0) {
			this.head = null; 
			this.verticalTail = null; 
		}
		else {
			at = this.head; 
			behind = this.head; 
			
			while(at != this.horizontalTail) {
				behind = at;
				at = at.nextCol;
			}
			 
			behind.nextCol = null;
			this.horizontalTail = behind;
		}
	
		this.cols--;
	}
	
	public void deleteFirstRow() { 
		if(null == this.head) {
			System.out.println("There is no row to delete"); 
		}
		else if(this.rows == 1) { 
			this.head.nextRow = this.head; 
			this.head = null; 
			this.cols = 0;
		}
		else {
			this.head = head.nextRow; 
		}
		
		this.rows--; 
	}
	
	
	public void deleteLastRow() { 
		LinkedGridNode<E> at = new LinkedGridNode<>(); 
		LinkedGridNode<E> behind = new LinkedGridNode<>(); 
		if(null == this.head) {
			System.out.println("There is no row to delete"); 
		}
		else if(this.rows == 0) {
			this.head = null; 
			this.horizontalTail = null; 
		}
		else {
			at = this.head; 
			behind = this.head; 
			
			while(at != this.verticalTail) {
				behind = at;
				at = at.nextRow;
			}
			 
			behind.nextRow = null;
			this.verticalTail = behind;
		}
	
		this.rows--;
	}
	
	
	public void deleteCol(int index) {
		LinkedGridNode<E> previous = new LinkedGridNode<>(); 
		if(index < 0 || this.cols < index) {
			throw new IndexOutOfBoundsException();
		}
		else if(null == this.head) {
			System.out.println("there is no column to delete"); 
		}
		else if(index == 0) {
			this.deleteFirstCol();
		}
		else if(index == this.cols) {
			this.deleteLastCol();
		}
		else {
			int count = 0;
			previous = this.head;
			
			while (count < index - 1) {
				previous = previous.nextCol;
				count++;
			}
			previous.nextCol = previous.nextCol.nextCol;
			this.cols--;
		}	
	}
	
	public void deleteRow(int index) { 
		if(index < 0 || this.rows < index) {
			throw new IndexOutOfBoundsException();
		}
		else if(null == this.head) {
			System.out.println("there is no row to delete"); 
		}
		else if (index == 0) {
			this.deleteFirstRow();
		}
		else if(index == this.rows) {
			this.deleteLastRow();
		}
		else {
			int count = 0;
			LinkedGridNode<E> previous = this.head;
			
			while (count < index - 1) {
				previous = previous.nextRow;
				count++;
			}
			
			previous.nextRow = previous.nextRow.nextRow;
		
			this.rows--;		
		}
		
	}
	
//done
	public void set(int row, int col, E item) {
		LinkedGridNode<E> start = new LinkedGridNode<>();
			start = this.head; 
			int rowMover = 0; 
			int colMover = 0; 
			
			while(colMover < col) {
				start = start.nextCol; 
				colMover++; 
			}
			
			while(rowMover < row) {
				start = start.nextRow; 
				rowMover++; 
			}
			start.setItem(item); 
	}

	
	public E get(int row, int col) {
		LinkedGridNode<E> start = new LinkedGridNode<>(); 
		if(row < 0 || col < 0 || row < this.rows|| col < this.cols) {
			throw new IndexOutOfBoundsException();
		}
		else {
			start = this.head; 
			int rowMover = 0; 
			int colMover = 0; 
			
			while(colMover < col) {
				start = start.nextCol; 
				colMover++; 
			}
			
			while(rowMover < row) {
				start = start.nextRow; 
				rowMover++; 
			}
			System.out.println(start.getItem()); 
			return start.getItem(); 
		}
	}
	
	public E getCol(int col) {
		LinkedGridNode<E> start = new LinkedGridNode<>(); 
		if(col < 0 || col > this.cols) {
			throw new IndexOutOfBoundsException();
		}
		else if(col == 0) {
			start = this.head;
			for(int i = 0; i < this.rows; i++) {
				start.nextRow = start; 
				System.out.println("cols = 1" + start.getItem());
			} 
		}
		else if(col == this.cols) {
			start = this.horizontalTail; 
			for(int i = 0; i < this.rows; i++) {
				start.nextRow = start; 
				System.out.println("cols = cols " + start.getItem());
			}
		}
		else {
			start = this.head; 
			for(int i = 0; i < col; i++) {
				start.nextCol = start; 
			}
			for(int r = 0; r < this.rows; r++) {
				start.nextRow = start; 
				System.out.println("col = choice " + start.getItem());
				
			}
		}
		return start.getItem();
	}
	
	public E getRow(int row) {
		LinkedGridNode<E> start = new LinkedGridNode<>(); 
		if(row < 0 || row  > this.rows ) {
			throw new IndexOutOfBoundsException();
		}
		else if(row == 0) {
			start = this.head;
			for(int i = 0; i < this.cols; i++) {
				start.nextCol = start; 
				System.out.println("row = 1 " + start.getItem());
			} 
		}
		else if(row == this.rows) {
			start = this.verticalTail; 
			for(int i = 0; i < this.cols; i++) {
				start.nextCol = start; 
				System.out.println("row = row " + start.getItem());
			}
		}
		else {
			start = this.head; 
			for(int i = 0 ; i < row; i++) {
				start.nextRow = start; 
			}
			for(int c = 0; c < this.cols; c++) {
				start.nextCol = start; 
				System.out.println("row = choice " + start.getItem());
			}
		}
		return start.getItem(); 
	}
	
	
	//done
	public int horizontalSize() { // returns column size
		System.out.println("cols: " + this.cols); 
		return this.cols; 
	}

	public int verticalSize() { // returns row size
		System.out.println("rows: " + this.rows);
		return this.rows; 
	}
	
	
//	private boolean isEmpty() { //checks if any are empty
//		return (this.head == null && this.verticalTail == null &&  this.horizontalTail == null &&
//				this.cols == 0 && this.rows ==  0);
//	}

	 public void displayGrid() {
	        if (null == head) {
	            System.out.println("[empty grid]");  
	            return;
	        }
	        
	        System.out.printf("[%d by %d grid]:\n ", rows, cols);
	        
	        LinkedGridNode<E> curr, nextRow;
	        int rowCount, colCount;
	        rowCount = 0;
	        nextRow = head;
	        do {
	            System.out.print("\t");
	            colCount = 0;
	            curr = nextRow;
	            do {
	                System.out.printf("%-7s", curr.getItem());
	                curr = curr.nextCol;
	            } 
	            while(curr != null && colCount++ < 10);
	            
	            nextRow = nextRow.nextRow;
	            System.out.println();
	            
	        } 
	        
	        while (nextRow != null && rowCount++ < 10);
	        
	    }
}