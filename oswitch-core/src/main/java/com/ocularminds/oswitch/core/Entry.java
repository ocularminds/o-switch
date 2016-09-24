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
    public Entry(final String deb, final String cre, final double amt) {
        this.debit = deb;
        this.credit = cre;
        this.amount = amt;
    }

    /**
     *
     * @return The id
     */
    public final String getId() {
        return this.id;
    }

    /**
     *
     * @param id The id
     */
    public void setId(final String idz) {
        this.id = idz;
    }

    /**
     *
     * @return The reference
     */
    public final String getReference() {
        return this.reference;
    }

    /**
     *
     * @param reference The reference
     */
    public void setReference(final String ref) {
        this.reference = ref;
    }

    /**
     *
     * @return The debit
     */
    public String getDebit() {
        return this.debit;
    }

    /**
     *
     * @param debit The debit
     */
    public final void setDebit(final String dbt) {
        this.debit = dbt;
    }

    /**
     *
     * @return The credit
     */
    public final String getCredit() {
        return this.credit;
    }

    /**
     *
     * @param credit The credit
     */
    public void setCredit(final String cdt) {
        this.credit = cdt;
    }

    /**
     *
     * @return The type
     */
    public final Type getType() {
        return this.type;
    }

    /**
     *
     * @param type The type
     */
    public void setType(final Type typ) {
        this.type = typ;
    }

    /**
     *
     * @return The amount
     */
    public double getAmount() {
        return this.amount;
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
        return this.date;
    }

    /**
     *
     * @param date The date
     */
    public final void setDate(final Date dat) {
        this.date = dat;
    }

    /**
     *
     * @return The beneficiary
     */
    public String getBeneficiary() {
        return this.beneficiary;
    }

    /**
     *
     * @param beneficiary The beneficiary
     */
    public final void setBeneficiary(final String ben) {
        this.beneficiary = ben;
    }

    /**
     *
     * @return The beneficiary
     */
    public final String getAccount() {
        return this.account;
    }

    /**
     *
     * @param beneficiary The beneficiary
     */
    public final void setAccount(final String acct) {
        this.account = acct;
    }

    /**
     *
     * @return The transaction narration
     */
    public final String getNarration() {
        return this.narration;
    }

    /**
     *
     * @param narrat The transaction narration
     */
    public final void setNarration(final String narrat) {
        this.narration = narrat;
    }

    @Override
    public String toString() {
        return new StringBuilder(this.debit).append(this.credit).append(this.amount)
                .append(this.reference).append(this.type).append(this.date)
                .append(this.beneficiary).toString();
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
