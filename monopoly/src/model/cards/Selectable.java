package model.cards;

public class Selectable {
    public boolean isSelectable = false;
    
    public boolean selected;
    
    public int x, y, w, h;

    public void selectable(){
        this.isSelectable =true;
    }
    public void unSelectable(){
        this.isSelectable = false;
    }
    public void setArea(int ix, int iy, int iw, int ih) {
    	x = ix;
    	y = iy;
    	w = iw;
    	h = ih;
    }
    
    public boolean inArea(int ix, int iy) {
    	if (ix >= x && ix <= x + w && iy >= y && iy <= y + h)
    		return true;
    	else
    		return false;
    }
    public boolean isSelectable(){
        return this.isSelectable;
    }

    public boolean isSelected(){
        return this.selected;
    }

    public void setSelected(boolean b){
        this.selected = b;
    }
}
