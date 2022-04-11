package com.today.tix.assign.lottery.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserSlotIdentity implements Serializable{
	
	@NotNull
	@Column(name = "USER_ID")
	private Long user_id;
	
	@NotNull
	@Column(name = "SLOT_ID")
	private Long slot_id;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSlotIdentity that = (UserSlotIdentity) o;

        if (!user_id.equals(that.user_id)) return false;
        return slot_id.equals(that.slot_id);
    }
	
	@Override
    public int hashCode() {
        int result = user_id.hashCode();
        result = 31 * result + slot_id.hashCode();
        return result;
    }

}
