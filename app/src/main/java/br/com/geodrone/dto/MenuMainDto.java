package br.com.geodrone.dto;

/**
 * Created by fernandes on 16/06/2018.
 */

public class MenuMainDto {

    private Integer idMenu;
    private String descricao;
    private int imageId;

    public MenuMainDto(Integer idMenu, String descricao, int imageId) {
        this.idMenu = idMenu;
        this.descricao = descricao;
        this.imageId = imageId;
    }

    public MenuMainDto() {
    }

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
