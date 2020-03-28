package com.cse.ds;

public class MyMail extends Deliverable {

    private String message;

    /**Constructor for initializing MyMail object.
     * @param: int of id
     * @param: String of fromAddress
     * @param: String of toAddress
     * @param: String of message
     * */
    public MyMail(int id, String fromAddress, String toAddress, String message) {
        this.id = id;
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.message = message;
    }

    public String getMessage() { return this.message; }

    /** Returns zipCode of toAddress by seperating it from the rest of the
     * String.
     * @return: String of zipCode for toAddress*/
    @Override
    public String getZipCode() {
        /** Splits toAddress into Strings based on commas and removes all
        * whitespace. According to the instructions, the last part of the
        * address will always be the zip code. */
        String[] splitAddress = toAddress.trim().split("\\s*,\\s*");
        return splitAddress[splitAddress.length-1];
    }

}
