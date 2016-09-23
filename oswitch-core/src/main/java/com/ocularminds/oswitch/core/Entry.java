package com.ocularminds.oswitch.core;

import java.util.Date;

public class Entry {

    private String id;
    private String reference;
    private String debit;
    private String credit;
    private Type type;
    private double amount;
    private String narration;
    private String account;
    private Date date;
    private String beneficiary;

    public enum Type {
        CREDIT, DEBIT;
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Entry() {}

    /**
     *
     * @param amount
     * @param id
     * @param debit
     * @param date
     * @param type
     * @param credit
     * @param beneficiary
     * @param reference
     */
    public Entry(final String debit, final String credit, final double amt) {
        this.debit = debit;
        this.credit = credit;
        this.amount = amount;
    }

    /**
     *
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return The reference
     */
    public String getReference() {
        return reference;
    }

    /**
     *
     * @param reference The reference
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     *
     * @return The debit
     */
    public String getDebit() {
        return debit;
    }

    /**
     *
     * @param debit The debit
     */
    public void setDebit(String debit) {
        this.debit = debit;
    }

    /**
     *
     * @return The credit
     */
    public String getCredit() {
        return credit;
    }

    /**
     *
     * @param credit The credit
     */
    public void setCredit(String credit) {
        this.credit = credit;
    }

    /**
     *
     * @return The type
     */
    public Type getType() {
        return type;
    }

    /**
     *
     * @param type The type
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     *
     * @return The amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     *
     * @param amount The amount
     */
    public void setAmount(final double amt) {
        this.amount = amt;
    }

    /**
     *
     * @return The date
     */
    public Date getDate() {
        return date;
    }

    /**
     *
     * @param date The date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     *
     * @return The beneficiary
     */
    public String getBeneficiary() {
        return beneficiary;
    }

    /**
     *
     * @param beneficiary The beneficiary
     */
    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    /**
     *
     * @return The beneficiary
     */
    public String getAccount() {
        return account;
    }

    /**
     *
     * @param beneficiary The beneficiary
     */
    public void setAccount(final String acct) {
        this.account = acct;
    }

    /**
     *
     * @return The transaction narration
     */
    public String getNarration() {
        return narration;
    }

    /**
     *
     * @param narrat The transaction narration
     */
    public void setNarration(final String narrat) {
        this.narration = narrat;
    }

    @Override
    public String toString() {
        return new StringBuilder(debit).append(credit).append(amount).append(reference)
                .append(type).append(date).append(beneficiary).toString();
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Entry) == false) {
            return false;
        }
        return ((Entry) other).toString().equals(this.toString());
    }

}
