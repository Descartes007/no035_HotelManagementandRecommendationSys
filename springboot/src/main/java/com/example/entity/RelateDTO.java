package com.example.entity;

//商城的信息
public class RelateDTO {
    /** 用户id */
    private Integer useId;
    /** 商品id */
    private Integer typeId;
    /** 指数 */
    private Integer index;

    public RelateDTO(Integer useId, Integer typeId, Integer index) {
        this.useId = useId;
        this.typeId = typeId;
        this.index = index;
    }

    public Integer getUseId() {
        return useId;
    }

    public void setUseId(Integer useId) {
        this.useId = useId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }


}