package br.com.bmgsistemas.supermarket.models;

import com.google.gson.annotations.SerializedName;

public class StatementType {

    //region Fields
    @SerializedName("id")
    public int id;

    @SerializedName("code")
    public String code;

    @SerializedName("name")
    public String name;
    //endregion

    //region GET/SET
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion
}
