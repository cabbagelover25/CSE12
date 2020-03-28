package com.cse.ds;



public abstract class Deliverable {

    protected int id;
    protected int weight;
    protected int timestamp;
    protected String fromAddress;
    protected String toAddress;

    public int getId() { return id; }
    public int getWeight() { return weight; }
    public int getTimestamp() { return timestamp; }
    public void setTimestamp(int timestamp) { this.timestamp = timestamp; }
    public String getFromAddress() { return fromAddress; }
    public String getToAddress() { return toAddress; }

    public abstract String getZipCode();

    /** Return hashcode based on reciever address String
     * @return: int representing hashCode
     * */
    @Override
    public int hashCode() {
        return getZipCode().hashCode();
    }

    /** Checks if this and obj are equal to each other by comparing their
     * getToAddresses, which is done by checking whether their hashCodes are
     * the same.
     * @param: Object to compare with this
     * @return: Boolean on whether equal or not.
     * */
    @Override
    public boolean equals(Object obj) {
        if (this.hashCode() == obj.hashCode()) {
            return true;
        } else {
            return false;
        }
    }

}
