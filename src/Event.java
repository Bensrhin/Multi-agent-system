
public abstract class Event implements Comparable<Event>{

  private long date;

  public Event(long date){
    this.date = date;
  }

  public long getDate(){
    return this.date;
  }

  public abstract void execute();

  @Override
  public int compareTo(Event e2){
    if (this.date > e2.date){
      return 1;
    }
    if (this.date < e2.date){
      return -1;
    }
    return 0;
  }


  @Override
  	public boolean equals(Object other) {
  		if(other instanceof Event) {
  			Event e2 = (Event) other;
  			return e2.date == this.date;
  		}
  		return false;
  	}

}
