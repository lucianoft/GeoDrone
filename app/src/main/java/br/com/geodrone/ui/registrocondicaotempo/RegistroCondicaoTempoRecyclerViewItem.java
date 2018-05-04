package br.com.geodrone.ui.registrocondicaotempo;

public class RegistroCondicaoTempoRecyclerViewItem {

    // Save car name.
    private String carName;

    // Save car image resource id.
    private int carImageId;

    public RegistroCondicaoTempoRecyclerViewItem(String carName, int carImageId) {
        this.carName = carName;
        this.carImageId = carImageId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getCarImageId() {
        return carImageId;
    }

    public void setCarImageId(int carImageId) {
        this.carImageId = carImageId;
    }
}