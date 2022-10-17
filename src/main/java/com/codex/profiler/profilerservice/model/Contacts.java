package com.codex.profiler.profilerservice.model;

import com.codex.profiler.profilerservice.entity.Address;
import com.codex.profiler.profilerservice.entity.PhoneNo;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class Contacts {

    @NotNull
    private List<PhoneNo> phoneNos;
    @NotNull
    private List<Address> addresses;

    public Contacts(List<PhoneNo> phoneNos, List<Address> addresses) {
        this.phoneNos = phoneNos;
        this.addresses = addresses;
    }
}
