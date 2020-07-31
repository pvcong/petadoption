package com.ck.controller.commander;

import com.ck.dto.PetDTO;

public class PetCommand extends AbstractCommand<PetDTO> {
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
