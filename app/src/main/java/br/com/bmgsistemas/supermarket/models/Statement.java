package br.com.bmgsistemas.supermarket.models;

import android.text.format.DateFormat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import br.com.bmgsistemas.supermarket.enums.eStatementOperationType;
import br.com.bmgsistemas.supermarket.enums.eStatementStatus;
import br.com.bmgsistemas.supermarket.enums.eStatementType;

public class Statement {



    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("flag")
    @Expose
    private Double flag;

    @SerializedName("statementType")
    @Expose
    private eStatementType statementType;

    @SerializedName("statementStatus")
    @Expose
    private eStatementStatus statementStatus;

    @SerializedName("statementStatusCode")
    @Expose
    private String statementStatusCode;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("operationType")
    @Expose
    private eStatementOperationType operationType;

    @SerializedName("transferToFinanceAccountID")
    @Expose
    private Object transferToFinanceAccountID;

    @SerializedName("financeAccountID")
    @Expose
    private Double financeAccountID;

    @SerializedName("financeAccountName")
    @Expose
    private Object financeAccountName;

    @SerializedName("document")
    @Expose
    private Object document;

    @SerializedName("instituitionID")
    @Expose
    private Double instituitionID;

    @SerializedName("instituitionName")
    @Expose
    private Object instituitionName;

    @SerializedName("userID")
    @Expose
    private String userID;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("memo")
    @Expose
    private String memo;

    @SerializedName("categoryID")
    @Expose
    private int categoryID;

    @SerializedName("subCategoryID")
    @Expose
    private int subCategoryID;

    @SerializedName("categoryName")
    @Expose
    private String categoryName;

    @SerializedName("subCategoryName")
    @Expose
    private String subCategoryName;

    @SerializedName("creditAmount")
    @Expose
    private Double creditAmount;

    @SerializedName("debitAmount")
    @Expose
    private Double debitAmount;

    @SerializedName("amount")
    @Expose
    private Double amount;

    @SerializedName("balance")
    @Expose
    private Double balance;

    @SerializedName("statementTransferency")
    @Expose
    private StatementTransferency statementTransferency;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getFlag() {
        return flag;
    }

    public void setFlag(Double flag) {
        this.flag = flag;
    }

    public eStatementType getStatementType() {
        return statementType;
    }

    public void setStatementType(eStatementType statementType) {
        this.statementType = statementType;
    }

    public eStatementStatus getStatementStatus() {
        return statementStatus;
    }

    public void setStatementStatus(eStatementStatus statementStatus) {
        this.statementStatus = statementStatus;
    }

    public String getStatementStatusCode() {
        return statementStatusCode;
    }

    public void setStatementStatusCode(String statementStatusCode) {
        this.statementStatusCode = statementStatusCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public eStatementOperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(eStatementOperationType operationType) {
        this.operationType = operationType;
    }

    public Object getTransferToFinanceAccountID() {
        return transferToFinanceAccountID;
    }

    public void setTransferToFinanceAccountID(Object transferToFinanceAccountID) {
        this.transferToFinanceAccountID = transferToFinanceAccountID;
    }

    public Double getFinanceAccountID() {
        return financeAccountID;
    }

    public void setFinanceAccountID(Double financeAccountID) {
        this.financeAccountID = financeAccountID;
    }

    public Object getFinanceAccountName() {
        return financeAccountName;
    }

    public void setFinanceAccountName(Object financeAccountName) {
        this.financeAccountName = financeAccountName;
    }

    public Object getDocument() {
        return document;
    }

    public void setDocument(Object document) {
        this.document = document;
    }

    public Double getInstituitionID() {
        return instituitionID;
    }

    public void setInstituitionID(Double instituitionID) {
        this.instituitionID = instituitionID;
    }

    public Object getInstituitionName() {
        return instituitionName;
    }

    public void setInstituitionName(Object instituitionName) {
        this.instituitionName = instituitionName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getSubCategoryID() {
        return subCategoryID;
    }

    public void setSubCategoryID(int subCategoryID) {
        this.subCategoryID = subCategoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public Double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public Double getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(Double debitAmount) {
        this.debitAmount = debitAmount;
    }

    public Double getAmount() {

        if(amount == null)
            return 0.0;

        return amount;
    }

    public String getAmountFormated() {

        return "9.999.999,99";
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getBalance() {
        if(amount == null)
            return 0.0;

        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public StatementTransferency getStatementTransferency() {
        return statementTransferency;
    }

    public void setStatementTransferency(StatementTransferency statementTransferency) {
        this.statementTransferency = statementTransferency;
    }

    public String getDateDay()  {

        if(date == null || date.equals("0001-01-01T00:00:00-02:00"))
            return "";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sszzzz");
//        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
//        sdf.parse("2013-09-29T18:46:19Z");

//        SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
        Date dt1= null;
        try {
            dt1 = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();

            return "";
        }

        return (String) DateFormat.format("dd",   dt1);
    }

    public String getDateMonth()  {

        if(date == null || date.equals("0001-01-01T00:00:00-02:00"))
            return "";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sszzzz");

        Date dt1= null;
        try {
            dt1 = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();

            return "";
        }

        return (String) DateFormat.format("MM",   dt1);
    }

    public String getDateYear()  {

        if(date == null || date.equals("0001-01-01T00:00:00-02:00"))
            return "";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sszzzz");

        Date dt1= null;
        try {
            dt1 = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();

            return "";
        }

        return (String) DateFormat.format("yyyy",   dt1);
    }

    public String getDateShort()  {

        if(date == null || date.equals("0001-01-01T00:00:00-02:00"))
            return "";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sszzzz");

        Date dt1= null;
        try {
            dt1 = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();

            return "";
        }

        return (String) DateFormat.format("dd/MM/yyyy",   dt1);
    }

    public String getDateDayMonth()  {

        if(date == null || date.equals("0001-01-01T00:00:00-02:00"))
            return "";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sszzzz");

        Date dt1= null;
        try {
            dt1 = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();

            return "";
        }

        return (String) DateFormat.format("dd/MM",   dt1);
    }

    public String getDateWeekDay() {
        if(date == null || date.equals("0001-01-01T00:00:00-02:00"))
            return "";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sszzzz");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
//        SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
        Date dt1= null;
        try {
            dt1 = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();

            return "";
        }

        return (String) DateFormat.format("EE",   dt1);
    }

}
