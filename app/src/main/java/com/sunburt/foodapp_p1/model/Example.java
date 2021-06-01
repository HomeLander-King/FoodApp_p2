
package com.sunburt.foodapp_p1.model;

import com.google.gson.annotations.SerializedName;

public class Example {
    @SerializedName("main")
    Main main;

    public Main getMain(){
        return main;
    }

    public void setMain(){
        this.main = main;
    }


}
