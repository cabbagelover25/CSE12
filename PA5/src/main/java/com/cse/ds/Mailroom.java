package com.cse.ds;

import org.mockito.internal.matchers.Null;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Mailroom {

  private HashMap<Deliverable,MyQueue<Deliverable>> deliveryBins;
  private int currentTimestamp;

  /** Creates new Mailroom object that has deliveryBins hashMap and
   * sets timestamp equal to 0.
   * */
  public Mailroom() {
    this.deliveryBins =  new HashMap<Deliverable,MyQueue<Deliverable>>();
    currentTimestamp = 0;
  }

  /** Adds the deliverable d to the HashMap along with the MyQueue it has just
   * been enqued to by either adding it to the end of one if a zipcode exists or
   * creating a new one.
   * */
  public void registerItem(Deliverable d) throws NullPointerException  {
      // Check if d is null and append timeStamp to d
    if(d == null){
        throw new NullPointerException();
    }
    d.setTimestamp(currentTimestamp);

    /** If there does not yet exist a MyQueue with that zipcode, make a new one
     * and add the current d. If one does exist, get it, and add the current
     * d. In either case, readd the current d to the Hashmap with it's now
     * associated MyQueue.
     * */
    if(deliveryBins.get(d) == null){
        MyQueue<Deliverable> currentQue = new MyQueue<Deliverable>();
        currentQue.enqueue(d);
        deliveryBins.put(d, currentQue);
    }
    else {
        MyQueue <Deliverable> currentQue = deliveryBins.get(d);
        currentQue.enqueue(d);
        deliveryBins.put(d,currentQue);
    }
    // Update timestamp by one.
      currentTimestamp += 1;
  }

  /**The Deliverable with the lowest timestamp is returned, to do this each
   * MyQueue has to be checked for it's first value, since that will be the
   * lowest in the MyQueue, and then the lowest from all the lists is returned.
   * @return: Earliest deliverable
   * */
  public Deliverable deliverEarliest() {
    int lowest = 500000;
    Deliverable lowestKey = null;
    for (Deliverable key : deliveryBins.keySet()) {
      int check = deliveryBins.get(key).peek().getTimestamp(); //here
      //Check whether the new timestamp is smaller
      if (check < lowest) {
        lowest = check;
        lowestKey = key;
      }
    }
    //If there was are no Deliverables then just return null.
    if (lowestKey == null) {
      return null;
    }

   Deliverable returnItem = deliveryBins.get(lowestKey).dequeue();
    // If the returned Deliverable Queue is now empty, delete it.
    if(deliveryBins.get(lowestKey).isEmpty()){
      deliveryBins.remove(lowestKey);
    }
    return returnItem;
  }

  /**The Deliverable with the lowest timestamp for the entered zipcode is
   *  returned, if that zipCode has no deliverables null is returned. Also if
   *  the Deliverable was the only thing left in the list, delete it.
   * @param: String of zip to find lowest timestamp for.
   * @return: Earliest deliverable with paramater zip
   * */
  public Deliverable deliverEarliest(String zip) {
    Deliverable thisOne = null;
    for (Deliverable key : deliveryBins.keySet()) {
      if(key.getZipCode().equals(zip)){
        thisOne = key;
      }
    }
    if(thisOne == null){
      return null;
    }
    //Grab the first item in the zip list as it will always be earliest.
    Deliverable returnItem = deliveryBins.get(thisOne).dequeue(); //here
      if(deliveryBins.get(thisOne).isEmpty()){
        deliveryBins.remove(thisOne);
      }
      return returnItem;

  }

  /**Check earliest returns the Deliverable with the lowest timestamp just like
   * deliverEarliest, the difference however is that it does not removes it
   * from MyQueue using dequeue, only checks on it using peek.
   * @return: Earliest deliverable
   * */
  public Deliverable checkEarliest() {
    int lowest = 5000000;
    Deliverable lowestKey = null;
    for (Deliverable key : deliveryBins.keySet()) {
      int check = deliveryBins.get(key).peek().getTimestamp();
      //Check whether the new timestamp is smaller
      if (check < lowest) {
        lowest = check;
        lowestKey = key;
      }
    }
    //If there was are no Deliverable then just return null.
    if (lowestKey == null) {
      return null;
    }
    Deliverable returnItem = deliveryBins.get(lowestKey).peek();
    // If the returned Deliverable Queue is now empty, delete it.
    return returnItem;
  }

  /**Check earliest returns the Deliverable with the lowest timestamp in the
   * particular inputted zip, just like deliverEarliest, the difference
   * however is that it does not removes it from MyQueue using
   * dequeue, only checks on it using peek.
   * @param: String of zip to find lowest timestamp for.
   * @return: Earliest deliverable
   * */
  public Deliverable checkEarliest(String zip) {
    Deliverable thisOne = null;
    for (Deliverable key : deliveryBins.keySet()) {
      if (key.getZipCode().equals(zip)) {
        thisOne = key;
      }
    }
    if (thisOne == null) {
      return null;
    }
    //Grab the first item in the zip list as it will always be earliest.
    Deliverable returnItem = deliveryBins.get(thisOne).peek(); //here
    if (deliveryBins.get(thisOne).isEmpty()) {
      deliveryBins.remove(thisOne);
    }
    return returnItem;
  }



  /** Return an ArrayList of all the deliverables in the deliveryBins, and make
   * sure not to change the order they were inputted into their MyQueue's.
   * @return: ArrayList<Deliverable> of all deliverables in deliveryBins.
   * */
  public ArrayList<Deliverable> deliverAll() {
    //Create new arrayList
    ArrayList<Deliverable> completeList = new ArrayList<>();
    // Loop through entire keySet
    while(!(deliveryBins.isEmpty())){
      completeList.add(deliverEarliest());
    }
	  return completeList;
  }

  /** Return an ArrayList of all the deliverables with the inputted zip in the
   * deliveryBins, and make sure not to change the order they were inputted
   * into their MyQueue's.
   * @param: Strng of zip matching MyQueue to get items from.
   * @return: ArrayList<Deliverable> of all deliverables in deliveryBins.
   * */
  public ArrayList<Deliverable> deliverAll(String zip){
    //Create new arrayList
    ArrayList<Deliverable> completeList = new ArrayList<>();
    // Loop through entire keySet
    while(checkEarliest(zip) != null){
      completeList.add(deliverEarliest(zip));
    }
    return completeList;
  }


  /** Removes the Deliverable object with the earlist timestamp and adds it to
   * an ArrayLis. Continues to do so as long as the weight of the ArrayList
   * doesn't go over the capacity or their are no more Deliverables.
   * @param: int capacity to not go over
   * @return: ArrayList of Deliverable objects
   * */
  public ArrayList<Deliverable> deliverByWeight(int capacity) {
    int currentWeight = 0;
    ArrayList<Deliverable> weightedList = new ArrayList<>();
    while(checkEarliest() != null) {
      if ((checkEarliest().getWeight() + currentWeight) <= capacity) {
        currentWeight += (checkEarliest().getWeight());
        weightedList.add(deliverEarliest());
      } else {
        return weightedList;
      }
    }
    return weightedList;
  }


  /** Removes the Deliverable object with the earlist timestamp of the specified
   * zip and adds it to an ArrayLis. Continues to do so as long as the weight of
   * the ArrayList doesn't go over the capacity or their are no more
   * Deliverables.
   * @param: int capacity to not go over
   * @return: ArrayList of Deliverable objects
   * */
  public ArrayList<Deliverable> deliverByWeight(int capacity, String zip) {
    int currentWeight = 0;
    ArrayList<Deliverable> weightedList = new ArrayList<>();
    while(checkEarliest(zip) != null){
      if((checkEarliest(zip).getWeight() + currentWeight) <= capacity) {
        currentWeight += (checkEarliest(zip).getWeight());
        weightedList.add(deliverEarliest(zip));
      }
      else{
        return weightedList;
      }
    }
    return weightedList;
  }


  /** mergeBins combines all the Deliverables from zipcodes that start with the
   * inputted prefix into one MyQueue.
   * @param: Strng prefix to search for
   * */
  public void mergeBins(String prefix) {
    ArrayList<Deliverable> al = new ArrayList<>();
    ArrayList<Deliverable> items = new ArrayList<>();
    for (Deliverable key : deliveryBins.keySet()) {
      if (key.getZipCode().startsWith(prefix)) {
        items.add(key);
      }
    }
    for (int i = 0; i < items.size(); i++) {
      al.addAll(deliverAll(items.get(i).getZipCode()));
    }
    boolean swap = true;
    while (swap == true) {
      swap = false;
      for (int i = 0; i <= al.size() - 2; i++) {

        Deliverable first = al.get(i);
        Deliverable second = al.get(i + 1);
        if (first.getTimestamp() > second.getTimestamp()) {
          swap = true;
          al.set(i, second);
          al.set(i + 1, first);
        }
      }
    }
    while (prefix.length() < 5) {
      prefix += "-";
    }

    MyQueue combinedQueue = new MyQueue();
    Deliverable dummy = new MyMail(1,
            "3811 Nobel Drive, La Jolla, CA, USA, " + prefix,
            "2231 Lebon Drive, La Jolla, CA, USA, " + prefix,
            "Hello World!");
    for (int i = 0; i < al.size(); i++) {
      System.out.println("Test");
      combinedQueue.enqueue(al.get(i));
    }
    deliveryBins.put(dummy, combinedQueue);
  }

}
