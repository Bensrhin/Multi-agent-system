import java.util.*;


public class EventManager{

  public long currentDate;
  public PriorityQueue<Event> events = new PriorityQueue<Event>();


  public EventManager(){
    this.currentDate = 1;
  }

  public void addEvent(Event e){
    events.add(e);
  }

  public void next(){
    currentDate++;
    Event e = events.peek();
    while (e!=null && e.getDate()<currentDate){
      e.execute();
      events.remove(e);
      e = events.peek();
    }

  }

  public boolean isFinished(){
    return (events.peek()==null);
  }

  public void restart(){
    events = new PriorityQueue<Event>();
  }

}
