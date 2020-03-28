package com.cse.ds;

public class MyPackage<E> extends Deliverable {

  private E content;


  /**Constructor for initializing MyPackage object.
   * @param: int of id
   * @param: String of fromAddress
   * @param: String of toAddress
   * @param: E type of content
   * @param: int of weight of package
   * */
  public MyPackage(int id, String fromAddress, String toAddress,
                 E content, int weight)
  {
    this.id = id;
    this.fromAddress = fromAddress;
    this.toAddress = toAddress;
    this.content = content;
    this.weight = weight;

  }
  
  public E getContent() { return this.content; }

  /** Returns zipCode of toAddress by seperating it from the rest of the
   * String.
   * @return: String of zipCode for toAddress*/
  @Override
  public String getZipCode() {

    /** Splits toAddress into Strings based on commas and removes all
     * whitespace. According to the instructions, the first part of the
     * address will always be the zip code. */
    String[] splitAddress = toAddress.trim().split("\\s*,\\s*");
    return splitAddress[0];
  }

}
