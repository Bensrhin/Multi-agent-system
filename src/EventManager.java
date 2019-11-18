import java.util.*;


public class EventManager{

  public long currentDate;
  public PriorityQueue<Event> events = new PriorityQueue<Event>();
  public PriorityQueue<Event> events_copy = new PriorityQueue<Event>();//pour le restart


  public EventManager(){
    this.currentDate = 1;
  }

  public void addEvent(Event e){
    events.add(e); events_copy.add(e);
  }

  public void next(){
    currentDate++;
    // System.out.println(currentDate);

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
    events = events_copy;
  }

}
